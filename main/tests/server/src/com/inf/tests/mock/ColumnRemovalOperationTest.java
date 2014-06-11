package com.inf.tests.mock;

import static org.mockito.Mockito.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.refine.model.Project;
import com.google.refine.operations.column.ColumnRemovalOperation;



public class ColumnRemovalOperationTest {
    
    ColumnRemovalOperation cRemoval;
    String name = "newname";
    JSONObject jsonObject;
    Project mockedProj; 
    
    
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
    

}
