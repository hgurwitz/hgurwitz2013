package gurwitz.interviewQs;

import java.util.Stack;

public class StackUsingQueues {

	private Stack<String> stack1;
	private Stack<String> stack2;

	public StackUsingQueues() {

		stack1 = new Stack<String>();
		stack2 = new Stack<String>();
	}

	public void enqueue(String s) {
		stack1.push(s);
	}

	public String dequeue() {
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		String toReturn = stack2.pop();

		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}

		return toReturn;

	}

	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	public static void main(String[] args) {

		StackUsingQueues s = new StackUsingQueues();
		s.enqueue("A");
		s.enqueue("B");
		s.enqueue("C");
		s.enqueue("D");
		s.dequeue();
		s.enqueue("E");
		s.enqueue("F");
		s.enqueue("G");

		while (!s.isEmpty()) {
			System.out.println(s.dequeue());
		}

	}
}
