package com.example.marvel.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvel.comicsToString
import com.example.marvel.model.CharacterComics
import com.example.marvel.model.CharacterComicsItems
import com.example.marvel.model.CharacterResult
import com.example.marvel.model.CharacterThumbnail
import com.example.marvel.model.db.Constants.CHARACTER_TABLE


@Entity(tableName = CHARACTER_TABLE)
data class DbCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val apiId: Int?,
    val name: String?,
    val thumbnail: String?,
    val comics: String?,
    val description: String?


) {
    companion object {
        fun fromCharacter(character: CharacterResult) =
            DbCharacter(
                id = 0,
                apiId = character.id,
                name = character.name,
                thumbnail = character.thumbnail?.path + character.thumbnail?.path,
                comics = character?.comics?.items?.mapNotNull { it.name }?.comicsToString()
                    ?: "no comics",
                description = character.description

            )


    }


}



