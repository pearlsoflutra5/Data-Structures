/*
Author: Kacie Rae
Date: 1-28-20

Description: 
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
      for(list)
      
      if(list)
      return list2;
  }
  
}



public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
  { 
  
    // Create a new ArrayList 
    ArrayList<T> newList = new ArrayList<T>(); 
  
    // Traverse through the first list 
    for (T element : list) { 
  
      // If this element is not present in newList 
      // then add it 
      if (!newList.contains(element)) { 
  
        newList.add(element); 
      } 
    } 
  
    // return the new list 
    return newList; 
  } 
