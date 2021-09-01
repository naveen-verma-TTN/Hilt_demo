package com.example.hilt_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import org.jetbrains.annotations.NotNull
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
    }
}

/*@FragmentScoped
@AndroidEntryPoint
class MyFragment: Fragment() {

    @Inject
    lateinit var someClass: SomeClass
}*/

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
        return "Look I got: ${someInterfaceImpl.getAThing()} and \"${gson.javaClass.canonicalName}\" object!"
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

interface GsonInterface {
    @NotNull
    fun getGson(): Gson
}


/**
 * complex way to create a Module dependencies
 */


/*
@InstallIn(SingletonComponent::class)
@Module
abstract class MyModuleWithApplication {

    @Singleton
    @Binds
    abstract fun bindSomeDependency(someInterfaceImpl: SomeInterfaceImpl): SomeInterface
}
*/


/*@InstallIn(ActivityComponent::class)
@Module
abstract class MyModuleWithActivity {

    @ActivityScoped
    @Binds
    abstract fun bindSomeDependency(someInterfaceImpl: SomeInterfaceImpl): SomeInterface

    *//**
     * will not work
     *//*
    *//*
    @ActivityScoped
    @Binds
    abstract fun bindGson(gson: Gson): GsonInterface
     *//*
}*/

/**
 * easy way to create a Module dependencies
 */

@InstallIn(ActivityComponent::class)
@Module
class MyModule {

    @ActivityScoped
    @Provides
    fun provideSomeInterface(): SomeInterface {
        return SomeInterfaceImpl()
    }

    @ActivityScoped
    @Provides
    fun provideGson(): Gson {
        return  Gson()
    }
}
