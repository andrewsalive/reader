package example.com.reader.presentation.common

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment(), MvpView {

    protected abstract val layoutResId: Int

    protected open val parentActivity: BaseActivity
        get() {
            if (activity !is BaseActivity) {
                throw IncorrectActivityType()
            }
            return activity as BaseActivity
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val argumentsBundle: Bundle = it
            if (savedInstanceState != null) {
                argumentsBundle.putAll(savedInstanceState)
            }
            extractArguments(argumentsBundle)
        }
    }

    protected open fun extractArguments(args: Bundle) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun showMessage(message: String?) {
        parentActivity.showMessage(message)
    }

    override fun showMessage(@StringRes messageResId: Int) {
        parentActivity.showMessage(messageResId)
    }


    class IncorrectActivityType : RuntimeException("Fragment must be attached to BaseActivity")
}
