package com.example.viewmodels

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.viewmodels.ui.theme.ViewModelsTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ChangeBackgroundViewModel>()
    private val airPlaneModeReceiver = AirPlaneModeReceiver()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(airPlaneModeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        setContent {
            ViewModelsTheme {
                val viewModel2 by viewModels<ChangeBackgroundViewModel>()
                // A surface container using the 'background' color from the theme

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
                        modifier = Modifier.padding(20.dp)
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


//
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(viewModel2.backgroundColor),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {


                // TODO: Increment counter on button click
                /*
                    Text(text = viewModel2.counter.toString())
                    Button(onClick = {
                        viewModel2.incrementCount(viewModel2.counter)
                    }) {
                        Text(text = "Click me to increment the count")
                    }
                */


                // Change background color on button click
                // ChangeBackgroundOnButtonClick(viewModel2, Modifier.align(Alignment.Center))

                /** Explicit Intent Example */
                // TODO: Open second activity on button click
                // OpenSecondActivityOnButtonClick(Modifier.align(Alignment.Center))

                // TODO: Open youtube app on clicking button
                // OpenYoutubeOnButtonClick(Modifier.align(Alignment.Center))


                /** Implicit Intent */
                // On button click open a chooser that show all possibilities for sending mail
                // OpenEmailChooserOnButtonCLick(modifier = Modifier.padding(10.dp))

                // TODO: to open shared image from any app
                /* viewModel.uri?.let {
                    AsyncImage(model = viewModel.uri, contentDescription = null)
                } */
//                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
    }


    @Composable
    private fun ChangeBackgroundOnButtonClick(
        viewModel2: ChangeBackgroundViewModel,
        modifier: Modifier
    ) {
        Button(
            onClick = {
                viewModel2.changeBackgroundColor()
            }, modifier = modifier
        ) {
            Text(text = "Change Color")
        }
    }

    @Composable
    fun OpenSecondActivityOnButtonClick(modifier: Modifier) {
        Button(onClick = {
            Intent(applicationContext, SecondActivity::class.java).also {
                startActivity(it)
            }
        }, modifier = modifier) {
            Text(text = "Click to open \n2nd activity")
        }
    }

    @Composable
    fun OpenYoutubeOnButtonClick(modifier: Modifier) {
        Button(
            onClick = {
                Intent(Intent.ACTION_MAIN).also {
                    it.`package` = "com.google.android.youtube"
                    try {
                        startActivity(it)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, "Error: $e", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = modifier
        ) {
            Text(text = "Click to open\nYoutube")
        }
    }

    @Composable
    fun OpenEmailChooserOnButtonCLick(modifier: Modifier) {
        Button(onClick = {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("example112@test.com"))
                putExtra(Intent.EXTRA_SUBJECT, "This is an email subject")
                putExtra(
                    Intent.EXTRA_TEXT,
                    "This is an email body. Hope you find this on correct position"
                )
                if (intent.resolveActivity(packageManager) != null)
                    startActivity(this)
            }
        }, modifier = modifier) {
            Text(text = "Click to open chooser")
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }

        viewModel.updateUri(uri)
    }

}

