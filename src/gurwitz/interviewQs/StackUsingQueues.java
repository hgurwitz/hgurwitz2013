package gurwitz.interviewQs;

import java.util.Stack;

public class StackUsingQueues {

	private Stack<String> stack1;
	private Stack <String> stack2;
	
	public StackUsingQueues(){
	
		stack1=new Stack<String>();
		stack2=new Stack<String>();
	}
	public void enqueue(String s){
		stack1.push(s);
	}
	
	//public String dequeue(){
		
	//}
	
	public static void main(String[]args){
		
	}
}
