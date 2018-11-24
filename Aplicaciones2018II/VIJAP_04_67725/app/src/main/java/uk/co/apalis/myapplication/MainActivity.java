package uk.co.apalis.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView input_usuario,input_contrasena,texto_conectar,texto_olvidaste_contrasena;
    Button boton_iniciar_sesion;
    private Cursor fila;
    int contador=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_usuario = (EditText) findViewById(R.id.input_usuario);
        input_contrasena = (EditText) findViewById(R.id.input_contrasena);
        boton_iniciar_sesion = (Button) findViewById(R.id.boton_iniciar_sesion);


        texto_conectar = (TextView) findViewById(R.id.texto_conectar);
    }
    public void ingresar(View v){
        DBHelper admin=new DBHelper(this,"upn",null,1);

        String usuario=input_usuario.getText().toString();
        String contrasena=input_contrasena.getText().toString();


        admin.abrir();

        fila = admin.ConsultarUsuPas(usuario,contrasena);
        if (fila.moveToFirst()){
            String usua=fila.getString(0);
            String pass=fila.getString(1);
            if (usuario.equalsIgnoreCase(usua)&&contrasena.equalsIgnoreCase(pass)){
                Toast.makeText(getApplicationContext(),
                        "Redireccionando ..." , Toast.LENGTH_SHORT).show();
                Intent k = new Intent( MainActivity.this, Menu.class);
                startActivity(k);
                input_usuario.setText("");
                input_contrasena.setText("");
            }




        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "Datos incorrectas ...", Toast.LENGTH_SHORT).show();


            contador--;

            if(contador==0)
                boton_iniciar_sesion.setEnabled(false);

            input_usuario.setText("");
            input_contrasena.setText("");
        }

        admin.cerrar();

    }


    public void registrar(View v){
        Intent ven=new Intent(this,Registro.class);
        startActivity(ven);
    }

    public void salir(View v){
        finish();
        //System.exit(0);
    }
}
