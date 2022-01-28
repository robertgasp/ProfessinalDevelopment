package com.example.professionaldevelopment.ui.alertDialog

import android.app.AlertDialog
import android.content.Context

fun getStubAlertDialog(context: Context):AlertDialog{
    return getAlertDialog(context, null,null)
}

fun getAlertDialog(context: Context, title: String?, message: String?): AlertDialog {
    val builder = AlertDialog.Builder(context)
    var finalTitle:String? = "Undefined error"
    if (!title.isNullOrBlank()){
        finalTitle = title
    }
    builder.setTitle(finalTitle)

    if (!message.isNullOrBlank()){
        builder.setMessage(message)
    }

    builder.setCancelable(true)
    builder.setPositiveButton("Cancel") { dialog, _ -> dialog.dismiss() }
    return builder.create()
}
