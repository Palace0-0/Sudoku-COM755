
package sudoku;

import java.util.ArrayList;
import java.util.List;


public class Sudoku {
    
    private int [][] tabuleiro = new int [9][9];
    
    private boolean verificarposicao(int valor, int[][]tabuleiro, int linha, int coluna){
        
        //Verifica se o valar a ser add ja existe na linha
        for (int i = 0; i < tabuleiro.length; i++) {
            if (valor == tabuleiro[linha][i]){
                System.out.println(valor + "já existe na linha");
                return false;
            }
        }
        
        //Verifica se o valar a ser add ja existe na coluna
        for (int j = 0; j < tabuleiro.length; j++) {
            if (valor == tabuleiro[j][coluna]){
                System.out.println(valor + "já existe na coluna");
                return false;
            }
        }
        
        //Verifica se o valar a ser add ja existe na matriz 3x3
        int inicioLinha = (linha / 3) * 3;
        int inicioColuna = (coluna / 3) * 3;
        
        for (int i = inicioLinha; i < inicioLinha + 3; i++) {
            for (int j = inicioColuna; j < inicioColuna + 3; j++) {
                if (tabuleiro[i][j] == valor) {
                    System.out.println(valor + "já existe na matriz 3x3");
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void populartabuleiro(){
        // Cria a lista uma vez
        List<Integer> numeros = new ArrayList<>();
        for (int n = 1; n <= 9; n++) numeros.add(n);
        
        //Valida se o valor pode ser inserido e o inseri
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                
            }
            
        }
    }
    
    
    
    public static void main(String[] args) {
       Sudoku jogo = new Sudoku();
       jogo.populartabuleiro();
    }
}
