package fr.euroforma.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class fraisauforfait extends AppCompatActivity {
        EditText Quantite;
        Spinner typeF;
        Button BtnAjouter;
        EditText datedepense;
        DatePickerDialog picker;
        TextView MSomme;
        String [] Valeurs;
        BDDHelper database;

    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraisauforfait);
        Quantite=findViewById(R.id.editQuantité);
        typeF=findViewById(R.id.type);
        BtnAjouter=findViewById(R.id.btnAjouter);
        datedepense=findViewById(R.id.datedeladépense);
        MSomme=findViewById(R.id.TxtSomme);
        Valeurs = getResources().getStringArray(R.array.Valeurforfait);
        //recupere les valeurs selectionner par le user
        database= new BDDHelper(this);
        database.open();

        Quantite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Integer q1 = Integer.parseInt(String.valueOf("0"+ Quantite.getText()));
                String f1 = typeF.getSelectedItem().toString();
                int posF1= typeF.getSelectedItemPosition();
                Float s1 = q1 * Float.parseFloat(Valeurs[posF1]);

                MSomme.setText(s1.toString());
            }
        });


    }
    public void retourMenu (View view) {
        Intent retourintent = new Intent(fraisauforfait.this, MainActivity.class);
        startActivity(retourintent);
    }
   public void clickbouton(View v){
        Affiche(Quantite.getText().toString() +" "+typeF.getSelectedItem().toString() );
    }

    public void Affiche(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void ShowCal(View vv)
    {
        picker = new DatePickerDialog(fraisauforfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        datedepense.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                },aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }
    if(database.insertData(typeForfait.toString(),q,mDate.toString(),m,f)){
        Affiche("Valeur ajoutée avec succès.Montant="+m);
        return;

    }

}