package come.jobfinder.dev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailCompany extends Activity implements View.OnClickListener {

	EditText companyEmail, intro, personsNameField, 
			outro , userName , positionField;
	String emailAdd, position,  actions, acts, out , personName;
	Button sendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initializeVars();
		sendEmail.setOnClickListener(this);
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		companyEmail = (EditText) findViewById(R.id.etEmails);
		personsNameField = (EditText) findViewById(R.id.etName);
		sendEmail = (Button) findViewById(R.id.bSentEmail);
		positionField = (EditText) findViewById(R.id.etPosition);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	
		convertEditTextVarsIntoStrings();
		String emailaddress[] = { emailAdd };
		String message = "Hi "
				
				+ " Attach my CV for the position "
				+ position+"\n\n"
				+"Thanks\n"			
				+ personName
				+ '\n';
		
		
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Attach "+personName+" CV");
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
	
	}

	private void convertEditTextVarsIntoStrings() {
		// TODO Auto-generated method stub
		emailAdd = companyEmail.getText().toString();
		position = positionField.getText().toString();
		personName = personsNameField.getText().toString(); 
	//	out = outro.getText().toString();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}