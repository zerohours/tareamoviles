package sv.ues.fia.moviles.controlador.pregunta;

import java.util.ArrayList;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;
import sv.ues.fia.moviles.modelo.DetalleCategoria;
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
	EditText id_preg;
	EditText pre;
	Spinner sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregunta_insertar);
		helper = new ControlBDTarea(this);
		id_preg = (EditText) findViewById(R.id.editIdpregunta);
		pre = (EditText) findViewById(R.id.editPregunta);
		sp = (Spinner) findViewById(R.id.spinnerCategora);
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

		int id_pregunt = Integer.parseInt(id_preg.getText().toString());
		String[] valor=sp.getSelectedItem().toString().split("-");
		int id_catetg = Integer.parseInt(valor[0]);
		String preg =  pre.getText().toString();

		String regInsertados;
		String regInsertadosDetCat;
		helper.abrir();
		
		Pregunta pregun = new Pregunta();
		pregun.setId_preg(id_pregunt);
		pregun.setPregunta(preg);
		regInsertados = helper.insertar(pregun);
		
		DetalleCategoria detca = new DetalleCategoria();
		detca.setId_pregunta(id_pregunt);
		detca.setId_categoria(id_catetg);
		regInsertadosDetCat = helper.insertar(detca);
		
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		Toast.makeText(this, regInsertadosDetCat, Toast.LENGTH_SHORT).show();
	}

	public void limpiarTexto(View v) {
		id_preg.setText("");
		pre.setText("");

	}

}
