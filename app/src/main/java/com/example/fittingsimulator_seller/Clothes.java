package com.example.fittingsimulator_seller;

public class Clothes {

    private String name;
    private int image;

    Clothes(String name, int image){
        this.name=name;
        this.image=image;
    }
    String getName(){
        return name;
    }

    int getImage(){
        return image;
    }

    void setTopName(String name){
        this.name=name;
    }

    void setTopImage(int  image){
        this.image=image;
    }
}
