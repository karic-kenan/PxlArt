package io.aethibo.pxlart.ui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() = hideKeyboard(currentFocus ?: View(this))

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.showSnackBarShort(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)

fun View.showSnackBarLong(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_LONG)