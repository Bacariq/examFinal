package com.example.myapplication.dao

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Favoris::class), version = 1)
abstract class FavorisDatabase : RoomDatabase() {
    abstract fun favorisDao(): FavorisDao

    companion object {

        @Volatile
        private var sharedInstance: FavorisDatabase? = null

        fun getDB(context: Context) : FavorisDatabase {
            if (sharedInstance != null) return sharedInstance!!
            synchronized(this) {
                sharedInstance = Room
                    .databaseBuilder(context, FavorisDatabase::class.java, "movie.db")
                    .fallbackToDestructiveMigration()
                    .build()
                return sharedInstance!!
            }
        }
    }
}


class dbRepository {
    companion object {
        var favorisDatabase: FavorisDatabase? = null

        fun initializeDB(context: Context) : FavorisDatabase {
            val db = FavorisDatabase.getDB(context)
            return db
        }

        //************************************************************************* Catégories

        fun insertFavoris(context: Context, name: String) {
            if(favorisDatabase == null) {
                favorisDatabase = initializeDB(context)
            }

            CoroutineScope(Dispatchers.IO).launch {
                var favoris : Favoris = Favoris(0, name)
                favorisDatabase!!.favorisDao().insert(favoris)
            }
        }

        fun getAllFavoris(context: Context): LiveData<List<Favoris>> {
            if(favorisDatabase == null) {
                favorisDatabase = initializeDB(context)
            }
            return favorisDatabase!!.favorisDao().getAll()
        }

        fun getAllFavorisList(context: Context): List<Favoris> {
            if(favorisDatabase == null) {
                favorisDatabase = initializeDB(context)
            }
            return favorisDatabase!!.favorisDao().getAllList()
        }

        fun deleteFavoris(context: Context, favoris: Favoris) {
            if(favorisDatabase == null) {
                favorisDatabase = initializeDB(context)
            }

            CoroutineScope(Dispatchers.IO).launch {
                favorisDatabase!!.favorisDao().delete(favoris)
            }
        }

        fun deleteFavorisById(context: Context, favorisId: String) {
            if(favorisDatabase == null) {
                favorisDatabase = initializeDB(context)
            }

            CoroutineScope(Dispatchers.IO).launch {
                favorisDatabase!!.favorisDao().deleteById(favorisId)
            }
        }

    }
}
