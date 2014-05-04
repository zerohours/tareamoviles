package sv.ues.fia.moviles.controlador;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteInsertarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdDocente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_docente_insertar);
		
		helper = new ControlBDTarea(this);
		editIdDocente = (EditText) findViewById(R.id.editIdDocente);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.docente_insertar, menu);
		return true;
	}
	
	public void insertarDocente(View v) {
		String regInsertados = "Docente insertado.";
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	}
	
	public void limpiarTexto(View v) {
		editIdDocente.setText("");
	}

}
