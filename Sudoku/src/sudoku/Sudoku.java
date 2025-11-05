
package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Sudoku {
    
    private int [][] tabuleiro = new int [9][9];
    
    //Construtor da classe
    public Sudoku() {
        populartabuleiro();
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }
    
    
    private boolean verificarposicao(int valor, int linha, int coluna){
        
        //Verifica se o valar a ser add ja existe na linha
        for (int i = 0; i < tabuleiro.length; i++) {
            if (valor == tabuleiro[linha][i]){
                //System.out.println(valor + "já existe na linha");
                return false;
            }
        }
        
        //Verifica se o valar a ser add ja existe na coluna
        for (int j = 0; j < tabuleiro.length; j++) {
            if (valor == tabuleiro[j][coluna]){
                //System.out.println(valor + "já existe na coluna");
                return false;
            }
        }
        
        //Verifica se o valar a ser add ja existe na matriz 3x3
        int inicioLinha = (linha / 3) * 3;
        int inicioColuna = (coluna / 3) * 3;
        
        for (int i = inicioLinha; i < inicioLinha + 3; i++) {
            for (int j = inicioColuna; j < inicioColuna + 3; j++) {
                if (tabuleiro[i][j] == valor) {
                    //System.out.println(valor + "já existe na matriz 3x3");
                    return false;
                }
            }
        }
        
        return true;
    }
    
     // Preenche o tabuleiro usando backtracking
    private boolean populartabuleiro() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tabuleiro[i][j] == 0) { // se a célula estiver vazia

                    // Cria lista de números 1-9 e embaralha para aleatoriedade
                    List<Integer> numeros = new ArrayList<>();
                    for (int n = 1; n <= 9; n++) numeros.add(n);
                    Collections.shuffle(numeros);

                    for (int valor : numeros) {
                        if (verificarposicao(valor, i, j)) {
                            tabuleiro[i][j] = valor;

                            // Chamada recursiva
                            if (populartabuleiro()) {
                                return true; // encontrou solução
                            }

                            // Se não der certo, desfaz (backtrack)
                            tabuleiro[i][j] = 0;
                        }
                    }

                    // Nenhum número funcionou nesta célula, volta
                    return false;
                }
            }
        }

        // Todas as células foram preenchidas
        return true;
    }
    
    public void printartabuleiro (){
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                System.out.print(tabuleiro[i][j]);
            }
            System.out.println("");
        }
    }
    
    
    
 
}
