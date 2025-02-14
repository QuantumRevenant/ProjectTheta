/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.controller.ClienteController;
import com.mycompany.controller.DetallePedidoController;
import com.mycompany.controller.MesaController;
import com.mycompany.controller.PedidoController;
import com.mycompany.controller.PersonalController;
import com.mycompany.controller.ProgramController;
import com.mycompany.controller.TipoPedidoController;
import com.mycompany.model.entities.Cliente;
import com.mycompany.model.entities.DetallePedido;
import com.mycompany.model.generics.General;
import com.mycompany.model.entities.Mesa;
import com.mycompany.model.entities.Pedido;
import com.mycompany.model.entities.Personal;
import com.mycompany.model.entities.TipoPedido;
import com.mycompany.model.generics.Print;
import com.mycompany.services.ClienteService;
import com.mycompany.services.DetallePedidoService;
import com.mycompany.services.PedidoService;
import com.mycompany.services.PersonalService;
import com.mycompany.services.TipoPedidoService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JTable;
import javax.swing.SwingUtilities;


/**
 *
 * @author bravo
 */
public class PedidosTable extends javax.swing.JFrame {

    ProgramController pc = ProgramController.getProgramController();
    PedidoController pController = new PedidoController(new PedidoService());
    DetallePedidoController dController = new DetallePedidoController(new DetallePedidoService());
    PersonalController perController = new PersonalController(new PersonalService());
    TipoPedidoController tpeController = new TipoPedidoController(new TipoPedidoService());
    ClienteController cController = new ClienteController(new ClienteService());
    MesaController mController = MesaController.getMesaController();
    DefaultTableModel dtmPedidos = new DefaultTableModel();
    DefaultTableModel dtmDetalles = new DefaultTableModel();

    private Pedido selectedPedido=null;
    private JFrame previousFrame;
    private boolean iniciado = false;
    
    
    private RegistrarPedido registrarPedido;

    /**
     * Creates new form PedidosTable
     */
    private enum TipoSelectMesa {
        TODOS, CON_MESA, SIN_MESA
    }

    public PedidosTable() {
        registrarPedido = new RegistrarPedido();
        initComponents();
        this.setLocationRelativeTo(null);
        loadColumns();
        loadRowsPedidos();
        loadCboPersonal();
        loadCboClientes();
        loadCboStatus();
        loadCboTipoPedido();
        loadCboTipoMesa();
        loadCboMesa();
        iniciado=true;
    }

    public void loadSpnFechas(List<Pedido> lst) {
        LocalDateTime datetime = LocalDateTime.now();
        for (Pedido x : lst) {
            if (datetime.isAfter(LocalDateTime.parse(x.getFechaPedido(), pc.getFormatDayTime()))) {
                datetime = LocalDateTime.parse(x.getFechaPedido(), pc.getFormatDayTime());
            }
        }
        Date date = Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
        spnFrom.setValue(date);
        spnTo.setValue(Date.from(Instant.now()));
    }

    public void loadColumns() {
        dtmPedidos.addColumn("Id");
        dtmPedidos.addColumn("Cliente");
        dtmPedidos.addColumn("Personal");
        dtmPedidos.addColumn("Tipo de pedido");
        dtmPedidos.addColumn("Fecha");
        dtmPedidos.addColumn("Estado");
        dtmPedidos.addColumn("Mesa");
        tbPedidos.setModel(dtmPedidos);

        dtmDetalles.addColumn("Servicio");
        dtmDetalles.addColumn("Cantidad");
        dtmDetalles.addColumn("Subtotal");
        tbDetalles.setModel(dtmDetalles);
    }

    public void loadRowsPedidos() {
        List<Pedido> lst = pController.getOrders();
        llenarTablaPedidos(lst);
        loadSpnFechas(lst);
    }

    public void loadCboPersonal() {
        List<Personal> lst = perController.getEmployees();
        cboPersonal.removeAllItems();
        cboPersonal.addItem("[0] - All");
        for (Personal p : lst) {
            cboPersonal.addItem("[" + p.getIdPersonal() + "] - " + p.getNombre() + " " + p.getApellidos());
        }
    }

