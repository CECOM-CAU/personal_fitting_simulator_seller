package com.example.fittingsimulator_seller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class QrActivity extends AppCompatActivity {

    private ImageView qr;
    private String result,clothes_name;
    private Bitmap clothes_image;

    private Button ok_button;
    private Button save_button;

    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/FittingSimulator/QRCode/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼

        qr=findViewById(R.id.qrcode);

        Intent intent = getIntent();
        result=intent.getExtras().getString("result");
        clothes_name=intent.getExtras().getString("clothes_name");
        byte[] arr = getIntent().getByteArrayExtra("image");
        clothes_image = BitmapFactory.decodeByteArray(arr, 0, arr.length);

        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

        //QR코드 생성
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(result, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qr.setImageBitmap(bitmap);

            MainActivity.adaptor.addItem(new Clothes(false,clothes_name,clothes_image,bitmap)); //메인액티비티에 옷 추가
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


        save_button=findViewById(R.id.save_btn);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //shareImage();
                //Bitmap bit =getBitmapFromView(qr);

            }
        });

    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            bgDrawable.draw(canvas);
        }   else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{//뒤로가기
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    //큐알코드 저장하기
    public static void SaveBitmapToFileCache(Bitmap bitmap, String strFilePath, String filename) {

        File file = new File(strFilePath);
        if (!file.exists())
            file.mkdirs();

        File fileCacheItem = new File(strFilePath + filename);

        OutputStream out = null;
        try {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


