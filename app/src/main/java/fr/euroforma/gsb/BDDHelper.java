package fr.euroforma.gsb;

import static android.content.Context.MODE_PRIVATE;
import static android.provider.Telephony.ThreadsColumns.DATE;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class BDDHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "GSB.db";
    public static final String DB_TABLE = "FRAIS";
    public static final String ID = "ID";
    public static final String TYPEFORFAIT = "TYPEFORFAIT";
    public static final String QUANTITE = "QUANTITE";
    public static final String DATEFRAIS = "DATEFRAIS";
    public static final String MONTANT = "MONTANT";
    public static final String DATESAISI = "DATESAISI";
    public static final String LIBELLE = "LIBELLE";
    public static final String CREATE = "Create table FRAIS (ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPEFORFAIT TEXT, QUANTITE INTEGER, DATEFRAIS TEXT, MONTANT REAL, LIBELLE TEXT, DATESAISI DATETIME DEFAULT CURRENT_TIMESTAMP)";
    SQLiteDatabase db;

    public BDDHelper(Context context) {
        super(context, DB_NAME, null, 1); // permet d'acceder d'acceder aux membres de la classe mère
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //Création de la BDD
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { //peux s'executer qd on fais une mise a jour de l'application

    }

    public BDDHelper open() throws SQLException {
        db = this.getWritableDatabase();
        return this;
    }

    public boolean insertData(String type, Integer quantite, String date, double montant, String libelle) {
        //on cree une variable de type sqLitedatabase pr pouvoir y acceder
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPEFORFAIT, type);
        contentValues.put(QUANTITE, quantite);
        contentValues.put(DATEFRAIS, date);
        contentValues.put(MONTANT, montant);
        contentValues.put(LIBELLE, libelle);
        //insert sert a inserer des donnees, elle insere ds notre table contentValue les contenus
        // des variables que l'utilisateur renseigne
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }

    public ArrayList<HashMap<String, String>> GetUsers() {
        db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT LIBELLE, MONTANT, DATEFRAIS FROM FRAIS ";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("LIBELLE", cursor.getString(cursor.getColumnIndex(LIBELLE)));
            user.put("MONTANT", cursor.getString(cursor.getColumnIndex(MONTANT)));
            user.put("DATEFRAIS", cursor.getString(cursor.getColumnIndex(DATEFRAIS)));
            userList.add(user);
        }
        return userList;
    }

    public Cursor ViewData() {
        db = this.getReadableDatabase();
        Cursor mycursor = null;
        if (inputText == null || inputText.length() == 0) {
            Cursor cursor = db.query(DB_TABLE, new String[]{"rowid _id", LIBELLE,
                            ID, DATEFRAIS, MONTANT, QUANTITE},
                    null, null, null, null, null);
        }else{
                mycursor = db.query(true, DB_TABLE, new String[]{LIBELLE,
                                ID, DATEFRAIS, MONTANT, QUANTITE},
                        DATEFRAIS + " like '%" + inputText + "%'", null,
                        null, null, null, null);
            }
            if (mycursor != null) {
                mycursor.moveToFirst();
            }
            return mycursor;!
        }

        public void deleteData ( int parseInt){
        }


    }

