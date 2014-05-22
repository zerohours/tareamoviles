package sv.ues.fia.moviles.controlador.pregunta;

import java.util.ArrayList;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class PreguntaActualizarActivity extends Activity {

	ControlBDTarea helper;
	
	EditText editPregunta;
	Spinner sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregunta_actualizar);
		helper = new ControlBDTarea(this);
		
		editPregunta = (EditText) findViewById(R.id.editPregunta);
		sp = (Spinner) findViewById(R.id.spinnerActualizar);
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
		getMenuInflater().inflate(R.menu.pregunta_actualizar, menu);
		return true;
	}

	public void actualizarPregunta(View v) {

		Pregunta pregunta = new Pregunta();
		pregunta.setId_preg(Integer.parseInt(sp.getSelectedItem().toString()));
		pregunta.setPregunta(editPregunta.getText().toString());

		helper.abrir();
		String estado = helper.actualizar(pregunta);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
	}

	public void limpiarTexto(View v) {
		
		editPregunta.setText("");

	}

}
