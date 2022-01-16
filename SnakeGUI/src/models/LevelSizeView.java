package models;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import enums.OpcionesDificultad;
import main.MainApp;

import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class LevelSizeView {

	public JFrame frmSeleccionaLaDificultad;
	private JLabel lblSelDificultad;
	private JButton btnFacil;
	private JButton btnIntermedio;
	private JButton btnDifcil;
	private JButton btnImposible;
	public static OpcionesDificultad dificultadSeleccionada;
	public boolean levelIsSelected;
	public static Object selection;
	
	
	
	public static OpcionesDificultad getDificultadSeleccionada() {
		return dificultadSeleccionada;
	}

	public static Object getSelection() {
		return selection;
	}


	/**
	 * Create the application.
	 */
	public LevelSizeView() {
		initialize();
		this.frmSeleccionaLaDificultad.setVisible(true);
	}

	/**
	 * Initialize the frame.
	 */
	public void initialize() {
		frmSeleccionaLaDificultad = new JFrame();
		this.jOptionPanePopsUp();
		this.configureUIComponents();
		this.configureListeners();
	}
	
	public void jOptionPanePopsUp () {
		 	JDialog.setDefaultLookAndFeelDecorated(true);
		    Object[] selectionValues = { "Pequeño", "Mediano", "Grande" };
		    String initialSelection = "Mediano";
		    selection = JOptionPane.showInputDialog(null, "¿Con qué tamaño de tablero quieres jugar?",
		        "Tamaño de tablero", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);	    
		    
	}
	
	/**
	 * Configura los componentes del View.
	 */
	public void configureUIComponents () {
		frmSeleccionaLaDificultad.setTitle("Snake");
		frmSeleccionaLaDificultad.setBounds(100, 100, 541, 300);
		frmSeleccionaLaDificultad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSeleccionaLaDificultad.getContentPane().setLayout(null);
		
		lblSelDificultad = new JLabel("Selecciona la dificultad");
		lblSelDificultad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelDificultad.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelDificultad.setBounds(158, 24, 201, 41);
		frmSeleccionaLaDificultad.getContentPane().add(lblSelDificultad);
		
		btnFacil = new JButton("F\u00E1cil");
		btnFacil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFacil.setBounds(166, 78, 182, 29);
		frmSeleccionaLaDificultad.getContentPane().add(btnFacil);
		
		btnIntermedio = new JButton("Intermedio");
		btnIntermedio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIntermedio.setBounds(166, 117, 182, 29);
		frmSeleccionaLaDificultad.getContentPane().add(btnIntermedio);
		
		btnDifcil = new JButton("Dif\u00EDcil");
		btnDifcil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDifcil.setBounds(166, 156, 182, 29);
		frmSeleccionaLaDificultad.getContentPane().add(btnDifcil);
		
		btnImposible = new JButton("Imposible");
		btnImposible.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnImposible.setBounds(166, 195, 182, 29);
		frmSeleccionaLaDificultad.getContentPane().add(btnImposible);
	}
	
	public void configureListeners() {
		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultadSeleccionada = OpcionesDificultad.Fácil;
				levelIsSelected = true;
				frmSeleccionaLaDificultad.setVisible(false);
				var game = new GameLogic ();
				try {
					game.start();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				
				}
			
	});
		
		btnIntermedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultadSeleccionada = OpcionesDificultad.Intermedio;
				levelIsSelected = true;
				frmSeleccionaLaDificultad.setVisible(false);
				var game = new GameLogic ();
				try {
					game.start();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnDifcil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultadSeleccionada = OpcionesDificultad.Difícil;
				levelIsSelected = true;
				frmSeleccionaLaDificultad.setVisible(false);
				var game = new GameLogic ();
				try {
					game.start();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnImposible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultadSeleccionada = OpcionesDificultad.Imposible;
				levelIsSelected = true;
				frmSeleccionaLaDificultad.setVisible(false);
				var game = new GameLogic ();
				try {
					game.start();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
	
		
	}
}
