package net.tsukajizo.stampapp

import android.app.Application
import net.tsukajizo.stampapp.di.AppComponent
import net.tsukajizo.stampapp.di.AppModule
import net.tsukajizo.stampapp.di.DaggerAppComponent
import net.tsukajizo.stampapp.task.InitializeStampTask
import javax.inject.Inject


class App : Application() {

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
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this)).build()
        App.app()!!.appComponent()!!.inject(this)
        initializeStampData()
    }

    fun appComponent(): AppComponent? {
        return appComponent
    }


    private fun initializeStampData() {
        initializeStampTask.execute()
    }

}