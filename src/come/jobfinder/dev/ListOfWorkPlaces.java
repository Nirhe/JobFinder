package come.jobfinder.dev;

import java.util.ArrayList;
import java.util.Collections;




import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;


public class ListOfWorkPlaces extends ListActivity implements OnMenuItemClickListener {

	private ArrayList<String> results;
	private ListAdapter arrayAdapter;
	private  int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workplacelisttot);
		SQLwrapperBasicTable entry = new SQLwrapperBasicTable(ListOfWorkPlaces.this);
	
		try{
		entry = entry.open();
		this.results = entry.getData();	
		}
		catch (SQLException e)
		{
			throw new RuntimeException("Couldn't get data from db",e);
		}
		finally{
			entry.close();
		}
		Collections.sort(this.results,String.CASE_INSENSITIVE_ORDER);
		this.arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results);
		this.setListAdapter(arrayAdapter); 

	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	super.onListItemClick(l, v, position, id);

		PopupMenu popupMenu = new PopupMenu(this, v);
		setSelectionPoistion(position);
		MenuInflater inflater = popupMenu.getMenuInflater();
		inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
		popupMenu.show();
		popupMenu.setOnMenuItemClickListener(this);
	
	}



	@Override
	public boolean onMenuItemClick(MenuItem item) {
		
		int itemId = item.getItemId();
		if (itemId == R.id.Open) {
		} else if (itemId == R.id.Update) {
			Intent updateIntent = new Intent(ListOfWorkPlaces.this,UpdateWorkPlace.class);
			String workplace = this.results.get(this.position);
			Bundle bundle = new Bundle();
			bundle.putString("workplace", workplace);
			updateIntent.putExtras(bundle);
			startActivity(updateIntent);
			return true;
		} else if (itemId == R.id.Delete) {
		}
		return false;
	}
	


	private void setSelectionPoistion(int position) {
			this.position = position;
		
	}


	
	

}
