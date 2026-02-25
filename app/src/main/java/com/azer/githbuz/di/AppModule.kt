package com.azer.githbuz.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Leave this empty for now.
    // In the future, you will put things here like:
    // - Global Configuration
    // - Analytics Engines
    // - App-wide State managers
}