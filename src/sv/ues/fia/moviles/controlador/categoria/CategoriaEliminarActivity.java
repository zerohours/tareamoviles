package sv.ues.fia.moviles.controlador.categoria;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaEliminarActivity extends Activity {

	EditText editId;
	ControlBDTarea controlhelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria_eliminar);
		controlhelper=new ControlBDTarea (this);
		editId=(EditText)findViewById(R.id.editIdcategoria);
	}

	public void eliminarCategoria(View v){
		String regEliminadas;	
		Categoria cat=new Categoria();
		
		cat.setId(Integer.parseInt(editId.getText().toString()));

		controlhelper.abrir();
		regEliminadas=controlhelper.eliminar(cat);
		controlhelper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		}

}
