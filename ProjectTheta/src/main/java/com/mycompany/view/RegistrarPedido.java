package com.mycompany.view;

import com.mycompany.controller.ClienteController;
import com.mycompany.controller.DetallePedidoController;
import com.mycompany.controller.MenuController;
import com.mycompany.controller.MesaController;
import com.mycompany.controller.PedidoController;
import com.mycompany.controller.PersonalController;
import com.mycompany.controller.ProgramController;
import com.mycompany.controller.TipoPagoController;
import com.mycompany.controller.TipoPedidoController;
import com.mycompany.model.entities.Cliente;
import com.mycompany.model.entities.DetallePedido;
import com.mycompany.model.entities.Personal;
import com.mycompany.model.entities.Menu;
import com.mycompany.model.entities.Mesa;
import com.mycompany.model.entities.Pedido;
import com.mycompany.model.entities.TipoPago;
import com.mycompany.model.entities.TipoPedido;
import com.mycompany.model.generics.General;
import com.mycompany.model.generics.Print;
import com.mycompany.services.ClienteService;
import com.mycompany.services.DetallePedidoService;
import com.mycompany.services.MenuService;
import com.mycompany.services.PedidoService;
import com.mycompany.services.PersonalService;
import com.mycompany.services.TipoPagoService;
import com.mycompany.services.TipoPedidoService;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lombok.Data;

/**
 *
 * @author bravo
 */
@Data
public class RegistrarPedido extends javax.swing.JFrame {

    private ProgramController pc = ProgramController.getProgramController();
    private PedidoController pedidoController = new PedidoController(new PedidoService());
    private DetallePedidoController dpController = new DetallePedidoController(new DetallePedidoService());
    private MesaController mc = MesaController.getMesaController();
    private ClienteController cController = new ClienteController(new ClienteService());
    private PersonalController pController = new PersonalController(new PersonalService());
    private MenuController mController = new MenuController(new MenuService());
    private TipoPagoController tpaController = new TipoPagoController(new TipoPagoService());
    private TipoPedidoController tpeController = new TipoPedidoController(new TipoPedidoService());
    private DefaultTableModel dtmDetalles = new DefaultTableModel();

    private REGISTRO registro = REGISTRO.Registrar;
    private Pedido previousPedido = null;
    private Pedido IdPedido = new Pedido();
    private List<DetallePedido> lstDetalles = new ArrayList<>();
    private Mesa mesaSeleccionada;

    private JFrame previousFrame;
    private boolean iniciado = false;

    public static enum REGISTRO {
        Registrar, Actualizar
    }

    /**
     * Creates new form RegistrarPedido
     */
    public RegistrarPedido() {
        initComponents();
        loadCbos();
        restablecer();
        iniciado = true;
    }

    public void update() {
        if (!iniciado) {
            return;
        }
        if ((getCodeFromString((String) cboTipoPedido.getSelectedItem()) != TipoPedido.SALON) && (getCodeFromString((String) cboTipoPedido.getSelectedItem()) != TipoPedido.RESERVA)) {
            cboMesa.setSelectedIndex(0);
            cboMesa.setEnabled(false);
        } else {
            cboMesa.setEnabled(true);
        }
    }

    public void restablecer() {
        if (iniciado) {
            int opt = JOptionPane.showConfirmDialog(this, "Los datos no guardados se perderán ¿Estas Seguro?", "Restablecer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opt != JOptionPane.OK_OPTION) {
                return;
            }
        }
        lstDetalles.clear();
        cboProductos.setSelectedIndex(0);
        spnUnidades.setValue(1);

        if (previousPedido == null) {
            IdPedido = new Pedido();
            cboClientes.setSelectedIndex(0);
            cboMesa.setSelectedIndex(0);
            cboStatus.setSelectedIndex(0);
            cboTipoPago.setSelectedIndex(0);
            cboTipoPedido.setSelectedIndex(0);
            spnFecha.setValue(Date.from(Instant.now()));
            txtObs.setText("");
            txtSubtotal.setText("" + (double) 0);
            txtIGV.setText("" + (double) 0);
            txtTotal.setText("" + (double) 0);
        } else {
            IdPedido = previousPedido.clone();
            lstDetalles = dpController.listByPedidoID(IdPedido.getIdPedido());
            cboClientes.setSelectedIndex(IdPedido.getIdCliente().getIdCliente() - 1);
            cboMesa.setSelectedIndex(IdPedido.getIdMesa().getCodigo());
            cboStatus.setSelectedItem(IdPedido.getStatus().toString());
            cboTipoPago.setSelectedIndex(IdPedido.getIdTipoPago().getIdTipoPago() - 1);
            cboTipoPedido.setSelectedIndex(IdPedido.getIdTipoPedido().getIdTipoPedido() - 1);
            LocalDateTime datetime = LocalDateTime.parse(IdPedido.getFechaPedido(), pc.getFormatDayTime());
            Date date = Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
            spnFecha.setValue(date);
            txtObs.setText(IdPedido.getDescripcion());
            loadRowsDetalles();
            updateWndDetalles();
        }
    }

