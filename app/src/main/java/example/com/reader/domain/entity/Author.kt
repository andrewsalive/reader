package example.com.reader.domain.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class Author(val id: String,
                  var name: String,
                  val username: String,
                  val email: String,
                  val phone: String) : Parcelable