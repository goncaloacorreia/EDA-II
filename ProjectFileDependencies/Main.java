package ProjectFileDependencies;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        int ntasks;
        int nrules;
        String s [] = input.readLine().split(" ");
        ntasks = Integer.parseInt((s)[0]);
        nrules = Integer.parseInt((s)[1]);
        ArrayList <Integer> rules [] = new ArrayList[ntasks - 1];

        //implementar fila com prioridade

        for(int i =0; i < nrules - 1; i++){
            s = input.readLine().split(" ");
            int r_index = Integer.parseInt((s)[0]); //index de rules
            rules[r_index] = new ArrayList<Integer>(); 
            int r_index2 = Integer.parseInt((s)[1]);
            
            if(r_index2 != 0){
                for(int z = 2; z < s.length; z++){
                    int r_index3 = Integer.parseInt((s)[z]);
                    rules[r_index].add(r_index3);
                }
            }
        }

        for (int y = 0; y <= ntasks; y++) {
            for(ArrayList <Integer> i : rules){
                System.out.print(i);
            }
            
          }
        

        

    }
}
