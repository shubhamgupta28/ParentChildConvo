package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shubham on 3/9/17.
 */

public class GalleryFragment extends Fragment {

    private FragmentTabHost mTabHost;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.gallery, container, false);
//
//        // Create the adapter that will return a fragment for each of the three
//        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) rootView.findViewById(R.id.container);
//        mViewPager.setOffscreenPageLimit(0);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
////        mViewPager.setCurrentItem(1);
//
//        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);
//
//        return rootView;




        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);



        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Gallery"),
                Tab2Gallery.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Albums"),
                Tab1Albums.class, null);

        return rootView;



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }

}
