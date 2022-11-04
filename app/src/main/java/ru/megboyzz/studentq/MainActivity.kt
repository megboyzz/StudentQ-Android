package ru.megboyzz.studentq

import android.os.Bundle
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import com.vk.sdk.api.users.UsersService
import com.vk.sdk.api.users.dto.UsersFields
import com.vk.sdk.api.users.dto.UsersUserFull
import ru.megboyzz.studentq.data.Profile
import ru.megboyzz.studentq.navigation.AppNavHost
import ru.megboyzz.studentq.ui.theme.background


var authLauncher: ActivityResultLauncher<Collection<VKScope>>? = null


@Composable
fun Int.AsPainter() = painterResource(this)

@Composable
fun Int.AsString() = stringResource(this)

@Composable
fun Dp.SpacerWidth() = Spacer(Modifier.width(this))

@Composable
fun Dp.SpacerHeight() = Spacer(Modifier.height(this))


class MainActivity : ComponentActivity() {

    val LOG_TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = App.instance?.database
        val dao = db?.profileDao()

        authLauncher = VK.login(this) { result: VKAuthenticationResult ->
            when (result) {
                is VKAuthenticationResult.Success -> {
                    Toast.makeText(this, "Успешный успех", Toast.LENGTH_LONG).show()
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
                            }else{
                                Log.e(LOG_TAG, "Какие то поля null((((")
                            }

                            Log.i("lol", "lol")
                        }
                    }

                    //Создает кофиг
                    val config = VKApiConfig(
                        context = this,
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

        val profile = dao?.getProfile()

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = background
            ) {
                AppNavHost()
            }
        }
    }

}