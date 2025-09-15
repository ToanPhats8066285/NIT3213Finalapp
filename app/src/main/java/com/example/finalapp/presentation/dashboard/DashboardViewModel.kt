package com.example.finalapp.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.data.models.DashboardResponse
import com.example.finalapp.domain.usecase.GetDashboardUseCase
import com.example.finalapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboard: GetDashboardUseCase
) : ViewModel() {

    private val _state = MutableLiveData<Resource<DashboardResponse>>()
    val state: LiveData<Resource<DashboardResponse>> = _state

    fun load(keypass: String) {
        viewModelScope.launch {
            getDashboard(keypass).collect { res ->
                _state.value = res
            }
        }
    }
}
