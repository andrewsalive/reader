package example.com.reader.domain.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class Comment(val id: String,
                   var postId: String,
                   val name: String,
                   val email: String,
                   val body: String) : Parcelable