package net.tsukajizo.stampapp.di

import dagger.Component
import dagger.android.AndroidInjector
import net.tsukajizo.stampapp.App
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ActivityModule::class)
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}