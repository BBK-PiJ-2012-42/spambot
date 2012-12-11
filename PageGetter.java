/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spambot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author tnealo01
 */
public class PageGetter {
    private String url;
    
    public PageGetter(String url) {
        this.url = url;
    }
    
    public List<String> getPage() {
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
    
    public List<String> getLinks(List<String> lines) {
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
                        // Still does not append http://domain to the links without it.
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
    
    public List<String> getEmails(List<String> lines) {
        List<String> emails = new ArrayList<>();
        return emails;
    }
    
}
