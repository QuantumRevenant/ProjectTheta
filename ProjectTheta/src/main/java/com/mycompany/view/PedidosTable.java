/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.controller.ClienteController;
import com.mycompany.controller.DetallePedidoController;
import com.mycompany.controller.PedidoController;
import com.mycompany.controller.PersonalController;
import com.mycompany.controller.TipoPedidoController;
import com.mycompany.model.entities.Cliente;
import com.mycompany.model.entities.DetallePedido;
import com.mycompany.model.entities.Pedido;
import com.mycompany.model.entities.Personal;
import com.mycompany.model.entities.TipoPedido;
import com.mycompany.services.ClienteService;
import com.mycompany.services.DetallePedidoService;
import com.mycompany.services.PedidoService;
import com.mycompany.services.PersonalService;
import com.mycompany.services.TipoPedidoService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bravo
 */
public class PedidosTable extends javax.swing.JFrame {
    PedidoController pController = new PedidoController(new PedidoService());
    DetallePedidoController dController = new DetallePedidoController(new DetallePedidoService());
    PersonalController perController = new PersonalController(new PersonalService());
    TipoPedidoController tpeController = new TipoPedidoController(new TipoPedidoService());
    ClienteController cController = new ClienteController(new ClienteService());
    DefaultTableModel dtmPedidos = new DefaultTableModel();
    DefaultTableModel dtmDetalles = new DefaultTableModel();
    
    private JFrame previousFrame;
    /**
     * Creates new form PedidosTable
     */
    public PedidosTable() {
        initComponents();
        loadColumns();
        loadRowsPedidos();
        loadCboPersonal();
        loadCboClientes();
        loadCboStatus();
        loadCboTipoPedido();
    }

