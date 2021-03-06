package com.example.tomo.yamaapp.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.tomo.yamaapp.R
import com.example.tomo.yamaapp.databinding.ActivityDetailBinding
import com.example.tomo.yamaapp.model.data.Diary
import com.example.tomo.yamaapp.viewmodel.DetailViewModel

/**
 * Created by tomo on 2018/04/06.
 */
class DetailActivity : AppCompatActivity() {

    private val binding by lazy { DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail) }
    private val viewModel by lazy { DetailViewModel(diary) }
    private val diary by lazy { intent.getSerializableExtra(keyDiary) as Diary }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menuCheck -> {
                viewModel.onClickCheck()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    companion object {
        private const val keyDiary = "keyDiary"

        fun newIntent(context: Context, diary: Diary): Intent {
            return Intent(context, DetailActivity::class.java).also {
                it.putExtra(keyDiary, diary)
            }
        }
    }
}