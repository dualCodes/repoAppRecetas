package com.example.tarearecetas;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

public class FragmentoPrincipal extends Fragment {

    public FragmentoPrincipal() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragmento_principal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup radioGroup = view.findViewById(R.id.opciones_radio_group);
        SearchView searchView = view.findViewById(R.id.search_buscar);
        Button buscarButton = view.findViewById(R.id.enter_button);

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String dificultad = null;
                if (selectedId != -1) {
                    RadioButton selectedRadioButton = view.findViewById(selectedId);
                    String textoDificultad = selectedRadioButton.getText().toString();
                    if (textoDificultad.equalsIgnoreCase("Medio")) {
                        dificultad = "Media";
                    } else {
                        dificultad = textoDificultad;
                    }
                }

                String ingrediente = searchView.getQuery().toString();

                Bundle bundle = new Bundle();
                bundle.putString("dificultad", dificultad);
                bundle.putString("ingrediente", ingrediente);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_fragmentoPrincipal_to_fragmentoListadoRecetas, bundle);
            }
        });
    }
}
