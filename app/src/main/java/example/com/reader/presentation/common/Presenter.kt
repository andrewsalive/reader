package example.com.reader.presentation.common

interface Presenter<in V : MvpView> {
    fun attachView(view: V)
    fun detachView()
}