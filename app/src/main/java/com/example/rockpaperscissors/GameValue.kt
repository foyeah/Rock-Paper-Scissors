package com.example.rockpaperscissors

import androidx.annotation.IdRes

enum class GameValue(@IdRes val resourceId: Int) {
    PAPER(R.id.Button_Paper),
    SCISSORS(R.id.Button_Scissors),
    ROOK(R.id.Button_Rock)
}
