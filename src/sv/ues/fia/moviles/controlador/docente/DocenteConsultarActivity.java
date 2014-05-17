package sv.ues.fia.moviles.controlador.docente;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Docente;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteConsultarActivity extends Activity {

	ControlBDTarea helper;
	EditText editIdDocente;
	EditText editIdEmail;
	EditText editIdNombreDoc;
	EditText editIdApell1;
	EditText editIdApell2;
	EditText editIdTelefono;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_docente_consultar);
		
		helper = new ControlBDTarea(this);

		editIdDocente = (EditText) findViewById(R.id.editIdDocente);
		editIdEmail = (EditText) findViewById(R.id.editIdEmail);
		editIdNombreDoc = (EditText) findViewById(R.id.editIdNombreDoc);
		editIdApell1 = (EditText) findViewById(R.id.editIdApell1);
		editIdApell2 = (EditText) findViewById(R.id.editIdApell2);
		editIdTelefono = (EditText) findViewById(R.id.editIdTelefono);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.docente_consultar, menu);
		return true;
	}

	//
	public void consultarDocente(View v) {
		helper.abrir();
		Docente docente = helper.consultarDocente(editIdDocente.getText()
				.toString());
		int cont = helper.getCount("docente", "id_docente");
		System.out.println(cont);
		helper.cerrar();
		if (docente == null)
			Toast.makeText(
					this,
					"Cliente con id " + editIdDocente.getText().toString()
							+ " no encontrado", Toast.LENGTH_LONG).show();
		else {
			editIdEmail.setText(docente.getEmail());
			editIdNombreDoc.setText(docente.getNombre());
			editIdApell1.setText(docente.getApellido1());
			editIdApell2.setText(docente.getApellido1());
			editIdTelefono.setText(docente.getTelefono());
		}
	}

	public void limpiarTexto(View v) {
		editIdDocente.setText("");
		editIdEmail.setText("");
		editIdNombreDoc.setText("");
		editIdApell1.setText("");
		editIdApell2.setText("");
		editIdTelefono.setText("");
	}

}
