package com.example.tomo.yamaapp.viewmodel

import com.example.tomo.yamaapp.model.data.Diary
import com.example.tomo.yamaapp.util.eventbus.EventBusHolder

/**
 * Created by tomo on 2018/04/08.
 */
class DetailViewModel(val diary: Diary) {
    fun onClickCheck(title: String) = EventBusHolder.checkEventBus.send(diary.copy(title = title))
}