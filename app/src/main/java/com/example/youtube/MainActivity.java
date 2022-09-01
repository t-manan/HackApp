package com.example.youtube;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
//    private String BASE_URL = "http://10.0.2.2:3000";

    private String BASE_URL = "https://backendqrcode.herokuapp.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                handleLoginDialog();
//                checkConnection();
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignupDialog();
            }
        });
        String src = "data:image/png;base64,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";
        byte[] decodedString = Base64.decode(src.substring(src.indexOf(",") + 1), Base64.DEFAULT);
        Bitmap bitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView imageView;
        imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setImageBitmap(bitMap);

    }

    private void checkConnection() {
        Call<LoginResult> call = retrofitInterface.checkConnection();
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Toast.makeText(MainActivity.this, response.body().getName().toString()+call.toString(),Toast.LENGTH_LONG).show();
                if (response.code() == 200) {
                    Toast.makeText(MainActivity.this,
                            "Signed up successfully", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(MainActivity.this,
                            "Already registered", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleLoginDialog() {

        View view = getLayoutInflater().inflate(R.layout.login_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view).show();

        Button loginBtn = view.findViewById(R.id.login);
        final EditText emailEdit = view.findViewById(R.id.emailEdit);
        final EditText passwordEdit = view.findViewById(R.id.passwordEdit);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();

                map.put("email", emailEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.code() == 200) {

                            LoginResult result = response.body();

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                            builder1.setTitle(result.getName());
                            builder1.setMessage(result.getEmail());

                            builder1.show();

                        } else if (response.code() == 404) {
                            Toast.makeText(MainActivity.this, "Wrong Credentials",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    private void handleSignupDialog() {

        View view = getLayoutInflater().inflate(R.layout.signup_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button signupBtn = view.findViewById(R.id.signup);
        final EditText nameEdit = view.findViewById(R.id.nameEdit);
        final EditText emailEdit = view.findViewById(R.id.emailEdit);
        final EditText passwordEdit = view.findViewById(R.id.passwordEdit);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();

                map.put("name", nameEdit.getText().toString());
                map.put("email", emailEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<Void> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200) {
                            Toast.makeText(MainActivity.this,
                                    "Signed up successfully", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 400) {
                            Toast.makeText(MainActivity.this,
                                    "Already registered", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
