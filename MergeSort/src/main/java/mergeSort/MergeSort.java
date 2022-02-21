package mergeSort;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MergeSort {
  /**
   * Thread that declares the lambda and then initiates the work
   */

  public static int message_id = 0;

  public static JSONObject init(int[] array) {
    JSONArray arr = new JSONArray();
    for (var i : array) {
      arr.put(i);
    }
    JSONObject req = new JSONObject();
    req.put("method", "init");
    req.put("data", arr);
    return req;
  }

  public static JSONObject peek() {
    JSONObject req = new JSONObject();
    req.put("method", "peek");
    return req;
  }

  public static JSONObject remove() {
    JSONObject req = new JSONObject();
    req.put("method", "remove");
    return req;
  }
  
  public static void Test(int port, String host) {

    long startTime;
    long endTime;

    int[] a = new int[1000];
    Random r = new Random();
    for (int i = 0; i < a.length; i++){
      a[i] = r.nextInt(100);
    }

    startTime = System.currentTimeMillis();

    JSONObject response = NetworkUtils.send(host, port, init(a));
    
    //System.out.println(response);
    response = NetworkUtils.send(host, port, peek());
    //System.out.println(response);

    while (true) {
      response = NetworkUtils.send(host, port, remove());


      if (response.getBoolean("hasValue")) {
        //System.out.println(response);
 
      } else{
        break;
      }
    }
    endTime = System.currentTimeMillis();
    long executionTime = endTime - startTime;
    System.out.println("Execution Time: " + executionTime + "ms");
  }

  public static void main(String[] args) {

    // use the port of one of the branches to test things
    Test(Integer.valueOf(args[0]), args[1]);

  }
}
