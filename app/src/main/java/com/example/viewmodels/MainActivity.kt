package com.example.viewmodels

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viewmodels.ui.theme.ViewModelsTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ViewModelsTheme {
                val viewModel2 by viewModels<ChangeBackgroundViewModel>()
                val scrollState = rememberScrollState()

                // TODO: Add item to the list
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            viewModel2.addItemToList("New User")
                        }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "")
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .verticalScroll(scrollState),
                    ) {
                        viewModel2.itemList.forEach {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = it)
                                IconButton(onClick = {
                                    viewModel2.removeItemFromList(it)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete, contentDescription = ""
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
