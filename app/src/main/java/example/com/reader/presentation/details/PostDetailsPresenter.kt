package example.com.reader.presentation.details

import android.os.Bundle
import example.com.reader.domain.PostsUnit
import example.com.reader.domain.entity.Post
import example.com.reader.domain.exception.NetworkException
import example.com.reader.presentation.common.loading.BaseLoadingPresenter
import example.com.reader.presentation.misc.ExtraConstants
import javax.inject.Inject


class PostDetailsPresenter @Inject constructor() : BaseLoadingPresenter<PostDetailsMvpView>() {

    @Inject
    lateinit var postUnit: PostsUnit

    private lateinit var postItem: Post

    override fun initFromBundle(bundle: Bundle) {
        postItem = bundle.getParcelable(ExtraConstants.EXTRA_POST_DATA)
    }

    fun getPostInfo() {
        mvpView?.onPostData(postItem)
    }

    fun getComments() {
        val getCommentsRequest = createRequest(postUnit.getComments(postItem.id),
                { mvpView?.onCommentsReceived(it) },
                { handleError(it) })
                .setShowLoading(false)
        executeRequest(getCommentsRequest)
    }

    fun getAuthor() {
        val getAuthorRequest = createRequest(postUnit.getAuthor(postItem.userId),
                { mvpView?.onAuthorReceived(it) },
                { handleError(it) })
                .setShowLoading(false)
        executeRequest(getAuthorRequest)
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