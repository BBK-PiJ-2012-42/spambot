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
    
    private static String seed = "http://www.dcs.bbk.ac.uk/dcswiki/index.php/Main_Page";
    
    public static void main(String[] args) {
        Manager myManager = new Manager();
        //myManager.spawnCrawlers(2);
        Crawler myCrawler = new Crawler(seed);
        myCrawler.startThreads();
        

    }
    
    public static void printEmails() {
        System.out.println();
        System.out.println("Gathered emails");
        Iterator<String> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            System.out.println(emailIterator.next());
        }        
    }
    
    
    public static synchronized void addLinks(Set<String> linkSubSet, String element) {
            Set<String> removeSet = new HashSet<>();
            if (!visitedLinks.isEmpty()) {
                    Iterator<String> itr = linkSubSet.iterator();
                    while (itr.hasNext()) {
                            String currentLink = itr.next();
                            if (visitedLinks.contains(currentLink)) {
                                    removeSet.add(currentLink);
                            }
                    }
                    linkSubSet.removeAll(removeSet);

            }		
            links.addAll(linkSubSet);
            addToVisitedSet(element);
            links.remove(element);
    }
    
    public static synchronized void addToVisitedSet(String url) {
        visitedLinks.add(url);
    }
    
//    public void spawnCrawlers(int threadCount) {
//        List<Thread> threadList = new ArrayList<>();
//        threadList.add(new Thread(new Crawler(links, visitedLinks, emails, seed, 0)));
//        for (int i = 1; i < threadCount; i++) {
//            threadList.add(new Thread(new Crawler(links, visitedLinks, emails, i)));
//        }
//        
//        
//        
//        for (Thread i : threadList) {
//            i.start();
//        }
//        
//        printEmails();
//        
//}
        
//        new Thread(new Crawler(links, visitedLinks, emails, seed, 0)).start();
//        new Thread(new Crawler(links, visitedLinks, emails, 1)).start();
                
    }
    
    

