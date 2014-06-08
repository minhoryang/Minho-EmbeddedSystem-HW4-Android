package minhoryang.omgingerbread;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.media.MediaPlayer;

@EActivity(R.layout.musicactivity)
public class MusicActivity extends Activity {
	MediaPlayer a = null;
	boolean isPause;

	@Click
	void PlayMusic(){
		if(a == null){
			a = MediaPlayer.create(this, R.raw.music);
			a.start();
			isPause = false;
		}else{
			a.stop();
			a = null;
			PlayMusic();
		}
	}
	
	@Click
	void PauseMusic(){
		if(a != null)
			if(isPause){
				isPause = false;
				a.start();
			}else{
				isPause = true;
				a.pause();
			}
	}
	
	@Override
	public void onBackPressed(){
		if(a != null)
			a.stop();
		super.onBackPressed();

	}
	
	@Click
	void ReturnToMain(){
		//startActivity(new Intent(MusicActivity.this, MainActivity_.class));
		this.onBackPressed();
	}
}
