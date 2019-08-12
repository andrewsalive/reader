package example.com.reader.data.entity.response

import com.google.gson.annotations.SerializedName
import example.com.reader.domain.entity.Post

data class PostEntity(@SerializedName("id") private val id: String,
                      @SerializedName("userId") private val userId: String,
                      @SerializedName("title") private val title: String,
                      @SerializedName("body") private val body: String) {

    fun toPostListItem(): Post {
        return Post(id,
                userId,
                title,
                body)
    }

}