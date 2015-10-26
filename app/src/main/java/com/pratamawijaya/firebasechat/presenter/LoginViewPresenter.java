package com.pratamawijaya.firebasechat.presenter;

import android.content.Context;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.pratamawijaya.firebasechat.base.BaseApplication;
import com.pratamawijaya.firebasechat.ui.login.LoginViewIntefaces;
import timber.log.Timber;

/**
 * Created by : pratama - set.mnemonix@gmail.com
 * Date : 10/26/15
 * Project : FirebaseChat
 */
public class LoginViewPresenter {
  private Context context;
  private LoginViewIntefaces view;
  private Firebase firebase;

  public LoginViewPresenter(Context context, LoginViewIntefaces view) {
    this.context = context;
    this.view = view;
    firebase = BaseApplication.getFirebase();
  }

  /**
   * login user to firebase
   *
   * @param email email user
   * @param password password user
   */
  public void loginUser(final String email, final String password) {
    view.showLoading();
    firebase.createUser(email, password, new Firebase.ResultHandler() {
      // if new user
      @Override public void onSuccess() {
        view.hideLoading();
        Timber.i("success register");
        authUser(email, password);
      }

      // user has been registered or something error
      @Override public void onError(FirebaseError firebaseError) {
        Timber.i("user has been registered");
        view.hideLoading();
        authUser(email, password);
      }
    });
  }

  /**
   * authenticate user
   *
   * @param email email user
   * @param password password user
   */
  private void authUser(String email, String password) {
    firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
      @Override public void onAuthenticated(AuthData authData) {
        Timber.i("auth success");
        Timber.i("auth data uid: " + authData.getUid());
        Timber.i("auth data provider: " + authData.getProvider());
        Timber.i("auth data token: " + authData.getToken());
        view.showHomePage();
      }

      @Override public void onAuthenticationError(FirebaseError firebaseError) {
        Timber.i("auth error");
        view.showMessage("error : " + firebaseError.getMessage());
      }
    });
  }
}
