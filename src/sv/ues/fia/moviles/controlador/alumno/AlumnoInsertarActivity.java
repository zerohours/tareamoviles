package sv.ues.fia.moviles.controlador.alumno;

import sv.ues.fia.moviles.R;
import sv.ues.fia.moviles.db.ControlBDTarea;
import sv.ues.fia.moviles.modelo.Alumno;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;

public class AlumnoInsertarActivity extends Activity implements OnClickListener {

	ControlBDTarea helper;
	EditText editIdAlumno;
	EditText editIdEmail;
	EditText editIdPass;
	EditText editIdNombre;
	EditText editIdApell1;
	EditText editIdApell2;
	EditText editIdFechaNac;
	EditText editIdTelefono;
	CheckBox editIdEstado;

	// Variable for storing current date and time
	private int mYear, mMonth, mDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alumno_insertar);

		helper = new ControlBDTarea(this);

		editIdAlumno = (EditText) findViewById(R.id.editIdAlumno);
		editIdEmail = (EditText) findViewById(R.id.editIdEmail);
		editIdPass = (EditText) findViewById(R.id.editIdPass);
		editIdNombre = (EditText) findViewById(R.id.editIdNombre);
		editIdApell1 = (EditText) findViewById(R.id.editIdApell1);
		editIdApell2 = (EditText) findViewById(R.id.editIdApell2);
		editIdFechaNac = (EditText) findViewById(R.id.editIdFechaNac);
		editIdTelefono = (EditText) findViewById(R.id.editIdTelefono);
		editIdEstado = (CheckBox) findViewById(R.id.editIdEstado);

		editIdFechaNac.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alumno_insertar, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		if (v == editIdFechaNac) {

			// Process to get Current Date
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);

			// Launch Date Picker Dialog
			DatePickerDialog dpd = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							// Display Selected date in textbox
							editIdFechaNac.setText(year + "/"
									+ (monthOfYear + 1) + "/" + dayOfMonth);

						}
					}, mYear, mMonth, mDay);
			dpd.show();
		}
	}

	public void insertarAlumno(View v) {
		String idAlumno = editIdAlumno.getText().toString();
		String email = editIdEmail.getText().toString();
		String pass = editIdPass.getText().toString();
		String nombre = editIdNombre.getText().toString();
		String apell1 = editIdApell1.getText().toString();
		String apell2 = editIdApell2.getText().toString();
		String fechNac = editIdFechaNac.getText().toString();
		String telefono = editIdTelefono.getText().toString();
		int estado = 0;
		String regInsertados;

		if (editIdEstado.isChecked())
			estado = 1;

		Alumno alumno = new Alumno();

		alumno.setId(Integer.parseInt(idAlumno));
		alumno.setEmail(email);
		alumno.setPassword(pass);
		alumno.setNombre(nombre);
		alumno.setApellido1(apell1);
		alumno.setApellido2(apell2);
		alumno.setFechaNac(fechNac);
		alumno.setTelefono(telefono);
		alumno.setEstado(estado);

		helper.abrir();
		regInsertados = helper.insertarAlumno(alumno);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		limpiarTexto(v);
	}

	public void limpiarTexto(View v) {
		editIdAlumno.setText("");
		editIdEmail.setText("");
		editIdPass.setText("");
		editIdNombre.setText("");
		editIdApell1.setText("");
		editIdApell2.setText("");
		editIdFechaNac.setText("");
		editIdTelefono.setText("");
		editIdEstado.setChecked(false);
	}

}
