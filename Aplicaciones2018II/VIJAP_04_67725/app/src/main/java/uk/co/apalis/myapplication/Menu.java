package uk.co.apalis.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void ReconocimientoTexto(View v){
        Intent ven=new Intent(this,LoginActivity.class);
        startActivity(ven);
    }

    public void MedidorDistancia(View v){
        Intent ven=new Intent(this,MainActivity1.class);
        startActivity(ven);
    }
    public void Recorte(View v){
        Intent ven=new Intent(this,MainActivity.class);
        startActivity(ven);
    }

}
