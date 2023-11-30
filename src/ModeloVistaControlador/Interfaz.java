package ModeloVistaControlador;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.GregorianCalendar;
import ModeloVistaControlador.Empleado;
import ModeloVistaControlador.MyList;

public class Interfaz extends JFrame {

    private MyList<Empleado> listaEmpleados;

    // Declaración de todos los elementos que se usarán en la interfaz (botones, cuadros de texto, paneles)
    private JPanel empleadoPanel, botonesPanel;
    private JLabel numLabel, nomLabel, fechaLabel, sueldoLabel, sueldoMaxLabel;
    private JTextField numTextField, nomTextField, fechaTextField, sueldoTextField, sueldoMaxTextField;
    private JButton avanzarButton, retrocederButton, crearButton, aceptarButton, aceptarModificarButton, cancelarButton,
            modificarButton, eliminarButton;

    // Constructor
    public Interfaz() {
        super("Gestion de empleados"); // Nombre del programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tamaño de la interfaz
        setSize(400, 200);
        setLayout(new BorderLayout());

        // Crea paneles internos para ayudar a ordenar todos los elementos
        empleadoPanel = new JPanel(new GridLayout(5, 2));
        botonesPanel = new JPanel(new FlowLayout());

        // Creación de todos los elementos
        // JLabels descriptivos
        numLabel = new JLabel("  Número:");
        nomLabel = new JLabel("  Nombre:");
        fechaLabel = new JLabel("  Fecha de Alta:");
        sueldoLabel = new JLabel("  Sueldo:");
        sueldoMaxLabel = new JLabel("  Sueldo Maximo:");

        // Cuadros de texto
        numTextField = new JTextField(10);
        nomTextField = new JTextField(10);
        fechaTextField = new JTextField(10);
        fechaTextField.setEditable(false);
        sueldoTextField = new JTextField(10);
        sueldoMaxTextField = new JTextField(10);

        // Botones
        avanzarButton = new JButton("Avanzar");
        retrocederButton = new JButton("Retroceder");
        crearButton = new JButton("Crear");
        aceptarButton = new JButton("Aceptar");
        aceptarModificarButton = new JButton("Aceptar");
        cancelarButton = new JButton("Cancelar");
        modificarButton = new JButton("Modificar");
        eliminarButton = new JButton("Eliminar");

        // Se agregan los paneles internos
        add(empleadoPanel, BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);

        // Aquí se añaden y configuran todos los elementos al panel de empleado
        empleadoPanel.add(numLabel);
        empleadoPanel.add(numTextField);

        empleadoPanel.add(nomLabel);
        empleadoPanel.add(nomTextField);

        empleadoPanel.add(fechaLabel);
        empleadoPanel.add(fechaTextField);

        empleadoPanel.add(sueldoLabel);
        empleadoPanel.add(sueldoTextField);

        empleadoPanel.add(sueldoMaxLabel);
        empleadoPanel.add(sueldoMaxTextField);

        // Aquí se añaden y configuran todos los botones al panel de botones
        botonesPanel.add(avanzarButton);
        botonesPanel.add(retrocederButton);
        botonesPanel.add(crearButton);
        botonesPanel.add(aceptarButton);
        botonesPanel.add(cancelarButton);
        botonesPanel.add(modificarButton);
        botonesPanel.add(aceptarModificarButton);
        botonesPanel.add(eliminarButton);

        // Todos los ActionListener de los botones
        avanzarButton.addActionListener(e -> {
            avanzarEmpleado();
        });
        retrocederButton.addActionListener(e -> {
            retrocederEmpleado();
        });
        crearButton.addActionListener(e -> crearEmpleado());
        aceptarButton.addActionListener(e -> aceptarEmpleado());
        aceptarModificarButton.addActionListener(e -> {
            modificarEmpleado();
            cancelarEmpleado();
        });
        cancelarButton.addActionListener(e -> cancelarEmpleado());
        modificarButton.addActionListener(e -> modificarEmpleadoVista());
        eliminarButton.addActionListener(e -> {
            eliminarEmpleado();
            cancelarEmpleado();
        });

        // Aquí declaro tanto la visibilidad como la activación de los botones al ejecutar el programa
        // Los campos de texto no se podrán editar a menos que se active el botón "Crear"
        numTextField.setEnabled(false);
        nomTextField.setEnabled(false);
        sueldoTextField.setEnabled(false);
        sueldoMaxTextField.setEnabled(false);

        // No se podrá avanzar ni retroceder ya que no hay empleados en la lista
        avanzarButton.setEnabled(false);
        retrocederButton.setEnabled(false);
        modificarButton.setEnabled(false);

        // Los botones "Aceptar" y "Cancelar" no serán visibles hasta que no se active el botón "Crear"
        aceptarButton.setVisible(false);
        aceptarModificarButton.setVisible(false);
        cancelarButton.setVisible(false);

        listaEmpleados = new MyList<>();
    }

