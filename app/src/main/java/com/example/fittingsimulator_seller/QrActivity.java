package com.example.fittingsimulator_seller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidx.appcompat.app.AppCompatActivity;

public class QrActivity extends AppCompatActivity {

    private ImageView qr;
    private String result,clothes_name;
    private int clothes_image;

    private Button ok_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        qr=findViewById(R.id.qrcode);

        Intent intent = getIntent();
        result=intent.getExtras().getString("result");

        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                //QR코드 생성
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(result, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qr.setImageBitmap(bitmap);
        } catch (Exception e) {
        }

        ok_button=findViewById(R.id.ok_btn);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QrActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}


