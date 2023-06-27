package com.mycompany.view;

import com.mycompany.controller.MesaController;
import com.mycompany.controller.PedidoController;
import com.mycompany.controller.PersonalController;
import com.mycompany.controller.ProgramController;
import static java.lang.Thread.sleep;
import com.mycompany.model.generics.General;
import com.mycompany.model.entities.Mesa;
import com.mycompany.model.entities.Pedido;
import com.mycompany.model.entities.Personal;
import com.mycompany.model.entities.TipoPedido;
import com.mycompany.model.generics.Print;
import com.mycompany.model.generics.Sha256;
import com.mycompany.services.PedidoService;
import com.mycompany.services.PersonalService;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class MainMenu extends javax.swing.JFrame {

    private MesaController mc = MesaController.getMesaController();
    private ProgramController pc = ProgramController.getProgramController();
    private PersonalController prC = new PersonalController(new PersonalService());
    private PedidoController peC = new PedidoController(new PedidoService());
    private SpinnerNumberModel spModel = new SpinnerNumberModel();

    private JFrame previousFrame;
    private Mesa mesaSeleccionada;
    private Pedido pedidoSeleccionado;
    private List<JButton> buttonsMesas = new ArrayList<>();

    private boolean iniciado = false;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        groupButtons();
        clock();
        iniciado = true;
    }

    public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    for (;;) {
                        lblClock.setText(General.clock());
                        updateLblInfo();
                        setSpinnerModel();
                        updateButtons();
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }

    public void groupButtons() {
        buttonsMesas.clear();
        buttonsMesas.add(btnMesa1);
        buttonsMesas.add(btnMesa2);
        buttonsMesas.add(btnMesa3);
        buttonsMesas.add(btnMesa4);
        buttonsMesas.add(btnMesa5);
        buttonsMesas.add(btnMesa6);
        buttonsMesas.add(btnMesa7);
        buttonsMesas.add(btnMesa8);
        buttonsMesas.add(btnMesa9);
        buttonsMesas.add(btnMesa10);
        buttonsMesas.add(btnMesa11);
        buttonsMesas.add(btnMesa12);
        buttonsMesas.add(btnMesa13);
        buttonsMesas.add(btnMesa14);
        buttonsMesas.add(btnMesa15);
        buttonsMesas.add(btnMesa16);
        buttonsMesas.add(btnMesa17);
        buttonsMesas.add(btnMesa18);
        buttonsMesas.add(btnMesa19);
        buttonsMesas.add(btnMesa20);
    }

    public void updateButtons() {
        int cantMesas = pc.getCantidadMesas();
        int pagina = (int) spnGrupos.getValue();
        int exceso = cantMesas - (20 * (pagina - 1));

        for (int i = 0; i < 20; i++) {
            int valorMesa = (i + 1) + (20 * (pagina - 1));
            if (valorMesa < 100) {
                buttonsMesas.get(i).setFont(new Font("Segoe UI", Font.PLAIN, 12));
            } else {
                buttonsMesas.get(i).setFont(new Font("Segoe UI", Font.PLAIN, 9));
            }
            buttonsMesas.get(i).setText("#" + valorMesa);
            if (i < exceso) {
                buttonsMesas.get(i).setEnabled(true);
            } else {
                buttonsMesas.get(i).setEnabled(false);
            }
        }

    }

    public void setSpinnerModel() {
        pc.cargar();
        spModel.setMinimum(1);
        spModel.setMaximum((int) (Math.floor(pc.getCantidadMesas() / 20) + 1));
        spModel.setStepSize(1);
        if ((int) spnGrupos.getValue() > (int) (Math.floor(pc.getCantidadMesas() / 20) + 1)) {
            spModel.setValue((int) (Math.floor(pc.getCantidadMesas() / 20) + 1));
        } else {
            spModel.setValue(spnGrupos.getValue());

        }
        spnGrupos.setModel(spModel);
    }

    public void updateLblInfo() {
        lblInfo.setText("<html>"
                + "M. disponible: " + "<br>" + " > Mesas " + mc.getQuantityStatus(Mesa.MESA_STATUS.OCUPADA) + "/" + mc.size() + "<br>"
                + "M. Libre en: " + "<br>" + " > " + mc.getProximaMesaLibre() + " min" + "<br>"
                + "Reservas hoy: " + "<br>" + " > " + mc.getQTablebyTypeOrder(TipoPedido.RESERVA) + "<br><br>"
                + "Pedidos Pend.: " + "<br>" + " > " + mc.getQuantityStatusPedido(Pedido.PEDIDO_STATUS.PENDIENTE) + "<br>"
                + "Pedidos Envio: " + "<br>" + " > " + mc.getQuantityStatusPedido(Pedido.PEDIDO_STATUS.EN_ENVIO)
                + "</html>"
        );
        prgBrAforo.setMaximum(mc.size());
        prgBrAforo.setValue(mc.getQuantityStatus(Mesa.MESA_STATUS.OCUPADA));
        prgBrAforo.setStringPainted(true);
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

        OptionsMainMenu = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        btnConfiguraciones = new javax.swing.JButton();
        btnPedidos = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnMesas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnCloseSesion = new javax.swing.JButton();
        ShowMesaInfo = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        lblMesaTitle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblReservaTiempo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPedidoCliente = new javax.swing.JLabel();
        lblPedidoCodigo = new javax.swing.JLabel();
        lblPedidoHoraLibre = new javax.swing.JLabel();
        btnNewShow = new javax.swing.JButton();
        btnModifyShow = new javax.swing.JButton();
        btnCleanShow = new javax.swing.JButton();
        btnCancelShow = new javax.swing.JButton();
        Login = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPasswordEmployee = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnCancelarLogin = new javax.swing.JButton();
        ShowOrders = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        btnCompleteOrders = new javax.swing.JButton();
        btnCancelOrders = new javax.swing.JButton();
        btnModifyOrders = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbOrders = new javax.swing.JTable();
        cboOrders = new javax.swing.JComboBox<>();
        btnBackOrders = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnPedidoNuevo = new javax.swing.JButton();
        btnReservaNueva = new javax.swing.JButton();
        lblClock = new javax.swing.JLabel();
        btnMenuOptions = new javax.swing.JButton();
        lblInfo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        prgBrAforo = new javax.swing.JProgressBar();
        btnMesa1 = new javax.swing.JButton();
        btnMesa2 = new javax.swing.JButton();
        btnMesa3 = new javax.swing.JButton();
        btnMesa4 = new javax.swing.JButton();
        btnMesa5 = new javax.swing.JButton();
        btnMesa6 = new javax.swing.JButton();
        btnMesa7 = new javax.swing.JButton();
        btnMesa8 = new javax.swing.JButton();
        btnMesa9 = new javax.swing.JButton();
        btnMesa10 = new javax.swing.JButton();
        btnMesa11 = new javax.swing.JButton();
        btnMesa12 = new javax.swing.JButton();
        btnMesa13 = new javax.swing.JButton();
        btnMesa14 = new javax.swing.JButton();
        btnMesa15 = new javax.swing.JButton();
        btnMesa16 = new javax.swing.JButton();
        btnMesa17 = new javax.swing.JButton();
        btnMesa18 = new javax.swing.JButton();
        btnMesa19 = new javax.swing.JButton();
        btnMesa20 = new javax.swing.JButton();
        spnGrupos = new javax.swing.JSpinner();

        OptionsMainMenu.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        OptionsMainMenu.setResizable(false);
        OptionsMainMenu.setSize(new java.awt.Dimension(200, 350));

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 300));

        btnConfiguraciones.setText("Configuraciones");
        btnConfiguraciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionesActionPerformed(evt);
            }
        });

        btnPedidos.setText("Consultar Pedidos");
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });

        btnReservas.setText("Consultar Reservas");
        btnReservas.setEnabled(false);
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });

        btnMesas.setText("Consultar Mesas");
        btnMesas.setEnabled(false);
        btnMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesasActionPerformed(evt);
            }
        });

        btnClientes.setText("Consultar Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCloseSesion.setText("Cerrar Sesión");
        btnCloseSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCloseSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReservas, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(btnMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfiguraciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConfiguraciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReservas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMesas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCloseSesion)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout OptionsMainMenuLayout = new javax.swing.GroupLayout(OptionsMainMenu.getContentPane());
        OptionsMainMenu.getContentPane().setLayout(OptionsMainMenuLayout);
        OptionsMainMenuLayout.setHorizontalGroup(
            OptionsMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
            .addGroup(OptionsMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
        );
        OptionsMainMenuLayout.setVerticalGroup(
            OptionsMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(OptionsMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(OptionsMainMenuLayout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        ShowMesaInfo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ShowMesaInfo.setResizable(false);
        ShowMesaInfo.setSize(new java.awt.Dimension(215, 350));
        ShowMesaInfo.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                ShowMesaInfoWindowLostFocus(evt);
            }
        });

        jPanel3.setPreferredSize(new java.awt.Dimension(200, 300));

        lblMesaTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMesaTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMesaTitle.setText("Mesa #xx");

        jLabel3.setText("Estatus:");

        jLabel4.setText("Reserva para las:");

        lblStatus.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblStatus.setText("Libre");

        lblReservaTiempo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblReservaTiempo.setText("No hay Reserva");

        jLabel5.setText("Pedido:");

        lblPedidoCliente.setText("Sin Cliente");

        lblPedidoCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPedidoCodigo.setText("#xxxxx");

        lblPedidoHoraLibre.setText("Hora: XX:XX:XX");

        btnNewShow.setText("Crear Pedido");
        btnNewShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewShowActionPerformed(evt);
            }
        });

        btnModifyShow.setText("Consultar Pedidos");
        btnModifyShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyShowActionPerformed(evt);
            }
        });

        btnCleanShow.setText("Liberar Mesa");
        btnCleanShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanShowActionPerformed(evt);
            }
        });

        btnCancelShow.setText("Cancelar Mesa");
        btnCancelShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMesaTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblReservaTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPedidoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCleanShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModifyShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPedidoHoraLibre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMesaTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblReservaTiempo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblPedidoCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPedidoCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPedidoHoraLibre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewShow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModifyShow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCleanShow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelShow)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout ShowMesaInfoLayout = new javax.swing.GroupLayout(ShowMesaInfo.getContentPane());
        ShowMesaInfo.getContentPane().setLayout(ShowMesaInfoLayout);
        ShowMesaInfoLayout.setHorizontalGroup(
            ShowMesaInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowMesaInfoLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ShowMesaInfoLayout.setVerticalGroup(
            ShowMesaInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowMesaInfoLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Login.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Login.setAlwaysOnTop(true);
        Login.setResizable(false);
        Login.setSize(new java.awt.Dimension(215, 175));
        Login.setType(java.awt.Window.Type.POPUP);

        jPanel4.setPreferredSize(new java.awt.Dimension(200, 150));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("LOGIN");

        jLabel9.setText("CODIGO");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelarLogin.setText("Cancelar");
        btnCancelarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPasswordEmployee)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(btnCancelarLogin)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPasswordEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelarLogin))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login.getContentPane());
        Login.getContentPane().setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        ShowOrders.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ShowOrders.setResizable(false);
        ShowOrders.setSize(new java.awt.Dimension(500, 350));
        ShowOrders.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                ShowOrdersWindowLostFocus(evt);
            }
        });

        jPanel5.setPreferredSize(new java.awt.Dimension(454, 300));

        btnCompleteOrders.setText("Completar");
        btnCompleteOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteOrdersActionPerformed(evt);
            }
        });

        btnCancelOrders.setText("Cancelar");
        btnCancelOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelOrdersActionPerformed(evt);
            }
        });

        btnModifyOrders.setText("Modificar");
        btnModifyOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyOrdersActionPerformed(evt);
            }
        });

        tbOrders.setModel(new javax.swing.table.DefaultTableModel(
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
        tbOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbOrdersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbOrders);

        cboOrders.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBackOrders.setText("Volver");
        btnBackOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackOrdersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnCompleteOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnModifyOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBackOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboOrders, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompleteOrders)
                    .addComponent(btnCancelOrders)
                    .addComponent(btnModifyOrders)
                    .addComponent(btnBackOrders))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ShowOrdersLayout = new javax.swing.GroupLayout(ShowOrders.getContentPane());
        ShowOrders.getContentPane().setLayout(ShowOrdersLayout);
        ShowOrdersLayout.setHorizontalGroup(
            ShowOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ShowOrdersLayout.setVerticalGroup(
            ShowOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowOrdersLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        btnPedidoNuevo.setText("<html>Registrar<br>Pedido</html>");
        btnPedidoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidoNuevoActionPerformed(evt);
            }
        });

        btnReservaNueva.setText("<html>Registrar<br>Reserva</html>");
        btnReservaNueva.setEnabled(false);
        btnReservaNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservaNuevaActionPerformed(evt);
            }
        });

        lblClock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClock.setText("Time");

        btnMenuOptions.setText("Options");
        btnMenuOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuOptionsActionPerformed(evt);
            }
        });

        lblInfo.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblInfo.setText("STATUS");
        lblInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel1.setText("Grupos");

        btnMesa1.setText("#1");
        btnMesa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa1ActionPerformed(evt);
            }
        });

        btnMesa2.setText("#2");
        btnMesa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa2ActionPerformed(evt);
            }
        });

        btnMesa3.setText("#3");
        btnMesa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa3ActionPerformed(evt);
            }
        });

        btnMesa4.setText("#4");
        btnMesa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa4ActionPerformed(evt);
            }
        });

        btnMesa5.setText("#5");
        btnMesa5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa5ActionPerformed(evt);
            }
        });

        btnMesa6.setText("#6");
        btnMesa6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa6ActionPerformed(evt);
            }
        });

        btnMesa7.setText("#7");
        btnMesa7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa7ActionPerformed(evt);
            }
        });

        btnMesa8.setText("#8");
        btnMesa8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa8ActionPerformed(evt);
            }
        });

        btnMesa9.setText("#9");
        btnMesa9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa9ActionPerformed(evt);
            }
        });

        btnMesa10.setText("#10");
        btnMesa10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa10ActionPerformed(evt);
            }
        });

        btnMesa11.setText("#11");
        btnMesa11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa11ActionPerformed(evt);
            }
        });

        btnMesa12.setText("#12");
        btnMesa12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa12ActionPerformed(evt);
            }
        });

        btnMesa13.setText("#13");
        btnMesa13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa13ActionPerformed(evt);
            }
        });

        btnMesa14.setText("#14");
        btnMesa14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa14ActionPerformed(evt);
            }
        });

        btnMesa15.setText("#15");
        btnMesa15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa15ActionPerformed(evt);
            }
        });

        btnMesa16.setText("#16");
        btnMesa16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa16ActionPerformed(evt);
            }
        });

        btnMesa17.setText("#17");
        btnMesa17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa17ActionPerformed(evt);
            }
        });

        btnMesa18.setText("#18");
        btnMesa18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa18ActionPerformed(evt);
            }
        });

        btnMesa19.setText("#19");
        btnMesa19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa19ActionPerformed(evt);
            }
        });

        btnMesa20.setText("#20");
        btnMesa20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa20ActionPerformed(evt);
            }
        });

        spnGrupos.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnGrupos.setValue(1);
        spnGrupos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnGruposStateChanged(evt);
            }
        });
        spnGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spnGruposMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(prgBrAforo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnGrupos))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMesa1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMesa6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMesa11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMesa16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMesa20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(btnPedidoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnReservaNueva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMenuOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnMenuOptions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReservaNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPedidoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblClock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMesa1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMesa6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMesa11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMesa16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMesa20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(spnGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(prgBrAforo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuOptionsActionPerformed
        OptionsMainMenu.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuOptionsActionPerformed

    private void btnPedidoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidoNuevoActionPerformed
        if (ProgramController.logInUser(this) != null) {
            RegistrarPedido form = new RegistrarPedido();
            form.setPreviousFrame(this);
            form.setRegistroValue(RegistrarPedido.REGISTRO.Registrar, null);
            form.setVisible(true);
            setVisible(false);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_btnPedidoNuevoActionPerformed

    private void btnReservaNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaNuevaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservaNuevaActionPerformed

    private void btnCloseSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseSesionActionPerformed
        previousFrame.setVisible(true);
        OptionsMainMenu.dispose();
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCloseSesionActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        OptionsMainMenu.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfiguracionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionesActionPerformed
        ConfigFrame form = new ConfigFrame();
        form.setPreviousFrame(this);
        OptionsMainMenu.dispose();
        form.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracionesActionPerformed

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed
        if(ProgramController.logInUser(this)==null)
        {
            return;
        }
        PedidosTable form = new PedidosTable();
        form.setPreviousFrame(this);
        OptionsMainMenu.dispose();
        form.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPedidosActionPerformed

    private void btnMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesasActionPerformed

    private void btnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservasActionPerformed

    private void setShowMesaInfo(String txtReservaTiempo, String txtLibreTiempo, String txtNombreCliente, String txtPedido) {
        //Show Hora de la Mesa
        lblReservaTiempo.setText(txtReservaTiempo);
        //Show Hora de Liberación
        lblPedidoHoraLibre.setText("Hora: " + txtLibreTiempo);
        //Show Nombre Cliente
        lblPedidoCliente.setText("Cliente: " + txtNombreCliente);
        //Set Pedido Codigo
        lblPedidoCodigo.setText(txtPedido);
    }

    private void showMesaInfo(int btnNumber) {
        if (!iniciado) {
            return;
        }
        int valueGrupos = (Integer) spnGrupos.getValue();
        mesaSeleccionada = mc.get(btnNumber + (20 * (valueGrupos - 1)) - 1);
        lblMesaTitle.setText(mesaSeleccionada.getNombreMesa());

        lblStatus.setText(mesaSeleccionada.getMesa_status().toString());

        String txtReservaHora = "No hay Reserva";
        String txtLibreHora = "Hora: 00:00:00";
        String txtCliente = "Sin Cliente";
        String txtPedido = "#xxxxxxxxxxx";

        List<Pedido> lstPedidos = peC.getStatusTableOrders(Pedido.PEDIDO_STATUS.PENDIENTE, mesaSeleccionada);

        if (lstPedidos == null || lstPedidos.size() == 0) {
            //Sin Pedidos
            if (mesaSeleccionada.getMesa_status() != Mesa.MESA_STATUS.LIBRE) {
                System.out.println("Error, Ocupada y sin pedido, Liberando...");

                mesaSeleccionada.LiberarMesa();
                showMesaInfo(btnNumber);
                return;
            }

        } else if (lstPedidos.size() == 1) {
            //Con 1 Pedido
            Pedido pedido = lstPedidos.get(0);
            LocalDateTime localdatetime = LocalDateTime.parse(pedido.getFechaPedido(), pc.getFormatDayTime());
            String time;
            if (pedido.getIdTipoPedido().getIdTipoPedido() == TipoPedido.RESERVA) {
                time = localdatetime.format(pc.getFormatTime());
                txtReservaHora = (time);

                localdatetime = localdatetime.plusMinutes(pc.getTiempoEstandarEnMesa());
                time = localdatetime.format(pc.getFormatTime());
                txtLibreHora = (time);
            }

            time = localdatetime.format(pc.getFormatTime());
            txtLibreHora = (time);

            txtCliente = pedido.getIdCliente().getNombre() + " " + pedido.getIdCliente().getApellido();
            txtPedido = "#" + pedido.getIdPedido();

            if (mesaSeleccionada.getMesa_status() != Mesa.MESA_STATUS.OCUPADA) {
                System.out.println("Error, Libre y con pedido, Ocupando...");
                mesaSeleccionada.OcuparMesa();
                showMesaInfo(btnNumber);
                return;
            }
        } else {
            //Con 2 Pedidos
            Pedido reservaMasPronta = null;
            Pedido primerPedido = null;
            for (Pedido x : lstPedidos) {
                //Gestion de Reserva
                if (x.getIdTipoPedido().getIdTipoPedido() == TipoPedido.RESERVA) {
                    if (reservaMasPronta == null) {
                        reservaMasPronta = x;
                    } else {
                        LocalDateTime ldtNew = LocalDateTime.parse(x.getFechaPedido(), pc.getFormatDayTime());
                        LocalDateTime ldtOld = LocalDateTime.parse(reservaMasPronta.getFechaPedido(), pc.getFormatDayTime());
                        Duration dNew = Duration.between(ldtNew, LocalDateTime.now());
                        Duration dOld = Duration.between(ldtOld, LocalDateTime.now());
                        if (dNew.toMinutes() < dOld.toMinutes()) {
                            reservaMasPronta = x;
                        }
                    }
                } else {
                    //Gestion de Pendiente
                    if (primerPedido == null) {
                        primerPedido = x;
                    } else {
                        LocalDateTime ldtNew = LocalDateTime.parse(x.getFechaPedido(), pc.getFormatDayTime());
                        LocalDateTime ldtOld = LocalDateTime.parse(primerPedido.getFechaPedido(), pc.getFormatDayTime());
                        Duration dNew = Duration.between(ldtNew, LocalDateTime.now());
                        Duration dOld = Duration.between(ldtOld, LocalDateTime.now());
                        if (dNew.toMinutes() < dOld.toMinutes()) {
                            primerPedido = x;
                        }
                    }
                }
            }

            if (reservaMasPronta != null) {
                LocalDateTime localdatetime = LocalDateTime.parse(reservaMasPronta.getFechaPedido(), pc.getFormatDayTime());
                String time;

                time = localdatetime.format(pc.getFormatTime());
                txtReservaHora = ("Hora: " + time);

                localdatetime = localdatetime.plusMinutes(pc.getTiempoEstandarEnMesa());
                time = localdatetime.format(pc.getFormatTime());
                txtLibreHora = ("Hora: " + time);

                txtCliente = reservaMasPronta.getIdCliente().getApellido() + " (+)";
                txtPedido = "#" + reservaMasPronta.getIdPedido();
            }
            if (primerPedido != null) {
                LocalDateTime localdatetime = LocalDateTime.parse(primerPedido.getFechaPedido(), pc.getFormatDayTime());
                String time;
                time = localdatetime.format(pc.getFormatTime());
                txtLibreHora = ("Hora: " + time);

                txtCliente = primerPedido.getIdCliente().getApellido() + " (+)";
                txtPedido = "#" + primerPedido.getIdPedido();
            }

            if (mesaSeleccionada.getMesa_status() != Mesa.MESA_STATUS.OCUPADA) {
                System.out.println("Error, Libre y con pedido, Ocupando...");
                mesaSeleccionada.OcuparMesa();
                showMesaInfo(btnNumber);
                return;
            }
        }

        setShowMesaInfo(txtReservaHora, txtLibreHora, txtCliente, txtPedido);

        if (ProgramController.logInUser(this)
                != null) {
            ShowMesaInfo.setVisible(true);
        }
    }
    private void btnMesa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa1ActionPerformed
        int numeroMesa = 1;
        showMesaInfo(numeroMesa);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa1ActionPerformed

    private void spnGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnGruposMouseClicked
// TODO add your handling code here:
    }//GEN-LAST:event_spnGruposMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarLoginActionPerformed
        Login.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarLoginActionPerformed

    private void btnMesa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa2ActionPerformed
        int numeroMesa = 2;
        showMesaInfo(numeroMesa);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa2ActionPerformed

    private void btnMesa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa3ActionPerformed
        int numeroMesa = 3;
        showMesaInfo(numeroMesa);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa3ActionPerformed

    private void btnMesa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa4ActionPerformed
        int numeroMesa = 4;
        showMesaInfo(numeroMesa);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa4ActionPerformed

    private void btnMesa5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa5ActionPerformed
        int numeroMesa = 5;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa5ActionPerformed

    private void btnMesa6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa6ActionPerformed
        int numeroMesa = 6;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa6ActionPerformed

    private void btnMesa7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa7ActionPerformed
        int numeroMesa = 7;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa7ActionPerformed

    private void btnMesa8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa8ActionPerformed
        int numeroMesa = 8;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa8ActionPerformed

    private void btnMesa9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa9ActionPerformed
        int numeroMesa = 9;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa9ActionPerformed

    private void btnMesa10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa10ActionPerformed
        int numeroMesa = 10;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa10ActionPerformed

    private void btnMesa11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa11ActionPerformed
        int numeroMesa = 11;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa11ActionPerformed

    private void btnMesa12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa12ActionPerformed
        int numeroMesa = 12;
        showMesaInfo(numeroMesa);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa12ActionPerformed

    private void btnMesa13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa13ActionPerformed
        int numeroMesa = 13;
        showMesaInfo(numeroMesa);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa13ActionPerformed

    private void btnMesa14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa14ActionPerformed
        int numeroMesa = 14;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa14ActionPerformed

    private void btnMesa15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa15ActionPerformed
        int numeroMesa = 15;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa15ActionPerformed

    private void btnMesa16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa16ActionPerformed
        int numeroMesa = 16;
        showMesaInfo(numeroMesa); // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa16ActionPerformed

    private void btnMesa17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa17ActionPerformed
        int numeroMesa = 17;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa17ActionPerformed

    private void btnMesa18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa18ActionPerformed
        int numeroMesa = 18;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa18ActionPerformed

    private void btnMesa19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa19ActionPerformed
        int numeroMesa = 19;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa19ActionPerformed

    private void btnMesa20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa20ActionPerformed
        int numeroMesa = 20;
        showMesaInfo(numeroMesa);// TODO add your handling code here:
    }//GEN-LAST:event_btnMesa20ActionPerformed

    private void spnGruposStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnGruposStateChanged
        updateButtons();
        // TODO add your handling code here:
    }//GEN-LAST:event_spnGruposStateChanged

    private void ShowMesaInfoWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ShowMesaInfoWindowLostFocus
        ShowMesaInfo.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_ShowMesaInfoWindowLostFocus

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        ClientesTable form = new ClientesTable();
        form.setPreviousFrame(this);
        setVisible(false);
        OptionsMainMenu.dispose();
        form.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        mc.updateController();
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnNewShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewShowActionPerformed
        if (!iniciado) {
            return;
        }
        RegistrarPedido form = new RegistrarPedido(mesaSeleccionada);
        form.setPreviousFrame(this);
        form.setRegistroValue(RegistrarPedido.REGISTRO.Registrar, null);
        form.setVisible(true);
        setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewShowActionPerformed

    private void btnCleanShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanShowActionPerformed
        if (!Print.warnConf("¿Desea Cambiar el estado de TODOS los pedidos pendientes de la Mesa a 'COMPLETO'?", this)) {
            return;
        }
        mesaSeleccionada.LiberarMesa();
        List<Pedido> lst = peC.getStatusTableOrders(Pedido.PEDIDO_STATUS.PENDIENTE, mesaSeleccionada);
        for (int i = 0; i < lst.size(); i++) {
            lst.get(i).setStatus(Pedido.PEDIDO_STATUS.COMPLETO);
            peC.updateOrder(lst.get(i));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCleanShowActionPerformed

    private void btnCancelShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelShowActionPerformed
        if (!Print.warnConf("¿Desea Cambiar el estado de TODOS los pedidos pendientes de la Mesa a 'CANCELADO'?", this)) {
            return;
        }
        mesaSeleccionada.LiberarMesa();
        List<Pedido> lst = peC.getStatusTableOrders(Pedido.PEDIDO_STATUS.PENDIENTE, mesaSeleccionada);
        Print.log(lst);
        for (int i = 0; i < lst.size(); i++) {
            lst.get(i).setStatus(Pedido.PEDIDO_STATUS.CANCELADO);
            peC.updateOrder(lst.get(i));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelShowActionPerformed

    private void setShowOrder(List<Pedido> lst) {
        DefaultTableModel dtm = new DefaultTableModel();
        List<String> columns = Arrays.asList("Id", "Cliente", "Total", "Fecha", "Estado");
        for (String x : columns) {
            dtm.addColumn(x);
        }
        dtm.setRowCount(0);
        try {
            for (Pedido p : lst) {
                Object[] vec = new Object[6];
                vec[0] = p.getIdPedido();
                vec[1] = p.getIdCliente().getNombre() + " " + p.getIdCliente().getApellido();
                vec[2] = p.getTotal();
                vec[3] = p.getFechaPedido();
                vec[4] = p.getStatus().toString();
                dtm.addRow(vec);
            }
            tbOrders.setModel(dtm);
        } catch (Exception ex) {
            System.out.println("Error");
        }

        cboOrders.removeAllItems();
        for (Pedido p : lst) {
            cboOrders.addItem("[" + p.getIdPedido() + "] - " + p.getIdCliente().getApellido() + " - " + p.getFechaPedido() + " - S/." + p.getTotal());
        }
    }

    private void btnModifyShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyShowActionPerformed
        if (!iniciado) {
            return;
        }
        setShowOrder(peC.getStatusTableOrders(Pedido.PEDIDO_STATUS.PENDIENTE, mesaSeleccionada));

        ShowOrders.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModifyShowActionPerformed

    private int getSelectedOrder() {
        String palabra = cboOrders.getSelectedItem().toString();
        int indice = palabra.indexOf(']');
        return Integer.parseInt(palabra.substring(1, indice));
    }

    private void btnCompleteOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteOrdersActionPerformed
        if (!Print.warnConf("¿Desea Cambiar el estado del pedido #" + getSelectedOrder() + " a 'COMPLETADO'?", ShowOrders)) {
            return;
        }
        Pedido pedido = peC.findCustomerById(getSelectedOrder());
        pedido.setStatus(Pedido.PEDIDO_STATUS.COMPLETO);
        peC.updateOrder(pedido);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCompleteOrdersActionPerformed

    private void btnCancelOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelOrdersActionPerformed
        if (!Print.warnConf("¿Desea Cambiar el estado del pedido #" + getSelectedOrder() + " a 'CANCELADO'?", ShowOrders)) {
            return;
        }
        Pedido pedido = peC.findCustomerById(getSelectedOrder());
        pedido.setStatus(Pedido.PEDIDO_STATUS.CANCELADO);
        peC.updateOrder(pedido);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelOrdersActionPerformed

    private void btnModifyOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyOrdersActionPerformed
        RegistrarPedido form = new RegistrarPedido();
        form.setPreviousFrame(this);
        form.setRegistroValue(RegistrarPedido.REGISTRO.Actualizar, peC.findCustomerById(getSelectedOrder()));
        form.setVisible(true);
        setVisible(false);
        ShowOrders.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModifyOrdersActionPerformed

    private void ShowOrdersWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ShowOrdersWindowLostFocus
        // TODO add your handling code here:
    }//GEN-LAST:event_ShowOrdersWindowLostFocus

    private void btnBackOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackOrdersActionPerformed
        ShowOrders.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackOrdersActionPerformed

    private void tbOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbOrdersMouseClicked
        int id = (int) tbOrders.getModel().getValueAt(tbOrders.getSelectedRow(), 0);
        Pedido p = peC.findCustomerById(id);
        cboOrders.setSelectedItem("[" + p.getIdPedido() + "] - " + p.getIdCliente().getApellido() + " - " + p.getFechaPedido() + " - S/." + p.getTotal());
// TODO add your handling code here:
    }//GEN-LAST:event_tbOrdersMouseClicked

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
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Login;
    private javax.swing.JFrame OptionsMainMenu;
    private javax.swing.JFrame ShowMesaInfo;
    private javax.swing.JFrame ShowOrders;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBackOrders;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancelOrders;
    private javax.swing.JButton btnCancelShow;
    private javax.swing.JButton btnCancelarLogin;
    private javax.swing.JButton btnCleanShow;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnCloseSesion;
    private javax.swing.JButton btnCompleteOrders;
    private javax.swing.JButton btnConfiguraciones;
    private javax.swing.JButton btnMenuOptions;
    private javax.swing.JButton btnMesa1;
    private javax.swing.JButton btnMesa10;
    private javax.swing.JButton btnMesa11;
    private javax.swing.JButton btnMesa12;
    private javax.swing.JButton btnMesa13;
    private javax.swing.JButton btnMesa14;
    private javax.swing.JButton btnMesa15;
    private javax.swing.JButton btnMesa16;
    private javax.swing.JButton btnMesa17;
    private javax.swing.JButton btnMesa18;
    private javax.swing.JButton btnMesa19;
    private javax.swing.JButton btnMesa2;
    private javax.swing.JButton btnMesa20;
    private javax.swing.JButton btnMesa3;
    private javax.swing.JButton btnMesa4;
    private javax.swing.JButton btnMesa5;
    private javax.swing.JButton btnMesa6;
    private javax.swing.JButton btnMesa7;
    private javax.swing.JButton btnMesa8;
    private javax.swing.JButton btnMesa9;
    private javax.swing.JButton btnMesas;
    private javax.swing.JButton btnModifyOrders;
    private javax.swing.JButton btnModifyShow;
    private javax.swing.JButton btnNewShow;
    private javax.swing.JButton btnPedidoNuevo;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnReservaNueva;
    private javax.swing.JButton btnReservas;
    private javax.swing.JComboBox<String> cboOrders;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblMesaTitle;
    private javax.swing.JLabel lblPedidoCliente;
    private javax.swing.JLabel lblPedidoCodigo;
    private javax.swing.JLabel lblPedidoHoraLibre;
    private javax.swing.JLabel lblReservaTiempo;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JProgressBar prgBrAforo;
    private javax.swing.JSpinner spnGrupos;
    private javax.swing.JTable tbOrders;
    private javax.swing.JPasswordField txtPasswordEmployee;
    // End of variables declaration//GEN-END:variables
}
