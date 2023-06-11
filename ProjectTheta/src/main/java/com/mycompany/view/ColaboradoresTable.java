/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.controller.PersonalController;
import com.mycompany.model.entities.Personal;
import com.mycompany.model.generics.Sha256;
import com.mycompany.model.generics.TimeAndDates;
import com.mycompany.services.PersonalService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author bravo
 */
public class ColaboradoresTable extends javax.swing.JFrame {
    PersonalController pController = new PersonalController(new PersonalService());
    DefaultTableModel dtmColaboradores = new DefaultTableModel();
    private JFrame previousFrame;

    /**
     * Creates new form ColaboradoresTable
     */
    public ColaboradoresTable() {
        initComponents();
        loadCboDias();
        loadCboNewCargo();
        loadCboPersonal();
        loadColumns();
        loadRows();
    }

    public void loadColumns() {
        dtmColaboradores.setColumnCount(0);
        dtmColaboradores.addColumn("id");
        dtmColaboradores.addColumn("Nombre");
        dtmColaboradores.addColumn("Apellido");
        dtmColaboradores.addColumn("Usuario");
        dtmColaboradores.addColumn("Teléfono");
        dtmColaboradores.addColumn("Hora de Inicio");
        dtmColaboradores.addColumn("Hora de salida");
        dtmColaboradores.addColumn("Dia descanso");
        dtmColaboradores.addColumn("Cargo");
        tbColaboradores.setModel(dtmColaboradores);
    }

    public void loadRows() {
        List<Personal> lst = pController.getEmployees();
        dtmColaboradores.setRowCount(0);
        try {
            for (Personal p : lst) {
                Object[] vec = new Object[9];
                vec[0] = p.getIdPersonal();
                vec[1] = p.getNombre();
                vec[2] = p.getApellidos();
                vec[3] = p.getUsuario();
                vec[4] = p.getTelefono();
                vec[5] = p.getHoraInicio();
                vec[6] = p.getHoraFin();
                vec[7] = p.getDiaDescanso();
                vec[8] = p.getNombreCargo();
                dtmColaboradores.addRow(vec);
            }
            tbColaboradores.setModel(dtmColaboradores);
        TableColumnModel tcm= tbColaboradores.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(20);
        } catch (Exception ex) {
            System.out.println("Error");
        }
        tbColaboradores.setModel(dtmColaboradores);
    }

    public void loadCboDias() {

        DefaultComboBoxModel dcbmDiasSemana = new DefaultComboBoxModel();
        DefaultComboBoxModel dcbmDiasSemana2 = new DefaultComboBoxModel();

        List<String> lstStr = new ArrayList<>();

        lstStr.add(TimeAndDates.DAYS_WEEK.Lunes.toString());
        lstStr.add(TimeAndDates.DAYS_WEEK.Martes.toString());
        lstStr.add(TimeAndDates.DAYS_WEEK.Miercoles.toString());
        lstStr.add(TimeAndDates.DAYS_WEEK.Jueves.toString());
        lstStr.add(TimeAndDates.DAYS_WEEK.Viernes.toString());
        lstStr.add(TimeAndDates.DAYS_WEEK.Sabado.toString());
        lstStr.add(TimeAndDates.DAYS_WEEK.Domingo.toString());

        dcbmDiasSemana.removeAllElements();
        dcbmDiasSemana2.removeAllElements();

        dcbmDiasSemana.addAll(lstStr);
        dcbmDiasSemana2.addAll(lstStr);

        cboDiaDescanso.setModel(dcbmDiasSemana);
        cboEditDiaDescanso.setModel(dcbmDiasSemana2);
    }

    public void loadCboPersonal() {
        List<Personal> lst = pController.getEmployees();
        DefaultComboBoxModel dcbmPersonal = new DefaultComboBoxModel();
        dcbmPersonal.removeAllElements();
        dcbmPersonal.addElement("[0] - All");
        for (Personal p : lst) {
            dcbmPersonal.addElement("[" + p.getIdPersonal() + "] - " + p.getNombre() + " " + p.getApellidos());
        }

        cboPersonal.setModel(dcbmPersonal);
        cboEditPersonal.setModel(dcbmPersonal);
    }

