package com.zeus.bugfix.compact.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.zeus.bugfix.R

/**
 * =======================================
 * Created by tangchunlin on 2019-05-24.
 * =======================================
 */
class ONotificationCompatTestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_notification_compact)
    }


    fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.send_notify -> sendNotification()
            else -> Log.e("kevint", "no")
        }
    }

    private fun sendNotification() {
        val name = "channel_name_1"
        val desc = "channel_name_1_des"
        val id = "channel_id_1"

        createChannel(name, id, desc)


        var builder = NotificationCompat
                .Builder(this, id)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("notification 1")
                .setContentText("notication content 1")
                .setStyle(NotificationCompat.BigTextStyle().bigText("much longer text"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }


    fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, ONotificationCompatTestActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        return pendingIntent
    }

    private fun createChannel(name: String, id: String, des: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, name, importance)
            channel.description = des
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)


        }
    }

}