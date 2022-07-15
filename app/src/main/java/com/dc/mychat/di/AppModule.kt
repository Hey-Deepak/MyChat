package com.dc.mychat.di

import android.content.Context
import androidx.activity.ComponentActivity
import com.dc.mychat.data.repository.local.LocalRepositoryImp
import com.dc.mychat.data.repository.remote.MessageRepositoryImp
import com.dc.mychat.data.repository.remote.ServerRepositoryImp
import com.dc.mychat.domain.repository.MessageRepository
import com.dc.mychat.domain.repository.ServerRepository
import com.dc.mychat.domain.repository.LocalRepository
import com.dc.mychat.ui.viewmodel.SharedViewModel
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
    fun provideLocalRepository(
        @ApplicationContext appContext: Context
    ): LocalRepository {
        return LocalRepositoryImp(
            appContext.getSharedPreferences("main", ComponentActivity.MODE_PRIVATE)
        )
    }


    @Singleton
    @Provides
    fun provideMessageRepository(): MessageRepository {
        return MessageRepositoryImp()
    }

    @Singleton
    @Provides
    fun provideServerRepository(): ServerRepository {
        return ServerRepositoryImp()
    }

    @Singleton
    @Provides
    fun provideSharedViewModel(
        @ApplicationContext appContext: Context
    ): SharedViewModel {
        return SharedViewModel(
            provideLocalRepository(
                appContext
            )
        )
    }


}