    public void loadCboClientes() {
        List<Cliente> lst = cController.getEmployees();
        cboClientes.removeAllItems();
        cboClientes.addItem("[0] - All");
        for (Cliente c : lst) {
            cboClientes.addItem("[" + c.getIdCliente() + "] - " + c.getNombre() + " " + c.getApellido());
        }
    }

    public void loadCboStatus() {
        List<Pedido.PEDIDO_STATUS> lst = Arrays.asList(Pedido.PEDIDO_STATUS.values());
        cboStatus.removeAllItems();
        cboStatus.addItem("All");
        for (Pedido.PEDIDO_STATUS x : lst) {
            cboStatus.addItem(x.toString());
        }
    }

    public void loadCboTipoPedido() {
        List<TipoPedido> lst = tpeController.getEmployees();
        cboTipoPedido.removeAllItems();
        cboTipoPedido.addItem("[0] - All");
        for (TipoPedido x : lst) {
            cboTipoPedido.addItem("[" + x.getIdTipoPedido() + "] - " + x.getTipoPedido());
        }
    }

    public void loadCboTipoMesa() {
        cboTypeMesas.removeAllItems();
        for (TipoSelectMesa x : TipoSelectMesa.values()) {
            cboTypeMesas.addItem(x.toString());
        }
    }

