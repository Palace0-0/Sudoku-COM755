
package sudoku;


public class SessaoUsuario {
    private static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        SessaoUsuario.usuarioLogado = usuarioLogado;
    }

    public static void logout(){
        usuarioLogado = null;
    }

}
