package com.example.myapplication12;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class User {

    public String name;
    public int age;
    public String email;
    public User()
    {
        this.name = "";
        this.age = 0;
        this.email = "";
    }

}
