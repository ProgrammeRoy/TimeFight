package com.application.roy.timefighter.timefighter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var gameTimeTextView: TextView

    internal var score = 0

    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal var initialCountDown: Long = 60000
    internal val intervalCountDown: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.B_tap)
        gameScoreTextView = findViewById(R.id.TV_score)
        gameTimeTextView = findViewById(R.id.TV_time_left)
        resetGame()
        //gameScoreTextView.text = getString(R.string.your_score,"0")

        tapMeButton.setOnClickListener{
            view ->
            incrementScore()
        }
    }

    private fun resetGame(){
        score = 0
        gameScoreTextView.text = getString(R.string.your_score,score.toString())
        val initialTimeLeft = initialCountDown / 1000
        gameTimeTextView.text = getString(R.string.time_left,initialTimeLeft.toString())

        countDownTimer = object : CountDownTimer(initialCountDown,intervalCountDown){
            override fun onTick(millisUntilFinished: Long) {
                val timeleft = millisUntilFinished/1000
                gameTimeTextView.text = getString(R.string.time_left, timeleft.toString())
            }
            override fun onFinish() {
                finishGame()
            }
        }
        gameStarted = false
    }

    private fun startGame(){
        countDownTimer.start()
        gameStarted = true
    }

    private fun finishGame(){
        Toast.makeText(this,getString(R.string.game_over_message, score.toString()), Toast.LENGTH_SHORT).show()
        resetGame()
    }

    private fun incrementScore() {
        if (!gameStarted){
            startGame()
        }
        score = score + 1
        val newScore = getString(R.string.your_score, score.toString())
        gameScoreTextView.text = newScore
    }
}
