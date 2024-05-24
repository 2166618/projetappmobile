package com.example.pourunmondeeveille;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pourunmondeeveille.R;
import com.example.pourunmondeeveille.ui.connexion.ConnexionFragment;
import com.example.pourunmondeeveille.ui.creationcompte.CreationCompteFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PageAccueilActivity extends AppCompatActivity {

    private LinearLayout contenuAccueil;
    private FrameLayout contenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);

        // OAuth
        getKeyHash("com.example.pourunmondeeveille");

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

    private void getKeyHash(String packageName) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("KeyHash:", keyHash);
                System.out.println("KeyHash: " + keyHash);  // Print to console
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
