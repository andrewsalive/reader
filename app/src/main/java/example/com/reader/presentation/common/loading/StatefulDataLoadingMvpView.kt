package example.com.reader.presentation.common.loading

import example.com.reader.R

interface StatefulDataLoadingMvpView : BaseLoadingView {
    fun onNoDataReceived() {}
    fun onNoInternetConnection() {
        showMessage(R.string.connection_error)
    }

    fun onSomethingWentWrong(message: String? = null) {}
}