package com.example.igor.deezclient.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import com.example.igor.deezclient.data.common.ErrorModel

object DialogUtils {

    fun showErrorDialog(context: Context, model: ErrorModel) {

        val builder = AlertDialog.Builder(context)

        builder.setTitle(model.title ?: "Внимание")
        builder.setMessage(model.message ?: "")

        builder.setPositiveButton("Ok"){ dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}