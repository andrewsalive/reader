package example.com.reader.domain

import example.com.reader.domain.entity.Author
import example.com.reader.domain.entity.Comment
import example.com.reader.domain.entity.Post
import io.reactivex.Single
import javax.inject.Inject

open class PostsUnit @Inject constructor(private val postRestApi: PostRestApi) : LogicUnit() {

    open fun getPostList(): Single<List<Post>> {
        return postRestApi.getPostList()
                .compose(applySchedulersToSingle())
    }

    fun getComments(postId: String): Single<List<Comment>> {
        return postRestApi.getComments(postId)
                .compose(applySchedulersToSingle())
    }

    fun getAuthor(userId: String): Single<Author> {
        return postRestApi.getAuthor(userId)
                .compose(applySchedulersToSingle())
    }

}