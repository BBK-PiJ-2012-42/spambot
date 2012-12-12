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

public class Crawler implements Runnable {
//	private Set<String> links;
//	private Set<String> visitedLinks;
//	private Set<String> emails;
        private static Set<String> links = new HashSet<>();
        private static Set<String> visitedLinks = new HashSet<>();
        private static Set<String> emails = new HashSet<>();
        private String urlSeed;
        private static final int threads = 3;
        private Manager parent;
        
        
	private Set<String> subLinkSet = new HashSet<>(); //for testing purposes
	
	public Crawler(String url) { //adds seed website upon construction of crawler.
                this.urlSeed = url;
                links.add(urlSeed);
                
                //this.run();
	}
        
//	public Crawler(Set<String> links,
//            Set<String> visitedLinks,
//            Set<String> emails,
//            int threadNo) { //adds seed website upon construction of crawler.
//                this.links = links;
//                this.visitedLinks = visitedLinks;
//                this.emails = emails;
  
                //this.threadNo = threadNo;
                //this.run();
	//}
	
	
	
//	private synchronized void addLinks(Set<String> linkSubSet, String element) {
//                Set<String> removeSet = new HashSet<>();
//		if (!visitedLinks.isEmpty()) {
//			Iterator<String> itr = linkSubSet.iterator();
//			while (itr.hasNext()) {
//                                String currentLink = itr.next();
//				if (visitedLinks.contains(currentLink)) {
//                                        removeSet.add(currentLink);
//				}
//			}
//                        linkSubSet.removeAll(removeSet);
//                        
//		}		
//                links.addAll(linkSubSet);
//                addToVisitedSet(element);
//                links.remove(element);
//		
//	}
	
	
	private void addEmails(Set<String> subEmailSet) {
            emails.addAll(subEmailSet);
	}
	
//	private synchronized void addToVisitedSet(String url) {
//            visitedLinks.add(url);
//	}			
			
        private synchronized void printVisited() {
        Iterator itr = visitedLinks.iterator();
        System.out.println("VISITED");
        while (itr.hasNext()) {
        System.out.println(itr.next());
        }
        }


        private synchronized void printLinks() {
        Iterator itr = links.iterator();
        System.out.println("LINKS");
        while (itr.hasNext()) {
        System.out.println(itr.next());
        }
        }
        
    public void printEmails() {
        System.out.println();
        System.out.println("Gathered emails");
        Iterator<String> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            System.out.println(emailIterator.next());
        }        
    }
    
    public void startThreads() {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 1; i < threads; i++) {
            threadList.add(new Thread(new Crawler(urlSeed)));
        }
        for (Thread i : threadList) {
            i.start();
        }
    }
    

        
    public void run() {
            //System.out.println("Thread: "+threadNo+ " running!");
            Iterator<String> iterator = links.iterator(); //at this point iterator only has bbk.ac.uk, hence it gets reassigned at end of loop to include updated links.
            while (iterator.hasNext() && PageReader.getCount() <= 500 && emails.size() <= 10) {		
                String element = iterator.next();
                System.out.println(element); //test print
                PageReader testReader = new PageReader(element);	
                addEmails(testReader.getEmails());      
                Manager.addLinks(testReader.getLinks(), element);
                //addToVisitedSet(element);
                //links.remove(element);
                iterator = links.iterator();
                
            }
            printEmails();



    }
}
			
	

