package net.tsukajizo.stampapp.di

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import net.tsukajizo.stampapp.App
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.database.AppDatabase
import javax.inject.Singleton


@Module
class AppModule() {


    @Singleton
    @Provides
    fun provideAppDatabase(app: App): AppDatabase {
        val context = app.applicationContext
        return Room.databaseBuilder(context, AppDatabase::class.java, context.resources.getString(R.string.db_name)).build()
    }
}