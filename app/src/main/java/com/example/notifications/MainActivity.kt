package com.example.notifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.SoundEffectConstants
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    var button_notfiy:Button? = null

    var button_download:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_notfiy = findViewById(R.id.bu_start_notification)

        button_download = findViewById(R.id.bu_start_notification_download)

        button_notfiy!!.setOnClickListener {

            display_notification()

        }

        button_download!!.setOnClickListener {

            progress_notification()
        }


    }

    val channel_id:String = "001"

    @SuppressLint("WrongConstant")
    private fun display_notification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel(channel_id ,"Notification Channel" ,NotificationManager.IMPORTANCE_HIGH)

            val nm = getSystemService(NotificationManager ::class.java)

            nm.createNotificationChannel(channel)

        }

        val notification = NotificationCompat.Builder(this,channel_id)

        val nm = NotificationManagerCompat.from(this)

        val intent = Intent(this ,MainActivity::class.java)

        val Pending_intent = PendingIntent.getActivities(this ,0 , arrayOf(intent),0)

        notification.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("Notifications created with NotificationCompat.MediaStyle will have their category set to CATEGORY_TRANSPORT unless you set a different category using setCategory().")
                .setStyle(NotificationCompat.BigTextStyle())
                .setContentTitle("New Messege")
                .setContentIntent(Pending_intent)

        nm.notify(19 ,notification.build())

    }


    private fun progress_notification()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel(channel_id ,"Notification Channel" ,NotificationManager.IMPORTANCE_HIGH)

            val nm = getSystemService(NotificationManager ::class.java)

            nm.createNotificationChannel(channel)

        }

        val notification = NotificationCompat.Builder(this ,channel_id)
                .setContentTitle("Download")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("Progress Download")
                .setProgress(0 ,0 ,false)

        val nm = NotificationManagerCompat.from(this)

        Thread(object :Runnable
        {
            override fun run() {

                var j = 0

                for (i in 0..10)
                {
                    nm.notify(11 ,notification.build())
                    notification.setContentText("$j/100")
                    notification.setProgress(100 ,j ,false)
                    j += 10
                    Thread.sleep(1000)
                }

                notification.setContentText("Download Completed")
                        .setProgress(0 ,0 ,false)
                nm.notify(11 ,notification.build())
            }

        }).start()


    }
}