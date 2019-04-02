package com.dev.lokeshkalal.ui

import com.dev.lokeshkalal.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UiThread @Inject constructor() : PostExecutionThread {
    override val schedular: Scheduler
        get() = AndroidSchedulers.mainThread()
}