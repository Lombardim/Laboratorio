package restaurante_la_prosperidad_camilo_miguel;

import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.table.DefaultTableModel;

public class Cocina extends javax.swing.JFrame {

    /**
     * Creates new form Cocina
     */
    String nombreM = "", horaP = "", TP = "", nombreC;
    int cont, MesaN, tp, cedulaC;
    String plato1="", plato2="", plato3="", plato4="", plato5="", bebida1="", bebida2="", bebida3="", postre1="", postre2="", postre3="", postre4="",nombrePlat;
    int cantpl1, cantpl2, cantpl3,cantpl4, cantpl5, cantb1, cantb2, cantb3, cantpo1, cantpo2, cantpo3, cantpo4, cantpo5, totalPlat, terminadosPlat, sw=0, platosInc =0;
    float preciopl1, preciopl2, preciopl3, preciopl4, preciopl5, preciob1, preciob2, preciob3, preciopo1, preciopo2, preciopo3, preciopo4;
    String url = "src\\archivos";
    
    public Cocina() {
        initComponents();
    }
    
    File archivo,  plato;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Comanda = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Ped = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        pedidoMesa = new javax.swing.JLabel();
        meseroCargo = new javax.swing.JLabel();
        totalPagar = new javax.swing.JLabel();
        horaPedido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("COCINA");
        setMinimumSize(new java.awt.Dimension(280, 385));
        setPreferredSize(new java.awt.Dimension(280, 385));
        setResizable(false);
        setSize(new java.awt.Dimension(280, 385));

        Ped.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pedido", "Cant.", "Precio Un."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Ped);
        if (Ped.getColumnModel().getColumnCount() > 0) {
            Ped.getColumnModel().getColumn(0).setResizable(false);
            Ped.getColumnModel().getColumn(1).setResizable(false);
            Ped.getColumnModel().getColumn(1).setPreferredWidth(10);
            Ped.getColumnModel().getColumn(2).setResizable(false);
            Ped.getColumnModel().getColumn(2).setPreferredWidth(15);
        }

