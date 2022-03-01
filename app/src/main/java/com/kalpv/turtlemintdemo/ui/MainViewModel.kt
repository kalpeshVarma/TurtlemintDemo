package com.kalpv.turtlemintdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalpv.turtlemintdemo.data.model.Comment
import com.kalpv.turtlemintdemo.data.model.Issues
import com.kalpv.turtlemintdemo.data.repositories.MainRepository
import com.kalpv.turtlemintdemo.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _issueList = MutableLiveData<DataState<List<Issues>>>()
    val issueList: LiveData<DataState<List<Issues>>>
        get() = _issueList

    private val _commentList = MutableLiveData<DataState<List<Comment>>>()
    val commentList: LiveData<DataState<List<Comment>>>
        get() = _commentList


    fun fetchIssues() {
        viewModelScope.launch {
            repository.fetchAllIssues()
                .onEach {
                    _issueList.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun fetchComments(commentsUrls: String, issueId: Int) {
        viewModelScope.launch {
            repository.fetchAllComments(commentsUrls, issueId)
                .onEach {
                    _commentList.value = it
                }.launchIn(viewModelScope)
        }
    }


}