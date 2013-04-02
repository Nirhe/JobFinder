package come.jobfinder.dev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import come.jobfinder.dev.R;

public class JobFinderActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();

				} finally {
					Intent openStartingPoint = new Intent("come.jobfinder.dev.MENU");
					startActivity(openStartingPoint);
				}

			}
		};
		timer.start();
    }
    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}





