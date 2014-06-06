package com.inf.tests.coverage;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;


public class StringTestTwo {
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
    public void testStartsWithTest() {
        String testString = "Welcome to China";
        String testString2 = "Welcome";
        Assert.assertEquals((invoke("startsWith", testString, testString2)),testString.startsWith(testString2));
    }
    
    @Test
    public void testEndsWithTest() {
        String testString = "Welcome to China";
        String testString2 = "China";
        Assert.assertEquals((invoke("endsWith", testString, testString2)),testString.endsWith(testString2));
    }
    
    @Test
    public void testLastIndexOf() {
        String testString1 = "Welcome to China";
        String testString2 = "China";
        Assert.assertEquals(invoke("lastIndexOf", testString1, testString2),testString1.lastIndexOf(testString2));
    }
    
    
    

}
