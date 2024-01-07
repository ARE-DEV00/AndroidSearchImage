package kr.co.are.searchimage.presentation.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.usecase.GetPhotoInfoPagingListUseCase
import kr.co.are.searchimage.domain.usecase.GetPhotoInfoListUseCase
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getPhotoInfoListUseCase: GetPhotoInfoListUseCase,
    private val getPhotoInfoPagingListUseCase: GetPhotoInfoPagingListUseCase,
) : ViewModel() {

    private val _searchText = mutableStateOf("")
    val searchText:State<String> = _searchText


    //private val _photoListPager = MutableLiveData<Pager<Int, PhotoDetailEntity>>()
    lateinit var photoListPager: Flow<PagingData<PhotoDetailEntity>>


    init{
        getPagingPhotoInfoList()
    }

    private fun getPagingPhotoInfoList() {
        viewModelScope.launch {
            getPhotoInfoPagingListUseCase()
                .catch {
                    Logger.e(it, it.message.toString())
                }
                .collectLatest {
                    photoListPager = it.flow.cachedIn(viewModelScope)
                }
        }
    }

    fun search(text:String){
        _searchText.value = text
    }
}