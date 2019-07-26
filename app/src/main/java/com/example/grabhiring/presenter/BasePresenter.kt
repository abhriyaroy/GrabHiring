package com.example.grabhiring.presenter

interface BasePresenter<T> {
  fun attachView(view: T)
  fun detachView()
}