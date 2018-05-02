package net.tsukajizo.stampapp.di

import dagger.Component
import net.tsukajizo.stampapp.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class)
)
interface AppComponent {
    fun inject(activity: MainActivity)
}