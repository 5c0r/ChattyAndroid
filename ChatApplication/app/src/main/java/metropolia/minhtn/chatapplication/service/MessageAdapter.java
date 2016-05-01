package metropolia.minhtn.chatapplication.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import metropolia.minhtn.chatapplication.R;
import metropolia.minhtn.chatapplication.models.Message;
import metropolia.minhtn.chatapplication.models.Messages;

/**
 * Created by nmt19 on 5/1/2016.
 */
public class MessageAdapter extends BaseAdapter implements ListAdapter {

    private Messages messages;
    private Context ctx;

    public MessageAdapter(Context ctx,Messages messages) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return -1; // TODO : Server-sided ?
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if ( row == null ) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.message_row,parent,false);
        }
        Message m = messages.get(position);

        if( m != null){
            TextView txtName = (TextView)row.findViewById(R.id.sender);
            TextView txtMessage = (TextView)row.findViewById(R.id.message);

            txtName.setText(m.getSender());
            txtMessage.setText(m.getMessage());
        }

        return row;
    }
}
