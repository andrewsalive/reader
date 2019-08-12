package example.com.reader.fakes

import example.com.reader.domain.PostRestApi
import example.com.reader.domain.PostsUnit
import example.com.reader.domain.entity.Post
import io.reactivex.Single
import io.reactivex.SingleEmitter


class FakePostUnit(postRestApi: PostRestApi) : PostsUnit(postRestApi) {

    override fun getPostList(): Single<List<Post>> {
        val posts = (1..100).map {
            Post(it.toString(), "User $it", "Title $it", "Body $it")
        }

        return Single.create { emitter: SingleEmitter<List<Post>> ->
            emitter.onSuccess(posts)
        }
    }
}