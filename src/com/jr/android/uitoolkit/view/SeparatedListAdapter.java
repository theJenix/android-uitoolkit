package com.jr.android.uitoolkit.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.jr.android.uitoolkit.R;

public class SeparatedListAdapter extends BaseAdapter {  

    public final Map<String,Adapter> sections = new LinkedHashMap<String,Adapter>();  
    public final Map<Integer,Drawable> sectionBackgrounds = new LinkedHashMap<Integer, Drawable>();
    public final ArrayAdapter<String> headers;
    public final static int TYPE_SECTION_HEADER = 0;  
      
    public SeparatedListAdapter(Context context) {  
        this(context, R.layout.separated_list_header);  
    }  
      
    public SeparatedListAdapter(Context context, int headerResId) {  
        headers = new ArrayAdapter<String>(context, headerResId);  
    }  
      
    public void addSection(String section, Adapter adapter) {
        addSection(section, null, adapter);
    }

    public void addSection(String section, Drawable background, Adapter adapter) {
        int pos = this.headers.getCount();
        this.headers.add(section);
        if (background != null) {
            this.sectionBackgrounds.put(pos, background);
        }
        this.sections.put(section, adapter);
        
        //if any section adapters have changed, bubble that notification up to the view, to repopulate the list
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
    }  

    public void clear() {
        this.headers.clear();
        this.sectionBackgrounds.clear();
        this.sections.clear();
    }  

    public Object getItem(int position) {  
        for(Object section : this.sections.keySet()) {  
            Adapter adapter = sections.get(section);  
            int size = adapter.getCount() + 1;  
              
            // check if position inside this section   
            if(position == 0) return section;  
            if(position < size) return adapter.getItem(position - 1);  
  
            // otherwise jump into next section  
            position -= size;  
        }  
        return null;  
    }  
  
    public int getCount() {  
        // total together all sections, plus one for each section header  
        int total = 0;  
        for(Adapter adapter : this.sections.values())  
            total += adapter.getCount() + 1;  
        return total;  
    }  
  
    public int getViewTypeCount() {  
        // assume that headers count as one, then total all sections  
        int total = 1;  
        for(Adapter adapter : this.sections.values())  
            total += adapter.getViewTypeCount();  
        return total;  
    }  
      
    public int getItemViewType(int position) {  
        int type = 1;  
        for(Object section : this.sections.keySet()) {  
            Adapter adapter = sections.get(section);  
            int size = adapter.getCount() + 1;  
              
            // check if position inside this section   
            if(position == 0) return TYPE_SECTION_HEADER;  
            if(position < size) return type + adapter.getItemViewType(position - 1);  
  
            // otherwise jump into next section  
            position -= size;  
            type += adapter.getViewTypeCount();  
        }  
        return -1;  
    }  
      
    public boolean areAllItemsSelectable() {  
        return false;  
    }  
  
    public boolean isEnabled(int position) {  
        return (getItemViewType(position) != TYPE_SECTION_HEADER);  
    }  
      
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        int sectionnum = 0;  
        for(Object section : this.sections.keySet()) {  
            Adapter adapter = sections.get(section);  
            int size = adapter.getCount() + 1;  
              
            // check if position inside this section   
            if (position == 0) {
                View view = headers.getView(sectionnum, convertView, parent);
                view.setBackgroundDrawable(this.sectionBackgrounds.get(sectionnum));
                return view;
            }
            
            if (position < size) {
                return adapter.getView(position - 1, convertView, parent);  
            }
  
            // otherwise jump into next section  
            position -= size;  
            sectionnum++;  
        }  
        return null;  
    }  
  
    @Override  
    public long getItemId(int position) {  
        return position;  
    }

    /**
     * Get the position for this view in the original data.  This is used for item click listeners, because the position passed in
     * is skewed by the section headers
     * 
     * @param viewPosition
     * @return
     */
	public int getPositionInData(int viewPosition) {
        int sectionCount  = 0; //start at one..we have at least one section
        int totalPosition = 0;
        int realPosition  = -1;
        for(Object section : this.sections.keySet()) {
            //increment section count
            sectionCount++;
            
            Adapter adapter = sections.get(section);
            //check if viewPosition is a header..if so, return -1
            if (viewPosition == totalPosition) {
            	realPosition = -1;
            	break;
            }
            //increment totalPosition to see if the referenced review is in this section
            totalPosition += adapter.getCount() + 1;
            //check if viewPosition is in this section..if so, compute the real position
            if (viewPosition < totalPosition) {
            	realPosition = viewPosition - sectionCount;
            	break;
            }
        }
        return realPosition;
    }
}  