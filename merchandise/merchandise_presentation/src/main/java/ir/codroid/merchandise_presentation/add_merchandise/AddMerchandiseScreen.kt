package ir.codroid.merchandise_presentation.add_merchandise

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.codroid.core.R
import ir.codroid.core.util.UiEvent
import ir.codroid.core_ui.LocalSpacing
import ir.codroid.core_ui.component.DefaultButton
import ir.codroid.core_ui.component.ImageFromGallery
import ir.codroid.core_ui.component.displaySnackBar
import ir.codroid.merchandise_presentation.add_merchandise.component.AddMerchandiseAppbar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun AddMerchandiseScreen(
    viewModel: AddMerchandiseViewModel = hiltViewModel(),
    merchandiseItemId: Int,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onSuccess: () -> Unit
) {
    val state = viewModel.state
    val context = LocalContext.current
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = merchandiseItemId) {
        viewModel.onEvent(AddMerchandiseContract.Event.OnMerchandiseItemId(merchandiseItemId))
    }

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.NavigateUp -> navController.popBackStack()
                is UiEvent.ShowSnackbar -> {
                    displaySnackBar(
                        this,
                        snackbarHostState,
                        uiEvent.message.asString(context),
                        onDismissed = {},
                        onActionPerformed = {})
                }

                is UiEvent.Success -> onSuccess()
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            AddMerchandiseAppbar(
                title = state.title.asString(context),
                modifier = Modifier.fillMaxWidth()
            ) {
                navController.popBackStack()
            }
        },
    ) {
        AddMerchandiseContent(
            state,
            modifier = Modifier.padding(top = spacing.spaceExtraLarge),
            onImageChange = {
                viewModel.onEvent(AddMerchandiseContract.Event.OnImageChange(it))
            },
            onNameChange = {
                viewModel.onEvent(AddMerchandiseContract.Event.OnNameChange(it))
            },
            onCodeChange = {
                viewModel.onEvent(AddMerchandiseContract.Event.OnCodeChange(it))
            },
            onSalesPriceChange = {
                viewModel.onEvent(AddMerchandiseContract.Event.OnSalesPriceChange(it))
            },
            onPurchasePriceChange = {
                viewModel.onEvent(AddMerchandiseContract.Event.OnPurchasePriceChange(it))
            },
            onCountChange = {
                viewModel.onEvent(AddMerchandiseContract.Event.OnCountChange(it))
            },
            onConfirm = {
                viewModel.onEvent(AddMerchandiseContract.Event.OnAddClick)
            })
    }


}

@Composable
private fun AddMerchandiseContent(
    state: AddMerchandiseContract.State,
    modifier: Modifier = Modifier,
    onImageChange: (Bitmap?) -> Unit,
    onNameChange: (String) -> Unit,
    onCodeChange: (String) -> Unit,
    onCountChange: (String) -> Unit,
    onSalesPriceChange: (String) -> Unit,
    onPurchasePriceChange: (String) -> Unit,
    onConfirm: () -> Unit,
) {
    val spacing = LocalSpacing.current
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageFromGallery(
            bitmap = state.merchandise.image,
            shape = RoundedCornerShape(spacing.spaceSmall)
        ) {
            onImageChange(it)
        }

        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        OutlinedTextField(
            value = state.merchandise.name,
            onValueChange = { onNameChange(it) },
            label = {
                Text(text = stringResource(id = R.string.hint_name))
            },
            placeholder = { Text(text = stringResource(id = R.string.place_holder_merchandise_name)) },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)

        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        OutlinedTextField(
            value = state.merchandise.code,
            onValueChange = { onCodeChange(it) },
            label = {
                Text(text = stringResource(id = R.string.hint_merchandise_code))
            },
            placeholder = { Text(text = stringResource(id = R.string.place_holder_merchandise_code)) },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
                .focusRequester(focusRequester)

        )


        // Count Text Field
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        OutlinedTextField(
            value = if (state.merchandise.count < 0) "" else state.merchandise.count.toString(),
            onValueChange = { onCountChange(it) },
            label = {
                Text(text = stringResource(id = R.string.hint_merchandise_count))
            },
            placeholder = { Text(text = stringResource(id = R.string.place_holder_merchandise_count)) },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
                .focusRequester(focusRequester)
        )

        // Purchase Price Text Field
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        OutlinedTextField(
            value = if (state.merchandise.purchasePrice < 0) "" else state.merchandise.purchasePrice.toString(),
            onValueChange = { onPurchasePriceChange(it) },
            label = {
                Text(text = stringResource(id = R.string.hint_merchandise_purchase_price))
            },
            placeholder = { Text(text = stringResource(id = R.string.place_holder_merchandise_purchase_price)) },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
                .focusRequester(focusRequester)
        )

        // Sales Price Text Field
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        OutlinedTextField(
            value = if (state.merchandise.salesPrice < 0) "" else state.merchandise.salesPrice.toString(),
            onValueChange = { onSalesPriceChange(it) },
            label = {
                Text(text = stringResource(id = R.string.hint_merchandise_sales_price))
            },
            placeholder = { Text(text = stringResource(id = R.string.place_holder_merchandise_sales_price)) },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
                .focusRequester(focusRequester)
        )


        Spacer(modifier = Modifier.height(spacing.spaceLarge))

        DefaultButton(
            text = stringResource(id = R.string.confirm),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
        ) {
            onConfirm()
        }

    }

}

@Preview
@Composable
private fun AddMerchandiseScreenPreview() {
    AddMerchandiseContent(
        AddMerchandiseContract.State(),
        onImageChange = {},
        onNameChange = {},
        onCodeChange = {},
        onSalesPriceChange = {},
        onPurchasePriceChange = {},
        onCountChange = {}) {}
}