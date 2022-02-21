import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int ncoins;
        int types [];
        int nquestions;
        int q [];

        ncoins = Integer.parseInt(input.readLine());

        for(int x = 0; x < ncoins; x ++){
            String s []= input.readLine().split(" ");
            for(int saux = 0; saux < s.length; saux ++){
                int sint = Integer.parseInt(s[saux]);
            }
        }


    }
}