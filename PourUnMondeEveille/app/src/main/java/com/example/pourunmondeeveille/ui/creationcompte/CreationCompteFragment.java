package com.example.pourunmondeeveille.ui.creationcompte;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.pourunmondeeveille.MainActivity;
import com.example.pourunmondeeveille.R;
import com.example.pourunmondeeveille.ui.connexion.ConnexionFragment;
import com.example.pourunmondeeveille.ui.connexion.ConnexionViewModel;

public class CreationCompteFragment extends Fragment {

    private CreationCompteViewModel creationCompteViewModel;
    private EditText nomUtilisateurEditText;
    private EditText courrielEditText;
    private EditText motDePasseEditText;
    private EditText confirmerMotDePasseEditText;
    private TextView errorTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        creationCompteViewModel = new ViewModelProvider(this).get(CreationCompteViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_creation_compte, container, false);

        nomUtilisateurEditText = view.findViewById(R.id.editTextNomUtilisateur);
        courrielEditText = view.findViewById(R.id.editTextCourriel);
        motDePasseEditText = view.findViewById(R.id.editTextMotDePasse);
        confirmerMotDePasseEditText = view.findViewById(R.id.editTextConfirmerLeMotDePasse);
        Button btnCreationCompte = view.findViewById(R.id.btnCreationCompte);
        Button btnConnexion = view.findViewById(R.id.btnConnexion);
        errorTextView = view.findViewById(R.id.error_text_view);

        creationCompteViewModel.getErrorMessages().observe(getViewLifecycleOwner(), this::showErrorMessage);
        creationCompteViewModel.isUserCreated().observe(getViewLifecycleOwner(), this::handleUserCreated);

        btnCreationCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomUtilisateur = nomUtilisateurEditText.getText().toString();
                String courriel = courrielEditText.getText().toString();
                String motDePasse = motDePasseEditText.getText().toString();
                String confirmPassword = confirmerMotDePasseEditText.getText().toString();
                creationCompteViewModel.createUser(nomUtilisateur, courriel, motDePasse, confirmPassword);
            }
        });

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naviguerAuFragmentConnexion();
            }
        });

        return view;
    }

    private void showErrorMessage(String errorMessage) {
        errorTextView.setText(errorMessage);
    }

    private void handleUserCreated(Boolean isCreated) {
        if (isCreated) {
            // Redirect to login screen or other appropriate action
            Toast.makeText(getContext(), "Compte bien  creé.", Toast.LENGTH_SHORT).show();
            naviguerAuFragmentConnexion();
        }
    }

    private void naviguerAuFragmentConnexion() {
        // Création d'une instance du ConnexionFragment
        ConnexionFragment connexionFragment = new ConnexionFragment();

        // Début de la transaction de fragment
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();

        // Remplacement du fragment actuel par le connexionFragment
        transaction.replace(R.id.contenu_fragment, connexionFragment);

        // Ajout de la transaction à la pile de retour arrière (back stack)
        transaction.addToBackStack(null);

        // Validation de la transaction
        transaction.commit();

    }
}
