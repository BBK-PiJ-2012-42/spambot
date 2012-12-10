/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spambot;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author tnealo01
 */
public class testclass {
    public static String testString = "<TD>E-Mail: <A HREF='mailto:nigel@dcs.bbk.ac.uk'>jim@dcs.bbk.ac.uk</a></TD>";
    public static String testString2 = "My research interests centre href="http://www.dcs.bbk.ac.uk/access_keys_desc.html\' on information management";
    public static void main(String[] args) {
        Pattern ValidURI;    
        final String MAIL_REGEX = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
        final String LINK_REGEX = "href=\'[^>]*\'>";
        Scanner myScanner = new Scanner(testString);
        String result = myScanner.findInLine(MAIL_REGEX);
        System.out.println(result);
        result = myScanner.findInLine(LINK_REGEX);
        System.out.println(result);
        
        

    }
}
