package net.tsukajizo.stampapp.di

import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.tsukajizo.stampapp.presentation.collect.QRCameraFragment
import net.tsukajizo.stampapp.presentation.collect.StampGetActivity

@Module
abstract class StampGetActivityModule {

    @Binds
    abstract fun providesAppCompatActivity(activity: StampGetActivity): AppCompatActivity

    @ContributesAndroidInjector
    abstract fun contributeQRCameraFragment(): QRCameraFragment
}