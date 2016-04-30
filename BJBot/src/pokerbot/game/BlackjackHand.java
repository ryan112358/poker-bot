package pokerbot.game;

import java.util.ArrayList;
import java.util.List;

public class BlackjackHand {

    private List<Card> mycards;
    private List<Card> dealercards;
    private int dActiveAces;
    private int mActiveAces;

    public BlackjackHand() {
        this.mycards = new ArrayList<>();
        this.dealercards = new ArrayList<>();
        dActiveAces = mActiveAces = 0;
    }

    public void reset() {
        this.mycards = new ArrayList<>();
        this.dealercards = new ArrayList<>();
        dActiveAces = mActiveAces = 0;
    }

    public boolean canSplit() {
        return mycards.size() == 2 &&
                mycards.get(0).getValue() == 
                mycards.get(1).getValue();
    }
    
    public String getMyHandKey() {
        if (mycards.size() == 2) {
            int c1 = mycards.get(0).getValue();
            int c2 = mycards.get(1).getValue();
            if (c1 == 11)
                if (c2 == 11)
                    return "A-A";
                else
                    return "A-" + c2;
            else if(c2 == 11)
                return "A-" + c1;
            else if (c1 == c2)
                return c1 + "-" + c2;
            else
                return "" + (c1 + c2);
        } else if (mActiveAces > 0)
            return "Soft-" + getMyTotal();
        else
            return "" + getMyTotal();
    }

    public boolean hasDealt() {
        return mycards.size() == 2 && (dealercards.size() == 1 || dealercards.size() == 2);
    }

    public int getDealerHandKey() {
        return dealercards.get(0).getValue();
    }

    public boolean iBusted() {
        return getMyTotal() > 21;
    }

    public boolean dealerDone() {
        int total = getDealerTotal();
        return total > 17 || total == 17 && dActiveAces == 0;
    }

    public boolean iHaveBlackjack() {
        return getMyTotal() == 21 && mycards.size() == 2;
    }

    public boolean dealerHasBlackjack() {
        return getDealerTotal() == 21 && dealercards.size() == 2;
    }

    public boolean canInsure() {
        return getDealerTotal() == 11 && dealercards.size() == 1;
    }

    public boolean getActiveAce() {
        return mActiveAces > 0;
    }

    public double getAmountWon() {
        if (getMyTotal() == 21 && mycards.size() == 2) {
            if (getDealerTotal() == 21 && dealercards.size() == 2)
                return 0;
            else
                return 1.5;
        } else if (getDealerTotal() == 21 && dealercards.size() == 2)
            return -1;
        else if (getMyTotal() > 21)
            return -1;
        else if (getMyTotal() > getDealerTotal())
            return 1;
        else if (getMyTotal() == getDealerTotal())
            return 0;
        else
            return -1;
    }

    public int getNumCards() {
        return mycards.size();
    }

    public int getDealerTotal() {
        int total = 0;
        dActiveAces = 0;
        for (Card c : dealercards) {
            total += c.getValue();
            if (c.getValue() == 11)
                dActiveAces++;
        }
        while (total > 21 && dActiveAces > 0) {
            total -= 10;
            dActiveAces--;
        }
        return total;
    }

    public int getNumDealerCards() {
        return dealercards.size();
    }

    public void setMyCards(List<Card> c) {
        for (int i = mycards.size(); i < c.size(); i++)
            mycards.add(c.get(i));
    }

    public void setDealerCards(List<Card> c) {
        for (int i = dealercards.size(); i < c.size(); i++)
            dealercards.add(c.get(i));
    }

    public int getMyTotal() {
        int total = 0;
        mActiveAces = 0;
        for (Card c : mycards) {
            total += c.getValue();
            if (c.getValue() == 11)
                mActiveAces++;
        }
        while (total > 21 && mActiveAces > 0) {
            mActiveAces--;
            total -= 10;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Me: " + mycards + " (" + getMyTotal() + ")\nDealer: " + dealercards + " (" + getDealerTotal() + ")";
    }
    public void log() {
        int m = getMyTotal(), d = getDealerTotal();
        String r = m > 21 || (m < d && d <= 21) ? "Lose" : (m == d ? "Tie " : "Win ");
        System.out.println(r + " : " + m + " - " + d);
    }

}
