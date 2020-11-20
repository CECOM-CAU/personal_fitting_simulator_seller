package com.example.fittingsimulator_seller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidx.appcompat.app.AppCompatActivity;

public class AddClothesActivity extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;

    private Button top_ok_btn;
    private Button qr_btn;
    private ImageView clothes_image; //옷 사진
    private EditText clothes_name_et,url_et; //옷 이름 입력값
    private String clothes_name; // 옷 이름
    private String t_shoulder, t_arm, t_chest, t_arm_width, t_total_len;
    private EditText top_shoulder,top_arm,top_chest,top_arm_width,top_total_len;

    private String result;

    private ImageView qr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼

        //갤러리에서 옷 사진 불러와 저장
        clothes_image=findViewById(R.id.image_iv);
        clothes_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        clothes_name_et=findViewById(R.id.name_et);
        clothes_name=clothes_name_et.getText().toString(); //옷 이름

        top_shoulder=(EditText)findViewById(R.id.top_shoulder_et);//상의_어깨길이
        t_shoulder=top_shoulder.getText().toString();

        top_arm=(EditText)findViewById(R.id.top_arm_et);//상의_팔길이
        t_arm=top_arm.getText().toString();

        top_chest=(EditText)findViewById(R.id.top_chest_et);//상의_가슴단면
        t_chest=top_chest.getText().toString();

        top_arm_width=(EditText)findViewById(R.id.top_arm_width_et);//상의_소매폭
        t_arm_width=top_arm_width.getText().toString();

        top_total_len=(EditText)findViewById(R.id.top_total_len_et);//상의_총길이
        t_total_len=top_total_len.getText().toString();

        result=top_shoulder.getText().toString().concat(",").concat(top_arm.getText().toString()).concat(",").concat(top_chest.getText().toString()).concat(",").concat(top_arm_width.getText().toString()).concat(",").concat(top_total_len.getText().toString());

        qr = findViewById(R.id.qrcode);
        qr_btn=findViewById(R.id.qrcode_button);
        qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothes_name_et.setText(result);

                //QR코드 생성
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(result, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qr.setImageBitmap(bitmap);
                } catch (Exception e) {
                }
            }
        });

        top_ok_btn=findViewById(R.id.top_ok_button);
        top_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddClothesActivity.this,MainActivity.class);

                //옷 추가하기 -수정!!
                GridListAdaptor adaptor=new GridListAdaptor();
                adaptor.addItem(new Clothes(clothes_name,clothes_image.getImageAlpha()));
                adaptor.notifyDataSetChanged();

                startActivity(intent);
                finish();
            }
        });

    }

    @Override //툴바 메뉴
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{//뒤로가기
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            clothes_image.setImageURI(selectedImageUri);

        }

    }
}
