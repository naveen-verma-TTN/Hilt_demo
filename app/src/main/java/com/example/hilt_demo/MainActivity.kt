package com.example.hilt_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
        println(someClass.doSomeOtherThing())
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
    // construction injection
    private val someOtherClass: SomeOtherClass
) {

    fun doAThing(): String {
        return "Look I did a thing!"
    }

    fun doSomeOtherThing(): String {
        return someOtherClass.doSomeOtherThing()
    }
}


class SomeOtherClass
@Inject
constructor() {

    fun doSomeOtherThing(): String {
        return "Look I did some other thing!"
    }
}
