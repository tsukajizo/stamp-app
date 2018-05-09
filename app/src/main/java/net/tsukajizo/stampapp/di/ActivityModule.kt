package net.tsukajizo.stampapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.tsukajizo.stampapp.presentation.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity
}