package com.example.alejandro_sanabria

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelMain: ViewModel(){

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    private val _result = MutableLiveData<Boolean>(null)
    val result = _result

    fun doLogin(user: UserModel){
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)

            delay(3000)

            _loading.postValue(false)

            _result.postValue(user.name == "alejandro" && user.password == "123ale")

        }
    }

}