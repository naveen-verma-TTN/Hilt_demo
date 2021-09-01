package com.example.hilt

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject


/**
 * Created by Naveen Verma on 01/09/21.
 * To The New
 * naveen.verma@tothenew.com
 */

class MainFragmentFactory
@Inject
constructor(
    private val someString: String
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className) {
            MyFragment::class.java.name -> {
                MyFragment(someString)
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }
}