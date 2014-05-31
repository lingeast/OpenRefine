/*
 * Author: Yidong Ling 
 * 2014
 */
package com.inf.tests.mock;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

import com.google.refine.model.AbstractOperation;
import com.google.refine.model.Project;
import com.google.refine.operations.column.ColumnRenameOperation;

import static org.mockito.Mockito.*;

/**
 * @author ling
 *
 */
public class ColumnRenameOperationTest {
    Project mockedProj; 
    ColumnRenameOperation crno;
    
    String oldName = "OldName";
    String newName = "NewName";
    @BeforeMethod
    public void SetUp() throws Exception {
        // mocking object
        mockedProj = mock(Project.class);
    }
    
    @Test
    public void testReconstruct() throws Exception {
        JSONObject jsonObj  = mock(JSONObject.class);
        
        // stubing 
        when(jsonObj.getString("oldColumnName")).thenReturn(oldName);
        when(jsonObj.getString("newColumnName")).thenReturn(newName);
        crno = (ColumnRenameOperation)
                ColumnRenameOperation.reconstruct(mockedProj, jsonObj);
        
        // verification
        verify(jsonObj, times(2)).getString(anyString());
    }

  @Test
  public void createHistoryEntry() {
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getBriefDescription() {
    //throw new RuntimeException("Test not implemented");
  }



  @Test
  public void write() throws JSONException {
      // mocking
      JSONWriter jsonWtr = mock(JSONWriter.class);
      Properties options = mock(Properties.class);
      
      crno.write(jsonWtr, options);
      
      //verification
      verify(jsonWtr).key("oldColumnName");
      verify(jsonWtr).value(oldName);
      verify(jsonWtr).key("newColumnName");
      verify(jsonWtr).value(newName);
  }
}
