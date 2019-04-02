package com.dev.lokeshkalal.presentation.state

class Resource<out T> constructor(val resourceState: ResourceState, val data: T?, val message: String?) {
}