/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.controller.MesaController;
import com.mycompany.controller.PersonalController;
import com.mycompany.controller.ProgramController;
import static java.lang.Thread.sleep;
import com.mycompany.model.generics.General;
import com.mycompany.model.entities.Mesa;
import com.mycompany.model.entities.Pedido;
import com.mycompany.model.entities.TipoPedido;
import com.mycompany.model.generics.Sha256;
import com.mycompany.services.PersonalService;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author sebap
 */
public class MainMenu extends javax.swing.JFrame {

    private MesaController mc = new MesaController();
    private ProgramController pc = new ProgramController();
    private PersonalController prC = new PersonalController(new PersonalService());
    private SpinnerNumberModel spModel = new SpinnerNumberModel();

    private JFrame previousFrame;
    private Mesa mesaSeleccionada;
    private List<JButton> buttonsMesas = new ArrayList<>();

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        groupButtons();
        clock();
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
                        mc.updateQuantity();
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
                + "Reservas hoy: " + "<br>" + " > " + mc.getQuantityTypePedido(TipoPedido.PEDIDOS.RESERVA) + "<br><br>"
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
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Login = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPasswordEmployee = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnCancelarLogin = new javax.swing.JButton();
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
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });

        btnMesas.setText("Consultar Mesas");
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

        jButton1.setText("Modificar Pedido");

        jButton4.setText("Crear Pedido");

        jButton2.setText("Completar Pedido");

        jButton3.setText("Cancelar Pedido");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnPedidoNuevo.setText("<html>Registrar<br>Pedido</html>");
        btnPedidoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidoNuevoActionPerformed(evt);
            }
        });

        btnReservaNueva.setText("<html>Registrar<br>Reserva</html>");
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
        LoginFrame form = new LoginFrame();
        form.setNext_frame(LoginFrame.NEXT_FRAME.CONFIG_MENU);
        form.setPreviousFrame(this);
        OptionsMainMenu.dispose();
        form.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracionesActionPerformed

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed
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
        lblPedidoHoraLibre.setText(txtLibreTiempo);
        //Show Nombre Cliente
        lblPedidoCliente.setText(txtNombreCliente);
        //Set Pedido Codigo
        lblPedidoCodigo.setText(txtPedido);
    }

    private void showMesaInfo(int btnNumber) {
        int valueGrupos = (Integer) spnGrupos.getValue();
        mesaSeleccionada = mc.get(btnNumber + (20 * (valueGrupos - 1)) - 1);
        lblMesaTitle.setText(mesaSeleccionada.getNombreMesa());

        lblStatus.setText(mesaSeleccionada.getMesa_status().toString());

        if (mesaSeleccionada.getMesa_status() == Mesa.MESA_STATUS.OCUPADA) {
            if (mesaSeleccionada.getIdPedido() != null) {
                if (mesaSeleccionada.getIdPedido().getIdTipoPedido().getTipoPedido() == TipoPedido.PEDIDOS.RESERVA) {
                    setShowMesaInfo(mesaSeleccionada.getFechaHoraMesa().format(pc.getFormatDayTime()),
                            "Hora: " + mesaSeleccionada.getFechaHoraMesa().plusMinutes(pc.getTiempoEstandarEnMesa()).format(pc.getFormatTime()),
                            mesaSeleccionada.getIdCliente().getNombre() + " " + mesaSeleccionada.getIdCliente().getApellido(),
                            "#" + mesaSeleccionada.getIdPedido().getIdPedido());
                } else {
                    setShowMesaInfo("No hay Reserva",
                            "Hora: " + mesaSeleccionada.getFechaHoraMesa().format(pc.getFormatTime()),
                            mesaSeleccionada.getIdCliente().getNombre() + " " + mesaSeleccionada.getIdCliente().getApellido(),
                            "#" + mesaSeleccionada.getIdPedido().getIdPedido());
                }
            } else {
                setShowMesaInfo("No hay Reserva", "Hora: 00:00:00", "Sin Cliente", "#xxxxxxxxxxx");

                System.out.println("Error, Ocupada y sin pedido, Liberando...");

                mesaSeleccionada.LiberarMesa();
                showMesaInfo(btnNumber);
            }
        } else if (mesaSeleccionada.getMesa_status() == Mesa.MESA_STATUS.LIBRE) {
            setShowMesaInfo("No hay Reserva", "Hora: 00:00:00", "Sin Cliente", "#xxxxxxxxxxx");
        }
        Login.setVisible(true);
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
        if (prC.findPersonalByPassword(Sha256.sha256(new String(txtPasswordEmployee.getPassword()))) != null) {
            ShowMesaInfo.setVisible(true);
            Login.dispose();
        } else {
            JOptionPane.showMessageDialog(Login, "Credencial Invalida", "Error", JOptionPane.WARNING_MESSAGE);
        }

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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancelarLogin;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnCloseSesion;
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
    private javax.swing.JButton btnPedidoNuevo;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnReservaNueva;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPasswordField txtPasswordEmployee;
    // End of variables declaration//GEN-END:variables
}
