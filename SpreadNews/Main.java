package SpreadNews;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        int employees = Integer.parseInt(input.readLine());
        int[][] friends = new int[employees][16]; //arrays de arrays (lista de amigos de cada employee)

        for(int x = 0; x < employees; x++){ //percorrer employees
            String [] s = input.readLine().split(" ");
            int namigos = Integer.parseInt(s[0]);
            for(int y = 1; y <= namigos; y++){ //percorrer amigos de cada employee
                friends[x][y-1] = Integer.parseInt(s[y]);
            }
            
        }
        System.out.println(friends[4][1]);
    }

}

