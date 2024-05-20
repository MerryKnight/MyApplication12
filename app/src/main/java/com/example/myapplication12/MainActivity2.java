package com.example.myapplication12;

import static com.example.myapplication12.BookProvider.CONTENT_URI;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


    public class MainActivity2 extends AppCompatActivity {

        private static final String[] BOOKS = {
                "Петербург", "Мартин Иден", "1984",
                "Мастер и Маргарита"
        };
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            sendDataToContentProvider();



        }
        private void sendDataToContentProvider() {
            fillBooksTable();

            ContentValues values = new ContentValues();
            try {
                Uri insertedUri = getContentResolver().insert(CONTENT_URI, values);
                if (insertedUri != null) {
                    Toast.makeText(MainActivity2.this, "Данные отправлены через провайдер контента", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Ошибка при отправке данных", Toast.LENGTH_SHORT).show();
                }
            } catch (SecurityException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity2.this, "Ошибка безопасности при отправке данных", Toast.LENGTH_SHORT).show();
            }
        }
        private void fillBooksTable() {
            for (int i = 0; i < BOOKS.length; i++) {
                ContentValues values = new ContentValues();
                values.put("name", BOOKS[i]);
                try {
                    Uri insertedUri = getContentResolver().insert(CONTENT_URI, values);
                    if (insertedUri == null) {
                        Log.e("FillBooksTable", "Ошибка при вставке фильма \"" + BOOKS[i] + "\" в таблицу");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("FillBooksTable", "Ошибка при вставке фильма \"" + BOOKS[i] + "\" в таблицу: " + e.getMessage());
                }
            }
        }
    }

