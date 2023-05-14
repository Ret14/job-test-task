package com.ntpro.mobileandroiddevtestwork.presentation.deals_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ntpro.mobileandroiddevtestwork.R
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.data.room.entities.LocalDeal
import com.ntpro.mobileandroiddevtestwork.utils.FiltrationOn
import kotlin.math.roundToInt

@Composable
fun DealsScreen(viewModel: DealsScreenViewModel = hiltViewModel()) {
    val deals = viewModel.pager.collectAsLazyPagingItems()

    Column {
        AscDescSwitch(isAscValue = viewModel.isFilterAscending.value,
            onValueChange = {
                viewModel.isFilterAscending.value = it
                viewModel.updateDealsFiltration()
            }
        )
        DropdownTextFieldListed(
            listOfItems = FiltrationOn.values().map { it.toString() },
            label = stringResource(R.string.filter_by_label),
            value = viewModel.filtrationOn.value.toString(),
            onValueChange = {
                viewModel.filtrationOn.value = FiltrationOn.valueOf(it)
                viewModel.updateDealsFiltration()
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_items_spacing)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = deals) { deal ->
                deal?.let { DealCard(it) }
            }
        }
    }
}

@Composable
private fun DealCard(deal: LocalDeal) {
    Card(
        colors = CardDefaults.cardColors(),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_items_spacing)),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.deal_card_padding))
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_items_spacing))
            ) {
                listOf("Id:", "Time:", "Instrument:", "Price:", "Amount:", "Side:").forEach {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontSize = integerResource(id = R.integer.regular_font_size).sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.small_items_spacing)
                )
            ) {
                Text(
                    text = deal.id.toString(),
                    fontSize = integerResource(id = R.integer.regular_font_size).sp,
                )
                Text(
                    text = deal.timeStamp.toString(),
                    fontSize = integerResource(id = R.integer.regular_font_size).sp,
                )
                Text(
                    text = deal.instrumentName,
                    fontSize = integerResource(id = R.integer.regular_font_size).sp,
                )
                Text(
                    text = ((deal.price * 100.0).roundToInt() / 100.0).toString(),
                    color = if (deal.side == Server.Deal.Side.BUY) Color.Green else Color.Red,
                    fontSize = integerResource(id = R.integer.regular_font_size).sp,
                )
                Text(
                    text = deal.amount.roundToInt().toString(),
                    fontSize = integerResource(id = R.integer.regular_font_size).sp,
                )
                Text(
                    text = deal.side.toString(),
                    fontSize = integerResource(id = R.integer.regular_font_size).sp,
                )
            }
        }

    }
}

@Composable
private fun AscDescSwitch(
    isAscValue: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_items_spacing)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.filter_direction_title),
            style = TextStyle(
                fontSize = integerResource(id = R.integer.regular_font_size).sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        )
        Button(
            border = BorderStroke(
                dimensionResource(id = R.dimen.filter_direction_button_border_width),
                Color.Black
            ),
            onClick = { onValueChange(!isAscValue) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isAscValue) Color.Green else Color.Transparent,
                contentColor = Color.Black
            ),
        ) {
            Text(text = "By Ascending")
        }
        Button(
            border = BorderStroke(
                dimensionResource(id = R.dimen.filter_direction_button_border_width),
                Color.Black
            ),
            onClick = { onValueChange(!isAscValue) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (!isAscValue) Color.Green else Color.Transparent,
                contentColor = Color.Black
            ),
        ) {
            Text(text = "By Descending")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownTextFieldListed(
    listOfItems: List<String>,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    val icon = if (isExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_items_spacing))
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = integerResource(id = R.integer.regular_font_size).sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    if (enabled) {
                        isExpanded = !isExpanded
                    }
                },
            enabled = false,
            trailingIcon = {
                Icon(icon, stringResource(R.string.dropdown_icon_description))
            },
            textStyle = TextStyle(
                fontSize = integerResource(id = R.integer.regular_font_size).sp,
                color = Color.Black
            )
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            listOfItems.forEach { entry ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(entry)
                        isExpanded = false
                    },
                    text = { Text(text = entry) }
                )
            }
        }
    }
}