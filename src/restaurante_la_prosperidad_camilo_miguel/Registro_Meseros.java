
package restaurante_la_prosperidad_camilo_miguel;

import javax.swing.*;
import static javax.swing.JOptionPane.*;


public class Registro_Meseros extends javax.swing.JFrame {
    Menú menu = new Menú();
    Mesero ptrMesero;
    int mc = 0, cont = 1;
    Mesero pMesero, qMesero;
    Mesa pMesa;
    
    public Registro_Meseros() {
        initComponents();
        ptrMesero = null;
        
    }
    
    //<editor-fold defaultstate="collapsed" desc="Genera la lista con los meseros y de mesas">
    public void listaMeseros(){
        DefaultListModel model = new DefaultListModel();
        pMesero = ptrMesero;
        
        while(pMesero != null){
            model.addElement(pMesero.nombre);
            pMesero = pMesero.linkMesero;
        }
        
        listameseros.setModel(model);
        
    }
    
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
        NOMBRET = new javax.swing.JTextField();
        APELLIDO = new javax.swing.JLabel();
        APELLIDOT = new javax.swing.JTextField();
        CEDULA = new javax.swing.JLabel();
        CEDULAT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaMesas = new javax.swing.JList<>();
        RESERVAR = new javax.swing.JButton();
        hint = new javax.swing.JLabel();
        listame = new javax.swing.JLabel();
        AYUDA = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listameseros = new javax.swing.JList<>();
        listames = new javax.swing.JLabel();
        comprobar = new javax.swing.JButton();
        Gerente = new javax.swing.JFrame();
        BIENVENIDO = new javax.swing.JLabel();
        MENSAJEV = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        TRESUMENVENTAS = new javax.swing.JTextField();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
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