        jButton1.setText("COCINAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ComandaLayout = new javax.swing.GroupLayout(Comanda);
        Comanda.setLayout(ComandaLayout);
        ComandaLayout.setHorizontalGroup(
            ComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComandaLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        ComandaLayout.setVerticalGroup(
            ComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComandaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pedidoMesa.setText("Pedido de la mesa #: ");

        meseroCargo.setText("Mesero a cargo: ");

        totalPagar.setText("Total a pagar: ");

        horaPedido.setText("Hora en que se realizó el pedido: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Comanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pedidoMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(meseroCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(horaPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(horaPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pedidoMesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meseroCargo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalPagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Comanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        Comanda.getAccessibleContext().setAccessibleName("Mesa #1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void generarMatriz(){
        int conta, pos=0;
        conta= this.cont;
        float totalAPagar = 0;
        DefaultTableModel model = (DefaultTableModel) Ped.getModel();
        model.setRowCount(conta);
        modificarArchivo("",2);
        modificarArchivo(MesaN);
        if (this.plato1.equals("Kati Roll")) {
            Ped.setValueAt(this.plato1, pos, 0);
            Ped.setValueAt(this.cantpl1, pos, 1);
            Ped.setValueAt(this.preciopl1, pos, 2);
            modificarArchivo(this.plato1 , this.cantpl1, this.preciopl1);
            pos=pos+1;
        }
        if (this.plato2.equals("Papada de Cerdo")) {
            Ped.setValueAt(this.plato2, pos, 0);
            Ped.setValueAt(this.cantpl2, pos, 1);
            Ped.setValueAt(this.preciopl2, pos, 2);
            modificarArchivo(this.plato2 , this.cantpl2, this.preciopl2);
            pos=pos+1;
        }
        if (this.plato3.equals("Cabracho con Alcachofas")) {
            Ped.setValueAt(this.plato3, pos, 0);
            Ped.setValueAt(this.cantpl3, pos, 1);
            Ped.setValueAt(this.preciopl3, pos, 2);
            modificarArchivo(this.plato3 , this.cantpl3, this.preciopl3);
            pos=pos+1;
        }
        if (this.plato4.equals("Solomillo de Cerdo Hojaldrado")) {
            Ped.setValueAt(this.plato4, pos, 0);
            Ped.setValueAt(this.cantpl4, pos, 1);
            Ped.setValueAt(this.preciopl4, pos, 2);
            modificarArchivo(this.plato4 , this.cantpl4, this.preciopl4);
            pos=pos+1;
        }
        if (this.plato5.equals("Spaghetti al Ragú")) {
            Ped.setValueAt(this.plato5, pos, 0);
            Ped.setValueAt(this.cantpl5, pos, 1);
            Ped.setValueAt(this.preciopl5, pos, 2);
            modificarArchivo(this.plato5 , this.cantpl5, this.preciopl5);
            pos=pos+1;
        }
        if (this.bebida1.equals("Coca Cola")) {
            Ped.setValueAt(this.bebida1, pos, 0);
            Ped.setValueAt(this.cantb1, pos, 1);
            Ped.setValueAt(this.preciob1, pos, 2);
            modificarArchivo(this.bebida1 , this.cantb1, this.preciob1);
            pos=pos+1;
        }
        if (this.bebida2.equals("Gaseosa")) {
            Ped.setValueAt(this.bebida2, pos, 0);
            Ped.setValueAt(this.cantb2, pos, 1);
            Ped.setValueAt(this.preciob2, pos, 2);
            modificarArchivo(this.bebida2 , this.cantb2, this.preciob2);
            pos=pos+1;
        }
        if (this.bebida3.equals("Jugos Naturales")) {
            Ped.setValueAt(this.bebida3, pos, 0);
            Ped.setValueAt(this.cantb3, pos, 1);
            Ped.setValueAt(this.preciob3, pos, 2);
            modificarArchivo(this.bebida3 , this.cantb3, this.preciob3);
            pos=pos+1;
        }
        if (this.postre1.equals("Manzana Templada")) {
            Ped.setValueAt(this.postre1, pos, 0);
            Ped.setValueAt(this.cantpo1, pos, 1);
            Ped.setValueAt(this.preciopo1, pos, 2);
            modificarArchivo(this.postre1 , this.cantpo1, this.preciopo1);
            pos=pos+1;
        }
        if (this.postre2.equals("Tiramisú Crujiente")) {
            Ped.setValueAt(this.postre2, pos, 0);
            Ped.setValueAt(this.cantpo2, pos, 1);
            Ped.setValueAt(this.preciopo2, pos, 2);
            modificarArchivo(this.postre2 , this.cantpo2, this.preciopo2);
            pos=pos+1;
        }
        if (this.postre3.equals("Exótico")) {
            Ped.setValueAt(this.postre3, pos, 0);
            Ped.setValueAt(this.cantpo3, pos, 1);
            Ped.setValueAt(this.preciopo3, pos, 2);
            modificarArchivo(this.postre3 , this.cantpo3, this.preciopo3);
            pos=pos+1;
        }
        if (this.postre4.equals("Minitarta Selva Negra")) {
            Ped.setValueAt(this.postre4, pos, 0);
            Ped.setValueAt(this.cantpo4, pos, 1);
            Ped.setValueAt(this.preciopo4, pos, 2);
            modificarArchivo(this.postre4 , this.cantpo4, this.preciopo4);
            pos = pos + 1;
        }
        Ped.setModel(model);
        modificarArchivo("", 1);
        modificarArchivo(horaP, 0);        
    }
    
    void actualizarGM(){
        String ttpp = "" + tp;
        if (ttpp.length() < 4) {
            TP = TP + ttpp;
        }else{
            if(ttpp.length() == 4){//1.000
                TP = TP + ttpp.charAt(0) + "." + ttpp.substring(1, ttpp.length());
            }else{
                if (ttpp.length() == 5) {//10.000
                    TP = TP + ttpp.substring(0, 2) + "." + ttpp.substring(2, ttpp.length());
                }else{
                    if (ttpp.length() == 6) {//100.000
                        TP = TP + ttpp.charAt(0) + ttpp.charAt(1) + ttpp.charAt(2) + "." + ttpp.substring(3, ttpp.length());
                    }else{
                        if (ttpp.length() == 7) {//1'000.000
                            TP = TP + ttpp.charAt(0) + "'" + ttpp.substring(1, 4) + "." + ttpp.substring(4, ttpp.length());
                        }
                    }
                }
            }
        }
        Registro_Mesero.horaPed = horaP;
        horaPedido.setText("Hora en que se realizó el pedido: " + horaP);
        pedidoMesa.setText("Pedido de la mesa #: " + MesaN + ".");
        meseroCargo.setText("Mesero a cargo: " + nombreM + ".");
        totalPagar.setText("Total a pagar sin IVA: " + TP + "$.");
        archivo = new File(url + "\\Pedido" + MesaN + ".txt");
    }
    
    TiempoCoccion tc;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String producto;
        int cantidad;
        int p;
        p = Ped.getRowCount();
        for (int i = 0; i < p; i++) {
            producto = Ped.getValueAt(i, 0).toString();
            cantidad = (int) Ped.getValueAt(i, 1);
            Aprovisionamiento(producto, cantidad); 
        }
        tc = new TiempoCoccion();
        tc.NomMesero = nombreM;
        tc.NumMesa = MesaN;
        tc.archivo = archivo;
        tc.m = 5 + ((int)(Math.random() * 10));
        tc.s = ((int)(Math.random() * 59));
        tc.tmc = tc.m - 5;
        tc.ma = (int)(Math.random() * (tc.m - 4));
        System.out.println(tc.ma + ":" + tc.sa);
        tc.actualizarCronometro();
        JOptionPane.showMessageDialog(null, "El pedido tardará aproximadamente " + tc.m + ":" + tc.s + " en la cocina.", "TIEMPO DE COCCIÓN", INFORMATION_MESSAGE);
        System.out.println("Tiempo estimado de preparación = " + tc.m + ":" + tc.s);
        tc.setLocationRelativeTo(null);
        tc.setVisible(true);
        this.dispose();
        tc.crono.start();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void modificarArchivo(String horaPe, int c){
        try(FileWriter fw = new FileWriter(archivo, true)){
            Object hP = horaPe;
            if (c == 0) {
                fw.write("Hora a la que se realizó el pedido: " + hP + ".\r\n");
                fw.write("Cocinando...\r\n");
            }else{
                if(c == 1){
                    fw.write("Fin del pedido.\r\n");
                }else{
                    fw.write("Nombre del cliente: " + nombreC + ".\r\n");
                    fw.write("Cédula del cliente: " + cedulaC + ".\r\n");
                }
            }
            
        }catch(NullPointerException ex){
            
        }catch(IOException e){
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void modificarArchivo(int mesa){
        try(FileWriter fw = new FileWriter(archivo, true)){
            fw.write("Pedido:" + "\r\n");
        }catch(NullPointerException ex){
            
        }catch(IOException e){
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void modificarArchivo(String plato, int cantidad, float precio){
        
        try(FileWriter bw = new FileWriter(archivo, true)){
            Object PL = plato;
            Object CA = cantidad;
            Object PR = precio;
            bw.write( PL + ", " + CA + ", " + PR + "\r" + "\n" );
        }catch(NullPointerException e){
        
        } catch (IOException ex) {
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarLinea(File arc, String linea){
        try(FileWriter bw = new FileWriter(arc, true)){
            Object PL = linea;
            bw.write( PL + "\r" + "\n" );
        }catch(NullPointerException e){
        
        } catch (IOException ex) {
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarLinea(String plato, int cantidad, int terminados, File arc){
        try(FileWriter bw = new FileWriter(arc, true)){
            Object PL = plato;
            Object CA = cantidad;
            Object PR = terminados;
            bw.write( PL + ", " + CA + ", " + PR + "\r" + "\n" );
        }catch(NullPointerException e){
        
        } catch (IOException ex) {
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String Plato(String linea){
        int tok = 1;
        String platoo = null;
        StringTokenizer mesa= new StringTokenizer(linea, ",");
        while(mesa.hasMoreTokens()){
            if (tok == 1) {
                platoo = mesa.nextToken();
                platoo = platoo.substring(0, platoo.length());
            }
            mesa.nextToken();
            tok++;
        }
        return platoo;
    }
    
    public int verificarArchivo(){
        String files;
        File folder = new File(url);
        File[] listOfFiles = folder.listFiles(); 
        for (int i = 0; i < listOfFiles.length; i++){ 
            if (listOfFiles[i].isFile()){ 
                files = listOfFiles[i].getName();
                if (files.endsWith(".txt") || files.endsWith(".TXT")){
                    if (files.equals("platos.txt")) {
                        sw = 0;
                    }else{
                        if (files.equals("plato0.txt")) {
                            sw = 1;
                        }
                    }
                }
            }    
        }
        return sw;
    }
    
    public void consumoAlimentos(String NomPlato, int cantidad){
        String arc = null;
        sw = verificarArchivo();
        if (sw == 0) {
            arc = url + "\\platos.txt";
            plato = new File(url + "\\plato" + sw + ".txt");
        }else{
            arc = url + "\\plato0.txt";
            plato = new File(url + "\\platos.txt");
        }
        try(FileReader archivop = new FileReader(arc)){
            try(BufferedReader br = new BufferedReader(archivop)) {
                String linea;
                String cantT, platosT, nombre;
                int cant, contt = 1, platos;
                while((linea = br.readLine()) != null){
                    nombre = Plato(linea);
                    if (nombre.equals(NomPlato)) {
                        StringTokenizer nc = new StringTokenizer(linea, ",");
                        while(nc.hasMoreTokens()){
                            if (contt ==1) {
                               nombre = nc.nextToken();
                               nombre = nombre.substring(0, nombre.length());
                               this.nombrePlat = nombre;
                            }else{
                                if (contt == 2) {
                                cantT = nc.nextToken();
                                cantT = cantT.substring(1, cantT.length());
                                cant = Integer.parseInt(cantT);
                                this.totalPlat = cant - cantidad;
                                    if (this.totalPlat<=0) {
                                        JOptionPane.showMessageDialog(null, "No se pueden realizar mas porciones de este producto" , this.nombrePlat , JOptionPane.INFORMATION_MESSAGE);
                                        this.platosInc = this.platosInc + ((cant - cantidad) * -1);
                                        Registro_Mesero.platosNT = this.platosInc;
                                    }
                                }else{
                                    platosT = nc.nextToken();
                                    platosT = platosT.substring(1, platosT.length());
                                    platos = Integer.parseInt(platosT);
                                    this.terminadosPlat = platos + cantidad;
                                }
                            }
                            contt++;
                        }
                        modificarLinea(this.nombrePlat, this.totalPlat, this.terminadosPlat, plato);
                    }else{
                        modificarLinea(plato, linea);

                    }
                    
                }
            }
        }catch(FileNotFoundException e){
        } catch (IOException ex) {
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
        File f = new File(arc); 
        f.delete();
    }
    
    public void Aprovisionamiento (String producto, int cant){
        if (producto.equals("Kati Roll")) {
            JOptionPane.showMessageDialog(null, "2 Cebollas.\n5 Chiles verdes.\n1 Cucharada de sal.\n6 Dientes de ajo.\n1 Pechuga de pollo cortada en tacos.\n"
            +"2 Cucharadas de aceite vegetal.\n125 gr de harina de trigo.\n100 gr de mantequilla.\n5 Huevos batidos." , "Kati Roll", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Kati Roll", cant);
        }
        if (producto.equals("Papada de Cerdo")) {
            JOptionPane.showMessageDialog(null, "500 gr papada de cerdo.\n2 Cucharadas de sal.\n1 Dientes de ajo.\n1 Cebolla.\n3 Tomates.\n"
            +"2 Cucharadas de aceite vegetal.\n1 Cucharada de pimienta.\n5 Cucharadas de vino tinto seco." , "Papada de cerdo", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Papada de Cerdo", cant);
        }
        if (producto.equals("Cabracho con Alcachofas")) {
            JOptionPane.showMessageDialog(null, "1000 gr de cabracho enfiletes.\n2 Dientes de ajo.\n1 Cucharada de sal.\n5 Cucharadas de aceite vegetal.\n"
            +"8 Alcachofas pequeñas.\n1 Cucharada de pimienta." , "Cabracho con alcachofas", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Cabracho con Alcachofas", cant );
        }
        if (producto.equals("Solomillo de Cerdo Hojaldrado")) {
            JOptionPane.showMessageDialog(null, "1 Solomillo grande.\n1 Masa de hojaldre refrigerada.\n3 Huevos.\n4 Tomates.\n"
            +"1 Cucharadas de sal.\n1 Cucharada de pimienta.\nJamon.\n5 Rebanadas de queso." , "Solomillo de cerdo hojaldrado", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Solomillo de Cerdo Hojaldrado", cant);
        }
        if (producto.equals("Spaghetti al Ragú")) {
            JOptionPane.showMessageDialog(null, "400 gr de Spaghetti.\n75 gr de mantequilla.\n5 Cucharadas de aceite vegetal.\n1 Tomate.\n"
            +"2 Cebollas.\n3 Rebanadas de queso y rayarlos.\n1 Cucharada de pimienta.\n50 gr de harina de trigo." , "Spaghetti al Ragú", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Spaghetti al Ragu", cant);
        }
        if (producto.equals("Coca Cola")) {
            JOptionPane.showMessageDialog(null, "Coca Cola personal." , "Bebida", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Coca Cola", cant);
        }
        if (producto.equals("Gaseosa")) {
            JOptionPane.showMessageDialog(null, "Gaseosa personal." , "Bebida", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Gaseosa", cant);
        }
        if (producto.equals("Jugos Naturales")) {
            JOptionPane.showMessageDialog(null, "1000 gr de fruta.\nAgua.\nAzucar al gusto.\nHielo." , "Jugo Natural", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Jugos Naturales", cant);
        }
        if (producto.equals("Manzana Templada")) {
            JOptionPane.showMessageDialog(null, "4 Manzanas.\n100 gr de mantequilla.\n2 Huevos.\n150 gr de harina de trigo.\n"
            +"5 Cucharas de azucar." , "Manzana Templada", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Manzana Templada", cant);
        }
        if (producto.equals("Tiramisú Crujiente")) {
            JOptionPane.showMessageDialog(null, "80 gr de chocolate.\n100 gr de mantequilla.\n5 Huevos.\n"
            +"6 Cucharas de azucar.\n60 gr de harina. " , "Tiramisú Crujiente", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Tiramisu Crujiente", cant);
        }
        if (producto.equals("Exótico")) {
            JOptionPane.showMessageDialog(null, "100 gr de mantequilla.\n5 Huevos.\n"
            +"7 Cucharas de azucar.\n100 gr de harina. " , "Exótico", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Exotico", cant);
        }
        if (producto.equals("Minitarta Selva Negra")) {
            JOptionPane.showMessageDialog(null, "30 gr de mantequilla.\n5 Huevos.\n"
            +"7 Cucharas de azucar.\n70 gr de harina.\n3 Cerezas.\nChocolate rayado. " , "Minitarta Selva Negra", JOptionPane.INFORMATION_MESSAGE);
            consumoAlimentos("Minitarta Selva Negra", cant);
        }
        
    }
    
 
    
    
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cocina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cocina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cocina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cocina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cocina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Comanda;
    public javax.swing.JTable Ped;
    private javax.swing.JLabel horaPedido;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel meseroCargo;
    private javax.swing.JLabel pedidoMesa;
    private javax.swing.JLabel totalPagar;
    // End of variables declaration//GEN-END:variables
}
