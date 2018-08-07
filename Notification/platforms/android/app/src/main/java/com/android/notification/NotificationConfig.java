package com.android.notification;

import android.content.Context;

public class NotificationConfig {

    private static NotificationConfig mInstance;
    private Context context;
    private String projectNumber;
    private String url;

    public Context getContext() {
        return context;
    }

    public String getProjectNumber() {
        return context.getResources().getString(R.string.project_number);
    }

    public String getUrl() {
        return context.getResources().getString(R.string.url);
    }

    public static NotificationConfig getInstance()
    {
        if(mInstance == null){
            mInstance = new NotificationConfig();
        }
        return mInstance;
    }

    public void init(Context context)
    {
        this.context=context;
    }
}
