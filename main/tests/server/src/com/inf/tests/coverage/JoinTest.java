package com.inf.tests.coverage;

import java.util.ArrayList;
import java.util.Properties;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.refine.expr.EvalError;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;

public class JoinTest {
    static Properties bindings;
    ArrayList<Integer> rawArr;
    @BeforeTest
    public void init() {
        rawArr = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            rawArr.add(i);
        }
        bindings = null;
    }
    
    /**
     * Lookup a control function by name and invoke it with a variable number of args
     */
    private static Object invoke(String name,Object... args) {
        // registry uses static initializer, so no need to set it up
        Function function = ControlFunctionRegistry.getFunction(name);
        if (function == null) {
            throw new IllegalArgumentException("Unknown function "+name);
        }
        if (args == null) {
            return function.call(bindings,new Object[0]);
        } else {
            return function.call(bindings,args);
        }
    }
    
  @Test
  public void testJoinJSONArray() {
      JSONArray jsonArr = new JSONArray(rawArr);
      invoke("join", jsonArr, ",");
  }
  
  @Test
  public void testJoinArray() {
      Assert.assertTrue(rawArr.toArray().getClass().isArray());
      invoke("join", rawArr.toArray(), ",");
  }
  
  @Test
  public void testInvalidParam() {
          Assert.assertTrue(invoke("join", 2) instanceof EvalError);
  }
}
