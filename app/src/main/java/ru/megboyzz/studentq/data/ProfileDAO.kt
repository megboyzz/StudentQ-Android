package ru.megboyzz.studentq.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
public interface ProfileDAO {

    @Query("SELECT * FROM Profile WHERE id = 1")
    fun getProfile(): Profile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setProfile(profile: Profile)

}