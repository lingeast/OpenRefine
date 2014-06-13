package com.inf.tests.parameterized;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;

public class IncTest {
    
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

  @DataProvider(name = "provideDates")
  public Object[][] dp() {
      Calendar now = Calendar.getInstance();
      
      Calendar chHour = (Calendar)now.clone();
      chHour.add(Calendar.HOUR, 1);
      Calendar chDay = (Calendar)now.clone();
      chDay.add(Calendar.DAY_OF_MONTH, 1);
      Calendar chYear = (Calendar)now.clone();
      chYear.add(Calendar.YEAR, 1);
      Calendar chWeek = (Calendar)now.clone();
      chWeek.add(Calendar.WEEK_OF_MONTH, 1);
      Calendar chMonth = (Calendar)now.clone();
      chMonth.add(Calendar.MONTH, 1);
      Calendar chMinute = (Calendar)now.clone();
      chMinute.add(Calendar.MINUTE, 1);
      Calendar chSec = (Calendar)now.clone();
      chSec.add(Calendar.SECOND, 1);
      
      Date date = now.getTime();
      
    return new Object[][] {
      new Object[] { now, 1, "hour", chHour },
      new Object[] { now, 1, "day", chDay },
      new Object[] { now, 1, "year", chYear },
      new Object[] { now, 1, "week", chWeek },
      new Object[] { now, 1, "month", chMonth },
      new Object[] { now, 1, "minute", chMinute },
      new Object[] { now, 1, "sec", chSec },
      
      new Object[] { now, 1.4, "hour", chHour },
      new Object[] { now, 1.4, "day", chDay },
      new Object[] { now, 1.4, "year", chYear },
      new Object[] { now, 1.4, "week", chWeek },
      new Object[] { now, 1.4, "month", chMonth },
      new Object[] { now, 1.4, "minute", chMinute },
      new Object[] { now, 1.4, "sec", chSec },

      new Object[] { date, 1, "hour", chHour },
      new Object[] { date, 1, "day", chDay },
      new Object[] { date, 1, "year", chYear },
      new Object[] { date, 1, "week", chWeek },
      new Object[] { date, 1, "month", chMonth },
      new Object[] { date, 1, "minute", chMinute },
      new Object[] { date, 1, "sec", chSec },
      
      new Object[] { date, 1.4, "hour", chHour },
      new Object[] { date, 1.4, "day", chDay },
      new Object[] { date, 1.4, "year", chYear },
      new Object[] { date, 1.4, "week", chWeek },
      new Object[] { date, 1.4, "month", chMonth },
      new Object[] { date, 1.4, "minute", chMinute },
      new Object[] { date, 1.4, "sec", chSec },
    };
  }

  @Test(dataProvider = "provideDates")
  public void testInc(Object obj, Number amount, String unit, Calendar ans) {
      Object[] paras = new Object[3];
      paras[0] = obj;
      paras[1] = amount;
      paras[2] = unit;
      
      Assert.assertEquals(paras.length, 3);
      Assert.assertEquals(invoke("inc", obj, amount, unit), ans);
  }
  
/*
  @Test
  public void write() {
    throw new RuntimeException("Test not implemented");
  }
  */
}
