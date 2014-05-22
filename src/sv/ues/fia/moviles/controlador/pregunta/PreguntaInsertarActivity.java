package sv.ues.fia.moviles.controlador.pregunta;

import java.util.ArrayList;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PreguntaInsertarActivity extends Activity {

	ControlBDTarea helper;
	EditText id_carnet;
	EditText pregunta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregunta_insertar);
		helper = new ControlBDTarea(this);
		id_carnet = (EditText) findViewById(R.id.editIdpregunta);
		pregunta = (EditText) findViewById(R.id.editPregunta);
		Spinner sp = (Spinner) findViewById(R.id.spinnerCategora);
		ArrayList<Categoria> categoria = new ArrayList<Categoria>();
		
		try {
			
			helper.consultarCategoriaAll(categoria);
		
		} catch (Exception e) {
			
		}
		
		ArrayAdapter<Categoria> adaptador = new ArrayAdapter<Categoria>(this,
				android.R.layout.simple_list_item_1, categoria);
		sp.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pregunta_insertar, menu);
		return true;
	}

	public void insertarPregunta(View v) {

		int id = Integer.parseInt(id_carnet.getText().toString());
		String preg = pregunta.getText().toString();

		String regInsertados;
		Pregunta pregun = new Pregunta();
		pregun.setId_preg(id);
		pregun.setPregunta(preg);

		helper.abrir();
		regInsertados = helper.insertar(pregun);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	}

	public void limpiarTexto(View v) {
		id_carnet.setText("");
		pregunta.setText("");

	}

}
