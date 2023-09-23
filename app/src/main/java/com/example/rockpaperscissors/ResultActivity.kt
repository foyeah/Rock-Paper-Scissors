package com.example.rockpaperscissors

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.rockpaperscissors.databinding.ActivityResultBinding
import kotlin.random.Random

class ResultActivity : AppCompatActivity() {
    private val RETRY_BUTTON_TEXT = "↻"
    private val WIN_TEXT = "Вы выиграли"
    private val LOSE_TEXT = "Вы проиграли"
    private val deadheat_TEXT = "Ничья"

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonRetry.text = RETRY_BUTTON_TEXT
        binding.ButtonRetry.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {  }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val bundle = intent.extras
        val gameValueOrdinal = bundle?.getInt(GameValue::class.java.simpleName)
        gameValueOrdinal ?: return
        val userValue: GameValue = GameValue.values()[gameValueOrdinal]
        val computerValue = GameValue.values()[Random.nextInt(GameValue.values().size)]
        resultPrint(userValue, computerValue)
    }

    private fun resultPrint(userValue: GameValue, computerValue: GameValue,){
        binding.ComputerValueText.text = "Computer value: " + computerValue.name
        binding.GamerValueText.text = "Gamer value: " + userValue.name
        lateinit var textValueResult: String
        when {
            userValue == GameValue.ROOK && computerValue == GameValue.PAPER-> textValueResult = LOSE_TEXT
            computerValue == GameValue.ROOK && userValue == GameValue.PAPER -> textValueResult = WIN_TEXT
            userValue.ordinal > computerValue.ordinal -> textValueResult = WIN_TEXT
            computerValue.ordinal > userValue.ordinal -> textValueResult = LOSE_TEXT
            userValue.ordinal == computerValue.ordinal -> textValueResult = deadheat_TEXT
        }
        binding.ResultText.text = textValueResult
    }
}