package net.tsukajizo.stampapp

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.tsukajizo.stampapp.di.AppComponent
import net.tsukajizo.stampapp.di.DaggerAppComponent
import net.tsukajizo.stampapp.task.InitializeStampTask
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    @Inject
    lateinit var initializeStampTask: InitializeStampTask

    companion object {
        private var app: App? = null

        fun app(): App? {
            return app
        }

    }

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        app = this
        DaggerAppComponent.builder()
                .create(this)
                .inject(this)
        initializeStampData()
    }

    fun appComponent(): AppComponent? {
        return appComponent
    }


    private fun initializeStampData() {
        initializeStampTask.execute()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}