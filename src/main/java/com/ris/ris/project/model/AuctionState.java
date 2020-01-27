package com.ris.ris.project.model;

/*Quick explanation of states:
* READY - user has entered data for the Auction but has not published it yet
* ACTIVE - auction is active and new bids can be submitted
* FINISHED - auction is finished
* SUSPENDED - to be implemented, admin can suspend an auction if it contents do not comply with rules (ex. user selling a weapon, text contains curse words, etc.)
* */
public enum AuctionState {
    READY, ACTIVE, FINISHED, SUSPENDED
}
