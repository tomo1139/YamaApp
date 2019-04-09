package com.example.tomo.yamaapp.util.eventbus

import com.example.tomo.yamaapp.model.data.Diary

/**
 * Created by tomo on 2018/04/06.
 */
object EventBusHolder {
    val checkEventBus by lazy { RxBus<Diary>() }
}