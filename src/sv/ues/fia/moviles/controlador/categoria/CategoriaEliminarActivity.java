package sv.ues.fia.moviles.controlador.categoria;

import sv.ues.fia.moviles.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CategoriaEliminarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria_eliminar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categoria_eliminar, menu);
		return true;
	}

}
