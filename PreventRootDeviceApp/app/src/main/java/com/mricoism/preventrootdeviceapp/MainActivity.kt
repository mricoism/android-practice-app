package com.mricoism.preventrootdeviceapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.scottyab.rootbeer.RootBeer


//import androidx.window.area.utils.DeviceUtils

class MainActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        if (isDeviceRooted()) {
            Log.d("HWS", "DEVICE IS ROOTED")
            showAlertDialogAndExitApp("This device is rooted. You can't use this app.");
        }
        Log.d("HWS", "Continue")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun showAlertDialogAndExitApp(message: String) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle("Alert")
        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            })

        alertDialog.show()
    }

    fun isDeviceRooted():Boolean {
        val rootBeer = RootBeer(this@MainActivity)
        return rootBeer.isRooted
    }
}