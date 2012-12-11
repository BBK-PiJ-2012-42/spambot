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
    public static String testString = "<TD>E-Mail: <A HREF=\"mailto:nigel@dcs.bbk.ac.uk\">jim@dcs.bbk.ac.uk</a></TD> My research interests centre href=\"http://www.dcs.bbk.ac.uk/access_keys_desc.html\" on information management";

    public static void main(String[] args) {
        PageGetter myGetter = new PageGetter("http://www.dcs.bbk.ac.uk/dcswiki/index.php/Main_Page");
        myGetter.getLinks(myGetter.getPage());
//        Pattern ValidURI;    
//        final String MAIL_REGEX = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
//        final String LINK_REGEX = "href=\"[^>]*\">";
//        Pattern htmltag = Pattern.compile("<a\\b[^>]*href=\"[^>]*>(.*?)</a>");
//        Scanner myScanner = new Scanner(testString);
//        String result = myScanner.findInLine(MAIL_REGEX);
//        System.out.println(result);
//        result = myScanner.findInLine(LINK_REGEX);
//        System.out.println(result);
//        result = myScanner.findInLine(htmltag);
//        System.out.println(result);
    }
}
