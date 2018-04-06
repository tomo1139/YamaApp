package com.example.tomo.yamaapp.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.tomo.yamaapp.R
import com.example.tomo.yamaapp.databinding.ActivityDetailBinding
import com.example.tomo.yamaapp.model.data.Diary
import com.example.tomo.yamaapp.util.eventbus.EventBusHolder

/**
 * Created by tomo on 2018/04/06.
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val diary: Diary by lazy { intent.getSerializableExtra(keyDiary) as Diary }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = this.javaClass.simpleName
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.diary = diary
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
                val title = binding.editText.text.toString()
                EventBusHolder.checkEventBus.send(diary.copy(title = title))
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