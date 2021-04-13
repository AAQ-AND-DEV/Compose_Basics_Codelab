package com.example.composebasicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun MyScreenContent(names: List<String> = List(1000){"Hello Android #$it"}) {
    val counterState = remember { mutableStateOf(0) }

    Column(Modifier.fillMaxHeight()) {
       NameList(names, Modifier.weight(1f))
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

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(items = names){ name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Preview(showBackground = true, name = "default_preview")
@Composable
fun DefaultPreview() {
    MyApp{
        MyScreenContent()
    }
}

@Preview(showBackground = true, name = "other_preview")
@Composable
fun OtherPreview() {
    ComposeBasicsCodelabTheme {
        Greeting(name = "Aaron")

    }
}