    private void modificarEmpleadoVista() {
        numTextField.setEnabled(true);
        nomTextField.setEnabled(true);
        sueldoTextField.setEnabled(true);
        sueldoMaxTextField.setEnabled(true);

        numTextField.setText(String.valueOf(listaEmpleados.getCurrentElement().getNum()));
        nomTextField.setText(listaEmpleados.getCurrentElement().getNom());
        sueldoTextField.setText(String.valueOf(listaEmpleados.getCurrentElement().getSueldo()));
        sueldoMaxTextField.setText(String.valueOf(listaEmpleados.getCurrentElement().getSueldoMax()));

        avanzarButton.setVisible(false);
        retrocederButton.setVisible(false);
        crearButton.setVisible(false);
        aceptarButton.setVisible(false);
        modificarButton.setVisible(false);
        cancelarButton.setVisible(true);
        eliminarButton.setVisible(true);
        eliminarButton.setEnabled(true);
        aceptarModificarButton.setVisible(true);
        aceptarModificarButton.setEnabled(true);
    }

    // Actualiza los campos de texto en la interfaz gráfica con los detalles del empleado introducidos
    private void mostrarEmpleado(Empleado empleado) {
        numTextField.setText(String.valueOf(empleado.getNum()));
        nomTextField.setText(empleado.getNom());

        sueldoTextField.setText(String.valueOf(empleado.getSueldo()));
        sueldoMaxTextField.setText(String.valueOf(empleado.getSueldoMax()));

        GregorianCalendar fechaAlta = empleado.getFecha_alta();
        fechaTextField.setText(
                fechaAlta.get(Calendar.DATE) + "/" +
                        (fechaAlta.get(Calendar.MONTH) + 1) + "/" +
                        fechaAlta.get(Calendar.YEAR)
        );
        actualizar();
    }

    // Método que actualiza los botones
    private void actualizar() {
        if (listaEmpleados.isEmpty()) {
            avanzarButton.setEnabled(false);
            retrocederButton.setEnabled(false);
            modificarButton.setEnabled(false);
            eliminarButton.setEnabled(false);
        } else {
            avanzarButton.setEnabled(listaEmpleados.indiceActual < listaEmpleados.size() - 1 && listaEmpleados.size() > 1);
            retrocederButton.setEnabled(listaEmpleados.indiceActual > 0 && listaEmpleados.size() > 1);
            System.out.println(listaEmpleados.indiceActual + " " + listaEmpleados.size());
            modificarButton.setEnabled(true);
            eliminarButton.setEnabled(true);
        }
    }

    /* Método que avanza al siguiente empleado en la lista y actualiza la interfaz
    gráfica para mostrar los detalles del empleado actual. */
    private void avanzarEmpleado() {
        if (listaEmpleados.indiceActual < listaEmpleados.size() - 1 && listaEmpleados.indiceActual >= 0) {
            listaEmpleados.avanzar();
            mostrarEmpleado(listaEmpleados.getCurrentElement());
        }
    }

    /* Método que retrocede al empleado anterior en la lista y actualiza la interfaz
    gráfica para mostrar los detalles del empleado actual. */
    private void retrocederEmpleado() {
        if (listaEmpleados.indiceActual > 0 && listaEmpleados.indiceActual <= listaEmpleados.size()) {
            listaEmpleados.retroceder();
            mostrarEmpleado(listaEmpleados.getCurrentElement());
        }
    }

    // Método que permite la creación de un nuevo empleado
    private void crearEmpleado() {
        // Oculta el botón "Crear" y muestra los botones "Aceptar" y "Cancelar"
        crearButton.setVisible(false);
        aceptarButton.setVisible(true);
        cancelarButton.setVisible(true);
        modificarButton.setVisible(false);
        eliminarButton.setVisible(false);
        aceptarModificarButton.setVisible(false);

        // Oculta los botones "Avanzar" y "Retroceder"
        avanzarButton.setVisible(false);
        retrocederButton.setVisible(false);

        // Y permite la edición de los campos de texto
        numTextField.setEnabled(true);
        nomTextField.setEnabled(true);
        sueldoTextField.setEnabled(true);
        sueldoMaxTextField.setEnabled(true);

        // Limpia los campos de texto
        numTextField.setText("");
        nomTextField.setText("");
        fechaTextField.setText("");
        sueldoTextField.setText("");
        sueldoMaxTextField.setText("");
    }

