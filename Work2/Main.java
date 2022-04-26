import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    // função da distância entre dois pontos
    public static double distance(Point A, Point B, double reach) {
        double dist = Math.sqrt(Math.pow((B.x - A.x), 2) + Math.pow((B.y - A.y), 2));
        //double dist = Math.sqrt((B.y - A.y) * (B.y - A.y) + (B.x - A.x) * (B.x - A.x));

        if(dist > reach)
        {
            return -1;
        } 

        return dist;
    }

    // Sort para os pontos
    public static void quickSort(Point[] arr, int start, int end) {

        int partition = partition(arr, start, end);

        if (partition - 1 > start) {
            quickSort(arr, start, partition - 1);
        }
        if (partition + 1 < end) {
            quickSort(arr, partition + 1, end);
        }
    }

    public static int partition(Point[] arr, int start, int end) {
        int pivot = arr[end].y;
        Point pivot1 = arr[end];

        for (int i = start; i < end; i++) {
            if (arr[i].y < pivot) {
                Point temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
                start++;
            }
        }

        Point temp = arr[start];
        arr[start] = pivot1;
        arr[end] = temp;

        return start;
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
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
        //quickSort(new_points, 0, new_points.length - 1);
        PointCmp z = new PointCmp();

        Arrays.sort(new_points, new PointCmp());
        
        
        //BFS.Graph g = new BFS.Graph(points);
        BFS2 g = new BFS2();

        for (; cases > 0; cases--) {

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
                        g.addEdge(adj, aux1, aux2);
                        //g.addEdge(adj, aux2, aux1);
                    }

                }
            }
            
            boolean bench = false;
            
            if(reach >= height){
                bench = true;
            }

            //verificar quais os pontos iniciais
            int max_initials = -1;
            for(int aux3 = 0; aux3 < points; aux3++){
                if(new_points[aux3].y <= reach){
                    max_initials = aux3;
                }
                else{
                    break;
                }
            }
            //verificar quais os pontos finais
            int min_finals = -1;
            for(int aux3 = points - 1; aux3 != -1; aux3--){
                if( (height - new_points[aux3].y) <= reach){
                    min_finals = aux3;
                }
                else{
                    break;
                }
            }
            
            boolean unreachable = false;
            if(min_finals == -1){
                unreachable = true;
            }

            if(max_initials >= 35){
                max_initials = 35;
            }
            
            //encontrar o caminho mais curto
            int short_path = 2147483647;
            if(unreachable == false){
                for(int aux4 = 0; aux4 <= max_initials; aux4++){
                    //System.out.println("Initial: " + aux4);
                    for(int aux5 = min_finals; aux5 < points; aux5++){
                        //System.out.println("Final: " + aux5);
                        int shrt = g.ShortestDistance(adj, aux4, aux5, points);
                        if(shrt < short_path){
                            short_path = shrt;
                        }
                    }
                } 
            }
            
            short_path += 1;
            if(bench == true){
                System.out.println("0");
            }
            else if(unreachable == true){
                System.out.println("unreachable");
            }
            else{
                System.out.println(short_path);
            }
            
        }
        
        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        //System.out.println("Time: " + formatter.format((end - start) / 1000d) + " seconds");
    }

}