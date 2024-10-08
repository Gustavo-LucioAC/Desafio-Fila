import java.util.LinkedList;
import java.util.Queue;

public class FilaDeImpressao {
    public static void main(String[] args) {
        
        Queue<Documento> filaDeImpressao = new LinkedList<>();

        filaDeImpressao.add(new Documento("Documento1", 5));
        filaDeImpressao.add(new Documento("Documento2", 3));
        filaDeImpressao.add(new Documento("Documento3", 7));

        Impressora impressora = new Impressora(filaDeImpressao);
        Thread threadImpressora = new Thread(impressora);

        threadImpressora.start();
    }
}
class Documento {
    String nome;
    int quantidadeFolhas;

    public Documento(String nome, int quantidadeFolhas) {
        this.nome = nome;
        this.quantidadeFolhas = quantidadeFolhas;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeFolhas() {
        return quantidadeFolhas;
    }
}

class Impressora implements Runnable {
    
    private Queue<Documento> filaDeImpressao;

    public Impressora(Queue<Documento> filaDeImpressao) {
        this.filaDeImpressao = filaDeImpressao;
    }

    @Override
    public void run() {
        while (!filaDeImpressao.isEmpty()) {
            Documento documento = filaDeImpressao.poll();
            if (documento != null) {
                System.out.println("Imprimindo documento: " + documento.getNome());
                try 
                {                   
                    Thread.sleep(documento.getQuantidadeFolhas() * 1000);

                } catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
                System.out.println("Documento " + documento.getNome() + " impresso.");
            }
        }
    }
}