package com.example.pourunmondeeveille.ui.enfants;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pourunmondeeveille.R;
import com.example.pourunmondeeveille.databinding.FragmentProfileEnfantBinding;
import com.example.pourunmondeeveille.model.enfants.Enfant;
import com.example.pourunmondeeveille.ui.familles.ProfileFamilleViewModel;

public class ProfileEnfantFragment extends Fragment {

    private FragmentProfileEnfantBinding binding;
    private String nom;
    private String statut;
    private String age;
    private String ocupation;
    private String langue;
    private String nationalite;
    private String religion;
    private String familleDAdoption;

    public FragmentProfileEnfantBinding getBinding() {
        return binding;
    }

    public void setBinding(FragmentProfileEnfantBinding binding) {
        this.binding = binding;
    }

    public ProfileEnfantFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Enfant enfantSelectionnee = (Enfant) getArguments().getSerializable("enfantSelectionnee");
            String stringFamilleAdoption = getArguments().getString("stringFamilleEtDateAdoption");
            setNom(enfantSelectionnee.getNom());
            setStatut(enfantSelectionnee.getStatutE().getStatutE());
            int age = enfantSelectionnee.getAge();
            setAge(String.valueOf(age));
            setOcupation(enfantSelectionnee.getOccupation());
            setLangue(enfantSelectionnee.getLangueE());
            setNationalite(enfantSelectionnee.getNationaliteE());
            setReligion(enfantSelectionnee.getReligionE());
            setFamilleDAdoption(stringFamilleAdoption);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ProfileFamilleViewModel famillesViewModel =
                new ViewModelProvider(this).get(ProfileFamilleViewModel.class);

        binding = FragmentProfileEnfantBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        TextView nomTextView = view.findViewById(R.id.nomEnfant);
        TextView statutTextView = view.findViewById(R.id.statutEnfant);
        TextView ageTextView = view.findViewById(R.id.ageEnfant);
        TextView occupationTextView = view.findViewById(R.id.occupationEnfant);
        TextView langueTextView = view.findViewById(R.id.langueEnfant);
        TextView nationaliteTextView = view.findViewById(R.id.nationaliteEnfant);
        TextView religionTextView = view.findViewById(R.id.religionEnfant);
        TextView familleDAdoptionTextView = view.findViewById(R.id.familleDAdoption);
        nomTextView.setText(getNom());
        String s = getStatut();
        statutTextView.setText(s);
        ageTextView.setText(getAge());
        occupationTextView.setText(getOcupation());
        langueTextView.setText(getLangue());
        nationaliteTextView.setText(getNationalite());
        religionTextView.setText(getReligion());
        familleDAdoptionTextView.setText(getFamilleDAdoption());

        // Gestion couleur statut
        if (s.equals("Non Placé")) {
            statutTextView.setTextColor(Color.RED);
        } else if (s.equals("En attente")) {
            statutTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_blue));
        } else if (s.equals("Placé")) {
            statutTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_green));
        }

        return view;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getFamilleDAdoption() {
        return familleDAdoption;
    }

    public void setFamilleDAdoption(String familleDAdoption) {
        this.familleDAdoption = familleDAdoption;
    }
}
