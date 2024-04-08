package ir.codroid.merchandise_presentation.merchandise_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ir.codroid.core_ui.LocalSpacing
import ir.codroid.core_ui.component.DefaultSearchBar
import ir.codroid.merchandise_domain.model.CountUnit
import ir.codroid.merchandise_domain.model.Merchandise
import ir.codroid.merchandise_presentation.merchandise_list.component.MerchandiseItem
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalMaterial3Api
@Composable
fun MerchandiseListScreen(
    viewModel: MerchandiseListViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val state = viewModel.state
    val spacing = LocalSpacing.current

    Column {
        DefaultSearchBar(
            searchQuery = state.searchQuery,
            isSearching = state.isSearching,
            placeholder = stringResource(id = ir.codroid.core.R.string.search_merchandise),
            onQueryChange = {
                viewModel.onEvent(
                    MerchandiseContract
                        .Event
                        .OnSearchQueryChange(it)
                )
            }, onSearch = {
                viewModel.onEvent(
                    MerchandiseContract
                        .Event
                        .OnSearchQueryChange(it)
                )
            },
            onActiveChange = {
                viewModel.onEvent(
                    MerchandiseContract
                        .Event
                        .OnActiveSearching(it)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MerchandiseListContent(state.searchedMerchandiseList) { merchandiseItemId ->
                onItemClick(merchandiseItemId)
            }
        }

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        MerchandiseListContent(state.merchandiseList) { merchandiseItemId ->
            onItemClick(merchandiseItemId)
        }
    }
}


@Composable
private fun MerchandiseListContent(
    merchandiseList: List<Merchandise>,
    onItemClick: (Int) -> Unit
) {
    val spacing = LocalSpacing.current
    LazyColumn {
        items(merchandiseList, key = {
            it.id!!
        }) { merchandise ->
            MerchandiseItem(
                merchandise = merchandise,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceSmall)
            ) { merchandiseItemId ->
                onItemClick(merchandiseItemId)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
private fun MerchandiseListScreenPreview() {
    DefaultSearchBar(
        searchQuery = "",
        isSearching = false,
        placeholder = stringResource(id = ir.codroid.core.R.string.search),
        onQueryChange = {},
        onSearch = {},
        onActiveChange = {}
    ) {

    }
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
    ) {}
}