package sv.ues.fia.moviles.controlador.asignatura;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Asignatura;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignaturaConsultarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdAsignatura;
	EditText editIdNombre;
	EditText editIdCodigo;
	EditText editIdDescripcion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asignatura_consultar);
		
		helper = new ControlBDTarea(this);
		
		editIdAsignatura  = (EditText) findViewById(R.id.editIdAsignatura);
		editIdNombre      = (EditText) findViewById(R.id.editIdNombre);
		editIdCodigo      = (EditText) findViewById(R.id.editIdCodigo);
		editIdDescripcion = (EditText) findViewById(R.id.editIdDescripcion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asignatura_consultar, menu);
		return true;
	}
	
	public void consultarAsignatura(View v) {
		helper.abrir();
		Asignatura asignatura = helper.consultarAsignatura(editIdAsignatura.getText()
				.toString());
		helper.cerrar();
		if (asignatura == null)
			Toast.makeText(
					this,
					"Asignatura con id " + editIdAsignatura.getText().toString()
							+ " no encontrado", Toast.LENGTH_LONG).show();
		else {
			editIdAsignatura.setText(Integer.toString(asignatura.getId()));
			editIdNombre.setText(asignatura.getNombre());
			editIdCodigo.setText(asignatura.getCodigo());
			editIdDescripcion.setText(asignatura.getDescripcion());
		}
	}
	
	public void limpiarTexto(View v) {		
		editIdAsignatura.setText("");
		editIdNombre.setText("");
		editIdCodigo.setText("");
		editIdDescripcion.setText("");
	}

}
