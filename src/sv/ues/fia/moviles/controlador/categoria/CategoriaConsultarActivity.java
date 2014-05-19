package sv.ues.fia.moviles.controlador.categoria;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaConsultarActivity extends Activity {

	ControlBDTarea helper;
	EditText editId;
	EditText editnombre;
	EditText editDescripcion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria_consultar);
		helper = new ControlBDTarea(this);
		editId = (EditText) findViewById(R.id.editCategoria);
		editnombre = (EditText) findViewById(R.id.editNombre);
		editDescripcion=(EditText) findViewById(R.id.editDescripcion);
	}
	
	public void consultarCategoria(View v) {
		helper.abrir();
		
		Categoria categoria=helper.consultarCategoria(editId.getText().toString());
		
		helper.cerrar();

		if (categoria == null)
			Toast.makeText(
					this,
					"Pregunta con ID " + editId.getText().toString()
							+ " no encontrado", Toast.LENGTH_LONG).show();
		else {
			editId.setText(categoria.getId());
			editnombre.setText(categoria.getNombre());
			editDescripcion.setText(categoria.getDescripcion());
		
		}
	}

	public void limpiarTexto(View v) {
		editId.setText("");
		editnombre.setText("");
		editDescripcion.setText("");
	
	}
	

}
