package fr.euroforma.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class authentification extends AppCompatActivity {
    LinearLayout Verification_layout; // Recupere tous le linearLayout pour afficher
    EditText codeSaisi;
    EditText editVisiteur;
    EditText editEmail;
    Integer aleatoire;
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String CODE_VISITEUR = "CODEVISITEUR";
    private static final String EMAIL = "EMAIL";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        this.getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).edit().clear().commit(); //Ecraser l'ancien code visiteur
        Verification_layout = (findViewById(R.id.Verification_Layout));
        codeSaisi=findViewById(R.id.editVerification);
        editVisiteur=findViewById(R.id.editVisiteur);
        editEmail=findViewById(R.id.editEmail);

    }

    public void CalculAleatoire(View view) {
        Random r = new Random();
        int min = 10000;
        int max = 99999;
        aleatoire = r.nextInt((max - min) + 1) + min;
        Verification_layout.setVisibility(View.VISIBLE);
    affiche_ici(aleatoire.toString());

        }

    void affiche_ici(String msg){
        Toast.makeText(authentification.this,msg,Toast.LENGTH_SHORT).show();


    }
    public void Verification (View view){
        String s1=aleatoire.toString();
        String s2 = codeSaisi.getText().toString();
        if (s1.equals(s2)){
            affiche_ici("le code entré est validé");
            SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putString(CODE_VISITEUR,editVisiteur.getText().toString())
                    .putString(EMAIL, editEmail.getText().toString())
                    .apply();
        String CODEVISITEURENREGISTRER = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(CODE_VISITEUR, null);
        String EMAILENREGISTRER = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(EMAIL, null);
        affiche_ici("code visiteur enregistre :"+CODEVISITEURENREGISTRER.toString()+EMAILENREGISTRER.toString());

            Intent intent = new Intent(authentification.this,MainActivity.class);
            startActivity(intent);
        }
        else{
            affiche_ici("le code entré n'est PAS valide");
        }
    }

}