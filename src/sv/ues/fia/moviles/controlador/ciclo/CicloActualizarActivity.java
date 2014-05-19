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

public class CicloActualizarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdCiclo;
	EditText editIdAnio;
	EditText editIdNumero;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ciclo_actualizar);
		
		helper = new ControlBDTarea(this);
		
		editIdCiclo   = (EditText) findViewById(R.id.editIdCiclo);
		editIdAnio    = (EditText) findViewById(R.id.editIdAnio);
		editIdNumero  = (EditText) findViewById(R.id.editIdNumero);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ciclo_actualizar, menu);
		return true;
	}
	
	public void actualizarCiclo(View v) {
		
		String idCiclo = editIdCiclo.getText().toString();
		String anio = editIdAnio.getText().toString();
		String numero = editIdNumero.getText().toString();
		
		Ciclo ciclo = new Ciclo();
		ciclo.setId(Integer.parseInt(idCiclo));
		ciclo.setAnio(Integer.parseInt(anio));
		ciclo.setNumero(Integer.parseInt(numero));
		helper.abrir();
		String regInsertados = helper.actualizar(ciclo);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
		
	}
	
	public void limpiarTexto(View v) {
		editIdCiclo.setText("");
		editIdAnio.setText("");
		editIdNumero.setText("");
	}

}
