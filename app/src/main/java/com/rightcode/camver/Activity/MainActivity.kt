package com.rightcode.camver.Activity

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.databinding.ActivityMainBinding
import com.rightcode.camver.fragment.*

class MainActivity : BaseActivity<ActivityMainBinding>() {
    var fm: FragmentManager? = null
    var transaction: FragmentTransaction? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_main)
    }

    override fun initActivity() {
        fm = supportFragmentManager
        replaceFragment(Menu1Fragment.newInstance())
    }

    override fun initClickListener() {
        dataBinding.navMenu.dispatchSetSelected(false)
        dataBinding.navMenu.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.navigation_1 -> {
                        Log.d("1번째")
                        replaceFragment(Menu1Fragment.newInstance())
                        return true
                    }
                    R.id.navigation_2 -> {
                        replaceFragment(Menu2Fragment.newInstance())
                        Log.d("2번째")
                        return true
                    }
                    R.id.navigation_3 -> {
                        replaceFragment(Menu3Fragment.newInstance())
                        Log.d("3번째")
                        return true
                    }
                    R.id.navigation_4 -> {
                        replaceFragment(Menu4Fragment.newInstance())
                        Log.d("4번째")
                        return true
                    }
                    R.id.navigation_5 -> {
                        replaceFragment(Menu5Fragment.newInstance())
                        Log.d("5번째")
                        return true
                    }
                }
                return true
            }
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        transaction = fm!!.beginTransaction()
        transaction!!.replace(R.id.frame_container, fragment)
        transaction!!.commit()
    }
}