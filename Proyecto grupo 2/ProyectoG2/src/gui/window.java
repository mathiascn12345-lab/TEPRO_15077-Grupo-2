package gui;
import arreglo.Arregoproductos;
import clases.Producto;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class window extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtnom;
	private JLabel lblCategora;
	private JTextField txtcant;
	private JComboBox cmbox;
	private JLabel lblCantidad;
	private JTextArea txtarea;
	private JButton btnNewButton;
	private JLabel lblPrecio;
	private JTextField txtpre;
	private JButton btnListar;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window frame = new window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(10, 10, 59, 12);
			contentPane.add(lblNewLabel);
		}
		{
			txtnom = new JTextField();
			txtnom.setBounds(10, 32, 96, 18);
			contentPane.add(txtnom);
			txtnom.setColumns(10);
		}
		{
			lblCategora = new JLabel("Categoría");
			lblCategora.setBounds(117, 10, 59, 12);
			contentPane.add(lblCategora);
		}
		{
			txtcant = new JTextField();
			txtcant.setColumns(10);
			txtcant.setBounds(239, 32, 96, 18);
			contentPane.add(txtcant);
		}
		{
			lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setBounds(239, 10, 59, 12);
			contentPane.add(lblCantidad);
		}
		{
			txtarea = new JTextArea();
			txtarea.setBounds(10, 60, 444, 278);
			contentPane.add(txtarea);
		}
		{
			btnNewButton = new JButton("Adicionar");
			btnNewButton.addActionListener(this);
			btnNewButton.setBounds(464, 31, 84, 51);
			contentPane.add(btnNewButton);
		}
		{
			lblPrecio = new JLabel("Precio:");
			lblPrecio.setBounds(358, 10, 59, 12);
			contentPane.add(lblPrecio);
		}
		{
			txtpre = new JTextField();
			txtpre.setColumns(10);
			txtpre.setBounds(358, 32, 96, 18);
			contentPane.add(txtpre);
		}
		{
			btnListar = new JButton("Reportar");
			btnListar.addActionListener(this);
			btnListar.setBounds(464, 146, 84, 51);
			contentPane.add(btnListar);
		}
		{
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBounds(464, 298, 84, 40);
			contentPane.add(btnEliminar);
		}
		{
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(this);
			btnBuscar.setBounds(464, 92, 84, 34);
			contentPane.add(btnBuscar);
		}
		{
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(this);
			btnModificar.setBounds(464, 233, 84, 46);
			contentPane.add(btnModificar);
		}
		{
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bebidas", "Snacks", "Helados", "Higiene"}));
			comboBox.setBounds(116, 31, 113, 20);
			contentPane.add(comboBox);
		}

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnListar) {
			do_btnListar_actionPerformed(e);
		}
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		}
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
	}
	Arregoproductos ap=new Arregoproductos();
	private JComboBox comboBox;
	
	protected void do_btnModificar_actionPerformed(ActionEvent e) {//Metodo Modificar
		
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) //método adicionar 
	{
		String cod = ap.GenerarCod();
		
		Producto pro = new Producto(cod, txtnom.getText(), comboBox.getSelectedItem().toString(), Double.parseDouble(txtpre.getText()), Integer.parseInt(txtcant.getText()));
		ap.adicionar(pro);
		txtcant.setText("");
		txtpre.setText("");
		txtnom.setText("");
	}
	protected void do_btnListar_actionPerformed(ActionEvent e) {
		txtarea.setText("");
		txtarea.append("Código\tNombre\tCategoría\tPrecio\tStock");
		for(int i=0; i<ap.Tamaño();i++) {
			txtarea.append("\n"+ap.Obtener(i).getCodigo()+"\t"+ap.Obtener(i).getNombre()+"\t\t"+ap.Obtener(i).getCategoria()+"\t"+ap.Obtener(i).getPrecio()+"\t"+ap.Obtener(i).getStock());
		}
	}
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		String Nom = txtnom.getText();
		Producto P = ap.Buscar(Nom);
		if (P != null) {
			JOptionPane.showMessageDialog(this,
					"Codigo: "+P.getCodigo()+"\n"+
					"Nombre: "+P.getNombre()+"\n"+
					"Categoría: "+P.getCategoria()+"\n"+
					"Precio: "+P.getPrecio()+"\n"+
					"Stock: "+P.getStock());
		}
		else JOptionPane.showInputDialog(this, "Producto no encontrado");
	}
}
