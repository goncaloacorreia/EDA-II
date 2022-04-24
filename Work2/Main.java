import java.io.*;
import java.awt.Point;
import java.util.LinkedList;



public class Main {

    public final static int counter = 150;

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

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String s[] = input.readLine().split(" ");

        int points = Integer.parseInt(s[0]);
        int height = Integer.parseInt(s[1]);
        int cases = Integer.parseInt(s[2]);

        // lista dos pontos
        Point new_points[] = new Point[points];

        // armazenar pontos
        for (int aux = 0; aux < points; aux++) {
            String single_point[] = input.readLine().split(" ");
            int x = Integer.parseInt(single_point[0]);
            int y = Integer.parseInt(single_point[1]);

            new_points[aux] = new Point(x, y);

        }
        // Realiza se o sort dos pontos
        quickSort(new_points, 0, new_points.length - 1);

        GFG.Graph g = new GFG.Graph(points);

        


       // for (; cases > 0; cases--) {
        double reach = Integer.parseInt(input.readLine());
            System.out.println(reach);
            for (int aux1 = 0; aux1 < points; aux1++) {
                
                for (int aux2 = aux1 + 1; aux2 < points; aux2++) {
                    double dist = distance(new_points[aux1], new_points[aux2], reach);

                    if(dist == -1)
                    {
                        continue;
                    }
                    else{
                        g.addEdge(aux1, aux2, dist);
                        g.addEdge(aux2, aux1, dist);
                    }

                    


                }
            }

            int src = 1, dest = 14;
		System.out.printf("\nShortest Distance between" +
							" %d and %d is %d\n", src,
							dest, g.findShortestPath(src, dest));
            
            
       // }

    }

}