package example.com.reader.presentation.details

import example.com.reader.domain.entity.Author
import example.com.reader.domain.entity.Comment
import example.com.reader.domain.entity.Post
import example.com.reader.presentation.common.loading.StatefulDataLoadingMvpView


interface PostDetailsMvpView : StatefulDataLoadingMvpView {

    fun onPostData(post: Post)

    fun onCommentsReceived(comments: List<Comment>)

    fun onAuthorReceived(author: Author)

}