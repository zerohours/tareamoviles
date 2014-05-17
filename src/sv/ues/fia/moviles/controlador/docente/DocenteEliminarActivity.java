package sv.ues.fia.moviles.controlador.docente;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Docente;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteEliminarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdDocente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_docente_eliminar);
		
		helper = new ControlBDTarea(this);
		
		editIdDocente   = (EditText) findViewById(R.id.editIdDocente);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.docente_eliminar, menu);
		return true;
	}
	
	public void eliminarDocente(View v) {
		String regEliminadas;
		Docente docente = new Docente();
		docente.setId(Integer.parseInt(editIdDocente.getText().toString()));
		helper.abrir();
		regEliminadas = helper.eliminar(docente);
		helper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
	}
	
	public void limpiarTexto(View v) {
		editIdDocente.setText("");
	}

}
