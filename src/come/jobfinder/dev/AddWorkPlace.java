package come.jobfinder.dev;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import come.jobfinder.dev.R;

public class AddWorkPlace extends Activity implements OnClickListener {

	private AutoCompleteTextView acWorkPlace;
	private TextView etAddress;
	private Button bAddWorkPlace;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.addworkplace);
		init();
		this.bAddWorkPlace.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		boolean worked = true;
		String workPlace = this.acWorkPlace.getText().toString();
		String address = this.etAddress.getText().toString();

		SQLwrapperBasicTable entry = new SQLwrapperBasicTable(AddWorkPlace.this);
		try {
			entry.open();
			entry.createEntry(workPlace, address);
		} catch (Exception e) {
			worked = false;
			showDialog(e.getMessage());

		} finally {
			if (worked) {
				showDialog("Workplace was added successfully");
				entry.close();
			}
		}

	
	}

	private void init() {

		this.acWorkPlace = (AutoCompleteTextView) findViewById(R.id.ACworkplace);
		this.etAddress = (TextView) findViewById(R.id.ETAddress);
		this.bAddWorkPlace = (Button) findViewById(R.id.bAddWorkPlace);

	}

	private void showDialog( String message) {
		Dialog dialog = new Dialog(this);
		TextView textView = new TextView(this);
		textView.setText(message);
		dialog.setContentView(textView);
		dialog.show();
		
	}

}
