package kr.co.are.searchimage.presentation.ui.component

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.are.searchimage.domain.usecase.CheckAppInitUseCase
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val checkAppInitUseCase:CheckAppInitUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    fun callTest(){
        viewModelScope.launch {
            checkAppInitUseCase()
                .catch {  }
                .collectLatest {
                    _text.value = it
                }
        }
    }

}