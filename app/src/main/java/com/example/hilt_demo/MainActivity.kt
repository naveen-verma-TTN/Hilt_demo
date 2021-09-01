package com.example.hilt_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
    }
}

@FragmentScoped
@AndroidEntryPoint
class MyFragment: Fragment() {

    @Inject
    lateinit var someClass: SomeClass
}

//@Singleton
//@ActivityScoped
//@FragmentScoped // will not work as Anything with ActivityRetained scoped can't be scoped using FragmentScoped
class SomeClass
@Inject
constructor(
    // we can't directly inject the interface in hilt :: Need a work around for this
    private val someInterfaceImpl: SomeInterface,
    // Also we can't directly inject the Third-party libraries in hilt :: Need a work around for this
    private val gson: Gson
) {

    fun doAThing(): String {
        return "Look I got: ${someInterfaceImpl.getAThing()}"
    }
}


class SomeInterfaceImpl
@Inject
constructor() : SomeInterface {
    override fun getAThing(): String {
        return "A Thing!"
    }
}

interface SomeInterface {

    fun getAThing(): String
}
