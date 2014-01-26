package eu.itcsolutions.android.tutorial;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//implement the OnClickListener interface
public class MainActivity extends Activity 
	implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
		Button button;
        button = (Button) findViewById(R.id.buttonClick2);
		button.setBackgroundResource(R.drawable.pp2);
    	//get the Button reference
    	//Button is a subclass of View 
    	//buttonClick if from main.xml "@+id/buttonClick"
        View btnClick = findViewById(R.id.buttonClick);
        //set event listener
        btnClick.setOnClickListener(this);
        View btnClick2 = findViewById(R.id.buttonClick2);
        //set event listener
        btnClick2.setOnClickListener(this);
    }

    //override the OnClickListener interface method
	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.buttonClick){
			//define a new Intent for the second Activity
			Intent intent = new Intent(this,SecondActivity.class);
			//start the second Activity
			this.startActivity(intent);
		}
		else if(arg0.getId() == R.id.buttonClick2)
		{
			Intent intent = new Intent(this,ThirdActivity.class);
			//start the second Activity
			this.startActivity(intent);
		}
	}
}