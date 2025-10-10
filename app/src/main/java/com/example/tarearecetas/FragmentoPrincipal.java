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

        // 1. Encontrar el botón "Buscar" usando su ID del archivo XML.
        Button buscarButton = view.findViewById(R.id.enter_button);

        // 2. Establecer un OnClickListener para reaccionar al clic.
        buscarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);

                // 4. Usar la acción definida en nav_graph.xml para navegar al siguiente fragmento.
                navController.navigate(R.id.action_fragmentoPrincipal_to_fragmentoListadoRecetas);
            }
        });
    }
}
