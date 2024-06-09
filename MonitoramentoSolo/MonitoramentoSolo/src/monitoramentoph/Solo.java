package monitoramentoph;

//CLASSE MAE

public class Solo {
	
    private double ph; //variavel que recebe o ph
    
    //Construtor da classe Solo
    public Solo(double ph) {
        this.ph = ph;
    }

    //Metodos acessores e modificadores
    public double getPh() {
        return ph;
    }
    public void setPh(double ph) {
        this.ph = ph;
    }
    
    //Metodo Base
    public String classificar() {
        return "Classificação não definida.";
    }
}

