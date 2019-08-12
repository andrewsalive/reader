package example.com.reader.extensions

import android.support.design.widget.Snackbar
import android.view.View
import example.com.reader.presentation.common.DebouncingOnClickListener

fun View.doOnClick(clickAction: (view: View) -> Unit) {
    this.setOnClickListener(DebouncingOnClickListener(clickAction))
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(makeInvisible: Boolean = false) {
    this.visibility = if (makeInvisible) View.INVISIBLE else View.GONE
}


fun View.markAsNotImplemented() {
    this.doOnClick {
        Snackbar.make(it, "Not Implemented Yet", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

}