import java.util.LinkedList;
import java.util.List;


public class ModGraph {
	List<Node> allNodes;
	List<Edge> allEdges;
	public ModGraph(LinkedList<Pair> list) {
		for (Pair p:list) {
			Node pre_node = null;
			Node post_node = null;
			for (Node n: allNodes) {
				if (n.name == p.getPre()) {
					pre_node = n;
				}
				if (n.name == p.getPost()) {
					post_node = n;
				}
			}
			if (pre_node == null) {
				pre_node = new Node();
				pre_node.name = p.getPre();
			}
			if (post_node == null) {
				post_node = new Node();
				post_node.name = p.getPost();
			}
			if (!pre_node.out_nodes.contains(post_node) && !post_node.in_nodes.contains(pre_node)) {
				pre_node.out_nodes.add(post_node);
				post_node.in_nodes.add(pre_node);
				Edge edge = new Edge();
				edge.up_node = pre_node;
				edge.down_node = post_node;
				allEdges.add(edge);
			}
		}
	}
}
