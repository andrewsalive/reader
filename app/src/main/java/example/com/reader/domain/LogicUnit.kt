package example.com.reader.domain

import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class LogicUnit constructor(protected val workerThreadScheduler: Scheduler = Schedulers.io(),
                                     protected val uiThreadScheduler: Scheduler = AndroidSchedulers.mainThread()) {

    private val singleTransformer: SingleTransformer<Any, Any>

    init {
        singleTransformer = SingleTransformer { single ->
            single.subscribeOn(workerThreadScheduler)
                    .observeOn(uiThreadScheduler)
        }
    }

    @SuppressWarnings("unchecked")
    fun <T> applySchedulersToSingle(): SingleTransformer<T, T> {
        return singleTransformer as SingleTransformer<T, T>
    }

}
