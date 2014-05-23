package sv.ues.fia.moviles.controlador.categoria;

import java.util.ArrayList;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;
import sv.ues.fia.moviles.modelo.DetalleCategoria;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CategoriaEliminarActivity extends Activity {

	
	ControlBDTarea helper;
	Spinner sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria_eliminar);
		sp = (Spinner) findViewById(R.id.spinnerEliminar);
		helper=new ControlBDTarea (this);
	
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
		getMenuInflater().inflate(R.menu.categoria_eliminar, menu);
		return true;
	}
	
	public void eliminarCategoria(View v){
		String regEliminadas;
		String regEliminadasdos;
		String[] valor=sp.getSelectedItem().toString().split("-");
		helper.abrir();
		
		Categoria categoria=new Categoria();
		categoria.setId_cat(Integer.parseInt(valor[0].toString()));
		regEliminadas =helper.eliminar(categoria);
		
		
		DetalleCategoria detcat= new DetalleCategoria();
		detcat.setId_categoria(Integer.parseInt(valor[0].toString()));
		regEliminadasdos=helper.eliminar(detcat);
				
		helper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		Toast.makeText(this, regEliminadasdos, Toast.LENGTH_SHORT).show();
		}

	
	
	
}
