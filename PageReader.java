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
    // Not implemented now returns as many links as it can find.
    //private static int maxLinks = 50;
    private String url;
    // Current list of lines from the webpage.
    private List<String> pageLines = new ArrayList<>();
    
    public PageReader(String url) {
        this.url = url;
    }
            
    public String getUrl() {
        return url;
    }
    
    public Set<String> getLinks() {
        if (pageLines.isEmpty()) {
            pageLines = getPage();
        } 
        Set<String> myLinkSet = new HashSet<>(getListLinks(pageLines));
        return myLinkSet;
    }
    
    public Set<String> getEmails() {
        if (pageLines.isEmpty()) {
            pageLines = getPage();
        } 
        Set<String> myEmailSet = new HashSet<>(getListEmails(pageLines));
        return myEmailSet;
    }
    
    private List<String> getPage() {
        // Get the page and return a nice list of lines
        // for use by the link getter and email getter methods.
        List<String> lines = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            String line;
            do {
                line = bufferedReader.readLine();
                //System.out.println(line);
                lines.add(line);
            } while (line != null);  
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    
    private List<String> getListLinks(List<String> lines) {
        String result;
        List<String> links = new ArrayList<>();
        String linkRegex = "href=\"[^>]*\">";
        for(String each : lines) {
            try {
                Scanner myScanner = new Scanner(each);
                do {
                    result = myScanner.findInLine(linkRegex);
                    if (result != null) {
                        // Remove the beginning and end href=""
                        result = result.replaceFirst("href=\"", "");
                        result = result.replaceFirst("\">*", "");
                        // Remove anything left over after a space.
                        result = result.replaceFirst(" .*", "");
                        result = result.replaceFirst("&amp.*", "");
                        // Still does not append http://domain to the links without it or
                        // pass over emailto: and javascript: links.
                        links.add(result);
                        System.out.println(result);
                    }
                } while(result != null);
            } catch (NullPointerException e) {
                return links;
               //e.printStackTrace(); 
            }
        }      
        return links;        
    }
    
    private List<String> getListEmails(List<String> lines) {
        List<String> emails = new ArrayList<>();
        String result;
        String mailRegex = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
        for(String each : lines) {
            try {
                Scanner myScanner = new Scanner(each);
                do {
                    result = myScanner.findInLine(mailRegex);
                    if (result != null) {
                        emails.add(result);
                        System.out.println(result);
                    }
                } while(result != null);
            } catch (NullPointerException e) {
                return emails;
               //e.printStackTrace(); 
            }
        }  
        return emails;
    }
    
    
}
