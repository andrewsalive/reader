package example.com.reader.presentation.common.request

import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class SingleRequest<A>(private val single: Single<A>,
                       private val onSuccess: Consumer<A>,
                       private val onError: Consumer<Throwable>) : Request() {

    override fun execute(): Disposable = single.subscribe(onSuccess, onError)
}