import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Component;

public class CramerFrame extends JFrame {

	private JPanel contentPane;
	private static JTable a;
	private static JTable b;
	private JTable s;
	private JTable x;
	private JTable y;
	private JTable z;
	private JLabel lblS;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblZ;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CramerFrame frame = new CramerFrame();
					JFrame.setDefaultLookAndFeelDecorated(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
					frame.setResizable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CramerFrame() {
		setTitle("Regla de Cramer 3x3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduceLosValores = new JLabel("Introduce los valores correspondientes");
		lblIntroduceLosValores.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblIntroduceLosValores.setBounds(10, 10, 271, 14);
		contentPane.add(lblIntroduceLosValores);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblA.setToolTipText("Introduce Matriz A");
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setBounds(44, 25, 225, 14);
		contentPane.add(lblA);
		
		JLabel lblB = new JLabel("B");
		lblB.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setBounds(337, 25, 77, 14);
		contentPane.add(lblB);
		
		a = new JTable();
		a.setFont(new Font("Calibri", Font.PLAIN, 13));
		a.setFillsViewportHeight(true);
		a.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				" ", "   A", " "
			}
		) {
			Class[] columnTypes = new Class[] {
				Float.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		a.getColumnModel().getColumn(0).setResizable(false);
		a.getColumnModel().getColumn(1).setPreferredWidth(73);
		a.getColumnModel().getColumn(2).setResizable(false);
		a.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		a.setSurrendersFocusOnKeystroke(true);
		a.setBounds(44, 45, 225, 48);
		contentPane.add(a);
		
		b = new JTable();
		b.setFont(new Font("Calibri", Font.PLAIN, 13));
		b.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		b.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
			},
			new String[] {
				"B"
			}
		) {
			Class[] columnTypes = new Class[] {
				Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		b.getColumnModel().getColumn(0).setResizable(false);
		
		JLabel lblttt = new JLabel("=");
		lblttt.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblttt.setHorizontalAlignment(SwingConstants.CENTER);
		lblttt.setBounds(268, 45, 70, 48);
		contentPane.add(lblttt);
		b.setBounds(337, 45, 77, 48);
		contentPane.add(b);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnCalcular.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				resolver();
			}
		});
		btnCalcular.setBounds(436, 45, 123, 48);
		contentPane.add(btnCalcular);
		
