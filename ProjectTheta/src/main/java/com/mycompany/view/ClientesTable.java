/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.controller.ClienteController;
import com.mycompany.model.entities.Cliente;
import com.mycompany.services.ClienteService;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bravo
 */
public class ClientesTable extends javax.swing.JFrame {

    ClienteController cController = new ClienteController(new ClienteService());
    DefaultTableModel dtmClientes = new DefaultTableModel();

    private JFrame previousFrame;

    /**
     * Creates new form ClientesTable
     */
    public ClientesTable() {
        initComponents();
        loadCboClientes();
        loadColumns();
        loadRows();
    }

    public void loadColumns() {
        dtmClientes.addColumn("Id");
        dtmClientes.addColumn("Nombre");
        dtmClientes.addColumn("Apellido");
        dtmClientes.addColumn("Dni");
        dtmClientes.addColumn("Teléfono");
        dtmClientes.addColumn("Dirección");
        tbClientes.setModel(dtmClientes);
    }

    public void loadRows() {
        dtmClientes.setRowCount(0);
        for (Cliente c : cController.getEmployees()) {
            Object[] vec = new Object[6];
            vec[0] = c.getIdCliente();
            vec[1] = c.getNombre();
            vec[2] = c.getApellido();
            vec[3] = c.getDni();
            vec[4] = c.getTelefono();
            vec[5] = c.getDireccion();
            dtmClientes.addRow(vec);
        }
        tbClientes.setModel(dtmClientes);
    }
    
    public void loadCboClientes(){
        cboClientes.removeAllItems();
        cboClientes.addItem("[0] - All");
        for(Cliente c : cController.getEmployees())
            cboClientes.addItem("[" + c.getIdCliente() + "] - " + c.getNombre() + " " + c.getApellido());
    }
    
    public void loadCboEditCliente(){
        cboEditCliente.removeAllItems();
        for(Cliente c : cController.getEmployees())
            cboEditCliente.addItem("[" + c.getIdCliente() + "] - " + c.getNombre() + " " + c.getApellido());
    }

    public JFrame getPreviousFrame() {
        return previousFrame;
    }

    public void setPreviousFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NewForm = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        txtNewNombre = new javax.swing.JTextField();
        btnNewSave = new javax.swing.JButton();
        btnNewRestore = new javax.swing.JButton();
        btnNewCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNewApellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNewDNI = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNewTelefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNewDireccion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        EditForm = new javax.swing.JFrame();
        btnEditSave = new javax.swing.JButton();
        btnEditRestore = new javax.swing.JButton();
        btnEditCancel = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboEditCliente = new javax.swing.JComboBox<>();
        txtEditDireccion = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEditApellido = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtEditDNI = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtEditTelefono = new javax.swing.JTextField();
        txtEditNombre = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnNewCliente = new javax.swing.JButton();
        btnEditCliente = new javax.swing.JButton();
        btnDeleteCliente = new javax.swing.JButton();
        cboClientes = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        NewForm.setSize(new java.awt.Dimension(300, 350));

