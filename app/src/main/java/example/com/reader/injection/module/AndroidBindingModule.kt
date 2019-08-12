package example.com.reader.injection.module


import dagger.Module
import dagger.android.ContributesAndroidInjector
import example.com.reader.injection.scope.ActivityScope
import example.com.reader.presentation.details.PostDetailsActivity
import example.com.reader.presentation.list.MainActivity

@Module
abstract class AndroidBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun detailsActivity(): PostDetailsActivity

}