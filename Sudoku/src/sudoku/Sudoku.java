
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
    
    // ==========================================
    //      CONSTRUTORES
    // ==========================================
    
    //Construtor da classe
    public Sudoku(String dificuldade) {
        populartabuleiro();
        this.jogo = gerarJogo(dificuldade);
        this.dificuldade = dificuldade;
        
    } 
    
    //Este construtor é para quando o jogo é continuado
    public Sudoku(String gabarito, String jogoAtual, String dificuldade) {
        this.tabuleiro = converterStringParaMatriz(gabarito);
        this.jogo = converterStringParaMatriz(jogoAtual);
        
        this.dificuldade = dificuldade;
        
        
    }
    
    
    // ==========================================
    //              API PÚBLICA 
    // ==========================================
    
    //Resolve o jogo atual utilizando o solver
    public void resolverJogo(){
        BacktrackingSolver(jogo);
    }
    
    // Retorna uma lista com várias soluções possíveis (até um limite)
    public List<int[][]> encontrarTodasSolucoes(int[][] jogoInicial) {
        List<int[][]> listaSolucoes = new ArrayList<>();
        
        // Trabalhamos numa cópia para não estragar o jogo atual visualmente agora
        int[][] copiaTrabalho = copiarMatriz(jogoInicial);
        
        // Chama o método recursivo que preenche a lista
        buscarSolucoesRecursivo(copiaTrabalho, listaSolucoes);
        
        return listaSolucoes;
    }
    
    //Printa o gabarito no console
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
    
    // ==========================================
    //    LÓGICA CORE (Privados de alto nível)
    // ==========================================
    
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
                        if (verificarPosicao(tabuleiro, valor, i, j)) {
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
    
    //Gera o jogo a ser jogado com base na dificuldade
    private int[][] gerarJogo(String dificuldade) {
        // Cria uma cópia do gabarito para não estragar o original
        int[][] jogo = new int[9][9];
        for (int i = 0; i < 9; i++) System.arraycopy(tabuleiro[i], 0, jogo[i], 0, 9);

        int tentativas = 0;
        
        // Agora usamos uma variável inteira para o limite, em vez de booleano
        int limiteSolucoes = 1; 

        switch (dificuldade.toLowerCase()) {
            case "facil": tentativas = 36; break;
            case "medio": tentativas = 46; break; 
            case "dificil": tentativas = 56; break;
            case "caos": 
                tentativas = 62; // Tenta remover bastante
                limiteSolucoes = 5; // PERMITE ATÉ 5 SOLUÇÕES (Caos Controlado)
                break;
            default: tentativas = 30;
        }

        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < 81; i++) posicoes.add(i);
        Collections.shuffle(posicoes);

        int removidos = 0;
        
        // Tenta remover N vezes (conforme a dificuldade)
        for (int i = 0; i < 81; i++) {
            if (removidos >= tentativas) break;
            
            // Traduz o número 0-80 para linha/coluna
            int pos = posicoes.get(i);
            int linha = pos / 9;
            int coluna = pos % 9;
            
            //Guarda o valor q foi apagado e apaga
            int valorBackup = jogo[linha][coluna];
            jogo[linha][coluna] = 0; // Remove

            // Fazemos uma cópia desse jogo com o buraco novo
            int[][] copiaParaTeste = copiarMatriz(jogo);
            solucoesEncontradas = 0;
            
            // Passamos o limite configurado (1 para normal, 5 para caos)
            contarSolucoes(copiaParaTeste, limiteSolucoes);

            // Se achou mais soluções do que o permitido, desfaz.
            if (solucoesEncontradas > limiteSolucoes) {
                jogo[linha][coluna] = valorBackup; 
            } else {
                // A remoção foi segura, conta como sucesso.
                removidos++;
            }
        }
        return jogo;
    }
    
    //Tenta resolver o jogo atual com backtracj
    private boolean BacktrackingSolver(int[][] JogoAtual) {

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {

                // Se achou uma casa vazia (0), precisamos tentar preencher
                if (JogoAtual[linha][coluna] == 0) {

                    for (int valor = 1; valor <= 9; valor++) {
                        // ATENÇÃO: Corrigi a ordem dos parâmetros aqui
                        if (verificarPosicao(JogoAtual, valor, linha, coluna)) {

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
    
    
    private void buscarSolucoesRecursivo(int[][] board, List<int[][]> lista) {
        // Limite de segurança: se já achou 10 soluções, para (para não travar o PC)
        if (lista.size() >= 10) return;

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {
                
                if (board[linha][coluna] == 0) { // Casa vazia
                    
                    for (int valor = 1; valor <= 9; valor++) {
                        if (verificarPosicao(board, valor, linha, coluna)) {
                            
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
    
    // Um solver modificado que não para na primeira solução, ele conta quantas existem
    private void contarSolucoes(int[][] board, int limite) {
        // Se já passou do limite desejado, para de processar para economizar CPU
        if (solucoesEncontradas > limite) return; 

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int valor = 1; valor <= 9; valor++) {
                        if (verificarPosicao(board, valor, i, j)) {
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

    // ==========================================
    //     UTILITÁRIOS (Helpers de baixo nível)
    // ==========================================
    
    
    //Verifica a posição para prencher o tabuleiro
    private boolean verificarPosicao(int[][] matrizAnalise, int valor, int linha, int coluna){
        
        // Verifica Linha
        for (int i = 0; i < 9; i++) {
            if (matrizAnalise[linha][i] == valor) return false;
        }
        
        // Verifica Coluna
        for (int j = 0; j < 9; j++) {
            if (matrizAnalise[j][coluna] == valor) return false;
        }
        
        // Verifica Quadrante 3x3
        int inicioLinha = (linha / 3) * 3;
        int inicioColuna = (coluna / 3) * 3;
        
        for (int i = inicioLinha; i < inicioLinha + 3; i++) {
            for (int j = inicioColuna; j < inicioColuna + 3; j++) {
                if (matrizAnalise[i][j] == valor) return false;
            }
        }
        
        return true;
    }
    
    // Método auxiliar para criar cópias profundas de matriz
    private int[][] copiarMatriz(int[][] original) {
        int[][] copia = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(original[i], 0, copia[i], 0, 9);
        }
        return copia;
    }
    
    // Método auxiliar que faz a mágica de conversão
    private int[][] converterStringParaMatriz(String dados) {
        int[][] matriz = new int[9][9];
        int contador = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Pega o caractere na posição 'contador' e transforma em int
                matriz[i][j] = Character.getNumericValue(dados.charAt(contador));
                contador++;
            }
        }
        return matriz;
    }
    
    // ==========================================
    //           GETTERS E SETTERS 
    // ==========================================
    
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
    
}
