package com.l3azh.flowexample

import kotlinx.coroutines.flow.Flow

interface IFakeSource {

    fun getListFakeData(): Flow<List<FakeData>>
}