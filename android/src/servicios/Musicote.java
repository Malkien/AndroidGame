package servicios;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import slayer.simulator.game.R;

public class Musicote extends Service {
    MediaPlayer musicPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        musicPlayer = MediaPlayer.create(this, R.raw.musica);
        musicPlayer.setLooping(true); // Set looping
        musicPlayer.setVolume(100,100);
        musicPlayer.setLooping(false);
        musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                    musicPlayer.start();
            }
        });;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Musica para que te de ambiente", Toast.LENGTH_LONG).show();
        musicPlayer.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
        Toast.makeText(this, "Ala, pues te quedaste sin musica", Toast.LENGTH_LONG).show();
    }

}
