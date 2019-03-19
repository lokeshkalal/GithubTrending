package com.dev.lokeshkalal.domain.interactor

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseobservable(params: Params? = null): Observable<T>


    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseobservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.schedular)
        observable.subscribeWith(observer)
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}