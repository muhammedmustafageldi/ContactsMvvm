package com.swanky.contactsmvvm.util

import android.widget.Button
import android.widget.EditText
import com.davidmiguel.numberkeyboard.NumberKeyboard
import com.davidmiguel.numberkeyboard.NumberKeyboardListener

class NumPadListener(
    private val numPad: NumberKeyboard,
    val editText: EditText,
    private val button: Button
) {

    fun defineNumPad() {
        numPad.setListener(object : NumberKeyboardListener {
            override fun onLeftAuxButtonClicked() {}

            override fun onNumberClicked(number: Int) {
                val currentText = StringBuilder(editText.text.toString())
                currentText.append(number)
                editText.setText(currentText.toString())

                // Check button state
                checkSubmitButtonState()
            }

            override fun onRightAuxButtonClicked() {
                val currentText = editText.text.toString()
                if (currentText.isNotEmpty()) {
                    editText.setText(currentText.substring(0, currentText.length - 1))
                }

                // Check button state
                checkSubmitButtonState()
            }
        })
    }

    private fun checkSubmitButtonState() {
        val text = editText.text.toString()
        button.isEnabled = text.isNotEmpty()
    }


}