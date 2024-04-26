package com.skcet;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class BraveDriver extends ChromeDriver {
    
    static ChromeOptions options =  new ChromeOptions().setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
    
    public BraveDriver()    {
        super(ChromeDriverService.createDefaultService(),options);
    }
}
