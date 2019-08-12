package example.com.reader.data.entity.response

import com.google.gson.annotations.SerializedName
import example.com.reader.domain.entity.Author

data class AuthorEntity(@SerializedName("id") private val id: String,
                        @SerializedName("name") private val name: String,
                        @SerializedName("username") private val username: String,
                        @SerializedName("email") private val email: String,
                        @SerializedName("phone") private val phone: String) {

    fun toAuthor(): Author {
        return Author(id,
                name,
                username,
                email,
                phone)
    }

}