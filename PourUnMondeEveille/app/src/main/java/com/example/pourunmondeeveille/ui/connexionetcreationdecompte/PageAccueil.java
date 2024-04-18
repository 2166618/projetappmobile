package com.example.pourunmondeeveille.ui.connexionetcreationdecompte;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pourunmondeeveille.R;

public class PageAccueil extends AppCompatActivity {

    private LinearLayout contenuAccueil;
    private FrameLayout contenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_accueil);

        contenuAccueil = findViewById(R.id.contenu_accueil);
        contenuFragment = findViewById(R.id.contenu_fragment);

        Button btnConnexion = (Button) findViewById(R.id.btnConnexion);
        Button btnCreationCompte = (Button) findViewById(R.id.btnCreationCompte);

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contenuAccueil.setVisibility(View.GONE);
                contenuFragment.setVisibility(View.VISIBLE);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenu_fragment, new ConnexionFragment())
                        .commit();

            }
        });

        btnCreationCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contenuAccueil.setVisibility(View.GONE);
                contenuFragment.setVisibility(View.VISIBLE);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenu_fragment, new CreationCompteFragment())
                        .commit();

            }
        });

    }


}
