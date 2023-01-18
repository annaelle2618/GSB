package fr.euroforma.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        securite();
    }
    public void clickMenu(View view) {
        Class mc = null;
        switch (view.getId()) {
            case R.id.btnFraisauforfait:
                AfficheMenu("notes forfait");
                mc= fraisauforfait.class;
                break;
            case R.id.btnFraishorsforfait:
                AfficheMenu("note frais hors forfait");
                mc= fraishorsforfait.class;
                break;
            case R.id.btnSynthesedumois:
                AfficheMenu("Synthese de mes frais");
                mc= synthesedumois.class;
                break;
            case R.id.btnParametre:
                AfficheMenu("mes informations");
                mc= parametre.class;
                break;
            case R.id.btnHistoriquedesenvois:
                AfficheMenu("historique");
                mc= historiquedesenvois.class;
                break;
            case R.id.btnAuthentification:
                 AfficheMenu("Authentification");
                 mc= authentification.class;
                 break;
        }
        Intent menu_Intent = new Intent(MainActivity.this, mc);
        startActivity(menu_Intent);

    }
    public void AfficheMenu (String msg){
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
    public void securite (){
        String Codevisiteur = getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString("CODEVISITEUR", "zero");
        AfficheMenu(Codevisiteur);
        if (Codevisiteur.equals("zero")){
            Intent intent = new Intent(MainActivity.this,authentification.class);
            startActivity(intent);
        }
     }

}