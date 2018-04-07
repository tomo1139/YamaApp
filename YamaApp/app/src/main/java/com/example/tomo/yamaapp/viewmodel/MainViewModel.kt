package com.example.tomo.yamaapp.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
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
class MainViewModel(private val listener: MainViewListener) : LifecycleObserver {

    private val disposables = CompositeDisposable()
    val controller by lazy { DiaryListController(listener) }
    val isLoading = ObservableBoolean(false)
    val isError = ObservableBoolean(false)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
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
        requestDiaries()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
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
