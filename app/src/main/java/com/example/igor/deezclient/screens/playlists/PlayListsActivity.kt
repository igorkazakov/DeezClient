package com.example.igor.deezclient.screens.playlists

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.example.igor.deezclient.R
import com.example.igor.deezclient.data.server.model.PlaylistModel
import com.example.igor.deezclient.databinding.ActivityPlayListsBinding
import com.example.igor.deezclient.screens.playlistInfo.PlaylistActivity
import com.example.igor.deezclient.viewModels.PlaylistsViewModel
import com.example.igor.deezclient.viewModels.ViewModelFactory
import com.igorkazakov.user.foursquareclient.screens.base.activity.BaseActivity

class PlayListsActivity : BaseActivity() {

    private lateinit var mPlaylistsViewModel: PlaylistsViewModel
    private lateinit var mActivityPlayListsBinding: ActivityPlayListsBinding

    private val mPlaylistAdapterListener = object : PlaylistsAdapter.PlaylistsItemListener {
        override fun onItemClick(item: PlaylistModel) {
            PlaylistActivity.launch(this@PlayListsActivity, item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityPlayListsBinding = DataBindingUtil.setContentView(this, R.layout.activity_play_lists)
        setSupportActionBar(mActivityPlayListsBinding.toolbar)

        mPlaylistsViewModel = ViewModelProviders.of(this, ViewModelFactory())
                .get(PlaylistsViewModel::class.java)

        if (mPlaylistsViewModel.needData()) {
            showLoading()
            mPlaylistsViewModel.loadPlaylists(this)
        }

        mPlaylistsViewModel.playlistsLiveData.observe(this, Observer {

            showPlayListsGrid(it!!)
            hideLoading()
        })

        mPlaylistsViewModel.errorsLiveData.observe(this, Observer {
            it?.let {
                showError(it)
                hideLoading()
            }
        })
    }

    private fun showPlayListsGrid(list: List<PlaylistModel>) {

        val adapter = PlaylistsAdapter(list, mPlaylistAdapterListener)
        val gridLayoutManager = GridLayoutManager(this,
                3,
                GridLayoutManager.VERTICAL,
                false)//AutoFitGridLayoutManager(this,  windowManager.defaultDisplay.width / 3)

        mActivityPlayListsBinding.contentLayout?.recyclerView?.adapter = adapter
        mActivityPlayListsBinding.contentLayout?.recyclerView?.layoutManager = gridLayoutManager
    }
}
