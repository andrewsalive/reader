package example.com.reader.presentation.common.loading

import example.com.reader.presentation.common.BasePresenter
import example.com.reader.presentation.common.request.Request
import example.com.reader.presentation.common.request.SingleRequest
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

abstract class BaseLoadingPresenter<V : BaseLoadingView> : BasePresenter<V>() {

    private val disposables = CompositeDisposable()

    protected fun <A> createRequest(single: Single<A>,
                                    success: (A) -> Unit,
                                    error: (Throwable) -> Unit = { defaultOnError(it) }): Request {
        val wrappedOnSuccess = Consumer<A> {
            success.invoke(it)
            defaultOnComplete()
        }

        return SingleRequest(single, wrappedOnSuccess, Consumer { error.invoke(it) })
    }

    protected fun defaultOnComplete() = mvpView?.hideLoading()

    protected fun defaultOnError(t: Throwable) {
        mvpView?.hideLoading()
        handleError(t)
    }

    protected open fun executeRequest(request: Request): Disposable {
        if (request.showLoading) {
            mvpView?.showLoading()
        }
        val disposable = request.execute()
        disposables.add(disposable)
        return disposable
    }

}