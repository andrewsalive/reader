package example.com.reader.presentation.details

import android.os.Bundle
import example.com.reader.R
import example.com.reader.data.network.ApiConstants
import example.com.reader.domain.entity.Author
import example.com.reader.domain.entity.Comment
import example.com.reader.domain.entity.Post
import example.com.reader.extensions.loadFromUrl
import example.com.reader.extensions.markAsNotImplemented
import example.com.reader.presentation.common.loading.BaseLoadingActivity
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_scrolling_post.*

class PostDetailsActivity : BaseLoadingActivity<PostDetailsMvpView, PostDetailsPresenter>(),
        PostDetailsMvpView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.markAsNotImplemented()

        loadDetails()
    }

    private fun loadDetails() {
        presenter.getPostInfo()
        presenter.getComments()
        presenter.getAuthor()
    }


    override fun onPostData(post: Post) {
        toolbarLayout.title = post.title
        tvBody.text = post.body

        ivAvatar.loadFromUrl(ApiConstants.AVATARS + post.userId, R.drawable.ic_person_black_24dp)
    }

    override fun onCommentsReceived(comments: List<Comment>) {
        tvPostId.text = getString(R.string.comments_count, comments.size)
    }

    override fun onAuthorReceived(author: Author) {
        tvUserId.text = author.name
    }

    override fun onNoInternetConnection() {
        showMessage(R.string.connection_error)
    }

    override fun onSomethingWentWrong(message: String?) {
        showMessage(message ?: getString(R.string.default_error))
    }

}