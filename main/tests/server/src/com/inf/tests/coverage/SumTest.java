package com.inf.tests.coverage;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.refine.expr.EvalError;
import com.google.refine.expr.functions.math.Sum;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;



public class SumTest {
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
	  Assert.assertTrue(invoke("sum") instanceof EvalError);
	  Assert.assertTrue(invoke("sum", 2, "astr") instanceof EvalError);
  }
  
  /*
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testException() {
	  Assert.assertTrue(invoke("factn", -1, 5) instanceof EvalError);
  }
  */
  
  @Test
  public void testSum() {
	  int arrLen = 10;
	  
	  ArrayList<Integer> tList = new ArrayList<Integer> ();
	  Integer[] tArr = new Integer[arrLen];
	  
	  double sum = 0;
	  for (int i = 0; i < arrLen; i++) {
		  tList.add(i+1);
		  tArr[i] = i + 1;
		  sum += i + 1;
	  }
	  //Assert.assertEquals(invoke("sum", (Object[]) tArr), sum);
	  Assert.assertEquals(invoke("sum", tList), sum);
	  
  }
  
  @Test
  public void testWriter() throws JSONException {
      StringWriter sw = new StringWriter();
      JSONWriter jw = new JSONWriter(sw);
      Sum smObj = new Sum();
      smObj.write(jw, null);
      JSONObject jo = new JSONObject(sw.toString());
      Assert.assertEquals("Sums numbers in array a", jo.get("description"));
      
  }


}
