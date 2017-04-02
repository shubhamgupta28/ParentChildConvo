//package usc.com.uscmaps.example1.shubham.parentchildconvo;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
//import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
//import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
//import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
//
//import java.util.List;
//
///**
// * Created by Shubham on 3/29/17.
// */
//
//public class MessageSelectedThreadedAdapter extends ExpandableRecyclerAdapter<MessageSelectedThreadedAdapter.CrimeParentViewHolder, MessageSelectedThreadedAdapter.CrimeChildViewHolder> {
//    private Context mContext;
//    private LayoutInflater mInflater;
//
////    MessageSelectedThreadedAdapter(Context context ){
////
////
////
////    }
//
//    public MessageSelectedThreadedAdapter(Context context, List<ParentObject> parentItemList) {
//        super(context, parentItemList);
//        mContext = context;
//        mInflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public CrimeParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
//        View view = mInflater.inflate(R.layout.message_thread_list_item_parent, viewGroup, false);
//        return new CrimeParentViewHolder(view);
//    }
//
//    @Override
//    public CrimeChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
//        View view = mInflater.inflate(R.layout.message_thread_list_item_child, viewGroup, false);
//        return new CrimeChildViewHolder(view);
//    }
//
//    @Override
//    public void onBindParentViewHolder(CrimeParentViewHolder crimeParentViewHolder, int i, Object o) {
//        Crime crime = (Crime) o;
//        crimeParentViewHolder.mCrimeTitleTextView.setText(crime.getTitle());
//    }
//
//    @Override
//    public void onBindChildViewHolder(CrimeChildViewHolder crimeChildViewHolder, int i, Object o) {
//        CrimeChild crimeChild = (CrimeChild) o;
//        crimeChildViewHolder.mCrimeDateText.setText(crimeChild.getDate().toString());
////        crimeChildViewHolder.mCrimeSolvedCheckBox.setChecked(crimeChild.isSolved());
//
//    }
//
//    public class CrimeParentViewHolder extends ParentViewHolder {
//
//        public TextView mCrimeTitleTextView;
//        public ImageButton mParentDropDownArrow;
//
//        public CrimeParentViewHolder(View itemView) {
//            super(itemView);
//
//            mCrimeTitleTextView = (TextView) itemView.findViewById(R.id.parent_list_item_crime_title_text_view);
//            mParentDropDownArrow = (ImageButton) itemView.findViewById(R.id.parent_list_item_expand_arrow);
//        }
//    }
//
//    public class CrimeChildViewHolder extends ChildViewHolder {
//
//        public TextView mCrimeDateText;
//        public CheckBox mCrimeSolvedCheckBox;
//
//        public CrimeChildViewHolder(View itemView) {
//            super(itemView);
//
//            mCrimeDateText = (TextView) itemView.findViewById(R.id.child_list_item_crime_date_text_view);
//            mCrimeSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.child_list_item_crime_solved_check_box);
//        }
//    }
//}