package sv.ues.fia.moviles.db;

import java.util.ArrayList;

import sv.ues.fia.moviles.bean.BeanPregunta;
import sv.ues.fia.moviles.modelo.AsignaCiclo;
import sv.ues.fia.moviles.modelo.Asignatura;
import sv.ues.fia.moviles.modelo.Categoria;
import sv.ues.fia.moviles.modelo.Ciclo;
import sv.ues.fia.moviles.modelo.DetalleCategoria;
import sv.ues.fia.moviles.modelo.Docente;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ControlBDTarea {

	private static final String[] camposCategoria = new String[] { "id_cat",
			"nombre", "descripcion" };
	private static final String[] camposPregunta = new String[] { "id_preg",
			"pregunta" };
	private static final String[] camposDetalleCategoria = new String[] {
			"idpregunta", "idcategoria" };
	private static final String[] camposDocente = new String[] { "ID_DOCENTE",
			"EMAIL", "PASSWORD", "NOMBRE", "APELLIDO1", "APELLIDO2",
			"FECHA_NAC", "TELEFONO", "ESTADO" };
	private static final String[] camposAsignaCiclo = new String[] { "id",
			"id_asignatura", "id_docente", "id_ciclo" };
	private static final String[] camposAsignatura = new String[] {
			"id_asignatura", "nombre", "codigo", "descripcion" };
	private static final String[] camposCiclo = new String[] { "id_ciclo",
			"anio", "numero" };

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
				db.execSQL("CREATE TABLE ciclo (id_ciclo integer not null, anio integer not null, numero integer not null, primary key (id_ciclo));");
				db.execSQL("CREATE TABLE asignatura (id_asignatura integer not null, nombre varchar(50) not null, codigo varchar(8) not null, descripcion varchar(255) not null, estado integer not null, primary key (id_asignatura));");
				db.execSQL(" CREATE TABLE asigna_ciclo (id integer not null, id_asignatura integer not null, id_docente integer not null, id_ciclo integer not null, primary key (id));");
				db.execSQL("CREATE TABLE pregunta(id_preg integer not null,pregunta varchar(255) NOT NULL, primary key(id_preg));");
				db.execSQL("CREATE TABLE categoria(id_cat INTEGER NOT NULL PRIMARY KEY,nombre VARCHAR(50),descripcion VARCHAR(255));");
				db.execSQL("CREATE TABLE detcat( [id_serial] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, id_pregunta integer not null,id_categoria integer not null);");
				db.execSQL("create table docente (id_docente integer not null, email varchar(50) not null, password varchar(255) not null, nombre varchar(50) not null, apellido1 varchar(50) not null, apellido2 varchar(50), fecha_nac date, telefono char(10), estado integer not null, primary key (id_docente));");

				// Asignacion de triggers para la tabla asigna_ciclo
				db.execSQL("CREATE TRIGGER fk_asignaciclo_docente  BEFORE INSERT ON asigna_ciclo  FOR EACH ROW  BEGIN  SELECT CASE  WHEN ((SELECT id_docente FROM docente WHERE id_docente = NEW.id_docente) IS NULL)  THEN RAISE(ABORT, 'No existe el docente.')  END; END;");
				db.execSQL("CREATE TRIGGER fk_asignaciclo_ciclo  BEFORE INSERT ON asigna_ciclo  FOR EACH ROW   BEGIN	 SELECT CASE  WHEN ((SELECT id_ciclo FROM ciclo WHERE id_ciclo = NEW.id_ciclo) IS NULL)	THEN RAISE(ABORT, 'No existe el ciclo.')  END;  END;");
				db.execSQL("CREATE TRIGGER fk_asignaciclo_asignatura  BEFORE INSERT ON asigna_ciclo  FOR EACH ROW  BEGIN  SELECT CASE  WHEN ((SELECT id_asignatura FROM asignatura WHERE id_asignatura = NEW.id_asignatura) IS NULL)  THEN RAISE(ABORT, 'No existe la asignatura.')  END;  END;");

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
		if (verificarIntegridad(docente, 4)) {
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
			// contador += db.delete("cliente_vehiculo",
			// "id_cliente='"+cliente.getIdCliente()+"'",
			// null); ¨
		} else {
			// borrar los registros de cliente
			contador += db.delete("docente", "id_docente='" + docente.getId()
					+ "'", null);
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

	public String insertar(Ciclo ciclo) {
		String regInsertados = "Registro Insertado Nº= ";
		long contador = 0;
		ContentValues cicl = new ContentValues();

		cicl.put("id_ciclo", ciclo.getId());
		cicl.put("anio", ciclo.getAnio());
		cicl.put("numero", ciclo.getNumero());

		contador = db.insert("ciclo", null, cicl);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String actualizar(Ciclo ciclo) {
		if (verificarIntegridad(ciclo, 8)) {
			String[] id = { Integer.toString(ciclo.getId()) };
			ContentValues cicl = new ContentValues();

			cicl.put("id_ciclo", ciclo.getId());
			cicl.put("anio", ciclo.getAnio());
			cicl.put("numero", ciclo.getNumero());

			db.update("ciclo", cicl, "id_ciclo = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro Ciclo " + ciclo.getId() + " no existe";
		}
	}

	public String eliminar(Ciclo ciclo) {
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(ciclo, 7)) {
			regAfectados = "0";
			// aplica para cascada
			// borrar registros de cliente_vehiculo
			// contador += db.delete("cliente_vehiculo",
			// "id_cliente='"+cliente.getIdCliente()+"'",
			// null); ¨
		} else {
			// borrar los registros de cliente
			contador += db.delete("ciclo", "id_ciclo='" + ciclo.getId() + "'",
					null);
			regAfectados += contador;
		}
		return regAfectados;
	}

	public Ciclo consultarCiclo(String id_ciclo) {

		String[] id = { id_ciclo };
		Cursor cursor = db.query("ciclo", camposCiclo, "id_ciclo = ?", id,
				null, null, null);

		if (cursor.moveToFirst()) {
			Ciclo ciclo = new Ciclo();

			ciclo.setId(cursor.getInt(0));
			ciclo.setAnio(cursor.getInt(1));
			ciclo.setNumero(cursor.getInt(2));

			return ciclo;
		} else {
			return null;
		}
	}

	public String insertar(Asignatura asignatura) {

		String regInsertados = "Registro Insertado Nº= ";
		long contador = 0;
		ContentValues asig = new ContentValues();

		asig.put("id_asignatura", asignatura.getId());
		asig.put("nombre", asignatura.getNombre());
		asig.put("codigo", asignatura.getCodigo());
		asig.put("descripcion", asignatura.getDescripcion());
		asig.put("estado", asignatura.getEstado());

		contador = db.insert("asignatura", null, asig);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String actualizar(Asignatura asignatura) {
		if (verificarIntegridad(asignatura, 12)) {
			String[] id = { Integer.toString(asignatura.getId()) };
			ContentValues asig = new ContentValues();

			asig.put("id_asignatura", asignatura.getId());
			asig.put("nombre", asignatura.getNombre());
			asig.put("codigo", asignatura.getCodigo());
			asig.put("descripcion", asignatura.getDescripcion());

			db.update("asignatura", asig, "id_asignatura = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro Asignatura " + asignatura.getId() + " no existe";
		}
	}

	public String eliminar(Asignatura asignatura) {
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(asignatura, 11)) {
			regAfectados = "0";
			// aplica para cascada
			// borrar registros de cliente_vehiculo
			// contador += db.delete("cliente_vehiculo",
			// "id_cliente='"+cliente.getIdCliente()+"'",
			// null); ¨
		} else {
			// borrar los registros de cliente
			contador += db.delete("asignatura",
					"id_asignatura='" + asignatura.getId() + "'", null);
			regAfectados += contador;
		}
		return regAfectados;
	}

	public Asignatura consultarAsignatura(String id_asignatura) {

		String[] id = { id_asignatura };
		Cursor cursor = db.query("asignatura", camposAsignatura,
				"id_asignatura = ?", id, null, null, null);

		if (cursor.moveToFirst()) {
			Asignatura asignatura = new Asignatura();

			asignatura.setId(cursor.getInt(0));
			asignatura.setNombre(cursor.getString(1));
			asignatura.setCodigo(cursor.getString(2));
			asignatura.setDescripcion(cursor.getString(3));

			return asignatura;
		} else {
			return null;
		}
	}

	public String insertar(AsignaCiclo asignaciclo) {
		String regInsertados = "Registro Insertado Nº= ";
		long contador = 0;
		ContentValues asigcicl = new ContentValues();

		asigcicl.put("id", asignaciclo.getId());
		asigcicl.put("id_asignatura", asignaciclo.getIdAsignatura());
		asigcicl.put("id_docente", asignaciclo.getIdDocente());
		asigcicl.put("id_ciclo", asignaciclo.getIdCiclo());

		contador = db.insert("asigna_ciclo", null, asigcicl);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String actualizar(AsignaCiclo asignaciclo) {
		if (verificarIntegridad(asignaciclo, 16)) {
			String[] id = { Integer.toString(asignaciclo.getId()) };
			ContentValues asig = new ContentValues();

			asig.put("id", asignaciclo.getId());
			asig.put("id_asignatura", asignaciclo.getIdAsignatura());
			asig.put("id_docente", asignaciclo.getIdDocente());
			asig.put("id_ciclo", asignaciclo.getIdCiclo());

			db.update("asigna_ciclo", asig, "id = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro Cliente " + asignaciclo.getId() + " no existe";
		}
	}

	public String eliminar(AsignaCiclo asignaciclo) {
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(asignaciclo, 15)) {
			regAfectados = "0";
			// aplica para cascada
			// borrar registros de cliente_vehiculo
			// contador += db.delete("cliente_vehiculo",
			// "id_cliente='"+cliente.getIdCliente()+"'",
			// null); ¨
		} else {
			// borrar los registros de cliente
			contador += db.delete("asigna_ciclo", "id='" + asignaciclo.getId()
					+ "'", null);
			regAfectados += contador;
		}
		return regAfectados;
	}

	public AsignaCiclo consultarAsignaCiclo(String id_asignaciclo) {

		String[] id = { id_asignaciclo };
		Cursor cursor = db.query("asigna_ciclo", camposAsignaCiclo, "id = ?",
				id, null, null, null);

		if (cursor.moveToFirst()) {
			AsignaCiclo asignaciclo = new AsignaCiclo();

			asignaciclo.setId(cursor.getInt(0));
			asignaciclo.setIdAsignatura(cursor.getInt(1));
			asignaciclo.setIdDocente(cursor.getInt(2));
			asignaciclo.setIdCiclo(cursor.getInt(3));

			return asignaciclo;
		} else {
			return null;
		}
	}

	/** Insertar **/
	public String insertar(Pregunta pregunta) {
		String regInsertados = "Registro Insertado Nº= ";
		long contador = 0;
		ContentValues preg = new ContentValues();

		preg.put("id_preg", pregunta.getId_preg());
		preg.put("pregunta", pregunta.getPregunta());

		contador = db.insert("pregunta", null, preg);

		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String insertar(Categoria categoria) {
		String regInsertados = "Registro Insertado Nº= ";
		long contador = 0;
		ContentValues catego = new ContentValues();

		catego.put("id_cat", categoria.getId_cat());
		catego.put("nombre", categoria.getNombre());
		catego.put("descripcion", categoria.getDescripcion());

		contador = db.insert("categoria", null, catego);

		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String insertar(DetalleCategoria detallecategoria) {
		String regInsertados = "Registro Insertado Nº= ";
		long contador = 0;
		Cursor reg;
		reg = db.rawQuery(
				" select  id_serial from detcat order by id_serial desc limit 1",
				null);
		reg.getCount();
		int prue = 0;
		if (reg.moveToFirst()) {
			prue = reg.getInt(0) + 1;// incremetador
		}
		ContentValues catedeta = new ContentValues();

		catedeta.put("id_serial", prue);
		catedeta.put("id_pregunta", detallecategoria.getId_pregunta());
		catedeta.put("id_categoria", detallecategoria.getId_categoria());

		contador = db.insert("detcat", null, catedeta);

		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	/** Eliminar **/

	public String eliminar(Pregunta pregunta) {
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(pregunta, 99)) {

			contador += db.delete("pregunta",
					"id_preg='" + pregunta.getId_preg() + "'", null);

			regAfectados += contador;
		} else {
			regAfectados = "0";
		}
		return regAfectados;
	}

	public String eliminar(Categoria categoria) {
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(categoria, 1000)) {

			contador += db.delete("categoria",
					"id_cat='" + categoria.getId_cat() + "'", null);

			regAfectados += contador;
		} else {
			regAfectados = "0";
		}
		return regAfectados;
	}

	public String eliminar(DetalleCategoria detallecategoria) {
		return null;
	}

	/** Actualizar **/

	public String actualizar(Pregunta pregunta) {

		if (verificarIntegridad(pregunta, 99)) {

			String[] id = { String.valueOf(pregunta.getId_preg()) };

			ContentValues cv = new ContentValues();
			cv.put("id_preg", pregunta.getId_preg());
			cv.put("pregunta", pregunta.getPregunta());

			db.update("pregunta", cv, "id_preg = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro con ID " + pregunta.getId_preg() + " no existe";
		}

	}

	public String actualizar(Categoria categoria) {
		if (verificarIntegridad(categoria, 1000)) {

			String[] id = { String.valueOf(categoria.getId_cat()) };

			ContentValues cv = new ContentValues();
			cv.put("id_cat", categoria.getId_cat());
			cv.put("nombre", categoria.getNombre());
			cv.put("descripcion", categoria.getDescripcion());

			db.update("categoria", cv, "id_cat = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro con ID " + categoria.getId_cat() + " no existe";
		}

	}

	/*
	 * public String actualizar(Asignatura asignatura) { if
	 * (verificarIntegridad(asignatura, 12)) { String[] id = {
	 * Integer.toString(asignatura.getId()) }; ContentValues asig = new
	 * ContentValues();
	 * 
	 * asig.put("id_asignatura", asignatura.getId()); asig.put("nombre",
	 * asignatura.getNombre()); asig.put("codigo", asignatura.getCodigo());
	 * asig.put("descripcion", asignatura.getDescripcion());
	 * 
	 * db.update("asignatura", asig, "id_asignatura = ?", id); return
	 * "Registro Actualizado Correctamente"; } else { return
	 * "Registro Asignatura " + asignatura.getId() + " no existe"; } }
	 */
	public String actualizar(DetalleCategoria detallecategoria) {
		return null;

	}

	/** Consultar **/
	public Categoria consultarCategoria(String id_cat) {// verifcar
		String[] id = { id_cat };
		Cursor cursor = db.query("categoria", camposCategoria, "id_cat = ?",
				id, null, null, null);

		if (cursor.moveToFirst()) {

			Categoria cat = new Categoria();
			cat.setId_cat(cursor.getInt(0));
			cat.setNombre(cursor.getString(1));
			cat.setDescripcion(cursor.getString(2));

			return cat;
		} else {
			return null;
		}
	}

	public void consultarCategoriaAll(ArrayList<Categoria> categoria) {// verifcar
		abrir();
		Cursor cursor = db.rawQuery("select * from categoria", null);
		if (cursor != null && cursor.getCount() > 0) {
			// Categoria cat;
			while (cursor.moveToNext()) {
				// cat=new Categoria();
				Categoria cat = new Categoria();
				cat.setId_cat(cursor.getInt(0));
				cat.setNombre(cursor.getString(1));
				cat.setDescripcion(cursor.getString(2));
				categoria.add(cat);
			}
		} else {
			CharSequence text = "Categoria Vacia!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

	public void consultarCategoriaAllById(String categorias,ArrayList<BeanPregunta> beanPregunta) {// verifcar

		abrir();
		
		try{
//		Cursor cursor = db.rawQuery(
//						"SELECT detcat.id_categoria,pregunta.pregunta FROM detcat,pregunta WHERE detcat.id_categoria = ? AND detcat.id_pregunta=pregunta.id_preg",
//						new String[] { categorias });
			Cursor cursor = db.rawQuery(
					"SELECT detcat.id_categoria,pregunta.pregunta FROM detcat,pregunta WHERE detcat.id_categoria = '3' AND detcat.id_pregunta=pregunta.id_preg",
					null);
	
		
		if (cursor != null && cursor.getCount() > 0) {
			// Categoria cat;
			while (cursor.moveToNext()) {
			
				BeanPregunta detcat = new BeanPregunta();
				detcat.setId_categoria(Integer.parseInt(cursor.getString(0)));
				detcat.setPregunta(cursor.getString(1));
				beanPregunta.add(detcat);
			
			}
		} else {
			CharSequence text = "Categoria sin Preguntas!!!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		}catch(Exception e){}
		
	}

	
	public void consultarPreguntaAll(ArrayList<Pregunta> pregunta) {// verifcar
		abrir();
		Cursor cursor = db.rawQuery("select * from pregunta", null);
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Pregunta preg = new Pregunta();
				preg.setId_preg(cursor.getInt(0));
				preg.setPregunta(cursor.getString(1));
				pregunta.add(preg);
			}
		} else {
			CharSequence text = "Pregunta Vacia!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

	public Pregunta consultarPregunta(String id_preg) {// verificar parametros
		String[] id = { id_preg };
		Cursor cursor = db.query("pregunta", camposPregunta, "id_preg = ?", id,
				null, null, null);

		if (cursor.moveToFirst()) {

			Pregunta pregunta = new Pregunta();
			pregunta.setId_preg(cursor.getInt(0));
			pregunta.setPregunta(cursor.getString(1));

			return pregunta;
		} else {
			return null;
		}
	}

	// ***verificar integridad***//
	private boolean verificarIntegridad(Object dato, int relacion)
			throws SQLException {
		switch (relacion) {

		case 3: {
			// verifica que no existan registros hijos de docente
			// Docente docente2 = (Docente) dato;
			return false;
			// Cursor c = db.query(true, "cliente_vehiculo",
			// new String[] { "id_docente" },
			// "id_docente='" + docente2.getId() + "'", null, null,
			// null, null, null);
			// if (c.moveToFirst())
			// return true;
			// else
			// return false;
		}

		case 4: {
			// verificar que exista Docente
			Docente docente2 = (Docente) dato;
			String[] id = { Integer.toString(docente2.getId()) };
			abrir();
			Cursor c2 = db.query("DOCENTE", null, "id_docente = ?", id, null,
					null, null);
			if (c2.moveToFirst()) {
				// Se encontro Docente
				return true;
			}
			return false;
		}

		case 7: {
			// verifica que no existan registros hijos de ciclo
			// Ciclo ciclo2 = (Ciclo) dato;
			return false;
			// Cursor c = db.query(true, "cliente_vehiculo",
			// new String[] { "id_docente" },
			// "id_docente='" + docente2.getId() + "'", null, null,
			// null, null, null);
			// if (c.moveToFirst())
			// return true;
			// else
			// return false;
		}

		case 8: {
			// verificar que exista Ciclo
			Ciclo ciclo2 = (Ciclo) dato;
			String[] id = { Integer.toString(ciclo2.getId()) };
			abrir();
			Cursor c2 = db.query("ciclo", null, "id_ciclo = ?", id, null, null,
					null);
			if (c2.moveToFirst()) {
				// Se encontro Ciclo
				return true;
			}
			return false;
		}

		case 11: {
			// verifica que no existan registros hijos de Asignatura
			// Asignatura asignatura2 = (Asignatura) dato;
			return false;
			// Cursor c = db.query(true, "cliente_vehiculo",
			// new String[] { "id_docente" },
			// "id_docente='" + docente2.getId() + "'", null, null,
			// null, null, null);
			// if (c.moveToFirst())
			// return true;
			// else
			// return false;
		}

		case 12: {
			// verificar que exista Asignatura
			Asignatura asignatura2 = (Asignatura) dato;
			String[] id = { Integer.toString(asignatura2.getId()) };
			abrir();
			Cursor c2 = db.query("asignatura", null, "id_asignatura = ?", id,
					null, null, null);
			if (c2.moveToFirst()) {
				// Se encontro Ciclo
				return true;
			}
			return false;
		}

		case 15: {
			// verifica que no existan registros hijos de Asignatura
			// AsignaCiclo asignatura2 = (AsignaCiclo) dato;
			return false;
			// Cursor c = db.query(true, "cliente_vehiculo",
			// new String[] { "id_docente" },
			// "id_docente='" + docente2.getId() + "'", null, null,
			// null, null, null);
			// if (c.moveToFirst())
			// return true;
			// else
			// return false;
		}

		case 16: {
			// verificar que exista Asignatura
			AsignaCiclo asignaciclo2 = (AsignaCiclo) dato;
			String[] id = { Integer.toString(asignaciclo2.getId()) };
			abrir();
			Cursor c2 = db.query("asigna_ciclo", null, "id = ?", id, null,
					null, null);
			if (c2.moveToFirst()) {
				// Se encontro Ciclo
				return true;
			}
			return false;
		}

		case 99: {

			Pregunta pregunta = (Pregunta) dato;
			String[] id1 = { String.valueOf(pregunta.getId_preg()) };
			abrir();
			Cursor cursor1 = db.query("pregunta", null, "id_preg = ?", id1,
					null, null, null);

			//
			if (cursor1.moveToFirst()) {
				return true;
			} else {
				return false;
			}

		}

		case 1000: {
			Categoria categoria = (Categoria) dato;
			String[] idcat = { String.valueOf(categoria.getId_cat()) };
			abrir();
			Cursor cursor = db.query("categoria", null, "id_cat = ?", idcat,
					null, null, null);
			if (cursor.moveToFirst()) {
				// // Se encontraron datos
				return true;
			} else {
				return false;
			}
		}

		default:
			return false;
		}

	}

	public String llenarBD() {

		final int[] VCid = { 1, 2, 3, 4 };
		final String[] VCnombre = { "MATEMATICA", "SOCIALES", "LENGUAJE",
				"INGLES" };
		final String[] VCdescripcion = { "NUMEROS", "MAPAS XXX", "LETRAS",
				"IDOMA INGLES" };

		final int[] VPid = { 11, 22, 33, 44 };
		final String[] VPpregunta = {
				"¿Podrían sumarse los infinítos números que hay entre el 0 y el 1?",
				"¿como se llaman las principales áreas culturales del continente americano? ",
				"¿Qué es una etopeya?",
				"We were having a good time at the party, weren't we?" };
		final int[] VDCidpregunta = { 11, 22, 33, 44 };
		final int[] VDCidcategoria = { 1, 2, 3, 4 };

		abrir();
		db.execSQL("DELETE FROM categoria");
		db.execSQL("DELETE FROM pregunta");
		// db.execSQL("DELETE FROM detallecategoria");

		Pregunta pregunta = new Pregunta();
		for (int i = 0; i < 4; i++) {
			pregunta.setId_preg(VPid[i]);
			pregunta.setPregunta(VPpregunta[i]);
			insertar(pregunta);
		}
		Categoria categoria = new Categoria();
		for (int i = 0; i < 4; i++) {
			categoria.setId_cat(VCid[i]);
			categoria.setNombre(VCnombre[i]);
			categoria.setDescripcion(VCdescripcion[i]);
			insertar(categoria);

		}
		DetalleCategoria detcat = new DetalleCategoria();
		for (int i = 0; i < 4; i++) {

			detcat.setId_categoria(VDCidcategoria[i]);
			detcat.setId_pregunta(VDCidpregunta[i]);
			insertar(detcat);
		}

		cerrar();
		return "Guardo Correctamente";
	}

	public int getCount(String tabla, String id) {

		// String sql = "select ? from ? order by ? desc limit 1 ;";

		// abrir();
		// Cursor cursor = db.rawQuery(sql, new String[]{id, tabla, id});
		// cerrar();
		// if(cursor.moveToFirst()) {
		// System.out.println("El valor es: " + cursor.getInt(0));
		// return cursor.getInt(0) + 1;
		// } else {
		// return 1;
		// }
		return 1;
	}

}