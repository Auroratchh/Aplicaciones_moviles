package com.example.examenu2.di

import android.content.Context
import androidx.room.Room
import com.example.examenu2.data.ContactDatabase
import com.example.examenu2.data.ThemeSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppInjectionModule {

    @Provides
    @Singleton
    fun provideThemeSettings(@ApplicationContext context: Context): ThemeSettings {
        return ThemeSettings(context)
    }

    @Provides
    @Singleton
    fun provideContactDatabase(@ApplicationContext context: Context): ContactDatabase {
        return Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contact_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactDao(database: ContactDatabase) = database.contactDao()
}