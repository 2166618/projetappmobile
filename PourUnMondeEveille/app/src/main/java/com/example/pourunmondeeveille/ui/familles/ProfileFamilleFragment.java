package com.example.pourunmondeeveille.ui.familles;

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
import com.example.pourunmondeeveille.databinding.FragmentProfileFamilleBinding;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;

import java.util.Objects;

public class ProfileFamilleFragment extends Fragment {
    private FragmentProfileFamilleBinding binding;
    //private FamilleAccueil famille;
    private String nom;
    private String statut;
    private String adresse;
    private String telephone;
    private String langue;
    private String nationalite;
    private String religion;
    private String enfantsAdoptesAvecDateAdoption;


    public FragmentProfileFamilleBinding getBinding() {
        return binding;
    }

    public void setBinding(FragmentProfileFamilleBinding binding) {
        this.binding = binding;
    }

//    public FamilleAccueil getFamille() {
//        return famille;
//    }
//
//    public void setFamille(FamilleAccueil famille) {
//        this.famille = famille;
//    }

    public ProfileFamilleFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            FamilleAccueil familleSelectionnee = (FamilleAccueil) getArguments().getSerializable("familleSelectionnee");
            setNom(familleSelectionnee.getPostulant().getNom());
            setStatut(familleSelectionnee.getStatutF().getStatutF());
            setAdresse(familleSelectionnee.getPostulant().getAdresse());
            setTelephone(familleSelectionnee.getPostulant().getTelephone());
            setLangue(familleSelectionnee.getLangue().getLibele());
            setNationalite(familleSelectionnee.getNationalite().getTitre());
            setReligion(familleSelectionnee.getReligion().getNom());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ProfileFamilleViewModel famillesViewModel =
                new ViewModelProvider(this).get(ProfileFamilleViewModel.class);

        binding = FragmentProfileFamilleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Gestion couleur statut


        TextView nomTextView = view.findViewById(R.id.nomFamille);
        TextView statutTextView = view.findViewById(R.id.statutFamille);
        TextView adresseTextView = view.findViewById(R.id.adresseFamille);
        TextView langueTextView = view.findViewById(R.id.langueFamille);
        TextView nationaliteTextView = view.findViewById(R.id.nationaliteFamille);
        TextView religionTextView = view.findViewById(R.id.religionFamille);
        TextView enfantsAdoptesAvecDateAdoptionTextView = view.findViewById(R.id.enfantAdoptesAvecDateAdoption);
        nomTextView.setText(getNom());
        String s = getStatut();
        statutTextView.setText(s);
        adresseTextView.setText(getAdresse());
        langueTextView.setText(getLangue());
        nationaliteTextView.setText(getNationalite());
        religionTextView.setText(getReligion());
        enfantsAdoptesAvecDateAdoptionTextView.setText(getEnfantsAdoptesAvecDateAdoption());

        // Gestion couleur statut
        if (s.equals("En attente d'accueil")) {
            statutTextView.setTextColor(Color.RED);
        } else if (s.equals("Non Inscrit")) {
            statutTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_blue));
        } else if (s.equals("Inscrit")) {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getEnfantsAdoptesAvecDateAdoption() {
        return enfantsAdoptesAvecDateAdoption;
    }

    public void setEnfantsAdoptesAvecDateAdoption(String enfantsAdoptesAvecDateAdoption) {
        this.enfantsAdoptesAvecDateAdoption = enfantsAdoptesAvecDateAdoption;
    }
}
