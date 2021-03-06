package com.example.converter.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainView : MvpView{
    fun showSuccess()
    fun showError()
    fun showInProgress()
    fun hideInProgress()
}