package com.example.rockpaperscissors

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.IdRes
import com.example.rockpaperscissors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val ROCK_BUTTON_TEXT = "Камень"
    private val SCISSORS_BUTTON_TEXT = "Ножницы"
    private val PAPER_BUTTON_TEXT = "Бумага"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonPaper.text = PAPER_BUTTON_TEXT
        binding.ButtonScissors.text = SCISSORS_BUTTON_TEXT
        binding.ButtonRock.text = ROCK_BUTTON_TEXT
        var buttons = listOf(
            binding.ButtonPaper,
            binding.ButtonScissors,
            binding.ButtonRock
        )
        for (button in buttons){
            button.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        val button = view as? Button
        button ?: return
        val gameValue: GameValue? = GameValue.values().find { it.resourceId == button.id }
        val intent = Intent(this, ResultActivity::class.java).apply {
            val bundle = Bundle().apply {
                gameValue ?: return
                putInt(GameValue::class.java.simpleName, gameValue.ordinal)
            }
            putExtras(bundle)
        }
        startActivity(intent)
    }

}