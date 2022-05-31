package com.example.a64thanc

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = "64th ANC"
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_camera)
        val fragmentManager = supportFragmentManager
        val fragment = ImportFragment()
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        val type: String
        var fragment: Fragment? = null
        val fragmentManager = supportFragmentManager
        if (id == R.id.nav_camera) {
            fragment = ImportFragment()
        } else if (id == R.id.nav_map) {
            fragment = MapFragment()
        } else if (id == R.id.nav_keynote) {
            type = "Keynote"
            fragment = EventFragment(type)
        } else if (id == R.id.nav_masterclass) {
            type = "Masterclass"
            fragment = EventFragment(type)
        } else if (id == R.id.nav_seminar) {
            type = "Seminar"
            fragment = EventFragment(type)

        } else if (id == R.id.nav_handshake) {
            type = "Handshake"
            fragment = EventFragment(type)
        } else if (id == R.id.nav_exhibition) {
            type = "Exhibition"
            fragment = EventFragment(type)
        } else if (id == R.id.nav_workshop) {
            type = "Workshop"
            fragment = EventFragment(type)
        } else if (id == R.id.nav_onspot) {
            type = "Onspot"
            fragment = EventFragment(type)
        } else if (id == R.id.nav_culturals) {
            type = "Culturals"
            fragment = EventFragment(type)
        } else if (id == R.id.nav_food) {
            type = "Food"
            fragment = EventFragment(type)
        } else if (id == R.id.nav_contact) {
            fragment = ContactFragment()
        }
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment!!).commit()
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}