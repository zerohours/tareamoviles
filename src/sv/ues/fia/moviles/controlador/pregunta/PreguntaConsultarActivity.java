package sv.ues.fia.moviles.controlador.pregunta;

import java.util.ArrayList;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PreguntaConsultarActivity extends Activity {

	ControlBDTarea helper;
	
	EditText editPregu;
	Spinner sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregunta_consultar);
		helper = new ControlBDTarea(this);
	
		editPregu = (EditText) findViewById(R.id.editPregunta);
		sp = (Spinner) findViewById(R.id.spinnerPregunta);
		ArrayList<Pregunta> pregunta = new ArrayList<Pregunta>();
		try {

			helper.consultarPreguntaAll(pregunta);

		} catch (Exception e) {
		}
		ArrayAdapter<Pregunta> adaptador = new ArrayAdapter<Pregunta>(this,
				android.R.layout.simple_list_item_1, pregunta);
		sp.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pregunta_consultar, menu);
		return true;
	}

	public void consultarPregunta(View v) {

		helper.abrir();
		Pregunta pregunta = helper.consultarPregunta(sp.getSelectedItem().toString());
		helper.cerrar();

		if (pregunta == null)
			Toast.makeText(
					this,
					"Pregunta con ID " +sp.getSelectedItem().toString()
							+ " no encontrado", Toast.LENGTH_LONG).show();
		else {
			// editIdPre.setText(pregunta.getId_preg());
			editPregu.setText(pregunta.getPregunta());

		}
	}

	public void limpiarTexto(View v) {
	
		editPregu.setText("");
	}

}
