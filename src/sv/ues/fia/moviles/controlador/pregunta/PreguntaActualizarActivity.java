package sv.ues.fia.moviles.controlador.pregunta;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PreguntaActualizarActivity extends Activity {

	ControlBDTarea helper;
	EditText editId;
	EditText editPregunta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregunta_actualizar);
		helper = new ControlBDTarea(this);
		editId = (EditText) findViewById(R.id.editIdpregunta);
		editPregunta = (EditText) findViewById(R.id.editPregunta);

	}

	public void actualizarPregunta(View v) {

		Pregunta pregunta = new Pregunta();
		pregunta.setId(Integer.parseInt(editId.getText().toString()));
		pregunta.setPregunta(editPregunta.getText().toString());

		helper.abrir();
		String estado = helper.actualizar(pregunta);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
	}

	public void limpiarTexto(View v) {
		editId.setText("");
		editPregunta.setText("");

	}

}
