
package sudoku;

import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;


public class ContinuarPanel extends javax.swing.JPanel {
    private DefaultTableModel modeloTabela;
    private Mainframe main;

    private void carregarTabela() {
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Dificuldade", "Tempo", "Pontuação", "Data", "Segundos", "Gabarito", "JogoAtual"}, 0);
        jTable1.setModel(modeloTabela);

        Usuario user = SessaoUsuario.getUsuarioLogado();
        if (user == null) return; 

        // O SQL de busca agora inclui a coluna 'pontuacao'
        String sql = "SELECT id, dificuldade, tempo_decorrido, data_ultima_jogada, pontuacao, gabarito, jogo_atual FROM partidas " +
                     "WHERE usuario_id = ? AND status = 'EM_ANDAMENTO' " +
                     "ORDER BY data_ultima_jogada DESC";

        try{
            java.sql.Connection conn = DBConnection.getInstance().getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, user.getId());

            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Adiciona a linha na tabela (AGORA COM O VALOR DA PONTUAÇÃO)
                    modeloTabela.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("dificuldade"),
                        rs.getInt("tempo_decorrido") / 60 + ":" + rs.getInt("tempo_decorrido") % 60,
                        rs.getInt("pontuacao"), // <--- PONTUAÇÃO AQUI
                        rs.getString("data_ultima_jogada"),
                        rs.getInt("tempo_decorrido"),
                        rs.getString("gabarito"), 
                        rs.getString("jogo_atual")
                    });
                }
            }
            
            stmt.close();
            // Esconde a coluna ID (coluna 0)
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(5).setMinWidth(0);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(6).setMinWidth(0);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(7).setMinWidth(0);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar jogos: " + e.getMessage());
        }
    }
    
    private void estilizar() {
        // --- PALETA DE CORES (Baseada nas suas telas) ---
        Color fundoClaro = new Color(245, 248, 252); // Fundo geral do painel (Claro)
        Color fundoTabela = Color.WHITE; // Fundo das células da tabela
        Color bordaTabela = new Color(200, 200, 200); // Borda suave para a tabela
        Color corCabecalhoTabela = new Color(79, 115, 156); // Azul escuro (Botão primário)
        Color corTextoCabecalho = Color.WHITE;

        Color corBotaoPrimario = new Color(79, 115, 156); // Azul forte para "Jogar" e "Voltar"
        Color corBotaoPerigo = new Color(200, 80, 80); // Vermelho para "Apagar"
        Color corTextoBotao = Color.WHITE;

        // --- 1. CONFIGURAÇÃO GERAL DO PAINEL ---
        this.setBackground(fundoClaro); 

        // --- 2. ESTILO E COMPORTAMENTO DA JTABLE (jTable1) ---
        jTable1.setBackground(fundoTabela);
        jTable1.setForeground(new Color(50, 50, 50));
        jTable1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTable1.setRowHeight(30);
        jTable1.setGridColor(bordaTabela);

        // *REQUISITO* 1: Apenas permitir a seleção de linhas (não células ou colunas)
        jTable1.setRowSelectionAllowed(true); 
        jTable1.setColumnSelectionAllowed(false); // Impede seleção de coluna/célula
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION); // Apenas uma linha por vez

        // Estilo do cabeçalho da tabela
        jTable1.getTableHeader().setBackground(corCabecalhoTabela);
        jTable1.getTableHeader().setForeground(corTextoCabecalho);
        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);

        // Borda para o JScrollPane (jScrollPane1). 
        // Para ocupar a tela inteira (o CENTER de um BorderLayout), remova o padding
        jScrollPane1.setBorder(BorderFactory.createLineBorder(bordaTabela, 1));
        jScrollPane1.setBackground(fundoTabela);

        // --- 3. ESTILO DOS BOTÕES (Ajustado para o novo layout) ---

        // Estilo base para todos os botões
        JButton[] botoes = {btnApagar, btnJogar, btnVoltar};
        for (JButton btn : botoes) {
            if (btn == null) continue;
            btn.setForeground(corTextoBotao);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.putClientProperty("JComponent.roundRect", true); // Cantos arredondados
            btn.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30)); // Padding interno
        }

        // *REQUISITO* 2: Destaque em vermelho para o botão Apagar
        if (btnApagar != null) {
            btnApagar.setBackground(corBotaoPerigo); 
        }

        // *REQUISITO* 3: Jogar e Voltar com o mesmo estilo primário
        if (btnJogar != null) {
            btnJogar.setBackground(corBotaoPrimario); 
        }
        if (btnVoltar != null) {
            btnVoltar.setBackground(corBotaoPrimario); 
        }
    }
    
    public ContinuarPanel(Mainframe main) {
        this.main = main;
        initComponents();
        carregarTabela();
        estilizar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnJogar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnJogar.setBackground(new java.awt.Color(79, 115, 156));
        btnJogar.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnJogar.setForeground(new java.awt.Color(243, 249, 255));
        btnJogar.setText("Jogar");
        btnJogar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnJogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJogarActionPerformed(evt);
            }
        });

        btnApagar.setBackground(new java.awt.Color(79, 115, 156));
        btnApagar.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnApagar.setForeground(new java.awt.Color(243, 249, 255));
        btnApagar.setText("Apagar");
        btnApagar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        btnVoltar.setBackground(new java.awt.Color(79, 115, 156));
        btnVoltar.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(243, 249, 255));
        btnVoltar.setText("Voltar");
        btnVoltar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnJogar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(625, 625, 625)
                        .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnJogar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnJogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJogarActionPerformed
        int linhaSelecionada = jTable1.getSelectedRow();
        
        if(linhaSelecionada == -1){
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um jogo para apagar.");
            return;
        }
        
        int id = (int) jTable1.getValueAt(linhaSelecionada, 0);
        int pontuacao = (int) jTable1.getValueAt(linhaSelecionada, 3);
        String dificuldade = (String) jTable1.getValueAt(linhaSelecionada, 1);
        int tempo = (int) jTable1.getValueAt(linhaSelecionada, 5);
        String gabarito = (String) jTable1.getValueAt(linhaSelecionada, 6);
        String jogo_atual = (String) jTable1.getValueAt(linhaSelecionada, 7);
        
        main.continuarSudoku(id, pontuacao, dificuldade, gabarito, jogo_atual, tempo, false);
        
        
    }//GEN-LAST:event_btnJogarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        int linhaSelecionada = jTable1.getSelectedRow();
        
        if(linhaSelecionada == -1){
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um jogo para apagar.");
            return;
        }
        
        // Pega o ID da partida na coluna 0 (que está invisível)
        int idPartida = (int) modeloTabela.getValueAt(linhaSelecionada, 0); 

        String sql = "DELETE FROM partidas WHERE id = ?";
        
        try {
            java.sql.Connection conn = DBConnection.getInstance().getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, idPartida);
            stmt.executeUpdate();
            
            stmt.close();
            javax.swing.JOptionPane.showMessageDialog(this, "Jogo apagado com sucesso!");
            carregarTabela(); // Recarrega a tabela para refletir a mudança

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao apagar jogo: " + e.getMessage());
        }
        
        
        
    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        main.mostrarTela("menu");
    }//GEN-LAST:event_btnVoltarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnJogar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
