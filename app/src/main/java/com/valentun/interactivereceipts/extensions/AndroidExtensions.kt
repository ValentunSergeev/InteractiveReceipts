package com.valentun.interactivereceipts.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.valentun.interactivereceipts.App
import com.valentun.interactivereceipts.R
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .placeholder(R.color.placeholder)
        .into(this)
}

fun Activity.hideKeyboard() {
    val view = currentFocus
    view?.hideKeyboard()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    requestFocus()

    val keyboard = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    keyboard.showSoftInput(this, 0)
}

val TextView.content
    get() = this.text.toString()

fun String.toReadableDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val date = dateFormat.parse(this)

    val calendar = Calendar.getInstance()
    calendar.time = date

    return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
}

fun Fragment.getColor(@ColorRes colorRes: Int): Int {
    return App.getColor(colorRes)
}