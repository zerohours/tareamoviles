package sv.ues.fia.moviles.controlador;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PreguntaMenuActivity extends ListActivity {

	String[] menu = { "Insertar Pregunta", "Eliminar Pregunta",
			"Consultar Pregunta", "Actualizar Pregunta" };
	String[] activities = { "PreguntaInsertarActivity",
			"PreguntaEliminarActivity", "PreguntaConsultarActivity",
			"PreguntaActualizarActivity" };

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
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String nombreValue = activities[position];
		l.getChildAt(position).setBackgroundColor(Color.rgb(0, 128, 255));
		try {
			Class<?> clase = Class.forName("sv.ues.fia.moviles.controlador.pregunta." + nombreValue);
			Intent inte = new Intent(this, clase);
			this.startActivity(inte);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

}
