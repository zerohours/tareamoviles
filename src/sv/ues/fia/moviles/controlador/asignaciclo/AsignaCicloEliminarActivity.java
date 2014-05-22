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

public class AsignaCicloEliminarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdAsignaCiclo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asigna_ciclo_eliminar);
		
		helper = new ControlBDTarea(this);
		
		editIdAsignaCiclo = (EditText) findViewById(R.id.editIdAsignaCiclo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asigna_ciclo_eliminar, menu);
		return true;
	}
	
	public void eliminarAsignaCiclo(View v) {
		String regEliminadas;
		AsignaCiclo asignaciclo = new AsignaCiclo();
		asignaciclo.setId(Integer.parseInt(editIdAsignaCiclo.getText().toString()));
		System.out.println("El id es: " + asignaciclo.getId());
		helper.abrir();
		regEliminadas = helper.eliminar(asignaciclo);
		helper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
	}
	
	public void limpiarTexto(View v) {
		editIdAsignaCiclo.setText("");
	}

}
