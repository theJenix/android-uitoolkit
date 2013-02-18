package com.jr.android.uitoolkit.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TwoLineArrayAdapter extends BaseAdapter {
    private int listItemLayoutResId;
    private boolean forceSingleLine;
    private Context context;
    private List<ITwoLineListItem> data;

    public TwoLineArrayAdapter(Context context) {
        this(context, true, new ArrayList<ITwoLineListItem>());
    }

    public TwoLineArrayAdapter(Context context, List<ITwoLineListItem> ts) {
        this(context, true, ts);
    }

    public TwoLineArrayAdapter(Context context, boolean forceSingleLine, List<ITwoLineListItem> ts) {
        this(context, android.R.layout.two_line_list_item, forceSingleLine, ts);
    }

    public TwoLineArrayAdapter(
            Context context, 
            int listItemLayoutResourceId,
            boolean singleLine,
            List<ITwoLineListItem> ts) {
        this.context = context;
        this.listItemLayoutResId = listItemLayoutResourceId;
        this.forceSingleLine = singleLine;
        this.data = ts;
    }

    public void clear() {
        this.data.clear();
    }
    
    public void add(ITwoLineListItem item) {
        this.data.add(item);
    }
    
    public void addAll(Collection<ITwoLineListItem> items) {
        this.data.addAll(items);
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public android.view.View getView(
            int position, 
            View convertView,
            ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater)this.context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listItemView = convertView;
        if (null == convertView) { 
            listItemView = inflater.inflate(
                listItemLayoutResId, 
                parent, 
                false);
        }

        // The ListItemLayout must use the standard text item IDs.
        TextView lineOneView = (TextView)listItemView.findViewById(
            android.R.id.text1);
        TextView lineTwoView = (TextView)listItemView.findViewById(
            android.R.id.text2);

        ITwoLineListItem t = (ITwoLineListItem)getItem(position); 
        lineOneView.setText(t.getLineOneText());
        lineTwoView.setText(t.getLineTwoText());

        //if we want to force a single line text view (regardless of how the
        // xml is defined), do that here.  This is useful when reusing the
        // built in list item resources.
        if (forceSingleLine) {
            lineOneView.setSingleLine(true);
            lineOneView.setEllipsize(TruncateAt.END);
            lineTwoView.setSingleLine(true);
            lineTwoView.setEllipsize(TruncateAt.END);
        }
        
        if (t.getBackground() != null) {
            listItemView.setBackgroundDrawable(t.getBackground());
        }

        int colorId = t.getTextColor() != null ? t.getTextColor() : android.R.color.white; //white is default
        
        int color = this.context.getResources().getColor(colorId);

        lineOneView.setTextColor(color);
        lineTwoView.setTextColor(color);
        return listItemView;
    }
}
