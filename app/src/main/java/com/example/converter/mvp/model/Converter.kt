package com.example.converter.mvp.model

import io.reactivex.rxjava3.core.Completable

interface Converter {
    fun converter(image: Image): Completable
}