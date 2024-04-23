package com.example.pourunmondeeveille.ui.connexionetcreationdecompte;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pourunmondeeveille.MainActivity;
import com.example.pourunmondeeveille.R;

public class CreationCompteFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_creation_compte, container, false);

        Button btnCreationCompte = view.findViewById(R.id.btnCreationCompte);
        Button btnConnexion = view.findViewById(R.id.btnConnexion);

        btnCreationCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
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
