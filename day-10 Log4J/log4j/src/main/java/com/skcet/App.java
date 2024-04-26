package com.skcet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    static Logger logger = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        System.out.println("\n Hello World \n");
        logger.info("This is info msg");
        logger.error("This is error msg");
        logger.warn("This is warning msg");
        logger.fatal("This is fatal msg");
    }
}
