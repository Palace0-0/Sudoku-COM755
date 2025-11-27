
package sudoku;

import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;


public class RankingPanel extends javax.swing.JPanel {
    private DefaultTableModel modeloTabela;
    private Mainframe main;

    public void carregarTabela() {
        modeloTabela = new DefaultTableModel(new Object[]{"Usuário", "Dificuldade", "Tempo", "Pontuação"}, 0);
        jTable1.setModel(modeloTabela);

        Usuario user = SessaoUsuario.getUsuarioLogado();
        if (user == null) return; 


        // CORREÇÃO: Removemos a linha "GROUP BY u.id"
        String sql = "SELECT u.login, p.dificuldade, p.tempo_decorrido, p.pontuacao " +
                     "FROM partidas p " +
                     "INNER JOIN usuarios u ON p.usuario_id = u.id " +
                     "WHERE p.pontuacao = ( " +
                     "    SELECT MAX(p2.pontuacao) " +
                     "    FROM partidas p2 " +
                     "    WHERE p2.usuario_id = p.usuario_id " +
                     ") " +
                     "ORDER BY p.pontuacao DESC";
        try {
            java.sql.Connection conn = DBConnection.getInstance().getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);

            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int segundosTotal = rs.getInt("tempo_decorrido");
                    String tempoFormatado = String.format("%02d:%02d", segundosTotal / 60, segundosTotal % 60);

                    modeloTabela.addRow(new Object[]{
                        rs.getString("login"),
                        rs.getString("dificuldade"),
                        tempoFormatado,
                        rs.getInt("pontuacao")
                    });
                }
            }
            stmt.close();

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar ranking: " + e.getMessage());
            e.printStackTrace();
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
        JButton[] botoes = { btnVoltar};
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

        
        if (btnVoltar != null) {
            btnVoltar.setBackground(corBotaoPrimario); 
        }
    }
    
    public RankingPanel(Mainframe main) {
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        main.mostrarTela("menu");
    }//GEN-LAST:event_btnVoltarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
