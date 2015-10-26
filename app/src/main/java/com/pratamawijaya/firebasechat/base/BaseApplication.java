package com.pratamawijaya.firebasechat.base;

import android.app.Application;
import com.firebase.client.Firebase;
import com.pratamawijaya.firebasechat.BuildConfig;
import timber.log.Timber;

/**
 * Created by : pratama - set.mnemonix@gmail.com
 * Date : 10/26/15
 * Project : FirebaseChat
 */
public class BaseApplication extends Application {

  // TODO : create Config.java and put FIREBASE_URL in there
  private static final String FIREBASE_URL = Config.FIREBASE_URL;
  private static Firebase firebase;

  public static Firebase getFirebase() {
    if (firebase == null) {
      firebase = new Firebase(FIREBASE_URL);
    }
    return firebase;
  }

  @Override public void onCreate() {
    super.onCreate();
    Firebase.setAndroidContext(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
