package com.example.fittingsimulator_seller;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GridListAdaptor extends BaseAdapter {
    static ArrayList<Clothes> c = new ArrayList<Clothes>();
    Context context;

    public void addItem(Clothes item){
        c.add(item);
    }

    @Override
    public int getCount() {
        return c.size();
    }

    public Clothes getItem(int position){
        return c.get(position);
    }

    public long getItemId(int position){ return position; }

    public void remove(int position){
        c.remove(position);
    }

    public boolean isChecked(int position){
        return c.get(position).check;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       context=parent.getContext();
       Clothes c_item=c.get(position);
       CheckBox checkBoxContent;

       if(convertView==null){
           LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView =inflater.inflate(R.layout.activity_clothes_list,parent,false);
       }

       //그리드 화면에 추가할 내용 - 현재는 옷 이름과 사진
        TextView nameText=convertView.findViewById(R.id.nameText);
        ImageView image=convertView.findViewById(R.id.clothe_image);
        checkBoxContent=convertView.findViewById(R.id.checkBox);

        nameText.setText(c_item.getName());
        image.setImageBitmap(c_item.getImage());

        checkBoxContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean newState=!c.get(position).isChecked();
                c.get(position).check=newState;
            }
        });

        checkBoxContent.setChecked(isChecked(position));
        return convertView;
    }
}
