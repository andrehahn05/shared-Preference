package com.hahn.sharedpreference

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.hahn.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate((layoutInflater))
    }
    private var color = ""

    companion object {
        const val PREFERENCES_FILE = "PreferencesFile"
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar !!.hide()
        handlerOnclicklistener()
    }

    private fun handlerOnclicklistener() {
        binding.color1.setOnClickListener {
            color = "#FF03DAC5"
            save(color)
        }
        binding.color2.setOnClickListener {
            color = "#FFBB86FC"
            save(color)
        }
        binding.color3.setOnClickListener {
            color = "#03A9F4"
            save(color)
        }
        binding.color4.setOnClickListener {
            color = "#FF0000"
            save(color)
        }
    }

    private fun save(color : String) {
        binding.layoutMain.setBackgroundColor(Color.parseColor(color))

        binding.btnSaveColor.setOnClickListener { view ->
            val preference = getSharedPreferences(PREFERENCES_FILE , MODE_PRIVATE)
            val editor = preference.edit()
            editor.putString("color" , color)
            editor.apply()
            snackbar(view)
        }
    }

    private fun snackbar(view : View) {
        val snackbar = Snackbar.make(view ,
            "Cor de fundo alterada com sucesso!" ,
            Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("OK") { }
        snackbar.setActionTextColor(Color.BLUE)
        snackbar.setBackgroundTint(Color.WHITE)
        snackbar.setTextColor(Color.BLACK)
        snackbar.show()
    }

    override fun onResume() {
        super.onResume()
        val preference = getSharedPreferences(PREFERENCES_FILE , MODE_PRIVATE)
        val color = preference.getString("cor" , "")
        if (color !!.isNotEmpty()) {
            binding.layoutMain.setBackgroundColor(Color.parseColor(color))
        }
    }
}