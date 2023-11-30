package ModeloVistaControlador;

public class MyList<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size;
    public int indiceActual;

    public MyList() {
        this.firstNode = null;
        this.lastNode = null;
        this.size = 0;
        this.indiceActual = 0;
    }

    //Recorre la lista enlazada e imprime cada elemento en la consola
    public void showMyList() {
        Node<E> temporary = this.firstNode;

        while (temporary != null) {
        	System.out.println(temporary.getMain());
            temporary = temporary.getNextNode();
        }
    }
    
    //Agrega un nuevo elemento al principio de la lista
    //Crea un nuevo nodo con el elemento y actualiza las referencias de firstNode y lastNode según corresponda
    public void add(E element) {
        Node<E> newNode = new Node<>(element);

        if (isEmpty()) {
            this.firstNode = newNode;
            this.lastNode = newNode;
        } else {
            indiceActual++;
            lastNode.setNextNode(newNode);
            lastNode = newNode;
        }
        size++;
    }

    //Inserta un elemento en la lista en la posición especificada por index
    //Verifica si el índice está dentro de un rango válido y luego realiza la inserción, actualizando las referencias de los nodos adecuados
    public void insert(int index, E element) {

        if (index < 1 || index > size + 1) {
            System.out.println("Índice fuera de rango");
            return;
        }

        Node<E> newNode = new Node<>(element);
        if (isEmpty() || index == 1) {
            add(element);
        } else if (index == size + 1) {
            this.lastNode.setNextNode(newNode);
            this.lastNode = newNode;
            size++;
        } else {
            Node<E> current = this.firstNode;
            int currentIndex = 1;

            while (currentIndex < index - 1) {
                current = current.getNextNode();
                currentIndex++;
            }

            newNode.setNextNode(current.getNextNode());
            current.setNextNode(newNode);
            size++;
        }
    }

    //Modifica el elemento en la posición especificada por index
    //Primero verifica si el índice está dentro de un rango válido y luego actualiza el contenido del nodo en esa posición.
    public void modify(int index, E element) {

        if (index < 1 || index > size) {
            System.out.println("Índice fuera de rango");
            return;
        }

        Node<E> current = this.firstNode;
        int currentIndex = 1;

        while (currentIndex < index) {
            current = current.getNextNode();
            currentIndex++;
        }
        current.setMain(element);
    }

    //Elimina el elemento en la posición especificada por index
    //Verifica si el índice es válido y luego realiza la eliminación, actualizando las referencias de los nodos
    public void remove(int index) {

        if (index < 0 || index > size) {
            System.out.println("Índice fuera de rango");
            return;
        }
        if (index == 0) {
            this.firstNode = this.firstNode.getNextNode();
            size--;
            if (isEmpty()) {
                this.lastNode = null;
                firstNode = null;
            }
        } else {
            Node<E> current = this.firstNode;
            int currentIndex = 0;

            while (currentIndex < index && current != null) {
                current = current.getNextNode();
                currentIndex++;
            }

            if (index == size - 1) {
                current.setNextNode(null);
                this.lastNode = current;
                indiceActual --;
            } else {
                current.setNextNode(current.getNextNode().getNextNode());
            }
            size--;
        }
    }

    public void avanzar() {

        if (indiceActual < size - 1) {
            indiceActual++;
        }
    }

    // Retroceder al elemento anterior en la lista
    public void retroceder() {

        if (indiceActual > 0) {
            indiceActual--;
        }
    }

    public E getCurrentElement() {

        if (isEmpty()) {
            return null;
        }
        if(indiceActual == 0 ) {
            return firstNode.getMain();
        }
        Node<E> current = this.firstNode;
        int currentIndex = 0;
        while (currentIndex < indiceActual) {
            System.out.println(indiceActual);
            current = current.getNextNode();
            currentIndex++;
        }
        return current.getMain();
    }

    //Devuelve el tamaño actual de la lista
    public int size() {
        return size;
    }

    //Verifica si la lista esta vacia
    public boolean isEmpty() {
        return size == 0;
    }

    public class Node<E>{
        private Node<E> nextNode;
        E main;

        public Node(E p){
            this.nextNode = null;
            this.main = p;                   
        }
        
        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public E getMain() {
            return main;
        }

        public void setMain(E p) {
            this.main = p;
        }
    }
}