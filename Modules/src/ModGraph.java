import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ModGraph {
	List<Node> allNodes;
	ArrayList<String> nodesForLookup;
	int[][] result;
	
	public ModGraph(LinkedList<Pair> list, ArrayList<String> nodes, int[][] result) {
		allNodes = new LinkedList<Node>();
		nodesForLookup = nodes;
		this.result = result;
		for (Pair p:list) {
			Node pre_node = null;
			Node post_node = null;
			if (allNodes != null) {
				for (Node n: allNodes) {
					if (n.name == p.getPre()) {
						pre_node = n;
					}
					if (n.name == p.getPost()) {
						post_node = n;
					}
				}
			}
			if (pre_node == null) {
				pre_node = new Node(p.getPre());
				allNodes.add(pre_node);
			}
			if (post_node == null) {
				post_node = new Node(p.getPost());
				allNodes.add(post_node);
			}
			if (!pre_node.out_nodes.contains(post_node) && !post_node.in_nodes.contains(pre_node)) {
				pre_node.out_nodes.add(post_node);
				post_node.in_nodes.add(pre_node);
			}
			
		}
	}//end constructor

	public boolean[][] getAdjMatrix()
	{
		boolean[][] adjMatrix = new boolean[nodesForLookup.size()][nodesForLookup.size()];
		for(Node n : allNodes)
		{
			List<Node> out_list = n.out_nodes;
			for(Node outN : out_list)
			{
				int pre_index = nodesForLookup.indexOf(n.name);
				int post_index = nodesForLookup.indexOf(outN.name);
				if(result[pre_index][post_index] >0)
				{
					adjMatrix[pre_index][post_index] = true;
				}
			}
		}
		
		return adjMatrix;
	}

	public List<String> getNodesForLookup()
	{
		return nodesForLookup;
	}
	
	public void removeEdge(Edge edge){
		String pre=edge.pre;
		String post=edge.post;
		Node preNode=null;
		Node postNode=null;
		for(Node n: allNodes){
			if(n.name.equals(pre))
				preNode=n;
			else if(n.name.equals(post))
				postNode=n;
		}
		preNode.out_nodes.remove(postNode);
		postNode.in_nodes.remove(preNode);
	}
	
	public static void main(String args[]) throws IOException
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		Pair p1 = new Pair("a", "b");
		Pair p2 = new Pair("b", "c");
		Pair p3 = new Pair("b", "d");
		LinkedList<Pair> pairs = new LinkedList<Pair>();
		pairs.add(p1);
		pairs.add(p2);
		pairs.add(p3);
		int[][] result = new int[list.size()][list.size()];
		result[0][1] = 5;
		result[1][2] = 2;
		result[1][3] = 4;
		ModGraph mg = new ModGraph(pairs, list, result);
		boolean[][] matrix = mg.getAdjMatrix();
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = 0; j < list.size(); j++)
			{
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("\n");
		}
		
	}
	
	public void removeTransitive(){
		ArrayList<Node> oneStep;
		ArrayList<Node> furtherStep;
		for(Node n: allNodes){
			furtherStep=new ArrayList<Node>();
			oneStep = new ArrayList<Node>();
			oneStep.addAll(n.out_nodes);
			for(Node m: n.out_nodes){
				DFS(m,furtherStep);
			}
			oneStep.retainAll(furtherStep);
			for(Node k: oneStep){
				removeEdge(new Edge(n.name,k.name));
			}
		}
		
	}
	
	public void DFS(Node m, ArrayList<Node> dest){
		for(Node n:m.out_nodes){
			dest.add(n);
			DFS(n,dest);
		}
	}
	
	

}//end class



