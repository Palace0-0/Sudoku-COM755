
package sudoku;

import com.sun.jdi.connect.spi.Connection;
import javax.swing.table.DefaultTableModel;


public class ContinuarPanel extends javax.swing.JPanel {
    private DefaultTableModel modeloTabela;
    private Mainframe main;

    public void carregarTabela() {
        // 1. Prepara a JTable (Adicionando a coluna "Pontuação")
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Dificuldade", "Tempo", "Pontuação", "Data"}, 0);
        jTable1.setModel(modeloTabela);

        Usuario user = SessaoUsuario.getUsuarioLogado();
        if (user == null) return; 

        // O SQL de busca agora inclui a coluna 'pontuacao'
        String sql = "SELECT id, dificuldade, tempo_decorrido, data_ultima_jogada, pontuacao FROM partidas " +
                     "WHERE usuario_id = ? AND status = 'EM_ANDAMENTO' " +
                     "ORDER BY data_ultima_jogada DESC";

        try (java.sql.Connection conn = DBConnection.getInstance().getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getId());

            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("A");
                    // Adiciona a linha na tabela (AGORA COM O VALOR DA PONTUAÇÃO)
                    modeloTabela.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("dificuldade"),
                        rs.getInt("tempo_decorrido") / 60 + ":" + rs.getInt("tempo_decorrido") % 60,
                        rs.getInt("pontuacao"), // <--- PONTUAÇÃO AQUI
                        rs.getString("data_ultima_jogada")
                    });
                }
            }

            // Esconde a coluna ID (coluna 0)
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar jogos: " + e.getMessage());
        }
    }
    public ContinuarPanel(Mainframe main) {
        this.main = main;
        initComponents();
        
        carregarTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnRanking = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        btnRanking2 = new javax.swing.JButton();

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
        ));
        jScrollPane1.setViewportView(jTable1);

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

        btnApagar.setBackground(new java.awt.Color(79, 115, 156));
        btnApagar.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnApagar.setForeground(new java.awt.Color(243, 249, 255));
        btnApagar.setText("Ranking");
        btnApagar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        btnRanking2.setBackground(new java.awt.Color(79, 115, 156));
        btnRanking2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnRanking2.setForeground(new java.awt.Color(243, 249, 255));
        btnRanking2.setText("Ranking");
        btnRanking2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnRanking2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRanking2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(btnRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRanking2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(229, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(704, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRanking2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(449, Short.MAX_VALUE)
                    .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(49, 49, 49)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRankingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRankingActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnRanking2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRanking2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRanking2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnRanking;
    private javax.swing.JButton btnRanking2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
