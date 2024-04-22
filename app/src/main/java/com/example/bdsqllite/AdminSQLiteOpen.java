package com.example.bdsqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpen extends SQLiteOpenHelper {
    //constructor
    public AdminSQLiteOpen(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //en este metodo se crea la BD
    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table Registro(codigo int primary key, nombre text, campus text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int oldVersion, int newVersion) {


    }
}
