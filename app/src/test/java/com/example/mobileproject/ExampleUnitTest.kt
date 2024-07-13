package com.example.mobileproject

import android.util.Log
import com.example.mobileproject.repository.RepositoryAsync
import com.example.mobileproject.repository.RepositorySync
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun APITestRestaurants(){
        val restaurantes = RepositorySync.getRestaurants()
        Log.i("Test", restaurantes.toString())
    }
}