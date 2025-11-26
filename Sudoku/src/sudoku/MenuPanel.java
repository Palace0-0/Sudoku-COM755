
package sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;


public class MenuPanel extends javax.swing.JPanel {
    private Mainframe main;

 
    private void estilizar() {
        // --- PALETA DE CORES ---
        Color azulSidebar = new Color(50, 75, 100); 
        Color azulBotaoPrimario = new Color(79, 115, 156); 
        Color azulClaroFundo = new Color(240, 245, 255);
        Color corTextoMenuSecundario = new Color(0,0,0); 
        Color corTextoConteudoSecundario = new Color(100, 100, 100);

        // Cores de Borda e Destaque
        Color corBordaSair = new Color(90, 120, 150); // Borda sutil para o Sair
        Dimension buttonSize = new Dimension(200, 40);

        // --- 1. CONFIGURAÇÃO GERAL E FUNDOS DOS PAINÉIS ---
        this.setBackground(azulClaroFundo); 
        PainelMenuLateral.setBackground(azulSidebar); 
        //PainelConteudoPrincipal.setBackground(offWhitePainel); 
        PainelMenuLateral.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(40, 60, 80))); 

        // --- 2. LOGO, BOAS-VINDAS E MASCOTE (NA ÁREA PRINCIPAL) ---
        if (lblLogo != null) {
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER); 
            lblLogo.setBorder(BorderFactory.createEmptyBorder(20, 0, 15, 0)); 
        }
        if (lblBoasVindas != null) {
            lblBoasVindas.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblBoasVindas.setForeground(corTextoConteudoSecundario); 
            lblBoasVindas.setHorizontalAlignment(SwingConstants.RIGHT); 
            lblBoasVindas.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 30)); 
        }
        if (lblHamster != null) {
            lblHamster.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // --- 3. ESTILO DOS BOTÕES DA BARRA LATERAL ---

        // A. Prepara o PainelMenuLateral (limpa e configura o BoxLayout)
        PainelMenuLateral.removeAll();
        PainelMenuLateral.setLayout(new BoxLayout(PainelMenuLateral, BoxLayout.Y_AXIS));
        PainelMenuLateral.add(Box.createVerticalStrut(30)); 

        JButton[] botoesMenu = {btnNovoJogo, btnContinuar, btnCarregar, btnRanking}; 

        for (JButton btn : botoesMenu) {
            if (btn == null) continue;

            btn.setBackground(azulSidebar); 
            btn.setForeground(Color.WHITE); 
            btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);

            btn.setAlignmentX(Component.CENTER_ALIGNMENT); 

            // Define o tamanho MÁXIMO e PREFERIDO (ex: 200px de largura)
            btn.setPreferredSize(buttonSize);
            btn.setMaximumSize(buttonSize);

            // Padding interno (para altura)
            btn.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30)); 

            // BORDAS ARREDONDADAS (Default para todos)
            btn.putClientProperty("JComponent.roundRect", true); 

            PainelMenuLateral.add(btn);
            PainelMenuLateral.add(Box.createVerticalStrut(8)); 
        }

        // --- 4. DESTAQUE ESPECIAL PARA "NOVO JOGO" (AÇÃO PRIMÁRIA) ---
        if (btnNovoJogo != null) {
            btnNovoJogo.setBackground(azulBotaoPrimario); 
            btnNovoJogo.setFont(new Font("Segoe UI", Font.BOLD, 17));

            // Mantemos o arredondamento padrão do "roundRect" e aumentamos o padding
            btnNovoJogo.setBorder(BorderFactory.createEmptyBorder(18, 30, 18, 30)); 

        }

        // --- 5. BOTÃO SAIR (NO RODAPÉ COM BORDA) ---

        // Empurra o Sair para o rodapé
        PainelMenuLateral.add(Box.createVerticalGlue()); 

        if (btnSair != null) {
            
            btnSair.setForeground(new Color(150, 200, 255)); 
            btnSair.setBackground(azulSidebar); 
            btnSair.setFont(new Font("Segoe UI", Font.BOLD, 13));
            btnSair.setFocusPainted(false);
            btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnSair.setText("Sair"); 

            // CRIAÇÃO DA BORDA E PADDING
            Border line = new LineBorder(corBordaSair, 1, true); // Borda de 1px, arredondada
            Border empty = BorderFactory.createEmptyBorder(10, 20, 10, 20); // Padding interno

            // Combina a borda e o padding
            btnSair.setBorder(new CompoundBorder(line, empty)); 

            // O botão Sair não deve ter o tamanho fixo dos outros botões
            
            btnSair.setPreferredSize(buttonSize);
            btnSair.setMaximumSize(buttonSize);

            PainelMenuLateral.add(btnSair);
        }
    }

    public MenuPanel(Mainframe main) {
        this.main = main;
        initComponents();
        
        estilizar();
        
      Usuario usuarioLogado = SessaoUsuario.getUsuarioLogado();
        if(usuarioLogado != null){
            lblBoasVindas.setText("Seja bem-vindo, "+ usuarioLogado.getLogin());
        }else{
            lblBoasVindas.setText("Bem-vindo(a)!");
            main.mostrarTela("login");
        }

    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelMenuLateral = new javax.swing.JPanel();
        btnNovoJogo = new javax.swing.JButton();
        btnContinuar = new javax.swing.JButton();
        btnCarregar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnRanking = new javax.swing.JButton();
        PainelConteudoPrincipal = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblBoasVindas = new javax.swing.JLabel();
        PainelImagemHamster = new javax.swing.JPanel();
        lblHamster = new javax.swing.JLabel();

        setBackground(new java.awt.Color(240, 245, 255));
        setLayout(new java.awt.BorderLayout());

        PainelMenuLateral.setBackground(new java.awt.Color(50, 75, 100));
        PainelMenuLateral.setPreferredSize(new java.awt.Dimension(250, 590));

        btnNovoJogo.setBackground(new java.awt.Color(79, 115, 156));
        btnNovoJogo.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnNovoJogo.setForeground(new java.awt.Color(243, 249, 255));
        btnNovoJogo.setText("Novo jogo");
        btnNovoJogo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnNovoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoJogoActionPerformed(evt);
            }
        });

        btnContinuar.setBackground(new java.awt.Color(79, 115, 156));
        btnContinuar.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnContinuar.setForeground(new java.awt.Color(243, 249, 255));
        btnContinuar.setText("Continuar");
        btnContinuar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnCarregar.setBackground(new java.awt.Color(79, 115, 156));
        btnCarregar.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnCarregar.setForeground(new java.awt.Color(243, 249, 255));
        btnCarregar.setText("Carregar");
        btnCarregar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });

        btnSair.setBackground(new java.awt.Color(255, 102, 102));
        btnSair.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnSair.setForeground(new java.awt.Color(243, 249, 255));
        btnSair.setText("Sair");
        btnSair.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnRanking.setBackground(new java.awt.Color(79, 115, 156));
        btnRanking.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnRanking.setForeground(new java.awt.Color(243, 249, 255));
        btnRanking.setText("Ranking");
        btnRanking.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRankingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelMenuLateralLayout = new javax.swing.GroupLayout(PainelMenuLateral);
        PainelMenuLateral.setLayout(PainelMenuLateralLayout);
        PainelMenuLateralLayout.setHorizontalGroup(
            PainelMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovoJogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCarregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRanking, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PainelMenuLateralLayout.setVerticalGroup(
            PainelMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLateralLayout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(btnNovoJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 554, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        add(PainelMenuLateral, java.awt.BorderLayout.LINE_START);

        PainelConteudoPrincipal.setBackground(new java.awt.Color(240, 245, 255));
        PainelConteudoPrincipal.setLayout(new java.awt.BorderLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Logo_Sudoku.png"))); // NOI18N
        PainelConteudoPrincipal.add(lblLogo, java.awt.BorderLayout.CENTER);

        lblBoasVindas.setText("jLabel2");
        PainelConteudoPrincipal.add(lblBoasVindas, java.awt.BorderLayout.PAGE_START);

        PainelImagemHamster.setBackground(new java.awt.Color(240, 245, 255));

        lblHamster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Mascote_Sudoku.png"))); // NOI18N

        javax.swing.GroupLayout PainelImagemHamsterLayout = new javax.swing.GroupLayout(PainelImagemHamster);
        PainelImagemHamster.setLayout(PainelImagemHamsterLayout);
        PainelImagemHamsterLayout.setHorizontalGroup(
            PainelImagemHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelImagemHamsterLayout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(lblHamster)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelImagemHamsterLayout.setVerticalGroup(
            PainelImagemHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelImagemHamsterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHamster)
                .addGap(35, 35, 35))
        );

        PainelConteudoPrincipal.add(PainelImagemHamster, java.awt.BorderLayout.PAGE_END);

        add(PainelConteudoPrincipal, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoJogoActionPerformed
        main.mostrarTela("dificuldade");
    }//GEN-LAST:event_btnNovoJogoActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        main.iniciarContinuar();
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        SessaoUsuario.logout();
        main.mostrarTela("login");
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRankingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRankingActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelConteudoPrincipal;
    private javax.swing.JPanel PainelImagemHamster;
    private javax.swing.JPanel PainelMenuLateral;
    private javax.swing.JButton btnCarregar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnNovoJogo;
    private javax.swing.JButton btnRanking;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel lblBoasVindas;
    private javax.swing.JLabel lblHamster;
    private javax.swing.JLabel lblLogo;
    // End of variables declaration//GEN-END:variables
}
