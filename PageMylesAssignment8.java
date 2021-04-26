import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
Myles Page
Cs 1450 002
Monday - Wednesday
Due 04-07-2021
Assignment 8
Stacks and Iterators
*/



public class PageMylesAssignment8 {
	public static void main(String[] args) throws FileNotFoundException {
		
		//makes the array
		ArrayList<Character> message = new ArrayList<>();
		ArrayList<Character> key = new ArrayList<>();
		
		//read in
		File messageFile = new File("listMessage.txt");
		Scanner messageRead = new Scanner(messageFile);
		File keyFile = new File("listKey.txt");
		Scanner keyRead = new Scanner(keyFile);
		
		//array and start
		int[] size = new int[2];
		int[] start = new int[2];
		
		//add message 
		String messageString = messageRead.nextLine();
		
		//add the message char by char
		for (int i=0; i < messageString.length(  ); i++) {
		    message.add(messageString.charAt(i));
		}
		
		//add key
		String keyString = keyRead.next();
		
		//add the key char by char
		for (int i=0; i < keyString.length(  ); i++) {
		    key.add(keyString.charAt(i));
		}
		
		//getts the size and start
		size[0] = keyRead.nextInt();
		size[1] = keyRead.nextInt();
		
		start[0] = keyRead.nextInt();
		start[1] = keyRead.nextInt();
		
		//makes the Iterator 
		Iterator<Character> messageI = message.iterator();
		Iterator<Character> keyI = key.iterator();
		
		//makes the decoder
		Decoder list = new Decoder(size[0], size[1], start[0], start[1]);
		//calls the decoder
		String answer = list.unscramble(messageI, keyI);
		//prints the answer
		System.out.println(answer);
		
		//______________________________________________________________________________________________________________________________________________
		//Queue Part
		
		//makes the queue
		Queue<Character> message2 = new LinkedList<>();
		Queue<Character> key2 = new LinkedList<>();
		
		//read in
		messageFile = new File("queueMessage.txt");
		messageRead = new Scanner(messageFile);
		keyFile = new File("queueKey.txt");
		keyRead = new Scanner(keyFile);
		
		//array and start
		size = new int[2];
		start = new int[2];
		
		//add message 
		messageString = messageRead.nextLine();
		
		//add the message char by char
		for (int i=0; i < messageString.length(  ); i++) {
		    message2.add(messageString.charAt(i));
		}
		
		//add key
		keyString = keyRead.next();
		
		//add the key char by char
		for (int i=0; i < keyString.length(  ); i++) {
		    key2.add(keyString.charAt(i));
		}
		
		//getts the size and start
		size[0] = keyRead.nextInt();
		size[1] = keyRead.nextInt();
		start[0] = keyRead.nextInt();
		start[1] = keyRead.nextInt();
		
		//makes the Iterator 
		messageI = message2.iterator();
		keyI = key2.iterator();
		
		//makes the decoder
		Decoder queue = new Decoder(size[0], size[1], start[0], start[1]);
		//calls the decoder
		answer = queue.unscramble(messageI, keyI);
		//prints the answer
		System.out.println(answer);
		//closes the read ins
		messageRead.close();
		keyRead.close();
	}	
}

class Decoder{	
	//variables
	private char[][] messageArray;
	private int startingRow;
	private int startingCol;
	private Stack stack;
	
	//constructor
	public Decoder(int numRows, int numCols, int startingRow, int startingCol) {
		messageArray = new char[numRows][numCols];
		this.startingRow = startingRow;
		this.startingCol = startingCol;
		stack = new Stack();
	}
	
	//unscramble
	public String unscramble(Iterator<Character> messageI, Iterator<Character>keyI) {
		//varables
		int row = 0;
		int col = 0;
		int rowSize = messageArray.length;
		int colSize = messageArray[0].length;

		//fill by col by col
		while(col < colSize) {
			while(row < rowSize) {
				messageArray[row][col] = messageI.next();
				row++;
			}
			col++;
			row = 0;
		}
	
		//get the starting point
		row = this.startingRow;
		col = this.startingCol;
		
		//has next
		while(keyI.hasNext()) {
			//variable
			char checker = keyI.next();
			//check which spot you should move
			switch(checker) {
			case 'u':	
				row--;
				stack.push(messageArray[row][col]);
				break;
			case 'l':
				col--;
				stack.push(messageArray[row][col]);
				break;
			case 'r':
				col++;
				stack.push(messageArray[row][col]);
				break;
			case 'd':
				row++;
				stack.push(messageArray[row][col]);
				break;
			}
		}
		
		//string 
		String finalString = "";
		
		//untill the stack is empty
		while(stack.isEmpty() == false) {
			//add from stack to the array
			finalString = finalString + stack.pop();
		}
		//return the string
		return finalString;
	}
}

class Stack{
	//varable
	private ArrayList<Character> stackArray;
	
	//constructor
	public Stack(){
		stackArray = new ArrayList<>(); 
	}
	
	//is empty
	public boolean isEmpty() {
		return stackArray.isEmpty();
	}
	
	//checks the size
	public int size() {
		return stackArray.size();
	}
	
	//push to the stack
	public void push(char value) {
		stackArray.add(value);
	}
	
	//pop from the stack
	public char pop(){
		int last = stackArray.size();
		return stackArray.remove(last - 1);
	}
}
