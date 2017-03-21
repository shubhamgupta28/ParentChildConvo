package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by Shubham on 3/20/17.
 */

public class Tab2Gallery extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GridView gridview = (GridView) this.getActivity().findViewById(R.id.photogridview);
//        gridview.setAdapter(new PhotoImageAdapter(this.getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootview = inflater.inflate(R.layout.tab2_gallery, container, false);
//        return rootview;
        View view = inflater.inflate(R.layout.tab2_gallery,container,false);
        GridView gridView = (GridView) view.findViewById(R.id.photogridview);
        gridView.setAdapter(new PhotoImageAdapter(view.getContext())); // uses the view to get the context instead of getActivity().
        return view;

    }
}