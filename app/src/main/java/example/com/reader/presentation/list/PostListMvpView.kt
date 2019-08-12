package example.com.reader.presentation.list

import example.com.reader.domain.entity.Post
import example.com.reader.presentation.common.loading.StatefulDataLoadingMvpView


interface PostListMvpView : StatefulDataLoadingMvpView {

    fun onPostsReceived(postList: List<Post>)

    fun onOpenDetails(post: Post?)

}