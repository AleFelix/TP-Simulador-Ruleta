package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class VistaMesaAdaptableRapida extends JFrame implements IVistaMesa {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtNombre;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField textNumDia;
	private JTextField textNumTirada;
	private JTextField textSaldo;

	private JRadioButton rdbtnRojo;

	private JRadioButton rdbtnNegro;

	private JSpinner spinnerApuesta;

	private JSpinner spinnerBalanceInicial;

	private JSpinner spinnerObjetivo;

	private JRadioButton rdbtnNewRadioButton;

	private JRadioButton rdbtnSinCero;

	private JSpinner spinnerMaxTiradas;

	private JSpinner spinnerMaxDias;

	private JSlider sliderVelocidad;

	private JLabel lblNumeroobtenido;

	private XYSeriesCollection seriesCollection;

	private String nombre;

	private int objetivo;

	private JFreeChart lineChart;

	private Mesa mesa;

	private XYSeries serie;

	private boolean jugando;

	private boolean hastaGanar;

	private DefaultPieDataset datasetColores;

	private XYSeriesCollection seriesIntentosCollection;

	private XYSeries serieIntentos;

	private JFreeChart lineChartIntentos;

	private int intentos;

	private int tiradasTotales;

	private boolean ganoEnLaUltima;

	protected int diasTotales;

	private int contadorDiasDelIntento;

	private int contadorTiradasDelIntento;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMesaAdaptableRapida frame = new VistaMesaAdaptableRapida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaMesaAdaptableRapida() {
		setTitle("Apostar al Color");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		JLabel lblNombreDelJugador = new JLabel("Nombre del Jugador");
		lblNombreDelJugador.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		rdbtnRojo = new JRadioButton("Rojo");
		rdbtnRojo.setMinimumSize(new Dimension(20, 23));
		rdbtnRojo.setSelected(true);
		buttonGroup.add(rdbtnRojo);
		rdbtnRojo.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		rdbtnNegro = new JRadioButton("Negro");
		rdbtnNegro.setMinimumSize(new Dimension(20, 23));
		buttonGroup.add(rdbtnNegro);
		rdbtnNegro.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		JLabel lblCantidad = new JLabel("Cantidad a Apostar");
		lblCantidad.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		spinnerApuesta = new JSpinner();
		spinnerApuesta.setMinimumSize(new Dimension(20, 20));
		spinnerApuesta.setModel(new SpinnerNumberModel(new Integer(5), new Integer(1), null, new Integer(5)));

		JLabel lblBalanceInicial = new JLabel("Balance Inicial");
		lblBalanceInicial.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		spinnerBalanceInicial = new JSpinner();
		spinnerBalanceInicial.setMinimumSize(new Dimension(20, 20));
		spinnerBalanceInicial.setModel(new SpinnerNumberModel(new Integer(50), new Integer(10), null, new Integer(10)));

		JLabel lblObjetivo = new JLabel("Objetivo");
		lblObjetivo.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		spinnerObjetivo = new JSpinner();
		spinnerObjetivo.setMinimumSize(new Dimension(20, 20));
		spinnerObjetivo.setModel(new SpinnerNumberModel(new Integer(600), new Integer(50), null, new Integer(10)));

		JLabel lblTipoDeRuleta = new JLabel("Tipo de Ruleta");
		lblTipoDeRuleta.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		rdbtnNewRadioButton = new JRadioButton("Con Cero");
		rdbtnNewRadioButton.setMinimumSize(new Dimension(20, 23));
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup_1.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		rdbtnSinCero = new JRadioButton("Sin Cero");
		rdbtnSinCero.setMinimumSize(new Dimension(20, 23));
		buttonGroup_1.add(rdbtnSinCero);
		rdbtnSinCero.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		JLabel lblLimiteTiradasDiarias = new JLabel("Max Tiradas por D\u00EDa");
		lblLimiteTiradasDiarias.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		spinnerMaxTiradas = new JSpinner();
		spinnerMaxTiradas.setMinimumSize(new Dimension(20, 20));
		spinnerMaxTiradas.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(1)));

		JButton btnSimular = new JButton("Simular");
		btnSimular.setMinimumSize(new Dimension(20, 23));
		btnSimular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				iniciarSimulacion();
			}
		});

		btnSimular.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		JLabel lblMaxCantidadDe = new JLabel("Max Cant. de D\u00EDas");
		lblMaxCantidadDe.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		spinnerMaxDias = new JSpinner();
		spinnerMaxDias.setMinimumSize(new Dimension(20, 20));

		JLabel lblVelocidad = new JLabel("Velocidad");
		lblVelocidad.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		sliderVelocidad = new JSlider();
		sliderVelocidad.setMinimumSize(new Dimension(20, 26));
		sliderVelocidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cambiarVelocidad();
			}
		});
		sliderVelocidad.setMajorTickSpacing(10);
		sliderVelocidad.setMinorTickSpacing(10);
		sliderVelocidad.setSnapToTicks(true);
		sliderVelocidad.setPaintTicks(true);

		JButton btnHastaGanar = new JButton("\u00A1Hasta Ganar!");
		btnHastaGanar.setMinimumSize(new Dimension(20, 23));
		btnHastaGanar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hastaGanar();
			}
		});
		btnHastaGanar.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		StopButton stopButton = new StopButton((String) null);
		stopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				detener();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblNombreDelJugador, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(8))
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblColor, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(rdbtnRojo, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE).addGap(24))
				.addGroup(gl_panel.createSequentialGroup().addGap(140).addComponent(rdbtnNegro, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE).addGap(24))
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(spinnerApuesta, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(8))
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblBalanceInicial, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(spinnerBalanceInicial, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(8))
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblObjetivo, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(spinnerObjetivo, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(8))
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblTipoDeRuleta, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(rdbtnNewRadioButton, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE).addGap(24))
				.addGroup(gl_panel.createSequentialGroup().addGap(140).addComponent(rdbtnSinCero, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE).addGap(24))
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblLimiteTiradasDiarias, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(spinnerMaxTiradas, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(8))
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblMaxCantidadDe, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(spinnerMaxDias, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(8))
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblVelocidad, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(sliderVelocidad, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(8))
				.addGroup(
						gl_panel.createSequentialGroup().addGap(5).addComponent(btnSimular, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE).addGap(13)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel.createSequentialGroup().addComponent(stopButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addContainerGap(153, Short.MAX_VALUE)).addGroup(gl_panel.createSequentialGroup().addGap(31).addComponent(btnHastaGanar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup().addGap(5).addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNombreDelJugador, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))).addGap(7)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(lblColor, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)).addComponent(rdbtnRojo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)).addGap(3).addComponent(rdbtnNegro, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addComponent(spinnerApuesta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(11)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblBalanceInicial, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addComponent(spinnerBalanceInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(11)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblObjetivo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addComponent(spinnerObjetivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(10)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(lblTipoDeRuleta, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)).addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)).addGap(3).addComponent(rdbtnSinCero, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblLimiteTiradasDiarias, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addComponent(spinnerMaxTiradas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(11)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblMaxCantidadDe, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(spinnerMaxDias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))).addGap(10)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblVelocidad, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE).addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(sliderVelocidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))).addGap(14)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnSimular, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addComponent(btnHastaGanar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)).addComponent(stopButton, 23, 23, 23))));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Simulaci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblTiradaNumero = new JLabel("Tirada Numero");
		lblTiradaNumero.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		textNumDia = new JTextField();
		textNumDia.setColumns(10);

		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		textNumTirada = new JTextField();
		textNumTirada.setColumns(10);

		JLabel lblDaNumero = new JLabel("D\u00EDa Numero");
		lblDaNumero.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		textSaldo = new JTextField();
		textSaldo.setColumns(10);

		JLabel lblResultado = new JLabel("Resultado Tirada");
		lblResultado.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));

		lblNumeroobtenido = new JLabel("0");
		lblNumeroobtenido.setBackground(Color.WHITE);
		lblNumeroobtenido.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNumeroobtenido.setForeground(Color.GREEN);
		lblNumeroobtenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroobtenido.setFont(new Font("Source Sans Pro", Font.PLAIN, 50));

		seriesCollection = new XYSeriesCollection();
		serie = new XYSeries("Serie");
		seriesCollection.addSeries(serie);

		lineChart = ChartFactory.createXYLineChart(null, "Tiradas", "Balance", seriesCollection, PlotOrientation.VERTICAL, false, false, false);

		ChartPanel chartPanelBalance = new ChartPanel(lineChart);

		JLabel lblProgreso = new JLabel("Progreso");
		lblProgreso.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addGap(5)
						.addGroup(
								gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addComponent(lblDaNumero, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(textNumDia, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
										.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblTiradaNumero, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(textNumTirada, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)).addGroup(gl_panel_1.createSequentialGroup().addComponent(lblSaldo, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(textSaldo, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
										.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblResultado, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)).addComponent(lblNumeroobtenido, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE).addComponent(lblProgreso, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE).addComponent(chartPanelBalance, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)).addGap(9)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_1.createSequentialGroup().addGap(6).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(lblDaNumero).addComponent(textNumDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(11).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(lblTiradaNumero).addComponent(textNumTirada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(11)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(lblSaldo).addComponent(textSaldo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(11).addComponent(lblResultado).addGap(11).addComponent(lblNumeroobtenido, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE).addGap(11).addComponent(lblProgreso).addGap(11).addComponent(chartPanelBalance, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE).addGap(6)));
		panel_1.setLayout(gl_panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Gr\u00E1ficos", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		datasetColores = new DefaultPieDataset();
		datasetColores.setValue("Rojo", 0);
		datasetColores.setValue("Negro", 0);
		datasetColores.setValue("Cero", 0);
		JFreeChart chartColores = ChartFactory.createPieChart(null, datasetColores, true, false, false);
		PiePlot plot = (PiePlot) chartColores.getPlot();
		plot.setSimpleLabels(true);
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
		plot.setLabelGenerator(gen);
		plot.setSectionPaint("Rojo", Color.RED);
		plot.setSectionPaint("Negro", Color.BLACK);
		plot.setSectionPaint("Cero", Color.GREEN);
		ChartPanel chartPanelColores = new ChartPanel(chartColores);

		seriesIntentosCollection = new XYSeriesCollection();
		serieIntentos = new XYSeries("SerieIntentos");
		seriesIntentosCollection.addSeries(serieIntentos);

		lineChartIntentos = ChartFactory.createXYLineChart(null, "Intentos", "Tiradas", seriesIntentosCollection, PlotOrientation.VERTICAL, false, false, false);

		ChartPanel chartPanelIntentos = new ChartPanel(lineChartIntentos);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup().addGap(5).addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(chartPanelColores, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE).addComponent(chartPanelIntentos, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)).addGap(5)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup().addGap(6).addComponent(chartPanelColores, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE).addGap(11).addComponent(chartPanelIntentos, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE).addGap(7)));
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addGap(5).addComponent(panel, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE).addGap(10).addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE).addGap(10).addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE).addGap(5)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addGap(6).addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addComponent(panel, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE).addGap(1)).addGroup(gl_contentPane.createSequentialGroup().addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE).addGap(1)).addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)).addGap(6)));
		contentPane.setLayout(gl_contentPane);

		jugando = false;
		hastaGanar = false;
		ganoEnLaUltima = false;
		contadorDiasDelIntento = 0;
		contadorTiradasDelIntento = 0;

	}

	public synchronized boolean estaJugando() {
		return jugando;
	}

	protected void detener() {
		hastaGanar = false;
		jugando = false;
		serieIntentos.clear();
		intentos = 0;
		tiradasTotales = 0;
		diasTotales = 0;
	}

	protected void hastaGanar() {
		if (!jugando) {
			intentos = 0;
			tiradasTotales = 0;
			diasTotales = 0;
			hastaGanar = true;
			serieIntentos.clear();
			datasetColores.setValue("Rojo", 0);
			datasetColores.setValue("Negro", 0);
			datasetColores.setValue("Cero", 0);
			iniciarSimulacion();
		}

	}

	protected void cambiarVelocidad() {
		if (jugando) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					mesa.setVelocidad(sliderVelocidad.getValue() * 10);
				}
			}).start();
		}
	}

	protected void iniciarSimulacion() {
		if (!jugando) {
			if (!hastaGanar) {
				datasetColores.setValue("Rojo", 0);
				datasetColores.setValue("Negro", 0);
				datasetColores.setValue("Cero", 0);
			}
			serie.clear();
			if (ganoEnLaUltima) {
				serieIntentos.clear();
				intentos = 0;
				tiradasTotales = 0;
				diasTotales = 0;
				datasetColores.setValue("Rojo", 0);
				datasetColores.setValue("Negro", 0);
				datasetColores.setValue("Cero", 0);
			}
			nombre = txtNombre.getText().trim();
			int color, cantidadApostable, balanceInicial, maxTiradas, maxDias, velocidad;
			boolean conCero;
			cantidadApostable = (int) spinnerApuesta.getValue();
			balanceInicial = (int) spinnerBalanceInicial.getValue();
			objetivo = (int) spinnerObjetivo.getValue();
			maxTiradas = (int) spinnerMaxTiradas.getValue();
			maxDias = (int) spinnerMaxDias.getValue();
			velocidad = sliderVelocidad.getValue() * 10;
			if (cantidadApostable > balanceInicial) {
				mostrarMensajeError("El balance inicial es menor a la cantidad a apostar");
			} else if (balanceInicial >= objetivo) {
				mostrarMensajeError("El objetivo establecido es menor al balance");
			} else if (nombre.equals("")) {
				mostrarMensajeError("Debe ingresar el nombre del jugador");
			} else {
				if (rdbtnRojo.isSelected()) {
					color = IResultado.ROJO;
				} else {
					color = IResultado.NEGRO;
				}
				if (rdbtnNewRadioButton.isSelected()) {
					conCero = true;
				} else {
					conCero = false;
				}
				mesa = new Mesa(this);
				jugando = true;
				new Thread(new Runnable() {
					@Override
					public void run() {
						mesa.jugar(nombre, balanceInicial, objetivo, cantidadApostable, color, conCero, maxTiradas, maxDias, velocidad);
					}
				}).start();
			}
		}
	}

	protected void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	protected void mostrarMensaje(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public synchronized void mostrarResultadoTirada(IResultado resultado, int contadorTiradas, int contadorDias, int balance) {
		int color = resultado.getColor();
		int numero = resultado.getNumero();
		String nombreColor;
		if (!hastaGanar) {
			textNumDia.setText(String.valueOf(contadorDias));
			textNumTirada.setText(String.valueOf(contadorTiradas));
		} else {
			textNumDia.setText(String.valueOf(diasTotales + contadorDias));
			textNumTirada.setText(String.valueOf(tiradasTotales + contadorTiradas));
		}
		contadorDiasDelIntento = contadorDias;
		contadorTiradasDelIntento = contadorTiradas;
		textSaldo.setText(String.valueOf(balance));
		lblNumeroobtenido.setText(String.valueOf(numero));
		if (color == IResultado.CERO) {
			lblNumeroobtenido.setForeground(Color.GREEN);
			nombreColor = "Cero";
		} else if (color == IResultado.ROJO) {
			lblNumeroobtenido.setForeground(Color.RED);
			nombreColor = "Rojo";
		} else {
			lblNumeroobtenido.setForeground(Color.BLACK);
			nombreColor = "Negro";
		}
		int cantidad = datasetColores.getValue(nombreColor).intValue() + 1;
		datasetColores.setValue(nombreColor, cantidad);
		serie.add(contadorTiradas, balance);
	}

	public synchronized void mostrarResultadoFinal(boolean gano) {
		intentos++;
		tiradasTotales += contadorTiradasDelIntento;
		diasTotales += contadorDiasDelIntento;
		serieIntentos.add(intentos, contadorTiradasDelIntento);
		jugando = false;
		if (gano) {
			ganoEnLaUltima = true;
			if (hastaGanar) {
				hastaGanar = false;
				mostrarMensaje("Resultado", nombre + " logro su objetivo de " + objetivo + "...\n" + "En " + intentos + " intentos que suman un total de " + tiradasTotales + " tiradas...");
			} else {
				mostrarMensaje("Resultado", "¡" + nombre + " logro su objetivo de " + objetivo + "!");
			}
		} else {
			ganoEnLaUltima = false;
			if (hastaGanar) {
				iniciarSimulacion();
			} else {
				mostrarMensaje("Resultado", nombre + " perdio todo...");
			}
		}
	}
}
