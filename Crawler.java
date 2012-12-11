/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spambot;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Crawler {
	private Set<String> links = HashSet<String>;
	private Set<String> visitedLinks = null;
	private Set<String> emails = null;
	private Set<String> subLinkSet = null; //for testing purposes
	
	public Crawler(String url) { //adds seed website upon construction of crawler.
		links = new HashSet<>();
		links.add(url);
	}
	
	public Crawler() { //used during testing only
		super();
	}
	
	
	private void addLinks(Set<String> linkSubSet) {
		if (!visitedLinks.isEmpty()) {
			Iterator<String> itr = linkSubSet.iterator();
			while (itr.hasNext()) {
				if (visitedLinks.contains(itr.next())) {
					linkSubSet.remove(itr.next());
				}
			}
		}
		else {			
			links.addAll(subLinkSet);
		}
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
            
                subLinkSet = new HashSet<>();
                subLinkSet.add("http://www.dcs.bbk.ac.uk/dcswiki/index.php/Main_Page");
                this.addLinks(subLinkSet);
                
		Iterator<String> iterator = links.iterator();
                String nextUrl;
		while (iterator.hasNext() && PageReader.getCount() <= 50) {	
                        nextUrl = iterator.next();
			System.out.println(nextUrl); //test print
			PageReader pageReader = new PageReader(nextUrl);			
			addEmails(pageReader.getEmails()); 
			addLinks(pageReader.getLinks());
			addToVisitedSet(nextUrl);
		}	
			
	}
}
			
	

