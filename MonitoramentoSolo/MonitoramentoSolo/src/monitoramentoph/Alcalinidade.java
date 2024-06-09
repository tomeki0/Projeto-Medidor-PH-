package monitoramentoph;

//Classe filha
public class Alcalinidade extends Solo {

	//Construtor da classe
    public Alcalinidade(double ph) {
        super(ph);
    }

    @Override
    public String classificar() {
    	
        double ph = getPh();
        
        if (ph >= 8.0 && ph <= 8.9) {
            return "Alcalinidade Média";
        } 
        else if (ph >= 9.0) {
            return "Alcalinidade Forte";
        } 
        else {
            return "Valor de pH fora da faixa esperada para alcalinidade.";
        }
    }
}
