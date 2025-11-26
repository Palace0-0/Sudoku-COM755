package sudoku; // Mudei o pacote para o atual

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    // Configurações do Banco (Substituí o arquivo .ini por variáveis diretas)
    private static final String URL = "jdbc:mysql://localhost:3306/sudoku_db";
    private static final String USER = "root";
    private static final String PASS = "root123"; // Coloque sua senha do MySQL aqui se tiver

    private Connection connection;
    private Statement statement;
    
    // Mantive a estrutura Singleton
    private static DBConnection instance = null;
    
    // Construtor Privado
    private DBConnection() {
        System.out.println("Iniciando conexão com o banco...");
        abrirConexao();
    }
    
    // Padrão Singleton: Garante que só existe UMA conexão aberta no sistema todo
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
    
    public boolean abrirConexao() {
        try {
            // Carrega o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Cria a conexão usando as constantes lá de cima
            connection = DriverManager.getConnection(URL, USER, PASS);
            
            // Cria o statement padrão (o "mensageiro" de comandos SQL)
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            if (connection != null) {
                System.out.println("Conexão realizada com sucesso!");
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            // Removi o 'handler' antigo e pus um log padrão do Java
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    // Retorna a Conexão pura (Útil para criar PreparedStatements, que são mais seguros)
    public Connection getConnection() {
        return connection;
    }

    // Mantive seu getter do Statement antigo
    public Statement getStatement() {
        return statement;
    }
    
    // Método para fechar
    public void fecharConexao() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}