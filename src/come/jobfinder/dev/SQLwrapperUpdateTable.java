package come.jobfinder.dev;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLwrapperUpdateTable {
	public static final String KEY_ROW_ID = "_id";
	public static final String KEY_NAME = "job";
	//public static final String KEY_POSITION = "position";
	public static final String KEY_CONTACT_THEM = "is_contact";
	public static final String KEY_BACK = "is_back";
	public static final String KEY_SET_INTERVIEW = "is_interview";
	public static final String KEY_DATE_INTERVIEW ="date_interview";
//	public static final String KEY_HOUR_INTERVIEW ="hour_interview";
			
 
	
	
	private static final String DATABASE_NAME = "jobFinderDb";
	private static final String WORKPLACE_TABLE = "updateDetailsTable1";
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
					db.execSQL("CREATE TABLE " +WORKPLACE_TABLE + " ( "+
					KEY_ROW_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_NAME
					+ " TEXT NOT NULL, "+
					KEY_CONTACT_THEM+" TEXT NOT NULL, "+KEY_BACK+" TEXT NOT NULL, " 
					+KEY_SET_INTERVIEW+" TEXT NOT NULL, "+KEY_DATE_INTERVIEW+" INTEGER NOT NULL, " +
					" FOREIGN KEY ("+KEY_NAME+") REFERENCES workPlaceDb(job_name) );");
		}
		

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ WORKPLACE_TABLE);
			onCreate(db);
			
		}
		
	}
	
	public SQLwrapperUpdateTable(Context c)
	{
		this.ourContext = c;
	}
	
	public SQLwrapperUpdateTable open() throws SQLException{
		this.ourHelper = new DBHelper(this.ourContext);
		this.ourDatabase = this.ourHelper.getWritableDatabase();
		return this;
	}
	
    public void close()
    {
    	this.ourHelper.close();
    }

	public long createEntry(String workPlace,String backToMe
			,String contact , String interview ,long date ) {
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_NAME, workPlace);
//		contentValues.put(KEY_POSITION, position);
		contentValues.put(KEY_CONTACT_THEM,contact);
		contentValues.put(KEY_SET_INTERVIEW,interview);
		contentValues.put(KEY_BACK,backToMe);
		contentValues.put(KEY_DATE_INTERVIEW, date);
	//	contentValues.put(KEY_HOUR_INTERVIEW,hour);
		
		return (this.ourDatabase.insert(WORKPLACE_TABLE,null, contentValues));
		
	}
	
	public void deleteEntry(String workPlace){
		
		this.ourDatabase.delete(WORKPLACE_TABLE, KEY_NAME+"-"+workPlace, null);
		
	}
	
	public ArrayList<String>  getData(){
		String[] columns = { KEY_NAME };
		Cursor cursor = this.ourDatabase.query(WORKPLACE_TABLE, columns, null,
				null, null, null, null);
		ArrayList<String>  result = new ArrayList<String>();

		//int iRow = cursor.getColumnIndex(KEY_ROW_ID);
		int iName = cursor.getColumnIndex(KEY_NAME);
//		int iPosition = cursor.getColumnIndex(KEY_POSITION);
		int iBack = cursor.getColumnIndex(KEY_BACK);
		int iInterview = cursor.getColumnIndex(KEY_SET_INTERVIEW);
		int iContact = cursor.getColumnIndex(KEY_CONTACT_THEM);
		int iDate = cursor.getColumnIndex(KEY_DATE_INTERVIEW);
	//	int iHour = cursor.getColumnIndex(KEY_HOUR_INTERVIEW);
		
		
		
		int i = 0 ;
		for (cursor.moveToFirst(); i <cursor.getCount(); cursor
				.moveToNext()) {

			String row = cursor.getString(iName)+ " "+
					cursor.getString(iBack)+" "+
					cursor.getString(iInterview)+" "+
					cursor.getString(iContact)+" "+
					cursor.getString(iDate)+" "+
				/*	cursor.getString(iHour)+*/"\n";
			result.add(i, row);

			i++;
		}
		return result;
	}
	


}
