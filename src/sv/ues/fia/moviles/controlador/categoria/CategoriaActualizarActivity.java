package sv.ues.fia.moviles.controlador.categoria;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaActualizarActivity extends Activity {

	ControlBDTarea helper;
	EditText editId;
	EditText editNombre;
	EditText editDescripcion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria_actualizar);
		helper = new ControlBDTarea(this);
		editId = (EditText) findViewById(R.id.editIdcategoria);
		editNombre = (EditText) findViewById(R.id.editnombre);
		editDescripcion=(EditText) findViewById(R.id.editdescripcion);
	}

	public void actualizarCategoria(View v) {

		int id=Integer.parseInt(editId.getText().toString());
		String nom=editNombre.getText().toString();
		String des=editDescripcion.getText().toString();
		
		Categoria categ=new Categoria();
		categ.setId(id);
		categ.setNombre(nom);
		categ.setDescripcion(des);
		
		helper.abrir();
		String estado = helper.actualizar(categ);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
	}

	public void limpiarTexto(View v) {
		editId.setText("");
		editNombre.setText("");
		editDescripcion.setText("");
	}
	

}
