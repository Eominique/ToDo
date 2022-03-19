package com.example.todo.util

import androidx.appcompat.widget.SearchView
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

//listener – obiekt nasłuchujący, który odbiera wywołania
// zwrotne, gdy użytkownik wykonuje akcje w SearchView,
// takie jak klikanie przycisków lub wpisywanie zapytania
inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }
    })
}

var TextInputLayout.text : String
    get() = editText?.text?.toString() ?: ""
    set(value) {
        editText?.setText(value)
    }

private val locale = Locale("", "")

fun Date.format() : String {
    return SimpleDateFormat("dd/MM/yyyy", locale).format(this)
}