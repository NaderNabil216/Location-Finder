package com.examples.core.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController

/*
*  Created by Nader Nabil.
*/
class FragmentExtensions {
    companion object {
        val FragmentManager.currentNavigationFragment: Fragment?
            get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()

        fun getCurrentNavigationComponentFragmentId(navController: NavController): Int? {
            return navController.currentDestination?.id
        }

        fun replaceFragment(supportFragmentManager: FragmentManager, @IdRes fragmentContainer: Int, fragment: Fragment, tag: String = "", isAddToBackStack: Boolean = false) {
            if (isAddToBackStack) {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(tag)
                    .commit()
            } else {
                supportFragmentManager
                    .beginTransaction()
                    .replace(fragmentContainer, fragment)
                    .commit()
            }
        }
    }
}