package com.l3azh.flowexample

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    lateinit var apiRepository:IFakeSource
    lateinit var state:MutableState<UIState>
    var listData:MutableState<List<FakeData>?> = mutableStateOf(null)

    init {
        apiRepository = FakeSource()
        state = mutableStateOf(
            UIState(
            true,
            false,
            false,
            false, "",
            false
        )
        )
    }

    fun getListDataFLow():Flow<List<FakeData>> = apiRepository.getListFakeData()
    fun resetToBegin(){
        state.value = UIState(
            true, false, false,
            false, "",false)
    }
    fun requestGetListData(){
        state.value = UIState(
            false, false, true,
            false, "",false)
        CoroutineScope(Dispatchers.IO).launch {
            getListDataFLow()
                .catch { cause: Throwable ->
                    state.value = UIState(
                        false, false, true,
                        true, cause.message!!,false)
                }
                .collect { resultList ->
                    listData.value = resultList
                    state.value = UIState(
                        false, false, false,
                        false, "",true)
                }
        }
    }

    fun onGetDataSuccess(){
        state.value = UIState(
            false, true, false,
            false, "",false)
    }
}

class UIState(
    var isBeginState:Boolean,
    var isShowListState:Boolean,
    var isLoading:Boolean,
    var onError:Boolean,
    var errMessage:String,
    var onSuccess:Boolean
){

}