package com.example.rmtsampleflight.v1.entitytypes;
/*

 Auto-Generated by SAP NetWeaver Gateway Productivity Accelerator, Version 1.1.1

*/
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import com.sap.gwpa.proxy.BaseEntityType;
import com.sap.gwpa.proxy.ODataQuery;
import com.sap.gwpa.proxy.TypeConverter;
import com.sap.mobile.lib.parser.IODataEntry;
import com.sap.mobile.lib.parser.IODataProperty;
import com.sap.mobile.lib.parser.IODataSchema;
import com.sap.mobile.lib.parser.IODataServiceDocument;
import com.sap.mobile.lib.parser.IParser;
import com.sap.mobile.lib.parser.ODataEntry;

/**
 * Subscription Entity Type 
 *
 * <br>key (ID)
 */
@SuppressLint("SimpleDateFormat")
public class Subscription extends BaseEntityType 
{
	// Subscription properties
    private String ID;
    private String user;
    private Date updated;
    private String title;
    private String deliveryAddress;
    private Boolean persistNotifications;
    private String collection;
    private String filter;
    private String select;
    private String changeType;

	// reference to the parser
	@SuppressWarnings("unused")
	private IParser parser;
	// reference to the schema
	@SuppressWarnings("unused")
	private IODataSchema schema;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


	private static Map<String, String> subscriptionLabels;

	private static Map<String, String> subscriptionTypes;	
	
	/**
	 * Constructor
	 * @param entry
	 * @throws MalformedURLException
	 */
	public Subscription(final IODataEntry entry, IParser parser, IODataSchema schema) throws MalformedURLException
	{
		super(entry);
		this.ID =  TypeConverter.getAsString(entry.getPropertyValue("ID"));
		this.user = entry.getAuthorName();
		this.updated = entry.getLastUpdated();
		this.title = entry.getTitle();
		this.deliveryAddress =  TypeConverter.getAsString(entry.getPropertyValue("deliveryAddress"));
		this.persistNotifications =  TypeConverter.getAsBoolean(entry.getPropertyValue("persistNotifications"));
		this.collection =  TypeConverter.getAsString(entry.getPropertyValue("collection"));
		this.filter =  TypeConverter.getAsString(entry.getPropertyValue("filter"));
		this.select =  TypeConverter.getAsString(entry.getPropertyValue("select"));
		this.changeType =  TypeConverter.getAsString(entry.getPropertyValue("changeType"));
	    this.parser = parser;
        this.schema = schema;
 	}
	
	/**
	 * Subscription Constructor</br>
	 * Dummy values may apply
	 *
	 */
	public Subscription( String ID) 
	{
		super(new ODataEntry());
		
        this.setID(ID);
	}



	// Subscription properties getters and setters
		
	/**
     * @return - String ID
	 */
	public String getID()
	{
		return this.ID;
	}
	
	/**
     * @param ID
	 */
	public void setID(String ID)
	{
		this.ID = ID;
		
		getEntry().putPropertyValue("ID", ID);
	}
		
	/**
	 * @return - String User Name
	 */
	public String getUser()
	{
		return this.user;
	}
	
	/**
	 * @param user - User Name
	 */
	public void setUser(String user)
	{
		this.user = user;
		
		getEntry().putPropertyValue("user", user);
	}
		
	/**
	 * @return - Date Time Stamp
	 */
	public Date getUpdated()
	{
		return this.updated;
	}
	
	/**
	 * @param updated - Time Stamp
	 */
	public void setUpdated(Date updated)
	{
		this.updated = updated;
		
		getEntry().putPropertyValue("updated", dateFormat.format(updated));
	}
		
	/**
     * @return - String title
	 */
	public String getTitle()
	{
		return this.title;
	}
	
	/**
     * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
		
		getEntry().putPropertyValue("title", title);
	}
		
	/**
     * @return - String deliveryAddress
	 */
	public String getDeliveryAddress()
	{
		return this.deliveryAddress;
	}
	
	/**
     * @param deliveryAddress
	 */
	public void setDeliveryAddress(String deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
		
		getEntry().putPropertyValue("deliveryAddress", deliveryAddress);
	}
		
	/**
	 * @return - Boolean Persist Notification
	 */
	public Boolean getPersistNotifications()
	{
		return this.persistNotifications;
	}
	
	/**
	 * @param persistNotifications - Persist Notification
	 */
	public void setPersistNotifications(Boolean persistNotifications)
	{
		this.persistNotifications = persistNotifications;
		
	}
		
	/**
     * @return - String collection
	 */
	public String getCollection()
	{
		return this.collection;
	}
	
	/**
     * @param collection
	 */
	public void setCollection(String collection)
	{
		this.collection = collection;
		
		getEntry().putPropertyValue("collection", collection);
	}
		
	/**
     * @return - String filter
	 */
	public String getFilter()
	{
		return this.filter;
	}
	
	/**
     * @param filter
	 */
	public void setFilter(String filter)
	{
		this.filter = filter;
		
		getEntry().putPropertyValue("filter", filter);
	}
		
	/**
     * @return - String select
	 */
	public String getSelect()
	{
		return this.select;
	}
	
	/**
     * @param select
	 */
	public void setSelect(String select)
	{
		this.select = select;
		
		getEntry().putPropertyValue("select", select);
	}
		
	/**
	 * @return - String Change Type
	 */
	public String getChangeType()
	{
		return this.changeType;
	}
	
	/**
	 * @param changeType - Change Type
	 */
	public void setChangeType(String changeType)
	{
		this.changeType = changeType;
		
		getEntry().putPropertyValue("changeType", changeType);
	}
	
	/**
	 * @return - representation of the Entity Type object in OData4SAP format
	 */
	public String getStringPayload()  
	{
		String xml = null;
		
		xml = getEntry().toXMLString();
			
		return xml;
	}
	
	/**
	 * @return - self ODataQuery object
	 * @throws MalformedURLException 
	 */
	public ODataQuery getEntityQuery() throws MalformedURLException  
	{
		return new ODataQuery(getEntry().getSelfLink().getUrl());
	}
	
	/**
	 * @return - the date format.
	 */
	public DateFormat getDateFormat()
	{
		return this.dateFormat;
	}


	/**
    * Static method that loads all of the entity type property labels. 
    * This method is called when the service class is initialized.
    * @param service Service document object containing all of the entity type properties.
   	*/	
    public static void loadLabels(IODataServiceDocument service)
    {
    	List<IODataProperty> properties = getSchemaPropertiesFromCollection(service, "SubscriptionCollection" );
        
    	subscriptionLabels = new HashMap<String, String>();
    	subscriptionTypes = new HashMap<String, String>();
    	
    	if (properties != null)
    	{
	        for (IODataProperty property : properties) 
	        {
	        	subscriptionLabels.put(property.getName(), property.getLabel());
	        	subscriptionTypes.put(property.getName(), property.getType());
			}
    	}
    }
    
    
    /**
    * Static method that returns the type for a given property name.
    * @param propertyName Property name.
    * @return Property label.
   	*/
    public static String getTypeForProperty(String propertyName)
    {
        return getLabelFromDictionary(subscriptionTypes, propertyName);
    }
    
    
    /**
    * Static method that returns the label for a given property name.
    * @param propertyName Property name.
    * @return Property label.
   	*/
    public static String getLabelForProperty(String propertyName)
    {
        return getLabelFromDictionary(subscriptionLabels, propertyName);
    }
}