package net.oleg.rtest

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("unused")
class MainActivity : AppCompatActivity() {

    companion object {

        private val TAG = MainActivity::class.simpleName

        @JvmStatic
        private external fun startRequestFromJni(callback: MainActivity)

        init {
            Log.d(TAG, "Trying to load library")
            System.loadLibrary("rust")
            Log.d(TAG, "Loaded")
        }
    }

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.sample_text)

        startRequestFromJni(this)
    }

    fun appendToTextView(string: String) {
        Log.d(TAG, "appendToTextView('$string')")
        textView.append(
            """
            $string
    
            """.trimIndent()
        )
    }

    fun hello() {
        Log.d(TAG, "hello()")
    }

    fun log(string: String?) {
        Log.d(TAG, string ?: "null")
    }
}