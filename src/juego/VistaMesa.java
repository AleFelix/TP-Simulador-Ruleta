package juego;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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

public class VistaMesa extends JFrame implements IVistaMesa {

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
					VistaMesa frame = new VistaMesa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaMesa() {
		setTitle("Apostar al Color");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 283, 395);
		panel.setLayout(null);
		contentPane.add(panel);

		JLabel lblNombreDelJugador = new JLabel("Nombre del Jugador");
		lblNombreDelJugador.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblNombreDelJugador.setBounds(10, 21, 125, 20);
		panel.add(lblNombreDelJugador);

		txtNombre = new JTextField();
		txtNombre.setBounds(145, 22, 125, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblColor.setBounds(10, 52, 125, 20);
		panel.add(lblColor);

		rdbtnRojo = new JRadioButton("Rojo");
		rdbtnRojo.setSelected(true);
		buttonGroup.add(rdbtnRojo);
		rdbtnRojo.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		rdbtnRojo.setBounds(145, 49, 109, 23);
		panel.add(rdbtnRojo);

		rdbtnNegro = new JRadioButton("Negro");
		buttonGroup.add(rdbtnNegro);
		rdbtnNegro.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		rdbtnNegro.setBounds(145, 75, 109, 23);
		panel.add(rdbtnNegro);

		JLabel lblCantidad = new JLabel("Cantidad a Apostar");
		lblCantidad.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblCantidad.setBounds(10, 105, 125, 20);
		panel.add(lblCantidad);

		spinnerApuesta = new JSpinner();
		spinnerApuesta.setModel(new SpinnerNumberModel(new Integer(5), new Integer(1), null, new Integer(5)));
		spinnerApuesta.setBounds(145, 105, 125, 20);
		panel.add(spinnerApuesta);

		JLabel lblBalanceInicial = new JLabel("Balance Inicial");
		lblBalanceInicial.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblBalanceInicial.setBounds(10, 136, 125, 20);
		panel.add(lblBalanceInicial);

		spinnerBalanceInicial = new JSpinner();
		spinnerBalanceInicial.setModel(new SpinnerNumberModel(new Integer(50), new Integer(10), null, new Integer(10)));
		spinnerBalanceInicial.setBounds(145, 136, 125, 20);
		panel.add(spinnerBalanceInicial);

		JLabel lblObjetivo = new JLabel("Objetivo");
		lblObjetivo.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblObjetivo.setBounds(10, 167, 125, 20);
		panel.add(lblObjetivo);

		spinnerObjetivo = new JSpinner();
		spinnerObjetivo.setModel(new SpinnerNumberModel(new Integer(600), new Integer(50), null, new Integer(10)));
		spinnerObjetivo.setBounds(145, 167, 125, 20);
		panel.add(spinnerObjetivo);

		JLabel lblTipoDeRuleta = new JLabel("Tipo de Ruleta");
		lblTipoDeRuleta.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblTipoDeRuleta.setBounds(10, 198, 125, 20);
		panel.add(lblTipoDeRuleta);

		rdbtnNewRadioButton = new JRadioButton("Con Cero");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup_1.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		rdbtnNewRadioButton.setBounds(145, 197, 109, 23);
		panel.add(rdbtnNewRadioButton);

		rdbtnSinCero = new JRadioButton("Sin Cero");
		buttonGroup_1.add(rdbtnSinCero);
		rdbtnSinCero.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		rdbtnSinCero.setBounds(145, 223, 109, 23);
		panel.add(rdbtnSinCero);

		JLabel lblLimiteTiradasDiarias = new JLabel("Max Tiradas por D\u00EDa");
		lblLimiteTiradasDiarias.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblLimiteTiradasDiarias.setBounds(10, 253, 125, 20);
		panel.add(lblLimiteTiradasDiarias);

		spinnerMaxTiradas = new JSpinner();
		spinnerMaxTiradas.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(1)));
		spinnerMaxTiradas.setBounds(145, 253, 125, 20);
		panel.add(spinnerMaxTiradas);

		JButton btnSimular = new JButton("Simular");
		btnSimular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				iniciarSimulacion();
			}
		});

		btnSimular.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		btnSimular.setBounds(10, 361, 89, 23);
		panel.add(btnSimular);

		JLabel lblMaxCantidadDe = new JLabel("Max Cant. de D\u00EDas");
		lblMaxCantidadDe.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblMaxCantidadDe.setBounds(10, 284, 125, 20);
		panel.add(lblMaxCantidadDe);

		spinnerMaxDias = new JSpinner();
		spinnerMaxDias.setBounds(145, 285, 125, 20);
		panel.add(spinnerMaxDias);

		JLabel lblVelocidad = new JLabel("Velocidad");
		lblVelocidad.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblVelocidad.setBounds(10, 315, 125, 31);
		panel.add(lblVelocidad);

		sliderVelocidad = new JSlider();
		sliderVelocidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cambiarVelocidad();
			}
		});
		sliderVelocidad.setMajorTickSpacing(10);
		sliderVelocidad.setMinorTickSpacing(10);
		sliderVelocidad.setSnapToTicks(true);
		sliderVelocidad.setPaintTicks(true);
		sliderVelocidad.setBounds(145, 316, 125, 31);
		panel.add(sliderVelocidad);

		JButton btnHastaGanar = new JButton("\u00A1Hasta Ganar!");
		btnHastaGanar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hastaGanar();
			}
		});
		btnHastaGanar.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		btnHastaGanar.setBounds(145, 362, 125, 23);
		panel.add(btnHastaGanar);

		StopButton stopButton = new StopButton((String) null);
		stopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				detener();
			}
		});
		stopButton.setBounds(112, 361, 23, 23);
		panel.add(stopButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Simulaci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(303, 11, 283, 395);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTiradaNumero = new JLabel("Tirada Numero");
		lblTiradaNumero.setBounds(10, 53, 124, 18);
		lblTiradaNumero.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		panel_1.add(lblTiradaNumero);

		textNumDia = new JTextField();
		textNumDia.setColumns(10);
		textNumDia.setBounds(144, 22, 125, 20);
		panel_1.add(textNumDia);

		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblSaldo.setBounds(10, 84, 124, 18);
		panel_1.add(lblSaldo);

		textNumTirada = new JTextField();
		textNumTirada.setColumns(10);
		textNumTirada.setBounds(144, 53, 125, 20);
		panel_1.add(textNumTirada);

		JLabel lblDaNumero = new JLabel("D\u00EDa Numero");
		lblDaNumero.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblDaNumero.setBounds(10, 22, 124, 18);
		panel_1.add(lblDaNumero);

		textSaldo = new JTextField();
		textSaldo.setColumns(10);
		textSaldo.setBounds(144, 84, 125, 20);
		panel_1.add(textSaldo);

		JLabel lblResultado = new JLabel("Resultado Tirada");
		lblResultado.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblResultado.setBounds(10, 115, 124, 18);
		panel_1.add(lblResultado);

		lblNumeroobtenido = new JLabel("0");
		lblNumeroobtenido.setBackground(Color.WHITE);
		lblNumeroobtenido.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNumeroobtenido.setForeground(Color.GREEN);
		lblNumeroobtenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroobtenido.setFont(new Font("Source Sans Pro", Font.PLAIN, 50));
		lblNumeroobtenido.setBounds(10, 144, 259, 56);
		panel_1.add(lblNumeroobtenido);

		seriesCollection = new XYSeriesCollection();
		serie = new XYSeries("Serie");
		seriesCollection.addSeries(serie);

		lineChart = ChartFactory.createXYLineChart(null, "Tiradas", "Balance", seriesCollection, PlotOrientation.VERTICAL, false, false, false);

		ChartPanel chartPanelBalance = new ChartPanel(lineChart);
		chartPanelBalance.setBounds(10, 240, 259, 144);
		panel_1.add(chartPanelBalance);

		JLabel lblProgreso = new JLabel("Progreso");
		lblProgreso.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		lblProgreso.setBounds(10, 211, 124, 18);
		panel_1.add(lblProgreso);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Gr\u00E1ficos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(596, 11, 283, 396);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		datasetColores = new DefaultPieDataset();
		datasetColores.setValue("Rojo", 0);
		datasetColores.setValue("Negro", 0);
		datasetColores.setValue("Cero", 0);
		JFreeChart chartColores = ChartFactory.createPieChart(null, datasetColores, true, false, false);
		PiePlot plot = (PiePlot) chartColores.getPlot();
		plot.setSimpleLabels(true);
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
		plot.setLabelGenerator(gen);
		plot.setSectionPaint("Rojo",Color.RED);
		plot.setSectionPaint("Negro",Color.BLACK);
		plot.setSectionPaint("Cero",Color.GREEN);
		ChartPanel chartPanelColores = new ChartPanel(chartColores);
		chartPanelColores.setBounds(10, 22, 263, 175);
		panel_2.add(chartPanelColores);

		seriesIntentosCollection = new XYSeriesCollection();
		serieIntentos = new XYSeries("SerieIntentos");
		seriesIntentosCollection.addSeries(serieIntentos);

		lineChartIntentos = ChartFactory.createXYLineChart(null, "Intentos", "Tiradas", seriesIntentosCollection, PlotOrientation.VERTICAL, false, false, false);

		ChartPanel chartPanelIntentos = new ChartPanel(lineChartIntentos);
		chartPanelIntentos.setBounds(10, 208, 263, 176);
		panel_2.add(chartPanelIntentos);

		jugando = false;
		hastaGanar = false;
		ganoEnLaUltima = false;
		contadorDiasDelIntento = 0;
		contadorTiradasDelIntento = 0;

	}

	@Override
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
			if (!java.awt.EventQueue.isDispatchThread()) {
				try {
					SwingUtilities.invokeAndWait(new Runnable() {

						@Override
						public void run() {
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
						}
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			} else {
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

	@Override
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
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					int cantidad = datasetColores.getValue(nombreColor).intValue() + 1;
					datasetColores.setValue(nombreColor, cantidad);
					serie.add(contadorTiradas, balance);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public synchronized void mostrarResultadoFinal(boolean gano) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					intentos++;
					tiradasTotales += contadorTiradasDelIntento;
					diasTotales += contadorDiasDelIntento;
					serieIntentos.add(intentos, contadorTiradasDelIntento);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
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
