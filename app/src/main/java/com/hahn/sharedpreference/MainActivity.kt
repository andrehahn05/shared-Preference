package com.hahn.sharedpreference

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hahn.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate((layoutInflater))
    }
    private var color = ""
    companion object{
        const val PREFERENCES_FILE ="PreferencesFile"
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar !!.hide()

        binding.color1.setOnClickListener {
            color = "#FF03DAC5"
        }
        binding.color2.setOnClickListener {
            color = "#FFBB86FC"
        }
        binding.color3.setOnClickListener {
            color = "#03A9F4"
        }
        binding.color4.setOnClickListener {
            color = "#FF0000"
        }
    }

    private fun save(color : String) {
        binding.layoutMain.setBackgroundColor(Color.parseColor(color))

        binding.btnSaveColor.setOnClickListener { view ->
            val preference = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE)
            val editor = preference.edit()
            editor.putString("color",color)
            editor.apply()


        }
    }
}