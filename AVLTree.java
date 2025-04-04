

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;
    private boolean status;

    public boolean isEmpty() { return root == null; }


    public void insert(T node) {
        if (this.isEmpty()) {
            this.root = new AVLNode<T>(node); // Caso a árvore esteja vazia, cria a raiz
        } else {
            this.root = insertNode(this.root, node);  // Chama o método auxiliar para inserção
            this.status = false;
        }
    }

    private AVLNode<T> insertNode(AVLNode<T> r, T node) {
        if (r == null) {
            r = new AVLNode<T>(node); // Cria um novo nó
            this.status = true;
        }

        else if (r.getInfo().compareTo(node) > 0) { // Inserção na esquerda
            r.setLeft(insertNode(r.getLeft(), node));

            if (this.status == true) { // Ajusta os fatores de balanceamento após a inserção
                switch (r.getFatBal()) {
                    case 1:
                        r.setFatBal(0);
                        this.status = false;
                        break;
                    case 0:
                        r.setFatBal(-1);
                        break;
                    case -1:
                        r = this.rotateRight(r);  // Necessário rotação para balanceamento
                        break;
                }
            }
        } else { // Inserção na direita 
            r.setRight(insertNode(r.getRight(), node));
            if (this.status == true) {
                switch (r.getFatBal()) {
                    case -1:
                        r.setFatBal(0);
                        this.status = false;
                        break;
                    case 0:
                        r.setFatBal(1);
                        break;
                    case 1:
                        r = this.rotateLeft(r);
                        break;
                }
            }
        }
        return r;
    }

    private AVLNode<T> rotateRight(AVLNode<T> a) {
        AVLNode<T> b, c;
        b = a.getLeft();
        // Rotação Simples
        if (b.getFatBal() == -1) {
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setFatBal(0);
            a = b;
        } else { // Rotação Dupla
            c = b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);
    
            // Ajuste dos fatores de balanceamento
            if (c.getFatBal() == -1) {
                a.setFatBal(1);
            } else {
                a.setFatBal(0);
            }
            if (c.getFatBal() == 1) {
                b.setFatBal(-1);
            } else {
                b.setFatBal(0);
            }
            a = c;
        }
    
        a.setFatBal(0);
        this.status = false;
        return a;
    }
    
    private AVLNode<T> rotateLeft(AVLNode<T> a) {
        AVLNode<T> b, c;
        b = a.getRight();
    
        // Rotação Simples
        if (b.getFatBal() == 1) {
            a.setRight(b.getLeft());
            b.setLeft(a);
            a.setFatBal(0);
            a = b;
        } else { // Rotação Dupla
            c = b.getLeft();
            b.setLeft(c.getRight());
            c.setRight(b);
            a.setRight(c.getLeft());
            c.setLeft(a);
    
            // Ajuste dos fatores de balanceamento
            if (c.getFatBal() == 1) {
                a.setFatBal(-1);
            } else {
                a.setFatBal(0);
            }
            if (c.getFatBal() == -1) {
                b.setFatBal(1);
            } else {
                b.setFatBal(0);
            }
            a = c;
        }
        a.setFatBal(0);
        this.status = false;
        return a;
    }

    public void passeioPorOrdem(){
        if(isEmpty()){
            System.out.println("Arvore Vazia");
        }else{ 
            Stack<AVLNode<T>> pilha = new Stack<>(); // Criação da Stack
            AVLNode<T> aux = root;
            while(!pilha.isEmpty() || aux != null){ // Percorre ate a pilha ser vazia ou o aux ser vazio
                while(aux != null){
                    pilha.push(aux);
                    aux = aux.getLeft();
                }
                aux = pilha.pop();
                System.out.println(aux.getInfo() + "-" + aux.getFatBal());
                aux = aux.getRight();
            }
        }
    }

    public void passeioPorNivel() {
        if (root == null) return; // Se a árvore estiver vazia, sai da função.
    
        Queue<AVLNode<T>> fila = new LinkedList<>(); // Instancia a fila
        fila.add(root); // Adiciona a raiz na fila
    
        while (!fila.isEmpty()) { // Enquanto houver elementos na fila
            AVLNode<T> atual = fila.poll(); // Remove o primeiro nó da fila
            
            // Imprime a informação do nó e seus filhos (ou null se não existirem)
            System.out.println(atual.getInfo() + "-" + atual.getFatBal() + 
                " -> " + (atual.getLeft() != null ? atual.getLeft().getInfo() + "-" + atual.getLeft().getFatBal() : "null") + 
                ", " + (atual.getRight() != null ? atual.getRight().getInfo() + "-" + atual.getRight().getFatBal() : "null"));
                
            // Adiciona os filhos à fila, mesmo que sejam null
            if (atual.getLeft() != null) fila.add(atual.getLeft());
            if (atual.getRight() != null) fila.add(atual.getRight());
        }
    }

    public void RemoveAVLNode(T valor){ 
        AVLNode<T> atual = null;
        AVLNode<T> percorrer = root; 
        if (isEmpty()){ 
            return;
        }
        while(percorrer != null && !percorrer.getInfo().equals(valor)){
            atual = percorrer;
            if (percorrer.getInfo().compareTo(valor) > 0){
                percorrer = percorrer.getLeft();  
            } else {
                percorrer = percorrer.getRight(); 
            }
        }
        if(percorrer == null){ // Nó não encontrado
            return;
        }
        //1 - nó sem filhos
        if(percorrer.getLeft() == null && percorrer.getRight() == null){
            if (percorrer == root) { // Se for a raiz a árvore fica vazia
                root = null; 
            } else if (atual.getLeft() == percorrer) {
                atual.setLeft(null);  
            } else {
                atual.setRight(null); 
            }
        }
        //2 - nó com um único filho 
        else if(percorrer.getLeft() == null){
            if (percorrer == root) {
                root = percorrer.getRight();  // Se o nó for a raiz a nova raiz será seu filho direito
            } else if(atual.getLeft() == percorrer){
                atual.setLeft(percorrer.getRight()); 
            } else{
                atual.setRight(percorrer.getRight()); // Se estava à direita do pai, substitui pelo filho direito
            }

        } else if(percorrer.getRight() == null){
            if (percorrer == root) {
                root = percorrer.getLeft(); // Se o nó for a raiz, a nova raiz será seu filho esquerdo
            } else if(atual.getLeft() == percorrer){
                atual.setLeft(percorrer.getLeft());
            } else{
                atual.setRight(percorrer.getLeft()); // Se estava à direita do pai, substitui pelo filho esquerdo
            }
        }
        // 3 - nó com dois filhos  
        else{
            AVLNode<T> AtualmaxPercorrer = percorrer; 
            AVLNode<T> maxPercorrer = percorrer.getLeft(); // Maior nó da subárvore esquerda
        
            while (maxPercorrer.getRight() != null){ // Encontra o maior elemento da subárvore esquerda
                AtualmaxPercorrer = maxPercorrer;
                maxPercorrer = maxPercorrer.getRight();
            }
        
            percorrer.setInfo(maxPercorrer.getInfo());  // Substitui o valor do nó a ser removido
        
            if(AtualmaxPercorrer.getRight() == maxPercorrer){ // Ajusta os ponteiros para remover o no da árvore
                AtualmaxPercorrer.setRight(maxPercorrer.getLeft());
            } else{
                AtualmaxPercorrer.setLeft(maxPercorrer.getLeft());
            }
        }

        Stack<AVLNode<T>> stack = new Stack<>();
        AVLNode<T> aux = root;
        while(!stack.isEmpty() || aux != null){ 
            while (aux != null) {
                stack.push(aux);
                aux = aux.getLeft();
            }
            aux = stack.pop();
            switch (aux.getFatBal()) { 
                case -2: //Rotação à esquerda necessária.
                    if(aux.getRight() != null && aux.getRight().getFatBal() > 0){// O filho direito está inclinado para a esquerda
                        aux.setRight(rotateRight(aux.getRight())); 
                    }
                    aux = rotateLeft(aux);
                    break;
                case 2: //Rotação à direita necessária.
                    if(aux.getLeft() != null && aux.getLeft().getFatBal() < 0){// O filho esquerdo está inclinado para a direita
                        aux.setLeft(rotateLeft(aux.getLeft())); 
                    }
                    aux = rotateRight(aux); 
                    break;
                default: 
                    break;
            }
            aux = aux.getRight();
        }
    } 
}