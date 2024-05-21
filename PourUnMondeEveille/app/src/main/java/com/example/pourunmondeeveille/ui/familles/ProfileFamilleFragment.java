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

public class ProfileFamilleFragment extends Fragment {

    private FragmentProfileFamilleBinding binding;

    private String nom;

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
            String param1 = getArguments().getString("nomDeFamille");
            setNom(param1);
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
        nomDeFamilleTextView.setText(getNom());

        return view;
    }

}
