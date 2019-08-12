package example.com.reader.presentation.list

import example.com.reader.domain.PostsUnit
import example.com.reader.domain.entity.Post
import example.com.reader.domain.exception.NetworkException
import example.com.reader.presentation.common.loading.BaseLoadingPresenter
import javax.inject.Inject


class PostListPresenter @Inject constructor() : BaseLoadingPresenter<PostListMvpView>(){

    @Inject
    lateinit var postUnit: PostsUnit

    fun getPosts() {
        val getPostListRequest = createRequest(postUnit.getPostList(),
                { mvpView?.onPostsReceived(it) },
                { handleError(it) })
        executeRequest(getPostListRequest)
    }

    fun openDetails(post: Post?) {
        mvpView?.onOpenDetails(post)
    }


    override fun handleError(t: Throwable) {
        mvpView?.hideLoading()
        if (t is NetworkException) {
            when (t.code) {
                NetworkException.NETWORK_ERROR -> mvpView?.onNoInternetConnection()
                else -> super.handleError(t)
            }
        } else {
            super.handleError(t)
        }
    }

}