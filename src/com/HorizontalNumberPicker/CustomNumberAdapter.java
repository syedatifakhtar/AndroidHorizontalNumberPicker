package com.HorizontalNumberPicker;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.horizontalnumberpicker.R;

public class CustomNumberAdapter<E extends Number>{
	
	private List<E> items;
	private int resourceID;
	private Context context;
	private LayoutInflater layoutInflater;
	
	private int currentPosition;
	
	/**
	 * 
	 * @param context - The Application Context
	 * @param resourceID - The Resource ID of the view the adapter is associated with
	 * @param numberList - The list of items with which to populate the view
	 */
	public CustomNumberAdapter(Context context,int resourceID,List<E> numberList) {
		this.context	=	context;
		this.resourceID	=	resourceID;
		this.items	=	numberList;
		
		layoutInflater	=	(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public View getView(ViewGroup root,E item,boolean attachToRoot) {
		View customView	= layoutInflater.inflate(resourceID, root,false);
		TextView tv	=	(TextView) customView.findViewById(R.id.numberTextView);
		tv.setText(item.toString());
		return customView;
	}
	
	private E getItemAtPosition(int position) {
		return items.get(position);
	}
	
	public void attachToRoot(ViewGroup root) {
		Iterator<E> listIterator	=	items.iterator();
		int tempIndex	=	0;
		while(listIterator.hasNext()) {
			root.addView(getView(root,listIterator.next(),false),tempIndex++);
		}
	}
	
}
