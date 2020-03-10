package slayer.simulator.game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * El fragmento jugar
 */
public class Jugar extends Fragment {
    /**
     * Sobreescribe el onCreateView
     * @param inflater El inflater
     * @param container El container
     * @param savedInstanceState El savedInstanceState
     * @return Devuelve el view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jugar, container, false);
        return view;
    }
}
