package com.example.viewmodels

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class AirPlaneModeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            val isAirplaneModeTurnedOn = Settings.Global.getInt(context?.contentResolver, Settings.Global.AIRPLANE_MODE_ON) != 0

            Toast.makeText(context, "Airplane mode is now turned on: $isAirplaneModeTurnedOn", Toast.LENGTH_SHORT).show()

        }
    }
}