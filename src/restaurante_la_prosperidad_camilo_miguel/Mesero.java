package restaurante_la_prosperidad_camilo_miguel;

public class Mesero {

    int ced;
    String nombre;
    Mesero linkMesero;
    Mesa ptrMesa = null, pMesa, qMesa;

    public Mesero(int ced, String nombre, int cont){
        this.ced = ced;
        this.nombre = nombre;
        linkMesero = null;
        for (int i = 0; i < 6; i++) {
            if(ptrMesa == null){

                ptrMesa = new Mesa(cont, true);

            }else{
                pMesa = ptrMesa;
                qMesa = new Mesa(cont, true);
                while(pMesa.link != null){
                    pMesa = pMesa.link;
                }
                pMesa.link = qMesa;
            }
            cont++;
        }
    }
    
}