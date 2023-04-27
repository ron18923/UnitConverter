package com.example.unitconverter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConverterDAO {

    @Insert
    suspend fun insertResult(result: ConversionResult)

    @Delete
    suspend fun deleteResult(result: ConversionResult)

    @Query("DELETE FROM result_table")
    suspend fun deleteAll()

    //no need for a suspend fun here because for 'select all' queries Room executes on it's own the
    //function on a separate coroutine
    @Query("SELECT * FROM result_table")
    fun getResults(): Flow<List<ConversionResult>>
}