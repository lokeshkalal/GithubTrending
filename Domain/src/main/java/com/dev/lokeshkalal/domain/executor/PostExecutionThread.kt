package com.dev.lokeshkalal.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    val schedular: Scheduler
}