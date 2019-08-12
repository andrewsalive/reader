package example.com.reader.domain.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class Post(val id: String,
                var userId: String,
                val title: String,
                val body: String) : Parcelable