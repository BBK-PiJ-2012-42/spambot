/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spambot;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author tnealo01
 */
public class PageReader implements WebPage {
    private static int maxLinks = 50;
    private String url;
    private BufferedReader bufferedText = null;
    private InputStream myInput = null;
    private Set<String> mySet = null;
    
    final private String MAIL_REGEX = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
    final private String LINK_REGEX = "href=\'[^>]*\'>";
    
    public PageReader(String url) {
        this.url = url;
    }
            
    public String getUrl() {
        return url;
    }
    
    public Set<String> getLinks() {
        if(mySet.equals(null)) {
            mySet = new HashSet<>();
            Scanner myScanner = new Scanner(getInputText());
            String result;
            boolean b = true;
            while(b == true) {
                result = myScanner.findInLine(LINK_REGEX);
                mySet.add(result);
                System.out.println(result);
                if(result == null) {
                    b = false;
                }
            }
            return mySet;     
        } else {
            return mySet;
        }
    }
    
    
    
    
    
    private InputStream getInputText() {
        if(myInput.equals(null)) {
            try {
                myInput = new URL(url).openStream();
                //InputStreamReader myInputStreamReader = new InputStreamReader(myInput);
                //BufferedReader myBufferedReader = new BufferedReader(myInputStreamReader);
                //bufferedText = myBufferedReader;
                //return myBufferedReader;
                return myInput;
            } catch(IOException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return myInput;
        }
    }
    
    
}
