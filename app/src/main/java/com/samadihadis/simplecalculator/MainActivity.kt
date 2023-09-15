package com.samadihadis.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.samadihadis.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    var isLastInputNumber = false
    var hasDotTextView = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }


    fun onDigitClick(view: View) {
        val clickButton = view as Button
        binding.textView.append(clickButton.text)
        isLastInputNumber = true

    }

    fun clearText(view: View) {
        binding.textView.text = ""
        isLastInputNumber = false
        hasDotTextView = false
    }

    fun onDecimalPointClick(view: View) {
        if (isLastInputNumber && !hasDotTextView) {
            binding.textView.append(".")
            hasDotTextView = true
        }
    }

    fun onOperatorClick(view: View) {
        if (isLastInputNumber && !isOperatorSelected(binding.textView.text.toString())) {
            binding.textView.append((view as Button).text)
            isLastInputNumber = false
            hasDotTextView = false
        }
    }

    fun onEqualClick(view: View) {
        var prefix = ""
        if (!isLastInputNumber) return

        var inputValue = binding.textView.text.toString()

        if (inputValue.contains("+")) {
            val splitValueArray = inputValue.split("+")
            var firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]
            val result = firstNumber.toDouble() + secondNumber.toDouble()

            binding.textView.text = result.toString()
        }
        if (inputValue.contains("*")) {
            val splitValueArray = inputValue.split("*")
            var firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]
            val result = firstNumber.toDouble() * secondNumber.toDouble()

            binding.textView.text = result.toString()
        }
        if (inputValue.contains("/")) {
            val splitValueArray = inputValue.split("/")
            var firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]
            val result = firstNumber.toDouble() / secondNumber.toDouble()

            binding.textView.text = result.toString()
        }


        if (inputValue.startsWith("-")) {
            prefix = "-"
            inputValue = inputValue.substring(1)

        }
        if (inputValue.contains("-")) {
            val splitValueArray = inputValue.split("-")
            var firstNumber = splitValueArray[0]
            val secondNumber = splitValueArray[1]

            if (prefix.isNotEmpty()) {
                firstNumber = prefix + firstNumber
            }
            val result = firstNumber.toDouble() - secondNumber.toDouble()

            binding.textView.text = result.toString()
        }

    }

    private fun isOperatorSelected(text: String): Boolean {
        if (text.startsWith("-")) {
            return false
        }
        return text.contains("+") || text.contains("-") || text.contains("*") || text.contains("/")
    }
}