package com.jr.android.uitoolkit.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jr.android.uitoolkit.R;

public class IconAndTextListAdapter extends BaseAdapter {

	private Context mContext;
	private List<String>   items;
    private List<Drawable> icons;

    public IconAndTextListAdapter(Context mContext) {
        this.mContext = mContext;
        this.items = new ArrayList<String>();
        this.icons = new ArrayList<Drawable>();
    }

    public void add(String item, int iconId) {
        add(item, this.mContext.getResources().getDrawable(iconId));
    }

    public void add(String item, Drawable icon) {
        this.items.add(item);
        this.icons.add(icon);
    }

    public void clear() {
        this.items.clear();
        this.icons.clear();
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		TextView tv;
		ImageView iv;
		v = setupView(convertView);
        //set the icon as the left drawable in the text box
		iv = (ImageView) v.findViewById(R.id.icon_image);
        iv.setImageDrawable(this.icons.get(position));
		tv = (TextView) v.findViewById(R.id.icon_text);
		tv.setText(this.items.get(position));
		return v;
	}

	private View setupView(View convertView) {
		View v;
		ImageView iv;
		final LayoutInflater li = (LayoutInflater) this.mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			// if it's not recycled, initialize some attributes
			v = li.inflate(R.layout.list_item_icon_and_text, null);
		} else {
			v = convertView;
		}
		return v;
	}

	@Override
	public int getCount() {
		return this.items.size();
	}

	@Override
	public Object getItem(int position) {
		return this.items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.items.get(position).hashCode();
	}
}
