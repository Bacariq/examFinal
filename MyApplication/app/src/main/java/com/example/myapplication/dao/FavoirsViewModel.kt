package com.example.myapplication.dao

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class FavoirsVM  {
    companion object {
        @Volatile
        var favoirsViewModel: FavoirsViewModel? = null

        fun get(owner: ViewModelStoreOwner) : FavoirsViewModel {
            if (favoirsViewModel != null) return favoirsViewModel!!
            synchronized(this) {
                favoirsViewModel = ViewModelProvider(owner).get(FavoirsViewModel::class.java)
                return favoirsViewModel!!
            }
        }
    }
}


class FavoirsViewModel : ViewModel() {

    fun getAllFavorisList(context: Context): List<Favoris> {
        return dbRepository.getAllFavorisList(context)
    }

    fun getAllFavoris(context: Context): LiveData<List<Favoris>> {
        return dbRepository.getAllFavoris(context)
    }
    fun addFavoris(context: Context, name : String) {
        dbRepository.insertFavoris(context,name)
    }

    fun deleteFavoris(context: Context, favoris : Favoris) {
        dbRepository.deleteFavoris(context,favoris)
    }

    fun deleteFavorisById(context: Context, id : String) {
        dbRepository.deleteFavorisById(context, id)
    }
}
