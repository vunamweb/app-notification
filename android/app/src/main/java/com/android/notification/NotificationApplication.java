package com.android.notification;

import android.app.Application;
import android.content.Context;

public class NotificationApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationConfig.getInstance().init(this);
    }
}
