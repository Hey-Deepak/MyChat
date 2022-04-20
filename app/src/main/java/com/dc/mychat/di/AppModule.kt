package com.dc.mychat.di

import android.content.Context
import androidx.activity.ComponentActivity
import com.dc.mychat.data.repository.local.ProfileRepositoryImp
import com.dc.mychat.data.repository.local.UserRepositoryImp
import com.dc.mychat.data.repository.remote.MessageRepositoryImp
import com.dc.mychat.domain.model.Profile
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
        return UserRepositoryImp(
            appContext.getSharedPreferences("main", ComponentActivity.MODE_PRIVATE)
        )
    }

    @Singleton
    @Provides
    fun provideProfileRepository(
        @ApplicationContext appContext: Context
    ) : ProfileRepository {
        return ProfileRepositoryImp(appContext.getSharedPreferences("main", ComponentActivity.MODE_PRIVATE))
    }

    @Singleton
    @Provides
    fun provideMessageRepository() : MessageRepository {
        return MessageRepositoryImp()
    }


}