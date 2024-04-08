package ir.codroid.onboarding_presentation.shop_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.codroid.core.R
import ir.codroid.core.util.UiEvent
import ir.codroid.core_ui.LocalSpacing
import ir.codroid.core_ui.component.DefaultButton
import ir.codroid.core_ui.component.ImageFromGallery
import ir.codroid.core_ui.component.displaySnackBar

@Composable
fun ShopInfoScreen(
    snackBarHostState: SnackbarHostState,
    onNextClick: () -> Unit,
    viewModel: ShopInfoViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> {
                    displaySnackBar(
                        scope = this,
                        snackBarHostState = snackBarHostState,
                        message = event.message.asString(context),
                        onDismissed = {

                        }) {

                    }
                }

                else -> Unit

            }

        }
    }
    ShopInfoScreenContent(
        shopName = viewModel.state.shopName,
        shopDescription = viewModel.state.shopDescription,
        imagePatch = viewModel.state.shopImagePath,
        onShopNameChange = {
            viewModel.onEvent(ShopInfoContract.Event.OnShopNameChange(it))
        },
        onShopDescriptionChange = {
            viewModel.onEvent(ShopInfoContract.Event.OnShopDescriptionChange(it))

        },
        onShopImageChange = {
            it?.let {
                viewModel.onEvent(ShopInfoContract.Event.OnImageChange(it))
            }

        },
        onNextClick = {
            viewModel.onEvent(ShopInfoContract.Event.OnNextClick)
        }
    )
}

@Composable
fun ShopInfoScreenContent(
    shopName: String,
    shopDescription: String,
    imagePatch: String?,
    onShopNameChange: (String) -> Unit,
    onShopDescriptionChange: (String) -> Unit,
    onShopImageChange: (String?) -> Unit,
    onNextClick: () -> Unit,
) {
    val spacing = LocalSpacing.current

    val focusRequester = remember { FocusRequester() }
    Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.padding(spacing.spaceMedium)) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = spacing.spaceExtraLarge),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            ImageFromGallery(size = 256.dp, imagePath = imagePatch) {
                onShopImageChange(it)
            }
            Spacer(modifier = Modifier.height(spacing.spaceLarge))

            Text(
                text = stringResource(id = R.string.tit_shop_info),
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            Text(
                text = stringResource(id = R.string.cpt_shop_info),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(spacing.spaceLarge)
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            OutlinedTextField(
                value = shopName,
                onValueChange = { onShopNameChange(it) },
                label = {
                    Text(text = stringResource(id = R.string.hint_name))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.place_holder_shop_name))
                },
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
                value = shopDescription,
                onValueChange = { onShopDescriptionChange(it) },
                label = {
                    Text(text = stringResource(id = R.string.hint_desc))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.place_holder_shop_desc))
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        onNextClick()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
                    .focusRequester(focusRequester)
            )


        }
        DefaultButton(
            text = "next",
        ) {
            onNextClick()
        }
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    ShopInfoScreenContent(
        onShopDescriptionChange = {},
        onShopImageChange = {},
        onShopNameChange = {},
        shopName = "",
        shopDescription = "",
        imagePatch = null
    ) {}
}