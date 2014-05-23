package sv.ues.fia.moviles.controlador.alumno;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Alumno;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlumnoConsultarActivity extends Activity {

	ControlBDTarea helper;
	EditText editIdAlumno;
	EditText editIdEmail;
	EditText editIdNombre;
	EditText editIdApell1;
	EditText editIdApell2;
	EditText editIdTelefono;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alumno_consultar);
		
		helper = new ControlBDTarea(this);

		editIdAlumno = (EditText) findViewById(R.id.editIdAlumno);
		editIdEmail = (EditText) findViewById(R.id.editIdEmail);
		editIdNombre = (EditText) findViewById(R.id.editIdNombreDoc);
		editIdApell1 = (EditText) findViewById(R.id.editIdApell1);
		editIdApell2 = (EditText) findViewById(R.id.editIdApell2);
		editIdTelefono = (EditText) findViewById(R.id.editIdTelefono);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alumno_consultar, menu);
		return true;
	}

	//
	public void consultarAlumno(View v) {
		helper.abrir();
		Alumno alumno = helper.consultarAlumno(editIdAlumno.getText()
				.toString());
//		int cont = helper.getCount("alumno", "id_alumno");
//		System.out.println(cont);
		helper.cerrar();
		if (alumno == null)
			Toast.makeText(
					this,
					"Docente con id " + editIdAlumno.getText().toString()
							+ " no encontrado", Toast.LENGTH_LONG).show();
		else {
			editIdEmail.setText(alumno.getEmail());
			editIdNombre.setText(alumno.getNombre());
			editIdApell1.setText(alumno.getApellido1());
			editIdApell2.setText(alumno.getApellido1());
			editIdTelefono.setText(alumno.getTelefono());
		}
	}

	public void limpiarTexto(View v) {
		editIdAlumno.setText("");
		editIdEmail.setText("");
		editIdNombre.setText("");
		editIdApell1.setText("");
		editIdApell2.setText("");
		editIdTelefono.setText("");
	}

}
