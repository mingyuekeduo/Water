package sam.test.waterfall;

import java.io.InputStream;

import android.R.integer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View;

public class WaterfallActivity extends Activity implements View.OnClickListener
{
    private  LinearLayout linearLayout1 = null;
    private  LinearLayout linearLayout2 = null;
    private  LinearLayout linearLayout3 = null;
    private int USE_LINEAR_INTERVAL = 0;
    private int linearlayoutWidth = 0;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        linearLayout1 = (LinearLayout)findViewById(R.id.main_linearlayout1);
        linearLayout2 = (LinearLayout)findViewById(R.id.main_linearlayout2);
        linearLayout3 = (LinearLayout)findViewById(R.id.main_linearlayout3);
       linearlayoutWidth =  getWindowManager().getDefaultDisplay().getWidth()/3;
        addBitmaps();
    }
    
    private void addBitmaps()
    {
    	int index =0;
    	try {
    		String filepaths[] = getResources().getAssets().list("images");
    		for(String string:filepaths)
    		{
    			try {
    				InputStream inputStream = getResources().getAssets().open("images/"+string);
    				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
    				Bitmap bitmap2 = BitmapZoom.bitmapZoomByWidth(bitmap, linearlayoutWidth);
    				ImageView imageView = new ImageView(this);
    				imageView.setImageBitmap(bitmap);
    				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(bitmap2.getWidth(), bitmap2.getHeight());
    				imageView.setLayoutParams(layoutParams);
    				imageView.setOnClickListener(this);
    				imageView.setTag(new Integer(index));
    				switch (USE_LINEAR_INTERVAL) 
    				{
						case 0:
							linearLayout1.addView(imageView);
							break;
						case 1:
							linearLayout2.addView(imageView);
							break;
						case 2:
							linearLayout3.addView(imageView);
							break;
						default:
							break;
					}
    				index++;
    				USE_LINEAR_INTERVAL++;
    				USE_LINEAR_INTERVAL= USE_LINEAR_INTERVAL%3;
    				inputStream.close();
    				
				} catch (Exception e) {
				}
    			
    			
    		}
    		

		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    @Override
    public void onClick(View v) 
    {
    	int index  =  (Integer)v.getTag();
    	System.out.println("click index= "+index);
    }
    
    
}