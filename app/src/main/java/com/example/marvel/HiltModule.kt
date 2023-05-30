package com.example.marvel

import android.content.Context
import androidx.room.Room
import com.example.marvel.model.api.ApiService
import com.example.marvel.model.api.MarvelApiRepo
import com.example.marvel.model.db.CharacterDao
import com.example.marvel.model.db.CollectionDb
import com.example.marvel.model.db.CollectionDbRepo
import com.example.marvel.model.db.CollectionDbRepoImpl
import com.example.marvel.model.db.Constants.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun providesApiRepo() = MarvelApiRepo(ApiService.api)

    @Provides
    fun provideCollectionDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CollectionDb::class.java, DB).build()

    @Provides
    fun provideCharacterDao(collectionDb: CollectionDb) = collectionDb.characterDao()

    @Provides
    fun provideDbRepoImpl(characterDao: CharacterDao):CollectionDbRepo=
        CollectionDbRepoImpl(characterDao)



}