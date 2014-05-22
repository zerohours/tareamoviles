package sv.ues.fia.moviles.controlador.categoria;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

public class CategoriaInsertarActivity extends Activity {

	ControlBDTarea helper;
	EditText id_categoria;
	EditText nombre;
	EditText descripcion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria_insertar);
		helper = new ControlBDTarea(this);
		id_categoria = (EditText) findViewById(R.id.editIdcategoria);
		nombre = (EditText) findViewById(R.id.editNombre);
		descripcion = (EditText) findViewById(R.id.editDescripcion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categoria_insertar, menu);
		return true;
	}

	public void insertarCategoria(View v) {
		int id = Integer.parseInt(id_categoria.getText().toString());
		String nom = nombre.getText().toString();
		String des = descripcion.getText().toString();
		String regInsertados;

		Categoria cat = new Categoria();
		cat.setId_cat(id);
		cat.setNombre(nom);
		cat.setDescripcion(des);

		helper.abrir();
		regInsertados = helper.insertar(cat);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	}

	public void limpiarTexto(View v) {
		id_categoria.setText("");
		nombre.setText("");
		descripcion.setText("");
	}

}
