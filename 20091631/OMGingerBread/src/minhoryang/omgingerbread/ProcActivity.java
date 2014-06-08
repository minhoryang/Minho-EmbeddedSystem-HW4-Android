package minhoryang.omgingerbread;

import java.io.IOException;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

@EActivity(R.layout.procactivity)
public class ProcActivity extends Activity {
	@Bean
	ProcParser myProc;
	
	@ViewById
	TextView ProcView;
	
	@Click
	void ProcRead(){
		try {
			ProcView.setText(ProcReader.read(false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	boolean isClicked = false;
	@Click
	void ProcRun(){
		if(!isClicked){
			Toast.makeText(this, "Proc Parser Run @ Here!", Toast.LENGTH_SHORT).show();
			myProc.execute((Void [])null);
			isClicked = true;
		}
	}
	
	@Click
	void ProcStop(){
		myProc.cancel(false);
		this.onBackPressed();
	}
}