    public void loadColumns(){
        dtmPedidos.addColumn("Id");
        dtmPedidos.addColumn("Cliente");
        dtmPedidos.addColumn("Personal");
        dtmPedidos.addColumn("Tipo de pedido");
        dtmPedidos.addColumn("Fecha");
        dtmPedidos.addColumn("Estado");
        tbPedidos.setModel(dtmPedidos);
        
        dtmDetalles.addColumn("Servicio");
        dtmDetalles.addColumn("Cantidad");
        dtmDetalles.addColumn("Subtotal");
        tbDetalles.setModel(dtmDetalles);
    }
    public void loadRowsPedidos(){
        List<Pedido> lst = pController.getOrders();
        dtmPedidos.setRowCount(0);
        try{
            for(Pedido p:lst){
                Object[] vec = new Object[6];
                vec[0] = p.getIdPedido();
                vec[1] = p.getIdCliente().getNombre()+" "+p.getIdCliente().getApellido();
                vec[2] = p.getIdPersonal().getNombre()+ " " +p.getIdPersonal().getApellidos();
                vec[3] = p.getIdTipoPedido().getTipoPedido();
                vec[4] = p.getFechaPedido();
                vec[5] = p.getStatus();
                dtmPedidos.addRow(vec);
            }
        tbPedidos.setModel(dtmPedidos);
        }catch(Exception ex){
            System.out.println("Error");
        }
    }
    public void loadCboPersonal(){
        List<Personal> lst = perController.getEmployees();
        cboPersonal.removeAllItems();
        cboPersonal.addItem("[0] - All");
        for(Personal p:lst){
            cboPersonal.addItem("["+p.getIdPersonal()+"] - "+p.getNombre()+" "+p.getApellidos());
        }
    }
    public void loadCboClientes(){
        List<Cliente> lst = cController.getEmployees();
        cboClientes.removeAllItems();
        cboClientes.addItem("[0] - All");
        for(Cliente c : lst){
            cboClientes.addItem("[" + c.getIdCliente() + "] - " + c.getNombre() + " " + c.getApellido());
        }
    }
    public void loadCboStatus(){
        List<Pedido.PEDIDO_STATUS> lst = Arrays.asList(Pedido.PEDIDO_STATUS.values());
        cboStatus.removeAllItems();
        cboStatus.addItem("All");
        for (Pedido.PEDIDO_STATUS x : lst) {
            cboStatus.addItem(x.toString());
        }
    }
    public void loadCboTipoPedido(){
        List<TipoPedido> lst = tpeController.getEmployees();
        cboTipoPedido.removeAllItems();
        cboTipoPedido.addItem("[0] - All");
        for (TipoPedido x : lst) {
            cboTipoPedido.addItem("[" + x.getIdTipoPedido() + "] - " + x.getTipoPedido());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtBusqueda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPersonal = new javax.swing.JTextField();
        cboPersonal = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtClientes = new javax.swing.JTextField();
        cboClientes = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cboStatus = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboTipoPedido = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();

        Detalles.setResizable(false);
        Detalles.setSize(new java.awt.Dimension(430, 710));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
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

        jLabel9.setText("Subtotal:");

        jLabel10.setText("IGV(18%):");

        jLabel11.setText("Total:");

        txtSubtotal.setEditable(false);

        txtIgv.setEditable(false);

        txtTotal.setEditable(false);

        jLabel8.setText("Cliente");

        jLabel12.setText("Personal");

        txtCliente.setEditable(false);

        txtPersonal_.setEditable(false);

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel14.setText("Fecha");

        jLabel15.setText("Status");

        txtFecha.setEditable(false);

        txtStatus.setEditable(false);

        jLabel16.setText("Tipo de Pago");

        jLabel17.setText("Tipo de Pedido");

        txtTipoPago.setEditable(false);

        txtTipoPedido.setEditable(false);

        jLabel18.setText("Observación");

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane4.setViewportView(txtObservacion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(txtTipoPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIgv, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotal)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
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
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
                                .addGap(0, 23, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
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
                .addComponent(btnVolver)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DetallesLayout = new javax.swing.GroupLayout(Detalles.getContentPane());
        Detalles.getContentPane().setLayout(DetallesLayout);
        DetallesLayout.setHorizontalGroup(
            DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DetallesLayout.setVerticalGroup(
            DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(720, 450));
        setSize(new java.awt.Dimension(750, 400));

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PEDIDOS");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BUSQUEDA POR ID");

        jLabel4.setText("Personal");

        txtPersonal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPersonalCaretUpdate(evt);
            }
        });

        cboPersonal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Clientes");

        txtClientes.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtClientesCaretUpdate(evt);
            }
        });

        cboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel6.setText("Status");

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Tipo de Pedido");

        cboTipoPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar Filtros");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(txtBusqueda)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPersonal)
                            .addComponent(cboPersonal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtClientes)
                            .addComponent(cboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTipoPedido, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscar))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnFiltrar))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPedidosMouseClicked
        int id = (int) dtmPedidos.getValueAt(tbPedidos.getSelectedRow(), 0);
        Pedido pedido = pController.findCustomerById(id);
        double sub = 0;
        lblTitulo.setText("PEDIDO #"+id);
        txtCliente.setText(pedido.getIdCliente().getNombre()+" "+pedido.getIdCliente().getApellido());
        txtPersonal.setText(pedido.getIdPersonal().getNombre()+" "+pedido.getIdPersonal().getApellidos());
        txtTipoPago.setText(pedido.getIdTipoPago().getTipoPago());
        txtTipoPedido.setText(pedido.getIdTipoPedido().getTipoPedido());
        txtFecha.setText(pedido.getFechaPedido());
        txtStatus.setText(pedido.getStatus().name());
        txtIgv.setText(pedido.getIgv()+"");
        txtTotal.setText(pedido.getTotal()+"");
        Detalles.setVisible(true);
        List<DetallePedido> lst = dController.getDetails();
        dtmDetalles.setRowCount(0);
        for(DetallePedido d:lst){
            if(d.getIdPedido().getIdPedido()==id){
                Object[] vec = new Object[3];
                vec[0] = d.getIdMenu().getTipo();
                vec[1] = d.getCantidadPlatos();
                vec[2] = d.getSubTotal();
                sub += d.getSubTotal();
                dtmDetalles.addRow(vec);
            }
        }
        txtSubtotal.setText(sub+"");
        tbDetalles.setModel(dtmDetalles);
    }//GEN-LAST:event_tbPedidosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        List<Pedido> lst = pController.getOrders();
        dtmPedidos.setRowCount(0);
        try{
            for(Pedido p:lst){
                if(p.getIdPedido()==getIdBuscado()){
                    Object[] vec = new Object[6];
                    vec[0] = p.getIdPedido();
                    vec[1] = p.getIdCliente().getNombre()+" "+p.getIdCliente().getApellido();
                    vec[2] = p.getIdPersonal().getNombre()+ " " +p.getIdPersonal().getApellidos();
                    vec[3] = p.getIdTipoPedido().getTipoPedido();
                    vec[4] = p.getFechaPedido();
                    vec[5] = p.getStatus();
                    dtmPedidos.addRow(vec);
                }
            }
        tbPedidos.setModel(dtmPedidos);
        }catch(Exception ex){
            System.out.println("Error");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtPersonalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalCaretUpdate
        // TODO add your handling code here:
        DefaultComboBoxModel<String> dcbmPersonal = new DefaultComboBoxModel<>();
        loadCboPersonal();
        String busqueda = txtPersonal.getText();
        for(int i=0;i<cboPersonal.getItemCount();i++){
            String item = cboPersonal.getItemAt(i);
            if(item.toLowerCase().contains(busqueda.toLowerCase())){
                dcbmPersonal.addElement(item);
            }
        }
        dcbmPersonal.addElement("[0] - All");
        cboPersonal.setModel(dcbmPersonal);
    }//GEN-LAST:event_txtPersonalCaretUpdate

    private void txtClientesCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtClientesCaretUpdate
        // TODO add your handling code here:
        DefaultComboBoxModel<String> dcbmClientes = new DefaultComboBoxModel<>();
        loadCboClientes();
        String busqueda = txtClientes.getText();
        for(int i=0;i<cboClientes.getItemCount();i++){
            String item = cboClientes.getItemAt(i);
            if(item.toLowerCase().contains(busqueda.toLowerCase())){
                dcbmClientes.addElement(item);
            }
        }
        dcbmClientes.addElement("[0] - All");
        cboClientes.setModel(dcbmClientes);
    }//GEN-LAST:event_txtClientesCaretUpdate

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        List<Pedido> lst = filtrarPedidos(pController.getOrders());
        dtmPedidos.setRowCount(0);
        try{
            for(Pedido p:lst){
                Object[] vec = new Object[6];
                vec[0] = p.getIdPedido();
                vec[1] = p.getIdCliente().getNombre()+" "+p.getIdCliente().getApellido();
                vec[2] = p.getIdPersonal().getNombre()+ " " +p.getIdPersonal().getApellidos();
                vec[3] = p.getIdTipoPedido().getTipoPedido();
                vec[4] = p.getFechaPedido();
                vec[5] = p.getStatus();
                dtmPedidos.addRow(vec);
            }
            tbPedidos.setModel(dtmPedidos);
        }catch(Exception ex){
            System.out.println("Error");
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtBusqueda.setText("");
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
    }//GEN-LAST:event_btnVolverActionPerformed
    
    private int getIdBuscado(){
        return Integer.parseInt(txtBusqueda.getText());
    }
    
    private int getSelectedPersonal(){
        String palabra = cboPersonal.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }
    
    private int getSelectedCliente(){
        String palabra = cboClientes.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }
    private Pedido.PEDIDO_STATUS getSelectedStatus(){
        if(cboStatus.getSelectedItem().toString().equals("All")){ return null; }
        return Pedido.PEDIDO_STATUS.valueOf(cboStatus.getSelectedItem().toString());
    }
    private int getSelectedTipoPedido(){
        String palabra = cboTipoPedido.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }
    private List<Pedido> filtrarPedidos(List<Pedido> lst){
        return filtrarCliente(filtrarPersonal(filtrarStatus(filtrarTipoPedido(lst))));
    }
    private List<Pedido> filtrarCliente(List<Pedido> lst){
        if(getSelectedCliente()!=0){
            return lst.stream().filter(pedido -> pedido.getIdCliente().getIdCliente()==getSelectedCliente()).collect(Collectors.toList());
        }
        return lst;
    }
    private List<Pedido> filtrarPersonal(List<Pedido> lst){
        if(getSelectedPersonal()!=0){
            return lst.stream().filter(pedido -> pedido.getIdPersonal().getIdPersonal()==getSelectedPersonal()).collect(Collectors.toList());
        }
        return lst;
    }
    private List<Pedido> filtrarStatus(List<Pedido> lst){
        if(getSelectedStatus()!=null){
            return lst.stream().filter(pedido -> pedido.getStatus().equals(getSelectedStatus())).collect(Collectors.toList());
        }
        return lst;
    }
    private List<Pedido> filtrarTipoPedido(List<Pedido> lst){
        if(getSelectedTipoPedido()!=0){
            return lst.stream().filter(pedido -> pedido.getIdTipoPedido().getIdTipoPedido()==getSelectedTipoPedido()).collect(Collectors.toList());
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
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cboClientes;
    private javax.swing.JComboBox<String> cboPersonal;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JComboBox<String> cboTipoPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblTitulo;
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
