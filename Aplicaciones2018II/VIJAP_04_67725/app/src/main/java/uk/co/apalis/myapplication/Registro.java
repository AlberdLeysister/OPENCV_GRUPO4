package uk.co.apalis.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText et1,et2,et3;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        et1= (EditText) findViewById(R.id.input_nombre);
        et2= (EditText) findViewById(R.id.input_usuario);
        et3= (EditText) findViewById(R.id.input_contrasena);




    }
    public void registrar(View view){

        DBHelper admin=new DBHelper(this,"upn",null,1);



        String nombre=et1.getText().toString();
        String usuario=et2.getText().toString();
        String contrasena=et3.getText().toString();



        admin.abrir();

        fila = admin.ConsultarUsuPas(usuario,contrasena);

        if (fila.moveToFirst()){
            String usua=fila.getString(0);

            if (usuario.equalsIgnoreCase(usua)){


                Toast.makeText(getApplicationContext(),
                        "El usuario ya existe ..." , Toast.LENGTH_SHORT).show();

                et1.setText("");
                et2.setText("");
                et3.setText("");
            }




        }
        else
        {
            admin.insertarReg(nombre,usuario,contrasena);

            Toast.makeText(getApplicationContext(),
                    "Registrando ..." , Toast.LENGTH_SHORT).show();
            Intent k = new Intent( Registro.this, MainActivity.class);
            startActivity(k);

        }

        admin.cerrar();



    }

    public void cancelar(View view){
        finish();

    }
}
