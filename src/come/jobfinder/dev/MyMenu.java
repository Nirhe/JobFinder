package come.jobfinder.dev;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import come.jobfinder.dev.R;

public class MyMenu extends ListActivity {

	private String classes [] = {"AddWorkPlace","ListOfWorkPlaces","EmailCompany"};
	private String displayClasses [] = {"Add Work Place" ,"List Of Work Places" ,"Email Company"}; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// to make it full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setListAdapter(new ArrayAdapter<String>(MyMenu.this, android.R.layout.simple_list_item_1, this.displayClasses));
	}
	
	
	


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		super.onListItemClick(l, v, position, id);
		
		String localClass = classes[position];
		Class ourClass;
		try {
			ourClass = Class
					.forName("come.jobfinder.dev."+localClass);
			Intent intent = new Intent(MyMenu.this, ourClass);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.upgraded_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int itemId = item.getItemId();
		if (itemId == R.id.aboutUs) {
			Intent i = new Intent("come.jobfinder.dev.ABOUT");
			startActivity(i);
		} else if (itemId == R.id.preferences) {
			Intent p = new Intent("come.jobfinder.dev.PREFS");
			startActivity(p);
		} else if (itemId == R.id.exit) {
			finish();
		}
		
		return false;

	}






}
