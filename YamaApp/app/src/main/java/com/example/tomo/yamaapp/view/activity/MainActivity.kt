package com.example.tomo.yamaapp.view.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast
import com.example.tomo.yamaapp.R
import com.example.tomo.yamaapp.databinding.ActivityMainBinding
import com.example.tomo.yamaapp.model.data.Diary
import com.example.tomo.yamaapp.view.listener.MainViewListener
import com.example.tomo.yamaapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), MainViewListener {

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }
    private val viewModel: MainViewModel by lazy { MainViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = viewModel.controller.adapter

        viewModel.requestDiaries()
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }

    override fun toDetail(diary: Diary) = startActivity(DetailActivity.newIntent(this, diary))
    override fun showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}
