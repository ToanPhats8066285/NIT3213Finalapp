package com.example.finalapp.data.repository

import com.example.finalapp.data.api.ApiService
import com.example.finalapp.data.models.DashboardResponse
import com.example.finalapp.data.models.LoginRequest
import com.example.finalapp.data.models.LoginResponse
import com.example.finalapp.domain.repository.AppRepositoryInterface
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val api: ApiService
) : AppRepositoryInterface {

    override suspend fun login(campus: String, request: LoginRequest): LoginResponse {
        return api.login(campus, request)
    }

    override suspend fun getDashboard(keypass: String): DashboardResponse {
        return api.getDashboard(keypass)
    }
}
