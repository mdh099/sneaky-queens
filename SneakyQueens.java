// Author: Mathew Holden
// Course: COP 3503C
// NID: ma157444
// Semester: Fall 2021

import java.util.*;

public class SneakyQueens
{
	public static double difficultyRating()
	{ 
		return 2.0;
	}
	
	public static double hoursSpent()
	{
		return 14.0;
	}
	
	//This method collects the y coordinate of a single string
	public static int numberCollector(String str)
	{
		int total = 0;
		int current;
		int counter = 1;
		
		//Starts at the final index of the coordinate string
		for (int i = str.length() - 1; i >= 0 ; i--)
		{			
			if (Character.isLetter(str.charAt(i)))
			{
				//do not finish loop
				break;
			}
			
			//gets the numeric value of that index
			current = (int)str.charAt(i) - '0';
			
			//properly multiplies value by 10 for its position
			current = current * counter;
			total += current;
			
			counter = counter * 10;
		}
		
		return total;
	}
	
	//converts letters portion of base 26 to correct x value
	public static int convertWord(String str)
	{
		int total = 0;
		int current;
		long expo = 0;
		
		for (int i = str.length() - 1; i >= 0 ; i--)
		{
			//if the index is a number skip loop
			if (Character.isDigit(str.charAt(i)))
			{
				continue;
			}
			
			//get letters value of 1 - 26
			current = str.charAt(i) - 'a' + 1;
			
			//first loop is 26^0 
			if (expo == 0)
			{
				total += current;
				expo = 26;
				continue;
			}
			
			total += current * expo;
			expo = expo * 26;	//base 26
		}
		return total;
	}
	
	//for all my arrays, I increment the index that indicates the straight line of...
	//the vertical, horizontal, pos diagonal, and neg diagonal components.
	//For the integer arrays I created for the diagonals, I increment the respective 
	//index and then check if it is greater than 1, which would indicate not safe.
	public static boolean allTheQueensAreSafe(ArrayList<String> list, int boardSize)
	{
		int x;
		int y;
		
		//boolean arrays require less space
		//one for x component, other for y component
		boolean[] arrX = new boolean[boardSize + 1];
		boolean[] arrY = new boolean[boardSize + 1];
		
		//created 2 integer arrays, one for pos diag component, one for neg diag component
		int[] arr = new int[2 * boardSize + 1];
		int[] oppArr = new int[2 * boardSize + 1];
		
		//loops through the list		
		for (int i = 0; i < list.size(); i++)
		{
			x = convertWord(list.get(i));		//get x
			y = numberCollector(list.get(i)); 	//get y 
			
			//verify x and y parameters
			if (x > boardSize || y > boardSize)
			{
				continue;
			}
			else if (x < 0 || y < 0)
			{
				continue;
			}
			
			//vertical and horizontal
			if (arrX[x])
			{
				return false;
			}
			
			else if (arrY[y])
			{
				return false;
			}
			
			arrX[x] = true;		//spot taken
			arrY[y] = true;  	//spot taken
			
			//opposite diagonal
			oppArr[x + y]++;
			
			if (oppArr[x + y] > 1)
			{
				return false;
			}
			
			//regular diagonal (changes x & y values)
			//finds closest zero thats either on x or y, by subtracting smaller coord from other.
			//if x & y same, then they are line y = x which ive designated to the 0 index
			if (x == y)
			{
				arr[0]++;
			
				if (arr[0] > 1)
				{
					return false;
				}
			}
			
			else if (x > y)
			{
				x -= y;
				arr[x]++;
				
				if (arr[x] > 1)
				{
					return false;
				}
			}
			
			else
			{
				y -= x;
				x -= x;
				arr[boardSize + y]++;
				
				if (arr[boardSize + y] > 1)
				{
					return false;
				}
			}
		}
		
		return true;
	}
}
