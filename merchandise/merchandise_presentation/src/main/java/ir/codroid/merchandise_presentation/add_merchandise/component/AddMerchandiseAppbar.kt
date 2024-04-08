package ir.codroid.merchandise_presentation.add_merchandise.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ir.codroid.core.R

@ExperimentalMaterial3Api
@Composable
fun AddMerchandiseAppbar(
    title: String,
    modifier: Modifier = Modifier,
    onclick: () -> Unit
) {
    TopAppBar(title = { Text(text = title) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { onclick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = stringResource(
                        id = R.string.hint_desc
                    )
                )
            }
        })
}

@Preview
@Composable
private fun AddMerchandiseAppbarPreview() {

}