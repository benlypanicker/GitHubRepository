package com.example;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rmtsampleflight.v1.entitytypes.Flight;

/**
 * List adapter.
 */
public class Page1ListAdapter extends BaseAdapter
{	
	private List<Flight> entries;
	private List<Flight> filteredEntries;

	private Context mContext;

	/**
	 * Constructs a new list adapter with the given context.
	 * @param c - application context.
	 */
	public Page1ListAdapter(Context c)
	{
		mContext = c;
	}

	/**
	 * Returns the list of entries.
	 * @return - list of entries.
	 */
	public List<Flight> getEntries()
	{
		return entries;
	}

	/**
	 * Sets the given entries.
	 * @param entries
	 */
	public void setEntries(List<Flight> entries)
	{
		this.entries = entries;
		this.filteredEntries = entries;
	}

	/**
	 * Returns the number of entries.
	 * @return - the number of entries.
	 */
	public int getCount()
	{
		return filteredEntries.size();
	}

	/**
	 * Returns the item in the given position.
	 * @param position - the position of the desired item.
	 * @return - the item in the given position.
	 */
	public Object getItem(int position)
	{
		return filteredEntries.get(position);
	}

	/**
	 * Returns the id of the item in the given position.
	 * @param position - the position of the item.
	 * @return - the id of the item in the given position.
	 */
	public long getItemId(int position)
	{
		return position;
	}
	
	private static class ViewHolder 
	{
		public LinearLayout ll;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View rowView = convertView;
		
		if (rowView == null) 
		{
			LayoutInflater mInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// Inflate a view template
			rowView = mInflater.inflate(com.example.R.layout.item_entry, parent, false);
			
			ViewHolder holder = new ViewHolder();
			holder.ll = (LinearLayout) rowView.findViewById(com.example.R.id.linearLayout1);
			holder.ll.setPadding(10, 10, 10, 10);
			holder.ll.setOrientation(LinearLayout.VERTICAL);
			
			rowView.setTag(holder);
		}

		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.ll.removeAllViews();
		
		Flight entry = filteredEntries.get(position);
		
		TextView carridTextView = new TextView(mContext);
		//set the first property to bigger font
		carridTextView.setTextSize(22); 
		carridTextView.setText(getPropertyValue(String.valueOf(entry.getCarrid())));
		holder.ll.addView(carridTextView);	
		
		TextView connidTextView = new TextView(mContext);
		connidTextView.setText(getPropertyValue(String.valueOf(entry.getConnid())));
		holder.ll.addView(connidTextView);	
		
		TextView CURRENCYTextView = new TextView(mContext);
		CURRENCYTextView.setText(getPropertyValue(String.valueOf(entry.getCURRENCY())));
		holder.ll.addView(CURRENCYTextView);	
		
		TextView fldateTextView = new TextView(mContext);
		fldateTextView.setText(getPropertyValue(String.valueOf(entry.getFldate())));
		holder.ll.addView(fldateTextView);	
		
		TextView flightDetailsTextView = new TextView(mContext);
		flightDetailsTextView.setText(getPropertyValue(String.valueOf(entry.getFlightDetails())));
		holder.ll.addView(flightDetailsTextView);	
		
		TextView PAYMENTSUMTextView = new TextView(mContext);
		PAYMENTSUMTextView.setText(getPropertyValue(String.valueOf(entry.getPAYMENTSUM())));
		holder.ll.addView(PAYMENTSUMTextView);	
		
		TextView PLANETYPETextView = new TextView(mContext);
		PLANETYPETextView.setText(getPropertyValue(String.valueOf(entry.getPLANETYPE())));
		holder.ll.addView(PLANETYPETextView);	
		
		TextView PRICETextView = new TextView(mContext);
		PRICETextView.setText(getPropertyValue(String.valueOf(entry.getPRICE())));
		holder.ll.addView(PRICETextView);	
		
		TextView SEATSMAXTextView = new TextView(mContext);
		SEATSMAXTextView.setText(getPropertyValue(String.valueOf(entry.getSEATSMAX())));
		holder.ll.addView(SEATSMAXTextView);	
		
		TextView SEATSMAX_BTextView = new TextView(mContext);
		SEATSMAX_BTextView.setText(getPropertyValue(String.valueOf(entry.getSEATSMAX_B())));
		holder.ll.addView(SEATSMAX_BTextView);	
		
		TextView SEATSMAX_FTextView = new TextView(mContext);
		SEATSMAX_FTextView.setText(getPropertyValue(String.valueOf(entry.getSEATSMAX_F())));
		holder.ll.addView(SEATSMAX_FTextView);	
		
		TextView SEATSOCCTextView = new TextView(mContext);
		SEATSOCCTextView.setText(getPropertyValue(String.valueOf(entry.getSEATSOCC())));
		holder.ll.addView(SEATSOCCTextView);	
		
		TextView SEATSOCC_BTextView = new TextView(mContext);
		SEATSOCC_BTextView.setText(getPropertyValue(String.valueOf(entry.getSEATSOCC_B())));
		holder.ll.addView(SEATSOCC_BTextView);	
		
		TextView SEATSOCC_FTextView = new TextView(mContext);
		SEATSOCC_FTextView.setText(getPropertyValue(String.valueOf(entry.getSEATSOCC_F())));
		holder.ll.addView(SEATSOCC_FTextView);	
		return rowView;
	}
	
	/**
	 * Returns the property value.
	 * @param value
	 * @return - property value.
	 */
	public String getPropertyValue(String value)
	{
		if (value.equalsIgnoreCase("null"))
		{
			return mContext.getString(com.example.R.string.no_value);
		}

		return value;
	}

	/**
	 * Filters the items by the given constraint.
	 * @param constraint
	 */
	public void filter(CharSequence constraint)
	{
		if (constraint != null)
		{
			constraint = constraint.toString().toLowerCase();
			this.filteredEntries = new ArrayList<Flight>();
			for (Flight entry : entries)
			{
				ArrayList<String> values = new ArrayList<String>();

				values.add(String.valueOf(entry.getCarrid()).toLowerCase());
				values.add(String.valueOf(entry.getConnid()).toLowerCase());
				values.add(String.valueOf(entry.getCURRENCY()).toLowerCase());
				values.add(String.valueOf(entry.getFldate()).toLowerCase());
				values.add(String.valueOf(entry.getFlightDetails()).toLowerCase());
				values.add(String.valueOf(entry.getPAYMENTSUM()).toLowerCase());
				values.add(String.valueOf(entry.getPLANETYPE()).toLowerCase());
				values.add(String.valueOf(entry.getPRICE()).toLowerCase());
				values.add(String.valueOf(entry.getSEATSMAX()).toLowerCase());
				values.add(String.valueOf(entry.getSEATSMAX_B()).toLowerCase());
				values.add(String.valueOf(entry.getSEATSMAX_F()).toLowerCase());
				values.add(String.valueOf(entry.getSEATSOCC()).toLowerCase());
				values.add(String.valueOf(entry.getSEATSOCC_B()).toLowerCase());
				values.add(String.valueOf(entry.getSEATSOCC_F()).toLowerCase());
				boolean found = false;
				for (String s : values)
				{
					if (s != null && s.contains(constraint))
					{
						found = true;
						break;
					}
				}
				
				if(found)
					filteredEntries.add(entry);
			}
		} 
		else
		{
			this.filteredEntries = entries;
		}
		
		notifyDataSetChanged();
	}
}