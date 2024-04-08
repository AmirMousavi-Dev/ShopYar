package ir.codroid.merchandise_presentation.merchandise_list.component

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ir.codroid.core.R

@Composable
fun MerchandiseDefaultText(
    @StringRes label: Int?,
    value: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
) {
    Text(
        text = if (label != null) stringResource(id = label, value) else value,
        style = textStyle,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis
    )
}


@Preview
@Composable
private fun MerchandiseDefaultTextPreview() {
    MerchandiseDefaultText(
        R.string.label_merchandise_name,
        "Mobile",

        )
}