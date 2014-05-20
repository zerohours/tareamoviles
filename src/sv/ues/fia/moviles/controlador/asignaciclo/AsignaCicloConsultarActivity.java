package sv.ues.fia.moviles.controlador.asignaciclo;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.AsignaCiclo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignaCicloConsultarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdAsignaCiclo;
	EditText editIdAsignatura;
	EditText editIdDocente;
	EditText editIdCiclo;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asigna_ciclo_consultar);
		
		helper = new ControlBDTarea(this);
		
		editIdAsignaCiclo = (EditText) findViewById(R.id.editIdAsignaCiclo);
		editIdAsignatura  = (EditText) findViewById(R.id.editIdAsignatura);
		editIdDocente     = (EditText) findViewById(R.id.editIdDocente);
		editIdCiclo       = (EditText) findViewById(R.id.editIdCiclo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asigna_ciclo_consultar, menu);
		return true;
	}
	
	public void consultarAsignaCiclo(View v) {
		helper.abrir();
		AsignaCiclo asignaciclo = helper.consultarAsignaCiclo(editIdAsignaCiclo.getText()
				.toString());
		helper.cerrar();
		
		if (asignaciclo == null)
			Toast.makeText(
					this,
					"Asignación de Ciclo con id " + editIdAsignaCiclo.getText().toString()
							+ " no encontrado", Toast.LENGTH_LONG).show();
		else {
			editIdAsignaCiclo.setText(Integer.toString(asignaciclo.getId()));
			editIdAsignatura.setText(Integer.toString(asignaciclo.getIdAsignatura()));
			editIdDocente.setText(Integer.toString(asignaciclo.getIdDocente()));
			editIdCiclo.setText(Integer.toString(asignaciclo.getIdCiclo()));
		}
	}
	
	public void limpiarTexto(View v) {
		editIdAsignaCiclo.setText("");
		editIdAsignatura.setText("");
		editIdDocente.setText("");
		editIdCiclo.setText("");
	}

}
