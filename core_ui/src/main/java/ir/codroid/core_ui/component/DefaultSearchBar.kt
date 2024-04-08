package ir.codroid.core_ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.codroid.core_ui.LocalSpacing
import kotlinx.coroutines.delay

@ExperimentalMaterial3Api
@Composable
fun DefaultSearchBar(
    searchQuery: String,
    isSearching: Boolean,
    placeholder: String,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    content: @Composable () -> Unit

) {
    val spacing = LocalSpacing.current
    var vector by remember {
        mutableStateOf(
            if (isSearching) Icons.Outlined.Close
            else Icons.Outlined.Search,
        )
    }

    var alpha = 1f

    val animatedAlpha by animateFloatAsState(
        targetValue = alpha, tween(300), label = "animatedAlpha"
    )


    val padding: Dp by animateDpAsState(
        if (isSearching) 0.dp
        else spacing.spaceMedium, tween(
            300,
            easing = FastOutLinearInEasing
        ), label = "padding"
    )


    val rotate by animateFloatAsState(
        targetValue = if (isSearching) 360f
        else 0f, animationSpec = tween(300),
        finishedListener = {
            vector = if (isSearching) Icons.Outlined.Close
            else Icons.Outlined.Search
        }, label = "rotate"
    )


    LaunchedEffect(isSearching) {
        alpha = 0f
        delay(300)
        alpha = 1f
    }


    SearchBar(
        query = searchQuery,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = isSearching,
        onActiveChange = onActiveChange,
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            Icon(
                imageVector = vector,
                contentDescription = placeholder,
                modifier = Modifier
                    .clickable {

                        onActiveChange(!isSearching)
                    }
                    .rotate(rotate)
                    .alpha(animatedAlpha)
            )

        },
        modifier = modifier
            .animateContentSize()
            .padding(horizontal = padding)
    ) {
        content()
    }
}