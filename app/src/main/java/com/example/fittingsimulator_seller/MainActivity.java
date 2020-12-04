package com.example.fittingsimulator_seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button add_btn;


    static GridListAdaptor adaptor=new GridListAdaptor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼

        add_btn=findViewById(R.id.btn_add);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddClothesActivity.class);
                startActivity(intent);
            }
        });

        ListView gridView=findViewById(R.id.listview1);
        //아이템 추가
        gridView.setAdapter(adaptor);

        adaptor.notifyDataSetChanged();

        Button upload=findViewById(R.id.upload); //공유
        upload.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                /*SparseBooleanArray checkedItems = gridView.getCheckedItemPositions();
                int count = adaptor.getCount();
                for (int i = count - 1; i >= 0; i--) {
                    if (checkedItems.get(i)){
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                        sharingIntent.setType("image/png");
                        sharingIntent.putExtra(Intent.EXTRA_STREAM, adaptor.getItem(i).getQrImage());
                        adaptor.remove(i);
                        startActivity(Intent.createChooser(sharingIntent, "Share image using")); // 변경가능
                    }

                }*/

                //큐알코드 이미지 공유
                String path  = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FittingSimulator/qr";
                File f = new File(path);
                Uri screenshotUri = Uri.fromFile(f);

                Intent sharingIntent = new Intent();
                sharingIntent.setAction(Intent.ACTION_SEND);
                sharingIntent.setType("image/png");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                startActivity(Intent.createChooser(sharingIntent, "공유하기"));

            }
        });

        Button delete=findViewById(R.id.delete); //삭제
        delete.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                SparseBooleanArray checkedItems = gridView.getCheckedItemPositions();
                int count = adaptor.getCount();

                for (int i = count - 1; i >= 0; i--) {
                    if (checkedItems.get(i))
                        adaptor.remove(i);
                }

                // 모든 선택 상태 초기화.
                gridView.clearChoices();
                adaptor.notifyDataSetChanged();

            }
        }) ;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
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


}