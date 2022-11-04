package ru.megboyzz.studentq.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    @PrimaryKey
    val id: Int = 1,
    val name: String,
    val surname: String,
    val vkID: Long,
    //Флажок от том что пользователь староста
    val isHeadman: Boolean
    )