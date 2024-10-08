import java.util.LinkedList;
import java.util.Queue;


public class DistribuicaoSenhas 
{
        
    public static void main(String[] args) 
    {
            Atendente atendente = new Atendente();

            atendente.adicionarPessoa(new Pessoa("João", false));
            atendente.adicionarPessoa(new Pessoa("Maria", true));
            atendente.adicionarPessoa(new Pessoa("Ana", true));
            atendente.adicionarPessoa(new Pessoa("Carlos", false));
            atendente.adicionarPessoa(new Pessoa("Paulo", true));
            atendente.adicionarPessoa(new Pessoa("Beatriz", true)); 
            atendente.adicionarPessoa(new Pessoa("Lucas", false)); 
            atendente.adicionarPessoa(new Pessoa("Carla", true));  
    
            atendente.atender();
    }
}
class Pessoa 
{
    private String nome;
    private boolean ePrioritaria;

    public Pessoa(String nome, boolean ePrioritaria) 
    {
        this.nome = nome;
        this.ePrioritaria = ePrioritaria;
    }

    public String getNome() 
    {
        return nome;
    }

    public boolean isPrioritaria() 
    {
        return ePrioritaria;
    }

    @Override
    public String toString() 
    {
        return nome + (ePrioritaria ? " (Prioritária)" : " (Normal)");
    }
}

class Atendente 
{
    private Queue<Pessoa> filaNormal;
    private Queue<Pessoa> filaPrioritaria;
    private int contadorPrioritario = 0;

    public Atendente() 
    {
        this.filaNormal = new LinkedList<>();
        this.filaPrioritaria = new LinkedList<>();
    }

    public void adicionarPessoa(Pessoa pessoa) 
    {
        if (pessoa.isPrioritaria()) 
        {
            filaPrioritaria.add(pessoa);
        } 
        else 
        {
            filaNormal.add(pessoa);
        }
    }

    public void atender() {
        while (!filaNormal.isEmpty() || !filaPrioritaria.isEmpty()) 
        {
            if (contadorPrioritario < 3 && !filaPrioritaria.isEmpty()) 
            {
                Pessoa pessoa = filaPrioritaria.poll();
                System.out.println("Atendendo " + pessoa);
                contadorPrioritario++;
            } 
            else if (!filaNormal.isEmpty()) 
            {
                Pessoa pessoa = filaNormal.poll();
                System.out.println("Atendendo " + pessoa);
                contadorPrioritario = 0;
            } else if (!filaPrioritaria.isEmpty()) {
           
                Pessoa pessoa = filaPrioritaria.poll();
                System.out.println("Atendendo " + pessoa);
                contadorPrioritario++;
            }
        }

        System.out.println("Todas as pessoas foram atendidas!");
    }
}
