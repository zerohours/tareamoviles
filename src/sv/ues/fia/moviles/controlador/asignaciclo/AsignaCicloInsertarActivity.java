package sv.ues.fia.moviles.controlador.asignaciclo;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.AsignaCiclo;
import sv.ues.fia.moviles.modelo.Ciclo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignaCicloInsertarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdAsignaCiclo;
	EditText editIdAsignatura;
	EditText editIdDocente;
	EditText editIdCiclo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asigna_ciclo_insertar);
		
		helper = new ControlBDTarea(this);
		
		editIdAsignaCiclo = (EditText) findViewById(R.id.editIdAsignaCiclo);
		editIdAsignatura  = (EditText) findViewById(R.id.editIdAsignatura);
		editIdDocente     = (EditText) findViewById(R.id.editIdDocente);
		editIdCiclo       = (EditText) findViewById(R.id.editIdCiclo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asigna_ciclo_insertar, menu);
		return true;
	}
	
	public void insertarAsignaCiclo(View v) {
		String id = editIdAsignaCiclo.getText().toString();
		String idAsignatura = editIdAsignatura.getText().toString();
		String idDocente = editIdDocente.getText().toString();
		String idCiclo = editIdCiclo.getText().toString();
		
		AsignaCiclo asignaciclo = new AsignaCiclo();
		
		asignaciclo.setId(Integer.parseInt(id));
		asignaciclo.setIdCiclo(Integer.parseInt(idCiclo));
		asignaciclo.setIdAsignatura(Integer.parseInt(idAsignatura));
		asignaciclo.setIdDocente(Integer.parseInt(idDocente));

		helper.abrir();
	    String regInsertados = helper.insertar(asignaciclo);
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
