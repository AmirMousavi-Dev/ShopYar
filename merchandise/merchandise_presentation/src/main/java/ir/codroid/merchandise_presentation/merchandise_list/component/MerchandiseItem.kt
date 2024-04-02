package ir.codroid.merchandise_presentation.merchandise_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.codroid.core.R
import ir.codroid.core_ui.LocalSpacing
import ir.codroid.merchandise_domain.model.CountUnit
import ir.codroid.merchandise_domain.model.Merchandise

@Composable
fun MerchandiseItem(
    merchandise: Merchandise,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall)
        ) {
            MerchandiseDefaultImage(
                bitmap = merchandise.image, modifier =
                Modifier.size(100.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceSmall)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth() ,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    MerchandiseDefaultText(
                        label = R.string.label_merchandise_name,
                        value = merchandise.name,
                        textStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    MerchandiseDefaultText(
                        label = null,
                        value = merchandise.code
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                Row(
                    modifier = Modifier.fillMaxWidth() ,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    MerchandiseDefaultText(
                        label = R.string.label_merchandise_price,
                        value = merchandise.salesPrice.toString(),
                    )
                    MerchandiseDefaultText(
                        label = R.string.label_merchandise_Count,
                        value = merchandise.count.toString(),
                    )
                }
            }
        }
    }

}

@Preview
@Composable
private fun MerchandiseItemPreview() {
    MerchandiseItem(
        merchandise = Merchandise(
            1, "Mobile", 100, 500, "164sd", CountUnit.NUMBER, null, 1500.0
        ),
        modifier = Modifier.fillMaxWidth()
    )
}