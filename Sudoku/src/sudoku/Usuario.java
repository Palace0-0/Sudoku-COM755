
package sudoku;


public class Usuario {
    private int id;
    private String login;

   
    public Usuario(int id, String login) {
        this.id = id;
        this.login = login;
    }

    
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
