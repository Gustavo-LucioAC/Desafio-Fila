import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SistemaProntoSocorro{
    public static void main(String[] args) {
        ProntoSocorro prontoSocorro = new ProntoSocorro();

        prontoSocorro.adicionarPessoa(new Pessoa("João", "vermelho"));
        prontoSocorro.adicionarPessoa(new Pessoa("Maria", "amarelo"));
        prontoSocorro.adicionarPessoa(new Pessoa("Pedro", "verde"));
        prontoSocorro.adicionarPessoa(new Pessoa("Ana", "vermelho"));
        prontoSocorro.adicionarPessoa(new Pessoa("Carlos", "verde"));
        prontoSocorro.adicionarPessoa(new Pessoa("Beatriz", "amarelo"));

        Timer timerAtendimento = new Timer();
        timerAtendimento.schedule(new TimerTask() {
            @Override
            public void run() {
                prontoSocorro.atenderPessoa();
            }
        }, 0, 5000);

        Timer timerChegada = new Timer();
        timerChegada.schedule(new TimerTask() {
            @Override
            public void run() {
                prontoSocorro.chegadaAleatoria();
            }
        }, 0, 7000);
    }
}

class ProntoSocorro {
    private Queue<Pessoa> filaVermelha;
    private Queue<Pessoa> filaAmarela;
    private Queue<Pessoa> filaVerde;
    private Random random;

    public ProntoSocorro() {
        filaVermelha = new LinkedList<>();
        filaAmarela = new LinkedList<>();
        filaVerde = new LinkedList<>();
        random = new Random();
    }

    public void adicionarPessoa(Pessoa pessoa) {
        switch (pessoa.getPrioridade()) {
            case "vermelho":
                filaVermelha.add(pessoa);
                break;
            case "amarelo":
                filaAmarela.add(pessoa);
                break;
            case "verde":
                filaVerde.add(pessoa);
                break;
        }
        System.out.println("Chegou ao pronto socorro: " + pessoa);
    }

    public void atenderPessoa() {
        if (!filaVermelha.isEmpty()) {
            Pessoa pessoa = filaVermelha.poll();
            System.out.println("Atendendo: " + pessoa);
        } else if (!filaAmarela.isEmpty()) {
            Pessoa pessoa = filaAmarela.poll();
            System.out.println("Atendendo: " + pessoa);
        } else if (!filaVerde.isEmpty()) {
            Pessoa pessoa = filaVerde.poll();
            System.out.println("Atendendo: " + pessoa);
        } else {
            System.out.println("Nenhuma pessoa para atender no momento.");
        }
    }

    public void chegadaAleatoria() {
        String[] prioridades = {"vermelho", "amarelo", "verde"};
        String prioridadeAleatoria = prioridades[random.nextInt(prioridades.length)];
        String nomeAleatorio = "Pessoa" + (random.nextInt(100) + 1);
        Pessoa novaPessoa = new Pessoa(nomeAleatorio, prioridadeAleatoria);
        adicionarPessoa(novaPessoa);
    }
}
class Pessoa {
    private String nome;
    private String prioridade;

    public Pessoa(String nome, String prioridade) {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public String getPrioridade() {
        return prioridade;
    }

    @Override
    public String toString() {
        return nome + " (" + prioridade + ")";
    }
}