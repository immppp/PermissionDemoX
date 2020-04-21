package com.example.permissiondemox

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gqlPermissiondemoX.PermissionX
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),  View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when(p0.id) {
                R.id.callBtn -> permission()
            }
        }
    }

    fun permission() {
        PermissionX.request(this,
            Manifest.permission.CALL_PHONE) { allGranter, deniedList ->
            if (allGranter) {
                call()
            } else {
                Toast.makeText(this, "you shoulde $deniedList", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:18756901908")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}
