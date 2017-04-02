package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import usc.com.uscmaps.example1.shubham.parentchildconvo.ExpandableView.ExpandingActivity;

/**
 * Created by Shubham on 3/21/17.
 */

public class MessageMainListAdapter extends RecyclerView.Adapter<MessageMainListAdapter.BuildingViewHolder> {

    private List<MessageData> messageData = Collections.emptyList();
    private Context mContext;
    private static ListItemClickListener mlistItemClickListener;
    private static final String TAG = MessageMainListAdapter.class.getSimpleName();
    ArrayList<Integer> arr;

    //    public MessageMainListAdapter(JSONObject jsonObj, Context context, ListItemClickListener listener) {
     MessageMainListAdapter(List<MessageData> messageData, Context context) {
        this.messageData = messageData;
        this.mContext = context;
//        this.mlistItemClickListener = listener;
    }

    @Override
    public BuildingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.message_list_item, parent, false);
        return new BuildingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BuildingViewHolder holder, int position) {

        try {
//            Log.e(TAG, "length: "+jsonObj.length());
            BuildingViewHolder myHolder =  holder;
            MessageData current = messageData.get(position);




            /**
             * Subject is set here. Max length condition and hence adding "..." m
             */
            String subjectContent = current.getSubject();
            if (subjectContent.length() >= 30) {
                String temp = subjectContent.substring(0, 30) + "...";
                myHolder.bTextViewSubject.setText(temp);
            } else {
                myHolder.bTextViewSubject.setText(subjectContent);
            }


            //Use the first json of the message_thread to set the UI
            List<MessagesThread> messages_thread = current.getMessages();


            /**
             * Sender Name is set here. Max length condition and hence adding "..." m
             */
            String senderName = messages_thread.get(0).getSender_name();
//            Log.e(TAG, senderName);
            if (senderName.length() >= 30) {
                String temp = senderName.substring(0, 30) + "...";
                myHolder.bTextViewSenderName.setText(temp);
            } else {
                myHolder.bTextViewSenderName.setText(senderName);
            }

            /**
             * Text is set here. Max length condition and hence adding "..." m
             */
            String messageContent = messages_thread.get(0).getText();
            if (messageContent.length() >= 30) {
//                Log.e(TAG, "vugkhjkhjb");
                String temp = messageContent.substring(0, 30) + "...";
                myHolder.bTextViewMessage.setText(temp);
            } else {
                myHolder.bTextViewMessage.setText(messageContent);
            }


            /**
             * Star Image is set here.
             */
            if (current.getIs_starred().equals("true")) {
                myHolder.bImageViewStarred.setImageResource(R.mipmap.starred);
            } else {
                myHolder.bImageViewStarred.setImageResource(R.mipmap.unstarred);

            }

            /**
             * Sender Image is set here.
             * Used CircleImageView from hdodenhof/CircleImageView(Github)
             *
             * Using TextDrawable from amulyakhare/TextDrawable(Github)
             *
             * Rule- if the first message inside the message thread is has null sender_image,
             * then I put up a image with the first character of the sender's name. Else I set the
             * image as the server's passed URL
             */

            if(messages_thread.get(0).getSender_image().equals("") || messages_thread.get(0).getSender_image() == null){

                // generate random color
                ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
                int color1 = generator.getRandomColor();

                TextDrawable drawable = TextDrawable.builder()
                        .buildRound(senderName.substring(0,1).toUpperCase(), color1);
                myHolder.bImageViewSenderImage.setImageDrawable(drawable);
            }
            else{
                Picasso.with(mContext)
                        .load(messages_thread.get(0).getSender_image())
                        .transform(new CircleTransform())
                        .into(myHolder.bImageViewSenderImage);
            }

            /**
             * Timestamp, single line check still required
             */
//            Log.e(TAG, "messages_thread: "+messages_thread.get(0).getTimestamp());

            String dtStart1 = messages_thread.get(0).getTimestamp();
            SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            Date datee = null;
            try {
                datee = format1.parse(dtStart1);
            } catch (ParseException e) {
                Log.e(TAG, "Inside catch Timestamp");
                e.printStackTrace();
            }

            SimpleDateFormat dateToStringformat = new SimpleDateFormat("MMM dd");
            try {
                String datetime = dateToStringformat.format(datee);
//                Log.e(TAG, "Current Date Time : " + datetime);
                myHolder.bTextViewTimestamp.setText(datetime);
            } catch (Exception e) {
                Log.e(TAG, "Inside catch Timestamp");
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return messageData.size();
//        return mCursor.getCount();
    }


    class BuildingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bTextViewSenderName;
        TextView bTextViewSubject;
        TextView bTextViewMessage;
        TextView bTextViewTimestamp;
        ImageView bImageViewSenderImage;
        ImageView bImageViewStarred;

        public BuildingViewHolder(View itemView) {
            super(itemView);
            bTextViewSenderName = (TextView) itemView.findViewById(R.id.sender_name);
            bTextViewSubject = (TextView) itemView.findViewById(R.id.subject);
            bTextViewMessage = (TextView) itemView.findViewById(R.id.email_body);
            bTextViewTimestamp = (TextView) itemView.findViewById(R.id.timestamp);
            bImageViewStarred = (ImageView) itemView.findViewById(R.id.star);
            bImageViewSenderImage = (ImageView) itemView.findViewById(R.id.sender_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Log.e(TAG, "clicked " + adapterPosition);
//            Intent intent =  new Intent(this, ExpandingActivity.class);
            Intent intent =  new Intent(mContext, ExpandingActivity.class);
            mContext.startActivity(intent);

//            mCursor.moveToPosition(adapterPosition);
//            String selectedString = mCursor.getString(mCursor.getColumnIndex(WaitListContract.WaitListEntry.COLUMN_BUILDING_NAME));
//            mlistItemClickListener.onClick(selectedString);


        }
    }


    /**
     * Creating a click listener for each item in Recycler View
     */
    public interface ListItemClickListener {
        void onClick(String weatherForDay);
    }


    /**
     * Used with PIcasso to set the userImages as Round shaped.
     */
    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size/2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}
