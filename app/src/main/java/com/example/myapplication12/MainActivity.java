package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        EditText editText = findViewById(R.id.editTextText);
        EditText editText2 = findViewById(R.id.editTextText2);
        EditText editText3 = findViewById(R.id.editTextText3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String json = createJsonUsingGson(editText.getText().toString(),
                        Integer.parseInt(editText2.getText().toString()),
                        editText3.getText().toString());
                Log.d("RRR", json);
                saveJsonToFile(json);

            }
        });
        String arr = "[{\"name\":\"John\", " +
                "\"age\":30," +
                "\"email\":\"john@gmail.com\"}," +

                "{\"name\":\"Alice\", " +
                "\"age\":25," +
                "\"email\":\"alice@mail.com\"}]";
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseJsonUsingGson(readJsonFromFile());
                System.out.println("");
                parseJsonArrayUsingGson(arr);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //     Uri insertedUri = getContentResolver().insert(CONTENT_URI, cv);//
               Intent intent = new Intent(MainActivity.this, MainActivity2.class);
               startActivity(intent);
            }
        });

    }
    private String readJsonFromFile() {
        StringBuilder jsonData = new StringBuilder();
        try (FileInputStream fis = openFileInput("data.json");
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonData.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData.toString();
    }
    private void saveJsonToFile(String json) {
        try (FileOutputStream fos = openFileOutput("data.json", Context.MODE_PRIVATE)) {
            fos.write(json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void parseJsonUsingGson(String str) {
        String jsonStr = str;
        Gson gson = new Gson();
        User user = gson.fromJson(jsonStr, User.class);

        System.out.println("Name: " + user.name);
        System.out.println("Age: " + user.age);
        System.out.println("Email: " + user.email);
    }
    public String createJsonUsingGson(String name, int age, String email) {
        User user = new User();
        user.name = name;
        user.age = age;
        user.email = email;

        Gson gson = new Gson();
        return gson.toJson(user);
    }
    public void parseJsonArrayUsingGson(String arr) {

        String jsonArrayStr = arr;

        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>(){}.getType();
        List<User> users = gson.fromJson(jsonArrayStr, userListType);

        for (User user : users) {
            System.out.println("Name: " + user.name);
            System.out.println("Age: " + user.age);
            System.out.println("Email: " + user.email);
        }
    }

}