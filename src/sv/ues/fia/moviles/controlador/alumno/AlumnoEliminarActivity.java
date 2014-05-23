package sv.ues.fia.moviles.controlador.alumno;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Alumno;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlumnoEliminarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdAlumno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alumno_eliminar);
		
		helper = new ControlBDTarea(this);
		
		editIdAlumno   = (EditText) findViewById(R.id.editIdAlumno);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alumno_eliminar, menu);
		return true;
	}
	
	public void eliminarAlumno(View v) {
		String regEliminadas;
		Alumno alumno = new Alumno();
		alumno.setId(Integer.parseInt(editIdAlumno.getText().toString()));
		helper.abrir();
		regEliminadas = helper.eliminarAlumno(alumno);
		helper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
	}
	
	public void limpiarTexto(View v) {
		editIdAlumno.setText("");
	}

}
