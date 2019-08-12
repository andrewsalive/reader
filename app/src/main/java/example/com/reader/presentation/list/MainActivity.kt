package example.com.reader.presentation.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import example.com.reader.R
import example.com.reader.domain.entity.Post
import example.com.reader.extensions.doOnClick
import example.com.reader.extensions.hide
import example.com.reader.extensions.show
import example.com.reader.presentation.common.loading.BaseLoadingActivity
import example.com.reader.presentation.details.PostDetailsActivity
import example.com.reader.presentation.misc.ExtraConstants
import kotlinx.android.synthetic.main.activity_post_list.*
import kotlinx.android.synthetic.main.error_view.*

class MainActivity : BaseLoadingActivity<PostListMvpView, PostListPresenter>(), PostListMvpView {

    private var adapter: PostsListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setSupportActionBar(toolbar)

        setupViews()

        refreshPostList()
    }

    private fun setupViews() {
        adapter = PostsListAdapter(this)
        adapter?.itemClicked {
            presenter.openDetails(it)
        }

        rvPosts.layoutManager = LinearLayoutManager(this)
        rvPosts.adapter = adapter
        swipeToRefresh.setOnRefreshListener { refreshPostList() }

        btnErrorAction.doOnClick { refreshPostList() }
    }

    private fun refreshPostList() {
        errorView.hide()
        showLoading()
        presenter.getPosts()
    }

    override fun onPostsReceived(postList: List<Post>) {
        rvPosts.show()
        adapter?.items = postList.toMutableList()
        if (adapter?.itemCount == 0) {
            tvNoPosts.show()
        } else {
            tvNoPosts.hide()
        }
    }

    override fun onOpenDetails(post: Post?) {
        val intent = Intent(this, PostDetailsActivity::class.java)
        intent.putExtra(ExtraConstants.EXTRA_POST_DATA, post)
        startActivity(intent)
    }


    override fun showLoading() {
        swipeToRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        super.hideLoading()
        swipeToRefresh.isRefreshing = false
    }

    override fun onNoInternetConnection() {
        if (adapter?.itemCount!! > 0) {
            showMessage(R.string.connection_error)
        } else {
            imvError.setImageResource(R.drawable.ic_no_internet_connection)
            tvErrorDescription.setText(R.string.connection_error)
            errorView.show()
            rvPosts.hide()
        }
    }

    override fun onSomethingWentWrong(message: String?) {
        if (adapter?.itemCount!! > 0) {
            showMessage(message ?: getString(R.string.default_error))
        } else {
            imvError.setImageResource(R.drawable.ic_sad_girl)
            tvErrorDescription.setText(R.string.default_error)
            errorView.show()
            rvPosts.hide()
        }
    }

}