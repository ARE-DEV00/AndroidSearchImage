package kr.co.are.searchimage.presentation.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.are.searchimage.domain.entity.PhotoDetailEntity
import kr.co.are.searchimage.domain.usecase.AddBookmarkInfoDbUseCase
import kr.co.are.searchimage.domain.usecase.DeleteBookmarkInfoDbUseCase
import kr.co.are.searchimage.domain.usecase.GetBookmarkInfoDbUseCase
import kr.co.are.searchimage.domain.usecase.GetPhotoDetailInfoUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getPhotoDetailInfoUseCase: GetPhotoDetailInfoUseCase,
    private val getBookmarkInfoDbUseCase: GetBookmarkInfoDbUseCase,
    private val addBookmarkInfoDbUseCase: AddBookmarkInfoDbUseCase,
    private val deleteBookmarkInfoDbUseCase: DeleteBookmarkInfoDbUseCase
) : ViewModel() {
    private val _loadPhotoDetailEntity = MutableLiveData<PhotoDetailEntity>()
    val loadPhotoDetailEntity: LiveData<PhotoDetailEntity> = _loadPhotoDetailEntity

    private val _isBookmark = MutableLiveData<Boolean>()
    val isBookmark: LiveData<Boolean> = _isBookmark


    fun getPhotoDetailInfo(id: String) {
        viewModelScope.launch {
            getPhotoDetailInfoUseCase(id = id)
                .catch {
                    Timber.e(it, it.message.toString())
                }
                .collectLatest {
                    _loadPhotoDetailEntity.value = it
                }
        }
        viewModelScope.launch {
            getBookmarkInfo(id)
        }
    }

    private fun getBookmarkInfo(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getBookmarkInfoDbUseCase(id = id)
                .catch {

                }.collectLatest {
                    if (it != null) {
                        _isBookmark.postValue(true)
                    } else {
                        _isBookmark.postValue(false)
                    }
                }
        }
    }

    fun addBookmarkInfo(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            _loadPhotoDetailEntity.value?.let {
                addBookmarkInfoDbUseCase(it)
                    .catch {

                    }.collectLatest {
                        _isBookmark.postValue(true)
                    }
            }
        }
    }

    fun deleteBookmarkInfo(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            deleteBookmarkInfoDbUseCase(id)
                .catch {

                }.collectLatest {
                    _isBookmark.postValue(false)
                }
        }
    }

}