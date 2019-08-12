package example.com.reader.presentation.common.request


import io.reactivex.disposables.Disposable

abstract class Request {

    var showLoading = true
        private set

    fun setShowLoading(showLoading: Boolean): Request {
        this.showLoading = showLoading
        return this
    }

    abstract fun execute(): Disposable
}