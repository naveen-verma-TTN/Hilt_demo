package com.example.hilt

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.hilt.di.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.hamcrest.Matchers.containsString
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Naveen Verma on 01/09/21.
 * To The New
 * naveen.verma@tothenew.com
 */

//@Config(application = HiltTestApplication::class)
@HiltAndroidTest
class MainTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var mActivityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var someString: String

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun mytTest() {
        Assert.assertEquals(someString, containsString("TESTING"))
    }

    @Test
    fun mainFragmentTest() {
//        val scenario = launchFragmentInHiltContainer<MyFragment>(
//            factory = fragmentFactory
//        )
    }

    @Test
    fun mainActivityTest() {
        val scenario = launchActivity<MainActivity>()
    }
}

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Singleton
    @Provides
    fun provideSomeString(): String {
        return "Its is TESTING String"
    }
}