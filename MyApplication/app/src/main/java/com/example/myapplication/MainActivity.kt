package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.ApiMovies
import com.example.myapplication.service.HttpServiceImp
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitNav()
    }
    //*********************************************************************************** Navigaion

    private fun InitNav(){

        val toolbar = findViewById<Toolbar>(R.id.custom_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val bottomNav: BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.navHost)
        //val toolbar = binding.toolbar
        //setSupportActionBar(toolbar)
        //val appBarConfiguration = AppBarConfiguration()

        NavigationUI.setupActionBarWithNavController(this, navController)
        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{controller, destination, argumetn ->
            when(destination.id){
                R.id.listMovieFragment -> {
                    toolbar.isVisible = true;
                    bottomNav.isVisible = true;
                    ShowHideElement("")
                }
                //api_key
                R.id.detailMovieFragment -> {
                    toolbar.isVisible = true;
                    bottomNav.isVisible = true;
                    ShowHideElement("Détail")

                }
                R.id.trendingFragment -> {
                    toolbar.isVisible = true;
                    bottomNav.isVisible = true;
                    ShowHideElement("A l'affiche")

                }
                else -> toolbar.title = null
            }
        }
    }


    private fun ShowHideElement(titre : String){
        var bShow : Boolean = true
        var ActionTxtTitre = findViewById<TextView>(R.id.ActionTxtTitre)
        var ActionTxtSearch = findViewById<TextView>(R.id.ActionTxtSearch)
        var ActionBarBtnOk = findViewById<ImageButton>(R.id.ActionBarBtnOk)

        if(titre == ""){
            bShow = false
        }else{
            ActionTxtTitre.text = titre
        }

        ActionTxtTitre.isVisible = bShow
        ActionTxtSearch.isVisible = !bShow
        ActionBarBtnOk.isVisible = !bShow

    }

    override fun onSupportNavigateUp() : Boolean {
        val navController = findNavController(R.id.navHost)
        return  navController.navigateUp()
    }
}