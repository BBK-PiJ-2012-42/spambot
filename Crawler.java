/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spambot;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Crawler {
	private Set<String> links = new HashSet<>();
	private Set<String> visitedLinks = new HashSet<>();
	private Set<String> emails = new HashSet<>();
	private Set<String> subLinkSet = new HashSet<>(); //for testing purposes
	
	public Crawler(String url) { //adds seed website upon construction of crawler.
		links = new HashSet<>();
		links.add(url);
	}
	
	public Crawler() { //used during testing only
		super();
	}
	
	
	private void addLinks(Set<String> linkSubSet) {
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
		
	}
	
	
	private void addEmails(Set<String> subEmailSet) {
		if (emails == null) {
			emails = new HashSet<String>();
			emails.addAll(subEmailSet);
		}
		else {
			emails.addAll(subEmailSet);
		}
	}
	
	private void addToVisitedSet(String url) {
		if (visitedLinks == null) {
			visitedLinks = new HashSet<String>();
			visitedLinks.add(url);
		}
		else {
			visitedLinks.add(url);
		}
	}			
			
	
				
	
	public static void main(String[] args) {
	
		Crawler crawler = new Crawler();
		crawler.launch();
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
        
	private void launch() {
	
		/**
		* Test: OK
		subLinkSet = new HashSet<String>();
		subLinkSet.add("Hello");
		subLinkSet.add("item1");
		subLinkSet.add("item2");		
		
		this.addLinks(subLinkSet);
		*/

		/**
		* For each link in the main set, invokes a PageReader to visit the link and parse the page,
		* while the set of links isn't empty and the number of sites visited is less than 51.
		*/
            
                //subLinkSet = new HashSet<>();
                //subLinkSet.add("https://sites.google.com/site/r3dt3ddy23/Home/dash");
                subLinkSet.add("http://news.bbc.co.uk");
                this.addLinks(subLinkSet);
                
                
                
//		Iterator<String> linkIterator = links.iterator();
//                String nextUrl;
//		while (linkIterator.hasNext() && PageReader.getCount() <= 50) {	
//                        nextUrl = linkIterator.next();
//			System.out.println(nextUrl); //test print
//			PageReader pageReader = new PageReader(nextUrl);			
//			addEmails(pageReader.getEmails()); 
//			addLinks(pageReader.getLinks());
//			addToVisitedSet(nextUrl);
//                        linkIterator = links.iterator();
//		}
                
                Iterator<String> iterator = links.iterator(); //at this point iterator only has bbk.ac.uk, hence it gets reassigned at end of loop to include updated links.
                while (iterator.hasNext() && PageReader.getCount() <= 50 && emails.size() <= 5) {	
                //while (iterator.hasNext()) {	
                    String element = iterator.next();
                    System.out.println(element); //test print
                    PageReader testReader = new PageReader(element);	
                    addEmails(testReader.getEmails());
//                    
//                    Iterator<String> emailIterator = testReader.getLinks().iterator();
//                    while (emailIterator.hasNext()) {
//                        System.out.println("Checking:::: " + emailIterator.next());
//                    }
                    
                            
                            
                    addLinks(testReader.getLinks());
                    addToVisitedSet(element);
//                    this.printEmails();
                    //this.printLinks();
//                    this.printVisited();
                    links.remove(element);
                    iterator = links.iterator();
                }
                
                System.out.println();
                System.out.println("Gathered emails");
                Iterator<String> emailIterator = emails.iterator();
                while (emailIterator.hasNext()) {
                    System.out.println(emailIterator.next());
                }
			
	}
}
			
	

