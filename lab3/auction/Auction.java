import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Show Lots status
     */
    public void close() {
        for (Lot lot : lots) {
            String result = "";
            Bid highest = lot.getHighestBid();
            if (highest != null) {
                // 
                String name = highest.getBidder().getName();
                long value = highest.getValue();
                result = "Lot #" + lot.getNumber() + " (" + lot.getDescription() + ") => " + 
                                    "Highest Bid: " + value + ", from " + name;
            } else {
                // 
                result = "Lot #" + lot.getNumber() + " (" + lot.getDescription() + ") => No Bids received.";
            }
            
            System.out.println(result);
        }
   
    }
    
    /**
     * @return list of unsold lots
     */
    public ArrayList<Lot> getUnsold() {
        ArrayList<Lot> unsold = new ArrayList<>();

        for (Lot lot : lots) {
            if (lot.getHighestBid() == null) {
                // lot unsold => must add to list
                unsold.add(lot);
            }
        }
        
        return unsold;
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        // check if there are elements in the ArrayList
        if (lots.size() == 0) {
            // empty list => lot not found => return null
            return null;
        }
        
        // search for the lot
        for (Lot lot : lots) {
            // check the number
            if (lot.getNumber() == lotNumber) {
                // when found, just return this lot
                return lot;
            }
        }
        
        // lot not found => return null
        return null;
    }

    /**
     * Remove the lot with the given lot number.
     * @param number The number of the lot to be removed
     * @return The Lot with the given number, or null if
     * there is no such lot.
     */
    public Lot removeLot(int number) {
        Lot lot = getLot(number);
        
        if (lot != null) {
            // lot found => we need to remove it
            lots.remove(lot);
        }

        return lot;
    }
}
