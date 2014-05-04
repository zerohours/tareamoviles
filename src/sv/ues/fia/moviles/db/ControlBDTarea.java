package sv.ues.fia.moviles.db;

import sv.ues.fia.moviles.modelo.Docente;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBDTarea {
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public ControlBDTarea(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String BASE_DATOS = "tareamovil.s3db";
		private static final int VERSION = 1;

		public DatabaseHelper(Context context) {
			super(context, BASE_DATOS, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				// Creamos toda la estructura de las tablas que tendra la base de datos.
				db.execSQL("CREATE TABLE docente (id INTEGER NOT NULL PRIMARY KEY);");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		}

	}
	
	public void abrir() throws SQLException {
		db = DBHelper.getWritableDatabase();
	}

	public void cerrar() {
		DBHelper.close();
	}
	
	public String insertar(Docente docente) {
		return null;
	}
	
	public String actualizar(Docente docente) {
		return null;
	}
	
	public String eliminar(Docente docente) {
		return null;
	}
	
}