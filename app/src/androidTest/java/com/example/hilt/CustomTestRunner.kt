package com.example.hilt

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import androidx.test.runner.MonitoringInstrumentation
import dagger.hilt.android.testing.HiltTestApplication


/**
 * Created by Naveen Verma on 01/09/21.
 * To The New
 * naveen.verma@tothenew.com
 */

// A custom runner to set up the instrumented application class for tests.
class CustomTestRunner : MonitoringInstrumentation() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }

}