package com.example.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavorisDao {

    @Query("SELECT * FROM Favoris")
    fun getAll(): LiveData<List<Favoris>>

    @Query("SELECT * FROM Favoris")
    fun getAllList(): List<Favoris>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(daoFavoris: Favoris): Long

    @Delete
    suspend fun delete(daoFavoris: Favoris)

    @Query("DELETE FROM favoris WHERE id = :favorisId")
    suspend fun deleteById(favorisId: String)

}
