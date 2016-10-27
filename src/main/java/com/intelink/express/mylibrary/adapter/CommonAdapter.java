package com.intelink.express.mylibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
	
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected int mItemLayoutId;
	
	public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		mInflater=LayoutInflater.from(context);
		this.mContext=context;
		this.mDatas=mDatas;
		this.mItemLayoutId=itemLayoutId;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	public void add(T object){
		this.mDatas.add(object);
	}

	public void addAll(List<T> newDatas){
		mDatas.clear();
		mDatas.addAll(newDatas);
	}

	public void remove(int position){
		mDatas.remove(position);
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=getViewHolder(position, convertView, parent);
		convert(viewHolder, getItem(position));
		return viewHolder.getConvertView();
	}
	
	public abstract void convert(ViewHolder holder,T item);
	
	private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent){
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
	}

}
