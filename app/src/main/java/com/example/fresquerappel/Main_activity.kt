package com.example.fresquerappel


import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fresquerappel.ui.recherche.MainViewModel
import com.example.fresquerappel.ui.recherche.RechercheDialogueFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView


class Main_activity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        //Nous definissons le ViewModel
        mainViewModel = MainViewModel.getInstance(this, applicationContext)!!

        //On définit le menu latéral
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_navigation_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /* Défini le bouton de recherche -- Debut */
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
             //   .setAction("Action", null).show()

            val dialogFragment = RechercheDialogueFragment()
            val bundle = Bundle()
            bundle.putBoolean("notAlertDialog", true)
            dialogFragment.arguments = bundle
            val ft = supportFragmentManager.beginTransaction()
            val prev = supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null)
            {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            dialogFragment.show(ft, "dialog")

        }
        /* Défini le bouton de recherche -- Fin */


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        /* Défini menu lateral -- Debut */
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home //Cet Id doit obligatoirement se trouver dans le fichier mobile_navigation.xml
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navView.setNavigationItemSelectedListener { menuItem ->




            mainViewModel.changeCollage(menuItem.title.toString())

            drawerLayout.closeDrawers()
            true
        }
        /* Défini menu lateral -- Fin */

        //La VM change le nom de la fresque active
        mainViewModel.name.observe(this, Observer {
            val actionBar: ActionBar? = supportActionBar
            actionBar?.setTitle(it.toString())
        })

    }

    //Creation menu
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
}