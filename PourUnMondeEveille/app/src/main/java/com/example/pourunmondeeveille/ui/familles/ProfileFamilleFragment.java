package com.example.pourunmondeeveille.ui.familles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pourunmondeeveille.R;
import com.example.pourunmondeeveille.databinding.FragmentFamillesBinding;
import com.example.pourunmondeeveille.databinding.FragmentProfileFamilleBinding;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;

public class ProfileFamilleFragment extends Fragment {
    private FragmentProfileFamilleBinding binding;
    private FamilleAccueil famille;
    private String nom;

    public FragmentProfileFamilleBinding getBinding() {
        return binding;
    }

    public void setBinding(FragmentProfileFamilleBinding binding) {
        this.binding = binding;
    }

    public FamilleAccueil getFamille() {
        return famille;
    }

    public void setFamille(FamilleAccueil famille) {
        this.famille = famille;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public ProfileFamilleFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            FamilleAccueil familleSelectionnee = (FamilleAccueil) getArguments().getSerializable("familleSelectionnee");
            setFamille(familleSelectionnee);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ProfileFamilleViewModel famillesViewModel =
                new ViewModelProvider(this).get(ProfileFamilleViewModel.class);

        binding = FragmentProfileFamilleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        TextView nomDeFamilleTextView = view.findViewById(R.id.nomFamille);
        nomDeFamilleTextView.setText(getFamille().getPostulant().getNom());

        return view;
    }

}
