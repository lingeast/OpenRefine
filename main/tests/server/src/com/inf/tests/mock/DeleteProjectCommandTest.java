package com.inf.tests.mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.refine.ProjectManager;
import com.google.refine.ProjectMetadata;
import com.google.refine.commands.project.DeleteProjectCommand;
import com.google.refine.model.Project;
import com.google.refine.process.ProcessManager;
import com.google.refine.tests.RefineTest;

public class DeleteProjectCommandTest extends RefineTest{
    @Override
    @BeforeTest
    public void init() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    //System Under Test
    DeleteProjectCommand SUT = null;
    
    // variables
    long PROJECT_ID_LONG = 7519;
    String PROJECT_ID = "7519";

    // mocks
    HttpServletRequest request = null;
    HttpServletResponse response = null;
    ProjectManager projMan = null;
    //Project proj = null;
    //ProcessManager processMan = null;
    PrintWriter pw = null;
    @BeforeMethod
    
    public void SetUp(){
        projMan = mock(ProjectManager.class);
        ProjectManager.singleton = projMan;
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        pw = mock(PrintWriter.class);
        //proj = new Project();
        SUT = new DeleteProjectCommand();
    }

    @AfterMethod
    public void TearDown(){
        SUT = null;
        request = null;
        response = null;
        pw = null;
        projMan = null;
    }

    @Test
    public void testDeleteProject() throws ServletException, IOException{
        //mock dependencies
        when (request.getParameter("project")).thenReturn(PROJECT_ID);
        when(response.getWriter()).thenReturn(pw);

        // run
        SUT.doPost(request, response);

        // verify
        verify(projMan, times(1)).deleteProject(PROJECT_ID_LONG);
        verify(pw, times(1)).flush();
        verify(pw, times(1)).close();
    }
    /*
  @Test
  public void doPost() {
      DeleteProjectCommand SUT = new DeleteProjectCommand();
    throw new RuntimeException("Test not implemented");
  }
  */
}
