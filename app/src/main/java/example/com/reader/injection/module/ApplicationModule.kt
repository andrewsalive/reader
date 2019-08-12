package example.com.reader.injection.module

import android.content.Context
import dagger.Binds
import dagger.Module
import example.com.reader.AndroidApp
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @Singleton
    @Binds
    abstract fun provideContext(app: AndroidApp): Context
}