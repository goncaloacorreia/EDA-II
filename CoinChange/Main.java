package CoinChange;
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

        for(int z = 0; z < nquestions; z ++){ //percorrer questions
            int problema = questions[z];
            int m [] = new int[problema + 1];  
            m[0] = 0;

            for(int x = 1; x <= problema; x++){ //percorrer m
                m[x] = 2147483647;
                for(int i = 0; i < ntypes; i++){ //percorre as moedas
                    if(types[i] <= x){ 
                        if( (m[x - types[i]] + 1) < m[x] ){ 
                            m[x] = m[x - types[i]] + 1; 
                        }
                    }
                }
            }
            
            System.out.println(problema + ": " + "[" + m[problema] + "]");
        }
        
    }
}