package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends Fragment {
    private String TAG = getClass().getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("Fdf", ":Dfd");
        View rootview = inflater.inflate(R.layout.message_main, container, false);

        String jso = "{  'response' : [    {      'is_starred'  : 'true',      'subject'   : 'Youve been accepted',      'messages_thread' : [        {          'sender_id'     : '1',          'sender_name'   : 'HackCU',          'sender_email'  : 'hack@usc.edu',          'sender_image'  : 'http://random-international.com/wp-content/uploads/2012/10/RR-home-img2.jpg',          'text'      : 'Congratulations, you have been accepted to TrojanHacks 2.0! Were excited to have you join us at a weekend of hacking and learning, built around the idea of giving back to the Trojan community. Check-in is from 9am to 10am this Saturday, March 24th, in King Hall Main Area. If youre looking for a team, arrive early to meet fellow hackers and discuss ideas before the opening ceremony. Bring anything and everything you need to turn your ideas into reality!',          'attachments'     : [                  {                    'type' : 'svg',                    'name' : 'attachment',                    'download_url' : 'www.google.com',                    'size' : '555'                  }                ],          'timestamp'     : '03/24/2017 07:21',          'sent_to'     : [            {              'id' : '1',              'name' : 'Shubham Gupta',              'email' : 'guptashu@usc.edu',              'image_url' : 'http://www.hbc333.com/data/out/193/47081647-random-picture.png'            }         ]        }      ]    },    {      'is_starred'  : 'false',      'subject'   : 'Result Day',      'messages_thread' : [        {          'sender_id'     : '1',          'sender_name'   : 'getReveel',          'sender_email'  : 'getReveel@reveel.com',          'sender_image'  : '',          'text'      : 'Hey Max, Im on way right now, but am stuck in traffic. Will be there in 15 minutes hopefully. Apologies.',          'attachments'     : [                  {                    'type' : 'png',                    'name' : 'attachment',                    'download_url' : 'www.google.com',                    'size' : '555'                  }                ],          'timestamp'     : '03/21/2017 08:21',          'sent_to'     : [            {              'id' : '1',              'name' : 'Shubham Gupta',              'email' : 'strguptashu@usc.eduing',              'image_url' : 'http://www.hbc333.com/data/out/193/47081647-random-picture.png'            }          ]        },        {          'sender_id'     : '2',          'sender_name'   : 'Max',          'sender_email'  : 'max@usc.edu',          'sender_image'  : '',  'text'      : 'Yoyoyoyoyoy Max, Im on way right now, but am stuck in traffic. Will be there in 15 minutes hopefully. Apologies.',        'attachments'     : [                  {                    'type' : 'jpeg',                    'name' : 'attachment',                    'download_url' : 'www.google.com',                    'size' : '33'                  }                ],          'timestamp'     : '03/21/2017 06:21',          'sent_to'     : [            {              'id' : '1',              'name' : 'Shubham Gupta',              'email' : 'strguptashu@usc.eduing',              'image_url' : 'http://www.hbc333.com/data/out/193/47081647-random-picture.png'            }          ]        }      ]    }  ]}  ";

        try {
            JSONObject obj = new JSONObject(jso);
//            Log.e(TAG, ""+obj.length());

            JSONArray response_data = obj.getJSONArray("response");
//            Log.e(TAG, ""+response_data.length());

            List<MessageData> list_message_complete = new ArrayList<>();
            MessageData messageData;
            for (int i = 0; i < response_data.length(); i++) {
                messageData = new MessageData();

//                Log.e(TAG, "response_data: " + response_data);
                JSONObject message_array = response_data.getJSONObject(i);
//                Log.e(TAG, "message_array: "+message_array);
//                Log.e(TAG, "message_array Length: "+message_array.length());
//                Log.e(TAG, "message_array loop: " + message_array.getString("is_starred"));
                messageData.setIs_starred(message_array.getString("is_starred"));
                messageData.setSubject(message_array.getString("subject"));

                JSONArray message_thread = message_array.getJSONArray("messages_thread");
//                Log.e(TAG, ""+message_thread);

                List<MessagesThread> list_messages_thread = new ArrayList<>();
                MessagesThread messageThreadObject;
                for (int j = 0; j < message_thread.length(); j++) {
                    JSONObject message_json = message_thread.getJSONObject(j);
//                    Log.e(TAG, "message_json: "+message_json);

                    messageThreadObject = new MessagesThread();
                    messageThreadObject.setSender_id(message_json.getString("sender_id"));
                    messageThreadObject.setSender_name(message_json.getString("sender_name"));
                    messageThreadObject.setSender_email(message_json.getString("sender_email"));
                    messageThreadObject.setSender_image(message_json.getString("sender_image"));
                    messageThreadObject.setText(message_json.getString("text"));
                    messageThreadObject.setTimestamp(message_json.getString("timestamp"));

                    Attachment attachment_obj;
                    JSONArray attachments_array = message_json.getJSONArray("attachments");
                    List<Attachment> list_attachment = new ArrayList<>();
                    for (int k = 0; k < attachments_array.length(); k++) {
                        JSONObject attachments_item = attachments_array.getJSONObject(k);
//                        Log.e(TAG, "attachments_item"+attachments_item);

                        attachment_obj = new Attachment();
                        attachment_obj.setName(attachments_item.getString("type"));
                        attachment_obj.setName(attachments_item.getString("name"));
                        attachment_obj.setName(attachments_item.getString("download_url"));
                        attachment_obj.setName(attachments_item.getString("size"));
                        list_attachment.add(attachment_obj);
                    }
                    messageThreadObject.setAttachments(list_attachment);

                    SentTo sentToObj;
                    JSONArray sent_to_array = message_json.getJSONArray("sent_to");
                    List<SentTo> list_sentTo = new ArrayList<>();
                    for (int l = 0; l < sent_to_array.length(); l++) {
                        JSONObject sent_to_item = sent_to_array.getJSONObject(l);
//                        Log.e(TAG, "sent_to_item: "+sent_to_item);


                        sentToObj = new SentTo();
                        sentToObj.setId(sent_to_item.getString("id"));
                        sentToObj.setName(sent_to_item.getString("name"));
                        sentToObj.setEmail(sent_to_item.getString("email"));
                        sentToObj.setId(sent_to_item.getString("image_url"));
                        list_sentTo.add(sentToObj);
                    }
                    messageThreadObject.setSent_to(list_sentTo);

                    list_messages_thread.add(messageThreadObject);
                }
                messageData.setMessages(list_messages_thread);
//                Log.e("--------" ,  ""+messageData);
                list_message_complete.add(messageData);
            }

            recyclerView = (RecyclerView) rootview.findViewById(R.id.my_recycler_view);
            mLayoutManager = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(mLayoutManager);


            mAdapter = new MessageMainListAdapter(list_message_complete, this.getContext());
            recyclerView.setAdapter(mAdapter);


        } catch (JSONException e) {
            Log.e(TAG, "Exception " + TAG + e);
            e.printStackTrace();
        }

        return rootview;
    }

    private void setMessageHomeScreen(JSONArray firstElement) {
        try {
            Log.e(TAG, "a:" + firstElement.get(0));
            for (int i = 0; i < firstElement.length(); i++) {
                JSONObject children1 = firstElement.getJSONObject(i);
                Log.e("curr_user_children_" + i, "" + children1.getString("sender_id"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
