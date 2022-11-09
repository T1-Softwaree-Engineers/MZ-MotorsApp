package com.example.mz_motorsport;

public class MyPostElement {
    public String imgCar;
    public String title;
    public String price;
    public String srcAut;
    public String srcComp;


    public MyPostElement(String imgCar, String title, String price, String srcAut, String srcComp) {
        this.imgCar = imgCar;
        this.title = title;
        this.price = price;
        this.srcAut = srcAut;
        this.srcComp = srcComp;
    }

    public String getImgCar() {
        return imgCar;
    }

    public void setImgCar(String imgCar) {
        this.imgCar = imgCar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSrcAut() {
        return srcAut;
    }

    public void setSrcAut(String srcAut) {
        this.srcAut = srcAut;
    }

    public String getSrcComp() {
        return srcComp;
    }

    public void setSrcComp(String srcComp) {
        this.srcComp = srcComp;
    }
}
