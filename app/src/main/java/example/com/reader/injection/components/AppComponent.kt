package example.com.reader.injection.components


import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import example.com.reader.AndroidApp
import example.com.reader.injection.module.AndroidBindingModule
import example.com.reader.injection.module.ApplicationModule
import example.com.reader.injection.module.DomainBindingModule
import example.com.reader.injection.module.NetworkModule
import javax.inject.Singleton


@Singleton
@Component(
        modules = [(ApplicationModule::class),
            (NetworkModule::class),
            (AndroidSupportInjectionModule::class),
            (AndroidBindingModule::class),
            (DomainBindingModule::class)])

interface AppComponent : AndroidInjector<AndroidApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AndroidApp>() {
        abstract fun networkModule(networkModule: NetworkModule): Builder
    }

}