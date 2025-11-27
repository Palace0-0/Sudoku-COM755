/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sudoku;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author gpala
 */
public class LoginPanel extends javax.swing.JPanel {

    private Mainframe main;
    
   private void estilizar() {
        // --- CORES ---
        Color azulForte = new Color(79, 115, 156);
        Color azulClaroFundo = new Color(240, 245, 255);

        this.setBackground(azulClaroFundo);

        // --- CARD ARREDONDADO (SOLUÇÃO DEFINITIVA) ---
        if (PainelLogin != null) {
            // 1. Deixa o painel transparente para não mostrar os cantos quadrados
            PainelLogin.setOpaque(false);

            // 2. Cria uma "Borda Mágica" que desenha o fundo branco arredondado
            PainelLogin.setBorder(new AbstractBorder() {
                @Override
                public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    // Ativa o Anti-aliasing para a curva não ficar serrilhada
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Desenha o Fundo Branco Arredondado
                    g2.setColor(Color.WHITE);
                    // 40 é o tamanho da curva (Arc)
                    g2.fillRoundRect(x, y, width - 1, height - 1, 40, 40);

                    // Desenha uma borda fina cinza ao redor (opcional)
                    g2.setColor(new Color(220, 220, 220));
                    g2.drawRoundRect(x, y, width - 1, height - 1, 40, 40);

                    g2.dispose();
                }

                // Define uma margem interna para os componentes não colarem na borda
                @Override
                public java.awt.Insets getBorderInsets(java.awt.Component c) {
                    return new java.awt.Insets(20, 20, 20, 20); 
                }
            });
        }

        // --- RESTANTE DOS COMPONENTES (IGUAL ANTES) ---

        // Logo
        if (lblLogo != null) {
            lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblLogo.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 15, 0)); 
        }

        // Campos
        String margem = "margin: 5,10,5,10";
        txtUsuario.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Usuário");
        txtUsuario.putClientProperty("JComponent.roundRect", true);
        txtUsuario.putClientProperty("JTextField.showClearButton", true);
        txtUsuario.putClientProperty(FlatClientProperties.STYLE, margem);

        txtSenha.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Senha");
        txtSenha.putClientProperty("JComponent.roundRect", true);
        txtSenha.putClientProperty("JTextField.showRevealButton", true);
        txtSenha.putClientProperty(FlatClientProperties.STYLE, margem);

        // Botões
        btnEntrar.setBackground(azulForte);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Removemos estilos complexos para evitar erro
        btnEntrar.setBorderPainted(false); 

        btnCadastrar.setBackground(Color.WHITE);
        btnCadastrar.setForeground(azulForte);
        btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.setBorder(javax.swing.BorderFactory.createLineBorder(azulForte, 1));
    }     

    public LoginPanel(Mainframe main) {
        this.main = main; 
        initComponents();
        estilizar();
        
        txtSenha.setText("1234");
        txtUsuario.setText("admin");
        
        //Faz o Enter funcionar como um clique no botão Entrar
        javax.swing.SwingUtilities.invokeLater(() -> {
        // Verifica se já está anexado a uma janela para evitar erro
            if (this.getRootPane() != null) {
                this.getRootPane().setDefaultButton(btnEntrar);

                //Já dá o foco no campo de usuário automaticamente
                txtUsuario.requestFocusInWindow();
            }
        });
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelLogin = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(240, 245, 255));

        PainelLogin.setBackground(new java.awt.Color(255, 255, 255));

        btnCadastrar.setBackground(new java.awt.Color(79, 115, 156));
        btnCadastrar.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnCadastrar.setForeground(new java.awt.Color(243, 249, 255));
        btnCadastrar.setText("CADASTRAR");
        btnCadastrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnEntrar.setBackground(new java.awt.Color(79, 115, 156));
        btnEntrar.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(243, 249, 255));
        btnEntrar.setText("ENTRAR");
        btnEntrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 227, 246), 1, true));
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelLoginLayout = new javax.swing.GroupLayout(PainelLogin);
        PainelLogin.setLayout(PainelLoginLayout);
        PainelLoginLayout.setHorizontalGroup(
            PainelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEntrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSenha))
                .addContainerGap())
        );
        PainelLoginLayout.setVerticalGroup(
            PainelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLoginLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Logo_Sudoku.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(PainelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(401, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        String login = txtUsuario.getText();
        String senha = txtSenha.getText();

        // REGRA 1: Não pode ter espaço
        if (login.isEmpty() || senha.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Preencha usuário e senha para cadastrar.");
            return;
        }
        
        // REGRA 2: Espaços em Branco (Sua exigência principal)
        if (login.contains(" ") || senha.contains(" ")) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Usuário e Senha NÃO podem conter espaços.", "Formato Inválido", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // REGRA 3: Tamanho Mínimo (Segurança básica)
        if (login.length() < 3) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "O usuário deve ter pelo menos 3 caracteres.", "Muito Curto", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (senha.length() < 4) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "A senha deve ter pelo menos 4 caracteres.", "Senha Fraca", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // REGRA 4: Caracteres Especiais no Usuário (Opcional, mas recomendado)
        // "matches" usa Regex. [a-zA-Z0-9]+ significa: Só aceita letras e números.
        if (!login.matches("[a-zA-Z0-9]+")) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "O usuário deve conter apenas letras e números (sem acentos ou símbolos).", "Caractere Inválido", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO usuarios (login, senha) VALUES (?, SHA1(?))";
        
        try {
            //Recebe a coneção la do Mainframe e prepara a querry
            java.sql.Connection conn = DBConnection.getInstance().getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            //Executa a querry
            stmt.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(this, "Conta criada com sucesso! Agora clique em ENTRAR.");
            
            //Limpa os campos
            txtUsuario.setText("");
            txtSenha.setText("");
            
            //Fecha a statement
            stmt.close();
  
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            // Esse erro específico acontece se tentar cadastrar um login que já existe (Unique Key)
            javax.swing.JOptionPane.showMessageDialog(this, "Este nome de usuário já está em uso.");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        
        String User = txtUsuario.getText();
        String Senha = txtSenha.getText();
        
        if(User.isEmpty() || Senha.isEmpty()){
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor, preencha usuário e senha.");
                return;
        }
        
        String sql = "SELECT id, login FROM usuarios WHERE login = ? AND senha = SHA1(?)";
        
        try {
            //Recebe a coneção la do Mainframe e prepara a querry
            java.sql.Connection conn = DBConnection.getInstance().getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, User);
            stmt.setString(2, Senha);
            
            java.sql.ResultSet rs = stmt.executeQuery();
            
            //Verifica se o user esta cadastrado
            if(rs.next()){
                
                int idBanco = rs.getInt("id");
                String nomeBanco = rs.getString("login");

                //Cria o usuario que ira ser salvo para uso posterior na aplicação
                Usuario u = new Usuario(idBanco, nomeBanco);
                SessaoUsuario.setUsuarioLogado(u);

                //Limpa os campos e muda a tela
                txtSenha.setText("");
                txtUsuario.setText("");
                
                main.iniciarMenu();
            }else{
                javax.swing.JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            
            //Fecha as coisas temporarios relacionadas a conecção
            rs.close();
            stmt.close();
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro de conexão: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEntrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelLogin;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
