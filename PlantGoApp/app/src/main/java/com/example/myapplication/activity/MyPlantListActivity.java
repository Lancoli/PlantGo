package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.business.plant.Plant;
import com.example.myapplication.storage.DBHandler;

import java.util.ArrayList;

public class MyPlantListActivity extends AppCompatActivity {

    public static final int RESULT_ADD_PLANT = 1;
    public static final int RESULT_PLANT_DETAILS = 2;
    ArrayList<Plant> plantsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plant_list);
        refreshPlantList();
        // recherche dans le gabarit l’objet ListView (à partir de son id)
        // créé une instance de notre adaptateur (cf point 5)
        // relie l’adaptateur à la liste
    }

    public void refreshPlantList() {
        DBHandler db = new DBHandler(this);
        plantsList = db.getAllPlants();

        ListView liste = (ListView) findViewById(R.id.plants);
        PlantAdapter adapter = new PlantAdapter(plantsList);
        liste.setAdapter(adapter);

        //On click event
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int idx, long l) {
                Intent detailsIntent = new Intent(MyPlantListActivity.this, PlantDetailsActivity.class);
                Plant selectedPlant = plantsList.get(idx);
                detailsIntent.putExtra("plant_id", selectedPlant.getId());
                startActivityForResult(detailsIntent, RESULT_PLANT_DETAILS);
            }
        });
    }

    public void onClickGoBack(View view) {
        this.finish();
    }

    public void onClickAddPlant(View view) {
        Intent detailsIntent = new Intent(MyPlantListActivity.this, AddPlantActivity.class);
        startActivityForResult(detailsIntent, RESULT_ADD_PLANT);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshPlantList();
    }

    class PlantAdapter extends BaseAdapter {

        public ArrayList<Plant> plantsList;

        public PlantAdapter(ArrayList<Plant> plantsList) {
            this.plantsList = plantsList;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            // on va chercher la randonnée dans le tableau
            Plant plant = plantsList.get(position);
            View itemView = getLayoutInflater().inflate(R.layout.plant_list_item, null);

            TextView plantName = (TextView) itemView.findViewById(R.id.plant_name);
            plantName.setText(plant.getName());
            return itemView;
        }

        public Object getItem(int position) {
            return null;
        }

        public int getCount() {
            return this.plantsList.size();
        }
        public View getView(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }
    }
}
