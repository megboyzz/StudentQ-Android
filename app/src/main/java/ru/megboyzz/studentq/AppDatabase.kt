package ru.megboyzz.studentq

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.megboyzz.studentq.data.Profile
import ru.megboyzz.studentq.data.ProfileDAO


@Database(
    entities = [Profile::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun profileDao(): ProfileDAO
}