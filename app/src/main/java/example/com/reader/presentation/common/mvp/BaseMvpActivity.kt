package example.com.reader.presentation.common.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import example.com.reader.presentation.common.BaseActivity
import example.com.reader.presentation.common.BasePresenter
import example.com.reader.presentation.common.MvpView
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
abstract class BaseMvpActivity<V : MvpView, P : BasePresenter<V>> : BaseActivity() {

    private var dataHolder: DataHolder? = null
    @Inject
    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fm = supportFragmentManager
        dataHolder = fm.findFragmentByTag("dataHolder") as DataHolder?
        if (dataHolder == null) {
            dataHolder = DataHolder()
            fm.beginTransaction().add(dataHolder, "dataHolder")
                    .commit()
        }
        val oldPresenter = dataHolder?.getData<P>()
        if (oldPresenter != null) {
            presenter = oldPresenter
        }
        initPresenter(savedInstanceState)
        attachMvpView()
    }

    protected open fun initPresenter(savedInstanceState: Bundle?) {
        val bundle = Bundle()
        if (intent != null && intent.extras != null) {
            bundle.putAll(intent.extras)
        }
        if (savedInstanceState != null) {
            bundle.putAll(savedInstanceState)
        }
        if (!bundle.isEmpty) {
            presenter.initFromBundle(bundle)
        }
    }

    protected fun attachMvpView() {
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        presenter.detachView()
        dataHolder?.setData(presenter)
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    class DataHolder : Fragment() {

        private var data: Any? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }

        fun setData(data: Any) {
            this.data = data
        }

        fun <T> getData(): T {
            return data as T
        }
    }

}