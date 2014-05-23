package sv.ues.fia.moviles.controlador.categoria;

import java.util.ArrayList;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CategoriaConsultarActivity extends Activity {

	ControlBDTarea helper;

	EditText editnombre;
	EditText editDescripcion;
	Spinner sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria_consultar);
		helper = new ControlBDTarea(this);

		sp = (Spinner) findViewById(R.id.spinnerConsultar);
		editnombre = (EditText) findViewById(R.id.editNombre);
		editDescripcion = (EditText) findViewById(R.id.editDescripcion);
		
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
		getMenuInflater().inflate(R.menu.categoria_consultar, menu);
		return true;
	}

	public void consultarCategoria(View v) {
		helper.abrir();

		String[] valor=sp.getSelectedItem().toString().split("-");
		Categoria categoria = helper.consultarCategoria(valor[0].trim());

		helper.cerrar();

		if (categoria == null)
			Toast.makeText(
					this,
					"Pregunta con ID " + sp.getSelectedItem().toString()
							+ " no encontrado", Toast.LENGTH_LONG).show();
		else {

			editnombre.setText(categoria.getNombre());
			editDescripcion.setText(categoria.getDescripcion());

		}
	}

	public void limpiarTexto(View v) {
		editnombre.setText("");
		editDescripcion.setText("");
	}

}
