/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;

import java.io.Serializable;

import dataStructures.DoubleList;
import dataStructures.Iterator;

public interface GalleryInterface extends Serializable{
	
	public static final DoubleList<User> users = new DoubleList<User>();
	
	public static final DoubleList<Work> works = new DoubleList<Work>();
	
	public static final DoubleList<Auction> auctions = new DoubleList<Auction>();
	
	/**
	 * Register a new user (collector) in the system. 
	 * To register a user, a login must always be entered, which must be unique.
	 * @param context
	 * @return User
	 * @throws Under18Exception
	 * @throws LoginNotUniqueException
	 */
	public UserPublicInterface addUser(String[] context) throws Under18Exception, LoginNotUniqueException;
	
	/**
	 * Register a new user (artist) in the system. 
	 * To register as an artist, you must always enter your login, which must be unique.
	 * @param context
	 * @return User
	 * @throws Under18Exception
	 * @throws LoginNotUniqueException
	 */
	public UserPublicInterface addArtist(String[] context) throws Under18Exception, LoginNotUniqueException;
	
	/**
	 * Removes a user (collector or artist) from the system. 
	 * @param context
	 * @return User
	 * @throws UserHasSubmitedProposalsException
	 * @throws UserDoesntExistException
	 * @throws ArtistWithWorksAtAuctionException
	 */
	public UserPublicInterface removeUser(String[] context) throws UserHasSubmitedProposalsException, UserDoesntExistException, 
			ArtistWithWorksAtAuctionException;
	
	/**
	 * Registers a new work of art in the system.
	 * @param context
	 * @return Work
	 * @throws WorkIdAlreadyExistsException
	 * @throws UserDoesntExistException
	 * @throws UserNotArtistException
	 */
	public WorkPublicInterface addWork(String[] context) throws WorkIdAlreadyExistsException, UserDoesntExistException, 
			UserNotArtistException;
	
	/**
	 * Query the generic data of a given user.
	 * @param context
	 * @return User
	 * @throws UserDoesntExistException
	 */
	public UserPublicInterface infoUser(String[] context) throws UserDoesntExistException;
	
	/**
	 * Query an artist's data.
	 * @param context
	 * @return User
	 * @throws UserDoesntExistException
	 * @throws UserNotArtistException
	 */
	public UserPublicInterface infoArtist(String[] context) throws UserDoesntExistException, UserNotArtistException;
	
	/**
	 * Query the data of a work of art.
	 * @param context
	 * @return Work
	 * @throws WorkDoesntExistException
	 */
	public WorkPublicInterface infoWork(String[] context) throws WorkDoesntExistException;
	
	/**
	 * Creates a new auction.
	 * To create an auction, the auction identifier must always be entered, which must be unique.
	 * @param context
	 * @return Auction
	 * @throws AuctionExistsException
	 */
	public AuctionPublicInterface createAuction(String[] context) throws AuctionExistsException;
	
	/**
	 * Add a work of art to an auction.
	 * @param context
	 * @return Work
	 * @throws AuctionDoesntExistException
	 * @throws WorkDoesntExistException
	 */
	public WorkPublicInterface addWorkAuction(String[] context) throws AuctionDoesntExistException, WorkDoesntExistException;
	
	/**
	 * Make a proposal to purchase a work of art at auction.
	 * @param context
	 * @return Bid
	 * @throws WorkNotInAuctionException
	 * @throws AuctionDoesntExistException
	 * @throws UserDoesntExistException
	 * @throws ValBelowMinException
	 */
	public BidPublicInterface bid(String[] context) throws WorkNotInAuctionException, AuctionDoesntExistException,
			UserDoesntExistException, ValBelowMinException;
	
	/**
	 * Closes auction.
	 * @param context
	 * @return Iterator<Work>
	 * @throws AuctionDoesntExistException
	 */
	public Iterator<WorkInAuction> closeAuction(String[] context) throws AuctionDoesntExistException;
	
	/**
	 * List works at auction.
	 * @param context
	 * @return Iterator<Work>
	 * @throws AuctionDoesntExistException
	 * @throws AuctionHasNoWorksException
	 */
	public Iterator<Work> listAuctionWorks(String[] context) throws AuctionDoesntExistException, AuctionHasNoWorksException;
	
	public void listArtistWorks(String[] context);// 2nd phase
	
	/**
	 * List the proposals to purchase a work at auction.
	 * @param context
	 * @return Iterator<Bid>
	 * @throws AuctionDoesntExistException
	 * @throws WorkNotInAuctionException
	 */
	public Iterator<Bid> listBidsWork(String[] context) throws AuctionDoesntExistException, WorkNotInAuctionException;
	
 	
}
