package com.example.tomo.yamaapp.viewmodel

import com.airbnb.epoxy.TypedEpoxyController
import com.example.tomo.yamaapp.DiaryListItemBindingModel_
import com.example.tomo.yamaapp.model.data.Diary
import com.example.tomo.yamaapp.view.listener.MainViewListener

/**
 * Created by tomo on 2018/04/06.
 */
class DiaryListController(private val listener: MainViewListener) : TypedEpoxyController<List<Diary>>() {
    override fun buildModels(diaries: List<Diary>) {
        diaries.forEach {
            DiaryListItemBindingModel_()
                    .id(it.id)
                    .listener(listener)
                    .diary(it)
                    .addTo(this)
        }
    }
}