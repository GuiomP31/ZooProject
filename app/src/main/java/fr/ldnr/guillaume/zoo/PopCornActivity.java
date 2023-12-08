package fr.ldnr.guillaume.zoo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class PopCornActivity extends Activity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(new PopCornView(this));
        Log.i("PopCornActivity","#### Créer !");
        // Lecture dans l'intent d'appel
        long t = getIntent().getLongExtra("temps",-1);
        if(t>2000){
            // Affichage d'une chaine à trou
            String s = "No popcorn for fishes";
                    Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            Intent i = new Intent(this, MapActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        return true;
    }

    class PopCornView extends View {
        public PopCornView(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap b = BitmapFactory.decodeResource(getResources(),
                    R.drawable.popcorn);
            canvas.drawBitmap(b, 0, 0, null);

        }

    }}






