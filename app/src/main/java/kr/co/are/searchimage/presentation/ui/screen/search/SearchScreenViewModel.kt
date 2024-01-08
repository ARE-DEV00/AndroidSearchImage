package kr.co.are.searchimage.presentation.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.usecase.GetPhotoInfoPagingListUseCase
import kr.co.are.searchimage.domain.usecase.GetPhotoInfoListUseCase
import kr.co.are.searchimage.domain.usecase.GetSearchPhotoInfoPagingListUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getPhotoInfoPagingListUseCase: GetPhotoInfoPagingListUseCase,
    private val getSearchPhotoInfoPagingListUseCase: GetSearchPhotoInfoPagingListUseCase,
) : ViewModel() {

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _photoListPager = MutableStateFlow<Flow<PagingData<PhotoDetailEntity>>?>(null)
    val photoListPager: Flow<PagingData<PhotoDetailEntity>> get() = _photoListPager.value!!

    private val _searchPhotoListPager = MutableStateFlow<Flow<PagingData<PhotoDetailEntity>>?>(null)
    val searchPhotoListPager: Flow<PagingData<PhotoDetailEntity>> get() = _searchPhotoListPager.value!!

    init {

        getPhotoInfoPagingList()
        getSearchPhotoInfoPagingList("")
    }

    private fun getPhotoInfoPagingList() {
        viewModelScope.launch {
            getPhotoInfoPagingListUseCase()
                .catch {
                    Timber.e(it)
                }
                .collectLatest {
                    _photoListPager.value = it.flow.cachedIn(viewModelScope)
                }
        }
    }

    private fun getSearchPhotoInfoPagingList(text:String){
        viewModelScope.launch {
            getSearchPhotoInfoPagingListUseCase(text)
                .catch {
                    Timber.e(it)
                }
                .collectLatest {
                    _searchPhotoListPager.value = it.flow.cachedIn(viewModelScope)
                }
        }
    }
    fun search(text: String) {
        _searchText.value = text

        if(text.isNotEmpty()){
            getSearchPhotoInfoPagingList(text)
        }
    }
}