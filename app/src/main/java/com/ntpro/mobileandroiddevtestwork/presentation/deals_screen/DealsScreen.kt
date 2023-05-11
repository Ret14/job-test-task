package com.ntpro.mobileandroiddevtestwork.presentation.deals_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.data.room.entities.LocalDeal
import kotlin.math.roundToInt

@Composable
fun DealsScreen(viewModel: DealsScreenViewModel = hiltViewModel()) {
    val deals = viewModel.pager.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = deals) { deal ->
            deal?.let { DealCard(it) }
        }
    }
}

@Composable
fun DealCard(deal: LocalDeal) {
    Card(
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(text = deal.id.toString())
            Text(text = deal.timeStamp.toString())
            Text(text = deal.instrumentName)
            Text(text = ((deal.price * 100.0).roundToInt() / 100.0).toString())
            Text(text = deal.amount.roundToInt().toString())
            Text(
                text = deal.side.toString(),
                color = if (deal.side == Server.Deal.Side.BUY) Color.Green else Color.Red
            )
        }
    }
}