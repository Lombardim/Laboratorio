package restaurante_la_prosperidad_camilo_miguel;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import javax.swing.table.DefaultTableModel;


public class Registro_Mesero extends javax.swing.JFrame {    
    public static String horaPed = "";
    public static int platosNT = 0;
    boolean sw = true;
    Mesero ptrMesero, pMesero, qMesero;
    int mc = 0,cedulaCli, cont = 1, nape = 1, ncc = 1, cedulaMfact,mesaSelec, Exist;
    Double Iva, total, propina, totalMesa, totalDia, totalMesero1, totalMesero2, totalMesero3, totalMesero4;
    Mesa pMesa;
    File archivoN, mesero;
    String path = "src\\archivos";
    String URLarchivo = "src\\archivos\\Meseros.txt", archivo, nombreCli ="", cedulaClit, mesaOc;
    
    //<editor-fold defaultstate="collapsed" desc="Manejo de archivos">
    /**
     * Subrutina para modificar el archivo {Meseros} creado, con dos parámetros que son los que utiliza para escribir en el archivo.
     * @param nombre Recibe una variable de tipo String para escribir el nombre del mesero a generar.
     * @param cedula Variable de tipo entero que tiene el número con el que se identifica al mesero creado.
     * @since Segunda Entrega.
     */
    public void modificarArchivo(String nombre, int cedula){
        Object Cedula = cedula;
        Object Nombre = nombre;
        try(FileWriter bw = new FileWriter(archivoN, true)){
            if(nombre.equals("Nombre_Apellido, Número de cédula.")){
                bw.write("Nombre_Apellido, Número de cédula." + "\r\n");
            }else{
                bw.write("Mesero #"+ (mc+1) + "\r\n" + Nombre + ", " + Cedula + "\r\n" + "A cargo de las mesas: " + "\r\n");
                for (int i = 0; i < 5; i++) {
                    bw.write("\tMesa #" + (cont+i) + "\r\n");
                }
            }
        }catch(NullPointerException ex){
        
        } catch (IOException ex) {
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Subrutina para comprobar si el archivo existe o no, convierte la variable {sw} en falso si existe
     * y si no existe crea un nuevo archivo y en su primera linea escribe "Nombre_Apellido, Número de cédula".
     * @since Segunda Entrega.
     */  
    public void abrirArchivo(){
        try(FileReader archivop = new FileReader(URLarchivo)){ 
            sw = false;
        }catch(FileNotFoundException e){
            archivoN = new File(URLarchivo);
            modificarArchivo("Nombre_Apellido, Número de cédula.", 1);
            } catch (IOException ex) {
                Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Subrutina que genera a los meseros automáticamente">
    /**
     * Esta subrutina sirve para generar a los meseros automáticamente, abriendo el archivo que se encuentra en la URL
     * {URLarchivo}, si existe y lo recorre para así sacar los nombres de los 4 meseros ya creados y generarlos
     * llamando la subrutina {Generar(String nombre, int cedula)}.
     * @since Segunda Entrega.
     */
    public void generarMeserosA(){
        
        try(FileReader archivop = new FileReader(URLarchivo)){
            try(BufferedReader br = new BufferedReader(archivop)) {
                String linea;
                boolean swg;
                while((linea = br.readLine()) != null){
                    swg = true;

                    if(linea.equals("Nombre_Apellido, Número de cédula.") || linea.equals("A cargo de las mesas: ")){
                        
                        swg = false;
                    }
                    if(swg == true){
                        for (int i = 1; i < 5; i++) {
                            if (linea.equals("Mesero #" + i)) {
                                swg = false;
                                break;
                            }
                        }
                    }
                    if(swg == true){
                        for (int i = 1; i < 21; i++) {
                            if(linea.equals("\tMesa #"+ i)){
                                swg = false;
                                break;
                            }
                        }
                    }
                    if(swg == true){
                        int contTok = 1;
                        StringTokenizer nc = new StringTokenizer(linea, ",");
                        String nombre = "", cedulaT, cedulaS;
                        int cedula = 0;
                        while(nc.hasMoreTokens()){
                            if (contTok == 1) {
                                nombre = nc.nextToken();
                            }else{
                                cedulaT = nc.nextToken();
                                cedulaS = cedulaT.substring(1, cedulaT.length());
                                cedula = Integer.parseInt(cedulaS);
                            }
                            contTok++;
                        }
                        Generar(nombre, cedula);
                        if(mc == 4){
                            listaMeseros();
                            listaMesas();
                        }
                    }

                }
            }
        }catch(FileNotFoundException e){
            
            } catch (IOException ex) {
                Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * El constructor de la clase {Registro_Meseros} se encarga de generar todos los componentes que se encuentran en dicha clase
     * además de comprobar si existe el archivo que contendrá a los meseros. Si al comprobar retorna {sw = false} entonces genera automáticamente
     * a los meseros y muestra la recepción, si no, entonces mostrará la ventana en donde se crearan los 4 meseros y generara un archivo llamado
     * {Meseros.txt} que será utilizado para la próxima vez que se corra el algoritmo.
     */
    public Registro_Mesero() {
        abrirArchivo();
        ptrMesero = null;
        initComponents();
        if (sw == false) {
            generarMeserosA();
            Recepcion.setVisible(true);
            Recepcion.setLocationRelativeTo(null);
        }else{
            this.setVisible(true);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Genera la lista con los meseros y de mesas">
    /**
     * Subrutina encargada de actualizar la jList en la ventana {Recepcion} la cual muestra a los
     * meseros que estan registrados en el sistema del restaurante.
     * Es posible dar click sobre uno de los meseros y presionar el botón {COMPROBAR} para saber
     * que mesas tiene a su cargo y comprobar su estado: {Ocupada} o {Disponible}
     */
    public void listaMeseros(){
        DefaultListModel model = new DefaultListModel();
        pMesero = ptrMesero;
        
        while(pMesero != null){
            model.addElement(pMesero.nombre);
            pMesero = pMesero.linkMesero;
        }
        
        listaMeseros.setModel(model);
        
    }
    /**
     * Subrutina encargada de actualizar la jList en la ventana {Recepcion] la cual muestra todas las mesas
     * que se encuentran disponibles en el momento, si alguna mesa es reservada desaparecerá de la lista.
     */
    public void listaMesas(){
        
        DefaultListModel model = new DefaultListModel();
        pMesero = ptrMesero;
        
        while(pMesero != null){
            
            pMesa = pMesero.ptrMesa;
            
            while(pMesa.link != null){
                if(pMesa.disponible == true){
                    model.addElement("Mesa" + ", " + pMesa.mesan);
                }
                pMesa = pMesa.link;
            }
            
            pMesero = pMesero.linkMesero;
            
        }
        
        listaMesas.setModel(model);
        
    }
    //</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Recepcion = new javax.swing.JFrame();
        titulo = new javax.swing.JLabel();
        NOMBRE = new javax.swing.JLabel();
        APELLIDO = new javax.swing.JLabel();
        CEDULA = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaMesas = new javax.swing.JList<>();
        RESERVAR = new javax.swing.JButton();
        hint = new javax.swing.JLabel();
        listamesas = new javax.swing.JLabel();
        AYUDA = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaMeseros = new javax.swing.JList<>();
        listameseros = new javax.swing.JLabel();
        comprobar = new javax.swing.JButton();
        NOMBRET = new javax.swing.JTextField();
        APELLIDOT = new javax.swing.JTextField();
        CEDULAT = new javax.swing.JTextField();
        Facturacion = new javax.swing.JFrame();
        nombreFacturacion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaMesasFacturacion = new javax.swing.JList<>();
        generarFactura = new javax.swing.JButton();
        Gerente = new javax.swing.JFrame();
        BIENVENIDO = new javax.swing.JLabel();
        MENSAJEV = new javax.swing.JLabel();
        RVM = new javax.swing.JRadioButton();
        TVD = new javax.swing.JRadioButton();
        PMV = new javax.swing.JRadioButton();
        RESUMENVENTAS = new javax.swing.JTextField();
        VCFR = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        MMV = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        Factura = new javax.swing.JFrame();
        facturaPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        platosTable = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        textoFactura1 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        textoFactura2 = new javax.swing.JTextArea();
        pagarButton = new javax.swing.JButton();
        JefeCocina = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        JefeTable = new javax.swing.JTable();
        Gerent = new javax.swing.ButtonGroup();
        PMES = new javax.swing.JPanel();
        NOMMES1 = new javax.swing.JLabel();
        APEMES1 = new javax.swing.JLabel();
        CCMES1 = new javax.swing.JLabel();
        TAPEMES1 = new javax.swing.JTextField();
        TCCMES1 = new javax.swing.JTextField();
        GENERAR = new javax.swing.JButton();
        TNOMMES1 = new javax.swing.JTextField();

        Recepcion.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Recepcion.setTitle("RECEPCION");
        Recepcion.setMinimumSize(new java.awt.Dimension(470, 386));
        Recepcion.setResizable(false);
        Recepcion.setSize(new java.awt.Dimension(510, 420));

        titulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("BIENVENIDO A NUESTRO RESTAURANTE");

        NOMBRE.setText("NOMBRE: ");

        APELLIDO.setText("APELLIDO: ");

        CEDULA.setText("C.C:");

        jScrollPane1.setViewportView(listaMesas);

        RESERVAR.setText("RESERVAR MESA");
        RESERVAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESERVARActionPerformed(evt);
            }
        });

        hint.setForeground(new java.awt.Color(153, 153, 153));
        hint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hint.setText("Llene los campos y seleccione la mesa donde quiera sentarse.");

        listamesas.setForeground(new java.awt.Color(0, 204, 102));
        listamesas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listamesas.setText("MESAS DISPONIBLES:");

        AYUDA.setText("?");
        AYUDA.setVisible(true);
        AYUDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AYUDAActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(listaMeseros);

        listameseros.setForeground(new java.awt.Color(0, 204, 102));
        listameseros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listameseros.setText("MESEROS:");
        listameseros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listameserosMouseClicked(evt);
            }
        });

        comprobar.setText("COMPROBAR");
        comprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprobarActionPerformed(evt);
            }
        });

        NOMBRET.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NOMBRETKeyTyped(evt);
            }
        });

        APELLIDOT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                APELLIDOTKeyTyped(evt);
            }
        });

        CEDULAT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CEDULATKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout RecepcionLayout = new javax.swing.GroupLayout(Recepcion.getContentPane());
        Recepcion.getContentPane().setLayout(RecepcionLayout);
        RecepcionLayout.setHorizontalGroup(
            RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RecepcionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RecepcionLayout.createSequentialGroup()
                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(RecepcionLayout.createSequentialGroup()
                                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(hint, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                                    .addGroup(RecepcionLayout.createSequentialGroup()
                                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(NOMBRE)
                                            .addComponent(APELLIDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(CEDULA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NOMBRET)
                                            .addComponent(APELLIDOT)
                                            .addComponent(CEDULAT))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AYUDA, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 6, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(RecepcionLayout.createSequentialGroup()
                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(listamesas, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RecepcionLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(RESERVAR)
                                .addGap(18, 18, 18)
                                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(listameseros, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RecepcionLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comprobar)
                                .addGap(67, 67, 67))))))
        );
        RecepcionLayout.setVerticalGroup(
            RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RecepcionLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addComponent(hint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NOMBRE)
                    .addComponent(NOMBRET, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(APELLIDO)
                    .addComponent(AYUDA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(APELLIDOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CEDULA)
                    .addComponent(CEDULAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RecepcionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(listameseros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RESERVAR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comprobar))
                    .addGroup(RecepcionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(listamesas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Facturacion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Facturacion.setTitle("VENTANA FACTURA");
        Facturacion.setMinimumSize(new java.awt.Dimension(300, 310));
        Facturacion.setResizable(false);
        Facturacion.setSize(new java.awt.Dimension(300, 310));

        nombreFacturacion.setText("Mesero a cargo: ");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MESAS CON FACTURACION DISPONIBLE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jScrollPane4.setViewportView(listaMesasFacturacion);

        generarFactura.setText("GENERAR FACTURA");
        generarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarFacturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
            .addComponent(generarFactura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generarFactura))
        );

        javax.swing.GroupLayout FacturacionLayout = new javax.swing.GroupLayout(Facturacion.getContentPane());
        Facturacion.getContentPane().setLayout(FacturacionLayout);
        FacturacionLayout.setHorizontalGroup(
            FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FacturacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreFacturacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        FacturacionLayout.setVerticalGroup(
            FacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacturacionLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(nombreFacturacion)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Gerente.setTitle("OPCIONES AVANZADAS");
        Gerente.setMinimumSize(new java.awt.Dimension(500, 300));
        Gerente.setResizable(false);
        Gerente.setSize(new java.awt.Dimension(500, 300));
        Gerente.getContentPane().setLayout(null);

        BIENVENIDO.setText("¡BIENVENIDO SEÑOR GERENTE!");
        Gerente.getContentPane().add(BIENVENIDO);
        BIENVENIDO.setBounds(130, 10, 290, 14);

        MENSAJEV.setText("¿QUÉ DESEA REALIZAR?");
        Gerente.getContentPane().add(MENSAJEV);
        MENSAJEV.setBounds(34, 36, 240, 14);

        Gerent.add(RVM);
        RVM.setText("RESUMEN DE VENTAS EN MESA. [ X ]");
        RVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RVMActionPerformed(evt);
            }
        });
        Gerente.getContentPane().add(RVM);
        RVM.setBounds(6, 73, 240, 23);

        Gerent.add(TVD);
        TVD.setText("TOTAL DE LAS VENTAS DEL DÍA.");
        TVD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TVDActionPerformed(evt);
            }
        });
        Gerente.getContentPane().add(TVD);
        TVD.setBounds(6, 101, 270, 23);

        Gerent.add(PMV);
        PMV.setText("PLATO MAS VENDIDO.");
        PMV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMVActionPerformed(evt);
            }
        });
        Gerente.getContentPane().add(PMV);
        PMV.setBounds(6, 127, 270, 23);

        RESUMENVENTAS.setEditable(false);
        RESUMENVENTAS.setText("Digite aqui el número de la mesa...");
        RESUMENVENTAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RESUMENVENTASMouseClicked(evt);
            }
        });
        RESUMENVENTAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESUMENVENTASActionPerformed(evt);
            }
        });
        Gerente.getContentPane().add(RESUMENVENTAS);
        RESUMENVENTAS.setBounds(250, 70, 220, 30);

        Gerent.add(VCFR);
        VCFR.setText("VENTAS SIN CONCRETAR POR FALTA DE RECURSOS.");
        VCFR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VCFRActionPerformed(evt);
            }
        });
        Gerente.getContentPane().add(VCFR);
        VCFR.setBounds(6, 153, 410, 23);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("¿QUÉ MESA DESEA VER?");
        Gerente.getContentPane().add(jLabel6);
        jLabel6.setBounds(290, 50, 175, 14);

        Gerent.add(MMV);
        MMV.setText("NOMBRE DEL MESERO CON MAYOR NUMERO DE VENTAS.");
        MMV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MMVActionPerformed(evt);
            }
        });
        Gerente.getContentPane().add(MMV);
        MMV.setBounds(6, 179, 440, 23);

        jButton1.setText("CONTINUAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Gerente.getContentPane().add(jButton1);
        jButton1.setBounds(200, 210, 130, 23);

        Factura.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Factura.setTitle("FACTURA");
        Factura.setResizable(false);
        Factura.setSize(new java.awt.Dimension(304, 444));

        facturaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FACTURA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        facturaPanel.setMinimumSize(new java.awt.Dimension(252, 360));
        facturaPanel.setPreferredSize(new java.awt.Dimension(252, 360));
        facturaPanel.setLayout(null);

        platosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCTO", "CANT.", "PRECIO UN."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(platosTable);
        if (platosTable.getColumnModel().getColumnCount() > 0) {
            platosTable.getColumnModel().getColumn(0).setResizable(false);
            platosTable.getColumnModel().getColumn(1).setResizable(false);
            platosTable.getColumnModel().getColumn(1).setPreferredWidth(4);
            platosTable.getColumnModel().getColumn(2).setResizable(false);
            platosTable.getColumnModel().getColumn(2).setPreferredWidth(33);
        }

        facturaPanel.add(jScrollPane5);
        jScrollPane5.setBounds(10, 160, 275, 100);

        textoFactura1.setColumns(20);
        textoFactura1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        textoFactura1.setRows(5);
        textoFactura1.setText(".____RESTAURANTE LA PROSPERIDAD____.\n. Hora en la que se realizó la facturación: \n.  \n. Hora del pedido:\n. Nombre del cliente: \n. Cédula del cliente: \n. Productos ordenados:");
        textoFactura1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textoFactura1.setEnabled(false);
        jScrollPane7.setViewportView(textoFactura1);

        facturaPanel.add(jScrollPane7);
        jScrollPane7.setBounds(10, 20, 275, 140);

        textoFactura2.setColumns(20);
        textoFactura2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        textoFactura2.setRows(5);
        textoFactura2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textoFactura2.setEnabled(false);
        jScrollPane8.setViewportView(textoFactura2);

        facturaPanel.add(jScrollPane8);
        jScrollPane8.setBounds(10, 260, 275, 110);

        pagarButton.setText("Pagar");
        pagarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FacturaLayout = new javax.swing.GroupLayout(Factura.getContentPane());
        Factura.getContentPane().setLayout(FacturaLayout);
        FacturaLayout.setHorizontalGroup(
            FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacturaLayout.createSequentialGroup()
                .addGroup(FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(facturaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FacturaLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(pagarButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FacturaLayout.setVerticalGroup(
            FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacturaLayout.createSequentialGroup()
                .addComponent(facturaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pagarButton)
                .addContainerGap())
        );

        JefeCocina.setTitle("Productos");
        JefeCocina.setMinimumSize(new java.awt.Dimension(400, 200));
        JefeCocina.setResizable(false);

        JefeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Plato", "Cantidad", "Terminados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JefeTable.setMinimumSize(new java.awt.Dimension(240, 0));
        jScrollPane3.setViewportView(JefeTable);
        if (JefeTable.getColumnModel().getColumnCount() > 0) {
            JefeTable.getColumnModel().getColumn(0).setResizable(false);
            JefeTable.getColumnModel().getColumn(1).setResizable(false);
            JefeTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout JefeCocinaLayout = new javax.swing.GroupLayout(JefeCocina.getContentPane());
        JefeCocina.getContentPane().setLayout(JefeCocinaLayout);
        JefeCocinaLayout.setHorizontalGroup(
            JefeCocinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JefeCocinaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        JefeCocinaLayout.setVerticalGroup(
            JefeCocinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JefeCocinaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO");
        setMinimumSize(new java.awt.Dimension(250, 330));
        setResizable(false);
        setSize(new java.awt.Dimension(250, 330));

        PMES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MESERO #1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        NOMMES1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NOMMES1.setText("NOMBRE:");

        APEMES1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        APEMES1.setText("APELLIDO:");

        CCMES1.setText("C.C (NÚMERO DE IDENTIFICACIÓN)");

        TAPEMES1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TAPEMES1ActionPerformed(evt);
            }
        });
        TAPEMES1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TAPEMES1KeyTyped(evt);
            }
        });

        TCCMES1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TCCMES1KeyTyped(evt);
            }
        });

        GENERAR.setText("GENERAR");
        GENERAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GENERARActionPerformed(evt);
            }
        });

        TNOMMES1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNOMMES1ActionPerformed(evt);
            }
        });
        TNOMMES1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TNOMMES1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PMESLayout = new javax.swing.GroupLayout(PMES);
        PMES.setLayout(PMESLayout);
        PMESLayout.setHorizontalGroup(
            PMESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PMESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GENERAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NOMMES1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(APEMES1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CCMES1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TAPEMES1)
                    .addComponent(TCCMES1)
                    .addComponent(TNOMMES1))
                .addContainerGap())
        );
        PMESLayout.setVerticalGroup(
            PMESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMESLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NOMMES1)
                .addGap(18, 18, 18)
                .addComponent(TNOMMES1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(APEMES1)
                .addGap(14, 14, 14)
                .addComponent(TAPEMES1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CCMES1)
                .addGap(12, 12, 12)
                .addComponent(TCCMES1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(GENERAR)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PMES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PMES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void GENERARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GENERARActionPerformed
        if(TNOMMES1.getText().equals("")){
            if(TAPEMES1.getText().equals("")){
                if(TCCMES1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Todos los campos estan en blanco.", "ESPACIOS EN BLANCO", ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "El nombre y el apellido del mesero están en blanco.", "ESPACIOS EN BLANCO", ERROR_MESSAGE);
                }
            }else{
                if (TCCMES1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "El nombre y el número de identificación están en blanco.", "ESPACIOS EN BLANCO", ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "El nombre esta en blanco.", "ESPACIO EN BLANCO", ERROR_MESSAGE);
                }
            }
        }else{
            if(TAPEMES1.getText().equals("")){
                if(TCCMES1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "El apellido y el número de identificación estan en blanco.", "ESPACIOS EN BLANCO", ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "El apellido está en blanco.", "ESPACIOS EN BLANCO", ERROR_MESSAGE);
                }
            }else{
                if (TCCMES1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "El número de identificación está en blanco.", "ESPACIOS EN BLANCO", ERROR_MESSAGE);
                }else{
                    String nombreCompleto = TNOMMES1.getText().concat(" ").concat(TAPEMES1.getText());
                    modificarArchivo(nombreCompleto, Integer.parseInt(TCCMES1.getText()));
                    Generar();
                    nnom = 1;
                    nape = 1;
                    ncc = 1;
                    if(mc == 4){
                        JOptionPane.showMessageDialog(null, "Los 4 meseros han sido generados y asignados a sus respectivas mesas.", "TODOS LOS DATOS ESTAN CORRECTOS", INFORMATION_MESSAGE);
                        Recepcion.setVisible(true);
                        Recepcion.setLocationRelativeTo(null);
                        listaMeseros();
                        listaMesas();
                        this.dispose();
                    } 

                }
            }
        }
    }//GEN-LAST:event_GENERARActionPerformed
    
    //<editor-fold defaultstate="collapsed" desc="Eventos al presionar teclas">
    private void TAPEMES1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAPEMES1KeyTyped
        char CR = evt.getKeyChar();
        String R = "\b", cr = "" + CR, E = "\n";
        if (!cr.equals(E)) {
            if(cr.equals(R) && nape > 0){
                nape--;
            }else{
                if((!Character.isLetter(CR) || Character.isDigit(CR))) { 
                    evt.consume();
                }else{
                    TAPEMES1.setText(MayusInicial(TAPEMES1.getText()));
                }
                if(nape > 10){
                    evt.consume();
                    JOptionPane.showMessageDialog(null, "El apellido no puede tener mas de 10 caracteres.", "DEMASIADAS LETRAS", ERROR_MESSAGE);
                }else{
                    nape++;
                }
            }
        }else{
            if(TAPEMES1.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "El campo seleccionado esta en blanco."
                        + "\nPara continuar escriba el apellido y luego presione la tecla [TAB]."
                        + "\nLlene los campos restantes, y por último presione el botón [GENERAR].", "TIENE EL CAMPO EN BLANCO", WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Presione la tecla [TAB] para pasar al siguiente campo de texto."
                        + "\nO de click al siguiente campo, no importa el orden en que los llene.", "PRESIONE [TAB] PARA CONTINUAR..", INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_TAPEMES1KeyTyped
    
    private void TCCMES1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCCMES1KeyTyped
        
        char CR = evt.getKeyChar();
        String R = "\b", cr = "" + CR, E = "\n";
        if (!cr.equals(E)) {
            
            if(cr.equals(R) && ncc > 0){
                ncc--;
            }else{
                if((Character.isLetter(CR) || !Character.isDigit(CR))) { 
                    evt.consume();
                }else{

                    if(ncc > 10){
                        evt.consume();
                        JOptionPane.showMessageDialog(null, "El número de identificación no puede tener mas de 10 digitos.", "DEMASIADOS NÚMEROS", ERROR_MESSAGE);
                    }else{
                        ncc++;
                    }

                }
            }
        }else{
            
            if(TCCMES1.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "El campo seleccionado esta en blanco."
                        + "\nPara continuar escriba el # de ID y luego presione la tecla [TAB]."
                        + "\nLlene los campos restantes, y por último presione el botón [GENERAR].", "TIENE EL CAMPO EN BLANCO", WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Presione la tecla [TAB] para pasar al siguiente campo de texto."
                        + "\nO de click al siguiente campo, no importa el orden en que los llene.", "PRESIONE [TAB] PARA CONTINUAR..", INFORMATION_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_TCCMES1KeyTyped
    //</editor-fold>
    
    private void AYUDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AYUDAActionPerformed
        String Compro = NOMBRET.getText();
        if(Compro.equals("Gerente")){
            Gerente.setVisible(true);
            Gerente.setLocationRelativeTo(null);
        }else{
            if (Compro.equals("Jefe")) {
                vistaJefe();
                JefeCocina.setVisible(true);
                JefeCocina.setLocationRelativeTo(null);
                
            }else{
                JOptionPane.showMessageDialog(null, "Llene todos los campos, seleccione su mesa y luego presione el botón [RESERVAR MESA] para continuar."
                        + "\nSi elige alguno de los nombres de los meseros y luego presiona [COMPROBAR] verá que mesas tiene a su cargo y cuales estan disponibles.", "Ayuda", INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_AYUDAActionPerformed

    private void RESUMENVENTASMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RESUMENVENTASMouseClicked
        RESUMENVENTAS.setText("");
    }//GEN-LAST:event_RESUMENVENTASMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (RVM.isSelected()) {
        String dato = RESUMENVENTAS.getText();
        int opcion = 1;
        opcionesGerente(dato, opcion);
        }
        if (TVD.isSelected()) {
            String dato = null;
            int opcion = 2;
            this.totalDia = 0.0;
            opcionesGerente(dato, opcion);
        }
        if (PMV.isSelected()) {
            opcionesGerente();
        }
        if (VCFR.isSelected()) {
            if (platosNT == 0) {
                JOptionPane.showMessageDialog(null, "Todos los platos han sido terminados", "Platos Terminados", INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "No se han terminado "+ platosNT + " por falta de recursos", "Platos Terminados", INFORMATION_MESSAGE);
            }
        }
        if (MMV.isSelected()) {
            String dato = null;
            int opcion = 3;
            this.totalMesero1 = 0.0;
            this.totalMesero2 = 0.0;
            this.totalMesero3 = 0.0;
            this.totalMesero4 = 0.0;
            opcionesGerente(dato, opcion);
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    //<editor-fold defaultstate="collapsed" desc="Comprobar las mesas del mesero seleccionado.">
    private void comprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprobarActionPerformed
        int p = listaMeseros.getSelectedIndex();
        String ncmc = NOMBRET.getText().concat(" ").concat(APELLIDOT.getText());
        if(p == -1){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún nombre.", "ERROR", ERROR_MESSAGE);
        }else{
            String MesasACargo = "";
            pMesero = ptrMesero;
            for (int i = 0; i < p; i++) {
                if (pMesero.linkMesero != null) {
                    pMesero = pMesero.linkMesero;
                }
            }
            pMesa = pMesero.ptrMesa;
            if (pMesero.nombre.equals(ncmc)) {
                try {
                    cedulaMfact = Integer.parseInt(CEDULAT.getText());
                    if(cedulaMfact == pMesero.ced){
                        DefaultListModel model = new DefaultListModel();
                        while(pMesa.link != null){
                            if(pMesa.disponible == false){
                                model.addElement("Mesa #" + pMesa.mesan);    
                            }
                            pMesa = pMesa.link;
                        }
                        listaMesasFacturacion.setModel(model);
                        nnom = 1;
                        nape = 1;
                        ncc = 1;
                        nombreFacturacion.setText("Mesero a cargo: " + pMesero.nombre);
                        Facturacion.setVisible(true);
                        Facturacion.setLocationRelativeTo(null);
                    }else{
                        JOptionPane.showMessageDialog(null, "La cédula " + CEDULAT.getText() + " no concuerda con la cédula del mesero.", "CÉDULA INCORRECTA", ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite su número de cédula correctamente.", "Falta escribir el número de cédula", ERROR_MESSAGE);
                }
            }else{
                for (int i = 0; i < 5; i++) {
                    if (pMesa.disponible == true) {

                        MesasACargo = MesasACargo + "Mesa " + pMesa.mesan + " - Disponible" + "\n";

                    }else{

                        MesasACargo = MesasACargo + "Mesa " + pMesa.mesan + " - Ocupada" + "\n";

                    }

                    pMesa = pMesa.link;
                }
                JOptionPane.showMessageDialog(null, MesasACargo, "Mesas a cargo del mesero " + pMesero.nombre, INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_comprobarActionPerformed
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Reserva la mesa elegida para el visitante.">
    private void RESERVARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESERVARActionPerformed
        
        if (NOMBRET.getText().equals("")) {
            if (APELLIDOT.getText().equals("")) {
                if (CEDULAT.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos están en blanco.", "TODOS CAMPOS EN BLANCO", ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "El nombre y el apellido estan en blanco.", "ALGUNOS CAMPOS EN BLANCO", ERROR_MESSAGE);
                }
            }else{
                if (CEDULAT.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "El nombre y la cedula estan en blanco.", "ALGUNOS CAMPOS EN BLANCO", ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "El nombre esta en blanco.", "ALGUNOS CAMPOS EN BLANCO", ERROR_MESSAGE);
                }
            }
        }else{
            if (APELLIDOT.getText().equals("")) {
                if (CEDULAT.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "El apellido y la cedula estan en blanco.", "ALGUNOS CAMPOS EN BLANCO", ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "La cedula esta en blanco.", "ALGUNOS CAMPOS EN BLANCO", ERROR_MESSAGE);
                }
            }else{
                if (CEDULAT.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "La cedula esta en blanco.", "ALGUNOS CAMPOS EN BLANCO", ERROR_MESSAGE);
                }else{
                    String nMesaR = listaMesas.getSelectedValue();
                    if (nMesaR == null) {
                        JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna mesa.", "ERROR", ERROR_MESSAGE);
                    }else{
                        String dUl = "";
                        if (nMesaR.length() < 8) {
                            dUl = dUl + nMesaR.charAt(nMesaR.length() - 1);
                        }else{
                            dUl = nMesaR.substring((nMesaR.length() - 2), nMesaR.length());
                        }
                        int nTb = Integer.parseInt(dUl);
                        ncc = 1;
                        nape = 1;
                        nnom = 1;
                        Menu menu = new Menu();
                        menu.nombreCliente = NOMBRET.getText().concat(" ").concat(APELLIDOT.getText());
                        menu.cedulaCliente = Integer.parseInt(CEDULAT.getText());
                        ReservarMesa(nTb, menu);
                        listaMesas();
                    }
                }
            }
        }
    }//GEN-LAST:event_RESERVARActionPerformed
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Subrutina <ReservarMesa>, recibe como parametro un entero llamado < p >">
    /**
     * Subrutina que toma el número para buscarlo entre los nodos {MESA}
     * continuar a la siguiente ventana llamada {MENÚ} y actualizar la lista
     * para mostrar la mesa como ocupada.
     * @param p Recibe el número de la posición seleccionada en la lista para reservarla. 
     * @param menuR 
     * @since Primera Entrega.
     */
    public void ReservarMesa(int p, Menu menuR){
        
        Menu menu = menuR;
        pMesero = ptrMesero;
        boolean swr = true;
        while(pMesero != null && swr == true) {
            pMesa = pMesero.ptrMesa;
            while(pMesa.link != null && swr == true){
                if (p == pMesa.mesan) {
                    swr = false;
                    menu.nombreMesero = pMesero.nombre;
                    menu.idMesa = pMesa.mesan;
                    pMesa.disponible = false;
                    menu.setVisible(true);
                    NOMBRET.setText("");
                    APELLIDOT.setText("");
                    CEDULAT.setText("");
                }else{
                    pMesa = pMesa.link;
                }
            }
            pMesero = pMesero.linkMesero;
        }
        
    }
    //</editor-fold>
    
        int ct = 1;
    private void TAPEMES1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TAPEMES1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TAPEMES1ActionPerformed

    private void TNOMMES1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNOMMES1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNOMMES1ActionPerformed
    int nnom = 1;
    private void TNOMMES1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNOMMES1KeyTyped
        char CR = evt.getKeyChar();
        String R = "\b", cr = "" + CR, E = "\n";
        if (!cr.equals(E)) {
            if(cr.equals(R) && nnom > 0){
                nnom--;
            }else{
                if((!Character.isLetter(CR) || Character.isDigit(CR))) { 
                    evt.consume();
                }else{
                    TNOMMES1.setText(MayusInicial(TNOMMES1.getText()));
                }
                if(nnom > 10){
                    evt.consume();
                    JOptionPane.showMessageDialog(null, "El nombre no puede tener mas de 10 caracteres.", "DEMASIADAS LETRAS", ERROR_MESSAGE);
                }else{
                    nnom++;
                }
            }
        }else{
            
            if(TNOMMES1.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "El campo seleccionado esta en blanco."
                        + "\nPara continuar escriba el nombre y luego presione la tecla [TAB]."
                        + "\nLlene los campos restantes, y por último presione el botón [GENERAR].", "TIENE EL CAMPO EN BLANCO", WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Presione la tecla [TAB] para pasar al siguiente campo de texto."
                        + "\nO de click al siguiente campo, no importa el orden en que los llene.", "PRESIONE [TAB] PARA CONTINUAR..", INFORMATION_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_TNOMMES1KeyTyped

    private void NOMBRETKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NOMBRETKeyTyped
        char CR = evt.getKeyChar();
        String R = "\b", cr = "" + CR, E = "\n";
        if (!cr.equals(E)) {
            if(cr.equals(R) && nnom > 0){
                nnom--;
            }else{
                if((!Character.isLetter(CR) || Character.isDigit(CR))) { 
                    evt.consume();
                }else{
                    NOMBRET.setText(MayusInicial(NOMBRET.getText()));
                }
                if(nnom > 10){
                    evt.consume();
                    JOptionPane.showMessageDialog(null, "El nombre no puede tener mas de 10 caracteres.", "DEMASIADAS LETRAS", ERROR_MESSAGE);
                }else{
                    nnom++;
                }
            }
        }
    }//GEN-LAST:event_NOMBRETKeyTyped

    private void APELLIDOTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_APELLIDOTKeyTyped
        char CR = evt.getKeyChar();
        String R = "\b", cr = "" + CR, E = "\n";
        if (!cr.equals(E)) {
            if(cr.equals(R) && nape > 0){
                nape--;
            }else{
                if((!Character.isLetter(CR) || Character.isDigit(CR))) { 
                    evt.consume();
                }else{
                    APELLIDOT.setText(MayusInicial(APELLIDOT.getText()));
                }
                if(nape > 10){
                    evt.consume();
                    JOptionPane.showMessageDialog(null, "El apellido no puede tener mas de 10 caracteres.", "DEMASIADAS LETRAS", ERROR_MESSAGE);
                }else{
                    nape++;
                }
            }
        }
    }//GEN-LAST:event_APELLIDOTKeyTyped

    private void CEDULATKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CEDULATKeyTyped
        char CR = evt.getKeyChar();
        String R = "\b", cr = "" + CR, E = "\n";
        if (!cr.equals(E)) {
            if(cr.equals(R) && ncc > 0){
                ncc--;
            }else{
                if((Character.isLetter(CR) || !Character.isDigit(CR))) { 
                    evt.consume();
                }else{

                    if(ncc > 10){
                        evt.consume();
                        JOptionPane.showMessageDialog(null, "El número de identificación no puede tener mas de 10 digitos.", "DEMASIADOS NÚMEROS", ERROR_MESSAGE);
                    }else{
                        ncc++;
                    }

                }
            }
        }
    }//GEN-LAST:event_CEDULATKeyTyped
    
    public String obtenerNombre(String linea){
        int contt = 1;
        StringTokenizer nc = new StringTokenizer(linea, ":");
        while(nc.hasMoreTokens()){
            if (contt != 2) {
                nc.nextToken();
            }            
            if (contt == 2) {
                nombreCli = nc.nextToken();
                nombreCli = nombreCli.substring(1, nombreCli.length());
            }
            contt++;
        }
        return nombreCli;
    }
    
    public int obtenerCedula(String linea){
        int contt = 1;
        StringTokenizer nc = new StringTokenizer(linea, ":");
        while(nc.hasMoreTokens()){
            if (contt != 2) {
                nc.nextToken();
            }   
            if (contt == 2) {
                cedulaClit = nc.nextToken();
                cedulaClit = cedulaClit.substring(1, cedulaClit.length()-1);
                cedulaCli = Integer.parseInt(cedulaClit);
                
            }
            contt++;
        }
        return cedulaCli;
    }
  
    private void listameserosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listameserosMouseClicked
        JOptionPane.showMessageDialog(null, "Haga click en alguno de los nombres de los meseros,\n"
                + "luego en el botón [COMPROBAR] para ver que mesas tiene a cargo el mesero\n"
                + "y saber si están disponibles o no.", "AYUDA", INFORMATION_MESSAGE);
    }//GEN-LAST:event_listameserosMouseClicked
    
    public void vistaJefe(){
        String files;
        int c = 0, d = 0;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
        for (int i = 0; i < listOfFiles.length; i++){ 
            if (listOfFiles[i].isFile()){ 
                files = listOfFiles[i].getName();
                if (files.endsWith(".txt") || files.endsWith(".TXT")){
                    if (files.equals("platos.txt") || files.equals("plato0.txt")  ) {
                        String file = path + "\\" + files;
                        archivo = file;
                        try(FileReader archivop = new FileReader(file)){
                            try(BufferedReader br = new BufferedReader(archivop)) {
                                String linea;
                                String cantT, precioT, nombre;
                                int cant, fila = 1;
                                float precio;
                                DefaultTableModel model = (DefaultTableModel) JefeTable.getModel();
                                while((linea = br.readLine()) != null){
                                    c = c + 1;
                                    model.setRowCount(fila);
                                    model.setColumnCount(3);
                                    int contt = 1;
                                    StringTokenizer nc = new StringTokenizer(linea, ",");
                                    while(nc.hasMoreTokens()){
                                        if (contt ==1) {
                                           nombre = nc.nextToken();
                                           nombre = nombre.substring(0, nombre.length());
                                           JefeTable.setValueAt(nombre,fila-1, 0);
                                        }else{
                                            if (contt == 2) {
                                            cantT = nc.nextToken();
                                            cantT = cantT.substring(1, cantT.length());
                                            cant = Integer.parseInt(cantT);
                                            JefeTable.setValueAt(cant,fila-1, 1);
                                            }else{
                                                precioT = nc.nextToken();
                                                precioT = precioT.substring(1, precioT.length());
                                                precio = Float.parseFloat(precioT);
                                                JefeTable.setValueAt(precio,fila-1, 2);
                                            }
                                        }
                                        contt++;
                                    }
                                    fila++;
                                }
                                fila = fila-1;
                                model.setRowCount(fila);
                                model.setColumnCount(3);
                            }
                        }catch(FileNotFoundException e){
                        } catch (IOException ex) {
                            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }
                }
            }
        }
    }
   
    public void organizarProductos(int mesaselec){
        
        String files;
        int c = 0, d = 0;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
        for (int i = 0; i < listOfFiles.length; i++){ 
            if (listOfFiles[i].isFile()){ 
                files = listOfFiles[i].getName();
                if (files.endsWith(".txt") || files.endsWith(".TXT")){
                    if (files.equals("Pedido" + mesaselec+ ".txt")  ) {
                        String file = path + "\\Pedido"+mesaselec+".txt";
                        archivo = file;
                        try(FileReader archivop = new FileReader(file)){
                            try(BufferedReader br = new BufferedReader(archivop)) {
                                String linea;
                                boolean swg;
                                Double totalIva, propin, iva;
                                String cantT, precioT, nombre;
                                int cant = 0, fila = 1;
                                float totall = 0, precio = 0;
                                DefaultTableModel model = (DefaultTableModel) platosTable.getModel();
                                while((linea = br.readLine()) != null){
                                    c = c + 1;
                                    model.setRowCount(fila);
                                    model.setColumnCount(3);
                                    swg = false;
                                    if (c==1) {
                                        this.nombreCli = obtenerNombre(linea);
                                    }
                                    if (c==2) {
                                        this.cedulaCli = obtenerCedula(linea);
                                    }
                                    if(c<4){
                                        swg = true;
                                    }
                                    if (linea.equals("Fin del pedido.")) {
                                        d = 1;
                                    }
                                    
                                    if(swg == false && d == 0){
                                        int contt = 1;
                                        StringTokenizer nc = new StringTokenizer(linea, ",");
                                        while(nc.hasMoreTokens()){
                                            if (contt ==1) {
                                               nombre = nc.nextToken();
                                               nombre = nombre.substring(0, nombre.length());
                                               platosTable.setValueAt(nombre,fila-1, 0);
                                            }else{
                                                if (contt == 2) {
                                                cantT = nc.nextToken();
                                                cantT = cantT.substring(1, cantT.length());
                                                cant = Integer.parseInt(cantT);
                                                platosTable.setValueAt(cant,fila-1, 1);
                                                }else{
                                                    precioT = nc.nextToken();
                                                    precioT = precioT.substring(1, precioT.length());
                                                    precio = Float.parseFloat(precioT);
                                                    platosTable.setValueAt(precio,fila-1, 2);
                                                }
                                            }
                                            contt++;
                                        }
                                        totall = totall + (cant * precio);
                                        fila++;
                                    }
                                }
                                fila = fila-1;
                                model.setRowCount(fila);
                                model.setColumnCount(3);
                                iva = totall * 0.19;
                                Iva = iva;
                                totalIva = (totall * 1.19);
                                propin = (totall * 0.10);
                                propina = propin;
                                totalIva = totalIva + propin;
                                total = totalIva;
                                
                            }
                        }catch(FileNotFoundException e){
                        } catch (IOException ex) {
                            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }
                }
            }
        }
    }
    public void EstadoDeMesa(int num){
        int p = listaMeseros.getSelectedIndex();
        pMesero = ptrMesero;
        int contt = 0;
        for (int i = 0; i < p; i++) {
            if (pMesero.linkMesero != null) {
                pMesero = pMesero.linkMesero;
            }
        }
        pMesa = pMesero.ptrMesa;
        
        while(pMesa.link != null){
            if (pMesa.mesan == num) {
                pMesa.disponible = true;
                contt = contt + 1;
            }
            pMesa = pMesa.link;
        }
    }
    
    public boolean comprobarFacturacionDisponible(int nmesa){
        boolean swf = true;
        try(FileReader archivop = new FileReader(path + "\\Pedido" + nmesa + ".txt")){ 
            try(BufferedReader br = new BufferedReader(archivop)) {
                String lineaT = br.readLine();
                while(!lineaT.equals("Cocinando...")){
                    lineaT = br.readLine();
                }
                if (br.readLine() == null) {
                    swf = false;
                }
                archivop.close();
                br.close();
            }
        }catch(FileNotFoundException e){
            } catch (IOException ex) {
                Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return swf;
    }
    
    private void generarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarFacturaActionPerformed
        int posFact = listaMesasFacturacion.getSelectedIndex();
        if(posFact == -1){
            JOptionPane.showMessageDialog(null, "Seleccione alguna posición en la lista para generar la información.", "NINGUNA POSICIÓN SELECCIONADA", ERROR_MESSAGE);
        }else{
            Calendar horaFactura = new GregorianCalendar();
            int hh = horaFactura.get(Calendar.HOUR_OF_DAY), mm = horaFactura.get(Calendar.MINUTE), ss = horaFactura.get(Calendar.SECOND);
            String horaFacturacion = (hh < 10?"0":"") + hh + ":" + (mm < 10?"0":"") + mm + ":" + (ss < 10?"0":"") + ss, horaPedidoR, nombreClientef;
            int tok = 1;
            String seleccionado = listaMesasFacturacion.getModel().getElementAt(posFact);
            String MesaT;
            StringTokenizer mesa = new StringTokenizer(seleccionado, "#");
            while(mesa.hasMoreTokens()){
                if (tok == 1) {
                    mesa.nextToken();
                }
                if (tok == 2) {
                    MesaT = mesa.nextToken();
                    MesaT = MesaT.substring(0, MesaT.length());
                    mesaSelec = Integer.parseInt(MesaT);
                }
                tok++;
            }
            if(comprobarFacturacionDisponible(mesaSelec) == false){
                JOptionPane.showMessageDialog(null, "Debe esperar a que el plato termine de cocinarse para pedir una facturación.", "EL PLATO AUN ESTÁ PREPARANDOSE", INFORMATION_MESSAGE);
            }else{
                organizarProductos(mesaSelec);
                textoFactura1.setText("._____RESTAURANTE LA PROSPERIDAD_____.\n" +
                        ". Hora en la que se realizó la facturación: \n" +
                        ". " + horaFacturacion + " \n" +
                        ". Hora del pedido: \n" +
                        ". " + horaPed + " \n" +
                        ". Nombre del cliente: " + nombreCli + "\n" +
                        ". Cédula del cliente: " + cedulaCli+ " \n" +
                        ". \n" +
                        ". Productos ordenados:");
                        textoFactura2.setText(". Usted se encontraba en la mesa: " + mesaSelec + "\n"+ 
                        ". " + nombreFacturacion.getText()+ "\n" +
                        ". Cédula del mesero: " + cedulaMfact + "\n" +
                        ". IVA (20% del total comprado): "+this.Iva + "\n" +
                        ". Propina al mesero (10%): " +this.propina+ " \n" +
                        ". Precio a pagar: "+this.total);
                Factura.setVisible(true);
                Factura.setLocationRelativeTo(null);
            }
        }
    }//GEN-LAST:event_generarFacturaActionPerformed
    
    public int verificarArchivo(){
        
        String files;
        int c = 0, d = 0;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
        for (int i = 0; i < listOfFiles.length; i++){ 
            if (listOfFiles[i].isFile()){ 
                files = listOfFiles[i].getName();
                if (files.endsWith(".txt") || files.endsWith(".TXT")){
                    if (files.equals("ventaMesas.txt")) {
                        Exist = 0;
                    }else{
                        if (files.equals("ventaMesas0.txt")) {
                            Exist = 1;
                        }
                    }
                }
            }    
        }
        return Exist;
    }
    
    public int verificarArc(){
    
        String files;
        int c = 0, d = 0;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
        for (int i = 0; i < listOfFiles.length; i++){ 
            if (listOfFiles[i].isFile()){ 
                files = listOfFiles[i].getName();
                if (files.endsWith(".txt") || files.endsWith(".TXT")){
                    if (files.equals("platos.txt")) {
                        Exist = 0;
                    }else{
                        if (files.equals("plato0.txt")) {
                            Exist = 1;
                        }
                    }
                }
            }    
        }
        return Exist;
    }
    public String mesaOcupada(String linea){
        int tok = 1;
        String plato = null;
        StringTokenizer mesa= new StringTokenizer(linea, ",");
        while(mesa.hasMoreTokens()){
            if (tok == 1) {
                plato= mesa.nextToken();
                plato = plato.substring(0, plato.length());
            }
            mesa.nextToken();
            tok++;
        }
        return plato;
    }
    
    public void ModificarVentas(int mesa, double tot){
        String archivoMesa;
        Exist = verificarArchivo();
        if (Exist==0) {
            archivoMesa = path + "\\ventaMesas.txt";
            mesero = new File(path + "\\ventaMesas" + Exist + ".txt");
        }else{
            archivoMesa = path + "\\ventaMesas0.txt";
            mesero = new File(path + "\\ventaMesas.txt");
        }
        try(FileReader archivop = new FileReader(archivoMesa)){
            try(BufferedReader br = new BufferedReader(archivop)) {
                String linea;
                String cantT, nombre;
                int contt = 1;
                Double cant ;
                while((linea = br.readLine()) != null){
                    nombre = mesaOcupada(linea);
                    if (nombre.equals("Mesa"+mesa)) {
                        StringTokenizer nc = new StringTokenizer(linea, ",");
                        while(nc.hasMoreTokens()){
                            if (contt ==1) {
                               nombre = nc.nextToken();
                               nombre = nombre.substring(0, nombre.length());
                               this.mesaOc = nombre;
                            }else{
                                if (contt == 2) {
                                    cantT = nc.nextToken();
                                    cantT = cantT.substring(1, cantT.length());
                                    cant = Double.parseDouble(cantT);
                                    this.totalMesa = cant + tot;
                                }
                            }
                            contt++;
                        }
                        modificarLinea(this.mesaOc, this.totalMesa, mesero);
                    }else{
                        modificarLinea(mesero, linea);

                    }
                    
                }
            }
        }catch(FileNotFoundException e){
        } catch (IOException ex) {
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
        File f = new File(archivoMesa); 
        f.delete();
    }
    public void modificarLinea(String plato, Double cantidad, File arc){
        try(FileWriter bw = new FileWriter(arc, true)){
            Object PL = plato;
            Object CA = cantidad;
            
            bw.write( PL + ", " + CA + "\r" + "\n" );
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
    private void pagarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Su pago ha sido realizado.\nTenga un feliz día.", "Pago", INFORMATION_MESSAGE);
        DefaultListModel listModel = (DefaultListModel) listaMesasFacturacion.getModel();
        listModel.removeAllElements();
        File f = new File(archivo); 
        f.delete(); 
        ModificarVentas(mesaSelec, this.total);
        EstadoDeMesa(mesaSelec);
        listaMesas();
        Factura.setVisible(false);
        Facturacion.setVisible(false);
        
    }//GEN-LAST:event_pagarButtonActionPerformed
    public void opcionesGerente(String mesa, int opcion){
        String archivoMesa;
        Exist = verificarArchivo();
        if (Exist==0) {
            archivoMesa = path + "\\ventaMesas.txt";
        }else{
            archivoMesa = path + "\\ventaMesas0.txt";
        }
        try(FileReader archivop = new FileReader(archivoMesa)){
            try(BufferedReader br = new BufferedReader(archivop)) {
                String linea;
                String cantT, nombre;
                int contt;
                Double cant;
                if (opcion == 1) {
                    while((linea = br.readLine()) != null){
                        contt = 1;
                        nombre = mesaOcupada(linea);
                        if (nombre.equals("Mesa"+mesa)) {
                            StringTokenizer nc = new StringTokenizer(linea, ",");
                            while(nc.hasMoreTokens()){
                                if (contt ==1) {
                                   nc.nextToken();
                                }else{
                                    if (contt == 2) {
                                        cantT = nc.nextToken();
                                        cantT = cantT.substring(1, cantT.length());
                                        cant = Double.parseDouble(cantT);
                                        JOptionPane.showMessageDialog(null, "La mesa #"+mesa+" vendio un total de: "+ cant, "Ventas de mesa #"+mesa, INFORMATION_MESSAGE);
                                    }
                                }
                                contt++;
                            }
                        }
                    }
                }else{
                    if (opcion==2) {
                        while((linea = br.readLine()) != null){
                            contt = 1;
                            StringTokenizer nc = new StringTokenizer(linea, ",");
                            while(nc.hasMoreTokens()){
                                if (contt ==1) {
                                   nc.nextToken();
                                }else{
                                    if (contt == 2) {
                                        cantT = nc.nextToken();
                                        cantT = cantT.substring(1, cantT.length());
                                        cant = Double.parseDouble(cantT);
                                        this.totalDia = this.totalDia + cant;
                                    }
                                }
                                contt++;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Las ventas totales del día fueron: "+ this.totalDia, "Ventas del día", INFORMATION_MESSAGE);
                    }else{
                        int Nmesa = 1;
                        while((linea = br.readLine()) != null){
                            contt = 1;
                            StringTokenizer nc = new StringTokenizer(linea, ",");
                            while(nc.hasMoreTokens()){
                                if (contt ==1) {
                                   nc.nextToken();
                                }else{
                                    if (contt == 2) {
                                        cantT = nc.nextToken();
                                        cantT = cantT.substring(1, cantT.length());
                                        cant = Double.parseDouble(cantT);
                                        if (Nmesa<6) {
                                            this.totalMesero1 = this.totalMesero1 + cant;
                                        }else{
                                            if (Nmesa>5 && Nmesa <11 ) {
                                                this.totalMesero2 = this.totalMesero2 + cant;
                                            }else{
                                                if (Nmesa>10 && Nmesa < 15) {
                                                    this.totalMesero3 = this.totalMesero3 + cant;
                                                }else{
                                                    this.totalMesero4 = this.totalMesero4 + cant;
                                                }
                                            }
                                        }
                                    }
                                }
                                contt++;
                            }
                            Nmesa++;
                        }
                        if (this.totalMesero1 > this.totalMesero2 && this.totalMesero1 > this.totalMesero3 && this.totalMesero1 > this.totalMesero4) {
                            JOptionPane.showMessageDialog(null, "El mesero #1 fue el que mas vendio con un total de :"+ this.totalMesero1, "Mejor Mesero", INFORMATION_MESSAGE);
                        }else{
                            if (this.totalMesero2 > this.totalMesero1 && this.totalMesero2 > this.totalMesero3 && this.totalMesero2 > this.totalMesero4) {
                                JOptionPane.showMessageDialog(null, "El mesero #2 fue el que mas vendio con un total de :"+ this.totalMesero2, "Mejor Mesero", INFORMATION_MESSAGE);
                            }else{
                                if (this.totalMesero3 > this.totalMesero1 && this.totalMesero3 > this.totalMesero2 && this.totalMesero3 > this.totalMesero4) {
                                    JOptionPane.showMessageDialog(null, "El mesero #3 fue el que mas vendio con un total de :"+ this.totalMesero2, "Mejor Mesero", INFORMATION_MESSAGE);
                                }else{
                                    if (this.totalMesero4 > this.totalMesero1 && this.totalMesero4 > this.totalMesero2 && this.totalMesero4 > this.totalMesero3) {
                                        JOptionPane.showMessageDialog(null, "El mesero #3 fue el que mas vendio con un total de :"+ this.totalMesero2, "Mejor Mesero", INFORMATION_MESSAGE);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Hay meseros que vendieron la misma cantidad", "Mejor Mesero", INFORMATION_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch(FileNotFoundException e){
        } catch (IOException ex) {
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void opcionesGerente(){
        String archivoMesa;
        Exist = verificarArc();
        if (Exist==0) {
            archivoMesa = path + "\\platos.txt";
        }else{
            archivoMesa = path + "\\plato0.txt";
        }
        try(FileReader archivop = new FileReader(archivoMesa)){
            try(BufferedReader br = new BufferedReader(archivop)) {
                String linea;
                String cantT, nombre, platoMV = null, platoMVT = null;
                int contt, cant, mayor = 0;
                while((linea = br.readLine()) != null){
                    contt = 1;
                    StringTokenizer nc = new StringTokenizer(linea, ",");
                    while(nc.hasMoreTokens()){
                        if (contt == 1) {
                            platoMVT = nc.nextToken();
                            platoMVT = platoMVT.substring(0, platoMVT.length()); 
                        }else{
                            if (contt == 2) {
                                nc.nextToken();
                            }else{
                                if (contt == 3) {
                                    cantT = nc.nextToken();
                                    cantT = cantT.substring(1, cantT.length());
                                    cant = Integer.parseInt(cantT);
                                    if (mayor< cant) {
                                        mayor = cant;
                                        platoMV = platoMVT;
                                    }

                                }
                            }
                        }
                        contt++;
                    }
                }
                if (platoMV == null) {
                    JOptionPane.showMessageDialog(null, "Todos los platos se han vendido la misma cantidad", "Ventas de platos", INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "El plato mas vendido fue "+ platoMV + "\nSe vendieron "+ mayor+ " porciones", "Ventas de platos", INFORMATION_MESSAGE);
                }
            }
        }catch(FileNotFoundException e){
        } catch (IOException ex) {
            Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void RESUMENVENTASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESUMENVENTASActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_RESUMENVENTASActionPerformed

    private void RVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RVMActionPerformed
        // TODO add your handling code here:
        RESUMENVENTAS.setEditable(true);
        RESUMENVENTAS.setFocusable(true);
        RESUMENVENTAS.setText("");
    }//GEN-LAST:event_RVMActionPerformed

    private void TVDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TVDActionPerformed
        // TODO add your handling code here:
        RESUMENVENTAS.setEditable(false);
        RESUMENVENTAS.setText("Digite aqui el número de la mesa...");
    }//GEN-LAST:event_TVDActionPerformed

    private void PMVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMVActionPerformed
        // TODO add your handling code here:
        RESUMENVENTAS.setEditable(false);
        RESUMENVENTAS.setText("Digite aqui el número de la mesa...");
    }//GEN-LAST:event_PMVActionPerformed

    private void VCFRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VCFRActionPerformed
        // TODO add your handling code here:
        RESUMENVENTAS.setEditable(false);
        RESUMENVENTAS.setText("Digite aqui el número de la mesa...");
    }//GEN-LAST:event_VCFRActionPerformed

    private void MMVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MMVActionPerformed
        // TODO add your handling code here:
        RESUMENVENTAS.setEditable(false);
        RESUMENVENTAS.setText("Digite aqui el número de la mesa...");
    }//GEN-LAST:event_MMVActionPerformed
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Automaticamente pone la primera letra del campo de texto en mayuscula">
    /**
     * Recibe como parámetro una variable de tipo {String} a la cual se le cambiará su primera letra por una mayúscula.
     * @param Texto Recibe la cadena a la cual se le cambiará la primera letra por mayúscula.
     * @return Regresa la cadena con mayúscula inicial. Ej: Entra miguel, retorna Miguel.
     */
    public String MayusInicial(String Texto){

        if(Texto.length() > 0){
                char pl = Texto.charAt(0);
                Texto = Character.toUpperCase(pl) + Texto.substring(1, Texto.length());
            }
        return Texto;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Subrutinas <Generar>, una con parámetros y otra sin parámetros">
    /**
     * Subrutina {Generar} que no recibe parámetros, y su función es como su nombre lo dice
     * generar los meseros 1 por uno, es la utilizada cuando no hay ningún archivo creado.
     * @since Segunda Entrega.
     */
    public void Generar(){
        String NOMBREC = TNOMMES1.getText() + (" ") + (TAPEMES1.getText());
        if(ptrMesero == null){
            ptrMesero = new Mesero(Integer.parseInt(TCCMES1.getText()), NOMBREC, cont);
        }else{
            pMesero = ptrMesero;
            qMesero = new Mesero(Integer.parseInt(TCCMES1.getText()), NOMBREC, cont);
            while(pMesero.linkMesero != null){
                pMesero = pMesero.linkMesero;
            }
            pMesero.linkMesero = qMesero;
        }
        cont = cont + 5;
        mc++;
        TNOMMES1.setText("");
        TAPEMES1.setText("");
        TCCMES1.setText("");
        if(mc != 4){
            PMES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MESERO #"
                + (mc + 1), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        }
       
    }
    /**
     * Esta subrutina al igual que la que lleva su mismo nombre genera a los meseros
     * con la única diferencia de que esta es utilizada para crearlos utilizando el archivo
     * {Meseros} existente utilizando los parametros {nombre} y {cedula}.
     * @param nombre Recibe el nombre y lo utiliza como uno de los parámetros para crear el nodo del mesero.
     * @param cedula Recibe la cedula y la utiliza como otro de los parámetros para crear el nodo del mesero.
     * @since Segunda Entrega.
     */
    public void Generar(String nombre, int cedula){
        if(ptrMesero == null){
            ptrMesero = new Mesero(cedula, nombre, cont);
        }else{
            pMesero = ptrMesero;
            qMesero = new Mesero(cedula, nombre, cont);
            while(pMesero.linkMesero != null){
                pMesero = pMesero.linkMesero;
            }
            pMesero.linkMesero = qMesero;
        }
        cont = cont + 5;
        mc++;
    }
    //</editor-fold>
    
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
            java.util.logging.Logger.getLogger(Registro_Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro_Mesero().setVisible(true);
            }
        });
    }
    //<editor-fold defaultstate="collapsed" desc="Variables creadas">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel APELLIDO;
    private javax.swing.JTextField APELLIDOT;
    private javax.swing.JLabel APEMES1;
    private javax.swing.JButton AYUDA;
    private javax.swing.JLabel BIENVENIDO;
    private javax.swing.JLabel CCMES1;
    private javax.swing.JLabel CEDULA;
    private javax.swing.JTextField CEDULAT;
    private javax.swing.JFrame Factura;
    private javax.swing.JFrame Facturacion;
    private javax.swing.JButton GENERAR;
    private javax.swing.ButtonGroup Gerent;
    private javax.swing.JFrame Gerente;
    private javax.swing.JFrame JefeCocina;
    private javax.swing.JTable JefeTable;
    private javax.swing.JLabel MENSAJEV;
    private javax.swing.JRadioButton MMV;
    private javax.swing.JLabel NOMBRE;
    private javax.swing.JTextField NOMBRET;
    private javax.swing.JLabel NOMMES1;
    private javax.swing.JPanel PMES;
    private javax.swing.JRadioButton PMV;
    private javax.swing.JButton RESERVAR;
    private javax.swing.JTextField RESUMENVENTAS;
    private javax.swing.JRadioButton RVM;
    private javax.swing.JFrame Recepcion;
    private javax.swing.JTextField TAPEMES1;
    private javax.swing.JTextField TCCMES1;
    private javax.swing.JTextField TNOMMES1;
    private javax.swing.JRadioButton TVD;
    private javax.swing.JRadioButton VCFR;
    private javax.swing.JButton comprobar;
    private javax.swing.JPanel facturaPanel;
    private javax.swing.JButton generarFactura;
    private javax.swing.JLabel hint;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JList<String> listaMesas;
    private javax.swing.JList<String> listaMesasFacturacion;
    private javax.swing.JList<String> listaMeseros;
    private javax.swing.JLabel listamesas;
    private javax.swing.JLabel listameseros;
    private javax.swing.JLabel nombreFacturacion;
    private javax.swing.JButton pagarButton;
    private javax.swing.JTable platosTable;
    private javax.swing.JTextArea textoFactura1;
    private javax.swing.JTextArea textoFactura2;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}