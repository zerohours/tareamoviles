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

public class CicloConsultarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdCiclo;
	EditText editIdAnio;
	EditText editIdNumero;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ciclo_consultar);
		
		helper = new ControlBDTarea(this);
		
		editIdCiclo   = (EditText) findViewById(R.id.editIdCiclo);
		editIdAnio    = (EditText) findViewById(R.id.editIdAnio);
		editIdNumero  = (EditText) findViewById(R.id.editIdNumero);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ciclo_consultar, menu);
		return true;
	}
	
	public void consultarCiclo(View v) {
		helper.abrir();
		Ciclo ciclo = helper.consultarCiclo(editIdCiclo.getText()
				.toString());
		helper.cerrar();
		
		if (ciclo == null)
			Toast.makeText(
					this,
					"Ciclo con id " + editIdCiclo.getText().toString()
							+ " no encontrado", Toast.LENGTH_LONG).show();
		else {
			editIdCiclo.setText(Integer.toString(ciclo.getId()));
			editIdAnio.setText(Integer.toString(ciclo.getAnio()));
			editIdNumero.setText(Integer.toString(ciclo.getNumero()));
		}
	}
	
	public void limpiarTexto(View v) {
		editIdCiclo.setText("");
		editIdAnio.setText("");
		editIdNumero.setText("");
	}

}
