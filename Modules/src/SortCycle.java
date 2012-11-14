import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SortCycle  {
  private int number;
  private List<Edge> allEdges;

  public Edge sort(List<String> cycle, ArrayList<String> nodes, int[][] result) {
	allEdges = new LinkedList<Edge>();

    if (cycle == null || cycle.size()==0){
      return null;
    }
    for (int i=0; i<cycle.size(); ++i) {
    	Edge edge = new Edge();
    	edge.pre = cycle.get(i);
    	if (i == cycle.size()-1)
    		edge.post = cycle.get(0);
    	else
    		edge.post = cycle.get(i+1);
    	edge.weight = Utilities.getEdgeVal(nodes, edge.pre, edge.post, result);
    	allEdges.add(edge);
    }
    number = allEdges.size();
    quicksort(0, number - 1);
    return allEdges.get(0);
  }
  
  private void quicksort(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    int pivot = allEdges.get(low + (high-low)/2).weight;

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (allEdges.get(i).weight < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (allEdges.get(j).weight > pivot) {
        j--;
      }

      // If we have found a values in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        exchange(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j)
      quicksort(low, j);
    if (i < high)
      quicksort(i, high);
  }

  private void exchange(int i, int j) {
    int temp = allEdges.get(i).weight;
    allEdges.get(i).weight = allEdges.get(j).weight;
    allEdges.get(j).weight = temp;
  }

} 