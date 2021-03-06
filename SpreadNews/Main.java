package SpreadNews;


import java.util.*; 
//undirected graph represented using adjacency list. 
@SuppressWarnings("unchecked") 
class Graph { 
    private int Vertices;   // No. of vertices 
    private LinkedList<Integer> adj_list[]; //Adjacency Lists 
   
    // graph Constructor:number of vertices in graph are passed 
    Graph(int v) { 
        Vertices = v; 
        adj_list =  new LinkedList[v];
        for (int i=0; i<v; ++i)         //create adjacency lists
            adj_list[i] = new LinkedList<Integer>(); 
    } 
   
    // add an edge to the graph 
    void addEdge(int v,int w) { 
        adj_list[v].add(w); 
    } 
   
    // BFS traversal from the root_node 
    void BFS(int root_node)   { 
        // initially all vertices are not visited 
        boolean visited[] = new boolean[Vertices]; 
        int boom = 0;
        int maxboom = -1;
        int day = 1;
        boolean firstday = false;
        // BFS queue 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
   
        // current node = visited, insert into queue 
        visited[root_node]=true; 
        queue.add(root_node); 
   
        while (queue.size() != 0)  { 
            // deque an entry from queue and process it  
            root_node = queue.poll(); 
            //System.out.print(root_node+" "); 
            
            // get all adjacent nodes of current node and process
            boom = 0;
            Iterator<Integer> i = adj_list[root_node].listIterator(); 
            while (i.hasNext()){ 
                int n = i.next(); 
                if (!visited[n]) { 
                    visited[n] = true; 
                    queue.add(n);
                    boom++; 
                    day++;
                    if(boom > maxboom){
                        maxboom = boom;
                    }
                } 
            } 
        }
        System.out.println(maxboom); 
    } 
  } 
public class Main{
    public static void main(String[] args) throws Exception{
        /*BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        int employees = Integer.parseInt(input.readLine());
        int[][] friends = new int[employees][16]; //arrays de arrays (lista de amigos de cada employee)

        for(int x = 0; x < employees; x++){ //percorrer employees
            String [] s = input.readLine().split(" ");
            int namigos = Integer.parseInt(s[0]);
            for(int y = 1; y <= namigos; y++){ //percorrer amigos de cada employee
                friends[x][y-1] = Integer.parseInt(s[y]);
            }
            
        }
        System.out.println(friends[3][2]);*/

        //create a graph with 5 vertices
        Graph g = new Graph(6); 
        //add edges to the graph  
        g.addEdge(0, 1); 
        g.addEdge(0, 2); 
        g.addEdge(1, 3); 
        g.addEdge(1, 4); 
        g.addEdge(2, 0); 
        g.addEdge(2, 4); 
        g.addEdge(2, 5); 
        g.addEdge(3, 4); 
        g.addEdge(5, 0); 
        g.addEdge(5, 2); 
         
        //print BFS sequence
        System.out.print("Breadth-first traversal of graph with 0 as starting vertex: "); 
        g.BFS(5); 
        System.out.println("");
    }

}

