package com.example.tomo.yamaapp.viewmodel

import android.databinding.ObservableField
import com.example.tomo.yamaapp.model.data.Diary
import com.example.tomo.yamaapp.util.eventbus.EventBusHolder

/**
 * Created by tomo on 2018/04/08.
 */
class DetailViewModel(private val diary: Diary) {
    val title = ObservableField<String>(diary.title)

    fun onClickCheck() = EventBusHolder.checkEventBus.send(diary.copy(title = title.get()))
}