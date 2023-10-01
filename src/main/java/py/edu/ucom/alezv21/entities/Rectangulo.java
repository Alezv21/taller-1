package py.edu.ucom.alezv21.entities;

public class Rectangulo extends FiguraGeometrica {
    public double largo;
    public double ancho;
    
    public Rectangulo(double largo, double ancho){
        this.ancho = ancho;
        this.largo = largo;
    }
    @Override
    public double calcularArea(){
        return 2*(largo * ancho);
    }
    @Override
    public double calcularPerimetro(){
        return 2*(largo + ancho);
    }
}
