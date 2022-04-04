package Work1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public final int subsize = 1000;
    public static void main(String[] args) throws Exception {
        long subtrees[] = new long[1000];
        // Array com o tamanho das peças de lego disponiveis.
        int lego[] = {1,2,3,4,6,8,10,12,16};
        long result = 1;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String s[] = input.readLine().split(" ");

        // Numero de linhas do input.
        int rows = Integer.parseInt(s[0]);

        // Numero de colunas do input.
        int columns = Integer.parseInt(s[1]);

        String row;

        // Array de inteiros que irao conter o tamanho das sequÃªncias.
        int count[] = new int[9999];
        int countaux = 0;

        // Ciclo utilizado para fazer a contagem do tamanho das diferentes sequencias encontradas no input dado.
        for (int x = 0; x < rows; x++) {
            char letra = '.';
            int number = 0;
            row = input.readLine();
            for (int y = 0; y < columns; y++) {
                if (row.charAt(y) == '.' && letra == '.') {

                }
                else if (letra == '.' && row.charAt(y) != letra) {

                    letra = row.charAt(y);
                    number++;

                } else if (letra != '.' && row.charAt(y) == letra) {

                    number++;

                } else if (letra != '.' && row.charAt(y) == '.') {
                    
                    count[countaux] = number;
                    countaux++;
                    number = 0;
                    letra = row.charAt(y);

                } else if (letra != '.' && row.charAt(y) != letra) {
                    
                    count[countaux] = number;
                    countaux++;
                    number = 0;
                    number++;
                    letra = row.charAt(y);

                }

                if (letra != '.' && y == columns - 1) {
                    count[countaux] = number;
                    countaux++;
                }
            }
        }
        // Ciclo encarregue de calcular as varias combinaçoes possi­veis para cada sequencia encontrada.
        for (int counter = 0; counter < countaux; counter++) {
            int seq = count[counter];
            long mult = 1;
            for (int aux = 1; aux <= seq; aux++) {
                long value = 0;
                for (int auxLego = 0; auxLego < 9; auxLego++) {
                    if (lego[auxLego] <= aux) {
                        if (aux - lego[auxLego] == 0) {

                            value++;

                        } else if (aux - lego[auxLego] != 0) {

                            value = value + subtrees[aux - lego[auxLego]];

                        }
                    } else {
                        break;
                    }
                    subtrees[aux] = value;
                }
            }
            mult = mult * subtrees[seq];
            result = result * mult;

        }

        System.out.println(result);

    }
}