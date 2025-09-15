// domain/usecase/GetDashboardUseCase.kt
package com.example.finalapp.domain.usecase

import com.example.finalapp.domain.repository.AppRepositoryInterface
import com.example.finalapp.data.models.DashboardResponse
import com.example.finalapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDashboardUseCase @Inject constructor(
    private val repo: AppRepositoryInterface
) {
    operator fun invoke(keypass: String): Flow<Resource<DashboardResponse>> = flow {
        emit(Resource.Loading())
        try {
            val data = repo.getDashboard(keypass) // suspend
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }
}
