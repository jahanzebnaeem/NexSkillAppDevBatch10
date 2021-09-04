package com.example.android.androidnodedemo;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.androidnodedemo.Retrofit.IMyService;
import com.example.android.androidnodedemo.Retrofit.RetrofitClient;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    /*
    1. Login
        API connect
        Send data to API for login
    2. Register User
        API connect
        Save registerd user
    3. Need to set permission for internet usage
     */

    TextView txtCreateAccount;
    MaterialEditText edtLoginEmail, edtLoginPassword;
    Button btnLogin;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init service
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);

        // Init view
        edtLoginEmail = (MaterialEditText) findViewById(R.id.edtEmail);
        edtLoginPassword = (MaterialEditText) findViewById(R.id.edtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loginUser(edtLoginEmail.getText().toString(),
                        edtLoginPassword.getText().toString());
            }
        });

        txtCreateAccount = (TextView) findViewById(R.id.txtCreateAccount);
        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View register_layout = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.register_layout, null);

//                new MaterialStyledDialog.Builder
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_user)
                        .setTitle("REGISTRATION");

                MaterialEditText edtEmail = (MaterialEditText) register_layout.findViewById(R.id.edtEmail);
                MaterialEditText edtName = (MaterialEditText) register_layout.findViewById(R.id.edtName);
                MaterialEditText edtPassword = (MaterialEditText) register_layout.findViewById(R.id.edtPassword);
                Button btn_cancel = (Button) register_layout.findViewById(R.id.btn_cancel);
                Button btn_save = (Button) register_layout.findViewById(R.id.btn_save);

                alert.setView(register_layout);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                            Toast.makeText(MainActivity.this,"Email cannot be null or empty", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(edtName.getText().toString())) {
                            Toast.makeText(MainActivity.this,"Email cannot be null or empty", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Password cannot be null or empty", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        registerUser(edtEmail.getText().toString(),
                                edtName.getText().toString(),
                                edtPassword.getText().toString());

                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    private void registerUser(String email, String name, String password) {
        compositeDisposable.add(iMyService.registerUser(email, name, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void loginUser(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this,"Email cannot be null or empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be null or empty", Toast.LENGTH_SHORT).show();
            return;
        }

        compositeDisposable.add(iMyService.loginUser(email, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}

