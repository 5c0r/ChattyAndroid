package metropolia.minhtn.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.WebSocket;

import org.json.JSONException;
import org.json.JSONObject;

import metropolia.minhtn.chatapplication.constants.ApiUrl;
import metropolia.minhtn.chatapplication.models.Message;
import metropolia.minhtn.chatapplication.models.Messages;
import metropolia.minhtn.chatapplication.service.MessageAdapter;
import metropolia.minhtn.chatapplication.service.MessageObserver;

public class PublicChatActivity extends AppCompatActivity implements MessageObserver {

    WebSocket publicSocket;
    EditText txtMsg;
    Button btnSend;
    MessageAdapter adapter;
    Messages messages;
    ListView messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_chat);

            // TODO: CLOSE SOCKET WHEN STOPPED
        txtMsg = (EditText)findViewById(R.id.txtMessage);
        btnSend = (Button)findViewById(R.id.btnSend);
        messageList = (ListView)findViewById(R.id.message_list);

        messages = Messages.getInstance();
        messages.registerObservers(this);
        adapter = new MessageAdapter(this,messages);
        messageList.setAdapter(adapter);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(publicSocket != null) {
                    String msgToSend = txtMsg.getText().toString();
                    if(msgToSend.isEmpty() || msgToSend.isEmpty()){
                        Toast.makeText(PublicChatActivity.this, "Please input something", Toast.LENGTH_SHORT).show();
                    }   else {
                        publicSocket.send(msgToSend);
                    }
                    txtMsg.setText("");
                }   else {
                    Toast.makeText(PublicChatActivity.this, "Socket error , please reconnect ?", Toast.LENGTH_SHORT).show();
                }
            }
        });
    if(publicSocket == null ) {
        AsyncHttpClient.getDefaultInstance().websocket(ApiUrl.CHAT,null, new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                if( ex !=null ){
                    ex.printStackTrace();
                    return;
                }

                publicSocket = webSocket;
                Log.d("socketsend","sent something");
                webSocket.send("Tri Nguyen");
//                webSocket.send(new byte[10]);
//                publicSocket.send("Hello I am newb from here");

                // Seems to not working har har har
//                webSocket.setDataCallback(new DataCallback() {
//                    @Override
//                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
//                        Log.d("Msg","Something happneded");
//                        bb.recycle();
//                    }
//                });

                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(String s) {
                        Log.d("msss",s);
                        try {
                            JSONObject json = new JSONObject(s);
                            String message = "";
                            if(json.has("message")){
                                message = json.getString("message");
                            }   else if( json.has("users")){

                            }

                            Log.d("message",message);
                            String[] split = message.split(":");
                            messages.addMessage(new Message(split[0],split[1]));
                            PublicChatActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    messages.notifyObservers();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }   else {
//        publicSocket.send("Tri Nguyen : Test Nguyen");
    }
    }

    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
        messageList.setSelection(messageList.getCount() - 1);
    }

}
