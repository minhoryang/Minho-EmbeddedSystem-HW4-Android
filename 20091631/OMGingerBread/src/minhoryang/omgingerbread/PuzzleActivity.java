package minhoryang.omgingerbread;

import java.util.ArrayList;
import java.util.Collections;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

@EActivity(R.layout.puzzleactivity)
public class PuzzleActivity extends Activity{
	
	ArrayList<LinearLayout> rowLayouts = new ArrayList<LinearLayout>();
	ArrayList<Button> buttons = new ArrayList<Button>();

	@ViewById
	LinearLayout main;
	
	@ViewById
	EditText GameSet;
	
	int x, y;
	boolean isStart = false;
	@Click
	void GameStart(){
		if(isStart)
			return ;
		
		try{
			String[] x_y = GameSet.getText().toString().split(" ");
			x = Integer.parseInt(x_y[0]);
			y = Integer.parseInt(x_y[1]);
		}catch(Exception e){
			Error();
			return ;
		}
		if(5 < x || x < 0){
			Error();
			return ;
		}
		if(5 < y || y < 0){
			Error();
			return ;
		}
		
		{  // XXX LAYOUT
			LinearLayout tmp;
			for(int i=0; i<x; i++){
				tmp = new LinearLayout(this);
				tmp.setOrientation(LinearLayout.HORIZONTAL);
				tmp.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f));
				main.addView(tmp);
				rowLayouts.add(tmp);
			}
		}
		{  // XXX BUTTON
			Button tmp;
			for(int i=0; i<x*y; i++){
				tmp = new Button(this);
				if(i!=0)
					tmp.setText(Integer.toString(i));
				else{
					CurrentEmpty = tmp;
					tmp.setText("");
					tmp.setBackgroundColor(getResources().getColor(android.R.color.transparent));
				}
				tmp.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT, 1f));
				tmp.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						if(SwapCheck((Button)v)){
							Swap((Button)v);
							if(isFinish())
								Finish();
						}
					}
				});
				buttons.add(tmp);
			}
		}
		{  // TODO MIX
			Collections.shuffle(buttons);
		}
		{  // TODO DISPLAY
			for(int i=0; i<x*y; i++){
				rowLayouts.get(i%x).addView(buttons.get(i));
			}
		}
		isStart = true;
	}
	
	Button CurrentEmpty;
	
	public boolean SwapCheck(Button Number){
		int now = 0, empty = 0;
		for(int i=0;i<x*y;i++){
			if(buttons.get(i).equals(Number)){
				now = i;
				break;
			}
		}
		for(int i=0;i<x*y;i++){
			if(buttons.get(i).equals(CurrentEmpty)){
				empty = i;
				break;
			}
		}
		if(now-x == empty)
				return true;
		if(now+x == empty)
				return true;
		if(now-1 == empty){
			if(now%x != 0)
				return true;
		}
		if(now+1 == empty){
			if(empty%x != 0)
				return true;
		}
		return false;
	}
	
	public void Swap(Button Number){
		if(Number.equals(CurrentEmpty))
			return ;
		CurrentEmpty.setText(Number.getText());
		Number.setText("");
		CurrentEmpty.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.btn_default));
		Number.setBackgroundColor(getResources().getColor(android.R.color.transparent));
		CurrentEmpty = Number;
	}

	void Error(){
		Toast.makeText(this, "0<x<=5 0<y<=5", Toast.LENGTH_SHORT).show();
	}
	
	boolean isFinish(){
		try{
			for(int i=0;i<x*y-1;i++){
				if(Integer.parseInt((String) buttons.get(i).getText()) == i+1){
					;
				}else{
					return false;
				}
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	void Finish(){
		this.onBackPressed();
	}
}
