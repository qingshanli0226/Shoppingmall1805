package cn.bw.textprojectone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

class MessageAdaper extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater layoutInflater;
    public MessageAdaper(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        varHead varHead  = null;
        if (view == null){
            varHead = new varHead();
            view = layoutInflater.inflate(R.layout.item, null);
            varHead.textView = view.findViewById(R.id.messageTextTlite);
            view.setTag(varHead);
        }else {
            varHead = (MessageAdaper.varHead) view.getTag();
        }
        varHead.textView.setText(list.get(i));

        return view;
    }

    public class varHead{
        TextView textView;
    }
}
