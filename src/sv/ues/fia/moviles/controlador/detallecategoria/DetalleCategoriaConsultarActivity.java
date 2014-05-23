package sv.ues.fia.moviles.controlador.detallecategoria;

import java.util.ArrayList;
import java.util.List;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.bean.BeanPregunta;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Categoria;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class DetalleCategoriaConsultarActivity extends Activity {

	ControlBDTarea helper;
	Spinner cat;
	ListView li;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_categoria_consultar);
		helper = new ControlBDTarea(this);
		 
		cat = (Spinner) findViewById(R.id.spinnerCategoria);
		li=(ListView)findViewById(R.id.ListPregunta);
		ArrayList<Categoria> categoria = new ArrayList<Categoria>();

		try {

			helper.consultarCategoriaAll(categoria);

		} catch (Exception e) {

		}

		ArrayAdapter<Categoria> adaptador = new ArrayAdapter<Categoria>(this,
				android.R.layout.simple_list_item_1, categoria);
		cat.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_categoria_consultar, menu);
		return true;
	}
	
	public void consultarDetalleCategoria(View v) {

		helper.abrir();
		String[] valor=cat.getSelectedItem().toString().split("-");

		
		ArrayList<BeanPregunta> lista=helper.consultarCategoriaAllById(valor[0].trim());
		for(BeanPregunta us: lista){
			System.out.println("El valor es: " + us.getId_categoria() +"otro valor"+ us.getPregunta());
		}
		
		ArrayAdapter<BeanPregunta> adaptador = new ArrayAdapter<BeanPregunta>(this,
				android.R.layout.simple_list_item_1,lista);
			
		helper.cerrar();	
		li.setAdapter(adaptador);
		
	//	}
	}

}
