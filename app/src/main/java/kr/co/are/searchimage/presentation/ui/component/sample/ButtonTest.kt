package kr.co.are.searchimage.presentation.ui.component.sample

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
import kotlinx.coroutines.launch


@Composable
fun ButtonTest(
    title:String,
    modifier: Modifier = Modifier
) {
    val testViewModel: ButtonTestViewModel = hiltViewModel<ButtonTestViewModel>()
    val textStr = testViewModel.text.observeAsState().value ?: ""

    Box(modifier.background(Color.White)) {
        Button(onClick = {
            testViewModel.callTest(title)
            testViewModel.getPhotoInfoList(1)
        }) {
            Text(text = textStr)
        }
    }
}