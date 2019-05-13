package com.example.nfctile

import android.app.Service
import android.content.Intent
import android.nfc.NfcAdapter
import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class NFCTileService : TileService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return Service.START_STICKY
    }

    override fun onClick() {
        val intent = Intent(Settings.ACTION_NFC_SETTINGS).also {
            it.addCategory(Intent.CATEGORY_DEFAULT)
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivityAndCollapse(intent)
    }

    override fun onStartListening() {
        super.onStartListening()

        qsTile?.let {
            it.state = if(NfcAdapter.getDefaultAdapter(application).isEnabled) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
            it.updateTile()
        }
    }
}
