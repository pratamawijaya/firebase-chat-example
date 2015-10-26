package com.pratamawijaya.firebasechat.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pratamawijaya.firebasechat.R;
import com.pratamawijaya.firebasechat.presenter.LoginViewPresenter;
import com.pratamawijaya.firebasechat.ui.home.HomeViewActivity;

public class LoginViewActivity extends AppCompatActivity implements LoginViewIntefaces {

  @Bind(R.id.input_email) EditText inputEmail;
  @Bind(R.id.input_password) EditText inputPassword;

  private LoginViewPresenter presenter;
  private ProgressDialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading...");

    presenter = new LoginViewPresenter(this, this);
  }

  @OnClick(R.id.btn_login) void login() {
    presenter.loginUser(inputEmail.getText().toString(), inputPassword.getText().toString());
  }

  @OnClick(R.id.btn_login_google) void loginGoogle() {

  }

  @Override public void showLoading() {
    progressDialog.show();
  }

  @Override public void hideLoading() {
    progressDialog.dismiss();
  }

  @Override public void showMessage(String msg) {
    Toast.makeText(LoginViewActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
  }

  @Override public void showHomePage() {
    Toast.makeText(LoginViewActivity.this, "show homepage", Toast.LENGTH_SHORT).show();
    startActivity(new Intent(this, HomeViewActivity.class));
    finish();
  }
}
