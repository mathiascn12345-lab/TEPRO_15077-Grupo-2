package arreglo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import clases.Producto;

public class Arregoproductos {

    private ArrayList<Producto> lista;

    public Arregoproductos() {
        lista = new ArrayList<>();
    }

    
    public void adicionar(Producto p){
        lista.add(p);
    }

    public void adicionar(String nombre, String categoria, double precio, int stock){
        String cod = GenerarCod();
        Producto p = new Producto(cod, nombre, categoria, precio, stock);
        lista.add(p);
    }
    
    public int Tamaño(){
        return lista.size();
    }

    public Producto Obtener(int x){
        if (x >= 0 && x < lista.size())
            return lista.get(x);
        return null;
    }

    public String GenerarCod(){
        int num = Tamaño() + 1;
        return String.format("P%03d", num);
    }

    
    public Producto Buscar(String Nom) {
        for (Producto p : lista) {
            if (p.getNombre().equalsIgnoreCase(Nom)) {
                return p;
            }
        }
        return null;
    }

    public Producto Buscar(int index){
        if(index >= 0 && index < lista.size())
            return lista.get(index);
        return null;
    }

    public boolean Modificar(String Nom1, String nuevoNombre, String categoria, double precio, int stock) {
        Producto p = Buscar(Nom1);

        if (p != null) {
            p.setNombre(nuevoNombre);
            p.setCategoria(categoria);
            p.setPrecio(precio);
            p.setStock(stock);
            return true;
        }

        JOptionPane.showMessageDialog(null, "Producto no encontrado");
        return false;
    }

    public boolean Eliminar(String Nom1) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equalsIgnoreCase(Nom1)) {
                lista.remove(i);
                return true;
            }
        }

        JOptionPane.showMessageDialog(null, "Producto no encontrado");
        return false;
    }
}
