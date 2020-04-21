/*
Author: Kacie Rae
Date: 3-24-20
Description: Adding elements to an ArrayList.
*/

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Exercise27point11 {
  public static void main(String[] args) {
    Set<String> set = new HashSet<String>(); 
    set.add("Smith");
    set.add("Anderson");
    set.add("Lewis");
    set.add("Cook"); 
    set.add("Smith");
    
    ArrayList<String> list = setToList(set);
    System.out.println(list);
  }
  
  public static <E> ArrayList<E> setToList(Set<E> s) {
    ArrayList<E> list = new ArrayList<>();
    for (E e: s) {
        list.add(e);
    }
    return list;
  }
  
}