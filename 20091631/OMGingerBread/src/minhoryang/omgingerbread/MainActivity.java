package minhoryang.omgingerbread;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import minhoryang.omgingerbread.R;
import android.app.Activity;
import android.content.Intent;

@EActivity(R.layout.mainactivity)
public class MainActivity extends Activity{
	@Click
	void Music(){
		startActivity(new Intent(MainActivity.this, MusicActivity_.class));
	}
	
	@Click
	void Proc(){
		startActivity(new Intent(MainActivity.this, ProcActivity_.class));
	}
	
	@Click
	void Puzzle(){
		startActivity(new Intent(MainActivity.this, PuzzleActivity_.class));
	}
}
