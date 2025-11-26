
package sudoku;


public class PartidaSalva {
    private int id;
    private String dificuldade;
    private int tempoSegundos;
    private String dataSalva;
    private int pontuacao;
    
    public PartidaSalva(int id, String dificuldade, int tempoSegundos, String dataSalva, int pontuacao) {
        this.id = id;
        this.dificuldade = dificuldade;
        this.tempoSegundos = tempoSegundos;
        this.dataSalva = dataSalva;
        this.pontuacao = pontuacao;
    }

    public int getId() {
        return id;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public int getTempoSegundos() {
        return tempoSegundos;
    }

    public String getDataSalva() {
        return dataSalva;
    }

    public int getPontuacao() {
        return pontuacao;
    }
    
    
    
}
