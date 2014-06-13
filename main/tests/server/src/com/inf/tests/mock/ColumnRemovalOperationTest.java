package com.inf.tests.mock;

import static org.mockito.Mockito.*;

import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.testng.annotations.Test;

import com.google.refine.model.Project;
import com.google.refine.operations.column.ColumnRemovalOperation;

/**
 * @author Lin Guan
 *
 */

public class ColumnRemovalOperationTest {
    
    ColumnRemovalOperation cRemoval;
    String name = "newname";
    JSONObject jsonObject;
    JSONWriter jsonWriter;
    Project mockedProj; 
    Properties mockedProper;
    
    
    
    @Test
    public void testReconstruct() throws Exception{
        //given
        jsonObject = mock(JSONObject.class);
        mockedProj = mock(Project.class);
        
        //stubing
        when(jsonObject.getString("newname")).thenReturn(name);
        cRemoval = (ColumnRemovalOperation)ColumnRemovalOperation.reconstruct(mockedProj, jsonObject);
        
        //verification
        verify(jsonObject, times(1)).getString(anyString());
        
    }
    
    @Test
    public void testWrite() throws JSONException{
        
        //given
        jsonWriter = mock(JSONWriter.class);
        mockedProper = mock(Properties.class);
        
        cRemoval.write(jsonWriter, mockedProper);
        
        //verification
        verify(jsonWriter).key("columnName");
        
        
        
        
        
        
        
    }
    

}
