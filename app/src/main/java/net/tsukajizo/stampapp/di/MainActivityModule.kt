package net.tsukajizo.stampapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.tsukajizo.stampapp.presentation.location.StampLocationFragment
import net.tsukajizo.stampapp.presentation.title.TitleStampRallyFragment
import net.tsukajizo.stampapp.presentation.viewer.StampViewerFragment

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeStampViewerFragment(): StampViewerFragment

    @ContributesAndroidInjector
    abstract fun contributeStampLocationFragment(): StampLocationFragment

    @ContributesAndroidInjector
    abstract fun contributeTitleStampRallyFragment(): TitleStampRallyFragment
}