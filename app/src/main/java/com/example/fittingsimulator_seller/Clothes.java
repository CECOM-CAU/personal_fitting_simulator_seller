package com.example.fittingsimulator_seller;

import android.graphics.Bitmap;
import android.net.Uri;

public class Clothes {

    private String name;
    private Bitmap image;
    private Bitmap qr;
    boolean check;

    Clothes(Boolean check, String name, Bitmap image, Bitmap qr){
        this.check=check;
        this.name=name;
        this.image=image;
        this.qr=qr;
    }

    boolean getCheck(){return check;}
    String getName(){
        return name;
    }

    Bitmap getImage(){
        return image;
    }

    Bitmap getQrImage(){return qr;}

    public boolean isChecked(){
        return check;
    }

}
