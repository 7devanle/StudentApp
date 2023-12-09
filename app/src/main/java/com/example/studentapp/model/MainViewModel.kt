package com.example.studentapp.model


import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.studentapp.MyApplication
import com.example.studentapp.entities.Admin
import com.example.studentapp.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val _authenticationResult = MutableLiveData<Boolean>()
    val authenticationResult:LiveData<Boolean> get() = _authenticationResult

    fun validateAdmin(username: String, password: String)=viewModelScope.launch {
        val admin = mainRepository.validateAdmin(username, password)
        _authenticationResult.value = admin != null
    }
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                return MainViewModel(
                    (application as MyApplication).repository
                ) as T
            }
        }
    }
}