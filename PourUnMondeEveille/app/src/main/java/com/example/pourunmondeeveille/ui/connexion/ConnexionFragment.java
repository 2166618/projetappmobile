package com.example.pourunmondeeveille.ui.connexion;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pourunmondeeveille.MainActivity;
import com.example.pourunmondeeveille.R;
import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.model.ConnexionResponse;
import com.example.pourunmondeeveille.ui.creationcompte.CreationCompteFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class ConnexionFragment extends Fragment {
    private ConnexionViewModel connexionViewModel;
    private LoginButton loginButton;
    private EditText nomUtilisateurEditText;
    private EditText motDePasseEditText;
    private CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connexionViewModel = new ViewModelProvider(this).get(ConnexionViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_connexion, container, false);

        Button btnConnexion = view.findViewById(R.id.btnConnexion);
        Button btnCreationCompte = view.findViewById(R.id.btnCreationCompte);
        nomUtilisateurEditText = view.findViewById(R.id.editTextNomUtilisateur);
        motDePasseEditText = view.findViewById(R.id.editTextPassword);

        // Initialiser Facebook SDK (si pas déjà initialisé)
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Initialiser CallbackManager
        callbackManager = CallbackManager.Factory.create();

        // Configurer le LoginButton
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setPermissions("email", "public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Connexion réussie
                // AccessToken accessToken = loginResult.getAccessToken();
                Toast.makeText(getContext(), "Connexion réussie!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                // Utiliser accessToken pour faire des requêtes ou pour récupérer des infos utilisateur
            }

            @Override
            public void onCancel() {
                // Connexion annulée
                // Réponse null signifie échec ou erreur
                Toast.makeText(getContext(), "Connexion cancellé", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // Erreur de connexion
                // Réponse null signifie échec ou erreur
                Toast.makeText(getContext(), "Erreur lors de la connexion", Toast.LENGTH_SHORT).show();
            }
        });

        // Observateur unique pour la connexion
        connexionViewModel.getConnexionResponse().observe(getViewLifecycleOwner(), new Observer<ConnexionResponse>() {
            @Override
            public void onChanged(ConnexionResponse connexionResponse) {
                if (connexionResponse == null) {
                    // Réponse null signifie échec ou erreur
                    Toast.makeText(getContext(), "Erreur lors de la connexion", Toast.LENGTH_SHORT).show();
                } else if (connexionResponse.getId() > 0) {
                    // Connexion réussie
                    Toast.makeText(getContext(), "Connexion réussie!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else {
                    // Connexion échouée
                    Toast.makeText(getContext(), "Connexion échouée, veuillez réessayer.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomUtilisateur = nomUtilisateurEditText.getText().toString();
                String motDePasse = motDePasseEditText.getText().toString();

                if (nomUtilisateur.isEmpty() || motDePasse.isEmpty()) {
                    // Vérification des champs vides
                    Toast.makeText(getContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    connexionViewModel.connexionUtilisateur(nomUtilisateur, motDePasse);
                }
            }
        });

        btnCreationCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naviguerAuFragmentCreationCompte();
            }
        });

        return view;
    }

    private void naviguerAuFragmentCreationCompte() {
        // Création d'une instance du CreationCompteFragment
        CreationCompteFragment creationCompteFragment = new CreationCompteFragment();

        // Début de la transaction de fragment
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();

        // Remplacement du fragment actuel par le creationCompteFragment
        transaction.replace(R.id.contenu_fragment, creationCompteFragment);

        // Ajout de la transaction à la pile de retour arrière (back stack)
        transaction.addToBackStack(null);

        // Validation de la transaction
        transaction.commit();

    }

}
