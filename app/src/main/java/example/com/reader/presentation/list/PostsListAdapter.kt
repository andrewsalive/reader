package example.com.reader.presentation.list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import example.com.reader.R
import example.com.reader.data.network.ApiConstants
import example.com.reader.domain.entity.Post
import example.com.reader.extensions.loadFromUrl
import example.com.reader.presentation.common.adapter.BaseRecyclerAdapter
import example.com.reader.presentation.common.adapter.BaseRecyclerHolder
import kotlinx.android.synthetic.main.item_post.view.*

class PostsListAdapter(context: Context) : BaseRecyclerAdapter<Post>(context) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        return PostItemHolder(inflater.inflate(R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        if (holder is PostItemHolder) {
            val post = getItem(position)

            holder.userId.text = context.getString(R.string.user_id, post.userId)
            holder.postId.text = context.getString(R.string.post_id, post.id)
            holder.title.text = post.title
            holder.avatar.loadFromUrl(ApiConstants.AVATARS + post.userId, R.drawable.ic_person_black_24dp)
        }
        super.onBindViewHolder(holder, position)
    }

    inner class PostItemHolder(view: View) : BaseRecyclerHolder(view) {
        var userId: TextView = view.tvUserId
        var postId: TextView = view.tvPostId
        var title: TextView = view.tvTitle
        var avatar: ImageView = view.ivAvatar
    }

}