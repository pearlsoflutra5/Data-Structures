/*
Author:Kacie Rae 
Date: 1-31-20
Description: Sorting an Array List.
*/
import java.util.ArrayList;
public class Exercise19point9 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(14);
		list.add(24);
		list.add(4);
		list.add(42);
		list.add(5);
		Exercise19point9.<Integer>sort(list);
		System.out.print(list);
	}
	public static <E extends Comparable<E>> void sort(ArrayList<E> list) { 
		
	}
}