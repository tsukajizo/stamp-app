package net.tsukajizo.stampapp

import android.app.Application
import net.tsukajizo.stampapp.di.AppComponent
import net.tsukajizo.stampapp.di.AppModule
import net.tsukajizo.stampapp.di.DaggerAppComponent


class App : Application() {

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
    }

    fun appComponent(): AppComponent? {
        return appComponent
    }


}