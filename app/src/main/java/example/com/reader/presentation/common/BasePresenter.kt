package example.com.reader.presentation.common

import android.os.Bundle
import example.com.reader.R
import example.com.reader.domain.exception.NetworkException

abstract class BasePresenter<V : MvpView> : Presenter<V> {

    protected var mvpView: V? = null

    override fun attachView(view: V) {
        this.mvpView = view
    }

    override fun detachView() {
        mvpView = null
    }

    open fun initFromBundle(bundle: Bundle) {
    }

    open fun onSaveInstanceState(outState: Bundle) {
    }

    protected open fun handleError(t: Throwable) {
        if (t is NetworkException) {
            when (t.code) {
                NetworkException.NETWORK_ERROR -> mvpView?.showMessage(R.string.connection_error)
                NetworkException.TIMEOUT_ERROR -> mvpView?.showMessage(R.string.timeout_error)
                else -> handleServerError(t)
            }
        } else {
            mvpView?.showMessage(R.string.default_error)
        }
    }

    protected open fun handleServerError(e: NetworkException) {
        mvpView?.showMessage(R.string.default_error)
    }
}