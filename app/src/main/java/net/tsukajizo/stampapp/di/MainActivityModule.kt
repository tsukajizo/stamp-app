package net.tsukajizo.stampapp.di

import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.tsukajizo.stampapp.presentation.MainActivity
import net.tsukajizo.stampapp.presentation.location.StampLocationFragment
import net.tsukajizo.stampapp.presentation.title.TitleStampRallyFragment
import net.tsukajizo.stampapp.presentation.viewer.StampViewerFragment

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector
    abstract fun contributeStampViewerFragment(): StampViewerFragment

    @ContributesAndroidInjector
    abstract fun contributeStampLocationFragment(): StampLocationFragment

    @ContributesAndroidInjector
    abstract fun contributeTitleStampRallyFragment(): TitleStampRallyFragment
}