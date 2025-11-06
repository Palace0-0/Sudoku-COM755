
package sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SudokuPanel extends javax.swing.JPanel {
    Sudoku sudoku = new Sudoku("dificil");
    private JTextField campoSelecionado;
    
    //Esta função printa os valores do jogo no paineltabuleiro
    private void popularPainel() {
        int[][] matriz = sudoku.getJogo();
        Component[] blocos = PainelTabuleiro.getComponents(); // 9 JPanels

        for (int b = 0; b < blocos.length; b++) {
            if (blocos[b] instanceof JPanel bloco) {
                Component[] campos = bloco.getComponents(); // 9 JTextFields

                for (int c = 0; c < campos.length; c++) {
                    if (campos[c] instanceof JTextField campo) {

                        // Calcula linha e coluna corretas na matriz
                        int blocoLinha = (b / 3) * 3; // linha inicial do bloco
                        int blocoColuna = (b % 3) * 3; // coluna inicial do bloco

                        int linha = blocoLinha + (c / 3);
                        int coluna = blocoColuna + (c % 3);

                        // Salva posição no JTextField
                        campo.putClientProperty("linha", linha);
                        campo.putClientProperty("coluna", coluna);

                        int valor = matriz[linha][coluna];

                        campo.setText(valor == 0 ? "" : String.valueOf(valor));
                        campo.setEditable(valor == 0); // apenas células vazias editáveis
                        
                        
                        campo.setBackground(Color.WHITE);
                        campo.setCaretColor(Color.WHITE);
                    }
                }
            }
        }
    }


    private void configurarcampoSelecionado(){
        for (Component c : PainelTabuleiro.getComponents()) {
            
            if (c instanceof JPanel){
                
                JPanel subPainel = (JPanel) c;
                for(Component e: subPainel.getComponents()){
                    
                    if(e instanceof JTextField){
                        JTextField campo = (JTextField) e;
                        
                        campo.addFocusListener(new java.awt.event.FocusAdapter() {
                            @Override
                            public void focusGained(java.awt.event.FocusEvent evt) {
                                campoSelecionado = campo;
                                destacarLinhaColunaBloco();
                            }

                            @Override
                            public void focusLost(java.awt.event.FocusEvent evt) {
                             
                            }
                        });
                    }
                }
            }
        }
    }
    
    private void destacarLinhaColunaBloco() {
        if (campoSelecionado == null) return;

        int linhaSel = (int) campoSelecionado.getClientProperty("linha");
        int colunaSel = (int) campoSelecionado.getClientProperty("coluna");

        // Bloco do campo selecionado
        int blocoLinhaSel = (linhaSel / 3) * 3;
        int blocoColunaSel = (colunaSel / 3) * 3;

        Component[] blocos = PainelTabuleiro.getComponents();
        for (int b = 0; b < blocos.length; b++) {
            if (blocos[b] instanceof JPanel bloco) {
                Component[] campos = bloco.getComponents();
                for (int c = 0; c < campos.length; c++) {
                    if (campos[c] instanceof JTextField campo) {
                        int blocoLinha = (b / 3) * 3;
                        int blocoColuna = (b % 3) * 3;
                        int linha = blocoLinha + (c / 3);
                        int coluna = blocoColuna + (c % 3);

                        // Não sobrescrever campos com erro
                        if (!campo.getBackground().equals(new Color(255, 180, 180))) {
                            if (campo == campoSelecionado) {
                                campo.setBackground(new Color(255, 255, 180)); // amarelo claro
                            } else {
                                boolean mesmaLinha = linha == linhaSel;
                                boolean mesmaColuna = coluna == colunaSel;
                                boolean mesmoBloco = linha >= blocoLinhaSel && linha < blocoLinhaSel + 3 &&
                                                     coluna >= blocoColunaSel && coluna < blocoColunaSel + 3;
                                if (mesmaLinha || mesmaColuna || mesmoBloco) {
                                    campo.setBackground(new Color(220, 240, 255)); // azul clarinho
                                } else {
                                    campo.setBackground(Color.WHITE); // padrão
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Adiciona o valor a célula desejada no tabuleiro
    private void adicionarValor(int valor){
        int [][] jogo = sudoku.getJogo();
        int[][] gabaritro = sudoku.getTabuleiro();
        
        //Pega a linha e coluna do jtextfield selecionado
        int linha = (int) campoSelecionado.getClientProperty("linha");
        int coluna = (int) campoSelecionado.getClientProperty("coluna");
        
        //Verifica se tem um jtextfield selecionado e se o é um espaço a ser prenchido
        if (campoSelecionado != null && campoSelecionado.isEditable()){
            
            //Inseri o valor ao tabuleiro jogo para manter as compraçoes e tudo mais
            jogo[linha][coluna] = valor;
            sudoku.setJogo(jogo);
            
            //Define o texto do jtextfield selecionado como o valor digitado
            campoSelecionado.setText(String.valueOf(valor));
            
            //Caso o valor não seja o certo o campo fica vermelhor
            if(jogo[linha][coluna] != gabaritro[linha][coluna]){
                campoSelecionado.setBackground(new Color(255, 180, 180));
                
            }else{
                campoSelecionado.setEditable(false);
                campoSelecionado.setBackground(Color.WHITE);
            }
            
        }
        
        
        

    }
    
    public SudokuPanel() {
        
        initComponents();
        popularPainel();
        sudoku.printartabuleiro();
        configurarcampoSelecionado();
        
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelTabuleiro = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        jTextField66 = new javax.swing.JTextField();
        jTextField73 = new javax.swing.JTextField();
        jTextField74 = new javax.swing.JTextField();
        jTextField75 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextField58 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        jTextField67 = new javax.swing.JTextField();
        jTextField68 = new javax.swing.JTextField();
        jTextField69 = new javax.swing.JTextField();
        jTextField76 = new javax.swing.JTextField();
        jTextField77 = new javax.swing.JTextField();
        jTextField78 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTextField61 = new javax.swing.JTextField();
        jTextField62 = new javax.swing.JTextField();
        jTextField63 = new javax.swing.JTextField();
        jTextField70 = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        jTextField72 = new javax.swing.JTextField();
        jTextField79 = new javax.swing.JTextField();
        jTextField80 = new javax.swing.JTextField();
        jTextField81 = new javax.swing.JTextField();
        PainelTeclado = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        PainelBotoes = new javax.swing.JPanel();

        setBackground(new java.awt.Color(245, 245, 245));

        PainelTabuleiro.setBackground(new java.awt.Color(129, 172, 220));
        PainelTabuleiro.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(129, 172, 220)));
        PainelTabuleiro.setMinimumSize(new java.awt.Dimension(550, 550));
        PainelTabuleiro.setLayout(new java.awt.GridLayout(3, 3, 4, 4));

        jPanel1.setBackground(new java.awt.Color(195, 214, 235));
        jPanel1.setLayout(new java.awt.GridLayout(3, 3, 1, 1));

        jTextField1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(79, 115, 156));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField1);

        jTextField2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(79, 115, 156));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField2);

        jTextField3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(79, 115, 156));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField3);

        jTextField10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(79, 115, 156));
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField10);

        jTextField11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(79, 115, 156));
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField11);

        jTextField12.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(79, 115, 156));
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField12);

        jTextField19.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField19.setForeground(new java.awt.Color(79, 115, 156));
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField19.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField19);

        jTextField20.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField20.setForeground(new java.awt.Color(79, 115, 156));
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField20.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField20);

        jTextField21.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField21.setForeground(new java.awt.Color(79, 115, 156));
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField21.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField21);

        PainelTabuleiro.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(195, 214, 235));
        jPanel2.setLayout(new java.awt.GridLayout(3, 3, 2, 2));

        jTextField4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(79, 115, 156));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField4);

        jTextField5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(79, 115, 156));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField5);

        jTextField6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(79, 115, 156));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField6);

        jTextField13.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(79, 115, 156));
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField13);

        jTextField14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField14.setForeground(new java.awt.Color(79, 115, 156));
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField14);

        jTextField15.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(79, 115, 156));
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField15);

        jTextField22.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField22.setForeground(new java.awt.Color(79, 115, 156));
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField22);

        jTextField23.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField23.setForeground(new java.awt.Color(79, 115, 156));
        jTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField23);

        jTextField24.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField24.setForeground(new java.awt.Color(79, 115, 156));
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField24.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField24);

        PainelTabuleiro.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(195, 214, 235));
        jPanel3.setLayout(new java.awt.GridLayout(3, 3, 2, 2));

        jTextField7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(79, 115, 156));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField7);

        jTextField8.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(79, 115, 156));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField8);

        jTextField9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(79, 115, 156));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField9);

        jTextField16.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField16.setForeground(new java.awt.Color(79, 115, 156));
        jTextField16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField16.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField16);

        jTextField17.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField17.setForeground(new java.awt.Color(79, 115, 156));
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField17);

        jTextField18.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField18.setForeground(new java.awt.Color(79, 115, 156));
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField18);

        jTextField25.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField25.setForeground(new java.awt.Color(79, 115, 156));
        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField25.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField25);

        jTextField26.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField26.setForeground(new java.awt.Color(79, 115, 156));
        jTextField26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField26.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField26);

        jTextField27.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField27.setForeground(new java.awt.Color(79, 115, 156));
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField27.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(jTextField27);

        PainelTabuleiro.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(195, 214, 235));
        jPanel4.setLayout(new java.awt.GridLayout(3, 3, 2, 2));

        jTextField28.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField28.setForeground(new java.awt.Color(79, 115, 156));
        jTextField28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField28.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel4.add(jTextField28);

        jTextField29.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField29.setForeground(new java.awt.Color(79, 115, 156));
        jTextField29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField29.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel4.add(jTextField29);

        jTextField30.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField30.setForeground(new java.awt.Color(79, 115, 156));
        jTextField30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField30.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel4.add(jTextField30);

        jTextField37.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField37.setForeground(new java.awt.Color(79, 115, 156));
        jTextField37.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField37.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel4.add(jTextField37);

        jTextField38.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField38.setForeground(new java.awt.Color(79, 115, 156));
        jTextField38.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField38.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel4.add(jTextField38);

        jTextField39.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField39.setForeground(new java.awt.Color(79, 115, 156));
        jTextField39.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField39.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel4.add(jTextField39);

        jTextField46.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField46.setForeground(new java.awt.Color(79, 115, 156));
        jTextField46.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField46.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel4.add(jTextField46);

        jTextField47.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField47.setForeground(new java.awt.Color(79, 115, 156));
        jTextField47.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField47.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel4.add(jTextField47);

        jTextField48.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField48.setForeground(new java.awt.Color(79, 115, 156));
        jTextField48.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField48.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel4.add(jTextField48);

        PainelTabuleiro.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(195, 214, 235));
        jPanel5.setLayout(new java.awt.GridLayout(3, 3, 2, 2));

        jTextField31.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField31.setForeground(new java.awt.Color(79, 115, 156));
        jTextField31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField31.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField31);

        jTextField32.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField32.setForeground(new java.awt.Color(79, 115, 156));
        jTextField32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField32.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField32);

        jTextField33.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField33.setForeground(new java.awt.Color(79, 115, 156));
        jTextField33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField33.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField33);

        jTextField40.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField40.setForeground(new java.awt.Color(79, 115, 156));
        jTextField40.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField40.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField40);

        jTextField41.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField41.setForeground(new java.awt.Color(79, 115, 156));
        jTextField41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField41.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField41);

        jTextField42.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField42.setForeground(new java.awt.Color(79, 115, 156));
        jTextField42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField42.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField42);

        jTextField49.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField49.setForeground(new java.awt.Color(79, 115, 156));
        jTextField49.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField49.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField49);

        jTextField50.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField50.setForeground(new java.awt.Color(79, 115, 156));
        jTextField50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField50.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField50);

        jTextField51.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField51.setForeground(new java.awt.Color(79, 115, 156));
        jTextField51.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField51.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel5.add(jTextField51);

        PainelTabuleiro.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(195, 214, 235));
        jPanel6.setLayout(new java.awt.GridLayout(3, 3, 2, 2));

        jTextField34.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField34.setForeground(new java.awt.Color(79, 115, 156));
        jTextField34.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField34.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTextField34);

        jTextField35.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField35.setForeground(new java.awt.Color(79, 115, 156));
        jTextField35.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField35.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTextField35);

        jTextField36.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField36.setForeground(new java.awt.Color(79, 115, 156));
        jTextField36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField36.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTextField36);

        jTextField43.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField43.setForeground(new java.awt.Color(79, 115, 156));
        jTextField43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField43.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTextField43);

        jTextField44.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField44.setForeground(new java.awt.Color(79, 115, 156));
        jTextField44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField44.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTextField44);

        jTextField45.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField45.setForeground(new java.awt.Color(79, 115, 156));
        jTextField45.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField45.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTextField45);

        jTextField52.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField52.setForeground(new java.awt.Color(79, 115, 156));
        jTextField52.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField52.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTextField52);

        jTextField53.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField53.setForeground(new java.awt.Color(79, 115, 156));
        jTextField53.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField53.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTextField53);

        jTextField54.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField54.setForeground(new java.awt.Color(79, 115, 156));
        jTextField54.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField54.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel6.add(jTextField54);

        PainelTabuleiro.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(195, 214, 235));
        jPanel7.setLayout(new java.awt.GridLayout(3, 3, 2, 2));

        jTextField55.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField55.setForeground(new java.awt.Color(79, 115, 156));
        jTextField55.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField55.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField55);

        jTextField56.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField56.setForeground(new java.awt.Color(79, 115, 156));
        jTextField56.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField56.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField56);

        jTextField57.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField57.setForeground(new java.awt.Color(79, 115, 156));
        jTextField57.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField57.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField57);

        jTextField64.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField64.setForeground(new java.awt.Color(79, 115, 156));
        jTextField64.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField64.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField64);

        jTextField65.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField65.setForeground(new java.awt.Color(79, 115, 156));
        jTextField65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField65.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField65);

        jTextField66.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField66.setForeground(new java.awt.Color(79, 115, 156));
        jTextField66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField66.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField66);

        jTextField73.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField73.setForeground(new java.awt.Color(79, 115, 156));
        jTextField73.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField73.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField73);

        jTextField74.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField74.setForeground(new java.awt.Color(79, 115, 156));
        jTextField74.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField74.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField74);

        jTextField75.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField75.setForeground(new java.awt.Color(79, 115, 156));
        jTextField75.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField75.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField75);

        PainelTabuleiro.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(195, 214, 235));
        jPanel8.setLayout(new java.awt.GridLayout(3, 3, 2, 2));

        jTextField58.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField58.setForeground(new java.awt.Color(79, 115, 156));
        jTextField58.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField58.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(jTextField58);

        jTextField59.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField59.setForeground(new java.awt.Color(79, 115, 156));
        jTextField59.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField59.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(jTextField59);

        jTextField60.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField60.setForeground(new java.awt.Color(79, 115, 156));
        jTextField60.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField60.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(jTextField60);

        jTextField67.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField67.setForeground(new java.awt.Color(79, 115, 156));
        jTextField67.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField67.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(jTextField67);

        jTextField68.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField68.setForeground(new java.awt.Color(79, 115, 156));
        jTextField68.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField68.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(jTextField68);

        jTextField69.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField69.setForeground(new java.awt.Color(79, 115, 156));
        jTextField69.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField69.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(jTextField69);

        jTextField76.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField76.setForeground(new java.awt.Color(79, 115, 156));
        jTextField76.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField76.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(jTextField76);

        jTextField77.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField77.setForeground(new java.awt.Color(79, 115, 156));
        jTextField77.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField77.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(jTextField77);

        jTextField78.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField78.setForeground(new java.awt.Color(79, 115, 156));
        jTextField78.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField78.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(jTextField78);

        PainelTabuleiro.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(195, 214, 235));
        jPanel9.setLayout(new java.awt.GridLayout(3, 3, 2, 2));

        jTextField61.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField61.setForeground(new java.awt.Color(79, 115, 156));
        jTextField61.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField61.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(jTextField61);

        jTextField62.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField62.setForeground(new java.awt.Color(79, 115, 156));
        jTextField62.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField62.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(jTextField62);

        jTextField63.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField63.setForeground(new java.awt.Color(79, 115, 156));
        jTextField63.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField63.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(jTextField63);

        jTextField70.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField70.setForeground(new java.awt.Color(79, 115, 156));
        jTextField70.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField70.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(jTextField70);

        jTextField71.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField71.setForeground(new java.awt.Color(79, 115, 156));
        jTextField71.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField71.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(jTextField71);

        jTextField72.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField72.setForeground(new java.awt.Color(79, 115, 156));
        jTextField72.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField72.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(jTextField72);

        jTextField79.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField79.setForeground(new java.awt.Color(79, 115, 156));
        jTextField79.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField79.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(jTextField79);

        jTextField80.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField80.setForeground(new java.awt.Color(79, 115, 156));
        jTextField80.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField80.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(jTextField80);

        jTextField81.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField81.setForeground(new java.awt.Color(79, 115, 156));
        jTextField81.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField81.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(jTextField81);

        PainelTabuleiro.add(jPanel9);

        PainelTeclado.setBackground(new java.awt.Color(245, 245, 245));

        jButton1.setBackground(new java.awt.Color(243, 249, 255));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(79, 115, 156));
        jButton1.setText("1");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(243, 249, 255));
        jButton2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(79, 115, 156));
        jButton2.setText("2");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(243, 249, 255));
        jButton3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton3.setForeground(new java.awt.Color(79, 115, 156));
        jButton3.setText("4");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(243, 249, 255));
        jButton4.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton4.setForeground(new java.awt.Color(79, 115, 156));
        jButton4.setText("3");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(243, 249, 255));
        jButton5.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton5.setForeground(new java.awt.Color(79, 115, 156));
        jButton5.setText("8");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(243, 249, 255));
        jButton6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton6.setForeground(new java.awt.Color(79, 115, 156));
        jButton6.setText("7");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(243, 249, 255));
        jButton7.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton7.setForeground(new java.awt.Color(79, 115, 156));
        jButton7.setText("5");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(243, 249, 255));
        jButton8.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton8.setForeground(new java.awt.Color(79, 115, 156));
        jButton8.setText("6");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(243, 249, 255));
        jButton9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton9.setForeground(new java.awt.Color(79, 115, 156));
        jButton9.setText("9");
        jButton9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelTecladoLayout = new javax.swing.GroupLayout(PainelTeclado);
        PainelTeclado.setLayout(PainelTecladoLayout);
        PainelTecladoLayout.setHorizontalGroup(
            PainelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTecladoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelTecladoLayout.setVerticalGroup(
            PainelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTecladoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PainelBotoes.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout PainelBotoesLayout = new javax.swing.GroupLayout(PainelBotoes);
        PainelBotoes.setLayout(PainelBotoesLayout);
        PainelBotoesLayout.setHorizontalGroup(
            PainelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );
        PainelBotoesLayout.setVerticalGroup(
            PainelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PainelTabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PainelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PainelTeclado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PainelTabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PainelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addComponent(PainelTeclado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        adicionarValor(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        adicionarValor(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        adicionarValor(3);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        adicionarValor(4);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        adicionarValor(5);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        adicionarValor(6);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        adicionarValor(7);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        adicionarValor(8);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        adicionarValor(9);
    }//GEN-LAST:event_jButton9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelBotoes;
    private javax.swing.JPanel PainelTabuleiro;
    private javax.swing.JPanel PainelTeclado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField70;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField72;
    private javax.swing.JTextField jTextField73;
    private javax.swing.JTextField jTextField74;
    private javax.swing.JTextField jTextField75;
    private javax.swing.JTextField jTextField76;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField78;
    private javax.swing.JTextField jTextField79;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField80;
    private javax.swing.JTextField jTextField81;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
