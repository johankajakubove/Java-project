/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;

import java.io.Serializable;
import dataStructures.*;

public class Gallery implements GalleryInterface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DoubleList<User> users;
	DoubleList<Work> works;
	DoubleList<Auction> auctions;
	
	
	public Gallery()
	{
		users = new DoubleList<User>();
		works = new DoubleList<Work>();
		auctions = new DoubleList<Auction>();
		
	}
	
	
	public UserPublicInterface addUser(String[] context) throws Under18Exception, LoginNotUniqueException
	{

		int age = Integer.valueOf(context[3]);
		if (age >= 18)
		{
			if (loginUnique(context[1]))
			{
				//Successful login
				User newUser = new User(context[1], context[2], "", age, context[4], false);
				users.addLast(newUser);
				return newUser;
			}
			else
			{
				//Login not Unique
				throw new LoginNotUniqueException();
			}
		}
		else
		{
			//Under 18
			throw new Under18Exception();
		}
	}
	
	public UserPublicInterface addArtist(String[] context) throws Under18Exception, LoginNotUniqueException
	{

		int age = Integer.valueOf(context[4]);
		if (age >= 18)
		{
			if (loginUnique(context[1]))
			{
				//Successful login
				User newUser = new User(context[1], context[2], context[3], age, context[5], true);
				users.addLast(newUser);
				return newUser;
			}
			else
			{
				//Login not Unique
				throw new LoginNotUniqueException();
			}
		}
		else
		{
			//Under 18
			throw new Under18Exception();
		}

	}
	
	public UserPublicInterface removeUser(String[] context) throws UserHasSubmitedProposalsException, UserDoesntExistException, 
				ArtistWithWorksAtAuctionException
	{
		Iterator<User> current = users.iterator();
		int i = 0;

		User tmp = null;
		boolean remove = false;
		while(current.hasNext())
		{
			tmp = current.next();
			if (tmp.login.trim().toUpperCase().equals(context[1].trim().toUpperCase()))
			{
				remove = true;
				break;
			}
			i++;
		}
		//login doesn't exist
		if (remove == false) throw new UserDoesntExistException();
		
		//check if user has active bids
		Iterator<Auction> currentAuction = auctions.iterator();
		while (currentAuction.hasNext())
		{
			Auction auction = currentAuction.next();
			Iterator<Bid> it = auction.bids.iterator();
			while (it.hasNext())
			{
				Bid curr = it.next();
				if (curr.bidder.getLogin().equals(tmp.getLogin()))
				{
					throw new UserHasSubmitedProposalsException();
				}
			}
		}
		
		//if artist and has works at auction
		List<Work> ids = new DoubleList<Work>();
		if (tmp.isArtist())
		{
			//get IDs of artist works
			Iterator<Work> currentWork = works.iterator();
			while (currentWork.hasNext())
			{
				Work curr = currentWork.next();
				if (curr.getAuthor().getLogin().equals(tmp.getLogin()))
				{
					ids.addLast(curr);
				}
			}
			
			//check for IDs in auction
			currentAuction = auctions.iterator();
			while (currentAuction.hasNext())
			{
				Auction auction = currentAuction.next();
				Iterator<Work> it = ids.iterator();
				while (it.hasNext())
				{
					Work id = it.next();
					if (auction.getWorkFromId(id.getId()) != null)
					{
						throw new ArtistWithWorksAtAuctionException();
					}
				}
			}
		}

		//Successful removal
		if (remove)
		{
			if (tmp.isArtist())
			{
				//remove artists work
				Iterator<Work> it = ids.iterator();
				while(it.hasNext())
				{
					Work toRemove = it.next();
					works.remove(works.find(toRemove));
				}
				
			}
			users.remove(i);
			return tmp;
		}
		throw new UserDoesntExistException();
	}
	
	public WorkPublicInterface addWork(String[] context) throws WorkIdAlreadyExistsException, UserDoesntExistException, 
		UserNotArtistException
	{	
		String id = context[1];
		// find if ID isn't used yet
		Iterator<Work> it = works.iterator();
		while(it.hasNext()) {
			Work curr = it.next();
			 if (curr.getId().equals(id)) {
				 throw new WorkIdAlreadyExistsException();
			 }
			}
		
		// if user exists and it's an artist, add work
		User user = getUserFromLogin(context[2]);
		if (user == null) throw new UserDoesntExistException();
		else if (!user.isArtist()) throw new UserNotArtistException();
		else {
			Work work = new Work(id, user, Integer.valueOf(context[3]), context[4]);
			works.addLast(work);
			return work;
		}
		
	}
	
	public UserPublicInterface infoUser(String[] context) throws UserDoesntExistException
	{
		User tmp = getUserFromLogin(context[1]);
		if (tmp == null) throw new UserDoesntExistException();
		return tmp;
	}
	
	public UserPublicInterface infoArtist(String[] context) throws UserDoesntExistException, UserNotArtistException
	{
		User tmp = getUserFromLogin(context[1]);
		if (tmp == null) throw new UserDoesntExistException();
		else if (tmp.isArtist() == false) throw new UserNotArtistException();
		return tmp;
	}
	
	public WorkPublicInterface infoWork(String[] context) throws WorkDoesntExistException
	{
		Work work = getWorkFromId(context[1]);
		if (work != null) return work;
		else throw new WorkDoesntExistException();
	}
	
	public AuctionPublicInterface createAuction(String[] context) throws AuctionExistsException
	{
		String id = context[1];
		// find if ID isn't used yet
		Iterator<Auction> it = auctions.iterator();
		while(it.hasNext()) {
			Auction curr = it.next();
			 if (curr.id.equals(id)) {
				 throw new AuctionExistsException();
			 }
		}
		Auction auction = new Auction(id);
		auctions.addLast(new Auction(id));
		return auction;
	}
	
	public WorkPublicInterface addWorkAuction(String[] context) throws AuctionDoesntExistException, WorkDoesntExistException
	{
		Auction auction = getAuctionFromId(context[1]);
		if (auction == null) throw new AuctionDoesntExistException();
		
		Work work = getWorkFromId(context[2]);
		if (work == null) throw new WorkDoesntExistException();
		else {
			// check if work isn't in the Auction yet, then nothing happens
			Iterator<Work> it = auction.works.iterator();
			while(it.hasNext()) {
				Work curr = it.next();
				if (curr.getId().equals(work.getId())) return curr;
				}
			
			WorkInAuction newWork = new WorkInAuction(work, Integer.valueOf(context[3]));
			auction.addWorkAuction(work, newWork);
			return work;
		}
	}
	
	public BidPublicInterface bid(String[] context) throws WorkNotInAuctionException, AuctionDoesntExistException,
		UserDoesntExistException, ValBelowMinException
	{
		User bidder = getUserFromLogin(context[3]);
		if (bidder == null) throw new UserDoesntExistException();
		Auction auction = getAuctionFromId(context[1]);
		if (auction == null) throw new AuctionDoesntExistException();
		
		WorkInAuction work = auction.getWorkFromId(context[2]);
		if (work == null) throw new WorkNotInAuctionException();

		// passed all exceptions, adding bid to auction and assigning it to work
		int val = Integer.valueOf(context[4]);
		if (val < work.minValue) throw new ValBelowMinException();
		Bid bid = new Bid(val, bidder);
		work.addBid(bid);
		auction.bids.addLast(bid);
		return bid;
	}
	
	// iterates trough works in auction, gets the final bid and returns iterator of closed works in auction, 
	// auction has highest value null if there were no bids
	public Iterator<WorkInAuction> closeAuction(String[] context) throws AuctionDoesntExistException
	{
		Auction auction = getAuctionFromId(context[1]);
		if (auction == null) throw new AuctionDoesntExistException();
		Iterator<WorkInAuction> it = auction.worksInAuction.iterator();
		while(it.hasNext()) {
			WorkInAuction curr = it.next();
			curr.close();
		}
		int removeIndex = 0;
		Iterator<Auction> it1 = auctions.iterator();
 		while(it1.hasNext()) {
 			Auction curr = it1.next();
			 if (curr.id.equals(auction.id)) {
				 break;
			}
			 removeIndex++;
		}
 		
 		auctions.remove(removeIndex);
		
		return auction.worksInAuction.iterator();
	}
	
	//return iterator of works in auction
	public Iterator<Work> listAuctionWorks(String[] context) throws AuctionDoesntExistException, AuctionHasNoWorksException
	{
		Auction auction = getAuctionFromId(context[1]);
		if (auction == null) throw new AuctionDoesntExistException();
		if (auction.works.isEmpty()) throw new AuctionHasNoWorksException();
		return auction.works.iterator();
	}
	
	public void listArtistWorks(String[] context)
	{
		// 2nd phase
	}
	
	//return iterator of work's bids
	public Iterator<Bid> listBidsWork(String[] context) throws AuctionDoesntExistException, WorkNotInAuctionException, AuctionHasNoWorksException
	{
		Auction auction = getAuctionFromId(context[1]);
		if (auction == null) throw new AuctionDoesntExistException();
		WorkInAuction work = auction.getWorkFromId(context[2]);
		if (work == null) throw new WorkNotInAuctionException();
		if (work.bids.isEmpty()) throw new AuctionHasNoWorksException();
		
		return work.bids.iterator();
	}
	
	public void listWorksByValue(String[] context)
	{
		// 2nd phase
	}
 	
 	private User getUserFromLogin(String login)
 	{
 		Iterator<User> current = users.iterator();
		while(current.hasNext())
		{
			User tmp = current.next();
			if (tmp.login.trim().toUpperCase().equals(login.trim().toUpperCase()))
			{
				return tmp;
			}
		}
		return null;
 	}
 	
 	private Work getWorkFromId(String id)
 	{
 		Iterator<Work> it = works.iterator();
 		
 		while(it.hasNext()) 
 		{
			Work curr = it.next();
			 if (curr.getId().equals(id)) return curr;
		}
		
		return null;
 	}
 	
 	private Auction getAuctionFromId(String id)
 	{
 		Iterator<Auction> it = auctions.iterator();
 		while(it.hasNext()) 
 		{
 			Auction curr = it.next();
			 if (curr.id.equals(id)) 
			 {
				 return curr;
			 }
		}
		return null;
 	}
 	
 	//check if login is unique
 	private boolean loginUnique(String login)
 	{
 		Iterator<User> current = users.iterator();
 		while(current.hasNext())
 		{
 			User tmp = current.next();
 			if (tmp.login.trim().equals(login.trim()))
 			{
 				return false;
 			}
 		}
 		return true;
  	}
	
}
