package sv.ues.fia.moviles.controlador.asignatura;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Asignatura;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AsignaturaActualizarActivity extends Activity {
	
	ControlBDTarea helper;
	EditText editIdAsignatura;
	EditText editIdNombre;
	EditText editIdCodigo;
	EditText editIdDescripcion;
	CheckBox editIdEstado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asignatura_actualizar);
		
		helper = new ControlBDTarea(this);
		
		editIdAsignatura  = (EditText) findViewById(R.id.editIdAsignatura);
		editIdNombre      = (EditText) findViewById(R.id.editIdNombre);
		editIdCodigo      = (EditText) findViewById(R.id.editIdCodigo);
		editIdDescripcion = (EditText) findViewById(R.id.editIdDescripcion);
		editIdEstado      = (CheckBox) findViewById(R.id.editIdEstado);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asignatura_actualizar, menu);
		return true;
	}
	
	public void actualizarAsignatura(View v) {
		
		String idAsignatura = editIdAsignatura.getText().toString();
		String nombre = editIdNombre.getText().toString();
		String codigo = editIdCodigo.getText().toString();
		String descripcion = editIdDescripcion.getText().toString();
		int estado = 0;
		
		if (editIdEstado.isChecked()) estado = 1;
		
		Asignatura asignatura = new Asignatura();
		
		asignatura.setId(Integer.parseInt(idAsignatura));
		asignatura.setNombre(nombre);
		asignatura.setCodigo(codigo);
		asignatura.setDescripcion(descripcion);
		asignatura.setEstado(estado);

		helper.abrir();
		String regInsertados = helper.actualizar(asignatura);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
		
	}
	
	public void limpiarTexto(View v) {		
		editIdAsignatura.setText("");
		editIdNombre.setText("");
		editIdCodigo.setText("");
		editIdDescripcion.setText("");
		editIdEstado.setChecked(false);
	}


}
