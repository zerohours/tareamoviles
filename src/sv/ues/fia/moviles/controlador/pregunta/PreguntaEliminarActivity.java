package sv.ues.fia.moviles.controlador.pregunta;

import java.util.ArrayList;

import sv.ues.fia.moviles.R;

import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.DetalleCategoria;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class PreguntaEliminarActivity extends Activity {

	
	ControlBDTarea helper;
	Spinner sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregunta_eliminar);
		helper = new ControlBDTarea(this);
	
		sp = (Spinner) findViewById(R.id.spinnerEliminar);
		ArrayList<Pregunta> pregunta = new ArrayList<Pregunta>();
		try {

			helper.consultarPreguntaAll(pregunta);

		} catch (Exception e) {

		}

		ArrayAdapter<Pregunta> adaptador = new ArrayAdapter<Pregunta>(this,
				android.R.layout.simple_list_item_1, pregunta);
		sp.setAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pregunta_eliminar, menu);
		return true;
	}

	public void eliminarPregunta(View v) {
		String regEliminadas;
		String regEliminadasdos;
		
		int valor=Integer.parseInt(sp.getSelectedItem().toString());
		helper.abrir();
		Pregunta preg = new Pregunta();
		preg.setId_preg(valor);
		regEliminadas = helper.eliminar(preg);
		
		DetalleCategoria detcat=new DetalleCategoria();
		detcat.setId_pregunta(valor);
		regEliminadasdos=helper.eliminarIdPregunta(detcat);
		
		helper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		Toast.makeText(this, regEliminadasdos, Toast.LENGTH_SHORT).show();
	}
}
