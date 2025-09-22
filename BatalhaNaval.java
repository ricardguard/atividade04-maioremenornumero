import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        char[][] mar = new char[7][7];
        int naviosEscondidos = 3;
        int naviosEncontrados = 0;

        // Inicializa o mar com água
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                mar[i][j] = '~';
            }
        }

        // Sorteia 3 posições de navios
        for (int k = 0; k < naviosEscondidos; k++) {
            int linha, coluna;
            do {
                linha = random.nextInt(7);
                coluna = random.nextInt(7);
            } while (mar[linha][coluna] == 'N'); // Garante que não sorteie no mesmo lugar
            mar[linha][coluna] = 'N'; // Marca internamente como navio
        }

        System.out.println("Bem-vindo à Batalha Naval Simplificada!");
        System.out.println("Tente encontrar os 3 navios escondidos no mar 7x7.");

        while (naviosEncontrados < naviosEscondidos) {
            exibirMar(mar, false); // Exibe o mar sem mostrar os navios escondidos

            System.out.print("Digite a linha (0-6) para atacar: ");
            int linhaAtaque = scanner.nextInt();
            System.out.print("Digite a coluna (0-6) para atacar: ");
            int colunaAtaque = scanner.nextInt();

            if (linhaAtaque < 0 || linhaAtaque >= 7 || colunaAtaque < 0 || colunaAtaque >= 7) {
                System.out.println("Coordenadas inválidas. Tente novamente.");
                continue;
            }

            if (mar[linhaAtaque][colunaAtaque] == 'N') {
                System.out.println("Parabéns! Você acertou um navio!");
                mar[linhaAtaque][colunaAtaque] = 'X'; // Marca como navio encontrado
                naviosEncontrados++;
            } else if (mar[linhaAtaque][colunaAtaque] == 'X' || mar[linhaAtaque][colunaAtaque] == 'O') {
                System.out.println("Você já atacou essa posição. Tente outra.");
            } else {
                System.out.println("Água! Você errou.");
                mar[linhaAtaque][colunaAtaque] = 'O'; // Marca como água atacada
            }
        }

        System.out.println("\n*** FIM DE JOGO! ***");
        System.out.println("Você encontrou todos os " + naviosEscondidos + " navios!");
        exibirMar(mar, true); // Exibe o mar com todos os navios revelados

        scanner.close();
    }

    public static void exibirMar(char[][] mar, boolean revelarNavios) {
        System.out.println("  0 1 2 3 4 5 6");
        for (int i = 0; i < 7; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 7; j++) {
                if (mar[i][j] == 'N' && !revelarNavios) {
                    System.out.print("~ "); // Esconde navios não encontrados
                } else {
                    System.out.print(mar[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}

