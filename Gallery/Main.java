/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import dataStructures.*;
import ArtGallery.*;

public class Main {
	
	static ArtGallery.Gallery gallery;
	private static final String fileName = "SavedGallery.dat";
	
	public static void main(String[] args) {
		boolean run = true;
		initialize(); // starts Gallery class.....
		
		Scanner in = new Scanner(System.in);
		String[] cmd = in.nextLine().split(" ");
		cmd[0] = cmd[0].toUpperCase();
		
		while (run) {
			switch (cmd[0]) {
			case "ADDUSER":
				addUser(in, cmd);
				break;
			case "ADDARTIST":
				addArtist(in, cmd);
				break;
			case "REMOVEUSER":
				removeUser(in, cmd);
				break;
			case "ADDWORK":
				addWork(in, cmd);
				break;
			case "INFOUSER":
				infoUser(in, cmd);
				break;
			case "INFOARTIST":
				infoArtist(in, cmd);
				break;
			case "INFOWORK":
				infoWork(in, cmd);
				break;
			case "CREATEAUCTION":
				createAuction(in, cmd);
				break;
			case "ADDWORKAUCTION":
				addWorkAuction(in, cmd);
				break;
			case "BID":
				bid(in, cmd);
				break;
			case "CLOSEAUCTION":
				closeAuction(in, cmd);
				break;
			case "LISTAUCTIONWORKS":
				listAuctionWork(in, cmd);
				break;
			case "LISTARTISTWORKS":
				listArtistWork(in, cmd);
				break;
			case "LISTBIDSWORK":
				listBidsWork(in, cmd);
				break;
			case "LISTWORKSBYVALUE":
				listWorksByValue(in, cmd);
				break;
			case "QUIT":
				quit();
				run = false;
				break;
			default: 
				break;
			}
			if (run == true)
			{
				cmd = in.nextLine().split(" ");
				cmd[0] = cmd[0].toUpperCase();
			}
		}
		
	}
	
	
	public static void initialize()
	{
		gallery = load();
	}

	public static void store(Gallery gal )
	{
		 try{
			 FileOutputStream f = new FileOutputStream(new File(fileName));
			 ObjectOutputStream o = new ObjectOutputStream(f);
			 o.writeObject(gal);
			 
			 o.close();
			 f.close(); 
		 }
		 catch ( IOException e )
		 {
			//System.out.println("GALLERY STORE UNSUCCESFUL");
		 }
	}

