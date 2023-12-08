package fr.ldnr.guillaume.zoo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ZooSQliteHelper extends SQLiteOpenHelper {

    public ZooSQliteHelper(@Nullable Context context) {
        // Context : activité alerte
        // Name : nom du fichier
        // Factory : null
        // Version : int 1
        super(context, "zoo.sqlite",null,1);
    }

    /**
     * Appelée si la base n'existe pas
     * @param db
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE alert(id INTEGER PRIMARY KEY," +
                "title TEXT, location TEXT, infos TEXT)");
    }

    /**
     * Appelée si la ba se existe mois version < version de ctor
     * @param db
     * @param v1 ancienne version
     * @param v2 nouvelle version
     */


    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2) { }

    public String addAlert(String title, String location, String infos){
        SQLiteDatabase db = getWritableDatabase();
        // Insertion
       db.execSQL("INSERT INTO alert(title, location, infos)VALUES(?,?,?)",
         new Object[]{title, location, infos});
       // Lecture
        Cursor c = db.rawQuery("SELECT title FROM alert WHERE location=?",
               new String[]{ location} );
        String res ="";
        while (c.moveToNext())
            res += c.getString(0)+",";
        c.close();
        db.close();
               return res;
    }

}

