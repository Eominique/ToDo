package com.example.todo.util

import androidx.appcompat.widget.SearchView
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