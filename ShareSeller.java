
package assignmentOne;

import java.util.LinkedList;
import java.util.Queue;

public class ShareSeller {
       
    private Queue<Share> shares;
    //Total balance
    double currentMoney;
    
    public ShareSeller(){
        shares = new LinkedList<Share>();
        this.currentMoney = 0.0;
    }
    
    //Helper class
    private class Share{
        double buyPrice;
         
        private Share(double buyPrice){
            this.buyPrice = buyPrice;    
        }
    }
    
    /*
    @param str: phrase to be proccessed
    TODO: Split string into words, and process them using only the correct words (like prices...)
    */
    public void read(String str){
        str = str.toLowerCase();
        String[] words = str.split("\\s+");
        if(words.length < 6){
            return;
        }
        
        if(words[0].equals("buy")){
            buy( Integer.parseInt(words[1]) , Double.parseDouble( words[4].substring(1) ) );
            
            
        }else if(words[0].equals("sell")){
            sell( Integer.parseInt(words[1]) ,Double.parseDouble( words[4].substring(1) ) );
        }
    }
    
    /*
    @param quantity: number of shares to buy
    @param price: price of each share
    */
    public void buy(int quantity, double price){
        for(int i=1; i<= quantity; i++){
            shares.add(new Share(price));
        }
        double lastMoney = this.currentMoney;
        this.currentMoney -= (quantity * price);
        System.out.println( " [BUY] Balance: " + this.currentMoney + ", Profit: " + 
                (this.currentMoney - lastMoney) + ", Shares: " + shares.size() );
    }
    
    /*
    @param quantity: number of owned shared to sell
    @param price: price of each share
    */
    public void sell(int quantity, double price){
        if(quantity <= shares.size()){
            double lastMoney = this.currentMoney;
            for(int i=0; i<quantity; i++){
                shares.remove();
            }
            this.currentMoney += (quantity * price);
            System.out.println( "[SELL] Balance: " + this.currentMoney + ", Profit: " + 
                (this.currentMoney - lastMoney) + ", Shares: " + shares.size() );
        }else{
            System.out.println("Error: do not have enough shares to sell");
        }
    }
    
    

    
}
