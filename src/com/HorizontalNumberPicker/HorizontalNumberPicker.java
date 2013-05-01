package com.HorizontalNumberPicker;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.horizontalnumberpicker.R;

public class HorizontalNumberPicker extends RelativeLayout{
	
	private static int MIN_VALUE=0;
	private static int MAX_VALUE=9;
	private View inflatedView;
	private HorizontalScrollView	horizontalScrollView;
	
	private LinearLayout linearLayoutNumberContainer;
	AttributeSet attributeSet;
	
	public HorizontalNumberPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context	=	context;
		layoutInflater	=	(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		attributeSet	=	attrs;
		constructView();
	}
	
	public HorizontalNumberPicker(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.context	=	context;
		layoutInflater	=	(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		attributeSet	=	attrs;
		constructView();
	}
	LayoutInflater layoutInflater;
	Context context;

	public HorizontalNumberPicker(Context context) {
		super(context);
		this.context	=	context;
		layoutInflater	=	(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		constructView();
	}
	
	
	public void constructView() {
			inflatedView=layoutInflater.inflate(R.layout.horizontal_number_picker_layout, this,true);
			linearLayoutNumberContainer=(LinearLayout)inflatedView.findViewById(R.id.linearLayoutNumberContainer);
			final HorizontalScrollView horizontalScrollView	=	(HorizontalScrollView)inflatedView.findViewById(R.id.numberPickerHorizontalScrollViewContainer);
			horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					Rect scrollRect = new Rect(horizontalScrollView.getScrollX(),horizontalScrollView.getScrollY(),horizontalScrollView.getScrollX()+horizontalScrollView.getWidth(),horizontalScrollView.getScrollY() + horizontalScrollView.getHeight());
//					horizontalScrollView.getHitRect(scrollRect);
					for(int index=MIN_VALUE;index<=MAX_VALUE;index++) {
						TextView view=(TextView)linearLayoutNumberContainer.getChildAt(index);
						Rect tvHitRect 	=	new Rect();
						view.getHitRect(tvHitRect);
						if(scrollRect.intersect(tvHitRect)) {
							Toast.makeText(context, view.getText(), Toast.LENGTH_SHORT).show();
						}
						
					}
					return false;
				}
			});
			List<Integer>	numberList=	new ArrayList<Integer>();
			for(int index=MIN_VALUE;index<=MAX_VALUE;index++) {
				Integer integer	=	new Integer(index);
				numberList.add(integer);
			}
			CustomNumberAdapter<Integer> customNumberAdapter	=	new CustomNumberAdapter<Integer>(context, R.layout.number_text_view,numberList );
			customNumberAdapter.attachToRoot(linearLayoutNumberContainer);
			linearLayoutNumberContainer.invalidate();
            
		}
	}
	
	


