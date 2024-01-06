package kr.co.are.searchimage.presentation.ui.screen.search

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
import kr.co.are.searchimage.domain.usecase.GetPhotoInfoListUseCase
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getPhotoInfoListUseCase: GetPhotoInfoListUseCase,
    private val getPagingPhotoInfoListUseCase: GetPagingPhotoInfoListUseCase,
) : ViewModel() {

    //private val _photoListPager = MutableLiveData<Pager<Int, PhotoDetailEntity>>()
    lateinit var photoListPager: Flow<PagingData<PhotoDetailEntity>>

    init{
        getPagingPhotoInfoList()
    }

    private fun getPagingPhotoInfoList() {
        viewModelScope.launch {
            getPagingPhotoInfoListUseCase()
                .catch {
                    Logger.e(it, it.message.toString())
                }
                .collectLatest {
                    photoListPager = it.flow.cachedIn(viewModelScope)
                }
        }
    }

}