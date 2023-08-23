package com.example.individualproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.individualproject.ui.theme.IndividualProjectTheme

class PracticeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndividualProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IndividualProjectTheme {
        Greeting("Android")
    }
}

@Composable
fun ScaleInText(
    text: String,
    scaleFactor: Float,
    fontSize: Int = 16,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal
) {
    val scale by animateFloatAsState(
        targetValue = if (scaleFactor > 1f) 1f else scaleFactor
    )

    Text(
        text = text,
        modifier = Modifier
            .scale(scale)
            .padding(4.dp),
        fontSize = fontSize.sp,
       color = color,
        fontWeight = fontWeight,
    )
}


@Composable
@Preview
fun ScaleInTextPreview(@PreviewParameter(TextProvider::class) text: String) {
    ScaleInText(text = text, scaleFactor = 1.5f, 16, Color.Black, FontWeight.Normal)
}

class TextProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("Hello", "Compose", "Animation")
}

@Composable
@Preview
fun ScaleInTextWithDifferentScaleFactors(@PreviewParameter(TextProvider::class) text: String) {
    Column {
        ScaleInText(text = text, scaleFactor = 1.2f)
        ScaleInText(text = text, scaleFactor = 0.8f)
    }
}