		JButton btnRandom = new JButton("Random");
		btnRandom.setFont(new Font("Calibri", Font.PLAIN, 13));		
		btnRandom.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				randomizar(a);
				randomizar(b);
				limpiarTabla(s);
				limpiarTabla(x);
				limpiarTabla(y);
				limpiarTabla(z);
				lblS.setText("");
				lblX.setText("");
				lblY.setText("");
				lblZ.setText("");
			}
		});
		btnRandom.setBounds(565, 45, 89, 23);
		contentPane.add(btnRandom);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnRandom.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnLimpiar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				limpiarTodo();
			}
		});
		btnLimpiar.setBounds(565, 70, 89, 23);
		contentPane.add(btnLimpiar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 100, 640, 2);
		contentPane.add(separator);
		
		s = new JTable();
		s.setFont(new Font("Calibri", Font.PLAIN, 13));
		s.setSurrendersFocusOnKeystroke(true);
		s.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"", "", "Det(s)", "", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				Float.class, Float.class, Float.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JLabel lblDets = new JLabel("\u0394S=");
		lblDets.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblDets.setBounds(10, 132, 31, 14);
		contentPane.add(lblDets);
		s.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		s.setFillsViewportHeight(true);
		s.setBounds(44, 117, 370, 48);
		contentPane.add(s);
		
		lblS = new JLabel("OPS: ");
		lblS.setEnabled(false);
		lblS.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblS.setBounds(10, 167, 404, 14);
		contentPane.add(lblS);
		
		JLabel lblDetx = new JLabel("\u0394x=");
		lblDetx.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblDetx.setBounds(10, 216, 31, 14);
		contentPane.add(lblDetx);
		
		x = new JTable();
		x.setFont(new Font("Calibri", Font.PLAIN, 13));
		x.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"", "", "Det(s)", "", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				Float.class, Float.class, Float.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		x.setSurrendersFocusOnKeystroke(true);
		x.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		x.setFillsViewportHeight(true);
		x.setBounds(44, 201, 370, 48);
		contentPane.add(x);
		
		lblX = new JLabel("OPS: ");
		lblX.setEnabled(false);
		lblX.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblX.setBounds(10, 251, 404, 14);
		contentPane.add(lblX);
		
		JLabel lblDety = new JLabel("\u0394y=");
		lblDety.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblDety.setBounds(10, 300, 31, 14);
		contentPane.add(lblDety);
		
		y = new JTable();
		y.setFont(new Font("Calibri", Font.PLAIN, 13));
		y.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"", "", "Det(s)", "", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				Float.class, Float.class, Float.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		y.setSurrendersFocusOnKeystroke(true);
		y.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		y.setFillsViewportHeight(true);
		y.setBounds(44, 285, 370, 48);
		contentPane.add(y);
		
		JLabel lblDetz = new JLabel("\u0394z=");
		lblDetz.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblDetz.setBounds(10, 384, 31, 14);
		contentPane.add(lblDetz);
		
		lblY = new JLabel("OPS: ");
		lblY.setEnabled(false);
		lblY.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblY.setBounds(10, 335, 404, 14);
		contentPane.add(lblY);
		
		z = new JTable();
		z.setFont(new Font("Calibri", Font.PLAIN, 13));
		z.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"", "", "Det(s)", "", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				Float.class, Float.class, Float.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		z.setSurrendersFocusOnKeystroke(true);
		z.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		z.setFillsViewportHeight(true);
		z.setBounds(44, 369, 370, 48);
		contentPane.add(z);
		
		lblZ = new JLabel("OPS: ");
		lblZ.setEnabled(false);
		lblZ.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblZ.setBounds(10, 418, 404, 14);
		contentPane.add(lblZ);
	}
	
	public void resolver(){
		if(comprobarTablaCorrecta(a) && comprobarTablaCorrecta(b)){
			crearMatrizAumentada(s, 3);
			crearMatrizAumentada(x, 0);
			crearMatrizAumentada(y, 1);
			crearMatrizAumentada(z, 2);
			etiquetar(s, lblS);
			etiquetar(x, lblX);
			etiquetar(y, lblY);
			etiquetar(z, lblZ);
			calcularDeterminante(x);
			calcularDeterminante(y);
			calcularDeterminante(z);
		}
		
	}
	
	public static void etiquetar(JTable tabla, JLabel etiqueta){
			float determinante=0, producto;
			String cadena="";
			
			for (int i=0; i<3; i++){
				producto = (Float) tabla.getValueAt(0, i) * (Float) tabla.getValueAt(1, i+1) * (Float) tabla.getValueAt(2, i+2);
				cadena += " + "+ producto;
				determinante += producto;
				
				producto = (Float) tabla.getValueAt(0, 4-i) * (Float) tabla.getValueAt(1, 3-i) * (Float) tabla.getValueAt(2, 2-i);
				cadena += " - " + producto;
				determinante -= producto;
			}
			etiqueta.setText("OPS: " + cadena + " = " + determinante);
	}
	
	public float calcularDeterminante(JTable tabla){
		float producto=0;
		
		for(int i=0; i<3; i++){
			producto=(Float) tabla.getValueAt(i, i)  *  (Float) tabla.getValueAt(i, i+1)  *  (Float) tabla.getValueAt(i, i+2);
		}
		return producto;
	}
	
	public static void crearMatrizAumentada(JTable tabla, int pos){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(j!=pos || pos==3){
					tabla.setValueAt(a.getValueAt(i, j), i, j);
				}else{
					tabla.setValueAt(b.getValueAt(i, 0), i, j);
				}
				if (j<2){
					tabla.setValueAt(tabla.getValueAt(i, j), i, j+3);
				}
			}
		}
	}
	
	public JTable randomizar(JTable tabla){
		for(int i=0; i<tabla.getRowCount(); i++){
			for(int j=0; j<tabla.getColumnCount(); j++){
				float numAleatorio = 0;
				while (numAleatorio == 0){ 
					numAleatorio = (int) (Math.random()*10);
				}
				tabla.setValueAt(numAleatorio, i, j);
			}
		}
		return tabla;
	}
	
	public void limpiarTodo(){
		limpiarTabla(a);
		limpiarTabla(b);
		limpiarTabla(s);
		lblS.setText("");
		limpiarTabla(x);
		lblX.setText("");
		limpiarTabla(y);
		lblY.setText("");
		limpiarTabla(z);
		lblZ.setText("");
	}
	
	public void limpiarTabla(JTable tabla){
		for(int i=0; i<tabla.getRowCount(); i++){
			for(int j=0; j<tabla.getColumnCount(); j++){
				tabla.setValueAt(null, i, j);
			}
		}
	}
	
	public static boolean comprobarTablaCorrecta(JTable tabla){
		boolean bandera=true;
		for(int i=0; i<tabla.getRowCount(); i++){
			for(int j=0; j<tabla.getColumnCount(); j++){
				if(tabla.getValueAt(i, j)==null){
					bandera=false;
					i=tabla.getRowCount();
					j=tabla.getColumnCount();
				}
			}
		}
		if(bandera == false){
			JOptionPane.showMessageDialog(null,"LLENA COMPLETAMENTE LAS TABLAS");
		}
		return bandera;	
	}
}
