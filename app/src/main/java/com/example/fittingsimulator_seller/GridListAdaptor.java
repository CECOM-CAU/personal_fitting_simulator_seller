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
import android.widget.TextView;

import java.util.ArrayList;

public class GridListAdaptor extends BaseAdapter {
    final ArrayList<Clothes> c = new ArrayList<Clothes>();
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

    public long getItemId(int position){
        return position;
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


        nameText.setText(c_item.getName());
        image.setImageResource(c_item.getImage());

        checkBoxContent = (CheckBox)convertView.findViewById(R.id.checkBox);
        checkBoxContent.setClickable(false);


        return convertView;
    }
}
