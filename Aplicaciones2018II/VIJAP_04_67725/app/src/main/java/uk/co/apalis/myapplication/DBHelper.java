package uk.co.apalis.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(codigo integer primary key autoincrement,nombre text,usuario text,contrasena text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table usuarios(codigo integer primary key autoincrement,nombre text,usuario text,contrasena text)");

    }

    // metodo que permite abrir la bd
    public void abrir(){
        this.getReadableDatabase();
    }

    //metodo para cerrar bd
    public void cerrar(){
        this.close();
    }

    //METODO QUE PERMITE INSERTAR REGISTROS EN LA TABLA USUARIOS
    public void insertarReg(String nombre, String usuario, String contrasena){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("usuario",usuario );
        valores.put("contrasena", contrasena);

        db.insert("usuarios",null,valores);
    }

    //METODO QUE PERMITE VALIDAR SI EL USUARIO EXISTE
    public Cursor ConsultarUsuPas(String usu, String pas) throws SQLException {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor mcursor=null;
        mcursor=db.rawQuery("select usuario,contrasena from usuarios where usuario='"+usu+"' and contrasena='"+pas+"'",null);

        return mcursor;
    }
}
