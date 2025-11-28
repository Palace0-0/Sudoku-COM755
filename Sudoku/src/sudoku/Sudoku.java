
package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Sudoku {
    
    private int [][] tabuleiro = new int [9][9];
    private int [][] jogo ;
    private String dificuldade;
    
    // Variável auxiliar para contar soluções
    private int solucoesEncontradas = 0;
    
    //Construtor da classe
    public Sudoku(String dificuldade) {
        resetarTabuleiro();
        populartabuleiro();
        this.jogo = gerarJogo(dificuldade);
        this.dificuldade = dificuldade;
        
    } 
    
    //Este construtor é para quando o jogo é continuado
    public Sudoku(String gabarito, String jogoAtual, String dificuldade) {
        resetarTabuleiro();
        
        this.tabuleiro = converterStringParaMatriz(gabarito);
        this.jogo = converterStringParaMatriz(jogoAtual);
        
        this.dificuldade = dificuldade;
        
        
    }
    
  
    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public int[][] getJogo() {
        return jogo;
    }

    public void setJogo(int[][] jogo) {
        this.jogo = jogo;
    }

    public String getDificuldade() {
        return dificuldade;
    }
    

    //Verifica a posição para prencher o tabuleiro
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
    
    //Verifica a posição no solver 
    private boolean verificarposicaoSolver(int[][] JogoAtual, int valor, int linha, int coluna){
        
        //Verifica se o valar a ser add ja existe na linha
        for (int i = 0; i < JogoAtual.length; i++) {
            if (valor == JogoAtual[linha][i]){
                //System.out.println(valor + "já existe na linha");
                return false;
            }
        }
        
        //Verifica se o valar a ser add ja existe na coluna
        for (int j = 0; j < JogoAtual.length; j++) {
            if (valor == JogoAtual[j][coluna]){
                //System.out.println(valor + "já existe na coluna");
                return false;
            }
        }
        
        //Verifica se o valar a ser add ja existe na matriz 3x3
        int inicioLinha = (linha / 3) * 3;
        int inicioColuna = (coluna / 3) * 3;
        
        for (int i = inicioLinha; i < inicioLinha + 3; i++) {
            for (int j = inicioColuna; j < inicioColuna + 3; j++) {
                if (JogoAtual[i][j] == valor) {
                    //System.out.println(valor + "já existe na matriz 3x3");
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean BacktrackingSolver(int[][] JogoAtual) {

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {

                // Se achou uma casa vazia (0), precisamos tentar preencher
                if (JogoAtual[linha][coluna] == 0) {

                    for (int valor = 1; valor <= 9; valor++) {
                        // ATENÇÃO: Corrigi a ordem dos parâmetros aqui
                        if (verificarposicaoSolver(JogoAtual, valor, linha, coluna)) {

                            JogoAtual[linha][coluna] = valor; // Tenta o número

                            // Chama a recursão. Se ela retornar true, achamos a solução!
                            if (BacktrackingSolver(JogoAtual)) {
                                return true;
                            }

                            // Se chegou aqui, o número não serviu. Backtrack (zera a casa)
                            JogoAtual[linha][coluna] = 0;
                        }
                    }

                    // Se testou 1 a 9 e nada funcionou nesta casa vazia,
                    // significa que o erro está numa jogada anterior. Retorna false.
                    return false;
                }
            }
        }

        // Se percorreu os dois loops inteiros e não achou nenhum 0, 
        // significa que o tabuleiro está cheio e correto.
        return true; 
    }
    
    public void resolverJogo(){
        BacktrackingSolver(jogo);
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
    
    // --- NOVOS MÉTODOS PARA O MODO CAOS ---

    // Retorna uma lista com várias soluções possíveis (até um limite)
    public List<int[][]> encontrarTodasSolucoes(int[][] jogoInicial) {
        List<int[][]> listaSolucoes = new ArrayList<>();
        
        // Trabalhamos numa cópia para não estragar o jogo atual visualmente agora
        int[][] copiaTrabalho = copiarMatriz(jogoInicial);
        
        // Chama o método recursivo que preenche a lista
        buscarSolucoesRecursivo(copiaTrabalho, listaSolucoes);
        
        return listaSolucoes;
    }

    private void buscarSolucoesRecursivo(int[][] board, List<int[][]> lista) {
        // Limite de segurança: se já achou 10 soluções, para (para não travar o PC)
        if (lista.size() >= 100) return;

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {
                
                if (board[linha][coluna] == 0) { // Casa vazia
                    
                    for (int valor = 1; valor <= 9; valor++) {
                        if (verificarposicaoSolver(board, valor, linha, coluna)) {
                            
                            board[linha][coluna] = valor;
                            
                            // Continua procurando...
                            buscarSolucoesRecursivo(board, lista);
                            
                            // BACKTRACK: Zera para tentar achar OUTRO caminho com outro número
                            board[linha][coluna] = 0;
                        }
                    }
                    return; // Se testou 1-9 e nada serviu nesta casa, volta
                }
            }
        }

        // Se chegou aqui, o tabuleiro está completo (uma solução foi achada)
        // Adicionamos uma CÓPIA PROFUNDA na lista
        lista.add(copiarMatriz(board));
    }
    
    private int[][] gerarJogo(String dificuldade) {
        int[][] jogo = new int[9][9];
        for (int i = 0; i < 9; i++) System.arraycopy(tabuleiro[i], 0, jogo[i], 0, 9);

        int tentativas = 0;
        
        // Agora usamos uma variável inteira para o limite, em vez de booleano
        int limiteSolucoes = 1; 

        switch (dificuldade.toLowerCase()) {
            case "facil": tentativas = 30; break;
            case "medio": tentativas = 50; break; 
            case "dificil": tentativas = 80; break;
            case "caos": 
                tentativas = 80; // Tenta remover bastante
                limiteSolucoes = 300; // PERMITE ATÉ 5 SOLUÇÕES (Caos Controlado)
                break;
            default: tentativas = 30;
        }

        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < 81; i++) posicoes.add(i);
        Collections.shuffle(posicoes);

        int removidos = 0;
        
        for (int i = 0; i < 81; i++) {
            if (removidos >= tentativas) break;

            int pos = posicoes.get(i);
            int linha = pos / 9;
            int coluna = pos % 9;

            int valorBackup = jogo[linha][coluna];
            jogo[linha][coluna] = 0; // Remove

            // --- SEMPRE VERIFICA, MAS COM LIMITES DIFERENTES ---
            int[][] copiaParaTeste = copiarMatriz(jogo);
            solucoesEncontradas = 0;
            
            // Passamos o limite configurado (1 para normal, 5 para caos)
            contarSolucoes(copiaParaTeste, limiteSolucoes);

            // Se achou mais soluções do que o permitido, desfaz.
            if (solucoesEncontradas > limiteSolucoes) {
                jogo[linha][coluna] = valorBackup; 
            } else {
                // Se achou 0 (impossível pois partimos de um jogo cheio) 
                // ou qualquer valor até o limite, aceita.
                removidos++;
            }
        }
        return jogo;
    }

    // Método auxiliar para criar cópias profundas de matriz
    private int[][] copiarMatriz(int[][] original) {
        int[][] copia = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(original[i], 0, copia[i], 0, 9);
        }
        return copia;
    }

    // Um solver modificado que não para na primeira solução, ele conta quantas existem
    // Para otimizar, paramos assim que acharmos 2 (pois já sabemos que não é único)
    // Agora aceita um parametro 'limite'
    private void contarSolucoes(int[][] board, int limite) {
        // Se já passou do limite desejado, para de processar para economizar CPU
        if (solucoesEncontradas > limite) return; 

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int valor = 1; valor <= 9; valor++) {
                        if (verificarposicaoSolver(board, valor, i, j)) {
                            board[i][j] = valor;
                            contarSolucoes(board, limite); // Passa o limite adiante
                            board[i][j] = 0; 
                        }
                    }
                    return;
                }
            }
        }
        solucoesEncontradas++;
    }
    
     // Método auxiliar que faz a mágica de conversão
    private int[][] converterStringParaMatriz(String dados) {
        int[][] matriz = new int[9][9];
        int contador = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Pega o caractere na posição 'contador' e transforma em int
                // Ex: pega o char '5' e transforma no número 5
                matriz[i][j] = Character.getNumericValue(dados.charAt(contador));
                contador++;
            }
        }
        return matriz;
    }
    
    private void resetarTabuleiro() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }

    public void printartabuleiro() {
        
        System.out.println("Dificuldade : "+dificuldade);
        
        for (int i = 0; i < 9; i++) {

            // Linha de separação dos blocos
            if (i % 3 == 0) {
                System.out.println(" -------------------------");
            }

            for (int j = 0; j < 9; j++) {

                if (j % 3 == 0) {
                    System.out.print(" | ");
                }

                System.out.print(tabuleiro[i][j] + " ");
            }

            System.out.println(" |");
        }

        System.out.println(" -------------------------\n");
    }

    
    
 
}
