package example.com.reader.fakes.injection.module

import dagger.Module
import dagger.Provides
import example.com.reader.domain.PostsUnit
import javax.inject.Singleton


@Module
class FakeApplicationModule(val postsUnit: PostsUnit) {

    @Provides
    @Singleton
    fun providePostUnit() : PostsUnit {
        return postsUnit
    }

}