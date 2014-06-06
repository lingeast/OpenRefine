package com.inf.tests.coverage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;


public class ExprFunctionsTest {
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
    public void testToString1() {
        Object testString = "Lin";
        Assert.assertEquals((invoke("toString", testString )),"Lin");
    }
    
    @Test
    public void testToString2() {
        Object testString = 06/01/2014;
        Assert.assertEquals((invoke("toString", testString )),testString.toString());
    }
    
    @Test
    public void testToString3() {
        Date testString = new Date(2014, 6, 1);
        DateFormat testString2 = new SimpleDateFormat("yyyy-MM-dd"); 
        Assert.assertEquals((invoke("toString", testString, "yyyy-MM-dd")),testString2.format(testString));
    }
    
    /**@Test
    public void testToString4() {
        Number testString = new Integer(0);
        DateFormat testString2 = new SimpleDateFormat("yyyy-MM-dd"); 
        Assert.assertEquals((invoke("toString", testString, "yyyy-MM-dd")),String.format(testString2.toString(), testString));
    }
    **/
    
    @Test
    public void testToNumber1() {
        Object testNum = 2;
        Assert.assertEquals((invoke("toNumber", testNum)),testNum);
    }
    
    @Test
    public void testToDate1() {
        Assert.assertEquals((invoke("toDate")),null);
    }
    
    @Test
    public void testToDate2() {
        Object testDate = new Date(2014, 6, 1);
        Assert.assertEquals((invoke("toDate", testDate)),testDate);
    }
    
    
    
   
    
    
    
    
    
    

}
