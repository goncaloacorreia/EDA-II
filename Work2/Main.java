import java.io.*;
import java.awt.Point;
import java.util.LinkedList;


public class Main{

    public final static int counter = 150;

    //função da distância entre dois pontos
    public static double distance(Point A, Point B)
    {
        double dist = Math.sqrt((B.x - A.x) * 2 + (B.y - A.y) * 2);

        return dist;
    }

    //Sort para os pontos
    public static void quickSort(Point[] arr, int start, int end){
 
        int partition = partition(arr, start, end);
 
        if(partition-1>start) {
            quickSort(arr, start, partition - 1);
        }
        if(partition+1<end) {
            quickSort(arr, partition + 1, end);
        }
    }
 
    public static int partition(Point[] arr, int start, int end){
        int pivot = arr[end].y;
        Point pivot1 = arr[end];
 
        for(int i=start; i<end; i++){
            if(arr[i].y<pivot){
                Point temp= arr[start];
                arr[start]=arr[i];
                arr[i]=temp;
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
        
        //armazenar pontos
        for(int aux = 0; aux<points; aux++)
        {
            String single_point[] = input.readLine().split(" ");
            int x = Integer.parseInt(single_point[0]);
            int y = Integer.parseInt(single_point[1]);

            new_points[aux] = new Point(x, y);


        }
        //Realiza se o sort dos pontos
        quickSort(new_points, 0, new_points.length-1);

        
        WeightedGraph.Graph graph = new WeightedGraph.Graph(points);

        graph.addEgde(0, 1, 4);

        for(int aux1 = 0; aux1<counter; aux1++)
        {

        }

        
        

    }

}