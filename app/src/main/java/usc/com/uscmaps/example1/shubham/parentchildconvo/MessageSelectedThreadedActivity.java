package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;

/**
 * Created by Shubham on 3/29/17.
 *
 * https://www.bignerdranch.com/blog/expand-a-recyclerview-in-four-steps/
 */


public class MessageSelectedThreadedActivity extends AppCompatActivity {

    RecyclerView mCrimeRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_thread_main);
        mCrimeRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_expandable_view);

        MessageSelectedThreadedAdapter mCrimeExpandableAdapter = new MessageSelectedThreadedAdapter(getApplicationContext(), generateCrimes());
        mCrimeExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        mCrimeExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
        mCrimeExpandableAdapter.setParentAndIconExpandOnClick(true);

        mCrimeRecyclerView.setAdapter(mCrimeExpandableAdapter);



//        recyclerView = (RecyclerView) rootview.findViewById(R.id.my_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mCrimeRecyclerView.setLayoutManager(mLayoutManager);
//
//
//        mAdapter = new MessageMainListAdapter(list_message_complete, this.getContext());
//        recyclerView.setAdapter(mAdapter);



    }

//    private ArrayList<ParentObject> generateCrimes() {
////        CrimeLab crimeLab = CrimeLab.get(getActivity());
////        List<Crime> crimes = crimeLab.getCrimes();
//        ArrayList<ParentObject> parentObjects = new ArrayList<>();
//        ArrayList<String> child = new ArrayList<>();
//        child.add("shubham");
//
//
//        ArrayList<Object> childList = new ArrayList<>();
//        childList.add(new CrimeChild(crime.getDate(), crime.isSolved()));
//        crime.setChildObjectList(childList);
//        parentObjects.add(crime);
//
////        parentObjects.add(child);
////        for (Crime crime : crimes) {
////            ArrayList<Object> childList = new ArrayList<>();
////            childList.add(new CrimeChild(crime.getDate(), crime.isSolved()));
////            crime.setChildObjectList(childList);
////            parentObjects.add(crime);
////        }
//        return parentObjects;
//    }

}
