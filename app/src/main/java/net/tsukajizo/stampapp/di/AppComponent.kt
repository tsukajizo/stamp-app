package net.tsukajizo.stampapp.di

import dagger.Component
import net.tsukajizo.stampapp.App
import net.tsukajizo.stampapp.presentation.location.StampLocationFragment
import net.tsukajizo.stampapp.presentation.viewer.StampViewerFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class)
)
interface AppComponent {
    fun inject(app: App)
    fun inject(stampViewerFragment: StampViewerFragment)
    fun inject(stampLocationFragment: StampLocationFragment)
}