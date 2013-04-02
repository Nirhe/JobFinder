package come.jobfinder.dev;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLwrapperBasicTable {
	
	public static final String KEY_ROW_ID = "_id";
	public static final String KEY_NAME = "job_name";
	public static final String KEY_ADDRESS = "address";
	private static final String DATABASE_NAME = "jobFinderDb";
	private static final String MAIN_TABLE = "workPlaceDb";
	//"updateDetailsTable";
	private static final int DB_VERSION = 2;
	
	private DBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DBHelper extends SQLiteOpenHelper{

		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DB_VERSION);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " +MAIN_TABLE + " ( "+
			KEY_ROW_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_NAME
			+ " TEXT NOT NULL, "+ KEY_ADDRESS + " TEXT NOT NULL);"					
					);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ MAIN_TABLE);
			onCreate(db);
			
		}
		
	}
	
	public SQLwrapperBasicTable(Context c)
	{
		this.ourContext = c;
	}
	
	public SQLwrapperBasicTable open() throws SQLException{
		this.ourHelper = new DBHelper(this.ourContext);
		this.ourDatabase = this.ourHelper.getWritableDatabase();
		return this;
	}
	
    public void close()
    {
    	this.ourHelper.close();
    	
    }

	public long createEntry(String workPlace, String address) {
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_NAME, workPlace);
		contentValues.put(KEY_ADDRESS, address);
		return (this.ourDatabase.insert(MAIN_TABLE,null, contentValues));
		
	}
	
	public void deleteEntry(String workPlace){
		
		this.ourDatabase.delete(MAIN_TABLE, KEY_NAME+"-"+workPlace, null);
		
	}
	
	public ArrayList<String>  getData(){
		String[] columns = { KEY_NAME, KEY_ADDRESS };
		Cursor cursor = this.ourDatabase.query(MAIN_TABLE, columns, null,
				null, null, null, null);
		ArrayList<String>  result = new ArrayList<String>();

		//int iRow = cursor.getColumnIndex(KEY_ROW_ID);
		int iName = cursor.getColumnIndex(KEY_NAME);
		int iAddress = cursor.getColumnIndex(KEY_ADDRESS);
		
		int i = 0 ;
		for (cursor.moveToFirst(); i <cursor.getCount(); cursor
				.moveToNext()) {

			String row = cursor.getString(iName) + " "
					+ cursor.getString(iAddress) + "\n";
			result.add(i, row);

			i++;
		}
		return result;
	}
	

	
}
