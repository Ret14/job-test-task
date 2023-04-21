package com.ntpro.mobileandroiddevtestwork.data.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DealDao {
    @Query(
        "SELECT * FROM deal ORDER BY " +
                "CASE WHEN :isAsc = 1 THEN timeStamp END ASC, " +
                "CASE WHEN :isAsc = 0 THEN timeStamp END DESC"
    )
    fun readAllByTime(isAsc: Boolean): PagingSource<Int, LocalDeal>

    @Query(
        "SELECT * FROM deal ORDER BY " +
                "CASE WHEN :isAsc = 1 THEN instrumentName END ASC, " +
                "CASE WHEN :isAsc = 0 THEN instrumentName END DESC"
    )
    fun readAllByInstrumentName(isAsc: Boolean): PagingSource<Int, LocalDeal>

    @Query(
        "SELECT * FROM deal ORDER BY " +
                "CASE WHEN :isAsc = 1 THEN price END ASC, " +
                "CASE WHEN :isAsc = 0 THEN price END DESC"
    )
    fun readAllByPrice(isAsc: Boolean): PagingSource<Int, LocalDeal>

    @Query(
        "SELECT * FROM deal ORDER BY " +
                "CASE WHEN :isAsc = 1 THEN amount END ASC, " +
                "CASE WHEN :isAsc = 0 THEN amount END DESC"
    )
    fun readAllByAmount(isAsc: Boolean): PagingSource<Int, LocalDeal>

    @Query(
        "SELECT * FROM deal ORDER BY " +
                "CASE WHEN :isAsc = 1 THEN side END ASC, " +
                "CASE WHEN :isAsc = 0 THEN side END DESC"
    )
    fun readAllBySide(isAsc: Boolean): PagingSource<Int, LocalDeal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(deals: List<LocalDeal>)

    @Query("DELETE FROM deal")
    suspend fun deleteAll()
}