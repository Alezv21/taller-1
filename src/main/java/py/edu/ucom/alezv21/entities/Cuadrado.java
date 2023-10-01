package py.edu.ucom.alezv21.entities;

public class Cuadrado extends FiguraGeometrica {

    public double lado;

    public Cuadrado(double lado){
        this.lado = lado;
    }
    @Override
    public double calcularPerimetro(){
        return lado * 4;
    }
    @Override
    public double calcularArea(){
        return lado*lado;
    }
    
}
