package example.com.reader.presentation.common

import android.support.annotation.StringRes


interface MvpView {
    fun showMessage(@StringRes messageResId: Int)
    fun showMessage(message: String?)
}