    public void updateCbos() {
        iniciado = false;
        loadCbos();
        setRegistroValue(registro, previousPedido);
        iniciado = true;
    }

    public void actualizarPedido() {
        IdPedido.setDescripcion(txtObs.getText());
        double total = 0;
        for (DetallePedido x : lstDetalles) {
            total += x.getSubTotal();
        }
        IdPedido.setTotal(total);
        IdPedido.setStatus(Pedido.PEDIDO_STATUS.valueOf((String) cboStatus.getSelectedItem()));
        IdPedido.setIdPersonal(pController.findPersonalById(getSelectedPersonal()));
        IdPedido.setIdTipoPedido(tpeController.findPersonalById(getCodeFromString((String) cboTipoPedido.getSelectedItem())));
        IdPedido.setIdCliente(cController.findCustomerById(getSelectedCliente()));
        IdPedido.setIdTipoPago(tpaController.findPersonalById(getSelectedTipoPago()));
        IdPedido.setIgv(total * Pedido.igv());
        IdPedido.setIdMesa(mc.get(getCodeFromString((String)cboMesa.getSelectedItem())));
        Date theDate = (Date) spnFecha.getValue();
        LocalDateTime thedatetime = java.time.LocalDateTime.ofInstant(theDate.toInstant(), ZoneId.systemDefault());
        IdPedido.setFechaPedido(thedatetime.format(pc.getFormatDayTime()));
    }

    public void updateWndDetalles() {
        if (iniciado) {
            actualizarPedido();
        }
        loadRowsDetalles();
        txtSubtotal.setText("" + (IdPedido.getTotal() - IdPedido.getIgv()));
        txtIGV.setText("" + IdPedido.getIgv());
        txtTotal.setText("" + IdPedido.getTotal());
    }

    public void setRegistroValue(REGISTRO reg, Pedido pedido) {
        registro = reg;
        previousPedido = registro != REGISTRO.Registrar ? pedido : null;
        setInicialStatus();
        setValues();
        iniciado = false;
        restablecer();
        iniciado = true;
        update();
    }

    public void setValues() {
        if (pc.getIdColaboradorActivo() != null) {
            if (registro == REGISTRO.Registrar) {
                Personal p = pc.getIdColaboradorActivo();
                cboPersonal.setSelectedItem("[" + p.getIdPersonal() + "] - " + p.getNombre() + " " + p.getApellidos());
            }
        }
    }

    public void setInicialStatus() {
        lblTitle.setText(registro.toString().toUpperCase() + " PEDIDO");
        btnUpdate.setText(registro.toString());
    }

    public void loadCbos() {
        loadCboMesas();
        loadCboStatus();
        loadCboClientes();
        loadCboPersonal();
        loadCboTipoPago();
        loadCboProductos();
        loadColumnsDetalles();
        loadRowsDetalles();
        loadCboTipoPedido();
    }

    public void loadCboMesas() {
        List<Mesa> lst = mc.getLstMesa();
        cboMesa.removeAllItems();
        cboMesa.addItem("[" + 0 + "] - " + "SIN MESA");
        for (Mesa c : lst) {
            cboMesa.addItem("[" + c.getCodigo() + "] - " + c.getNombreMesa());
        }
    }

    public void loadCboStatus() {
        List<Pedido.PEDIDO_STATUS> lst = Arrays.asList(Pedido.PEDIDO_STATUS.values());
        cboStatus.removeAllItems();
        for (Pedido.PEDIDO_STATUS x : lst) {
            cboStatus.addItem(x.toString());
        }
    }

