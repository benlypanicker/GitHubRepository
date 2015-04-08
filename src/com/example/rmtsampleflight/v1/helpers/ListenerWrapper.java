package com.example.rmtsampleflight.v1.helpers;
/*

 Auto-Generated by SAP NetWeaver Gateway Productivity Accelerator, Version 1.1.1

*/
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class for the listeners of request handler.
 */
public class ListenerWrapper
{
    // the listener
    private IRMTSAMPLEFLIGHTRequestHandlerListener listener;
    // the list of request IDs
    private List<RMTSAMPLEFLIGHTRequestID> requestIDs;
    
    /**
     * Constructs a new listener wrapper with the given listener
     * and request IDs.
     * 
     * @param listener - the request handler listener.
     * @param requestIDs - may be passed as an array or as a sequence of request IDs.
     */
    public ListenerWrapper(IRMTSAMPLEFLIGHTRequestHandlerListener listener, RMTSAMPLEFLIGHTRequestID... requestIDs)
    {
        this.listener = listener;
        
        this.requestIDs = new ArrayList<RMTSAMPLEFLIGHTRequestID>();
        for (RMTSAMPLEFLIGHTRequestID requestID : requestIDs)
        {
            this.requestIDs.add(requestID);
        }
    }

    /**
     * Returns the listener.
     * @return the listener.
     */
    public IRMTSAMPLEFLIGHTRequestHandlerListener getListener()
    {
        return listener;
    }

    /**
     * Returns the request IDs.
     * @return - the request IDs.
     */
    public List<RMTSAMPLEFLIGHTRequestID> getRequestIDs()
    {
        return requestIDs;
    }
}