package kr.co.are.searchimage.presentation.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.usecase.GetPagingPhotoInfoListUseCase
import kr.co.are.searchimage.domain.usecase.GetPhotoDetailInfoUseCase
import kr.co.are.searchimage.domain.usecase.GetPhotoInfoListUseCase
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getPhotoDetailInfoUseCase: GetPhotoDetailInfoUseCase
) : ViewModel() {
    private val _loadPhotoDetailEntity = MutableLiveData<PhotoDetailEntity>()
    val loadPhotoDetailEntity: LiveData<PhotoDetailEntity> = _loadPhotoDetailEntity

    fun getPhotoDetailInfo(id: String) {
        viewModelScope.launch {
            getPhotoDetailInfoUseCase(id = id)
                .catch {
                    Logger.e(it, it.message.toString())
                }
                .collectLatest {
                    _loadPhotoDetailEntity.value = it
                }
        }

    }

}