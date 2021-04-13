package com.example.composebasicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasicscodelab.ui.theme.ComposeBasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp { MyScreenContent() }
        }
    }
}

@Composable
//adding a composable fun makes MyApp a container, thereby reusable throughout app to host other composables and maintain theme
fun MyApp(content: @Composable () -> Unit) {
    ComposeBasicsCodelabTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = listOf("Android", "there")) {
    val counterState = remember { mutableStateOf(0) }

    Column(Modifier.fillMaxHeight()) {
        Column(Modifier.weight(1f)) {
            for (name in names) {
                Greeting(name = name)
                Divider(color = Color.Black)
            }
        }
        Counter(count = counterState.value,
            updateCount = { newCount -> counterState.value = newCount })
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!", modifier = Modifier
//        .wrapContentHeight()
//        .wrapContentWidth()
//        .size(28.dp)
            .padding(12.dp)
    )
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.LightGray
        )
    ) {
        Text(stringResource(R.string.counter_text, count))
    }
}

@Preview(showBackground = true, name = "default_preview")
@Composable
fun DefaultPreview() {
    ComposeBasicsCodelabTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true, name = "other_preview")
@Composable
fun OtherPreview() {
    ComposeBasicsCodelabTheme {
        Greeting(name = "Aaron")

    }
}