package fr.euroforma.gsb;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import fr.euroforma.gsb.BDDHelper;
import fr.euroforma.gsb.R;

public class synthesedumois extends AppCompatActivity {
    private Cursor cursor;
    private BDDHelper db = new BDDHelper(this);
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthesedumois);
        db.open();
        displayListView();
        cursor = db.ViewData();


    }

    private void displayListView() {

        Cursor cursor = db.ViewData();

        // Les colonnes que l’on veut lier
        String[] columns = new String[]{
                BDDHelper.LIBELLE,
                BDDHelper.MONTANT,
                BDDHelper.DATEFRAIS,
                BDDHelper.QUANTITE,
                BDDHelper.ID

        };

        // Les éléments defnis dans le XML auxquels les données sont liées
        int[] to = new int[]{
                R.id.libelleForfait,
                R.id.montantForfait,
                R.id.DateForfait,
                R.id.QuantiteForfait,
                R.id.IdForfait
        };


        //On crée l'adaptateur à l'aide du curseur pointant sur les données souhaitées
        // ainsi que les informations de mise en page
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.detailsynthese,
                cursor,
                columns,
                to,
                0);

        ListView listView = findViewById(R.id.liste_frais);
        // Attribuer l’adapter au ListView
        listView.setAdapter(dataAdapter);

        //Pour que l'id du frais s'affiche quand on clique dessus, et pour supprimer le frais
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                //On obtient l'id du frais en cliquant sur le frais
                String myId = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                Toast.makeText(getApplicationContext(), myId, Toast.LENGTH_SHORT).show();
                db.deleteData(parseInt(myId));
            }
        });
        }
    public void retourMenu (View view){
        Intent retourIntent = new Intent(synthesedumois.this, MainActivity.class);
        startActivity(retourIntent);
    }
}
