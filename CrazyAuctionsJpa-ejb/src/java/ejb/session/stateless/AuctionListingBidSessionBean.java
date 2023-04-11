/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AuctionListing;
import entity.AuctionListingBid;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static oracle.jrockit.jfr.events.Bits.doubleValue;
import util.exception.BidIncrementException;
import util.exception.InvalidBidIncrementException;
import util.exception.ListingNotFoundException;
import util.exception.MinimumBidException;

/**
 *
 * @author xinyi
 */
@Stateless
public class AuctionListingBidSessionBean implements AuctionListingBidSessionBeanRemote, AuctionListingBidSessionBeanLocal {

    @PersistenceContext(unitName = "CrazyAuctionsJpa-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public AuctionListingBid createNewBid(BigDecimal price){
        AuctionListingBid newBid = new AuctionListingBid(price);
        em.persist(newBid);
        return newBid;
    }
    
    public void placeNewBid(String auctionName, BigDecimal price) throws MinimumBidException, BidIncrementException, InvalidBidIncrementException, ListingNotFoundException {
        AuctionListingBid newBid = createNewBid(price);
        Query query = em.createQuery("SELECT l FROM AuctionListing l WHERE l.auctionName = :inAuctionName");
        query.setParameter("inAuctionName", auctionName);
        AuctionListing currentListing;
        try{
            currentListing = (AuctionListing) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex){
            throw new ListingNotFoundException("Listing not found, please try again");
        }
        
        List<AuctionListingBid> listingBids = currentListing.getAuctionListingBids();
        if (listingBids.size() == 0){
            if (price.compareTo(new BigDecimal(0.05)) == 1 | price.compareTo(new BigDecimal(0.05)) == 0){
                listingBids.add(newBid);
            } else {
                throw new MinimumBidException("Minimum Bid is 0.0500");
            
            }
           
        } else {
            AuctionListingBid winningBid = currentListing.getAuctionListingBids().get(listingBids.size() - 1);
            BigDecimal winningBidPrice = winningBid.getBidPrice();
            if (winningBidPrice.doubleValue() >= 0.01 && winningBidPrice.doubleValue() <= 0.99){
                if (price.doubleValue() >= 0.05 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else if (winningBid.getBidPrice().doubleValue() <= 4.99 && winningBid.getBidPrice().doubleValue() >= 1.00){
                if (price.doubleValue() >= 0.05 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else if (winningBid.getBidPrice().doubleValue() <= 24.99 && winningBid.getBidPrice().doubleValue() >= 5.00){
                if (price.doubleValue() >= 0.50 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else if (winningBid.getBidPrice().doubleValue() <= 99.99 && winningBid.getBidPrice().doubleValue() >= 25.00){
                if (price.doubleValue() >= 1.00 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else if (winningBid.getBidPrice().doubleValue() <= 249.99 && winningBid.getBidPrice().doubleValue() >= 100.00){
                if (price.doubleValue() >= 2.50 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else if (winningBid.getBidPrice().doubleValue() <= 499.99 && winningBid.getBidPrice().doubleValue() >= 250.00){
                if (price.doubleValue() >= 5.00 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else if (winningBid.getBidPrice().doubleValue() <= 999.99 && winningBid.getBidPrice().doubleValue() >= 500.00){
                if (price.doubleValue() >= 10.00 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else if (winningBid.getBidPrice().doubleValue() <= 2499.99 && winningBid.getBidPrice().doubleValue() >= 1000.00){
                if (price.doubleValue() >= 25.00 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else if (winningBid.getBidPrice().doubleValue() <= 4999.99 && winningBid.getBidPrice().doubleValue() >= 2500.00) {
                if (price.doubleValue() >= 50.00 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else if (winningBid.getBidPrice().doubleValue() >= 5000){
                if (price.doubleValue() >= 100.00 + winningBid.getBidPrice().doubleValue()) {
                    listingBids.add(newBid);
                } else {
                    throw new BidIncrementException("Minimum Bid Increment is 0.05, please try again!");
                }
            } else {
                throw new InvalidBidIncrementException("Invalid bid! Please try again!");
            }
        }
    }

    
    

}