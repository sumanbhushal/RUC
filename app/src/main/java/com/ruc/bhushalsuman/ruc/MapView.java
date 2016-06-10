package com.ruc.bhushalsuman.ruc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by Bhushal Suman on 3/23/2016.
 */
public class MapView extends View implements Runnable {

    private Paint userLocation;

    private Thread animationThread = new Thread(this);
    private boolean animationRunning;

    private float px;
    private float py ;

    int dHeight;
    int dWidth;

    ImageLoader imageLoader;
    boolean showLocation = false;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundResource(R.drawable.rucmap);
       /* Resources rec = getResources();
        setBackgroundResource(imageLoader.decodeSampledBitmapFromResource(getResources(),R.drawable.rucmap,dHeight,dWidth));
        */

        userLocation = new Paint();
        userLocation.setColor(Color.RED);
        userLocation.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(showLocation)
        canvas.drawCircle(px, py, 10, userLocation);

    }


    @Override
    public void run() {
        try {
            while (animationRunning){
                postInvalidate();
                Thread.sleep(5);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public void stopAnimation() {
        animationRunning = false;
        while (true) {
            try {
                animationThread.join();
                animationThread = null;
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startAnimation() {
        if (animationThread == null)
            animationThread = new Thread(this);
        if (!animationThread.isAlive())
            //animationThread.start();
        animationRunning = true;
    }


    public void setLocationCoordinates(double latitude, double longitude, int deviceHeight, int deviceWidth){

        dHeight = deviceHeight;
        dWidth = deviceWidth;

        double topLeftLat = 55.655350;
        double topLeftLong = 12.134900;
        double topRightLat = 55.655301;
        double topRightLong = 12.143910;
        double lowLat = 55.649260;
        double lowLong = 12.133509;

        double studentCurrentPositionLatitude = latitude;
        double studentCurrentPositionLongitude = longitude;

        double userXCoordinate = (dWidth * (studentCurrentPositionLongitude - topLeftLong))/ (topRightLong - topLeftLong);
        double userYCoordinate = (dHeight * (topLeftLat - studentCurrentPositionLatitude))/ (topLeftLat - lowLat);

        Log.d("x coordinate value", ""+userXCoordinate);
        Log.d("Y coordinate Value", "" + userYCoordinate);
        px = (float) userXCoordinate;
        py = (float) userYCoordinate;

        //update the view itself
        postInvalidate();
    }

    public void setShowLocation(){
        showLocation = true;
    }
}
