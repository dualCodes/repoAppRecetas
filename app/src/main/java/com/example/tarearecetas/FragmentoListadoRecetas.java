package com.example.tarearecetas;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FragmentoListadoRecetas extends Fragment {

    public FragmentoListadoRecetas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_listado_recetas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String dificultad = getArguments() != null ? getArguments().getString("dificultad") : null;
        String ingrediente = getArguments() != null ? getArguments().getString("ingrediente") : null;

        List<Receta> listaRecetas = parseRecetas();
        List<Receta> recetasFiltradas = filtrarRecetas(listaRecetas, dificultad, ingrediente);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecetaAdapter recetaAdapter = new RecetaAdapter(recetasFiltradas);
        recyclerView.setAdapter(recetaAdapter);

        Button btnVolver = view.findViewById(R.id.button_volver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_fragmentoListadoRecetas_to_fragmentoPrincipal);
            }
        });
    }

    private List<Receta> filtrarRecetas(List<Receta> recetas, String dificultad, String ingrediente) {
        List<Receta> recetasFiltradas = new ArrayList<>(recetas);

        if (dificultad != null && !dificultad.isEmpty()) {
            recetasFiltradas = recetasFiltradas.stream()
                    .filter(r -> r.getDificultad().equalsIgnoreCase(dificultad))
                    .collect(Collectors.toList());
        }

        if (ingrediente != null && !ingrediente.isEmpty()) {
            recetasFiltradas = recetasFiltradas.stream()
                    .filter(r -> r.getIngredientes().stream().anyMatch(i -> i.equalsIgnoreCase(ingrediente)))
                    .collect(Collectors.toList());
        }

        return recetasFiltradas;
    }

    private List<Receta> parseRecetas() {
        List<Receta> recetas = new ArrayList<>();
        XmlPullParser parser = getResources().getXml(R.xml.recetas);
        try {
            int eventType = parser.getEventType();
            Receta currentReceta = null;
            List<String> currentIngredientes = null;
            String text = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("receta".equalsIgnoreCase(tagName)) {
                            currentReceta = new Receta("", "", "", 0, new ArrayList<>());
                            currentIngredientes = new ArrayList<>();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (currentReceta != null) {
                            if ("nombre".equalsIgnoreCase(tagName)) {
                                currentReceta.setNombre(text);
                            } else if ("categoria".equalsIgnoreCase(tagName)) {
                                currentReceta.setCategoria(text);
                            } else if ("dificultad".equalsIgnoreCase(tagName)) {
                                currentReceta.setDificultad(text);
                            } else if ("tiempo".equalsIgnoreCase(tagName)) {
                                currentReceta.setTiempo(Integer.parseInt(text));
                            } else if ("ingrediente".equalsIgnoreCase(tagName)) {
                                if (currentIngredientes != null) {
                                    currentIngredientes.add(text);
                                }
                            } else if ("ingredientes".equalsIgnoreCase(tagName)) {
                                currentReceta.setIngredientes(currentIngredientes);
                            } else if ("receta".equalsIgnoreCase(tagName)) {
                                recetas.add(currentReceta);
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return recetas;
    }
}
