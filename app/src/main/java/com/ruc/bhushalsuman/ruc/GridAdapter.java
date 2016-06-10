package com.ruc.bhushalsuman.ruc;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Bhushal Suman on 4/2/2016.
 */
public class GridAdapter extends BaseAdapter {

    private Context myGridContext;
    private final String [] menuValues;

    //creating Constructor
    public GridAdapter(Context gridContext, String[] menuValues) {
        this.myGridContext = gridContext;
        this.menuValues = menuValues;
    }

    @Override
    public int getCount() {
        return menuValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //creating a new view for each item referenced by the adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myGridContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if(convertView == null){
            gridView = new View(myGridContext);

            //get layout for each menu from gridview_item
            gridView = inflater.inflate(R.layout.gridview_item, null);

            //setting value into textview
            TextView textLabel = (TextView) gridView.findViewById(R.id.grid_item_label);
            Log.d("menu text", textLabel.toString());
            textLabel.setText(menuValues[position]);

            //setting image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            String menuText = menuValues[position];

            ImageLoader imageLoader = new ImageLoader();
            Resources resource = imageView.getResources();

            if(menuText.equals("Map")){
                //imageView.setImageResource(R.drawable.icon_map);
                imageView.setImageBitmap(imageLoader.decodeSampledBitmapFromResource(resource,R.drawable.map_icon,70, 70));
            }else if(menuText.equals("Help")){
                imageView.setImageBitmap(imageLoader.decodeSampledBitmapFromResource(resource,R.drawable.help_icon,70,70));
            }else if(menuText.equals("Settings")){
                imageView.setImageBitmap(imageLoader.decodeSampledBitmapFromResource(resource, R.drawable.setting_icon,70,70));
            }else if (menuText.equals("Logout")){
                imageView.setImageBitmap(imageLoader.decodeSampledBitmapFromResource(resource, R.drawable.logout, 70, 70));
            }else{
                imageView.setImageBitmap(imageLoader.decodeSampledBitmapFromResource(resource,R.drawable.schedule_icon,70, 70));
            }
        }else{
            gridView = (View) convertView;
        }

        return gridView;
    }
}
