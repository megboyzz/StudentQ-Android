package ru.megboyzz.studentq

import android.app.Activity
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.AndroidViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.sdk.api.users.UsersService
import com.vk.sdk.api.users.dto.UsersUserFull
import ru.megboyzz.studentq.data.Profile

class AppViewModel(activity: ComponentActivity) : AndroidViewModel(activity.application) {

     val authLauncher = VK.login(activity) { result: VKAuthenticationResult ->

        when (result) {
            is VKAuthenticationResult.Success -> {
                Toast.makeText(activity.applicationContext, "Успешный успех", Toast.LENGTH_LONG).show()
                val userId = VK.getUserId()
                val value = object : VKApiCallback<List<UsersUserFull>> {

                    override fun fail(error: Exception) {
                        error.message?.let { Log.e("VK::getUser", it) }
                    }

                    override fun success(result: List<UsersUserFull>) {

                        val name = result[0].firstName
                        val surname = result[0].lastName
                        val id = userId.value

                        if(name != null && surname != null) {
                            val profile = Profile(name = name, surname = surname, vkID = id, isHeadman = false)
                            dao?.setProfile(profile)
                        }
                    }
                }

                //Создает кофиг
                val config = VKApiConfig(
                    context = activity.applicationContext,
                    langProvider = {"ru"}
                )
                VK.setConfig(config)

                VK.execute(
                    UsersService().usersGet(listOf(userId), listOf()),
                    value
                )

            }
            is VKAuthenticationResult.Failed -> {
                Toast.makeText(this, "Не успех", Toast.LENGTH_LONG).show()
            }
        }
    }

}