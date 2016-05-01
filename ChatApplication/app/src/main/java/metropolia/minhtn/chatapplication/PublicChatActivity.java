package metropolia.minhtn.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.WebSocket;

import metropolia.minhtn.chatapplication.constants.ApiUrl;
import metropolia.minhtn.chatapplication.service.MessageObserver;

public class PublicChatActivity extends AppCompatActivity implements MessageObserver {

    WebSocket publicSocket;
    EditText txtMsg;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_chat);

        txtMsg = (EditText)findViewById(R.id.txtMessage);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(publicSocket != null) {
                    publicSocket.send(txtMsg.getText().toString());
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
                publicSocket.send("Hello I am newb from here");


                webSocket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.d("Msg","Something happneded");
                        bb.recycle();
                    }
                });

                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(String s) {
                        Log.d("msss",s);
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

    }

}
