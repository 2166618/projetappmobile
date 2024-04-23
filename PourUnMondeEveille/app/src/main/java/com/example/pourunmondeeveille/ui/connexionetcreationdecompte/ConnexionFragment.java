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

public class ConnexionFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_connexion, container, false);

        Button btnConnexion = view.findViewById(R.id.btnConnexion);
        Button btnCreationCompte = view.findViewById(R.id.btnCreationCompte);

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
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
