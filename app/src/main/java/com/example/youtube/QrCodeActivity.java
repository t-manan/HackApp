package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class QrCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        showQrCode(source);

        findViewById(R.id.share_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Hello World");
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                }
            }
        });
    }

    private void showQrCode(String source) {
        String src = source;
        byte[] decodedString = Base64.decode(src.substring(src.indexOf(",") + 1), Base64.DEFAULT);
        Bitmap bitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView imageView;
        imageView = (ImageView) findViewById(R.id.qr_image);
        imageView.setImageBitmap(bitMap);
    }
}