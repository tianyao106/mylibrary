package com.intelink.express.mylibrary.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

	private SparseArray<View> mViews;
	private View mConvertView;
	
	public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		 this.mViews=new SparseArray<View>();
		 mConvertView=LayoutInflater.from(context).inflate(layoutId, parent, false);
		 mConvertView.setTag(this);
	}
	
	public static ViewHolder get(Context context,View convertView,ViewGroup parent,int layoutId,int position){
		if(convertView==null){
			return new ViewHolder(context, parent, layoutId, position);
		}
		return (ViewHolder) convertView.getTag();
	}
	
	public <T extends View> T getView(int viewId){
		View view=mViews.get(viewId);
		if(view==null){
			view=mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}
	
	public View getConvertView(){
		return mConvertView;
	}
	
	public void setText(int viewId,String text){
		TextView textView=getView(viewId);
		textView.setText(text);
	}

	public void setText(int viewId,Spanned text){
		TextView textView=getView(viewId);
		textView.setText(text);
	}

	public ViewHolder setImageResource(int viewId, int drawableId,int width,int height)
	{
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);
		view.setMaxWidth(width);
		view.setMaxHeight(height);
		return this;
	}
	public ViewHolder setImageResource(int viewId, int drawableId,int width)
	{
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);
		view.setMaxWidth(width);
		return this;
	}

	public void setImageView(int viewId,Bitmap mBitmap){
		ImageView imageView=getView(viewId);
		imageView.setImageBitmap(mBitmap);
	}

	/*public void setImageView(int viewId,Bitmap mBitmap,View.OnClickListener listener,ImageClickCallback callback){
		ImageView imageView=getView(viewId);
		imageView.setImageBitmap(mBitmap);
		imageView.setOnClickListener(listener);
		callback.onReturn(listener, imageView);
	}*/

	public ImageView setImageView2(int viewId,Bitmap mBitmap){
		ImageView imageView=getView(viewId);
		imageView.setImageBitmap(mBitmap);
		return imageView;
	}


	public void setTextColor(int viewId,Context mContext,int colorId){
		TextView textView=getView(viewId);
		textView.setTextColor(mContext.getResources().getColor(colorId));
	}

	public void setImageResource(int viewId,int imageId){
		ImageView imageView=getView(viewId);
		imageView.setImageResource(imageId);
	}

	public void setCheckBox(int viewId,boolean status,CompoundButton.OnCheckedChangeListener listener){
		CheckBox checkBox=getView(viewId);
		checkBox.setChecked(status);
		checkBox.setOnCheckedChangeListener(listener);
	}
}
