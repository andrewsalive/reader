package example.com.reader.data.network

import example.com.reader.data.entity.response.AuthorEntity
import example.com.reader.data.entity.response.CommentEntity
import example.com.reader.data.entity.response.PostEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitRestService {

    @GET(ApiConstants.POSTS)
    fun getPostList(): Single<List<PostEntity>>

    @GET(ApiConstants.COMMENTS)
    fun getComments(@Query("postId") postId: String): Single<List<CommentEntity>>


    @GET(ApiConstants.USERS)
    fun getAuthor(): Single<List<AuthorEntity>>

}