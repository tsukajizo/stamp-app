package net.tsukajizo.stampapp

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import net.tsukajizo.stampapp.di.AppComponent
import net.tsukajizo.stampapp.di.DaggerAppComponent
import net.tsukajizo.stampapp.task.InitializeStampTask
import javax.inject.Inject


class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .create(this)
    }


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

        initializeStampData()
    }

    fun appComponent(): AppComponent? {
        return appComponent
    }


    private fun initializeStampData() {
        initializeStampTask.execute()
    }

}