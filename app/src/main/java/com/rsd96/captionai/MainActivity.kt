package com.rsd96.captionai

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rsd96.captionai.ui.theme.CaptionaiTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptionaiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            // todo - replace with actual uri
            navController.navigate("caption/some-uri")
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    val launchImagePicker = {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {

        composable(NavigationItem.Home.route) {
            Home(launchImagePicker)
        }

        composable(NavigationItem.GenerateCaption.route) {
            GenerateCaption()
        }
    }
}

@Composable
fun GenerateCaption() {

    Surface {
        Text(text = "Generate caption screen")

    }

}

@Composable
fun Home(launchImagePicker: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Upload a picture to get started!")
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Button(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "camera icon"
                )
            }
            Button(onClick = { launchImagePicker() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_gallery),
                    contentDescription = "gallery icon"
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CaptionaiTheme {
//        UploadPrompt()
//    }
//}