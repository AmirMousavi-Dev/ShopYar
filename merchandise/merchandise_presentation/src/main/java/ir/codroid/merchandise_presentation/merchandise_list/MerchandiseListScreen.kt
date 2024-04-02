package ir.codroid.merchandise_presentation.merchandise_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ir.codroid.core_ui.LocalSpacing
import ir.codroid.merchandise_domain.model.CountUnit
import ir.codroid.merchandise_domain.model.Merchandise
import ir.codroid.merchandise_presentation.merchandise_list.component.MerchandiseItem

@Composable
fun MerchandiseListScreen(
    viewModel: MerchandiseListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    MerchandiseListContent(state.merchandiseList)
}

@Composable
private fun MerchandiseListContent(
    merchandiseList: List<Merchandise>
) {
    val spacing = LocalSpacing.current
    LazyColumn {
        items(merchandiseList, key = {
            it.id!!
        }) { merchandise ->
            MerchandiseItem(merchandise = merchandise, modifier = Modifier.fillMaxWidth().padding(spacing.spaceSmall))
        }
    }
}

@Preview
@Composable
private fun MerchandiseListScreenPreview() {
    MerchandiseListContent(
        listOf(
            Merchandise(
                1, "Mobile", 100, 500, "164sd", CountUnit.NUMBER, null, 1500.0
            ),
            Merchandise(
                2, "Mobile", 100, 500, "164sd", CountUnit.NUMBER, null, 1500.0
            ),
            Merchandise(
                3, "Mobile", 100, 500, "164sd", CountUnit.NUMBER, null, 1500.0
            )
        )
    )
}