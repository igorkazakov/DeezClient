package com.igorkazakov.user.foursquareclient.screens.base.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.igor.deezclient.data.common.ErrorModel
import com.example.igor.deezclient.utils.DialogUtils
import com.igorkazakov.user.foursquareclient.screens.progress.FragmentProgress
import com.igorkazakov.user.foursquareclient.screens.progress.LoadingInterface

open class BaseActivity : AppCompatActivity() {

    private var mProgress: LoadingInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mProgress = FragmentProgress(this, window.decorView.rootView as ViewGroup?)
    }

    fun showLoading() {
        mProgress?.showLoading()
    }

    fun hideLoading() {
        mProgress?.hideLoading()
    }

    fun showError(model: ErrorModel) {

        DialogUtils.showErrorDialog(this, model)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}