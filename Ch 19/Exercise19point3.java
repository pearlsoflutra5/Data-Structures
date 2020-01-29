/*
Author: Kacie Rae
Date: 1-28-20

Description: Removing duplicates from an Integer ArrayList.
 */
import java.util.ArrayList;

public class Exercise19point3 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(14);
    list.add(24);
    list.add(14);
    list.add(42);
    list.add(25);
    ArrayList<Integer> newList = removeDuplicates(list);
    System.out.print(newList);
  }
  
  public static <Integer> ArrayList<Integer> removeDuplicates (ArrayList<Integer> list) {
      ArrayList<Integer> list2 = new ArrayList<Integer>();
      for (Integer element : list) { 
        if (!list2.contains(element)) { 
          list2.add(element); 
        } 
      }
      return list2; 
  }
}