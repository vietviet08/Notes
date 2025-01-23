package com.vietquoc.todo_list.di

import android.app.Application
import androidx.room.Room
import com.vietquoc.todo_list.db.NoteDb
import com.vietquoc.todo_list.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDb(application: Application): NoteDb {
        return Room.databaseBuilder(
            context = application,
            klass = NoteDb::class.java,
            name = "news_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDb): NoteRepository {
        return NoteRepository(db)
    }


}