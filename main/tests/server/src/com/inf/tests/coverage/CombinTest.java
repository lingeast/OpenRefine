package com.inf.tests.coverage;

import java.io.StringWriter;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.refine.expr.EvalError;
import com.google.refine.expr.functions.arrays.Reverse;
import com.google.refine.expr.functions.math.Combin;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;

public class CombinTest {
	static Properties bindings;
	
    @BeforeTest
    public void init() {
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
  public void testInvalidParam() {
	  Assert.assertTrue(invoke("combin") instanceof EvalError);
	  Assert.assertTrue(invoke("combin", 2, "astr") instanceof EvalError);
	  Assert.assertTrue(invoke("combin", "astr", "3") instanceof EvalError);
  }
  
  /*
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testException() {
	  Assert.assertTrue(invoke("factn", -1, 5) instanceof EvalError);
  }
  */
  
  @Test
  public void testCombin() {
	  long val1 = 6;
	  long val2 = 15;
	  Assert.assertEquals(invoke("combin", 4, 2), val1);
	  Assert.assertEquals(invoke("combin", 6, 4), val2);
  }
  
  @Test
  public void testWriter() throws JSONException {
      StringWriter sw = new StringWriter();
      JSONWriter jw = new JSONWriter(sw);
      Combin cbObj = new Combin();
      cbObj.write(jw, null);
      JSONObject jo = new JSONObject(sw.toString());
      Assert.assertEquals("Returns the number of combinations for n elements as divided into k", jo.get("description"));
      
  }
}
