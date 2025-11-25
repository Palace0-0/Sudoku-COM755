
package sudoku;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Mainframe extends javax.swing.JFrame {

    
    private CardLayout cardLayout;
    private JPanel container;

    public Mainframe() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        // Cria o CardLayout e o painel container
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // Cria os painéis
        MenuPanel menuPanel = new MenuPanel(this);
        SudokuPanel sudokuPanel = new SudokuPanel();

        // Adiciona “cartas” ao container
        container.add(menuPanel, "menu");
        container.add(sudokuPanel, "jogo");

        add(container);
        
        //mostrarTela("menu");
        setVisible(true);
    }

    // Método para trocar de tela
    public void mostrarTela(String nome) {
        cardLayout.show(container, nome);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sudoku");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mainframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
