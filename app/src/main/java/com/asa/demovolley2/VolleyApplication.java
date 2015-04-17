package com.asa.demovolley2;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by APRIJAL_PASARIBU on 16/04/2015.
 */
public class VolleyApplication extends Application {
    private static VolleyApplication sInstance;
    private RequestQueue mReaquestQueue;

    @Override
    public void onCreate(){
        super.onCreate();
        mReaquestQueue = Volley.newRequestQueue(this);
        sInstance = this;
    }

    public synchronized static VolleyApplication getInstance(){
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mReaquestQueue;
    }
}
