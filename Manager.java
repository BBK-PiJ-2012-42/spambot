/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spambot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tnealo01
 */
public class Manager {
    private static Set<String> links = new HashSet<>();
    private static Set<String> visitedLinks = new HashSet<>();
    private static Set<String> emails = new HashSet<>();
    
    private String seed = "http://www.dcs.bbk.ac.uk";
    
    public static void main(String[] args) {
        Manager myManager = new Manager();
        myManager.spawnCrawlers(4);
        printEmails();

    }
    
    public static void printEmails() {
        System.out.println();
        System.out.println("Gathered emails");
        Iterator<String> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            System.out.println(emailIterator.next());
        }        
    }
    
    public void spawnCrawlers(int threadCount) {
        List<Thread> threadList = new ArrayList<>();
        threadList.add(new Thread(new Crawler(links, visitedLinks, emails, seed, 0)));
        for (int i = 1; i < threadCount; i++) {
            threadList.add(new Thread(new Crawler(links, visitedLinks, emails, i)));
        }
        
        
        
        for (Thread i : threadList) {
            i.start();
        }
        
//        new Thread(new Crawler(links, visitedLinks, emails, seed, 0)).start();
//        new Thread(new Crawler(links, visitedLinks, emails, 1)).start();
                
    }
    
    
}
