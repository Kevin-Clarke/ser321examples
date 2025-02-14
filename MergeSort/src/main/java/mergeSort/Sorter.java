package mergeSort;

import java.util.PriorityQueue;

import org.json.JSONObject;

public class Sorter extends Node {
  private PriorityQueue<Integer> sorted = new PriorityQueue<Integer>();
  
  public Sorter(int port) {
    super (port);
  }

  public synchronized JSONObject init(JSONObject object) {

    long startTime;
    long endTime;
    long executionTime;

    startTime = System.currentTimeMillis();

    sorted.clear();
    for (var val : object.getJSONArray("data")) {
      sorted.add((Integer) val);
    }

    endTime = System.currentTimeMillis();
    executionTime = endTime - startTime;

    System.out.println("Time to build priority queue: " + executionTime + "ms");

    return object;
  }

  public synchronized JSONObject peek(JSONObject object) {
    object.put("response", true);
    if (sorted.size() > 0) {
      object.put("hasValue", true);
      object.put("value", sorted.peek());
    } else {
      object.put("hasValue", false);
    }
    return object;
  }

  public synchronized JSONObject remove(JSONObject object) {
    object.put("response", true);
    if (sorted.size() > 0) {
      object.put("hasValue", true);
      object.put("value", sorted.remove());
    } else {
      object.put("hasValue", false);
    }
    return object;
  }

  public JSONObject error(String error) {
    JSONObject ret = new JSONObject();
    ret.put("error", error);
    return ret;
  }

  public static void main(String[] args) {

    new Sorter(Integer.valueOf(args[0])).run();

  }
}