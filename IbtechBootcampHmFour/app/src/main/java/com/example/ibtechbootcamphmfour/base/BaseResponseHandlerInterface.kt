package com.example.ibtechbootcamphmfour.base

interface BaseResponseHandlerInterface<T> {
    fun onSuccess(data: T)
    fun onFailure()
}