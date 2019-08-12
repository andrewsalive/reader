package example.com.reader.presentation.list

import example.com.reader.domain.PostRestApi
import example.com.reader.domain.PostsUnit
import example.com.reader.domain.entity.Post
import example.com.reader.fakes.FakePostUnit
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PostListPresenterTest {

    @Mock
    lateinit var mockRestService: PostRestApi

    @Mock
    lateinit var mockView: PostListMvpView

    lateinit var postsUnit: PostsUnit

    lateinit var userListPresenter: PostListPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        postsUnit = FakePostUnit(mockRestService)
        userListPresenter = PostListPresenter()
    }

    @Test
    fun testPosts_errorCase_showError() {
        // Given
        val error = "Test error"
        val single: Single<List<Post>> = Single.create { emitter ->
            emitter.onError(Exception(error))
        }

        // When
        Mockito.`when`(postsUnit.getPostList()).thenReturn(single)

        userListPresenter.attachView(mockView)
        userListPresenter.getPosts()

        // Then
        Mockito.verify(mockView).hideLoading()
    }

}