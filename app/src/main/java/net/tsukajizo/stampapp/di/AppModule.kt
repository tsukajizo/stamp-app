package net.tsukajizo.stampapp.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.database.AppDatabase
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideContext(): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, context.resources.getString(R.string.db_name)).build()
    }
}