package com.example.finalapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.data.models.LoginResponse
import com.example.finalapp.domain.usecase.LoginUseCase
import com.example.finalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableLiveData<Resource<LoginResponse>>()
    val state: LiveData<Resource<LoginResponse>> = _state

    fun login(campus: String, username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(campus, username, password).collect { result ->
                _state.value = result
            }
        }
    }
}
