package example.com.reader.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadFromUrl(url: String?, placeHolder: Int? = null) {

    var request = Glide.with(context)
            .load(url)

    placeHolder?.let {
        request = request.apply(RequestOptions
                .placeholderOf(placeHolder))
    }

    request.into(this)

}