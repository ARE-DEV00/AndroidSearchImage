package kr.co.are.searchimage.presentation.ui.composable.searchtextfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import kr.co.are.searchimage.presentation.ui.theme.BgTextField
import kr.co.are.searchimage.presentation.ui.theme.Gray600
import kr.co.are.searchimage.presentation.ui.theme.Gray800


@Composable
fun SearchTextField(
    text: String,
    hint: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = text,
        onValueChange = { onTextChanged(it) },
        textStyle = LocalTextStyle.current.copy(color = Gray800),
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(color = BgTextField, shape = RoundedCornerShape(8.dp)),
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(all = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Gray800
                    )
                    Spacer(modifier = Modifier.width(width = 3.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(20.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(Color.Transparent)
                            .padding(start = 8.dp), // Add padding to the Box
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (text.isEmpty()) {
                            Text(
                                text = hint,
                                color = Gray600,
                            )
                        }
                        innerTextField()
                    }
                }
            }

        },
    )

}