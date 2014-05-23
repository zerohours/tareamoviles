package sv.ues.fia.moviles.controlador.asignapreg;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.AsignaPregunta;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignapregInsertarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdAsignapreg;
	EditText editIdPregunta;
	EditText editIdCuestionario;
	EditText editPorcentaje;
	EditText editRespuesta;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asignapreg_insertar);
		
		helper = new ControlBDTarea(this);
		
		editIdAsignapreg   = (EditText) findViewById(R.id.editIdAsignapreg);
		editIdPregunta    = (EditText) findViewById(R.id.editIdPregunta);
		editIdCuestionario  = (EditText) findViewById(R.id.editIdCuestionario);
		editPorcentaje  = (EditText) findViewById(R.id.editPorcentaje);
		editRespuesta  = (EditText) findViewById(R.id.editRespuesta);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asignapreg_insertar, menu);
		return true;
	}
	
	public void insertarAsignaPreg(View v) {
		String id = editIdAsignapreg.getText().toString();
		String idPregunta = editIdPregunta.getText().toString();
		String idCuestionario = editIdCuestionario.getText().toString();
		String porcentaje = editPorcentaje.getText().toString();
		String respuesta = editRespuesta.getText().toString();
		
		String regInsertados;
		
		AsignaPregunta asigpreg = new AsignaPregunta();
		
		asigpreg.setId(Integer.parseInt(id));
		asigpreg.setIdPregunta(Integer.parseInt(idPregunta));
		asigpreg.setIdCuestionario(Integer.parseInt(idCuestionario));
		asigpreg.setPorcentaje(Integer.parseInt(porcentaje));
		asigpreg.setRespuesta(respuesta);

		helper.abrir();
		regInsertados = helper.insertar(asigpreg);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
	}
	
	public void limpiarTexto(View v) {
		
		editIdAsignapreg.setText("");
		editIdPregunta.setText("");
		editIdCuestionario.setText("");
		editPorcentaje.setText("");
		editRespuesta.setText("");
	}

}
