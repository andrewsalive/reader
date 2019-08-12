package example.com.reader.data.entity.response

import com.google.gson.annotations.SerializedName
import example.com.reader.domain.entity.Comment

data class CommentEntity(@SerializedName("id") private val id: String,
                         @SerializedName("postId") private val postId: String,
                         @SerializedName("name") private val name: String,
                         @SerializedName("email") private val email: String,
                         @SerializedName("body") private val body: String) {

    fun toComment(): Comment {
        return Comment(id,
                postId,
                name,
                email,
                body)
    }

}