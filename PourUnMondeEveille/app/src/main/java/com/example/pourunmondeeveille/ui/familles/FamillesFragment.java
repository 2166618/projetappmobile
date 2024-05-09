package com.example.pourunmondeeveille.ui.familles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pourunmondeeveille.R;
import com.example.pourunmondeeveille.databinding.FragmentFamillesBinding;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;
import com.example.pourunmondeeveille.model.familles.FamilleDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FamillesFragment extends Fragment {

    private FragmentFamillesBinding binding;
    private ListView famillesList;
    private EditText barreDeRecherche;
    private List<String> nomFamilles;
    private ArrayAdapter<String> adapter;

    public List<String> getNomFamilles() {
        return nomFamilles;
    }

    public void setNomFamilles(List<String> nomFamilles) {
        this.nomFamilles = nomFamilles;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FamillesViewModel famillesViewModel =
                new ViewModelProvider(this).get(FamillesViewModel.class);

        binding = FragmentFamillesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        famillesList = view.findViewById(R.id.famillesList);
        barreDeRecherche = view.findViewById(R.id.barreDeRecherche);

        FamillesViewModel viewModel = new ViewModelProvider(this).get(FamillesViewModel.class);

        viewModel.getFamillesAccueil().observe(getViewLifecycleOwner(), new Observer<List<FamilleAccueil>>() {
            @Override
            public void onChanged(List<FamilleAccueil> familles) {
                List<String> nomsDeFamille = getNomsDesPostulants(familles);
                setNomFamilles(nomsDeFamille);
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nomsDeFamille);
                famillesList.setAdapter(adapter);  // Met à jour l'adapter lorsque les données changent
            }
        });

        viewModel.fetchFamillesAccueil();

        famillesList.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
            String familleSelectionnee = nomFamilles.get(position);
            Intent intent = new Intent(getContext(), FamilleDetailsActivity.class);
            intent.putExtra("NOM_DE_FAMILLE", familleSelectionnee);
            getContext().startActivity(intent);
        });

        barreDeRecherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> filteredList = getNomFamilles();

                if (s.length() > 0) {
                    filteredList = filteredList.stream()
                            .filter(name -> name.toLowerCase().contains(s.toString().toLowerCase()))
                            .collect(Collectors.toList());
                }

                adapter.clear();
                adapter.addAll(filteredList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public List<String> getNomsDesPostulants(List<FamilleAccueil> familles) {
        List<String> nomsDesPostulants = new ArrayList<>();

        for (FamilleAccueil famille : familles) {
            if (famille.getPostulant() != null) {  // Vérifiez si le postulant existe
                String nomPostulant = famille.getPostulant().getNom();
                nomsDesPostulants.add(nomPostulant);  // Ajoutez le nom à la liste
            }
        }

        return nomsDesPostulants;  // Retournez la liste des noms des postulants
    }

}