/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spambot;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Crawler implements Runnable {
	private Set<String> links;
	private Set<String> visitedLinks;
	private Set<String> emails;
        private String urlSeed;
        private int threadNo;
        
	private Set<String> subLinkSet = new HashSet<>(); //for testing purposes
	
	public Crawler(Set<String> links,
            Set<String> visitedLinks,
            Set<String> emails,
            String url,
            int threadNo) { //adds seed website upon construction of crawler.
                this.links = links;
                this.visitedLinks = visitedLinks;
                this.emails = emails;
                this.urlSeed = url;
                this.threadNo = threadNo;
                links.add(urlSeed);
                //this.run();
	}
        
	public Crawler(Set<String> links,
            Set<String> visitedLinks,
            Set<String> emails,
            int threadNo) { //adds seed website upon construction of crawler.
                this.links = links;
                this.visitedLinks = visitedLinks;
                this.emails = emails;
                this.threadNo = threadNo;
                //this.run();
	}
	
	
	
	private synchronized void addLinks(Set<String> linkSubSet, String element) {
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
		
	}
	
	
	private synchronized void addEmails(Set<String> subEmailSet) {
            emails.addAll(subEmailSet);
	}
	
	private synchronized void addToVisitedSet(String url) {
            visitedLinks.add(url);
	}			
			
        private synchronized void printVisited() {
        Iterator itr = visitedLinks.iterator();
        System.out.println("VISITED");
        while (itr.hasNext()) {
        System.out.println(itr.next());
        }
        }

        private synchronized void printEmails() {
        Iterator itr = emails.iterator();
        System.out.println("EMAILS");
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
        
    @Override
    public void run() {
            System.out.println("Thread: "+ threadNo+ " running!");
            Iterator<String> iterator = links.iterator(); //at this point iterator only has bbk.ac.uk, hence it gets reassigned at end of loop to include updated links.
            while (iterator.hasNext() && PageReader.getCount() <= 500 && emails.size() <= 10) {		
                String element = iterator.next();
                System.out.println("Thread: "+ threadNo+ " ::: "+element); //test print
                PageReader testReader = new PageReader(element);	
                addEmails(testReader.getEmails());      
                addLinks(testReader.getLinks(), element);
                //addToVisitedSet(element);
                links.remove(element);
                iterator = links.iterator();
            }



    }
}
			
	

