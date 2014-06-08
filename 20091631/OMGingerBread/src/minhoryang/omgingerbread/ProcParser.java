package minhoryang.omgingerbread;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

@EBean
public class ProcParser extends AsyncTask<Void, Double, Void>{

	@RootContext
	Context parent;
	
	ProcInfo before = null;
	
	@Override
	protected Void doInBackground(Void... params) {
		ProcInfo after = null;
		try{
			before = ProcReader.parse();
		}catch(Exception e){
			this.cancel(false);
		}
		while(!this.isCancelled()){
			try {
				Thread.sleep(2500);
				after = ProcReader.parse();
				if(!this.isCancelled())
					this.publishProgress(after.getPercent(before));
				before = after;
			} catch (Exception e) {
				e.printStackTrace();
				this.cancel(false);
			}
		}
		return null;
	}
	
	protected void onProgressUpdate(Double... duration){
		Toast.makeText(this.parent, Double.toString(duration[0]), 2).show();
	}

}
