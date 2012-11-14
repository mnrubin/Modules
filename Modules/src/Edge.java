
public class Edge {
	@Override
	public String toString() {
		return "Edge [pre=" + pre + ", post=" + post + ", weight=" + weight
				+ "]";
	}
	
	public Edge(){
	}
	
	public Edge(String s, String t){
		this.pre=s;
		this.post=t;
	}
	
	public String pre;
	public String post;
	public int weight;
}
