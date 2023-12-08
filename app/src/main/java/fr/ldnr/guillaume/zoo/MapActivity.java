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
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MapActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MapView(this));
        Log.i("MapActivity", "#### Créer !");
        Toast.makeText(this, "Bienvenue Guillaume", Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked()==MotionEvent.ACTION_DOWN) {
            Intent i = new Intent(this, AquariumActivity.class);
            startActivity(i);
        }
        return true;
    }
    class MapView extends View {
        public MapView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap b = BitmapFactory.decodeResource(getResources(),
                    R.drawable.map);
            canvas.drawBitmap(b, 0, 0, null);

        }

    }
}