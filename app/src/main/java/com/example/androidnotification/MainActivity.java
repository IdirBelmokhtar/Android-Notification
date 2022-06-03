package com.example.androidnotification;

import static com.example.androidnotification.App.CHANNEL_1_ID;
import static com.example.androidnotification.App.CHANNEL_2_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText edit_Title,edit_Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        edit_Title = findViewById(R.id.edit_Title);
        edit_Description = findViewById(R.id.edit_Description);
    }

    public void sendOnChannel1(View view) {
        String title = edit_Title.getText().toString();
        String description = edit_Description.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_baseline_looks_one_24)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        //.setVibrate()
        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2(View view) {
        String title = edit_Title.getText().toString();
        String description = edit_Description.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_baseline_looks_two_24)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);
    }
}