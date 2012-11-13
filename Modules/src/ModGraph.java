import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ModGraph {
	List<Node> allNodes;
	public ModGraph(LinkedList<Pair> list, ArrayList<String> nodes, int[][] result) {
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
				pre_node = new Node();
				pre_node.name = p.getPre();
				allNodes.add(pre_node);
			}
			if (post_node == null) {
				post_node = new Node();
				post_node.name = p.getPost();
				allNodes.add(post_node);
			}
			if (pre_node.out_nodes == null) {
				pre_node.out_nodes = new LinkedList<Node>();
			}
			if (pre_node.in_nodes == null) {
				pre_node.in_nodes = new LinkedList<Node>();
			}
			if ((pre_node.out_nodes == null || !pre_node.out_nodes.contains(post_node)) && (post_node.in_nodes == null || !post_node.in_nodes.contains(pre_node))) {
				pre_node.out_nodes.add(post_node);
				post_node.in_nodes.add(pre_node);
			}
			
		}
	}
}