        btnNewSave.setText("Guardar Cliente");
        btnNewSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewSaveActionPerformed(evt);
            }
        });

        btnNewRestore.setText("Restablecer");
        btnNewRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRestoreActionPerformed(evt);
            }
        });

        btnNewCancel.setText("Cancelar");
        btnNewCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCancelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NUEVO CLIENTE");

        jLabel5.setText("Nombre");

        jLabel8.setText("Apellido");

        jLabel9.setText("DNI");

        jLabel10.setText("Teléfono");

        jLabel11.setText("Dirección");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(btnNewRestore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNewNombre)
                            .addComponent(txtNewApellido)
                            .addComponent(txtNewDNI)
                            .addComponent(txtNewTelefono)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewDireccion)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNewNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNewApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNewDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNewTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNewDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewRestore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout NewFormLayout = new javax.swing.GroupLayout(NewForm.getContentPane());
        NewForm.getContentPane().setLayout(NewFormLayout);
        NewFormLayout.setHorizontalGroup(
            NewFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        NewFormLayout.setVerticalGroup(
            NewFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        EditForm.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        EditForm.setSize(new java.awt.Dimension(300, 400));

        btnEditSave.setText("Actualizar Cliente");
        btnEditSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSaveActionPerformed(evt);
            }
        });

        btnEditRestore.setText("Restablecer");
        btnEditRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditRestoreActionPerformed(evt);
            }
        });

        btnEditCancel.setText("Cancelar");
        btnEditCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCancelActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("MODIFICAR CLIENTE");

        jLabel15.setText("Clientes");

        cboEditCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEditClienteActionPerformed(evt);
            }
        });

        jLabel17.setText("Dirección");

        jLabel6.setText("Nombre");

        jLabel18.setText("Apellido");

        jLabel19.setText("DNI");

        jLabel20.setText("Teléfono");

        javax.swing.GroupLayout EditFormLayout = new javax.swing.GroupLayout(EditForm.getContentPane());
        EditForm.getContentPane().setLayout(EditFormLayout);
        EditFormLayout.setHorizontalGroup(
            EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(btnEditSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditRestore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(EditFormLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboEditCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(EditFormLayout.createSequentialGroup()
                        .addGroup(EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEditNombre)
                            .addComponent(txtEditApellido)
                            .addComponent(txtEditDNI)
                            .addComponent(txtEditTelefono)))
                    .addGroup(EditFormLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEditDireccion)))
                .addContainerGap())
        );
        EditFormLayout.setVerticalGroup(
            EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(4, 4, 4)
                .addGroup(EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cboEditCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEditNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtEditApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtEditDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtEditTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtEditDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditRestore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        tbClientes.setAutoCreateRowSorter(true);
        tbClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbClientes);

        txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarCaretUpdate(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CLIENTES");

        btnNewCliente.setText("Registrar Nuevo");
        btnNewCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewClienteActionPerformed(evt);
            }
        });

        btnEditCliente.setText("Modificar Existente");
        btnEditCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditClienteActionPerformed(evt);
            }
        });

        btnDeleteCliente.setText("Eliminar");
        btnDeleteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClienteActionPerformed(evt);
            }
        });

        cboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboClientesActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar Cliente");

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscar)
                                    .addComponent(cboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNewCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewCliente)
                    .addComponent(btnEditCliente)
                    .addComponent(btnDeleteCliente)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSaveActionPerformed
        // TODO add your handling code here:
        if(getNewNombre().isBlank()||getNewApellido().isBlank()||getNewDni().isBlank()||getNewTelefono().isBlank()||getNewDireccion().isBlank()){
            JOptionPane.showMessageDialog(this, "Error, Valores Inválidos", "Datos sin llenar", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(datosRegistrados(getNewDni(), getNewTelefono())) { return; }
        int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea generar el nuevo cliente?");
        if(resp!=0){ return; }
        Cliente temp = new Cliente(0, getNewNombre(), getNewApellido(), getNewDni(), getNewTelefono(), getNewDireccion());
        cController.addCustomer(temp);
    }//GEN-LAST:event_btnNewSaveActionPerformed

    private void btnNewRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRestoreActionPerformed
        // TODO add your handling code here:
        txtNewNombre.setText("");
        txtNewApellido.setText("");
        txtNewDNI.setText("");
        txtNewTelefono.setText("");
        txtNewDireccion.setText("");
    }//GEN-LAST:event_btnNewRestoreActionPerformed

    private void btnNewCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCancelActionPerformed
        // TODO add your handling code here:
        loadRows();
        loadCboClientes();
        NewForm.dispose();
    }//GEN-LAST:event_btnNewCancelActionPerformed

    private void btnNewClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewClienteActionPerformed
        // TODO add your handling code here:
        NewForm.setVisible(true);
    }//GEN-LAST:event_btnNewClienteActionPerformed

    private void btnEditClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditClienteActionPerformed
        // TODO add your handling code here:
        if(cController.getEmployees().isEmpty()){
            JOptionPane.showMessageDialog(this, "Aún no hay clientes registrados", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        loadCboEditCliente();
        EditForm.setVisible(true);
    }//GEN-LAST:event_btnEditClienteActionPerformed

    private void btnEditSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSaveActionPerformed
        // TODO add your handling code here:
        if(getEditNombre().isBlank()||getEditApellido().isBlank()||getEditDni().isBlank()||getEditTelefono().isBlank()||getEditDireccion().isBlank())
        {
            JOptionPane.showMessageDialog(this, "Error, Valores Inválidos", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(datosRegistrados(getEditDni(), getEditTelefono())) { return; }
        int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea editar el Cliente? Esta acción es Irreversible");
        if (resp != 0) { return; }
        Cliente temp = new Cliente(getSelectedClienteEdit(), getEditNombre(), getEditApellido(), getEditDni(), getEditTelefono(), getEditDireccion());
        cController.updateCustomer(temp);
    }//GEN-LAST:event_btnEditSaveActionPerformed

    private void btnEditRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditRestoreActionPerformed
        // TODO add your handling code here:
        llenarDatosEdit();
    }//GEN-LAST:event_btnEditRestoreActionPerformed

    private void btnEditCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCancelActionPerformed
        // TODO add your handling code here:
        loadRows();
        loadCboClientes();
        EditForm.dispose();
    }//GEN-LAST:event_btnEditCancelActionPerformed

    private void cboEditClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEditClienteActionPerformed
        // TODO add your handling code here:
        llenarDatosEdit();
    }//GEN-LAST:event_cboEditClienteActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        // TODO add your handling code here:
        DefaultComboBoxModel<String> dcbmClientes = new DefaultComboBoxModel<>();
        loadCboClientes();
        String busqueda = txtBuscar.getText();
        for(int i=0;i<cboClientes.getItemCount();i++){
            String item = cboClientes.getItemAt(i);
            if(item.toLowerCase().contains(busqueda.toLowerCase())){
                dcbmClientes.addElement(item);
            }
        }
        cboClientes.setModel(dcbmClientes);
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void cboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClientesActionPerformed
        // TODO add your handling code here:
        if(getSelectedClienteId()==0) { loadRows(); }
        else{
            Cliente temp = cController.findCustomerById(getSelectedClienteId());
            dtmClientes.setRowCount(0);
            Object[] vec = new Object[6];
            vec[0] = temp.getIdCliente();
            vec[1] = temp.getNombre();
            vec[2] = temp.getApellido();
            vec[3] = temp.getDni();
            vec[4] = temp.getTelefono();
            vec[5] = temp.getDireccion();
            dtmClientes.addRow(vec);
            tbClientes.setModel(dtmClientes);
        }
    }//GEN-LAST:event_cboClientesActionPerformed

    private void btnDeleteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClienteActionPerformed
        // TODO add your handling code here:
        Cliente temp = cController.findCustomerById(getSelectedClienteId());
        if(getSelectedClienteId()==0)
        {
            JOptionPane.showMessageDialog(this, "Error, aun no selecciona un cliente", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar al Cliente "+temp.getNombre()+" "+temp.getApellido()+"? Esta acción es Irreversible");
        if (resp != 0) { return; }
        cController.deleteCustomer(temp);
        loadRows();
        loadCboClientes();
    }//GEN-LAST:event_btnDeleteClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        previousFrame.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private String getNewNombre()    { return txtNewNombre.getText();    }
    private String getNewApellido()  { return txtNewApellido.getText();  }
    private String getNewDni()       { return txtNewDNI.getText();       }
    private String getNewTelefono()  { return txtNewTelefono.getText();  }
    private String getNewDireccion() { return txtNewDireccion.getText(); }
    
    private String getEditNombre()   { return txtEditNombre.getText();   }
    private String getEditApellido() { return txtEditApellido.getText(); }
    private String getEditDni()      { return txtEditDNI.getText();      }
    private String getEditTelefono() { return txtEditTelefono.getText(); }
    private String getEditDireccion(){ return txtEditDireccion.getText();}
    
    private int getSelectedClienteEdit(){
        if(cboEditCliente.getItemCount()==0) { return 1; }
        String  palabra = cboEditCliente.getSelectedItem().toString();
        int     indice  = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }
    private boolean datosRegistrados(String dni, String telefono){
        if(cController.dniExist(dni)){
            JOptionPane.showMessageDialog(this, "Error, El dni ingresado ya se encuentra registrado", "Dato ya registrado", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(cController.telefonoExist(telefono)){
            JOptionPane.showMessageDialog(this, "Error, El telefono ingresado ya se encuentra registrado", "Dato ya registrado", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }
    private int getSelectedClienteId(){
        if(cboClientes.getItemCount()==0){ return 0; }
        String  palabra = cboClientes.getSelectedItem().toString();
        int     indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }
    private void llenarDatosEdit(){
        Cliente temp = cController.findCustomerById(getSelectedClienteEdit());
        txtEditNombre.setText(temp.getNombre());
        txtEditApellido.setText(temp.getApellido());
        txtEditDNI.setText(temp.getDni());
        txtEditTelefono.setText(temp.getTelefono());
        txtEditDireccion.setText(temp.getDireccion());
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
            java.util.logging.Logger.getLogger(ClientesTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientesTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame EditForm;
    private javax.swing.JFrame NewForm;
    private javax.swing.JButton btnDeleteCliente;
    private javax.swing.JButton btnEditCancel;
    private javax.swing.JButton btnEditCliente;
    private javax.swing.JButton btnEditRestore;
    private javax.swing.JButton btnEditSave;
    private javax.swing.JButton btnNewCancel;
    private javax.swing.JButton btnNewCliente;
    private javax.swing.JButton btnNewRestore;
    private javax.swing.JButton btnNewSave;
    private javax.swing.JComboBox<String> cboClientes;
    private javax.swing.JComboBox<String> cboEditCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtEditApellido;
    private javax.swing.JTextField txtEditDNI;
    private javax.swing.JTextField txtEditDireccion;
    private javax.swing.JTextField txtEditNombre;
    private javax.swing.JTextField txtEditTelefono;
    private javax.swing.JTextField txtNewApellido;
    private javax.swing.JTextField txtNewDNI;
    private javax.swing.JTextField txtNewDireccion;
    private javax.swing.JTextField txtNewNombre;
    private javax.swing.JTextField txtNewTelefono;
    // End of variables declaration//GEN-END:variables
}
