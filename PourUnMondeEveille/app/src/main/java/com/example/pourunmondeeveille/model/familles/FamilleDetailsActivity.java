package com.example.pourunmondeeveille.model.familles;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pourunmondeeveille.R;

public class FamilleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familles_details);

        String nomDeFamille = getIntent().getStringExtra("NOM_DE_FAMILLE");

        TextView nomDeFamilleTextView = findViewById(R.id.nomDeFamille);
        nomDeFamilleTextView.setText(nomDeFamille);

        // Ajoutez d'autres détails ici, si nécessaire
    }

}
