package ru.skillbranch.devintensive.extensions

import android.widget.EditText

fun EditText.onAction(action: Int, runAction: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        return@setOnEditorActionListener when (actionId) {
            action -> {
                runAction.invoke()
                true
            }
            else -> false
        }
    }
}