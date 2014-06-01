package com.inf.tests.coverage;

import java.io.StringWriter;
import java.util.Properties;

import org.json.JSONWriter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.refine.expr.EvalError;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;

public class FactNTest {
	
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
	  Assert.assertTrue(invoke("factn") instanceof EvalError);
	  Assert.assertTrue(invoke("factn", 2, "astr") instanceof EvalError);
	  Assert.assertTrue(invoke("factn", "astr", "3") instanceof EvalError);
  }
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testException() {
	  Assert.assertTrue(invoke("factn", -1, 5) instanceof EvalError);
  }
  @Test
  public void testFactN() {
	  long val1 = 8;
	  long val2 = 24;
	  Assert.assertEquals(invoke("factn", 4, 2), val1);
	  Assert.assertEquals(invoke("factn", 4, 1), val2);
  }


  @Test
  public void testWrite() {
      StringWriter sw = new StringWriter();
      JSONWriter jw = new JSONWriter(sw);
      
  }
}
