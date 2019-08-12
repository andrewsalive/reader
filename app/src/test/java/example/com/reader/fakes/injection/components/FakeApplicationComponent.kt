package example.com.reader.fakes.injection.components


import dagger.Component
import example.com.reader.fakes.injection.module.FakeApplicationModule
import example.com.reader.injection.components.AppComponent
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(FakeApplicationModule::class))
interface FakeApplicationComponent : AppComponent