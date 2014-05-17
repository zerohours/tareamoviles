package sv.ues.fia.moviles.controlador.pregunta;

import sv.ues.fia.moviles.R;

import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.app.Activity;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PreguntaConsultarActivity extends Activity {

	ControlBDTarea helper;
	EditText editId;
	EditText editPregunta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregunta_consultar);
		helper = new ControlBDTarea(this);
		editId = (EditText) findViewById(R.id.editIdpregunta);
		editPregunta = (EditText) findViewById(R.id.editPregunta);

	}

	public void consultarAlumno(View v) {
		helper.abrir();

		Pregunta pregunta = helper.consultarPregunta(editId.getText()
				.toString());
		helper.cerrar();

		if (pregunta == null)
			Toast.makeText(
					this,
					"Pregunta con ID " + editId.getText().toString()
							+ " no encontrado", Toast.LENGTH_LONG).show();
		else {
			editId.setText(pregunta.getId());
			editPregunta.setText(pregunta.getPregunta());
		
		}
	}

	public void limpiarTexto(View v) {
		editId.setText("");
		editPregunta.setText("");
	
	}

}
