package net.tsukajizo.stampapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.tsukajizo.stampapp.presentation.MainActivity
import net.tsukajizo.stampapp.presentation.collect.StampGetActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [StampGetActivityModule::class])
    abstract fun contributeStampGetActivity(): StampGetActivity
}