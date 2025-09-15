package com.example.finalapp.domain.usecase

import com.example.finalapp.data.models.LoginRequest
import com.example.finalapp.data.models.LoginResponse
import com.example.finalapp.domain.repository.AppRepositoryInterface
import com.example.finalapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: AppRepositoryInterface
) {
    operator fun invoke(
        campus: String,
        username: String,
        password: String
    ): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        try {
            val res = repo.login(
                campus = campus,
                request = LoginRequest(username = username, password = password)
            )
            emit(Resource.Success(res))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }
}
