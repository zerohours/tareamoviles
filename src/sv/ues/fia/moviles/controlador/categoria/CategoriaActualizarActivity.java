package sv.ues.fia.moviles.controlador.categoria;

import java.util.ArrayList;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CategoriaActualizarActivity extends Activity {

	ControlBDTarea helper;

	EditText editNombre;
	EditText editDescripcion;
	Spinner sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria_actualizar);
		helper = new ControlBDTarea(this);
		sp = (Spinner) findViewById(R.id.spinnerCategoria);
		editNombre = (EditText) findViewById(R.id.editnombre);
		editDescripcion = (EditText) findViewById(R.id.editdescripcion);
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
		getMenuInflater().inflate(R.menu.categoria_actualizar, menu);
		return true;
	}

	public void actualizarCategoria(View v) {

		int id = Integer.parseInt(sp.getSelectedItem().toString());
		String nom = editNombre.getText().toString();
		String des = editDescripcion.getText().toString();

		Categoria categ = new Categoria();
		categ.setId_cat(id);
		categ.setNombre(nom);
		categ.setDescripcion(des);

		helper.abrir();
		String estado = helper.actualizar(categ);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
	}

	public void limpiarTexto(View v) {
		editNombre.setText("");
		editDescripcion.setText("");
	}

}
