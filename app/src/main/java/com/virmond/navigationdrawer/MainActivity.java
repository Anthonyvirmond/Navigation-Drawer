package com.virmond.navigationdrawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(

                R.id.nav_principal, R.id.nav_servico, R.id.nav_clientes,
                R.id.nav_contato, R.id.nav_sobre

        )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    public void enviarEmail(){

        String celular = "tel: 11996352964";
        String imagem = "https://media-cdn.tripadvisor.com/media/photo-s/18/11/9d/0b/pitinga.jpg";
        String endereco = "https://www.google.com/maps/place/Paranagu%C3%A1+-+PR/@-25.5498116,-48.490513,11z/data=!3m1!4b1!4m6!3m5!1s0x94db9ae5c93dc3ed:0xa7b55278e4a8ae8b!8m2!3d-25.515148!4d-48.5224133!16zL20vMDR4MjVu";
       // Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse("celular") );
       // Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse("imagem") );
        //Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse("endereco") );

        Intent intent = new Intent( Intent.ACTION_SEND );

        intent.putExtra( Intent.EXTRA_EMAIL, new String []{"atendimento@atmconsultoria.com.br"} );
        intent.putExtra( Intent.EXTRA_SUBJECT, "Contato pelo App" );
        intent.putExtra( Intent.EXTRA_TEXT, "Mensagem Automatica" );

       //  intent.setType("image/rfc822");
        intent.setType("text/plain");
        //intent.setType("image/*");
       // intent.setType("aplication/pdf");


        startActivity( intent.createChooser( intent, "Escolha um App de e-mail" ) );
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
