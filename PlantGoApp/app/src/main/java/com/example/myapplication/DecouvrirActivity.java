package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DecouvrirActivity extends AppCompatActivity {

    private String[] data = { "Lahore", "Karachi", "Islamabad" };
    private ListView listView1;


    // création du tableau (à placer au début de RandoActivity, pas dans une fonction)
    ArrayList<Rando> randos = new ArrayList<Rando>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decouvrir);

        // pour l’instant, on créé plusieurs randonnées manuellement
        Rando rando1 = new Rando();
        rando1.nomRando = "Tour du lac Kir";

        Rando rando2 = new Rando();
        rando2.nomRando = "Combe à la serpent";

        randos.add(rando1);
        randos.add(rando2);

        // recherche dans le gabarit l’objet ListView (à partir de son id)
        ListView liste = (ListView) findViewById(R.id.randos);
        // créé une instance de notre adaptateur (cf point 5)
        RandoAdapter adaptateur = new RandoAdapter(randos);
        // relie l’adaptateur à la liste
        liste.setAdapter(adaptateur);
    }

    public void onClickRetour(View view) {
        this.finish();
    }

    class RandoAdapter extends BaseAdapter {

        public ArrayList<Rando> randos;

        public RandoAdapter(ArrayList<Rando> randos) {
            this.randos = randos;
        }

        public int getCount() {
            return this.randos.size();
        }
        public View getView(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            // on va chercher la randonnée dans le tableau
            Rando rando = randos.get(position);
            // pour récupérer les données de la rando, il suffira
            // de faire « rando.nomRando », « rando.lieuRando », etc …
            View vieww = getLayoutInflater().inflate(R.layout.rando_activite, null);

            // on recherche le champ de texte pour le nom de la rando :
            TextView nomRandoText = (TextView) vieww.findViewById(R.id.item_nom_rando);
            nomRandoText.setText(rando.nomRando);
            // faites de même pour les autres valeurs…
            return vieww;
        }

        public Object getItem(int position) {
            return null;
        }
    }
}

