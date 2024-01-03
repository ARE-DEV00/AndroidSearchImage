package kr.co.are.searchimage.presentation.ui.component

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.are.searchimage.domain.usecase.TestUseCase
import javax.inject.Inject

@HiltViewModel
class ButtonTestViewModel @Inject constructor(
    private val checkAppInitUseCase:TestUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private var callCnt = 0

    fun callTest(title:String){
        viewModelScope.launch {
            checkAppInitUseCase(title)
                .catch {
                    print(it)
                }
                .collectLatest {
                    print("[TEST]$it")
                    _text.value = "[${callCnt++}]${it}"
                }
        }
    }

}