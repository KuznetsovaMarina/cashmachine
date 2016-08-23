package cashmachine;

import cashmachine.exception.NotEnoughMoneyException;

import java.util.*;

/**
 *  class CurrencyManipulator keeps all information aboun chosen currency
 *
 *  String currencyCode - for example, USD
 *  Map<Integer, Integer> denominations - Map<value, amount>
 *
 *  void addAmount(int denomination, int count) - add entered value and amount to Map denominations
 *  int getTotalAmount() - amount of money for chosen currency
 *  boolean hasMoney() - returns "true", if some banknotes were added
 *  boolean isAmountAvailable(int expectedAmount) - returns "true", if the user has enough money for withdraw operation
 *  Map<Integer, Integer> withdrawAmount(int expectedAmount) - map contains set of value and amount of withdraw banknotes.
 *  Method returns the minimal quantity banknotes, that need to compose expected amount("greedy algorithm" was used).
 */
public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<Integer, Integer>();

    public CurrencyManipulator(String currencyCode){
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        denominations.put(denomination, count);
    }

    public int getTotalAmount(){
        int sum = 0;
        for(Map.Entry<Integer, Integer> pair: denominations.entrySet()){
            sum += pair.getKey()*pair.getValue();
        }
        return sum;
    }

    public boolean hasMoney(){
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>();
        sortedMap.putAll(denominations);

        HashMap<Integer, Integer> resMap = new HashMap<Integer, Integer>();


        for (Integer i : sortedMap.descendingKeySet())
        {
            int denom = i;

            if (denom <= expectedAmount)
            {
                int count = expectedAmount / denom;
                expectedAmount -= denom * count;
                resMap.put(denom, count);
                if (expectedAmount == 0)
                {
                    break;
                }
                if (expectedAmount < 0)
                    throw new  NotEnoughMoneyException();
            }
        }
        if (expectedAmount > 0)
            throw new NotEnoughMoneyException();

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet())
        {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (resMap.containsKey(key))
            {
                if (value - resMap.get(key) != 0)
                    map.put(key, value - resMap.get(key));
            }
            else
                map.put(key, value);
        }
        denominations = map;
        return resMap;
    }
}
