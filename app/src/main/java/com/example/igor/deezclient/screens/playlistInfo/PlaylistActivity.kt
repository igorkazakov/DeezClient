package com.example.igor.deezclient.screens.playlistInfo

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.igor.deezclient.R
import com.example.igor.deezclient.data.server.model.PlaylistModel
import com.example.igor.deezclient.data.server.model.TrackModel
import com.example.igor.deezclient.databinding.ActivityPlaylistBinding
import com.example.igor.deezclient.viewModels.PlaylistViewModel
import com.example.igor.deezclient.viewModels.ViewModelFactory
import com.igorkazakov.user.foursquareclient.screens.base.activity.BaseActivity
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import com.igorkazakov.user.foursquareclient.data.server.Repository


class PlaylistActivity : BaseActivity() {

    private lateinit var mActivityPlayListBinding: ActivityPlaylistBinding
    private lateinit var mPlayListViewModel: PlaylistViewModel
    private lateinit var model: PlaylistModel
    private lateinit var mAdapter: PlaylistAdapter

    companion object {

        private const val EXTRA_OBJECT: String = "EXTRA_OBJECT"

        fun launch(parent: Activity, model: PlaylistModel) {

            val i = Intent(parent, PlaylistActivity::class.java)
            i.putExtra(EXTRA_OBJECT, model)
            parent.startActivity(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityPlayListBinding = DataBindingUtil.setContentView(this, R.layout.activity_playlist)
        setSupportActionBar(mActivityPlayListBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        model = intent.extras.getParcelable(EXTRA_OBJECT)
        mActivityPlayListBinding.playlist = model

        mPlayListViewModel = ViewModelProviders.of(this, ViewModelFactory())
                .get(PlaylistViewModel::class.java)

        setUpList()
        showLoading()
        loadData()

        mPlayListViewModel.playlistLiveData.observe(this, Observer {
            showPlayList(it!!)
            hideLoading()
        })

        mPlayListViewModel.errorsLiveData.observe(this, Observer {
            it?.let {
                showError(it)
                hideLoading()
            }
        })
    }

    private fun showPlayList(list: List<TrackModel>) {

        mAdapter.addItems(list)
    }

    private fun loadData() {
        mPlayListViewModel.loadPlaylist(model.playlistId,
                mAdapter.itemCount,
                this)
    }

    private fun setUpList() {
        mAdapter = PlaylistAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mActivityPlayListBinding.recyclerView.adapter = mAdapter
        mActivityPlayListBinding.recyclerView.layoutManager = layoutManager
        mActivityPlayListBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (mPlayListViewModel.needLoadAdditionalData(
                                visibleItemCount,
                                totalItemCount,
                                firstVisibleItemPosition)) {

                    loadData()
                }
            }
        })
    }
}
