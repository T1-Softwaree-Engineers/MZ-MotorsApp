package com.example.mz_motorsport;

import android.graphics.Bitmap;

import java.io.Serializable;

public class MyPostElement implements Serializable {
    public String id;
    public String email_user;
    public String imgCar;
    public String title;
    public String marca;
    public String modelo;
    public String año;
    public double price;
    public String ubicacion;
    public String features;
    public int condicion;
    public String descripcion;
    public int autorizada;
    public int vendida;


    public MyPostElement(String id, String email_user, String imgCar, String title, String marca, String modelo, String año, double price, String ubicacion, String features, int condicion, String descripcion, int autorizada, int vendida) {
        this.id = id;
        this.email_user = email_user;
        this.imgCar = imgCar;
        this.title = title;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.price = price;
        this.ubicacion = ubicacion;
        this.features = features;
        this.condicion = condicion;
        this.descripcion = descripcion;
        this.autorizada = autorizada;
        this.vendida = vendida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(int autorizada) {
        this.autorizada = autorizada;
    }

    public int getVendida() {
        return vendida;
    }

    public void setVendida(int vendida) {
        this.vendida = vendida;
    }
}
