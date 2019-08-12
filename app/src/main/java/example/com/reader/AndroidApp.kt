package example.com.reader

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import example.com.reader.injection.components.DaggerAppComponent
import example.com.reader.injection.module.NetworkModule
import timber.log.Timber

class AndroidApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val builder = DaggerAppComponent.builder()
        builder.networkModule(NetworkModule(BuildConfig.BASE_URL))

        return builder.create(this)

    }

}
