package fr.euroforma.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class fraishorsforfait extends AppCompatActivity {
    EditText libelle;
    EditText montant;
    EditText maDate;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);
    BDDHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraishorsforfait);
        libelle=findViewById(R.id.libelle);
        montant=findViewById(R.id.montant);
        maDate=findViewById(R.id.DateDepense);
        database= new BDDHelper(this);
        database.open();
    }


    void AfficheFHF(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void clickFHF(View v) {
        AfficheFHF(libelle.getText().toString() + " " + montant.getText().toString());
        if (libelle.getText().toString().length() == 0 || maDate.getText().toString().trim().length() == 0
                || montant.getText().toString().trim().length() == 0) {
            //teste si le champ quantite est renseigné ou si le champ type n'est pas vide
            // et qu'on a selectionne l'une des 4 possibilités et si la date est renseignée
            AfficheFHF("Erreur! Champ vide");
            return;
        } else if (maDate.getText().toString().trim().length() > 10 || maDate.getText().toString().trim().length() < 8) {
            //test sur la validité du champ date
            AfficheFHF("Erreur! Date invalide");
            return;

        } else {

            String lb1 = libelle.getText().toString();
            Integer q1 = 0;
            String d1 = maDate.getText().toString();
            Float m1 = Float.parseFloat(montant.getText().toString());


            if (database.insertData(lb1, q1, d1, m1, lb1)) {
                AfficheFHF("Valeur ajoutée avec succès.Montant=" + m1);
                return;

            }
        }
    }

    public void ShowCal(View vv)
    {


        picker = new DatePickerDialog(fraishorsforfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        maDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                },aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }
}