package sv.ues.fia.moviles.db;

import sv.ues.fia.moviles.modelo.Docente;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBDTarea {

	private static final String[] camposDocente = new String[] { "ID_DOCENTE",
			"EMAIL", "PASSWORD", "NOMBRE", "APELLIDO1", "APELLIDO2",
			"FECHA_NAC", "TELEFONO", "ESTADO" };

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
				// Creamos toda la estructura de las tablas que tendra la base
				// de datos.
				db.execSQL("create table docente (id_docente integer not null, email varchar(50) not null, password varchar(255) not null, nombre varchar(50) not null, apellido1 varchar(50) not null, apellido2 varchar(50), fecha_nac date, telefono char(10), estado integer not null, primary key (id_docente));");
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
		String regInsertados = "Registro Insertado Nº= ";
		long contador = 0;
		ContentValues docen = new ContentValues();

		docen.put("ID_DOCENTE", docente.getId());
		docen.put("EMAIL", docente.getEmail());
		docen.put("PASSWORD", docente.getPassword());
		docen.put("NOMBRE", docente.getNombre());
		docen.put("APELLIDO1", docente.getApellido1());
		docen.put("APELLIDO2", docente.getApellido2());
		docen.put("FECHA_NAC", docente.getFechaNac());
		docen.put("TELEFONO", docente.getTelefono());
		docen.put("ESTADO", docente.getEstado());

		contador = db.insert("DOCENTE", null, docen);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String actualizar(Docente docente) {
		if (verificarIntegridad(docente, 5)) {
			String[] id = { Integer.toString(docente.getId()) };
			ContentValues docen = new ContentValues();

			docen.put("ID_DOCENTE", docente.getId());
			docen.put("EMAIL", docente.getEmail());
			docen.put("PASSWORD", docente.getPassword());
			docen.put("NOMBRE", docente.getNombre());
			docen.put("APELLIDO1", docente.getApellido1());
			docen.put("APELLIDO2", docente.getApellido2());
			docen.put("FECHA_NAC", docente.getFechaNac());
			docen.put("TELEFONO", docente.getTelefono());
			docen.put("ESTADO", docente.getEstado());
			db.update("DOCENTE", docen, "id_docente = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro Cliente " + docente.getId() + " no existe";
		}
	}

	public String eliminar(Docente docente) {
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(docente, 3)) {
			regAfectados = "0";
			// aplica para cascada
			// borrar registros de cliente_vehiculo
			// contador += db.delete("cliente_vehiculo", "id_cliente='"+cliente.getIdCliente()+"'",
			// null); ¨
		} else {
			// borrar los registros de cliente
			contador += db.delete("docente", "id_docente='" + docente.getId() + "'", null);
			regAfectados += contador;
		}
		return regAfectados;
	}

	public Docente consultarDocente(String id_cliente) {

		String[] id = { id_cliente };
		Cursor cursor = db.query("DOCENTE", camposDocente, "id_docente = ?",
				id, null, null, null);

		if (cursor.moveToFirst()) {
			Docente docente = new Docente();

			docente.setId(cursor.getInt(0));
			docente.setEmail(cursor.getString(1));
			docente.setNombre(cursor.getString(3));
			docente.setApellido1(cursor.getString(4));
			docente.setApellido2(cursor.getString(5));
			docente.setTelefono(cursor.getString(7));

			return docente;
		} else {
			return null;
		}
	}

	private boolean verificarIntegridad(Object dato, int relacion)
			throws SQLException {
		switch (relacion) {
		
		case 3: {
			// verifica que no existan registros hijos de docente
//			Docente docente2 = (Docente) dato;
			return false;
//			Cursor c = db.query(true, "cliente_vehiculo",
//					new String[] { "id_docente" },
//					"id_docente='" + docente2.getId() + "'", null, null,
//					null, null, null);
//			if (c.moveToFirst())
//				return true;
//			else
//				return false;
		}
		
		case 5: {
			// verificar que exista Docente
			Docente docente2 = (Docente) dato;
			String[] id = { Integer.toString(docente2.getId()) };
			abrir();
			Cursor c2 = db.query("DOCENTE", null, "id_docente = ?", id, null,
					null, null);
			if (c2.moveToFirst()) {
				// Se encontro Alumno
				return true;
			}
			return false;
		}
		default:
			return false;
		}
	}
	
	public int getCount(String tabla, String id) {
	
//		String sql = "select ? from ? order by ? desc limit 1 ;"; 
		
//		abrir();
//		Cursor cursor = db.rawQuery(sql, new String[]{id, tabla, id});
//		cerrar();
//		if(cursor.moveToFirst()) {
//			System.out.println("El valor es: " + cursor.getInt(0));
//			return cursor.getInt(0) + 1;
//		} else { 
//			return 1;
//		}
		return 1;
	}

}