    public void loadCboNewCargo() {
        DefaultComboBoxModel dcbmCargo = new DefaultComboBoxModel();
        DefaultComboBoxModel dcbmCargo2 = new DefaultComboBoxModel();

        dcbmCargo.removeAllElements();
        dcbmCargo.addElement(Personal.CARGOS.ADMIN.toString());
        dcbmCargo.addElement(Personal.CARGOS.EMPLEADO.toString());

        dcbmCargo2.removeAllElements();
        dcbmCargo2.addElement(Personal.CARGOS.ADMIN.toString());
        dcbmCargo2.addElement(Personal.CARGOS.EMPLEADO.toString());

        cboCargo.setModel(dcbmCargo);
        cboEditCargo.setModel(dcbmCargo2);
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
        btnNewSave = new javax.swing.JButton();
        btnNewRestore = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnNewCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNewApellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNewUsuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNewNombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNewContrasena = new javax.swing.JPasswordField();
        txtNewConfiContrasena = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtNewTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNewHoraInicio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNewHoraFin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cboCargo = new javax.swing.JComboBox<>();
        cboDiaDescanso = new javax.swing.JComboBox<>();
        EditForm = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        cboEditCargo = new javax.swing.JComboBox<>();
        txtEditTelefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtEditApellido = new javax.swing.JTextField();
        txtEditHoraInicio = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtEditUsuario = new javax.swing.JTextField();
        txtEditHoraFin = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtEditNombre = new javax.swing.JTextField();
        btnUpdatePersonal = new javax.swing.JButton();
        cboEditDiaDescanso = new javax.swing.JComboBox<>();
        btnEditRestore = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        btnEditCancel = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cboEditPersonal = new javax.swing.JComboBox<>();
        btnCambiarContrasena = new javax.swing.JButton();
        EditContrasenaForm = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtAnteriorContrasena = new javax.swing.JPasswordField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtEditContrasena = new javax.swing.JPasswordField();
        jLabel27 = new javax.swing.JLabel();
        txtEditConfiContrasena = new javax.swing.JPasswordField();
        btnEditSaveContrasena = new javax.swing.JButton();
        btnEditCancelContrasena = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbColaboradores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnNewPersonal = new javax.swing.JButton();
        btnEditPersonal = new javax.swing.JButton();
        btnDeletePersonal = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        cboPersonal = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        NewForm.setPreferredSize(new java.awt.Dimension(300, 513));
        NewForm.setSize(new java.awt.Dimension(300, 550));

        btnNewSave.setText("Guardar Colaborador");
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

        jLabel11.setText("Confirmar");

        btnNewCancel.setText("Cancelar");
        btnNewCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCancelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NUEVO COLABORADOR");

        jLabel5.setText("Nombre");

        jLabel8.setText("Apellidos");

        jLabel9.setText("Usuario");

        jLabel10.setText("Contraseña");

        jLabel3.setText("Telefono");

        jLabel6.setText("Hora-Inicio");

        jLabel7.setText("Hora-Fin");

        jLabel12.setText("Dia Descanso");

        jLabel13.setText("Cargo");

        cboCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "EMPLEADO" }));

        cboDiaDescanso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(btnNewRestore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNewTelefono, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNewHoraInicio)
                            .addComponent(txtNewHoraFin)
                            .addComponent(txtNewConfiContrasena)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDiaDescanso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNewApellido, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNewUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNewContrasena)
                            .addComponent(txtNewNombre)
                            .addComponent(cboCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(txtNewUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNewContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNewConfiContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNewTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNewHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNewHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cboDiaDescanso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewRestore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewCancel)
                .addContainerGap(120, Short.MAX_VALUE))
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

        EditForm.setSize(new java.awt.Dimension(300, 550));

        cboEditCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "EMPLEADO" }));

        jLabel14.setText("Nombre");

        jLabel15.setText("Hora-Inicio");

        jLabel16.setText("Apellidos");

        jLabel17.setText("Hora-Fin");

        jLabel18.setText("Usuario");

        jLabel19.setText("Dia Descanso");

        btnUpdatePersonal.setText("Actualizar Colaborador");
        btnUpdatePersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePersonalActionPerformed(evt);
            }
        });

        cboEditDiaDescanso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES" }));

        btnEditRestore.setText("Restablecer");
        btnEditRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditRestoreActionPerformed(evt);
            }
        });

        jLabel22.setText("Cargo");

        btnEditCancel.setText("Cancelar");
        btnEditCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCancelActionPerformed(evt);
            }
        });

        jLabel23.setText("Telefono");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("MODIFICAR COLABORADOR");

        jLabel25.setText("Colaborador");

        cboEditPersonal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboEditPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEditPersonalActionPerformed(evt);
            }
        });

        btnCambiarContrasena.setText("Cambiar Contraseña");
        btnCambiarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarContrasenaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(6, 6, 6))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(16, 16, 16)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboEditPersonal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEditUsuario)
                            .addComponent(txtEditApellido)
                            .addComponent(txtEditNombre)
                            .addComponent(cboEditCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnUpdatePersonal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(btnEditRestore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboEditDiaDescanso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEditTelefono, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEditHoraInicio)
                            .addComponent(txtEditHoraFin)))
                    .addComponent(btnCambiarContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboEditPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cboEditCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtEditNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtEditApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtEditUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtEditTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtEditHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtEditHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cboEditDiaDescanso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdatePersonal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditRestore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCambiarContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditCancel)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EditFormLayout = new javax.swing.GroupLayout(EditForm.getContentPane());
        EditForm.getContentPane().setLayout(EditFormLayout);
        EditFormLayout.setHorizontalGroup(
            EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        EditFormLayout.setVerticalGroup(
            EditFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("MODIFICAR CONTRASEÑA");

        jLabel20.setText("Anterior Contraseña");

        jLabel21.setText("Nueva Contraseña");

        jLabel27.setText("Confirmar Nueva Contraseña");

        btnEditSaveContrasena.setText("Guardar Cambios");
        btnEditSaveContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSaveContrasenaActionPerformed(evt);
            }
        });

        btnEditCancelContrasena.setText("Cancelar");
        btnEditCancelContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCancelContrasenaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(txtAnteriorContrasena)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEditContrasena)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEditConfiContrasena)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditSaveContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditCancelContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(7, 7, 7)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAnteriorContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEditContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEditConfiContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditSaveContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditCancelContrasena)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EditContrasenaFormLayout = new javax.swing.GroupLayout(EditContrasenaForm.getContentPane());
        EditContrasenaForm.getContentPane().setLayout(EditContrasenaFormLayout);
        EditContrasenaFormLayout.setHorizontalGroup(
            EditContrasenaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        EditContrasenaFormLayout.setVerticalGroup(
            EditContrasenaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));
        setSize(new java.awt.Dimension(600, 400));

        tbColaboradores.setModel(new javax.swing.table.DefaultTableModel(
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
        tbColaboradores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbColaboradoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbColaboradores);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PERSONAL");

        btnNewPersonal.setText("Registrar Nuevo");
        btnNewPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPersonalActionPerformed(evt);
            }
        });

        btnEditPersonal.setText("Modificar Existente");
        btnEditPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPersonalActionPerformed(evt);
            }
        });

        btnDeletePersonal.setText("Eliminar");
        btnDeletePersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePersonalActionPerformed(evt);
            }
        });

        txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarCaretUpdate(evt);
            }
        });

        cboPersonal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPersonalActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar Personal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNewPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeletePersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar)
                            .addComponent(cboPersonal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewPersonal)
                    .addComponent(btnEditPersonal)
                    .addComponent(btnDeletePersonal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPersonalActionPerformed
        // TODO add your handling code here:
        NewForm.setVisible(true);
    }//GEN-LAST:event_btnNewPersonalActionPerformed

    private void btnEditPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPersonalActionPerformed
        // TODO add your handling code here:
        if(pController.getEmployees().isEmpty()){
            JOptionPane.showMessageDialog(this, "Aún no hay personal registrado", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
            return;
        }
//        loadCboEditPersonal();
        EditForm.setVisible(true);
    }//GEN-LAST:event_btnEditPersonalActionPerformed

    private void btnDeletePersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePersonalActionPerformed
        // TODO add your handling code here:
        Personal temp = pController.findPersonalById(getSelectedPersonal());
        if (getSelectedPersonal() == 0) {
            JOptionPane.showMessageDialog(this, "Error, aun no selecciona un Colaborador", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar al Colaborador "+temp.getNombre()+" "+temp.getApellidos()+"? Esta acción es Irreversible");
        if (resp != 0) { return; }
        pController.deletePersonal(temp);
        loadRows();
        loadCboPersonal();
    }//GEN-LAST:event_btnDeletePersonalActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        // TODO add your handling code here:
        DefaultComboBoxModel<String> dcbmPersonal = new DefaultComboBoxModel<>();
        loadCboPersonal();
        String busqueda = txtBuscar.getText();
        for (int i = 0; i < cboPersonal.getItemCount(); i++) {
            String item = cboPersonal.getItemAt(i);
            if (item.toLowerCase().contains(busqueda.toLowerCase())) {
                dcbmPersonal.addElement(item);
            }
        }
        cboPersonal.setModel(dcbmPersonal);
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void cboPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPersonalActionPerformed
        // TODO add your handling code here:
        if (getSelectedPersonal() == 0) {
            loadRows();
            return;
        }
        Personal p = pController.findPersonalById(getSelectedPersonal());
        dtmColaboradores.setRowCount(0);
        Object[] vec = new Object[9];
        vec[0] = p.getIdPersonal();
        vec[1] = p.getNombre();
        vec[2] = p.getApellidos();
        vec[3] = p.getUsuario();
        vec[4] = p.getTelefono();
        vec[5] = p.getHoraInicio();
        vec[6] = p.getHoraFin();
        vec[7] = p.getDiaDescanso();
        vec[8] = p.getNombreCargo();
        dtmColaboradores.addRow(vec);
        tbColaboradores.setModel(dtmColaboradores);

    }//GEN-LAST:event_cboPersonalActionPerformed

    private void btnNewSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSaveActionPerformed
        // TODO add your handling code here:
        String encryptedContrasena = Sha256.sha256(getNewContrasena());
        if (getNewNombre().isBlank()
                || getNewApellidos().isBlank()
                || getNewContrasena().isBlank()
                || getNewConfiContrasena().isBlank()
                || (getSelectedCargo() == Personal.CARGOS.ADMIN && getNewUsuario().isBlank())
                || getNewHoraInicio().isBlank()
                || getNewHoraFin().isBlank()) {
            JOptionPane.showMessageDialog(this, "Error, debe llenar todas las casillas", "Casillas vacias", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(!getNewContrasena().equalsIgnoreCase(getNewConfiContrasena())){
            JOptionPane.showMessageDialog(this, "Error, Las contraseñas no coinciden", "Contraseñas diferentes", JOptionPane.WARNING_MESSAGE);
            txtNewContrasena.setText("");
            txtNewConfiContrasena.setText("");
            return;
        }
        List<Personal> lst=pController.getEmployees();
        for (Personal p : lst) {
            if (getSelectedCargo() == Personal.CARGOS.ADMIN) {
                if (p.getUsuario().equalsIgnoreCase(getNewUsuario())) {
                    JOptionPane.showMessageDialog(this, "Error, El usuario ingresado ya se encuentra regisstrado", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (p.getTelefono().equalsIgnoreCase(getNewTelefono())) {
                JOptionPane.showMessageDialog(this, "Error, El telefono ingresado ya se encuentra registrado", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (p.getPassword().equalsIgnoreCase(encryptedContrasena)) {
                JOptionPane.showMessageDialog(this, "Error, La contraseña ingresada ya se encuentra registrada", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea generar el nuevo colaborador?");
        if (resp != 0) {
            return;
        }
        Personal temp = new Personal(0, getNewNombre(), getNewApellidos(), getNewUsuario(), encryptedContrasena, getNewTelefono(), getNewHoraInicio(), getNewHoraFin(), getSelectedNewDiaDescanso(), getSelectedCargo());

        //no existe new Personal, ni procedure, ni service
        pController.addPersonal(temp);
        btnNewRestoreActionPerformed(evt);
    }//GEN-LAST:event_btnNewSaveActionPerformed

    private void btnNewRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRestoreActionPerformed
        // TODO add your handling code here:
        cboCargo.setSelectedIndex(0);
        txtNewNombre.setText("");
        txtNewApellido.setText("");
        txtNewUsuario.setText("");
        txtNewContrasena.setText("");
        txtNewConfiContrasena.setText("");
        txtNewTelefono.setText("");
        txtNewHoraInicio.setText("");
        txtNewHoraFin.setText("");
        cboDiaDescanso.setSelectedIndex(0);
    }//GEN-LAST:event_btnNewRestoreActionPerformed

    private void btnNewCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCancelActionPerformed
        // TODO add your handling code here:
        loadCboPersonal();
        loadRows();
        NewForm.dispose();
    }//GEN-LAST:event_btnNewCancelActionPerformed

    private void btnUpdatePersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePersonalActionPerformed
        // TODO add your handling code here:
        List<Personal> lst = pController.getEmployees();
        if (getEditNombre().isBlank()
                || getEditApellidos().isBlank()
                || (getEditSelectedCargo() == Personal.CARGOS.ADMIN && getEditUsuario().isBlank())
                || getEditTelefono().isBlank()
                || getEditHoraInicio().isBlank()
                || getEditHoraFin().isBlank()) {
            JOptionPane.showMessageDialog(this, "Error, debe llenar todas las casillas", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (Personal p : lst) {
            if (p.getIdPersonal() != getSelectedPersonal()) {
                if (getEditSelectedCargo() == Personal.CARGOS.ADMIN) {
                    if (p.getUsuario().equalsIgnoreCase(getEditUsuario())) {
                        JOptionPane.showMessageDialog(this, "Error, El usuario ingresado ya se encuentra registrado", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                if (p.getTelefono().equalsIgnoreCase(getEditTelefono())) {
                    JOptionPane.showMessageDialog(this, "Error, El telefono ingresado ya se encuentra registrado", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
        int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea modificar al colaborador?");
        if (resp != 0) {
            return;
        }
        Personal temp = new Personal(getSelectedEditPersonal(), getEditNombre(), getEditApellidos(), getEditUsuario(), getEditContrasena(), getEditTelefono(), getEditHoraInicio(), getEditHoraFin(), getSelectedEditDiaDescanso(), getEditSelectedCargo());
        //no existe new Personal, ni procedure, ni service
        pController.updatePersonal(temp);
        loadCboPersonal();
        cboPersonal.setSelectedIndex(0);
        llenarDatosPersonal();
    }//GEN-LAST:event_btnUpdatePersonalActionPerformed

    private void btnEditRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditRestoreActionPerformed
        // TODO add your handling code here:
        llenarDatosPersonal();
    }//GEN-LAST:event_btnEditRestoreActionPerformed

    private void btnEditCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCancelActionPerformed
        // TODO add your handling code here:
        loadCboPersonal();
        loadRows();
        EditForm.dispose();
    }//GEN-LAST:event_btnEditCancelActionPerformed

    private void cboEditPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEditPersonalActionPerformed
        // TODO add your handling code here:
        llenarDatosPersonal();
    }//GEN-LAST:event_cboEditPersonalActionPerformed

    private void btnEditSaveContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSaveContrasenaActionPerformed
        // TODO add your handling code here:
        List<Personal> lst = pController.getEmployees();
        Personal temp = getPersonalXContrasena(Sha256.sha256(getAnteriorContrasena()));
        if (getAnteriorContrasena().isBlank()
                || getEditContrasena().isBlank()
                || getEditConfiContrasena().isBlank()) {
            JOptionPane.showMessageDialog(this, "Error, debe llenar todas las casillas", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (temp == null) {
            JOptionPane.showMessageDialog(this, "Error, a contraseña anterior es inválida", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!getEditContrasena().equalsIgnoreCase(getEditConfiContrasena())) {
            JOptionPane.showMessageDialog(this, "Error, las contraseñas no coinciden", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
            txtEditContrasena.setText("");
            txtEditConfiContrasena.setText("");
            return;
        }
        for (Personal p : lst) {
            if (p.getPassword().equalsIgnoreCase(Sha256.sha256(getEditContrasena()))) {
                JOptionPane.showMessageDialog(this, "Error, La contraseña ingresada ya se encuentra regitrada", "Mensaje en la barra de titulo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        //UpadteContraseña
        System.out.println("Funcion aun no implementada, falta procedure/service");
        EditContrasenaForm.dispose();
    }//GEN-LAST:event_btnEditSaveContrasenaActionPerformed

    private void btnEditCancelContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCancelContrasenaActionPerformed
        // TODO add your handling code here:
        EditContrasenaForm.dispose();
    }//GEN-LAST:event_btnEditCancelContrasenaActionPerformed

    private void btnCambiarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarContrasenaActionPerformed
        // TODO add your handling code here:
        EditContrasenaForm.setVisible(true);
    }//GEN-LAST:event_btnCambiarContrasenaActionPerformed

    private void tbColaboradoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbColaboradoresMouseClicked
        cboPersonal.setSelectedIndex((Integer) dtmColaboradores.getValueAt(tbColaboradores.getSelectedRow(), 0));
// TODO add your handling code here:
    }//GEN-LAST:event_tbColaboradoresMouseClicked

    private String getNewNombre() {
        return txtNewNombre.getText();
    }

    private String getNewApellidos() {
        return txtNewApellido.getText();
    }

    private String getNewUsuario() {
        return txtNewUsuario.getText();
    }

    private String getNewContrasena() {
        return new String(txtNewContrasena.getPassword());
    }

    private String getNewConfiContrasena() {
        return new String(txtNewConfiContrasena.getPassword());
    }

    private String getNewTelefono() {
        return txtNewTelefono.getText();
    }

    private String getNewHoraInicio() {
        return txtNewHoraInicio.getText();
    }

    private String getNewHoraFin() {
        return txtNewHoraFin.getText();
    }

    private String getSelectedNewDiaDescanso() {
        return cboDiaDescanso.getSelectedItem().toString();
    }

    private Personal.CARGOS getSelectedCargo() {
        return Personal.CARGOS.valueOf((String) cboCargo.getSelectedItem());
    }

    private String getEditNombre() {
        return txtEditNombre.getText();
    }

    private String getEditApellidos() {
        return txtEditApellido.getText();
    }

    private String getEditUsuario() {
        return txtEditUsuario.getText();
    }

    private String getEditContrasena() {
        return new String(txtEditContrasena.getPassword());
    }

    private String getEditConfiContrasena() {
        return new String(txtEditConfiContrasena.getPassword());
    }

    private String getAnteriorContrasena() {
        return new String(txtAnteriorContrasena.getPassword());
    }

    private String getEditTelefono() {
        return txtEditTelefono.getText();
    }

    private String getEditHoraInicio() {
        return txtEditHoraInicio.getText();
    }

    private String getEditHoraFin() {
        return txtEditHoraFin.getText();
    }

    private String getSelectedEditDiaDescanso() {
        return cboEditDiaDescanso.getSelectedItem().toString();
    }

    private Personal.CARGOS getEditSelectedCargo() {
        return Personal.CARGOS.valueOf((String) cboEditCargo.getSelectedItem());
    }

    private int getSelectedPersonal() {
        if (cboPersonal.getItemCount() == 0) {
            return 0;
        }
        String palabra = cboPersonal.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }

    private int getSelectedEditPersonal() {
        if (cboEditPersonal.getItemCount() == 0) {
            return 1;
        }
        String palabra = cboEditPersonal.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }
    
    private boolean datosRegistrados(String usuario, String telefono, String password){
        if(pController.usuarioExist(usuario)){
            JOptionPane.showMessageDialog(this, "Error, El usuario ingresado ya se encuentra regisstrado", "Dato ya registrado", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(pController.telefonoExist(telefono)){
            JOptionPane.showMessageDialog(this, "Error, El telefono ingresado ya se encuentra registrado", "Dato ya registrado", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if(pController.contrasenaExist(password)){
            JOptionPane.showMessageDialog(this, "Error, La contraseña ingresada ya se encuentra registrada", "Dato ya registrado", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }
    
    private void llenarDatosPersonal() {
        Personal temp = new Personal(0, "", "", "", "", "", "", "", "Lunes", Personal.CARGOS.EMPLEADO);
        if (getSelectedPersonal() != 0) {
            temp = pController.findPersonalById(getSelectedEditPersonal());
        }

        cboEditCargo.setSelectedItem(temp.getNombreCargo().toString());
        txtEditNombre.setText(temp.getNombre());
        txtEditApellido.setText(temp.getApellidos());
        txtEditUsuario.setText(temp.getUsuario());
        txtEditTelefono.setText(temp.getTelefono());
        txtEditHoraInicio.setText(temp.getHoraInicio());
        txtEditHoraFin.setText(temp.getHoraFin());
        cboEditDiaDescanso.setSelectedItem(TimeAndDates.DAYS_WEEK.valueOf(temp.getDiaDescanso()).toString());
    }

    public Personal getPersonalXContrasena(String contrasena) {
        List<Personal> lst = pController.getEmployees();
        for (Personal p : lst) {
            if (p.getPassword().equalsIgnoreCase(contrasena)) {
                return p;
            }
        }
        return null;
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
            java.util.logging.Logger.getLogger(ColaboradoresTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColaboradoresTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColaboradoresTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColaboradoresTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColaboradoresTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame EditContrasenaForm;
    private javax.swing.JFrame EditForm;
    private javax.swing.JFrame NewForm;
    private javax.swing.JButton btnCambiarContrasena;
    private javax.swing.JButton btnDeletePersonal;
    private javax.swing.JButton btnEditCancel;
    private javax.swing.JButton btnEditCancelContrasena;
    private javax.swing.JButton btnEditPersonal;
    private javax.swing.JButton btnEditRestore;
    private javax.swing.JButton btnEditSaveContrasena;
    private javax.swing.JButton btnNewCancel;
    private javax.swing.JButton btnNewPersonal;
    private javax.swing.JButton btnNewRestore;
    private javax.swing.JButton btnNewSave;
    private javax.swing.JButton btnUpdatePersonal;
    private javax.swing.JComboBox<String> cboCargo;
    private javax.swing.JComboBox<String> cboDiaDescanso;
    private javax.swing.JComboBox<String> cboEditCargo;
    private javax.swing.JComboBox<String> cboEditDiaDescanso;
    private javax.swing.JComboBox<String> cboEditPersonal;
    private javax.swing.JComboBox<String> cboPersonal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbColaboradores;
    private javax.swing.JPasswordField txtAnteriorContrasena;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtEditApellido;
    private javax.swing.JPasswordField txtEditConfiContrasena;
    private javax.swing.JPasswordField txtEditContrasena;
    private javax.swing.JTextField txtEditHoraFin;
    private javax.swing.JTextField txtEditHoraInicio;
    private javax.swing.JTextField txtEditNombre;
    private javax.swing.JTextField txtEditTelefono;
    private javax.swing.JTextField txtEditUsuario;
    private javax.swing.JTextField txtNewApellido;
    private javax.swing.JPasswordField txtNewConfiContrasena;
    private javax.swing.JPasswordField txtNewContrasena;
    private javax.swing.JTextField txtNewHoraFin;
    private javax.swing.JTextField txtNewHoraInicio;
    private javax.swing.JTextField txtNewNombre;
    private javax.swing.JTextField txtNewTelefono;
    private javax.swing.JTextField txtNewUsuario;
    // End of variables declaration//GEN-END:variables
}
