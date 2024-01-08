package kr.co.are.searchimage.presentation.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.usecase.GetBookmarkInfoDbPagingListUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookmarkScreenViewModel @Inject constructor(
    private val getBookmarkInfoDbPagingListUseCase: GetBookmarkInfoDbPagingListUseCase,
) : ViewModel() {
    private val _bookmarkInfoListPager = MutableStateFlow<Flow<PagingData<PhotoDetailEntity>>?>(null)
    val bookmarkInfoListPager: Flow<PagingData<PhotoDetailEntity>> get() = _bookmarkInfoListPager.value!!

    init {
        getBookmarkInfoPagingList()
    }

    private fun getBookmarkInfoPagingList() {
        viewModelScope.launch {
            getBookmarkInfoDbPagingListUseCase()
                .catch {
                    Timber.e(it)
                    it.printStackTrace()
                }
                .collectLatest {
                    _bookmarkInfoListPager.value = it.flow.cachedIn(viewModelScope)
                }
        }
    }

}