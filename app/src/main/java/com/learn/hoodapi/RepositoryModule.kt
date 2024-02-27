package com.learn.hoodapi

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

/**
created by Rachit on 12/24/2023.
 */

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepo(repoImpl: RepoImpl):Repository
}