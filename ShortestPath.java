
//import java.lang.*;


//importing necessary libraries




import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import java.util.Stack;
public class ShortestPath {

    int V = 12;//number of vertices
    int parent[]=new int[V];//parent array which stores the min distance parent of each vertex
    int minDistance(int dist[], Boolean visited[])// function which takes the distance and visited array and returns the minimum distant vertex
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (visited[v] == false && dist[v] <= min) {
                //we are checking if the vertex is not visited and distance of that vertex is less than the minimum distance vertex  then the vertex is returned
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }



    void dijkstra(int graph[][], int src)//this function finds the shortest path using dijkstras algorithm
    {   parent[src]=-1;//we are saying that the parent of the source vertex is -1
        int dist[] = new int[V]; //distance array which holds the shortest distance of source vertex  to all vertex


        Boolean visited[] = new Boolean[V];//visited array to check if a vertex has been added to distance array


        for (int i = 0; i < V; i++) { //here we are initialising all the distances to infinity and visited to false
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;


        for (int c = 0; c < V - 1; c++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, visited);//get the index of minimum distance vertex

            // Mark the picked vertex as visited
            visited[u] = true;


            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not visited , there is an
                // edge from u to v, and total distance from src to
                // v through u is smaller than current value of dist[v]
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
        }


        for(int i=0;i<V;i++){
            System.out.println(parent[i]+"-->"+i+" --dist--->"+dist[i]);//printing the path  and distances
        }

    }

    void navigation(int graph[][]) { // function which finds the shortest path between
                                    //source and destination given a graph

        Map<String, Integer> dict = new HashMap<>();//creating a hashmap which stores
        //key value pairs to convert the given input string which is the name of a place to
        //the respective vertex number
        dict.put("thrissur", 11);
        dict.put("viyur", 8);
        dict.put("mgk", 5);
        dict.put("athani", 3);
        dict.put("parlikkad", 1);
        dict.put("wadakkanchery", 0);
        dict.put("erumapetty", 2);
        dict.put("vellarkkad", 4);
        dict.put("kunnamkulam", 6);
        dict.put("eranellur", 7);
        dict.put("puzhakkal", 9);
        dict.put("punkunnam", 10);
        Map<Integer, String> dict2 = new HashMap<>();//creating a hashmap which stores
        //key value pairs to convert the given vertex number back to the string which resembles the name of a place
        dict2.put(11, "thrissur");
        dict2.put(8, "viyur");
        dict2.put(5, "mgk");
        dict2.put(3, "athani");
        dict2.put(1, "parlikkad");
        dict2.put(0, "wadakkanchery");
        dict2.put(2, "erumapetty");
        dict2.put(4, "vellarkkad");
        dict2.put(6, "kunnamkulam");
        dict2.put(7, "eranellur");
        dict2.put(9, "puzhakkal");
        dict2.put(10, "punkunnam");
        Stack<String> stack = new Stack<>();// creating a stack which can be used for printing the path correctly
        JOptionPane.showMessageDialog(null, "welcome to MapNav!" + '\n');//joptionpane is basically a simple ui dialogue box
        //which can show a message or get input from user.


        //How does it work?
        // here we are first taking the parent of the destination vertex and then store  it in a variable.
        // then we use a loop  to iterate and find the parent of the parents till we reach the source vertex (the parent will be a min ditance vertex )
        // 1->2->3->4->5
        // |           |
        //source     destination
        // step 1) find parent of 5 which is 4 then store it (push 5 to a stack)
        // step 2) find the parent of 5's parent and it goes on till we reach the source node and keep on pushing the parents to the stack
        // 5 - parent 4 (push 5 to stack)
        // 4 - parent 3 (push 4 to stack)
        // 3 - parent 2 (push 2 to stack)
        // 2 - parent 1 (push 2 to stack)
        //we have reached the source node (push 1 to stack)
        // when you pop  from stack we can print it in the order 1 -> 2 -> 3 -> 4 -> 5

        String source = JOptionPane.showInputDialog("Enter source");//we are getting input from the user
        String destination = JOptionPane.showInputDialog("Enter destination");
        int a = dict.get(destination.toLowerCase());//we are getting the respective vertex number by comparing the string with the hashmap
        stack.push(destination);//we are pushing it to the stack
        int b = dict.get(source.toLowerCase());
        if(a==b){//a case where the source and destination are same so we can print a simple message
            JOptionPane.showMessageDialog(null, "you are in "+source + '\n');
            return;
        }
        dijkstra(graph,b);//calling the dijkstra function,which takes graph and source vertex as parameters,and finds
        //the shortest path for us
        int c = parent[a];//we are finding the parent ofthe destination vertex



        for (int i = 0; i <= parent.length; i++) {
            stack.push(dict2.get(c));//we are pushing the parent of destination to the stack

            c = parent[c];//we are finding the current parent vertex's parent till we find the source vertex
            if (c == b) {//if we  reach the source vertex we break out of the for loop
                stack.push(dict2.get(c));
                break;
            }

        }

        String display = "";
        while (!stack.isEmpty()) {
            if (stack.size() == 1) {//if stack only has one element left just add that to display string and no need of  ---> because it shows the destination
                display = display + stack.pop();

            } else {
                display = display + stack.pop() + "<--->";//we are popping elements from the stack and adding it to the display string with a --> in between
            }


        }
        JOptionPane.showMessageDialog(null, display + '\n');//we are then displaying the path in a new dialogue box
        System.out.println();

    }

    // Driver method
    public static void main(String[] args)
    {
        /* Let us create the example graph discussed above */
        int graph[][] = new int[][] {
                {0,  12,  24,  0,  0,  0,  0, 0, 0, 0, 0,  0},

                {12,  0,  0,  11,  0,  0,  0,  0,  0, 0, 0, 0},

                {24,  0,  0,  0,  7,  0,  0,  0,  0, 0, 0,  0},

                {0,  11,  0,  0,  0,  5,  0,  0,  0, 0, 0,  0},

                {0,  0,  7,  0,  0,  0,  15,  0,  0, 0, 0,  0},

                {0,  0,  0,  5,  0,  0,  0,  0,  12, 0, 0,   0},

                {0,  0,  0,  0,  15,  0,  0,  12,  0, 0, 0,  0},

                {0,  0,  0,  0,  0,  0,  12,  0,  0, 18, 0,  0},

                {0,  0,  0,  0,  0,  12,  0,  0,  0, 0, 0,  8},

                {0,  0,  0,  0,  0,  0,  0,  18,  0, 0, 8,  0},

                {0,  0,  0,  0,  0,  0,  0,  0,  0, 8, 0,  9},
                {0,  0,  0,  0,  0,  0,  0,  0,  8, 0, 9,  0} };
        ShortestPath t = new ShortestPath();
        t.navigation(graph);
    }
}