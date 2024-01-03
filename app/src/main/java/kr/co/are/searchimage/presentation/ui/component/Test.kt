package kr.co.are.searchimage.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun Test(
    modifier: Modifier = Modifier
) {
    /*val testViewModel: TestViewModel = hiltViewModel<TestViewModel>()

    val textStr = testViewModel.text.observeAsState() ?: ""*/
    Box(modifier.background(Color.White)) {
        Button(onClick = {
           // testViewModel.callTest()
        }) {
           // Text(text = "$textStr")
        }
    }
}

@Preview(name = "Test")
@Composable
private fun PreviewTest() {
    Test()
}