package Work1;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Work1{
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String s []= input.readLine().split(" ");

        int rows = Integer.parseInt(s[0]);
        int columns = Integer.parseInt(s[1]);

        String row;
        int count [] = new int[9999];
        int countaux = 0;
        for(int x = 0; x < rows; x++){ //linhas
            char letra = '.';
            int number = 0;
            row = input.readLine();
            for(int y = 0; y < columns; y++){ //colunas
                if(row.charAt(y) == '.' && letra == '.'){
                }
               
                else if(letra == '.' && row.charAt(y) != letra)
                {
                    letra = row.charAt(y);
                    number++;
                }
                else if(letra != '.' && row.charAt(y) == letra)
                {
                    number++;
                }
                else if(letra != '.' && row.charAt(y) == '.')
                {
                    count[countaux] = number;
                    countaux++;
                    number = 0;
                    letra = row.charAt(y);
                }
                else if(letra != '.' && row.charAt(y) != letra)
                {
                    count[countaux] = number;
                    countaux++;
                    number = 0;
                    number++;
                    letra = row.charAt(y);
                }

                if(letra != '.' && y == columns - 1)
                {
                    count[countaux] = number;
                    countaux++;
                }
            }
        }

    }
}