    public void loadCboTipoPedido() {
        List<TipoPedido> lst = tpeController.getEmployees();
        cboTipoPedido.removeAllItems();
        for (TipoPedido x : lst) {
            cboTipoPedido.addItem("[" + x.getIdTipoPedido() + "] - " + x.getTipoPedido());
        }
    }

    public void loadCboClientes() {
        List<Cliente> lst = cController.getEmployees();
        cboClientes.removeAllItems();
        for (Cliente c : lst) {
            cboClientes.addItem("[" + c.getIdCliente() + "] - " + c.getNombre() + " " + c.getApellido());
        }
    }

    public void loadCboPersonal() {
        List<Personal> lst = pController.getEmployees();
        cboPersonal.removeAllItems();
        for (Personal p : lst) {
            cboPersonal.addItem("[" + p.getIdPersonal() + "] - " + p.getNombre() + " " + p.getApellidos());
        }
    }

    public void loadCboTipoPago() {
        List<TipoPago> lst = tpaController.getEmployees();
        cboTipoPago.removeAllItems();
        for (TipoPago p : lst) {
            cboTipoPago.addItem("[" + p.getIdTipoPago() + "] - " + p.getTipoPago());
        }
    }

    public void loadCboProductos() {
        List<Menu> lst = mController.getMenus();
        cboProductos.removeAllItems();
        for (Menu s : lst) {
            cboProductos.addItem("[" + s.getIdMenu() + "] - " + s.getTipo());
        }
    }

    public void loadColumnsDetalles() {
        dtmDetalles.addColumn("Producto");
        dtmDetalles.addColumn("Precio Unitario");
        dtmDetalles.addColumn("Cantidad");
        dtmDetalles.addColumn("Subtotal");
        tbDetalles.setModel(dtmDetalles);
    }

