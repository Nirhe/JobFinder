package come.jobfinder.dev;


import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class UpdateWorkPlace extends Activity implements OnClickListener  , OnCheckedChangeListener{

	private final static String WORKPLACE = "workplace";
	private CheckBox contactThem;
	private CheckBox backToMe;
	private CheckBox setInterview;
	private DatePicker interviewDate;
	private TimePicker interviewHour;
	private Button update;
	private TextView textDate;
	private TextView textHour;
	private Bundle bundle;
	private SQLwrapperUpdateTable query = new SQLwrapperUpdateTable(UpdateWorkPlace.this);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateworkplace);
		init();
		this.setInterview.setOnCheckedChangeListener(this);
		this.update.setOnClickListener(this);
	
		
	}

	

	private void init() {

		this.contactThem = (CheckBox) findViewById(R.id.cbContact);
		this.backToMe = (CheckBox) findViewById(R.id.cbBackToMe);
		this.setInterview = (CheckBox) findViewById(R.id.cbInterview);
		this.interviewDate = (DatePicker) findViewById(R.id.dpInterviewDate);
		this.interviewHour = (TimePicker) findViewById(R.id.tpInterviewHour);
		this.textDate = (TextView) findViewById(R.id.tvDate);
		this.textHour = (TextView) findViewById(R.id.tvHour);
		this.update = (Button)findViewById(R.id.bUpdate);
		this.bundle = getIntent().getExtras();
		setTitle(this.bundle.getCharSequence(WORKPLACE));
		
	}

	@Override
	public void onClick(View v) {
		
		Boolean contactThem , backToMe , setInterview;
		String workplace = this.bundle.getCharSequence(WORKPLACE).toString();
		
		//getting the check boxes
		contactThem = this.contactThem.isChecked();
		backToMe = this.backToMe.isChecked();
		setInterview = this.setInterview.isChecked();

		Calendar date = prepareInterviewDate();
		
		
		boolean worked = true;
		
		try {
			this.query.open();
			this.query.createEntry(workplace, backToMe.toString(),
					contactThem.toString(), setInterview.toString(),
					date.getTimeInMillis());
		} catch (SQLException e) {
			worked = false;
			showDialog(e.getMessage());

		} finally {
			if(worked){
			 showDialog(workplace+" was updated successfully");
			 this.query.close();
			}
		}
		
	}
	
	
	private Calendar prepareInterviewDate() {
		
		int dayInMonth  , month,  year;
		Integer hour , minute;
		// get the date of the interview
		dayInMonth = this.interviewDate.getDayOfMonth();
		month = this.interviewDate.getMonth();
		year = this.interviewDate.getYear();
		hour = this.interviewHour.getCurrentHour();
		minute = this.interviewHour.getCurrentMinute();		
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, year);	
		date.set(Calendar.MONTH, month);
		date.set(Calendar.DAY_OF_MONTH,dayInMonth);
		date.set(Calendar.HOUR,hour);
		date.set(Calendar.MINUTE,minute);
		
	
		return date;
	}



	private void showDialog( String message) {
		Dialog dialog = new Dialog(this);
		TextView textView = new TextView(this);
		textView.setText(message);
		dialog.setContentView(textView);
		dialog.show();
		
	}
	

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
			if(isChecked){
			setCalendarVisible(View.VISIBLE);
			}
			else{
			setCalendarVisible(View.INVISIBLE);
			}
				
	}
	

	private void setCalendarVisible(int visiblity) {
		

		this.textDate.setVisibility(visiblity);
		this.textHour.setVisibility(visiblity);
		this.interviewDate.setVisibility(visiblity);
		this.interviewHour.setVisibility(visiblity);

		
		}
	
	



}
