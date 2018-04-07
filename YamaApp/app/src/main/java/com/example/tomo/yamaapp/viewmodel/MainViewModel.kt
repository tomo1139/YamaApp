package com.example.tomo.yamaapp.viewmodel

import android.databinding.ObservableBoolean
import com.example.tomo.yamaapp.model.webapi.DiariesWebAPI
import com.example.tomo.yamaapp.util.eventbus.EventBusHolder
import com.example.tomo.yamaapp.view.listener.MainViewListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by tomo on 2018/04/06.
 */
class MainViewModel(private val listener: MainViewListener) {

    private val disposables = CompositeDisposable()
    val controller: DiaryListController by lazy { DiaryListController(listener) }
    var isLoading = ObservableBoolean(false)
    val isError = ObservableBoolean(false)

    init {
        disposables.add(EventBusHolder.checkEventBus.toObservable()
                .subscribe { diary ->
                    val datas = controller.currentData?.map {
                        if (it.id == diary.id) {
                            diary
                        } else {
                            it
                        }
                    }
                    controller.setData(datas)
                    listener.showToast("タイトルを設定しました")
                })
    }

    fun onDestroy() = disposables.dispose()

    fun requestDiaries() {
        DiariesWebAPI().request.diaries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.set(true) }
                .doFinally { isLoading.set(false) }
                .subscribe({ diaries ->
                    isError.set(false)
                    controller.setData(diaries)
                }, { _: Throwable ->
                    isError.set(true)
                    listener.showToast("通信エラー")
                })
    }
}