    // Método que crea el nuevo empleado con los valores introducidos
    private void aceptarEmpleado() {
        // Lee los valores introducidos
        int num = Integer.parseInt(numTextField.getText());
        String nom = nomTextField.getText();
        double sueldo = Double.parseDouble(sueldoTextField.getText());
        double sueldoMax = Double.parseDouble(sueldoMaxTextField.getText());

        // Crea el nuevo empleado y muestra los detalles del mismo
        Empleado nuevoEmpleado = new Empleado(num, nom);
        nuevoEmpleado.setSueldo(sueldo);
        nuevoEmpleado.setSueldoMax(sueldoMax);
        

        listaEmpleados.add(nuevoEmpleado);
        listaEmpleados.indiceActual = listaEmpleados.size() - 1;
        mostrarEmpleado(nuevoEmpleado);
        System.out.println(listaEmpleados.indiceActual + " " + listaEmpleados.size());

        // Restablece la interfaz a su estado predeterminado
        crearButton.setVisible(true);
        aceptarButton.setVisible(false);
        cancelarButton.setVisible(false);
        avanzarButton.setVisible(true);
        retrocederButton.setVisible(true);
        numTextField.setEnabled(false);
        nomTextField.setEnabled(false);
        sueldoTextField.setEnabled(false);
        sueldoMaxTextField.setEnabled(false);
        modificarButton.setVisible(true);
        modificarButton.setEnabled(true);

        // Por último, actualiza la visibilidad de los botones "Avanzar" y "Retroceder"
    }

    // Método que cancela la creación del nuevo empleado
    private void cancelarEmpleado() {
        crearButton.setVisible(true);
        aceptarButton.setVisible(false);
        modificarButton.setVisible(true);
        aceptarModificarButton.setVisible(false);
        eliminarButton.setVisible(false);
        cancelarButton.setVisible(false);
        avanzarButton.setVisible(true);
        retrocederButton.setVisible(true);
        numTextField.setEnabled(false);
        nomTextField.setEnabled(false);
        sueldoTextField.setEnabled(false);
        sueldoMaxTextField.setEnabled(false);
        modificarButton.setEnabled(true);

        if (listaEmpleados.size() > 0) {
            mostrarEmpleado(listaEmpleados.getCurrentElement());
        } else {
            numTextField.setText("");
            nomTextField.setText("");
            fechaTextField.setText("");
            sueldoTextField.setText("");
            sueldoMaxTextField.setText("");
        }
        
    }

    private void modificarEmpleado() {
        if (listaEmpleados.indiceActual >= 0 && listaEmpleados.indiceActual <= listaEmpleados.size()) {
            // Obtén el empleado actual
            Empleado empleadoActual = listaEmpleados.getCurrentElement();

            int num = Integer.parseInt(numTextField.getText());
            String nom = nomTextField.getText();
            double sueldo = Double.parseDouble(sueldoTextField.getText());
            double sueldoMax = Double.parseDouble(sueldoMaxTextField.getText());

            empleadoActual.setNum(num);
            empleadoActual.setNom(nom);
            empleadoActual.setSueldo(sueldo);
            empleadoActual.setSueldoMax(sueldoMax);

            mostrarEmpleado(empleadoActual);
        }
    }

    private void eliminarEmpleado() {
        if (listaEmpleados.indiceActual >= 0 && listaEmpleados.indiceActual < listaEmpleados.size()) {
            listaEmpleados.remove(listaEmpleados.indiceActual); // Elimina el empleado actual de la lista
            System.out.println(listaEmpleados.indiceActual + " " + listaEmpleados.size());
            if (listaEmpleados.isEmpty()) {
                // Si no hay más empleados en la lista, borra los campos de texto
                numTextField.setText("");
                nomTextField.setText("");
                fechaTextField.setText("");
                sueldoTextField.setText("");
                sueldoMaxTextField.setText("");
                listaEmpleados.indiceActual = 0;
                System.out.println(listaEmpleados.indiceActual);
            } else {
                if (listaEmpleados.indiceActual > listaEmpleados.size()) {
                    // Si el índice actual supera el tamaño de la lista, muéstralo en el último empleado
                    listaEmpleados.indiceActual = listaEmpleados.size();
                }
                mostrarEmpleado(listaEmpleados.getCurrentElement());
            }
        }
    }
}