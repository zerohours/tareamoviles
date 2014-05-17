package sv.ues.fia.moviles.controlador.pregunta;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PreguntaInsertarActivity extends Activity {

	ControlBDTarea helper;
	EditText id_carnet;
	EditText pregunta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregunta_insertar);
		helper = new ControlBDTarea(this);
		id_carnet = (EditText) findViewById(R.id.editIdpregunta);
		pregunta = (EditText) findViewById(R.id.editPregunta);

	}

	public void insertarPregunta(View v) {
		int id = Integer.parseInt(id_carnet.getText().toString());
		String preg = pregunta.getText().toString();
		
		String regInsertados;		
		Pregunta pregun=new Pregunta();
		pregun.setId(id);
		pregun.setPregunta(preg);
		
		helper.abrir();
		regInsertados = helper.insertar(pregun);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	}

	public void limpiarTexto(View v) {
		id_carnet.setText("");
		pregunta.setText("");
		
	}

}
