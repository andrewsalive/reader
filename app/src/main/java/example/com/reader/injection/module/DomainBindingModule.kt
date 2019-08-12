package example.com.reader.injection.module

import dagger.Binds
import dagger.Module
import example.com.reader.data.network.RetrofitRestApi
import example.com.reader.domain.PostRestApi

@Module
abstract class DomainBindingModule {

    @Binds
    abstract fun postApi(restApi: RetrofitRestApi): PostRestApi

}