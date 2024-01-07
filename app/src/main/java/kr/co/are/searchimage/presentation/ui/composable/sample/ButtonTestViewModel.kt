package kr.co.are.searchimage.presentation.ui.composable.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.are.searchimage.domain.usecase.GetPhotoDetailInfoUseCase
import kr.co.are.searchimage.domain.usecase.GetPhotoInfoListUseCase
import kr.co.are.searchimage.domain.usecase.GetSearchPhotoInfoListUseCase
import kr.co.are.searchimage.domain.usecase.TestUseCase
import javax.inject.Inject

@HiltViewModel
class ButtonTestViewModel @Inject constructor(
    private val testUseCase: TestUseCase,
    private val getPhotoInfoListUseCase: GetPhotoInfoListUseCase,
    private val getPhotoDetailInfoUseCase: GetPhotoDetailInfoUseCase,
    private val searchPhotoInfoListUseCase: GetSearchPhotoInfoListUseCase,
) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private var callCnt = 0

    fun callTest(title: String) {
        viewModelScope.launch {
            testUseCase(title)
                .catch {

                }
                .collectLatest {
                    print("[TEST]$it")
                    _text.value = "[${callCnt++}]${it}"
                }
        }
    }

    fun getPhotoInfoList(page: Int) {
        viewModelScope.launch {
            /*getPhotoInfoListUseCase(page, 10)
                .catch {
                    Logger.e(it,"")

                }
                .collectLatest {
                    Logger.d("#### ButtonTestViewModel-getPhotoInfoList")

                    it.forEachIndexed { index, obj ->
                        Logger.d("$index: ${obj.id} / ${obj.thumb}")
                    }
                }*/
            searchPhotoInfoListUseCase("test", page, 10)
                .catch {
                    Logger.e(it, "")

                }
                .collectLatest {
                    Logger.d("#### ButtonTestViewModel-getPhotoInfoList")
                    it.list.forEachIndexed { index, obj ->
                        Logger.d("$index: ${obj.imageInfo}")
                    }
                }
        }
    }

}