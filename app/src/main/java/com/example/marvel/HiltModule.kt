package com.example.marvel

import com.example.marvel.model.api.ApiService
import com.example.marvel.model.api.MarvelApiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun providesApiRepo()=MarvelApiRepo(ApiService.api)

}