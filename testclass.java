/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spambot;


/**
 *
 * @author tnealo01
 */
public class testclass {
    
    // Run this class to see how PageReader works.

    public static void main(String[] args) {
        PageReader myReader = new PageReader("http://www.dcs.bbk.ac.uk/dcswiki/index.php/Main_Page");
        for (String link : myReader.getLinks()) {
            System.out.println(link);
        }
        System.out.println("//////////////////////////////////////////////////////////");
        System.out.println(myReader.getUrl());
        System.out.println("//////////////////////////////////////////////////////////");
        myReader = new PageReader("http://www.dcs.bbk.ac.uk/~nigel/");
        for (String emails : myReader.getEmails()) {
            System.out.println(emails);
        }
    }
}
