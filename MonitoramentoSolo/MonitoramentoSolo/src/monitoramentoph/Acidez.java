package monitoramentoph;
public class Acidez extends Solo {

    public Acidez(double ph) {
        super(ph);
    }

    @Override
    public String classificar() {
    	
        double ph = getPh();
               
        if (ph >= 6.0 && ph <= 6.9) {
            return "Acidez Moderada";
        } 
        else if (ph >= 5.5 && ph <= 5.9) {
            return "Acidez Média";
        } 
        else if (ph >= 5.0 && ph <= 5.4) {
            return "Acidez Forte";
        } 
        else if (ph >= 0 && ph <= 4.9) {
            return "Acidez Muito Forte";
        } 
        else {
            return "Valor de pH fora da faixa esperada para acidez.";
        }
    }
}

