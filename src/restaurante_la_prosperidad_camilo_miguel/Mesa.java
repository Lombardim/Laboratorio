package restaurante_la_prosperidad_camilo_miguel;

public class Mesa {
    public int mesan;
    public boolean disponible;
    Mesa link;
    
    public Mesa(int mesan, boolean disponible) {
        
        this.mesan = mesan;
        this.disponible = disponible;
        link = null;
        
    }
    
}     