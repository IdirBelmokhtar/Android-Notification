package com.example.androidnotification;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ReplayReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        if (remoteInput != null) {
            CharSequence replayText = remoteInput.getCharSequence("key_text_replay");
            Message answer = new Message(replayText, null);
            MainActivity.MESSAGES.add(answer);

            MainActivity.sendOnChannel8Notification(context);
        }else {
            Toast.makeText(context, "no", Toast.LENGTH_SHORT).show();
        }
    }
}