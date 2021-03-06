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

/**
 * Notification Entity Type 
 *
 * <br>key (ID)
 */
@SuppressLint("SimpleDateFormat")
public class Notification extends BaseEntityType 
{
	// Notification properties
    private String ID;
    private String collection;
    private String title;
    private Date updated;
    private String changeType;
    private Integer entriesOfInterest;
    private String recipient;

	// reference to the parser
	@SuppressWarnings("unused")
	private IParser parser;
	// reference to the schema
	@SuppressWarnings("unused")
	private IODataSchema schema;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


	private static Map<String, String> notificationLabels;

	private static Map<String, String> notificationTypes;	
	
	/**
	 * Constructor
	 * @param entry
	 * @throws MalformedURLException
	 */
	public Notification(final IODataEntry entry, IParser parser, IODataSchema schema) throws MalformedURLException
	{
		super(entry);
		this.ID =  TypeConverter.getAsString(entry.getPropertyValue("ID"));
		this.collection =  TypeConverter.getAsString(entry.getPropertyValue("collection"));
		this.title = entry.getTitle();
		this.updated = entry.getLastUpdated();
		this.changeType =  TypeConverter.getAsString(entry.getPropertyValue("changeType"));
		this.entriesOfInterest =  TypeConverter.getAsInt(entry.getPropertyValue("entriesOfInterest"));
		this.recipient =  TypeConverter.getAsString(entry.getPropertyValue("recipient"));
	    this.parser = parser;
        this.schema = schema;
 	}
	



	// Notification properties getters and setters
		
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
	 * @return - Integer No. of Entries
	 */
	public Integer getEntriesOfInterest()
	{
		return this.entriesOfInterest;
	}
	
	/**
	 * @param entriesOfInterest - No. of Entries
	 */
	public void setEntriesOfInterest(Integer entriesOfInterest)
	{
		this.entriesOfInterest = entriesOfInterest;
		
	}
		
	/**
	 * @return - String Recipient
	 */
	public String getRecipient()
	{
		return this.recipient;
	}
	
	/**
	 * @param recipient - Recipient
	 */
	public void setRecipient(String recipient)
	{
		this.recipient = recipient;
		
		getEntry().putPropertyValue("recipient", recipient);
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
    	List<IODataProperty> properties = getSchemaPropertiesFromCollection(service, "NotificationCollection" );
        
    	notificationLabels = new HashMap<String, String>();
    	notificationTypes = new HashMap<String, String>();
    	
    	if (properties != null)
    	{
	        for (IODataProperty property : properties) 
	        {
	        	notificationLabels.put(property.getName(), property.getLabel());
	        	notificationTypes.put(property.getName(), property.getType());
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
        return getLabelFromDictionary(notificationTypes, propertyName);
    }
    
    
    /**
    * Static method that returns the label for a given property name.
    * @param propertyName Property name.
    * @return Property label.
   	*/
    public static String getLabelForProperty(String propertyName)
    {
        return getLabelFromDictionary(notificationLabels, propertyName);
    }
}