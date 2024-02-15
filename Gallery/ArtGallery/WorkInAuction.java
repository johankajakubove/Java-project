/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;

import dataStructures.*;
import java.io.Serializable;

public class WorkInAuction extends Work implements WorkPrivateInterface, WorkPublicInterface, Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Minimal bid value allowed.
	 */
	public int minValue;
	
	public Work work;
	
	DoubleList<Bid> bids;
	
	public Bid finalBid;
	
	public WorkInAuction(Work work, int minV) {
		super(work.getId(), work.getAuthor(), work.getYear(), work.getName());
		minValue = minV;
		this.work = work;
		bids = new DoubleList<Bid>();
	}
	
	public void addBid(Bid bid){
		bids.addLast(bid);
	}
	
	/**
	 * When auction is being closed, it chooses first highest (finalBid) bid and 
	 * in case it is the highest value yet, assigns it's value to the Work (work.value)
	 */
	public void close() {
		if (bids.isEmpty()) {
			finalBid = null;
		}
		else
		{			
			Bid res = bids.getFirst();
			Iterator<Bid> it = bids.iterator();
	 		
	 		while(it.hasNext()) {
	 			Bid curr = it.next();
				 if (curr.value > res.value) {
					 res = curr;
				 }
				}
	 		finalBid = res;
	 		if (work.getValue() < res.value) {
	 			work.setValue(res.value);
	 		}
		}
	}
	
}
