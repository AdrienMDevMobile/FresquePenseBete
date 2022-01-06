package com.micheladrien.fresquerappel.View.view


import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Main_activity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    @Inject lateinit var collageDataManager: CollageDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        /* Défini menu lateral -- Debut */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_navigation_activity)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        //Set up l'hamburger à droite
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_relation, R.id.nav_timer, R.id.nav_single,  R.id.nav_about
                ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    //Creation menu hamburger
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //Affichage du menu
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //Retrait du menu sur le boutton back
    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}