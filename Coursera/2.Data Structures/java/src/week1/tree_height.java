package week1;

import java.util.*;
import java.io.*;

public class tree_height {
	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}

		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeHeight {
		int n;
		int parent[];

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				int nodeValue = in.nextInt();
				parent[i] = nodeValue;
				//if(nodeValue == 60)
					//System.out.println(nodeValue + " " + i);
			}
		}

		int computeHeight() {
			// Replace this code with a faster implementation
			int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			return maxHeight;
		}
	}
	
	public class Tree {
		private Node root;
		
		private int height=0;
		
		public void addNode(int parentValue, int childValue){
			if(root == null){
				root = new Node(childValue);
			} else 
				root.addNode(parentValue,childValue);
		}
		
		public int getHeight(){
			return root == null ? 0 : root.getHeight();
		}
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			int n = in.nextInt();
			int[] nodes = new int[n];
			ArrayList<Integer> test = new ArrayList<Integer>();
			for (int nodeValue = 0; nodeValue < n; nodeValue++) {
				int parentValue = in.nextInt();
				if(parentValue == -1){
					addNode(parentValue, nodeValue);
					test.add(nodeValue);
					//System.out.println(nodeValue);
				}
				nodes[nodeValue] = parentValue;
			}
			
			while(!test.isEmpty()){
				int currentParrentNode = test.remove(0);
				for(int i=0;i<nodes.length;i++){
					if(nodes[i]==currentParrentNode){
						test.add(i);
						//System.out.println(currentParrentNode+ " " +i);
						addNode(currentParrentNode, i);
					}
				}
			}
			
				
				
			
//			for (int childValue = 0; childValue < nodes.length; childValue++) {
//				int parentValue = nodes[childValue];
//				if (parentValue != -1)
//					addNode(parentValue,childValue);
//			}
			
			
		}
		
		
	}
	
	public class Node{

		private int value;
		
		private List<Node> children = new ArrayList<Node>();
		
		public Node(int value) {
			this.value = value;
		}

		public int getHeight() {
			int heigth = 0;
			for(Node node:children){
				int nodeHeight = node.getHeight();
				if(nodeHeight > heigth)
					heigth = nodeHeight;
			}
			return 1 + heigth;
		}

		public int addNode(int parentValue, int childValue) {
			int response = 0;
			if(value == parentValue){
				// if list empty, then a new level is added
				if(children.isEmpty())
					response+=1;
				children.add(new Node(childValue));
				//System.out.println(parentValue + " " + childValue);
			} else {
				//Ask the children if it needs to be added
				boolean added =  false;
				for(int i=0;i<children.size() && !added; i++){
					response += children.get(i).addNode(parentValue, childValue);
					added = response != 0;
				}
			}
			return response;
		}

		private int getValue() {
			return value;
		}


		@Override
		public String toString() {
			return "[" + value + "]";
		}
		
	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new tree_height().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}

	public void run() throws IOException {
		//TreeHeight tree = new TreeHeight();
		//tree.read();
		
		Tree tree2 = new Tree();
		tree2.read();

		//System.out.println(tree.computeHeight());
		System.out.println(tree2.getHeight());
	}
}