    public void loadCboMesa() {
        mController.updateQuantity();
        List<Mesa> lst = mController.getLstMesa();
        cboMesas.removeAllItems();
        cboMesas.addItem("[0] - All");
        for (Mesa x : lst) {
            cboMesas.addItem("[" + x.getCodigo() + "] - " + x.getNombreMesa());
        }
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

        Detalles = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDetalles = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtIgv = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtPersonal_ = new javax.swing.JTextField();
        btnVolver = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTipoPago = new javax.swing.JTextField();
        txtTipoPedido = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        btnEditar = new javax.swing.JButton();
        btnVpdf = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        spnFrom = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        spnTo = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        btnBuscar = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPersonal = new javax.swing.JTextField();
        cboPersonal = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtClientes = new javax.swing.JTextField();
        cboClientes = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboStatus = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboTipoPedido = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cboTypeMesas = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cboMesas = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        Detalles.setResizable(false);
        Detalles.setSize(new java.awt.Dimension(430, 710));
        Detalles.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                DetallesWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 240, 213));

        lblTitulo.setFont(new java.awt.Font("Snap ITC", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("PEDIDO #0000");

        tbDetalles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbDetalles);

        jLabel9.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel9.setText("Subtotal:");

        jLabel10.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel10.setText("IGV(18%):");

        jLabel11.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel11.setText("Total:");

        txtSubtotal.setEditable(false);

        txtIgv.setEditable(false);

        txtTotal.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel8.setText("Cliente");

        jLabel12.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel12.setText("Personal");

        txtCliente.setEditable(false);

        txtPersonal_.setEditable(false);

        btnVolver.setBackground(new java.awt.Color(204, 204, 255));
        btnVolver.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel14.setText("Fecha");

        jLabel15.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel15.setText("Status");

        txtFecha.setEditable(false);

        txtStatus.setEditable(false);

        jLabel16.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel16.setText("Tipo de Pago");

        jLabel17.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel17.setText("Tipo de Pedido");

        txtTipoPago.setEditable(false);

        txtTipoPedido.setEditable(false);

        jLabel18.setFont(new java.awt.Font("Lucida Handwriting", 1, 12)); // NOI18N
        jLabel18.setText("Observación");

        txtObservacion.setEditable(false);
        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane4.setViewportView(txtObservacion);

        btnEditar.setBackground(new java.awt.Color(204, 204, 255));
        btnEditar.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnVpdf.setBackground(new java.awt.Color(204, 204, 255));
        btnVpdf.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        btnVpdf.setText("VER PDF");
        btnVpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVpdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(23, 23, 23))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(11, 11, 11)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(11, 11, 11)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTipoPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSubtotal)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTotal)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(11, 11, 11)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(11, 11, 11)
                                            .addComponent(txtPersonal_))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(189, 189, 189))
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(23, 23, 23))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnVpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPersonal_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnVolver))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVpdf)
                .addContainerGap(199, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DetallesLayout = new javax.swing.GroupLayout(Detalles.getContentPane());
        Detalles.getContentPane().setLayout(DetallesLayout);
        DetallesLayout.setHorizontalGroup(
            DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DetallesLayout.setVerticalGroup(
            DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetallesLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(750, 400));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 240, 213));

        tbPedidos.setAutoCreateRowSorter(true);
        tbPedidos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPedidos);

        jLabel2.setText("From");

        spnFrom.setModel(new javax.swing.SpinnerDateModel());

        jLabel19.setText("To");

        spnTo.setModel(new javax.swing.SpinnerDateModel());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnBuscar.setBackground(new java.awt.Color(204, 204, 255));
        btnBuscar.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BUSQUEDA POR ID");

        txtPersonal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPersonalCaretUpdate(evt);
            }
        });

        cboPersonal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPersonalActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel5.setText("Clientes");

        txtClientes.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtClientesCaretUpdate(evt);
            }
        });

        cboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel6.setText("Status");

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel7.setText("Tipo de Pedido");

        cboTipoPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnFiltrar.setBackground(new java.awt.Color(204, 204, 255));
        btnFiltrar.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 255));
        btnLimpiar.setFont(new java.awt.Font("Kristen ITC", 1, 12)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        cboTypeMesas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTypeMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTypeMesasActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel13.setText("Mesas");

        cboMesas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMesas.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel4.setText("Personal");

        jLabel1.setFont(new java.awt.Font("Snap ITC", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PEDIDOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtBusqueda)
                                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtPersonal, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtClientes, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cboStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cboTipoPedido, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cboTypeMesas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cboMesas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(spnTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator3))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimpiar)
                            .addComponent(cboMesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltrar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTypeMesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spnFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(spnTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPedidosMouseClicked
        Detalles.setVisible(true);
        int id = (int) dtmPedidos.getValueAt(tbPedidos.getSelectedRow(), 0);
        Pedido pedido = pController.findCustomerById(id);
        selectedPedido=pedido;
        List<DetallePedido> lst = dController.getDetails().stream()
                .filter(detalle -> detalle.getIdPedido().getIdPedido() == id)
                .collect(Collectors.toList());
        llenarDatosPedido(pedido);
        llenarTablaDetalles(lst);
    }//GEN-LAST:event_tbPedidosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        List<Pedido> lst = pController.getOrders().stream()
                .filter(pedido -> pedido.getIdPedido() == getIdBuscado())
                .collect(Collectors.toList());
        llenarTablaPedidos(lst);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtPersonalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalCaretUpdate
        loadCboPersonal();
        General.filtrarCbo(cboPersonal, txtPersonal);
    }//GEN-LAST:event_txtPersonalCaretUpdate

    private void txtClientesCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtClientesCaretUpdate
        loadCboClientes();
        General.filtrarCbo(cboClientes, txtClientes);
    }//GEN-LAST:event_txtClientesCaretUpdate

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        List<Pedido> lst = filtrarPedidos(pController.getOrders());
        llenarTablaPedidos(lst);
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtBusqueda.setText("");
        txtPersonal.setText("");
        cboClientes.setSelectedIndex(0);
        cboPersonal.setSelectedIndex(0);
        cboStatus.setSelectedIndex(0);
        cboTipoPedido.setSelectedIndex(0);
        loadRowsPedidos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        Detalles.setVisible(false);
        setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        RegistrarPedido form = new RegistrarPedido();
        form.setPreviousFrame(Detalles);
        form.setRegistroValue(RegistrarPedido.REGISTRO.Actualizar, selectedPedido);
        form.setVisible(true);
        setVisible(false);
        Detalles.setVisible(false);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void DetallesWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DetallesWindowGainedFocus
        int id = (int) dtmPedidos.getValueAt(tbPedidos.getSelectedRow(), 0);
        Pedido pedido = pController.findCustomerById(id);
        List<DetallePedido> lst = dController.getDetails().stream()
                .filter(detalle -> detalle.getIdPedido().getIdPedido() == id)
                .collect(Collectors.toList());
        llenarDatosPedido(pedido);
        llenarTablaDetalles(lst);
        
        Detalles.setLocationRelativeTo(null); 
        // Mostrar la ventana
        Detalles.setVisible(true);
    }//GEN-LAST:event_DetallesWindowGainedFocus

    private void cboPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPersonalActionPerformed
    }//GEN-LAST:event_cboPersonalActionPerformed

    private void cboTypeMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTypeMesasActionPerformed
        if(!iniciado) { return; }
        cboMesas.setSelectedIndex(0);
        if (TipoSelectMesa.valueOf((String) cboTypeMesas.getSelectedItem()) == TipoSelectMesa.CON_MESA) {
            cboMesas.setEnabled(true);
        } else { cboMesas.setEnabled(false); }
    }//GEN-LAST:event_cboTypeMesasActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        txtBusqueda.setText("");
        cboClientes.setSelectedIndex(0);
        cboPersonal.setSelectedIndex(0);
        cboStatus.setSelectedIndex(0);
        cboTipoPedido.setSelectedIndex(0);
        loadRowsPedidos();
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnVpdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVpdfActionPerformed
        // TODO add your handling code here:
        // Obtener el ID del pedido seleccionado en la tabla (ejemplo: columna 0)
    int filaSeleccionada = tbPedidos.getSelectedRow();
    int idPedido = (int) tbPedidos.getValueAt(filaSeleccionada, 0);
    
    // Generar la ruta del archivo PDF específico del pedido
    String rutaPDF = "src/main/java/pdf/venta" + idPedido + ".pdf";
    
    // Intentar abrir el PDF con el visor predeterminado del sistema
    try {
        File pdfFile = new File(rutaPDF);
        if (pdfFile.exists()) {
            Desktop.getDesktop().open(pdfFile);
        } else {
            System.out.println("El archivo PDF no existe en la ruta especificada.");
        }
    } catch (IOException e) {
        System.out.println("Error al intentar abrir el archivo PDF: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVpdfActionPerformed

    private void llenarTablaPedidos(List<Pedido> lstPedidos) {
        dtmPedidos.setRowCount(0);
        try {
            for (Pedido p : lstPedidos) {
                Object[] vec = new Object[7];
                vec[0] = p.getIdPedido();
                vec[1] = p.getIdCliente().getNombre() + " " + p.getIdCliente().getApellido();
                vec[2] = p.getIdPersonal().getNombre() + " " + p.getIdPersonal().getApellidos();
                vec[3] = p.getIdTipoPedido().getTipoPedido();
                vec[4] = p.getFechaPedido();
                vec[5] = p.getStatus();
                if (p.getIdMesa() != null) {
                    vec[6] = p.getIdMesa().getNombreMesa();
                } else {
                    vec[6] = "No Mesa";
                }
                dtmPedidos.addRow(vec);
            }
            tbPedidos.setModel(dtmPedidos);
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    private void llenarTablaDetalles(List<DetallePedido> lstDetalles) {
        dtmDetalles.setRowCount(0);
        for (DetallePedido d : lstDetalles) {
            Object[] vec = new Object[3];
            vec[0] = d.getIdMenu().getTipo();
            vec[1] = d.getCantidadPlatos();
            vec[2] = d.getSubTotal();
            dtmDetalles.addRow(vec);
        }
        tbDetalles.setModel(dtmDetalles);
    }

    private void llenarDatosPedido(Pedido pedido) {
        lblTitulo.setText("PEDIDO #" + pedido.getIdPedido());
        txtCliente.setText(pedido.getIdCliente().getNombre() + " " + pedido.getIdCliente().getApellido());
        txtPersonal_.setText(pedido.getIdPersonal().getNombre() + " " + pedido.getIdPersonal().getApellidos());
        txtObservacion.setText(pedido.getDescripcion());
        txtTipoPago.setText(pedido.getIdTipoPago().getTipoPago());
        txtTipoPedido.setText(pedido.getIdTipoPedido().getTipoPedido());
        txtFecha.setText(pedido.getFechaPedido());
        txtStatus.setText(pedido.getStatus().name());
        txtSubtotal.setText(pedido.getTotal() - pedido.getIgv() + "");
        txtIgv.setText(pedido.getIgv() + "");
        txtTotal.setText(pedido.getTotal() + "");
    }

    private int getIdBuscado()          { return Integer.parseInt(txtBusqueda.getText()); }
    private int getSelectedPersonal()   { return General.getSelectedId(cboPersonal); }
    private int getSelectedMesa()       { return General.getSelectedId(cboMesas); }
    private int getSelectedCliente()    { return General.getSelectedId(cboClientes); }
    private int getSelectedTipoPedido() { return General.getSelectedId(cboTipoPedido); }
    private Pedido.PEDIDO_STATUS getSelectedStatus() {
        if (cboStatus.getSelectedItem().toString().equals("All")) { return null; }
        return Pedido.PEDIDO_STATUS.valueOf(cboStatus.getSelectedItem().toString());
    }

    private List<Pedido> filtrarPedidos(List<Pedido> lst) {
        return filtrarCliente(filtrarFecha(filtrarMesa(filtrarPersonal(filtrarStatus(filtrarTipoPedido(lst))))));
    }

    private List<Pedido> filtrarFecha(List<Pedido> lst) {
        LocalDateTime dateFrom = ProgramController.dateToLocalDateTime((Date) spnFrom.getValue());
        LocalDateTime dateTo = ProgramController.dateToLocalDateTime((Date) spnTo.getValue());

        List<Pedido> lst1 = lst.stream().filter(pedido
                -> (LocalDateTime.parse(pedido.getFechaPedido(), pc.getFormatDayTime()).isAfter(dateFrom)
                || LocalDateTime.parse(pedido.getFechaPedido(), pc.getFormatDayTime()).isEqual(dateFrom))
                && (LocalDateTime.parse(pedido.getFechaPedido(), pc.getFormatDayTime())
                        .isBefore(dateTo)
                || LocalDateTime.parse(pedido.getFechaPedido(), pc.getFormatDayTime())
                        .isEqual(dateTo)))
                .collect(Collectors.toList());
        return lst1;
    }

    private List<Pedido> filtrarMesa(List<Pedido> lst) {
        switch (TipoSelectMesa.valueOf((String) cboTypeMesas.getSelectedItem())) {
            case TODOS:
                return lst;
            case SIN_MESA:
                return lst.stream().filter(pedido -> pedido.getIdMesa() == null).collect(Collectors.toList());
            case CON_MESA:
                List<Pedido> lstOut = lst.stream().filter(pedido -> pedido.getIdMesa() != null).collect(Collectors.toList());
                if (getSelectedMesa() != 0) {
                    return lstOut.stream().filter(pedido -> pedido.getIdMesa().getCodigo() == getSelectedMesa()).collect(Collectors.toList());
                }
                return lstOut;
            default:
                Print.error("Status no correcto en TipoSelectMesa");
                return lst;
        }
    }

    private List<Pedido> filtrarCliente(List<Pedido> lst) {
        if (getSelectedCliente() != 0) {
            return lst.stream().filter(pedido -> pedido.getIdCliente().getIdCliente() == getSelectedCliente()).collect(Collectors.toList());
        }
        return lst;
    }

    private List<Pedido> filtrarPersonal(List<Pedido> lst) {
        if (getSelectedPersonal() != 0) {
            return lst.stream().filter(pedido -> pedido.getIdPersonal().getIdPersonal() == getSelectedPersonal()).collect(Collectors.toList());
        }
        return lst;
    }

    private List<Pedido> filtrarStatus(List<Pedido> lst) {
        if (getSelectedStatus() != null) {
            return lst.stream().filter(pedido -> pedido.getStatus().equals(getSelectedStatus())).collect(Collectors.toList());
        }
        return lst;
    }

    private List<Pedido> filtrarTipoPedido(List<Pedido> lst) {
        if (getSelectedTipoPedido() != 0) {
            return lst.stream().filter(pedido -> pedido.getIdTipoPedido().getIdTipoPedido() == getSelectedTipoPedido()).collect(Collectors.toList());
        }
        return lst;
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
            java.util.logging.Logger.getLogger(PedidosTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Detalles;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton btnVpdf;
    private javax.swing.JComboBox<String> cboClientes;
    private javax.swing.JComboBox<String> cboMesas;
    private javax.swing.JComboBox<String> cboPersonal;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JComboBox<String> cboTipoPedido;
    private javax.swing.JComboBox<String> cboTypeMesas;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JSpinner spnFrom;
    private javax.swing.JSpinner spnTo;
    private javax.swing.JTable tbDetalles;
    private javax.swing.JTable tbPedidos;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtClientes;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIgv;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtPersonal;
    private javax.swing.JTextField txtPersonal_;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTipoPago;
    private javax.swing.JTextField txtTipoPedido;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
