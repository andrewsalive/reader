package example.com.reader.domain

import example.com.reader.domain.entity.Author
import example.com.reader.domain.entity.Comment
import example.com.reader.domain.entity.Post
import io.reactivex.Single

interface PostRestApi {

    fun getPostList(): Single<List<Post>>

    fun getComments(postId : String): Single<List<Comment>>

    fun getAuthor(userId : String): Single<Author>

}