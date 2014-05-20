package sv.ues.fia.moviles.controlador.asignaciclo;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.AsignaCiclo;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignaCicloActualizarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdAsignaCiclo;
	EditText editIdAsignatura;
	EditText editIdDocente;
	EditText editIdCiclo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asigna_ciclo_actualizar);
		
		helper = new ControlBDTarea(this);
		
		editIdAsignaCiclo = (EditText) findViewById(R.id.editIdAsignaCiclo);
		editIdAsignatura  = (EditText) findViewById(R.id.editIdAsignatura);
		editIdDocente     = (EditText) findViewById(R.id.editIdDocente);
		editIdCiclo       = (EditText) findViewById(R.id.editIdCiclo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asigna_ciclo_actualizar, menu);
		return true;
	}
	
	public void actualizarAsignaCiclo(View v) {
		
		String id = editIdAsignaCiclo.getText().toString();
		String asignatura = editIdAsignatura.getText().toString();
		String docente = editIdDocente.getText().toString();
		String ciclo = editIdCiclo.getText().toString();
		
		AsignaCiclo asignaciclo = new AsignaCiclo();
		
		asignaciclo.setId(Integer.parseInt(id));
		asignaciclo.setIdCiclo(Integer.parseInt(ciclo));
		asignaciclo.setIdAsignatura(Integer.parseInt(asignatura));
		asignaciclo.setIdDocente(Integer.parseInt(docente));
		
		helper.abrir();
		String regInsertados = helper.actualizar(asignaciclo);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
		
	}
	
	public void limpiarTexto(View v) {
		editIdAsignaCiclo.setText("");
		editIdAsignatura.setText("");
		editIdDocente.setText("");
		editIdCiclo.setText("");
	}

}
