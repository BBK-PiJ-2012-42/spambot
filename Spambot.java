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
public class Spambot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Spambot().launch();
    }
    
    private void launch() {
        //new Manager();
        Crawler myCrawler = new Crawler("https://sites.google.com/site/r3dt3ddy23/Home/dash");
//        PageReader myReader = new PageReader("http://www.dcs.bbk.ac.uk/~nigel/");
//        Set<String> mySet = myReader.getLinks();
    }
        
}
