package sv.ues.fia.moviles;

import sv.ues.fia.moviles.db.ControlBDTarea;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TareaActivity extends ListActivity {

	String[] menu = { "Tabla Docente","Tabla Pregunta","Tabla Categoria","Tabla Detalle Categoria", "LLenar Base de Datos" };
	String[] activities = { "DocenteMenuActivity","PreguntaMenuActivity","CategoriaMenuActivity","DetalleCategoriaMenuActivity"};

	ControlBDTarea BDhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menu));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (position != 4) {
			String nombreValue = activities[position];
			try {
				Class<?> clase = Class
						.forName("sv.ues.fia.moviles.controlador."
								+ nombreValue);
				Intent inte = new Intent(this, clase);

				this.startActivity(inte);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			// CODIGO PARA LLENAR BASE DE DATOS
			System.out.println("=== LLENADO DE LA BD ===");
			BDhelper = new ControlBDTarea(this);
			BDhelper.abrir();
		  //  String tost = BDhelper.ControlBDTarea();
			BDhelper.cerrar();
			//Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
		}
	}

}
