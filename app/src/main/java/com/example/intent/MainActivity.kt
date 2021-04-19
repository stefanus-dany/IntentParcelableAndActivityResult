package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMoveActivityObject.setOnClickListener {
            val person = Person(
                "Stefanus", 21, "stefanusdany12@gmail.com", "Bandung"
            )

            val intent = Intent(this, Move::class.java)
            intent.putExtra(Move.EXTRA_PERSON, person)
            startActivity(intent)
        }

        binding.btnMoveActivityForResult.setOnClickListener {
            val intent = Intent(this, MoveForResult::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResult.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(MoveForResult.EXTRA_SELECTED_VALUE, 0)
                binding.tvResult.text = "Hasil : $selectedValue"
            }
        }
    }

    companion object {
        private const val REQUEST_CODE = 100

    }
}