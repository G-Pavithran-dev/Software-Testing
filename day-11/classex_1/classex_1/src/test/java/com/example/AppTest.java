package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    @Test
    public void shouldAnswerdfWithTrue() 
    {
        try{

            Logger logger=LogManager.getLogger(AppTest.class);
            logger.info("adkj");
            logger.warn("dikus");
            logger.error("sjjs");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
      
        
    }
}
