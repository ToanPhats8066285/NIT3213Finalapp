package com.example.finalapp.domain.repository

import com.example.finalapp.data.models.DashboardResponse
import com.example.finalapp.data.models.LoginRequest
import com.example.finalapp.data.models.LoginResponse

interface AppRepositoryInterface {
    suspend fun login(campus: String, request: LoginRequest): LoginResponse
    suspend fun getDashboard(keypass: String): DashboardResponse
}
