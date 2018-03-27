package pms.com.pmdp04_tofranciscofernandez;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus410 on 25/03/2018.
 */

public class PreciosBD {

    public static final String ID = "_id";
    public static final String NOMBRE = "nombre";
    public static final String PRECIO = "precio";

    private static final String DATABASE_NAME = "dbprecios";
    private static final String DATABASE_TABLE = "precios";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE +
                    "(" + ID + " integer primary key autoincrement, "
                    + NOMBRE + " text not null, "
                    + PRECIO + " double not null)";

    private final Context context;
    private Helper BDHelper;
    private SQLiteDatabase bsSql;

    public PreciosBD (Context ctx){
        this.context = ctx;
        BDHelper = new Helper(context);
    }

    //--- abre una conexión a la BD para lectura/escritura
    public PreciosBD open() throws SQLException {
        bsSql = BDHelper.getWritableDatabase();
        return this;
    }

    //---cierra la base de datos---
    public void close() {
        BDHelper.close();
    }

    public long insertar(String nombre, double precio){
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE,nombre);
        valores.put(PRECIO,precio);

        //manda una sentencia INSERT a la BD para insertar una fila con los valores initialValues
        return bsSql.insert(DATABASE_TABLE, null, valores);
    }

    public  boolean actualizar (String producto, double precio){
        ContentValues args = new ContentValues();
        args.put(PRECIO, precio);

        //manda una sentencia UPDATE a la BD para modificar el contacto
        // identificado por numero
        return bsSql.update(DATABASE_TABLE, args, NOMBRE + "=" + producto, null)>0;
    }

    private static class Helper extends SQLiteOpenHelper {

        public Helper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //ejecuta la sentencia SQL de creación de la tabla de la BD
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
