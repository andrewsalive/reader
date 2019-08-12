package example.com.reader.data.network

import example.com.reader.domain.PostRestApi
import example.com.reader.domain.entity.Author
import example.com.reader.domain.entity.Comment
import example.com.reader.domain.entity.Post
import example.com.reader.domain.exception.NetworkException
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RetrofitRestApi(private val restApiService: RetrofitRestService,
                      private val errorConverter: Converter<ResponseBody, Error>) : PostRestApi {

    override fun getPostList(): Single<List<Post>> {
        return restApiService.getPostList()
                .flatMap {
                    Observable.fromIterable(it)
                            .map { item -> item.toPostListItem() }
                            .toList()
                }
                .onErrorResumeNext { wrapSingleError(it) }
    }

    override fun getComments(postId: String): Single<List<Comment>> {
        return restApiService.getComments(postId)
                .flatMap {
                    Observable.fromIterable(it)
                            .map { item -> item.toComment() }
                            .toList()
                }
                .onErrorResumeNext { wrapSingleError(it) }
    }

    override fun getAuthor(userId: String): Single<Author> {
        return restApiService.getAuthor()
                .flatMap {
                    Observable.fromIterable(it)
                            .map { item -> item.toAuthor() }
                            .filter { author -> author.id == userId }
                            .firstOrError()

                }
                .onErrorResumeNext { wrapSingleError(it) }
    }

//    override fun login(phone: String, password: String): Single<AuthResponse> {
//        return restApiService.login(phone, password)
//                .map { it.data }
//                .map { it.toAuthResponce() }
//                .onErrorResumeNext { wrapSingleError(it) }
//    }
//
//    override fun uploadFiles(photos: List<FileData>): Single<List<Image>> {
//
//        val parts = photos.mapIndexed { index, fileData ->
//            val request = RequestBody.create(MediaType.parse(fileData.mimeType), fileData.file)
//            return@mapIndexed MultipartBody.Part.createFormData("file${index + 1}", fileData.file.name, request)
//        }
//        return restApiService.uploadFiles(parts)
//                .map { it.data }
//                .flatMapObservable { Observable.fromIterable(it) }
//                .map { ImageEntity.toImage(it) }
//                .toList()
//                .onErrorResumeNext { wrapSingleError(it) }
//    }


//    private fun <T> wrapObservableError(t: Throwable): Observable<T> = Observable.error<T>(wrapError(t))

    private fun <T> wrapSingleError(t: Throwable): Single<T> = Single.error<T>(wrapError(t))

//    private fun wrapCompletableError(t: Throwable): Completable = Completable.error(wrapError(t))

    private fun wrapError(t: Throwable): Throwable = when (t) {
        is HttpException -> NetworkException(t, t.code(), parseError(t).message)
        is UnknownHostException -> NetworkException(t, NetworkException.NETWORK_ERROR)
        is ConnectException -> NetworkException(t, NetworkException.NETWORK_ERROR)
        is SocketTimeoutException -> NetworkException(t, NetworkException.TIMEOUT_ERROR)
        else -> NetworkException(t)
    }

    private fun parseError(httpException: HttpException): Error {
        return try {
            val errorResponseBody = httpException.response().errorBody()
            errorConverter.convert(errorResponseBody)
        } catch (e: IOException) {
            Error(httpException.message())
        }
    }

}