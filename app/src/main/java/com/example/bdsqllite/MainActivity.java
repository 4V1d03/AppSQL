package com.example.bdsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Varables de los objetos XML
    private EditText ob_codigo, ob_nombre, ob_campus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variables de objetos XML a JAVA

        ob_codigo = (EditText) findViewById(R.id.txt_codigo);
        ob_nombre = (EditText) findViewById(R.id.txt_nombre);
        ob_campus = (EditText) findViewById(R.id.txt_campus);


    }

    //Boton de Guardar
    public void ingresar(View view){
        AdminSQLiteOpen admin = new AdminSQLiteOpen(this, "administrador", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = ob_codigo.getText().toString();
        String nombre = ob_nombre.getText().toString();
        String campus = ob_campus.getText().toString();

        if (!codigo.isEmpty() && !nombre.isEmpty() && !campus.isEmpty()){

            ContentValues registro = new ContentValues(); //registra los datos

            registro.put("codigo", codigo);//codigo del alumno
            registro.put("nombre", nombre);
            registro.put("campus", campus);

            Toast.makeText(this, "DATOS GUARDADOS",Toast.LENGTH_SHORT).show();

            BaseDeDatos.insert("Registro", null, registro);
            BaseDeDatos.close();
        }
        else{
            Toast.makeText(this, "LLENE TODOS LOS CAMPOS",Toast.LENGTH_SHORT).show();

        }
    }

    //metodo Buscar
    public  void Buscar(View view){

        AdminSQLiteOpen admin = new AdminSQLiteOpen(this, "administrador", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = ob_codigo.getText().toString(); //obtenemos  codigo ingresado en el teclado


        if(!codigo.isEmpty()){

            Cursor fila = BaseDeDatos.rawQuery("select nombre,campus from Registro where codigo ="+ codigo,null);

            if(fila.moveToFirst()){
                ob_nombre.setText(fila.getString(0));
                ob_campus.setText(fila.getString(1));
                BaseDeDatos.close();

            }else{
                Toast.makeText(this,"EL CODIGO INGRESADO NO EXISTE",Toast.LENGTH_SHORT).show();
                ob_codigo.setText(""); //Borra los editext
                ob_nombre.setText(""); //Borra los Editext
                ob_campus.setText(""); //Borra los Editext
            }

        }else{
            Toast.makeText(this,"INGRESE EL CODIGO DEL ALUMNO",Toast.LENGTH_SHORT).show();
        }
    } // fin metodo buscar


    //metodo modificar

    public void modificar (View view){

        /*
        AdminSQLiteopen admin  = new AdminSQLiteopen(this,"admnistrador",null,1); // objeto clase del adminsqlite
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase(); // base de datos modo escritura
        */

        AdminSQLiteOpen admin = new AdminSQLiteOpen(this, "administrador", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();


        String codigo = ob_codigo.getText().toString(); //obtenemos  codigo ingresado en el teclado
        String nombre = ob_nombre.getText().toString();
        String campus = ob_campus.getText().toString() ;

        if(!codigo.isEmpty() && !nombre.isEmpty() && !campus.isEmpty()){

            ContentValues registro = new ContentValues(); //contenedor de registro

            registro.put("codigo",codigo);// inserta el codigo en la tabla
            registro.put("nombre",nombre); // inserta nombre en la tabla
            registro.put("campus",campus); //inserta el campus en la tabla


            int actualizar  = BaseDeDatos.update("Registro",registro,"codigo="+codigo,null);
            BaseDeDatos.close();

            if(actualizar==1){
                Toast.makeText(this,"REGISTRO ACTUALIZADO CON EXITO",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this,"Codigo no Existe",Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this,"DEBE DE LLENAR TODOS LOS CAMPOS",Toast.LENGTH_SHORT).show();
        }
    }

    //METODO DE ELIMINAR
    public void eliminar (View view) {

        AdminSQLiteOpen admin = new AdminSQLiteOpen(this, "administrador", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = ob_codigo.getText().toString(); //obtenemos  codigo ingresado en el teclado

        if (!codigo.isEmpty()){
            int id = BaseDeDatos.delete("Registro", "Codigo=" + codigo, null);
            BaseDeDatos.close();

            ob_codigo.setText(""); //Borra los editext
            ob_nombre.setText(""); //Borra los Editext
            ob_campus.setText(""); //Borra los Editext


            if (id==1) {
                Toast.makeText(this, "ALUMNO ELIMINADO", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "ALUMNO NO EXISTE", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debe de ingresar el codigo del Alumno", Toast.LENGTH_SHORT).show();
        }

    } //Fin del metodo eliminar


} // fin class mainactivity

