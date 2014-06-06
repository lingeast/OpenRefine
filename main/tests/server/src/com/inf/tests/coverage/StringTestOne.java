package com.inf.tests.coverage;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;


public class StringTestOne {
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
    public void testToLowercase() {
        String testString = "LIN";
        Assert.assertEquals((String)(invoke("toLowercase", testString)),testString.toLowerCase());
    }
    
    @Test
    public void testUppercase() {
        String testString = "lin";
        Assert.assertEquals((String)(invoke("toUppercase", testString)),testString.toUpperCase());
    }
    
    @Test
    public void testIndexOf() {
        String testString1 = "Welcome to China";
        String testString2 = "China";
        Assert.assertEquals(invoke("indexOf", testString1, testString2),testString1.indexOf(testString2));
    }
    
    
    
    

}
