package com.example;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rmtsampleflight.v1.entitytypes.Booking;
	
/**
 * Details adapter.
 */
public class Page2DetailsAdapter extends BaseAdapter
{
	public static enum SapSemantics {tel, email, url};
	

	private Context mContext;
	private Booking entry;

	private List<String> propertiesValues = new ArrayList<String>();
	private List<String> labels = new ArrayList<String>();
	private List<String> sapSemanticsList = new ArrayList<String>();

	/**
	 * Constructs a new details adapter with the given parameters.
	 * @param c - application context.
	 * @param e - entry.
	 */
	public Page2DetailsAdapter(Context c, Booking e)
	{
		
		mContext = c;
		entry = e;
		propertiesValues.add(String.valueOf(entry.getAGENCYNUM()));
		labels.add(Booking.getLabelForProperty("AGENCYNUM"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getBookid()));
		labels.add(Booking.getLabelForProperty("bookid"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getCANCELLED()));
		labels.add(Booking.getLabelForProperty("CANCELLED"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getCarrid()));
		labels.add(Booking.getLabelForProperty("carrid"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getCLASS()));
		labels.add(Booking.getLabelForProperty("CLASS"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getConnid()));
		labels.add(Booking.getLabelForProperty("connid"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getCOUNTER()));
		labels.add(Booking.getLabelForProperty("COUNTER"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getCUSTOMID()));
		labels.add(Booking.getLabelForProperty("CUSTOMID"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getCUSTTYPE()));
		labels.add(Booking.getLabelForProperty("CUSTTYPE"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getFldate()));
		labels.add(Booking.getLabelForProperty("fldate"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getFORCURAM()));
		labels.add(Booking.getLabelForProperty("FORCURAM"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getFORCURKEY()));
		labels.add(Booking.getLabelForProperty("FORCURKEY"));
		sapSemanticsList.add("currency-code");
		propertiesValues.add(String.valueOf(entry.getINVOICE()));
		labels.add(Booking.getLabelForProperty("INVOICE"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getLOCCURAM()));
		labels.add(Booking.getLabelForProperty("LOCCURAM"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getLOCCURKEY()));
		labels.add(Booking.getLabelForProperty("LOCCURKEY"));
		sapSemanticsList.add("currency-code");
		propertiesValues.add(String.valueOf(entry.getLUGGWEIGHT()));
		labels.add(Booking.getLabelForProperty("LUGGWEIGHT"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getORDER_DATE()));
		labels.add(Booking.getLabelForProperty("ORDER_DATE"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getPASSBIRTH()));
		labels.add(Booking.getLabelForProperty("PASSBIRTH"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getPASSFORM()));
		labels.add(Booking.getLabelForProperty("PASSFORM"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getPASSNAME()));
		labels.add(Booking.getLabelForProperty("PASSNAME"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getRESERVED()));
		labels.add(Booking.getLabelForProperty("RESERVED"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getSMOKER()));
		labels.add(Booking.getLabelForProperty("SMOKER"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getWUNIT()));
		labels.add(Booking.getLabelForProperty("WUNIT"));
		sapSemanticsList.add("unit-of-measure");
	}

	/**
	 * Returns the amount of entries.
	 * @return - the amount of entries.
	 */
	public int getCount()
	{
		return propertiesValues.size();
	}

	/**
	 * Returns the item in the given position.
	 * @param position - the position of the desired item.
	 * @return - the item in the given position.
	 */
	public Object getItem(int position)
	{
		return position;
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
	
	private class ViewHolder 
	{
		public ImageView imageView;
		public TextView textView1;
		public TextView textView2;
	}
		
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View rowView = convertView;
		
		if (rowView == null || position == propertiesValues.size()) 
		{			
			LayoutInflater mInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			// Inflate a view template
			rowView = mInflater.inflate(com.example.R.layout.item_detail_row, parent, false);
			
			ViewHolder holder = new ViewHolder();
			
			holder.textView1 = (TextView) rowView.findViewById(com.example.R.id.textView1);
			holder.textView1.setTextSize(22);
			holder.textView2 = (TextView) rowView.findViewById(com.example.R.id.textView2);
			holder.imageView = (ImageView) rowView.findViewById(com.example.R.id.imageView1);
			rowView.setTag(holder);
		}
		
		ViewHolder holder = (ViewHolder) rowView.getTag();
		

		String value = getPropertyValue(position);
		String label = labels.get(position);

		holder.textView1.setText(label);
		holder.textView2.setText(value);
		holder.imageView.setVisibility(View.INVISIBLE);
		
		SapSemantics sapSemantics = getSapSemantics(position);
		if (sapSemantics != null)
		{
			switch (sapSemantics)
			{
				case tel:   holder.imageView.setImageResource(com.example.R.drawable.tel);
							holder.imageView.setVisibility(View.VISIBLE);
						    break;
						    
				case email: holder.imageView.setImageResource(com.example.R.drawable.email);
							holder.imageView.setVisibility(View.VISIBLE);
					        break;
					        
				case url:   holder.imageView.setImageResource(com.example.R.drawable.url);
							holder.imageView.setVisibility(View.VISIBLE);
					        break;
			}
		}

		return rowView;
	}
	
	/**
	 * Returns the SAP semantics in the given position.
	 * @param position
	 * @return - SAP semantics in the given position.
	 */
	public SapSemantics getSapSemantics(int position)
	{
		if (sapSemanticsList == null || sapSemanticsList.isEmpty() || position >= sapSemanticsList.size())
		{
			return null;
		}
		
		String value = this.sapSemanticsList.get(position);
		if (value == null)
		{
			return null;
		}
		
		value = value.toLowerCase();
		
		SapSemantics[] values = SapSemantics.values();
		for (SapSemantics sapSemantics : values) 
		{
			String semanticName = sapSemantics.name();
			if (semanticName.equals(value) || value.contains(semanticName + ";"))
			{
				return sapSemantics;
			}
		}
		return null;
	}
	
	/**
	 * Returns the property value.
	 * @param value
	 * @return - property value.
	 */
	public String getPropertyValue(int position)
	{
		if (propertiesValues == null || propertiesValues.isEmpty() || position >= propertiesValues.size())
		{
			return mContext.getString(com.example.R.string.no_value);
		}

		String value = propertiesValues.get(position);
		if (value == null || value.length() == 0 || value.equalsIgnoreCase("null"))
		{
			return mContext.getString(com.example.R.string.no_value);
		}
		
		return value;
	}
}
