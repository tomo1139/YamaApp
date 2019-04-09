package com.example.tomo.yamaapp.util.eventbus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by tomo on 2018/04/06.
 */
class RxBus<T> {
    private val bus = PublishSubject.create<T>()

    fun send(value: T) = bus.onNext(value)
    fun toObservable(): Observable<T> = bus
}