package sv.ues.fia.moviles.controlador.ciclo;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Ciclo;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloEliminarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdCiclo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ciclo_eliminar);
		
		helper = new ControlBDTarea(this);
		
		editIdCiclo   = (EditText) findViewById(R.id.editIdCiclo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ciclo_eliminar, menu);
		return true;
	}
	
	public void eliminarCiclo(View v) {
		String regEliminadas;
		Ciclo ciclo = new Ciclo();
		ciclo.setId(Integer.parseInt(editIdCiclo.getText().toString()));
		System.out.println("El id es: " + ciclo.getId());
		helper.abrir();
		regEliminadas = helper.eliminar(ciclo);
		helper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
	}
	
	public void limpiarTexto(View v) {
		editIdCiclo.setText("");
	}

}