    public void loadRowsDetalles() {
        dtmDetalles.setRowCount(0);

        for (DetallePedido x : lstDetalles) {

            Object arreglo[] = new Object[4];
            arreglo[0] = x.getIdMenu().getTipo();
            arreglo[1] = x.getIdMenu().getPrecio();
            arreglo[2] = x.getCantidadPlatos();
            arreglo[3] = x.getSubTotal();
            dtmDetalles.addRow(arreglo);
        }
        this.tbDetalles.setModel(dtmDetalles);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modificarFrm = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        btnAumentar = new javax.swing.JButton();
        btnReducir = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        cboPersonal = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalles = new javax.swing.JTable();
        cboProductos = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        spnUnidades = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        cboTipoPago = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        txtIGV = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnNuevoCliente = new javax.swing.JButton();
        cboClientes = new javax.swing.JComboBox<>();
        txtTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnRestablecer = new javax.swing.JButton();
        btnBackCancel = new javax.swing.JButton();
        lblMesa = new javax.swing.JLabel();
        cboMesa = new javax.swing.JComboBox<>();
        spnFecha = new javax.swing.JSpinner();
        btnAddProduct = new javax.swing.JButton();
        btnReduceProduct = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboTipoPedido = new javax.swing.JComboBox<>();
        btnUnlock = new javax.swing.JButton();
        cboStatus = new javax.swing.JComboBox<>();

        modificarFrm.setSize(new java.awt.Dimension(275, 75));

        btnAumentar.setText("Aumentar");
        btnAumentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAumentarActionPerformed(evt);
            }
        });

        btnReducir.setText("Eliminar");
        btnReducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReducirActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAumentar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReducir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAumentar)
                    .addComponent(btnReducir)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modificarFrmLayout = new javax.swing.GroupLayout(modificarFrm.getContentPane());
        modificarFrm.getContentPane().setLayout(modificarFrmLayout);
        modificarFrmLayout.setHorizontalGroup(
            modificarFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        modificarFrmLayout.setVerticalGroup(
            modificarFrmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(517, 675));

        jPanel1.setPreferredSize(new java.awt.Dimension(517, 675));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("REGISTRAR PEDIDO");

        cboPersonal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPersonal.setEnabled(false);

        jLabel2.setText("Cliente");

        jLabel3.setText("Personal");

        tbDetalles.setAutoCreateRowSorter(true);
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
        jScrollPane1.setViewportView(tbDetalles);

        cboProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Producto");

        spnUnidades.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));

        jLabel5.setText("Unidades");

        jLabel7.setText("Tipo de Pago");

        cboTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Fecha");

        txtObs.setColumns(20);
        txtObs.setRows(3);
        jScrollPane2.setViewportView(txtObs);

        jLabel9.setText("Observación");

        txtIGV.setEditable(false);

        jLabel10.setText("IGV (18%):");

        txtSubtotal.setEditable(false);

        jLabel12.setText("Subtotal:");

        btnNuevoCliente.setText("Nuevo Cliente");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        cboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboClientesActionPerformed(evt);
            }
        });

        txtTotal.setEditable(false);

        jLabel13.setText("Total:");

        btnUpdate.setText("Registrar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRestablecer.setText("Restablecer");
        btnRestablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestablecerActionPerformed(evt);
            }
        });

        btnBackCancel.setText("Cancelar Operacion");
        btnBackCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackCancelActionPerformed(evt);
            }
        });

        lblMesa.setText("Mesa");

        cboMesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMesaActionPerformed(evt);
            }
        });

        spnFecha.setModel(new javax.swing.SpinnerDateModel());

        btnAddProduct.setText("+");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        btnReduceProduct.setText("-");
        btnReduceProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReduceProductActionPerformed(evt);
            }
        });

        jLabel14.setText("Status");

        jLabel15.setText("Tipo de Pedido");

        cboTipoPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTipoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoPedidoActionPerformed(evt);
            }
        });

        btnUnlock.setText("Unlock");
        btnUnlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnlockActionPerformed(evt);
            }
        });

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnRestablecer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBackCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddProduct)
                                .addGap(18, 18, 18)
                                .addComponent(btnReduceProduct))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboClientes, 0, 282, Short.MAX_VALUE)
                                    .addComponent(cboPersonal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnNuevoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(btnUnlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(2, 2, 2))
                            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboMesa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboTipoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spnFecha)
                                    .addComponent(cboTipoPedido, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevoCliente)
                        .addComponent(cboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnUnlock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(spnFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMesa)
                            .addComponent(cboMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(spnUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnAddProduct)
                    .addComponent(btnReduceProduct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnRestablecer)
                    .addComponent(btnBackCancel))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClientesActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboClientesActionPerformed

    private void btnAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAumentarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAumentarActionPerformed

    private void btnReducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReducirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReducirActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnBackCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackCancelActionPerformed
        previousFrame.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackCancelActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        ClientesTable form = new ClientesTable();
        form.setPreviousFrame(this);
        form.setVisible(true);
        setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        int idProducto = getSelectedProducto();
        int cantidad = getSelectedUnidades();

        for (int i = 0; i < lstDetalles.size(); i++) {
            if (lstDetalles.get(i).getIdMenu().getIdMenu() == idProducto) {
                lstDetalles.get(i).setCantidadPlatos(lstDetalles.get(i).getCantidadPlatos() + cantidad);
                lstDetalles.get(i).setSubTotal(lstDetalles.get(i).getIdMenu().getPrecio() * lstDetalles.get(i).getCantidadPlatos());
                System.out.println(lstDetalles.get(i).getCantidadPlatos() + " " + lstDetalles.get(i).getSubTotal());
                loadRowsDetalles();
                updateWndDetalles();
                return;
            }
        }
        DetallePedido ndp = new DetallePedido(mController.findServicioById(idProducto),
                null, cantidad, mController.findServicioById(idProducto).getPrecio() * cantidad);
        lstDetalles.add(ndp);
//        actualizarDetalles();
        loadRowsDetalles();
        updateWndDetalles();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnReduceProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReduceProductActionPerformed
        int idProducto = getSelectedProducto();
        int cantidad = getSelectedUnidades();

        for (int i = 0; i < lstDetalles.size(); i++) {
            if (lstDetalles.get(i).getIdMenu().getIdMenu() == idProducto) {
                if (lstDetalles.get(i).getCantidadPlatos() < cantidad) {
                    int opt = JOptionPane.showConfirmDialog(this, "El valor introducido es mayor a lo que tenemos. Vamos a eliminar todo ¿Estas Seguro?", "", JOptionPane.OK_CANCEL_OPTION);
                    if (opt == JOptionPane.OK_OPTION) {
                        lstDetalles.remove(i);
                    }
                    loadRowsDetalles();
                    updateWndDetalles();
                    return;
                } else if (lstDetalles.get(i).getCantidadPlatos() == cantidad) {
                    lstDetalles.remove(i);
                    loadRowsDetalles();
                    updateWndDetalles();
                    return;

                } else {
                    lstDetalles.get(i).setCantidadPlatos(lstDetalles.get(i).getCantidadPlatos() - cantidad);
                    lstDetalles.get(i).setSubTotal(lstDetalles.get(i).getIdMenu().getPrecio() * lstDetalles.get(i).getCantidadPlatos());
                    System.out.println(lstDetalles.get(i).getCantidadPlatos() + " " + lstDetalles.get(i).getSubTotal());
                    loadRowsDetalles();
                    updateWndDetalles();
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Error, No agregaste nunca el Producto", "Error", JOptionPane.WARNING_MESSAGE);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReduceProductActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int opt = JOptionPane.showConfirmDialog(this, registro.toString() + "a el pedido ¿Estas Seguro?", registro.toString().toUpperCase(), JOptionPane.OK_CANCEL_OPTION);
        if (opt != JOptionPane.OK_OPTION) {
            return;
        }
        actualizarPedido();

        int idPed = 0;
        if (registro == REGISTRO.Registrar) {
            pedidoController.addOrder(IdPedido);
            idPed = pedidoController.findMaxItem().getIdPedido();
        } else {
            pedidoController.updateOrder(IdPedido);
            dpController.deletePerIdPedido(IdPedido);
            idPed = IdPedido.getIdPedido();
        }        
        for (int i = 0; i < lstDetalles.size(); i++) {
            lstDetalles.get(i).setIdPedido(pedidoController.findCustomerById(idPed));
        }
        dpController.addLstDetails(lstDetalles);

//        btnBackCancelActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cboTipoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoPedidoActionPerformed
        update();
// TODO add your handling code here:
    }//GEN-LAST:event_cboTipoPedidoActionPerformed

    private void btnUnlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnlockActionPerformed
        if (btnUnlock.getText() != "Lock") {
            if (ProgramController.logInAdmin(this) != null) {
                cboPersonal.setEnabled(true);
                btnUnlock.setText("Lock");
            }
        } else {
            cboPersonal.setEnabled(false);
            btnUnlock.setText("Unlock");
        }

// TODO add your handling code here:
    }//GEN-LAST:event_btnUnlockActionPerformed

    private void btnRestablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestablecerActionPerformed
        restablecer();
    }//GEN-LAST:event_btnRestablecerActionPerformed

    private void cboMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMesaActionPerformed

    }//GEN-LAST:event_cboMesaActionPerformed

    public int getCodeFromString(String palabra) {
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }

    public int getSelectedProducto() {
        String palabra = cboProductos.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }

    public int getSelectedCliente() {
        String palabra = cboClientes.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }

    public int getSelectedPersonal() {
        String palabra = cboPersonal.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }

    public int getSelectedTipoPago() {
        String palabra = cboTipoPago.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }

    public int getSelectedUnidades() {
        return Integer.parseInt(spnUnidades.getValue().toString());
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
            java.util.logging.Logger.getLogger(RegistrarPedido.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarPedido.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarPedido.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarPedido.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnAumentar;
    private javax.swing.JButton btnBackCancel;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnReduceProduct;
    private javax.swing.JButton btnReducir;
    private javax.swing.JButton btnRestablecer;
    private javax.swing.JButton btnUnlock;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboClientes;
    private javax.swing.JComboBox<String> cboMesa;
    private javax.swing.JComboBox<String> cboPersonal;
    private javax.swing.JComboBox<String> cboProductos;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JComboBox<String> cboTipoPago;
    private javax.swing.JComboBox<String> cboTipoPedido;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JFrame modificarFrm;
    private javax.swing.JSpinner spnFecha;
    private javax.swing.JSpinner spnUnidades;
    private javax.swing.JTable tbDetalles;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
