import java.io.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

class PointCmp implements Comparator<Point> {
    public int compare(Point a, Point b) {
        return (a.y < b.y) ? -1 : (a.y > b.y) ? 1 : 0;
    }
}

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

    public static int printLevels(ArrayList<ArrayList<Integer>> graph, int nVertex, int max_initial, int height, double reach, Point points[])
    {   
        boolean unreachable = true;
        int min = 30001;
        int queueAux;

        if(reach >= height)
        {

            return 0;

        }

        // array para cada nivel do node
        int level[] = new int[nVertex];
        boolean marked[] = new boolean[nVertex];

        // criar a queue
        Queue<Integer> que = new LinkedList<Integer>();
        for(int y = 0; y <= max_initial; y++)
        {
            // enqueue em cada ponto inicial
            que.add(y);

            // nivel do node fonde inicializado a 0
            level[y] = 1;

            // marcado como visitado
            marked[y] = true;
        }
        while (que.size() > 0)
        {   
            
            // primeiro elemento da queue
            queueAux = que.peek();

            // dequeue elemento
            que.remove();
            
            if(points[queueAux].y + reach >= height)
            {   
                unreachable = false;
                if(level[queueAux] < min)
                {
                    min = level[queueAux];
                }
            }
            // percorrer os adjacentes do node y
            for (int i = 0; i < graph.get(queueAux).size(); i++)
            {
                
                // cada adjacente
                int b = graph.get(queueAux).get(i);

                if(distance(points[queueAux], points[b], reach) == -1)
                {   
                    continue;
                }
                
                // se b não estiver marcado, será adicionado à queue
                if (!marked[b])
                {

                    // enqueue b in queue
                    que.add(b);
                    
                    // nivel do vertice = nivel do adjacente + 1
                    level[b] = level[queueAux] + 1;
                    if(points[b].y + reach >= height)
                    {   
                        unreachable = false;
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
        
        //não encontrou nenhum ponto em que conseguisse chegar ao topo
        if(unreachable == true)
        {
            return -1;
        }

        //encontra pelo menos um caminho e retorna o menor
        else
        {
            return min;
        }
    }

    public static void main(String[] args) throws IOException {
        //leitura do input
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String s[] = input.readLine().split(" ");
        int npoints = Integer.parseInt(s[0]);
        int height = Integer.parseInt(s[1]);
        int cases = Integer.parseInt(s[2]);

        // array dos pontos
        Point points[] = new Point[npoints];

        // armazenar pontos
        for (int aux = 0; aux < npoints; aux++) 
        {
            String single_point[] = input.readLine().split(" ");
            int x = Integer.parseInt(single_point[0]);
            int y = Integer.parseInt(single_point[1]);

            points[aux] = new Point(x, y);

        }

        //Sort dos pontos de acordo com a ordenada
        Arrays.sort(points, new PointCmp());
        
        //encontrar o maior reach
        double maxreach = -1;
        double reach[] = new double [cases];

        for(int reachindex = 0; reachindex < cases; reachindex++)
        {    
            reach[reachindex] = Integer.parseInt(input.readLine());
            if(reach[reachindex] > maxreach)
            {
                maxreach = reach[reachindex];
            }
        }

        
        //criação do grafo
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(npoints);

        for (int i = 0; i < npoints; i++) 
        {
            adj.add(new ArrayList<Integer>());
        }
        
        for (int aux1 = 0; aux1 < npoints; aux1++) 
        {
            for (int aux2 = aux1 + 1; aux2 < npoints; aux2++) 
            {
                if(points[aux2].y - points[aux1].y > maxreach)
                {
                    break;
                }
                else
                {
                    double dist = distance(points[aux1], points[aux2], maxreach);
                    if(dist != -1)
                    {
                        adj.get(aux1).add(aux2);
                        adj.get(aux2).add(aux1);
                    }
                }
            }
        }
        
        //verificar se existe um caminho
        boolean path = false;
        for(int i = 0; i < npoints; i++)
        {
            if(points[i].y + maxreach >= height)
            {
                path = true;
                break;
            }
        }

        //se não existir um caminho para maxreach, printar unreachable ou 0(caso seja possível chegar a height sem precisar de qualquer ponto), para os restantes cases
        if(path == false)
        {
            for(int caseindex = 0; caseindex < cases; caseindex++)
            {
                if(reach[caseindex] >= height){
                    System.out.println("0");
                }
                else
                {
                    System.out.println("unreachable");
                }
            }
        }
        else
        {
            //verificar cada reach
            for(int reachindex = 0; reachindex < cases; reachindex++)
            {   
                double new_reach = reach[reachindex];
                
                //calcular valores iniciais para este reach
                int max_initials = -1;
                for(int aux3 = 0; aux3 < npoints; aux3++)
                {
                    if(points[aux3].y <= new_reach)
                    {
                        max_initials = aux3;
                    }
                    else
                    {
                        break;
                    }
                }

                //caso não haja valores iniciais
                if(max_initials == -1 && new_reach < height)
                {
                    System.out.println("unreachable");
                } 
                else if(max_initials == -1 && new_reach >= height)
                {
                    System.out.println(0);
                }
                else
                {
                    //calcular caminho mais curto para cada valor inicial ou unreachable, caso não seja possível chegar ao topo
                    int aux5 = printLevels(adj, npoints, max_initials, height, new_reach, points);
                    if(aux5 == -1)
                    {
                        System.out.println("unreachable");
                    }else
                    {
                        System.out.println(aux5);
                    }    
                }
            }
        } 
    }
}