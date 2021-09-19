package com.example.notifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    var button_notfiy:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_notfiy = findViewById(R.id.bu_start_notification)

        button_notfiy!!.setOnClickListener {

            display_notification()

        }

    }

    val channel_id:String = "my_channel"

    @SuppressLint("WrongConstant")
    private fun display_notification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel(channel_id,"this is My notification", NotificationManager.IMPORTANCE_HIGH)
            channel.description = "my channel Description"

            val nm = getSystemService(NotificationManager :: class.java)

            nm.createNotificationChannel(channel)

        }

        val intent = Intent(this,MainActivity::class.java)

        val pending_intent = PendingIntent.getActivities(this,0, arrayOf(intent),1,null)

        val notification_builder = NotificationCompat.Builder(this ,channel_id)

        val nm = NotificationManagerCompat.from(this)

        notification_builder
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("my notificaton Title")
            .setContentText("bla blaaaaaaaaaaaaaaaaaaaaaaaaaaaaa blal blalvblblblallb")
            .setStyle(NotificationCompat.BigTextStyle().bigText("My Big Text"))
            .setContentIntent(pending_intent)
            .color = Color.RED

        nm.notify(9,notification_builder.build())

    }
}