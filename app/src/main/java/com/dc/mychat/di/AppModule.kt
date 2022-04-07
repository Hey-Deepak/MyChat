package com.dc.mychat.di

import android.content.Context
import androidx.activity.ComponentActivity
import com.dc.mychat.domain.repository.MessageRepository
import com.dc.mychat.domain.repository.ProfileRepository
import com.dc.mychat.domain.repository.UserRepository
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

    @Singleton
    @Provides
    fun provideProfileRepository() : ProfileRepository {
        return ProfileRepository()
    }

    @Singleton
    @Provides
    fun provideMessageRepository() : MessageRepository {
        return MessageRepository()
    }


}