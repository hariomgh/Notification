package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "channelId"
    val CHANNEL_NAME = "channelName"
    val notificationId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        // for pending intent
        val inten = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("30 days of app dev notification lecture Day 27")
            .setContentText("congratulation for being here today")
            .setSmallIcon(R.drawable.ic_baseline_tag_faces_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener{
            notificationManager.notify(notificationId, notification)
        }

    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "this is my notification channel"
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}