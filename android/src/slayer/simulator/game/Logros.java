package slayer.simulator.game;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import Clases.Logro;
import basedatos.BaseDeDatosAndroid;

/**
 * Fragmento Logros
 */
public class Logros extends Fragment {
    private ListView lista;// EL listView
    private BaseDeDatosAndroid base; // La bbdd
    private Context context;//El contexto

    /**
     * El constructor del fragmento
     * @param context El contexto
     * @param base La base de datos
     */
    public Logros(Context context, BaseDeDatosAndroid base){ this.base = base;this.context = context;}

    /**
     * Sobreescrite el oncreateView
     * @param inflater El inflater
     * @param container El container
     * @param savedInstanceState El savedInstanceState
     * @return Devuelve el view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logors, container, false);
        lista = (ListView) view.findViewById(R.id.lista);
        ArrayList<Logro> logros = base.cargarLogros();

        ArrayAdapter<Logro> adapter = new ArrayAdapter<Logro>(context, android.R.layout.simple_list_item_1, logros){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setText(getItem(position).getNombre());
                if(!getItem(position).getCompletado()){
                    tv.setBackgroundColor(Color.GREEN);
                }else{
                    tv.setBackgroundColor(Color.RED);
                }
                // Generate ListView Item using TextView
                return view;
            }
        };
        lista.setAdapter(adapter);
        return view;

    }
}
