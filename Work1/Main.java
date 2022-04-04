import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        
        
        // Array com o tamanho das peÃ§as de lego disponÃ­veis.
        int lego[] = {1,2,3,4,6,8,10,12,16};

        long result = 1;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String s[] = input.readLine().split(" ");

        // NÃºmero de linhas do input.
        int rows = Integer.parseInt(s[0]);

        // NÃºmero de colunas do input.
        int columns = Integer.parseInt(s[1]);

        int maxSeq = columns * rows;

        long subtrees[] = new long[columns+1];

        String row;

        // Array de inteiros que irÃ¡ conter o tamanho das sequÃªncias.
        int count[] = new int[maxSeq];
        int countaux = 0;

        // Ciclo utilizado para fazer a contagem do tamanho das diferentes sequÃªncias encontradas no input dado.
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
        // Ciclo encarregue de calcular as vÃ¡rias combinaÃ§Ãµes possÃ­veis para cada sequÃªncia encontrada.
        for (int counter = 0; counter < countaux; counter++) {
            int seq = count[counter];
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
            //Vai se multiplicando os valores de cada sequÃªncia, cujo resultado serÃ¡ a resposta final
            result = result * subtrees[seq];

        }

        System.out.println(result);

    }
}