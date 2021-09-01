package com.example.hilt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class MyFragment(private val someString: String) : Fragment(R.layout.fragment_my) {

    companion object {
        private const val TAG = "MyFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "Here is some string: $someString")
    }
}