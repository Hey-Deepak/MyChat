package com.dc.mychat.di

import android.content.Context
import androidx.activity.ComponentActivity
import com.dc.mychat.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        @ApplicationContext appContext: Context
    ): UserRepository {
        return UserRepository(
            appContext.getSharedPreferences("main", ComponentActivity.MODE_PRIVATE)
        )
    }


}