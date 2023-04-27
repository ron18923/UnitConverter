package com.example.unitconverter.data

import kotlinx.coroutines.flow.Flow

interface ConverterRepository {

    suspend fun insertResult(result: ConversionResult)
    suspend fun deleteResult(result: ConversionResult)
    suspend fun deleteAll()
    fun getSavedResults(): Flow<List<ConversionResult>>

}