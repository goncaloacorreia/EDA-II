import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int ntypes;
        int types [] = new int[100];
        int nquestions;
        int questions [] = new int[200000];

        ntypes = Integer.parseInt(input.readLine());
        String s []= input.readLine().split(" ");

        for(int x = 0; x < ntypes; x ++){ //preencher types[]
            types[x] = Integer.parseInt(s[x]);
        }

        nquestions = Integer.parseInt(input.readLine());
        

        for(int y = 0; y < nquestions; y ++){ //preencher questions
            questions[y] = Integer.parseInt(input.readLine());
        }


    }
}