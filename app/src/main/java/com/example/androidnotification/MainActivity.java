package com.example.androidnotification;

import static com.example.androidnotification.App.CHANNEL_1_ID;
import static com.example.androidnotification.App.CHANNEL_2_ID;
import static com.example.androidnotification.App.CHANNEL_3_ID;
import static com.example.androidnotification.App.CHANNEL_4_ID;
import static com.example.androidnotification.App.CHANNEL_5_ID;
import static com.example.androidnotification.App.CHANNEL_6_ID;
import static com.example.androidnotification.App.CHANNEL_7_ID;
import static com.example.androidnotification.App.CHANNEL_8_ID;
import static com.example.androidnotification.App.CHANNEL_CUSTOM_ID;
import static com.example.androidnotification.App.CHANNEL_DOWNLOAD_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText edit_Title, edit_Description;

    MediaSessionCompat mediaSession;

    //we will used in BroadcastReceiver
    static final List<Message> MESSAGES = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        edit_Title = findViewById(R.id.edit_Title);
        edit_Description = findViewById(R.id.edit_Description);

        mediaSession = new MediaSessionCompat(this, "tag");

        MESSAGES.add(new Message("Good morning", "Alex"));
        MESSAGES.add(new Message("Hi", null));// null mean the user name in this cas is "Me"
        MESSAGES.add(new Message("Hello", "Idir"));
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

    public void sendOnChannel3(View view) {
        String title = edit_Title.getText().toString();
        String description = edit_Description.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", description);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_3_ID)
                .setSmallIcon(R.drawable.ic_baseline_looks_3_24)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.RED)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Replay", actionIntent)
                .build();
        //.setVibrate()
        notificationManager.notify(3, notification);
    }

    public void sendOnChannel4(View view) {
        String title = edit_Title.getText().toString();
        String description = edit_Description.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", description);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.image1);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_4_ID)
                .setSmallIcon(R.drawable.ic_baseline_looks_4_24)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.lorem))
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Replay", actionIntent)
                .build();
        //.setVibrate()
        notificationManager.notify(4, notification);
    }

    public void sendOnChannel5(View view) {
        String title = edit_Title.getText().toString();
        String description = edit_Description.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_5_ID)
                .setSmallIcon(R.drawable.ic_baseline_looks_5_24)
                .setContentTitle(title)
                .setContentText(description)
                .setStyle(new NotificationCompat.InboxStyle()
                        .setBigContentTitle("Big content Title")
                        .setSummaryText("Summary Text")
                        .addLine("this is line 1")
                        .addLine("this is line 2")
                        .addLine("this is line 3")
                        .addLine("this is line 4")
                        .addLine("this is line 5")
                        .addLine("this is line 6")
                        .addLine("this is line 6")
                        .addLine("this is line 7"))  //you can see just 7 line
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);
    }

    public void sendOnChannel6(View view) {
        String title = edit_Title.getText().toString();
        String description = edit_Description.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.image2);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_6_ID)
                .setSmallIcon(R.drawable.ic_baseline_looks_6_24)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();
        //.setVibrate()
        notificationManager.notify(5, notification);
    }

    public void sendOnChannel7(View view) {
        //add dependency
        String title = edit_Title.getText().toString();
        String description = edit_Description.getText().toString();

        Bitmap artwork = BitmapFactory.decodeResource(getResources(), R.drawable.image1);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_7_ID)
                .setSmallIcon(R.drawable.ic_baseline_filter_7_24)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(artwork)
                .addAction(R.drawable.ic_baseline_dislike_previous_24, "Dislike", null)
                .addAction(R.drawable.ic_baseline_skip_previous_24, "Previous", null)
                .addAction(R.drawable.ic_baseline_pause_24, "Pause", null)
                .addAction(R.drawable.ic_baseline_skip_next_24, "Next", null)
                .addAction(R.drawable.ic_baseline_like_previous_24, "Like", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                        .setMediaSession(mediaSession.getSessionToken()))
                .setSubText("Sub Text")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(6, notification);
    }

    public void sendOnChannel8(View view) {
        sendOnChannel8Notification(MainActivity.this);
    }

    public static void sendOnChannel8Notification(Context context) {
        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, 0);

        RemoteInput remoteInput = new RemoteInput.Builder("key_text_replay") //cnst
                .setLabel("Your answer...") //Hint
                .build();

        Intent replayIntent;
        PendingIntent replayPendingIntent = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            replayIntent = new Intent(context, ReplayReceiver.class);
            replayPendingIntent = PendingIntent.getBroadcast(context, 0, replayIntent, 0);
        } else {
            //startChatActivity instead (PendingIntent.getActivity)
            //cancel notification with NotificationManagerCompat.cancel(id)
        }

        NotificationCompat.Action replayAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_baseline_send_24,
                "Replay",
                replayPendingIntent
        ).addRemoteInput(remoteInput).build();

        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle("Me")
                .setConversationTitle("Group Chat");

        for (Message chatMessage : MESSAGES) {
            NotificationCompat.MessagingStyle.Message notificationMessage =
                    new NotificationCompat.MessagingStyle.Message(
                            chatMessage.getText(),
                            chatMessage.getTimestamp(),
                            chatMessage.getSender()
                    );
            messagingStyle.addMessage(notificationMessage);
        }

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_8_ID)
                .setSmallIcon(R.drawable.ic_baseline_filter_8_24)
                .setStyle(messagingStyle)
                .addAction(replayAction)
                .setColor(Color.BLUE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(7, notification);
    }

    public void sendOnChannelDownload(View view) {
        final int progressMax = 100;

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_DOWNLOAD_ID)
                .setSmallIcon(R.drawable.ic_baseline_download_24)
                .setContentTitle("Download")
                .setContentText("Download in progress")
                .setColor(Color.CYAN)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setProgress(progressMax, 0, true);

        notificationManager.notify(8, notification.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                for (int progress = 0; progress <= progressMax; progress += 10) {
                    notification.setProgress(progressMax, progress, false);
                    notificationManager.notify(8, notification.build());
                    SystemClock.sleep(1000);
                }

                notification.setContentText("")
                        .setProgress(0, 0, true);
                notificationManager.notify(8, notification.build());
                SystemClock.sleep(2000);
                notificationManager.cancel(8);

                notification.setContentText("Download finished")
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                notificationManager.notify(8, notification.build());
            }
        }).start();
    }

    public void sendOnChannelCustom(View view) {
        RemoteViews collapsedView = new RemoteViews(getPackageName(), R.layout.notification_collapsed);
        RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notification_expanded);

        //here we can change the texts and images of our Custom Notification
        collapsedView.setTextViewText(R.id.text_view_collapsed_1, "Title");
        collapsedView.setTextViewText(R.id.text_view_collapsed_2, "Description");

        expandedView.setTextViewText(R.id.text_view_expand, "Your custom notification");
        expandedView.setImageViewResource(R.id.image_view_expand, R.drawable.image2);

        //add our intent when we clicked
        Intent activityIntent = new Intent(this, CustomReceiver.class);
        PendingIntent onClickIntent = PendingIntent.getBroadcast(this, 0, activityIntent, 0);

        //onClick method 1: 100%
        expandedView.setOnClickPendingIntent(R.id.image_view_expand, onClickIntent);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_CUSTOM_ID)
                .setSmallIcon(R.drawable.ic_icons8_android_os)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                //.setStyle(new NotificationCompat.DecoratedCustomViewStyle())

                //onClick method 2: 50%
                //.setContentIntent(onClickIntent)
                .build();

        notificationManager.notify(9, notification);
    }
}