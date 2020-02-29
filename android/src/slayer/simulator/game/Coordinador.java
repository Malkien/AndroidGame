package slayer.simulator.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class Coordinador  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        setContentView(R.layout.activity_coordenador);
    }

    public void palJuego(View view) {
        Intent intent = new Intent(this,AndroidLauncher.class);
        startActivity(intent);
    }
}
