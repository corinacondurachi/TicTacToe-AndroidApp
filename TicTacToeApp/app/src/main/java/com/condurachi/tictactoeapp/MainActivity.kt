package com.condurachi.tictactoeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View){
        val buSelected = view as Button

        var cellId = 0
        when (buSelected.id){
            R.id.bu1 -> cellId = 1
            R.id.bu2 -> cellId = 2
            R.id.bu3 -> cellId = 3
            R.id.bu4 -> cellId = 4
            R.id.bu5 -> cellId = 5
            R.id.bu6 -> cellId = 6
            R.id.bu7 -> cellId = 7
            R.id.bu8 -> cellId = 8
            R.id.bu9 -> cellId = 9

        }

        Log.d("buClick: ", buSelected.id.toString())
        Log.d("buClick: cellID", cellId.toString())
        playGame(cellId, buSelected)

    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    fun playGame(cellId:Int, buSelected:Button ){

        if(activePlayer == 1){
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        }
        else{
            buSelected.text = "0"
            buSelected.setBackgroundResource(R.color.darkgreen)
            player2.add(cellId)
            activePlayer = 1

    }
        buSelected.isEnabled = false

        checkWinner()
    }

    fun checkWinner() {
        var winner = -1

        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }

        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //col1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }

        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //diag 1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        //diag 2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner == 1) {
            player1WinsCount += 1
            Toast.makeText(this, "Player 1 won the game ", Toast.LENGTH_LONG).show()
            restartGame()
        } else if (winner == 2) {
            player2WinsCount += 1
            Toast.makeText(this, "Player 2 won the game ", Toast.LENGTH_LONG).show()
            restartGame()
        }
    }

        fun autoPlay() {
            var emptyCells = ArrayList<Int>()
            for (cellId in 1..9){
                if( ! (player1.contains(cellId) || player2.contains(cellId))){
                    emptyCells.add(cellId)
                }
            }

            if(emptyCells.size == 0){
                restartGame()
            }

            val randomIndex = Random.nextInt(emptyCells.size)
            val cellId = emptyCells[randomIndex]

            var buSelected:Button?
            buSelected = when(cellId){
                1 -> bu1
                2 -> bu2
                3 -> bu3
                4 -> bu4
                5 -> bu5
                6 -> bu6
                7 -> bu7
                8 -> bu8
                9 -> bu9
                else -> {bu1}
            }

            playGame(cellId, buSelected)

        }

    var player1WinsCount = 0
    var player2WinsCount = 0

    fun restartGame(){
        activePlayer = 1
        player1.clear()
        player2.clear()

        for ( cellId in 1..9){
            var buSelected:Button?
             buSelected = when(cellId){
                1 -> bu1
                2 -> bu2
                3 -> bu3
                4 -> bu4
                5 -> bu5
                6 -> bu6
                7 -> bu7
                8 -> bu8
                9 -> bu9
                else -> {bu1}
            }
            buSelected.text = ""
            buSelected.setBackgroundResource(R.color.whitebuttons)
            buSelected.isEnabled = true
        }

        Toast.makeText(this, "Player1:  $player1WinsCount, Player2:  $player2WinsCount", Toast.LENGTH_LONG).show()
    }

    }


