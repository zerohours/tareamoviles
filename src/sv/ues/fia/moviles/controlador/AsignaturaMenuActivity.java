package sv.ues.fia.moviles.controlador;

import sv.ues.fia.moviles.R;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AsignaturaMenuActivity extends ListActivity {
	
	String[] menu = { "Insertar Registro", "Consultar Registro",
			"Actualizar Registro", "Eliminar Registro" };
	String[] activities = { "AsignaturaInsertarActivity",
			"AsignaturaConsultarActivity", "AsignaturaActualizarActivity",
			"AsignaturaEliminarActivity" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView listView = getListView();
		listView.setBackgroundColor(Color.rgb(0, 0, 255));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menu);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asignatura_menu, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String nombreValue = activities[position];
		l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 255));
		try {
			Class<?> clase = Class.forName("sv.ues.fia.moviles.controlador.asignatura."
					+ nombreValue);
			Intent inte = new Intent(this, clase);
			this.startActivity(inte);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
