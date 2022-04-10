package com.l3azh.flowexample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSource : IFakeSource{

    override fun getListFakeData(): Flow<List<FakeData>> {
        return flow {
            val listData = FakeSource.provideFakeListData()
            emit(listData)
        }
    }

    companion object{
        private const val delayTime = 5000L

        suspend fun provideFakeListData():List<FakeData>{
            delay(delayTime)
            return listOf(
                FakeData("1", "John", "Michael"),
                FakeData("2","Sam", "Ram"),
                FakeData("3","My", "Man"),
                FakeData("4","Rick", "Morty"),
                FakeData("5","Tim", "Tam"),
                FakeData("6","Chi", "Rich"),
                FakeData("7","Duck", "Dick")
            )
        }
    }
}