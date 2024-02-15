/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;

import java.io.Serializable;

import dataStructures.*;

public class Auction implements AuctionPrivateInterface, AuctionPublicInterface, Serializable{

	private static final long serialVersionUID = 1L;
	DoubleList<WorkInAuction> worksInAuction;
	DoubleList<Work> works;
	DoubleList<Bid> bids;
	String id;

	public Auction(String id)
	{
		this.id = id;
		worksInAuction = new DoubleList<WorkInAuction>();
		works = new DoubleList<Work>();
		bids = new DoubleList<Bid>();
	}

	@Override
	public void addWorkAuction(Work work, WorkInAuction workInAuction) {
		worksInAuction.addLast(workInAuction);
		works.addLast(work);
	}
	
	@Override
	public String listWorks() {
		String res = "";
		Iterator<Work> it = works.iterator();
		while(it.hasNext()) {
			Work curr = it.next();
			String[] info = {String.valueOf(curr.getId()), curr.getName(), String.valueOf(curr.value), 
					curr.getAuthor().login, curr.getAuthor().name};
			res += String.join(" ", info);
			res += "\n";
		}
		return res;
	}

	@Override
	public WorkInAuction getWorkFromId(String id) {
		Iterator<WorkInAuction> it = worksInAuction.iterator();
 		while(it.hasNext())
 		{
 			WorkInAuction curr = it.next();
			 if (curr.getId().equals(id)) {
				 return curr;
			 }
		}
		return null;
	}
	
}
