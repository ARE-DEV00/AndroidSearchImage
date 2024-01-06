package kr.co.are.searchimage.presentation.ui.component.photolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.usecase.GetPhotoInfoListUseCase
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val getPhotoInfoListUseCase: GetPhotoInfoListUseCase,
) : ViewModel() {

    private val _photoList = MutableLiveData<Array<PhotoDetailEntity>>()
    val photoList: LiveData<Array<PhotoDetailEntity>> = _photoList


    private var currentPhotoList: Array<PhotoDetailEntity> = emptyArray()

    fun getPhotoInfoList(page: Int) {
        Logger.d("#### getPhotoInfoList")
        viewModelScope.launch {
            getPhotoInfoListUseCase(page, 100)
                .catch {
                    Logger.e(it, "")
                }
                .collectLatest {
                    Logger.d("#### ButtonTestViewModel-getPhotoInfoList")
                    it.forEachIndexed { index, obj ->
                        Logger.d("$index: ${obj.id} / ${obj.thumb}")
                    }

                    currentPhotoList += it
                    _photoList.value = currentPhotoList

                }
        }
    }

}