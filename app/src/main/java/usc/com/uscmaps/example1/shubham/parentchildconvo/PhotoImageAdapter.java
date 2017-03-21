package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Shubham on 3/20/17.
 */


public class PhotoImageAdapter extends BaseAdapter {
    private Context mContext;

    public PhotoImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        int height = parent.getHeight();
        ImageView imageView;
        imageView = new ImageView(mContext);

//        imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setPadding(8,8,8,8);
        imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    private Integer[] mThumbIds = {
            R.mipmap.sample_2, R.mipmap.sample_3,
            R.mipmap.sample_2, R.mipmap.sample_4,
            R.mipmap.sample_2, R.mipmap.sample_3,
            R.mipmap.sample_2, R.mipmap.sample_3
    };


}