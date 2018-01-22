	package com.example.megadud;
	
	import android.app.Activity;
	import android.graphics.Color;
	import android.graphics.Typeface;
	import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.example.megadud.R;
	import android.os.AsyncTask;
	import java.net.Socket;
	import java.net.UnknownHostException;
import java.io.BufferedReader;
	import java.io.DataInputStream;
import java.io.DataOutputStream;
	import java.io.IOException;
	import java.io.InputStreamReader;
import java.util.regex.*;


	public class megadud extends Activity {
		EditText console;
		EditText input;
		Socket server;
		BufferedReader in;
		DataOutputStream out;
		DataInputStream onee;
		 String holder = new String("");
	    /** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState)   {
    super.onCreate(savedInstanceState);
 setContentView(R.layout.main);
 
 
 console = (EditText) findViewById(R.id.EditText1);
 input= (EditText) findViewById(R.id.editText2);

console.setTextSize(12);
console.setTypeface(Typeface.MONOSPACE);
console.setBackgroundColor(Color.BLACK);
console.setTextColor(Color.WHITE);





try {
	server = new Socket("holodeckbbs.com",23);




in = new BufferedReader(new InputStreamReader(server.getInputStream(), "US-ASCII"));
out = new DataOutputStream(server.getOutputStream());


	




} catch (UnknownHostException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


	       
ReadBuffer buf = new ReadBuffer();
buf.execute();

input.setOnKeyListener(new OnKeyListener(){

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
	
			//System.err.println(input.getText()+"\n\r");
			
		 if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
	             (keyCode           == KeyEvent.KEYCODE_ENTER)   )
	        {        
			 
			 
			 try {
				out.writeChars(input.getText()+"\r");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 input.setText("");
			 
	        	InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
	                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);   
	                return true;
		
		}
		return false;
	}



});
 }





	



	


class WriteBuffer extends AsyncTask<Object, String, Object>{
	

	  
	@Override
	protected Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stub

		  
		  	
		
		while(true)  {
		
}}
	
	
	@Override
	protected void onProgressUpdate(String... arg0) {
		// TODO Auto-generated method stub

		
		return;
	}
	
	
	
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		
		return;
	}
	

   
}

class ReadBuffer extends AsyncTask<Object, String, Object>{

	@Override
	protected Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		
		Pattern pattern = Pattern.compile("\\u001B\\[(\\d+)(?:;(\\d+)(?:;(\\d+))?)?m");
		
		  
        try {
       
        	String test="";
        	

			  String RCP1 ="\u001B]"+ Integer.toString(console.getSelectionStart()) + ";" +Integer.toString(console.getSelectionStart()) + "100R";
		  byte[] RCP = RCP1.getBytes();
		  out.write(RCP);
		  
        while(true)  {
        	
         
              // byte  one;
              /*
                test=in.readLine();
                
                if(test.contains(ANSI.QCP)){
                	
                	System.err.println("QCP");
                	  String RCP1 ="\u001B]"+ Integer.toString(console.getSelectionStart()) + ";" +Integer.toString(console.getSelectionStart()) + "100R";
            		  byte[] RCP = RCP1.getBytes();
            		  
            		  out.write(RCP);
                }
                
            
    		    
                Pattern pattern = Pattern.compile("\\u001B\\[(\\d+)(?:;(\\d+)(?:;(\\d+))?)?m");
            	Matcher matcher = pattern.matcher(test); //public static final String CYAN = "\u001B[36m";
            	
            	if(matcher.find()){
            	System.err.println(matcher.group());}
            	
                        	//	System.err.println(matcher.group(1));
           
            	
             // char test1 = 0;
              
              //  test1=(char) in.read();
               
               // publishProgress(test1+"");
              //  if(test1=='\n')
           //    {
                	//System.err.println("newline achieved");
                	
                	
                	
                    publishProgress(test+"\r");
                    test="";
            //    }
                
             //   test=test+test1;
            //    publishProgress(test);
                
             */   
                char test1=0;
                test1=(char) in.read();
              /*  if(test1=='\u001B' && in.read()=='['){
                	
                
                	
                	
    
                		  
                		  if((test1=(char)in.read())=='6' && (test1=(char)in.read())=='n' ){
                			//  System.err.println((char)in.read());

            				  String RCP1 ="\u001B]"+ Integer.toString(console.getSelectionStart()) + ";" +Integer.toString(console.getSelectionStart()) + "100R";
                		  byte[] RCP = RCP1.getBytes();
                		  out.write(RCP);
                		  
                		  
                		  }
                		  
                		
                }
                */
            	test=test+test1;
               // Pattern pattern = Pattern.compile("\\u001B\\[(\\d+)(?:;(\\d+)(?:;(\\d+))?)?m");
            	   int ansi=0;
             
              //  System.err.println(test1);
             
                if(test1=='\u0020'){
                	
                	
              
                	
                	Matcher matcher = pattern.matcher(test); 
                	
                  	if(matcher.find()){
                  //  	System.err.println(matcher.group());
                //    System.err.println("group(1) " + matcher.group(1));
                //    System.err.println("group(2) " + matcher.group(2));
                 
                    int ansi1 = Integer.parseInt(matcher.group(1));
                    int ansi2 = 0;
                    int ansi3 = 0;
                if(matcher.group(2)!=null){     ansi2 = Integer.parseInt(matcher.group(2)); }
                if(matcher.group(3)!=null){  ansi3 = Integer.parseInt(matcher.group(3)); }
                  	
                    
                    if  (ansi1 >= 30  && ansi1 <= 37){
                    	
                         ansi=ansi1;
                        
                    
                        
                        }
                        else if  (ansi2 >= 30  && ansi2 <= 37){
                        	   ansi=ansi2;
                        	
                      	}
                        
                        else if (ansi3 >= 30  && ansi3 <= 37){
                      	   ansi=ansi3;
                      	
                    	}
                  	}
                  	
                //  	System.err.println("ANSI " +ansi);
                    
              String test2 = matcher.replaceAll("");
                	 publishProgress(test2, Integer.toString(ansi));
                	 test="";
                	 
                }
                
          
                /******************************************************/
              
               
               

        }
       
       
        } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
        } catch (IOException e1) {
                // TODO Auto-generated catch block
                                e1.printStackTrace();
                        }
                	
	
		
	
		return null;
	}
	protected void onProgressUpdate(String... arg0 ) {
		// TODO Auto-generated method stub
		SpannableStringBuilder builder = new SpannableStringBuilder();

		builder.clearSpans();
		builder.append(arg0[0]);
		
		

		
		
//((Spannable) console).setSpan(new ForegroundColorSpan(Color.BLACK), 0,arg0[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


		switch(Integer.parseInt(arg0[1])){
        case 30: builder.setSpan(new ForegroundColorSpan(Color.BLACK), 0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //console.setTextColor(Color.BLACK);
        	break;
        case 31: builder.setSpan(new ForegroundColorSpan(Color.RED), 0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // console.setTextColor(Color.RED);
        break;
        case 32: builder.setSpan(new ForegroundColorSpan(Color.GREEN), 0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //console.setTextColor(Color.GREEN);
        break;
        case 33: builder.setSpan(new ForegroundColorSpan(Color.YELLOW), 0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //console.setTextColor(Color.YELLOW);
        break;
        case 34: builder.setSpan(new ForegroundColorSpan(Color.BLUE), 0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // console.setTextColor(Color.BLUE);
        break;
        case 35: builder.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //console.setTextColor(Color.MAGENTA);
        break;
        case 36: builder.setSpan(new ForegroundColorSpan(Color.CYAN), 0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // console.setTextColor(Color.CYAN);
        break;
        case 37: builder.setSpan(new ForegroundColorSpan(Color.WHITE), 0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // console.setTextColor(Color.WHITE);
        break;
		}
		
		//console.setTextColor(Color.YELLOW);
		//System.err.println(arg0[0]);
	//	console.append(arg0[0]);
		console.append(builder);
        
		return;
	}
	
	
	
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		
		return;
	}
	
	
    
  
}
	
	
	  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {     
	

		  if (keyCode == KeyEvent.KEYCODE_MENU) {  
		  	try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	

		  	android.os.Process.killProcess(android.os.Process.myPid()); 
		  	return true;     } 
		      	return super.onKeyDown(keyCode, event); } 
		      
		  }

