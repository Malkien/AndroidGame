package servicios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import slayer.simulator.game.R;

/**
 * Clase de Dialogo que extiende de DialogFragment
 */
public class InterfazDialogo extends DialogFragment {
    /**
     * Interfaz para manejar la respuesta del usuario
     */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener

    /**
     * Sobreescribe el onAttach con la interfaz que maneja la salida del usuario
     * @param context El contexto
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Implementar InterfazDialogo");
        }
    }

    /**
     * Sobreescribe el onCreateDialog Para personalizar el dialogo
     * @param savedInstanceState El savedInstanceState
     * @return Devuelve el dialogo creado
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.seguro);
        builder.setMessage(R.string.seguro)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        listener.onDialogPositiveClick(InterfazDialogo.this);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        listener.onDialogNegativeClick(InterfazDialogo.this);
                    }
                });
        return builder.create();
    }

}
