package sv.ues.fia.moviles.controlador.asignatura;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Asignatura;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AsignaturaEliminarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdAsignatura;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asignatura_eliminar);
		
		helper = new ControlBDTarea(this);
		
		editIdAsignatura  = (EditText) findViewById(R.id.editIdAsignatura);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asignatura_eliminar, menu);
		return true;
	}
	
	public void eliminarAsignatura(View v) {
		String regEliminadas;
		Asignatura asignatura = new Asignatura();
		asignatura.setId(Integer.parseInt(editIdAsignatura.getText().toString()));
		helper.abrir();
		regEliminadas = helper.eliminar(asignatura);
		helper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
	}
	
	public void limpiarTexto(View v) {		
		editIdAsignatura.setText("");
	}


}
