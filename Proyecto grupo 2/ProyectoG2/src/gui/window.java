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
import java.awt.Font;


public class window extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtnom, txtcant, txtpre, txtNuevoNombre;
    private JTextArea txtarea;
    private JComboBox<String> comboBox;

    private JButton btnAdicionar, btnListar, btnEliminar, btnBuscar, btnModificar;

    Arregoproductos ap = new Arregoproductos();

    public static void main(String[] args) {
        new window().setVisible(true);
    }

    public window() {
        setTitle("Gestión de Productos");
        setSize(650, 400);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        txtnom = new JTextField();
        txtnom.setBounds(140, 31, 100, 20);
        getContentPane().add(txtnom);
        comboBox = new JComboBox<>(new String[]{"Bebidas", "Snacks", "Helados", "Higiene"});
        comboBox.setBounds(20, 30, 100, 20);
        getContentPane().add(comboBox);
        txtcant = new JTextField();
        txtcant.setBounds(260, 30, 80, 20);
        getContentPane().add(txtcant);
        txtpre = new JTextField();
        txtpre.setBounds(360, 30, 80, 20);
        getContentPane().add(txtpre);
        txtNuevoNombre = new JTextField();
        txtNuevoNombre.setBounds(460, 30, 120, 20);
        getContentPane().add(txtNuevoNombre);

        txtarea = new JTextArea();
        txtarea.setBounds(20, 70, 450, 250);
        getContentPane().add(txtarea);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(500, 70, 120, 30);
        getContentPane().add(btnAdicionar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(500, 110, 120, 30);
        getContentPane().add(btnBuscar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(500, 150, 120, 30);
        getContentPane().add(btnModificar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(500, 190, 120, 30);
        getContentPane().add(btnListar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(500, 230, 120, 30);
        getContentPane().add(btnEliminar);
        
        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20, 10, 80, 20);
        getContentPane().add(lblCategoria);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(137, 14, 65, 16);
        getContentPane().add(lblNombre);
        
        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(261, 14, 79, 16);
        getContentPane().add(lblStock);
        
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(367, 14, 73, 16);
        getContentPane().add(lblPrecio);
        
        JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
        lblNuevoNombre.setBounds(460, 12, 91, 16);
        getContentPane().add(lblNuevoNombre);

        btnAdicionar.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnListar.addActionListener(this);
        btnEliminar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
    	
        try {
            if (e.getSource() == btnAdicionar) {

                if (txtnom.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese nombre");
                    return;
                }

                if(Double.parseDouble(txtpre.getText())>=1)
                {double precio = Double.parseDouble(txtpre.getText());
                 int stock = Integer.parseInt(txtcant.getText());
                ap.adicionar(txtnom.getText(),
                        comboBox.getSelectedItem().toString(),
                        precio, stock);

                JOptionPane.showMessageDialog(this, "Producto agregado");}
                else if (Double.parseDouble(txtpre.getText())<=0) 
                {JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0, ni modo que sea gratis o te endeudes");}
                int stock = Integer.parseInt(txtcant.getText());

                

            } else if (e.getSource() == btnListar) {

                txtarea.setText("CODIGO\tNOMBRE\tCATEGORIA\tPRECIO\tSTOCK\n");

                for (int i = 0; i < ap.Tamaño(); i++) {
                    Producto p = ap.Obtener(i);
                    txtarea.append(p.getCodigo() + "\t" +
                            p.getNombre() + "\t" +
                            p.getCategoria() + "\t" +
                            p.getPrecio() + "\t" +
                            p.getStock() + "\n");
                }

            } else if (e.getSource() == btnBuscar) {

                Producto p = ap.Buscar(txtnom.getText());

                if (p != null) {
                    JOptionPane.showMessageDialog(this,
                            "Código: " + p.getCodigo() +
                            "\nNombre: " + p.getNombre() +
                            "\nCategoría: " + p.getCategoria() +
                            "\nPrecio: " + p.getPrecio() +
                            "\nStock: " + p.getStock());
                } else {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado");
                }

            } else if (e.getSource() == btnModificar) {

                double precio = Double.parseDouble(txtpre.getText());
                int stock = Integer.parseInt(txtcant.getText());

                boolean ok = ap.Modificar(
                        txtnom.getText(),
                        txtNuevoNombre.getText(),
                        comboBox.getSelectedItem().toString(),
                        precio,
                        stock
                );

                if (ok)
                    JOptionPane.showMessageDialog(this, "Modificado correctamente");

            } else if (e.getSource() == btnEliminar) {

                boolean ok = ap.Eliminar(txtnom.getText());

                if (ok)
                    JOptionPane.showMessageDialog(this, "Eliminado correctamente");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese números válidos en precio y stock");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage());
        }
    }
}

