import java.io.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Main {

    // função da distância entre dois pontos
    public static double distance(Point A, Point B, double reach) {
        double dist = Math.sqrt(Math.pow((B.x - A.x), 2) + Math.pow((B.y - A.y), 2));

        if(dist > reach)
        {
            return -1;
        } 

        return dist;
    }

    public static int printLevels(ArrayList<ArrayList<Integer>> graph, int V, int x, int height, double reach, Point new_points[])
    {   
        boolean unreachable = true;
        int min = 30001;
        // array para cada nivel do node
        int level[] = new int[V];
        boolean marked[] = new boolean[V];

        // criar a queue
        Queue<Integer> que = new LinkedList<Integer>();

        // dar enqueue
        que.add(x);

        // nivel do node fonde inicializado a 0
        level[x] = 0;

        // marcado como visitado
        marked[x] = true;

        if(V == 1)
        {
            if(new_points[x].y + reach >= height)
            {   unreachable = false;
                
                min = 0;
                
            }
        }
                        
        else{

        
            while (que.size() > 0)
            {
                

                // primeiro elemento da queue
                x = que.peek();

                // dequeue eelemento
                que.remove();

                // percorrer os adjacentes do node x
                for (int i = 0; i < graph.get(x).size(); i++)
                {
                    // cada vizinho
                    int b = graph.get(x).get(i);
                    

                    // se b não estiver marcado, será adicionado à queue
                    if (!marked[b])
                    {

                        // enqueue b in queue
                        que.add(b);
                        
                        // nivel do vertice = nivel do adjacente + 1
                        level[b] = level[x] + 1;
                        if(new_points[b].y + reach >= height)
                            
                        {   unreachable = false;
                            if(level[b] < min)
                            {
                                min = level[b];
                            }
                        }

                        // marcar b
                        marked[b] = true;
                    }
                }
            }
        }

        if(reach >= height)
        {

            return 0;

        }

        else if(unreachable == true)
        {

            return -1;
            
        }
        else{
            if(min == 1){
                return 1;
            }
            return min + 1;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String s[] = input.readLine().split(" ");

        int points = Integer.parseInt(s[0]);
        int height = Integer.parseInt(s[1]);
        int cases = Integer.parseInt(s[2]);

        int counter = 150;
        if(points < 150){
            counter = points;
        }
        // lista dos pontos
        Point new_points[] = new Point[points];

        // armazenar pontos
        for (int aux = 0; aux < points; aux++) {
            String single_point[] = input.readLine().split(" ");
            int x = Integer.parseInt(single_point[0]);
            int y = Integer.parseInt(single_point[1]);

            new_points[aux] = new Point(x, y);
        }

        // Realiza-se o sort dos pontos
        Arrays.sort(new_points, new PointCmp());
        
        for (; cases > 0; cases--) {
            int min = 30001;
            boolean u = true;

            double reach = Integer.parseInt(input.readLine());
            //criação do grafo
            ArrayList<ArrayList<Integer>> adj =
                        new ArrayList<ArrayList<Integer>>(points);
            for (int i = 0; i < points; i++) {
                adj.add(new ArrayList<Integer>());
            }
            
            for (int aux1 = 0; aux1 < points; aux1++) {
                for (int aux2 = aux1 + 1; aux2 < counter; aux2++) {
                    double dist = distance(new_points[aux1], new_points[aux2], reach);

                    if(dist == -1)
                    {
                        continue;
                    }
                    else{
                        adj.get(aux1).add(aux2);
                        adj.get(aux2).add(aux1);
                        
                    }
                }
            }

            int max_initials = -1;
            for(int aux3 = 0; aux3 < points; aux3++){
                if(new_points[aux3].y <= reach){
                    max_initials = aux3;
                }
                else{
                    break;
                }
            }

            if(max_initials == -1 && reach < height){
                System.out.println("unreachable");
            } else if(max_initials == -1 && reach >= height){
                System.out.println("0");
            }

            for(int aux4 = 0; aux4 <= max_initials; aux4++){
                
                int aux5 = printLevels(adj, points, aux4, height, reach, new_points);
                if(aux5 != -1)
                {  u = false;
                    if(aux5 < min)
                    {
                        min = aux5;
                    }
                }
                if( u == true && aux4 == max_initials)
                {
                    System.out.println("unreachable");

                }
                else if( u == false && aux4 == max_initials)
                {
                    System.out.println(min);

                }
            }
        }
    }

}