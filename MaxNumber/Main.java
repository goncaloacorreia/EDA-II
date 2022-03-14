package MaxNumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int children = Integer.parseInt(input.readLine());
        int max = 0;
        boolean checkMax = false;
        for(int c = 0; c < children; c++){
            String s []= input.readLine().split(" ");
            
            for(int aux = 1; aux < s.length; aux ++){
               int sint = Integer.parseInt(s[aux]);
               if(checkMax == false) {
                   max = sint;
                   checkMax = true;
               }

                if(sint > max){
                    max = sint; 
                }
            }
        }

        System.out.println(max);

    }
}