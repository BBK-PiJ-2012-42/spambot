/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spambot;

import java.util.Set;

/**
 *
 * @author tnealo01
 */
public class Manager {
    PageReader myReader = new PageReader("http://www.dcs.bbk.ac.uk/~nigel/");
    Set<String> mySet = myReader.getEmails();
    
}
