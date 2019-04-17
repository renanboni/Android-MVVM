package com.boni

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}