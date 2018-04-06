package com.example.tomo.yamaapp.view.listener

import com.example.tomo.yamaapp.model.data.Diary

/**
 * Created by tomo on 2018/04/06.
 */
interface MainViewListener {
    fun toDetail(diary: Diary)
    fun showToast(msg: String)
}