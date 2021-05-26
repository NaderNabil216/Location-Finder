package com.examples.core.base.activity

import android.content.Context
import android.os.Bundle
import com.examples.data.source.prefrences.StorageManager
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.examples.core.R
import com.examples.core.utils.LanguageUtils
import com.examples.core.utils.LoadingListener
import com.examples.core.utils.LocaleUtils
import com.examples.core.utils.VersionUtils
import kotlinx.android.synthetic.main.activity_common.*
import javax.inject.Inject

/**
*  Created by Nader Nabil
*/
abstract class BaseActivity : AppCompatActivity(), LoadingListener{
    private val TAG = BaseActivity::class.java.simpleName

    private lateinit var navFragment: NavHostFragment
    lateinit var navController: NavController

    abstract var navGraphResourceId: Int
    private lateinit var bundle: Bundle

    @LayoutRes
    open var layoutRes = R.layout.activity_common

    @Inject
    lateinit var storageManager: StorageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!VersionUtils.isOreoAndLater){
            LanguageUtils.setLocale(this)
        }
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        setNavFragment()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleUtils.onAttach(base))
    }


    private fun setNavFragment() {
        navFragment =
            supportFragmentManager.findFragmentById(R.id.common_nav_fragment) as NavHostFragment
        navController = navFragment.navController
        if (::bundle.isInitialized) navController.setGraph(navGraphResourceId, bundle)
        else navController.setGraph(navGraphResourceId)


    }


    fun setFragmentDestination(@IdRes resId: Int, bundle: Bundle?) =
        navController.navigate(resId, bundle)

    fun popupFragment() = navController.popBackStack()

    override fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onBackPressed() {
      super.onBackPressed()
    }
}