	public static Gallery load( )
	{
		Gallery gal = new Gallery();
		 try{
			FileInputStream fi = new FileInputStream(new File(fileName));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			gal = (Gallery) oi.readObject();
			
			oi.close();
			fi.close();
		 }
		 catch ( IOException e )
		 {
			 //System.out.println("GALLERY LOAD UNSUCCESFUL");
			 return new Gallery();
		 }
		 catch ( ClassNotFoundException e )
		 	{
			 //System.out.println("GALLERY NOT FOUND");
			 return new Gallery();
		 	}
		 return gal;
	}
	
	
	private static void addUser(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 5, true, false, false);
		//STARTS ADD USER FUNCTION in context = ["ADDUSER", "login/password", "name", "age", "email"]
		try
		{			
			gallery.addUser(context);
			System.out.println();
			System.out.println("Registo de utilizador executado.");
			System.out.println();
		}
		catch(Under18Exception e)
		{
			System.out.println();
			System.out.println("Idade inferior a 18 anos.");
			System.out.println();
		}
		catch(LoginNotUniqueException e)
		{
			System.out.println();
			System.out.println("Utilizador existente.");
			System.out.println();
		}	
	}
	
	private static void addArtist(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 6, false, true, false);
		
		//STARTS ADD ARTIST FUNCTION in context = ["ADDARTIST", "login/password", "name", "artistic name", "age", "email"]
		try
		{			
			gallery.addArtist(context);
			System.out.println();
			System.out.println("Registo de artista executado.");
			System.out.println();
		}
		catch(Under18Exception e)
		{
			System.out.println();
			System.out.println("Idade inferior a 18 anos.");
			System.out.println();
		}
		catch(LoginNotUniqueException e)
		{
			System.out.println();
			System.out.println("Utilizador existente.");
			System.out.println();
		}
	}
	
	private static void removeUser(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 2, false, false, false);
		//STARTS REMOVE USER FUNCTION in context = ["REMOVEUSER", "login"]
		try
		{	
			gallery.removeUser(context);
			System.out.println();
			System.out.println("Remocao de utilizador executada.");
			System.out.println();
		}
		catch(UserDoesntExistException e)
		{
			System.out.println();
			System.out.println("Utilizador inexistente.");
			System.out.println();
		}
		catch(UserHasSubmitedProposalsException e)
		{
			System.out.println();
			System.out.println("Utilizador com propostas submetidas.");
			System.out.println();
		}
		catch(ArtistWithWorksAtAuctionException e)
		{
			System.out.println();
			System.out.println("Artista com obras em leilao.");
			System.out.println();
		}
	}
	
	private static void addWork(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 5, false, false, true);
		//STARTS ADD WORK FUNCTION in context = ["ADDWORK", "id work", "login author", "year", "name of work"]
		try
		{
			gallery.addWork(context);
			System.out.println();
			System.out.println("Registo de obra executado.");
			System.out.println();
		}
		catch(WorkIdAlreadyExistsException e)
		{
			System.out.println();
			System.out.println("Obra existente.");
			System.out.println();
		}
		catch(UserDoesntExistException e)
		{
			System.out.println();
			System.out.println("Utilizador inexistente.");
			System.out.println();
		}
		catch(UserNotArtistException e)
		{
			System.out.println();
			System.out.println("Artista inexistente.");
			System.out.println();
		}
	}
	
	private static void infoUser(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 2, false, false, false);
		//STARTS INFO USER FUNCTION in context = ["INFOUSER", "login"]
		try
		{
			UserPublicInterface user = gallery.infoUser(context);
			System.out.println();
			System.out.println(user.getInfoUser());
			System.out.println();
		}
		catch (UserDoesntExistException e)
		{
			System.out.println();
			System.out.println("Utilizador inexistente.");
			System.out.println();
			
		}
	}
	
	private static void infoArtist(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 2, false, false, false);
		//STARTS INFO ARTIST FUNCTION in context = ["INFOARTIST", "login"]
		try
		{		
			UserPublicInterface user = gallery.infoArtist(context);
			System.out.println();
			System.out.println(user.getInfoArtist());
			System.out.println();
		}
		catch (UserDoesntExistException e)
		{
			System.out.println();
			System.out.println("Utilizador inexistente.");
			System.out.println();
		}
		catch (UserNotArtistException e)
		{
			System.out.println();
			System.out.println("Artista inexistente.");
			System.out.println();
		}
	}
	
	private static void infoWork(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 2, false, false, false);
		//STARTS INFO WORK FUNCTION in context = ["INFOWORK", "id_work"]
		try
		{
			WorkPublicInterface work = gallery.infoWork(context);
			System.out.println();
			System.out.println(work.infoWork());
			System.out.println();
		}
		catch(WorkDoesntExistException e)
		{
			System.out.println();
			System.out.println("Obra inexistente.");
			System.out.println();
		}
	}
	
	private static void createAuction(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 2, false, false, false);
		//STARTS CREATE AUCTION FUNCTION in context = ["CREATEAUCTION", "id_auction"]
		try
		{
			gallery.createAuction(context);
			System.out.println();
			System.out.println("Registo de leilao executado.");
			System.out.println();
		}
		catch (AuctionExistsException e)
		{
			System.out.println();
			System.out.println("Leilao existente.");
			System.out.println();
		}
	}
	
	private static void addWorkAuction(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 4, false, false, false);
		//STARTS ADD WORK AUCTION in context = ["ADDWORKAUCTION", "id_auction", "id_work", "minimal value"]
		try
		{
			gallery.addWorkAuction(context);
			
			System.out.println();
			System.out.println("Obra adicionada ao leilao.");
			System.out.println();
			
		}
		catch (AuctionDoesntExistException e)
		{
			System.out.println();
			System.out.println("Leilao inexistente.");
			System.out.println();
		}
		catch (WorkDoesntExistException e)
		{
			System.out.println();
			System.out.println("Obra inexistente.");
			System.out.println();
		}
	}
	
	private static void bid(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 5, false, false, false);
		//STARTS BID in context = ["BID", "id_auction", "id_work", "login", "value"]
		try
		{
			gallery.bid(context);
			System.out.println();
			System.out.println("Proposta aceite.");
			System.out.println();
		}
		catch (UserDoesntExistException e)
		{
			System.out.println();
			System.out.println("Utilizador inexistente.");
			System.out.println();
		}
		catch (AuctionDoesntExistException e)
		{
			System.out.println();
			System.out.println("Leilao inexistente.");
			System.out.println();
		}
		catch (WorkNotInAuctionException e)
		{
			System.out.println();
			System.out.println("Obra inexistente no leilao.");
			System.out.println();
		}
		catch (ValBelowMinException e)
		{
			System.out.println();
			System.out.println("Valor proposto abaixo do valor minimo.");
			System.out.println();
		}
			
	}
	
	private static void closeAuction(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 2, false, false, false);
		//STARTS CLOSE AUCTION in context = ["CLOSEAUCTION", "id_auction"]
		try
		{
			Iterator<WorkInAuction> works = gallery.closeAuction(context);
			System.out.println();
			System.out.println("Leilao encerrado.");
			
			//prints closed works in auction
			while(works.hasNext())
	 		{
	 			WorkInAuction work = works.next();
	 			if (work.finalBid != null) 
	 			{
	 				Bid bid = work.finalBid;
	 				System.out.println(work.getId()+" "+work.getName()+" "+bid.getBidder().getLogin()+" "+
	 				bid.getBidder().getName()+" "+bid.getValue());
	 			}
	 			else 
	 			{
	 				System.out.println(work.getId()+" "+work.getName()+" sem propostas de venda.");
	 			}
	 		}
			System.out.println();
		}
		catch(AuctionDoesntExistException e)
		{
			System.out.println();
			System.out.println("Leilao inexistente.");
			System.out.println();
		}
	}
	
	private static void listAuctionWork(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 2, false, false, false);
		//STARTS CLOSE AUCTION in context = ["LISTAUCTIONWORK", "id_auction"]
		try
		{
			Iterator<Work> works = gallery.listAuctionWorks(context);
			System.out.println();
			//print works in auction
			while(works.hasNext())
	 		{
	 			Work work = works.next();
	 			System.out.println(work.infoWork());
	 		}
			System.out.println();
			
		}
		catch (AuctionDoesntExistException e)
		{
			System.out.println();
			System.out.println("Leilao inexistente.");
			System.out.println();
		}
		catch (AuctionHasNoWorksException e)
		{
			System.out.println();
			System.out.println("Leilao sem obras.");
			System.out.println();
		}
	}
	
	private static void listArtistWork(Scanner in, String[] line)
	{
		//String[] context = parseInput(in, line, 2, false, false, false);
		//STARTS LIST ARTIST WORK in context = ["LISTARTISTWORK", "login"]
		//2nd phase
	}
	
	private static void listBidsWork(Scanner in, String[] line)
	{
		String[] context = parseInput(in, line, 3, false, false, false);
		//STARTS LIST BIDS WORK in context = ["LISTBIDSWORK", "id_auction", "id_work"]	
		try
		{
			Iterator<Bid> bids = gallery.listBidsWork(context);
			
			System.out.println();
			while(bids.hasNext())
	 		{
	 			Bid bid = bids.next();
	 			System.out.println(bid.getBidder().login+" "+bid.getBidder().getName()+" "+bid.getValue());
	 		}
			System.out.println();
		}
		catch (AuctionDoesntExistException e)
		{
			System.out.println();
			System.out.println("Leilao inexistente.");
			System.out.println();
		}
		catch (WorkNotInAuctionException e)
		{
			System.out.println();
			System.out.println("Obra inexistente no leilao.");
			System.out.println();
		}
		catch (AuctionHasNoWorksException e)
		{
			System.out.println();
			System.out.println("Obra sem propostas.");
			System.out.println();	
		}
	}
	
	private static void listWorksByValue(Scanner in, String[] line)
	{
		//String[] context = parseInput(in, line, 1, false, false, false);
		//STARTS LIST WORKS BY VALUE in context = ["LISTWORKSBYVALUE"]
		//2nd phase
	}
	
	private static void quit()
	{
		//calls quit function from gallery
		store(gallery);
		System.out.println();
		System.out.println("Obrigado. Ate a proxima.");
	}
	
	// Helper function: puts input in string array
	private static String[] parseInput(Scanner in, String[] line, int size, boolean isAddUser, boolean isAddArtist, boolean isAddWork)
	{
		String[] out = new String[size];	
		int i = 0;
		
		if (isAddUser) {
			String joined = "";
			for (String current : line){
	 			if (i < 2) {	
		 			out[i] = current;
		 			i ++;
	 			} else {
	 				joined = joined + current + " ";
	 			}
			}
			out[i] = joined.trim();
			i++;
			
	 		for (int e = 0; e < 1; e ++) {
				line = in.nextLine().split(" ");
				for (String current : line) {
					out[i] = current;
					i ++;
				}
	 		}
			
		} else if (isAddArtist) {
			String joined = "";
			for (String current : line){
	 			if (i < 2) {	
		 			out[i] = current;
		 			i ++;
	 			} else {
	 				joined = joined + current + " ";
	 			}
			}
			out[i] = joined.trim();
			i++;
			
	 		for (int e = 0; e < 2; e ++) {
	 			if(e == 1)
	 			{	
					line = in.nextLine().split(" ");
					for (String current : line) {
						out[i] = current;
						i ++;
					}
	 			}
	 			else
	 			{
	 				joined = "";
					line = in.nextLine().split(" ");
					for (String current : line) {
						joined = joined + current + " ";
					}
					out[i] = joined.trim();
					i++;
	 			}
	 		}
			
		} else if (isAddWork) {
			String joined = "";
			for (String current : line){
	 			if (i < 4) {	
		 			out[i] = current;
		 			i ++;
	 			} else {
	 				joined = joined + current + " ";
	 			}
			}
			out[i] = joined.trim();
			i++;
			
		} else {
			for (String current : line){	
		 		out[i] = current;
		 		i ++;
			}

		}
		
 		return out;
	}
	
	
}
