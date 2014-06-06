package com.inf.tests.coverage;

import java.util.Properties;

import org.python.modules.math;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.refine.expr.functions.math.Max;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;


public class MathTest {
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
    public void testMax() {
        double testNum1 = 1.23;
        double testNum2 = 1.27;
        Assert.assertEquals((invoke("max", testNum1, testNum2)), Math.max(testNum1, testNum2));
    }
    
    @Test
    public void testMin() {
        double testNum1 = 1.23;
        double testNum2 = 1.27;
        Assert.assertEquals((invoke("min", testNum1, testNum2)),Math.min(testNum1, testNum2));
    }
    
    @Test
    public void testAbs() {
        double testNum = -2.0;
        Assert.assertEquals((invoke("abs", testNum)), Math.abs(testNum));
    }
    
    @Test
    public void testMod() {
        int testNum1 = 2;
        int testNum2 = 5;
        Assert.assertEquals((invoke("mod", testNum1, testNum2)), testNum1%testNum2);
    }
    
   
    
    
    
    

}
