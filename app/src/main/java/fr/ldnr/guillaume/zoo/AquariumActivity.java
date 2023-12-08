package fr.ldnr.guillaume.zoo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Canvas;



public class AquariumActivity extends Activity {
    private long debut;
  @Override
   protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(new AquariumView(this));
        Log.i("AquariumActivity","#### Créer !");
        debut = System.currentTimeMillis();
  }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            Intent i = new Intent(this, PopCornActivity.class);
            // Evoi du temps vers l'activité de popcorn
            long fin = System.currentTimeMillis();
            i.putExtra("temps",fin-debut);
            startActivity(i);
        }
        return true;
    }

    class AquariumView extends View {
        public AquariumView(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap b = BitmapFactory.decodeResource(getResources(),
                    R.drawable.aqua);
            canvas.drawBitmap(b, 0, 0, null);

        }

    }}
