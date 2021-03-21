package com.example.converter.mvp.presenter

import com.example.converter.mvp.model.Converter
import com.example.converter.mvp.model.Image
import com.example.converter.ui.MainView
import moxy.MvpPresenter

class MainPresenter(val converter: Converter) : MvpPresenter<MainView>() {
    fun converter(image: Image) {
        viewState.showInProgress()
        converter.converter(image).subscribe({
            viewState.hideInProgress()
            viewState.showSuccess()
        }, {
            viewState.hideInProgress()
            viewState.showError()
        })
    }
}