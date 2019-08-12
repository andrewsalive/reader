package example.com.reader.presentation.common.loading

import example.com.reader.presentation.common.MvpView

interface BaseLoadingView : MvpView {
    fun showLoading()
    fun hideLoading()
}