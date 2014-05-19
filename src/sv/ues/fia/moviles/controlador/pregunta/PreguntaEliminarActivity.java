package sv.ues.fia.moviles.controlador.pregunta;

import sv.ues.fia.moviles.R;


import sv.ues.fia.moviles.R.layout;
import sv.ues.fia.moviles.R.menu;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PreguntaEliminarActivity extends Activity {

	EditText editId;
	ControlBDTarea controlhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregunta_eliminar);
		controlhelper=new ControlBDTarea (this);
		editId=(EditText)findViewById(R.id.editIdpregunta);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pregunta_eliminar, menu);
		return true;
	}

	public void eliminarAlumno(View v){
		String regEliminadas;
		Pregunta preg=new Pregunta();
		preg.setId(Integer.parseInt(editId.getText().toString()));
		controlhelper.abrir();
		regEliminadas=controlhelper.eliminar(preg);
		controlhelper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		}
}