        NOMBRET.setFocusTraversalPolicyProvider(true);
        NOMBRET.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NOMBRETKeyTyped(evt);
            }
        });

        APELLIDO.setText("APELLIDO: ");

        APELLIDOT.setFocusTraversalPolicyProvider(true);
        APELLIDOT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                APELLIDOTKeyTyped(evt);
            }
        });

        CEDULA.setText("C.C:");

        CEDULAT.setFocusTraversalPolicyProvider(true);
        CEDULAT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CEDULATKeyTyped(evt);
            }
        });

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

        listame.setForeground(new java.awt.Color(0, 204, 102));
        listame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listame.setText("MESAS DISPONIBLES:");

        AYUDA.setText("?");
        AYUDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AYUDAActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(listameseros);

        listames.setForeground(new java.awt.Color(0, 204, 102));
        listames.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listames.setText("MESEROS:");

        comprobar.setText("COMPROBAR");
        comprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprobarActionPerformed(evt);
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
                                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(APELLIDOT, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                            .addComponent(NOMBRET)
                                            .addComponent(CEDULAT))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AYUDA, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 6, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(RecepcionLayout.createSequentialGroup()
                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(listame, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RecepcionLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(RESERVAR)
                                .addGap(18, 18, 18)
                                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(listames, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
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
                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NOMBRE)
                    .addComponent(NOMBRET, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(APELLIDO)
                    .addComponent(APELLIDOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AYUDA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CEDULA)
                    .addComponent(CEDULAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RecepcionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(listames)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RESERVAR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comprobar))
                    .addGroup(RecepcionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(listame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Gerente.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Gerente.setTitle("OPCIONES AVANZADAS");
        Gerente.setMinimumSize(new java.awt.Dimension(500, 300));
        Gerente.setSize(new java.awt.Dimension(500, 300));
        Gerente.getContentPane().setLayout(null);

        BIENVENIDO.setText("¡BIENVENIDO SEÑOR GERENTE!");
        Gerente.getContentPane().add(BIENVENIDO);
        BIENVENIDO.setBounds(130, 10, 290, 14);

        MENSAJEV.setText("¿QUÉ DESEA REALIZAR?");
        Gerente.getContentPane().add(MENSAJEV);
        MENSAJEV.setBounds(34, 36, 240, 14);

        jRadioButton1.setText("RESUMEN DE VENTAS EN MESA. [ X ]");
        Gerente.getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(6, 73, 240, 23);

        jRadioButton2.setText("TOTAL DE LAS VENTAS DEL DÍA.");
        Gerente.getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(6, 101, 270, 23);

        jRadioButton3.setText("PLATO MAS VENDIDO.");
        Gerente.getContentPane().add(jRadioButton3);
        jRadioButton3.setBounds(6, 127, 270, 23);

        TRESUMENVENTAS.setText("Digite aqui el número de la mesa...");
        TRESUMENVENTAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TRESUMENVENTASMouseClicked(evt);
            }
        });
        Gerente.getContentPane().add(TRESUMENVENTAS);
        TRESUMENVENTAS.setBounds(250, 70, 220, 30);

        jRadioButton4.setText("VENTAS SIN CONCRETAR POR FALTA DE RECURSOS.");
        Gerente.getContentPane().add(jRadioButton4);
        jRadioButton4.setBounds(6, 153, 410, 23);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("¿QUÉ MESA DESEA VER?");
        Gerente.getContentPane().add(jLabel6);
        jLabel6.setBounds(290, 50, 175, 14);

        jRadioButton5.setText("NOMBRE DEL MESERO CON MAYOR NUMERO DE VENTAS.");
        Gerente.getContentPane().add(jRadioButton5);
        jRadioButton5.setBounds(6, 179, 440, 23);

        jButton1.setText("CONTINUAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Gerente.getContentPane().add(jButton1);
        jButton1.setBounds(200, 210, 130, 23);

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
    int nape = 1;
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
    int ncc = 1;
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
        /*String Compro = NOMBRET.getText();
        if(Compro.charAt(0) == '!'){
            Gerente.setVisible(true);
        }*/
    }//GEN-LAST:event_AYUDAActionPerformed

    private void TRESUMENVENTASMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TRESUMENVENTASMouseClicked
        TRESUMENVENTAS.setText("");
    }//GEN-LAST:event_TRESUMENVENTASMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprobarActionPerformed
        int p = listameseros.getSelectedIndex();
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
    }//GEN-LAST:event_comprobarActionPerformed

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
                        ReservarMesa(nTb);
                        listaMesas();
                    }
                }
            }
        }
    }//GEN-LAST:event_RESERVARActionPerformed


    public void ReservarMesa(int p){
        pMesero = ptrMesero;
        boolean sw = true;
        while(pMesero != null && sw == true) {
            pMesa = pMesero.ptrMesa;
            while(pMesa.link != null && sw == true){
                if (p == pMesa.mesan) {
                    sw = false;
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
    
    //<editor-fold defaultstate="collapsed" desc="Comprobantes al presionar teclas">
    
    private void NOMBRETKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NOMBRETKeyTyped
        char CR = evt.getKeyChar();
        if((!Character.isLetter(CR) || Character.isDigit(CR))) { 
            evt.consume();
        }else{
            NOMBRET.setText(MayusInicial(NOMBRET.getText()));
        }
        
    }//GEN-LAST:event_NOMBRETKeyTyped

    private void APELLIDOTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_APELLIDOTKeyTyped
        char CR = evt.getKeyChar();
        if((!Character.isLetter(CR) || Character.isDigit(CR))) { 
            evt.consume();
        }else{
            APELLIDOT.setText(MayusInicial(APELLIDOT.getText()));
        }
    }//GEN-LAST:event_APELLIDOTKeyTyped
    int ct = 1;
    private void CEDULATKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CEDULATKeyTyped
        char CR = evt.getKeyChar();
        String R = "\b", cr = "" + CR;
        if(cr.equals(R) && ct > 0){
            ct--;
        }else{
            if((Character.isLetter(CR) || !Character.isDigit(CR))) { 
                evt.consume();
            }else{

                if(ct > 10){
                    evt.consume();
                    JOptionPane.showMessageDialog(null, "El número de identificación no puede tener mas de 10 digitos.", "DEMASIADOS NÚMEROS", ERROR_MESSAGE);
                }else{
                    ct++;
                }
            }
        }
    }//GEN-LAST:event_CEDULATKeyTyped

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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Automaticamente pone la primera letra del campo de texto en mayuscula">
    public String MayusInicial(String Texto){
        if(Texto.length() > 0){
                char pl = Texto.charAt(0);
                Texto = Character.toUpperCase(pl) + Texto.substring(1, Texto.length());
            }
        return Texto;
    }
    //</editor-fold>
    
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
            java.util.logging.Logger.getLogger(Registro_Meseros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_Meseros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_Meseros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_Meseros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro_Meseros().setVisible(true);
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
    private javax.swing.JButton GENERAR;
    private javax.swing.JFrame Gerente;
    private javax.swing.JLabel MENSAJEV;
    private javax.swing.JLabel NOMBRE;
    private javax.swing.JTextField NOMBRET;
    private javax.swing.JLabel NOMMES1;
    private javax.swing.JPanel PMES;
    private javax.swing.JButton RESERVAR;
    private javax.swing.JFrame Recepcion;
    private javax.swing.JTextField TAPEMES1;
    private javax.swing.JTextField TCCMES1;
    private javax.swing.JTextField TNOMMES1;
    private javax.swing.JTextField TRESUMENVENTAS;
    private javax.swing.JButton comprobar;
    private javax.swing.JLabel hint;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaMesas;
    private javax.swing.JLabel listame;
    private javax.swing.JLabel listames;
    private javax.swing.JList<String> listameseros;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
