import com.Infnet.MedievalBattle.Heroi.Barbaro;
import com.Infnet.MedievalBattle.Heroi.Guerreiro;
import com.Infnet.MedievalBattle.Heroi.Paladino;
import com.Infnet.MedievalBattle.Monstro.Kobold;
import com.Infnet.MedievalBattle.Monstro.MortoVivo;
import com.Infnet.MedievalBattle.Monstro.Orc;
import com.Infnet.MedievalBattle.Personagem.Personagem;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class MedievalBattleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o seu nickname: ");
        String playerName = scanner.nextLine();

        System.out.println("Escolha a classe do herói:");
        System.out.println("1. Guerreiro");
        System.out.println("2. Barbaro");
        System.out.println("3. Paladino");

        int heroiChoice = scanner.nextInt();
        scanner.nextLine();

        Personagem heroChoice = criarHeroi(playerName, heroiChoice);

        if (heroChoice == null) {
            System.out.println("Escolha de herói inválida. Um Guerreiro foi escolhido por padrão.");
            heroChoice = new Guerreiro(playerName);
        }

        Personagem monstro = criarMonstroAleatorio();

        if (monstro == null) {
            System.out.println("Escolha de monstro inválida. Um Morto-Vivo foi escolhido por padrão.");
            monstro = new MortoVivo("Morto-Vivo");
        }

        System.out.println("Batalha começou!");
        System.out.println("Você, " + playerName + ", enfrentará um " + monstro.getNome() + " como um " + heroChoice + ".");

        int quantidadeDeRodadas = 0;

        while (heroChoice.estaVivo() && monstro.estaVivo()) {
            quantidadeDeRodadas++;
            System.out.println("\nRodada " + quantidadeDeRodadas + ":");

            // Cálculo de iniciativa
            int iniciativaHeroi = heroChoice.calcularIniciativa();
            int iniciativaMonstro = monstro.calcularIniciativa();

            // Quem tem a maior iniciativa inicia o ataque
            if (iniciativaHeroi > iniciativaMonstro) {
                realizarAtaque(heroChoice, monstro);
                realizarAtaque(monstro, heroChoice);
            } else if (iniciativaHeroi < iniciativaMonstro) {
                realizarAtaque(monstro, heroChoice);
                realizarAtaque(heroChoice, monstro);
            } else {
                // Em caso de empate na iniciativa, repete-se o teste
                System.out.println("Empate na iniciativa. Repetindo o teste.");
            }

            System.out.println("Sua saúde: " + heroChoice.getSaude());
            System.out.println("Saúde do " + monstro.getNome() + ": " + monstro.getSaude());
        }

        // Scripts para o CSV
        String resultado = heroChoice.estaVivo() ? "GANHOU" : "PERDEU";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataDaPartida = new Date();

        // Caminho para a pasta "temp" no mesmo diretório
        String caminhoDoArquivo = "temp/";

        // Escreva os resultados da batalha no arquivo CSV
        escreverDadosCSV(caminhoDoArquivo, dateFormat.format(dataDaPartida), playerName, resultado, monstro.getNome(), quantidadeDeRodadas);

        if (heroChoice.estaVivo()) {
            System.out.println("\nVocê derrotou o " + monstro.getNome() + "! Parabéns, " + playerName + "!");
        } else {
            System.out.println("\nVocê foi derrotado pelo " + monstro.getNome() + ". Game Over!");
        }

        scanner.close();
    }

    // Adicione este método para realizar um ataque entre dois personagens
    private static void realizarAtaque(Personagem atacante, Personagem alvo) {
        int dano = atacante.calcularDano();
        alvo.receberDano(dano);
        System.out.println(atacante.getNome() + " causou " + dano + " de dano ao " + alvo.getNome() + ".");
    }

    public static Personagem criarHeroi(String nomeJogador, int escolha) {
        return switch (escolha) {
            case 1 -> new Guerreiro(nomeJogador);
            case 2 -> new Barbaro(nomeJogador);
            case 3 -> new Paladino(nomeJogador);
            default -> null;
        };
    }

    public static Personagem criarMonstroAleatorio() {
        Random random = new Random();
        int escolha = random.nextInt(3) + 1;

        return switch (escolha) {
            case 1 -> new MortoVivo("Morto-Vivo");
            case 2 -> new Orc("Orc");
            case 3 -> new Kobold("Kobold");
            default -> null;
        };
    }

    public static void escreverDadosCSV(String caminhoDoArquivo, String data, String jogador, String resultado, String monstro, int quantidadeDeRodadas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoDoArquivo, true))) {
            writer.println(data + " || " + jogador + " || " + resultado + " || " + monstro + " || Rodadas: " + quantidadeDeRodadas);
        } catch (IOException e) {
            System.err.println("Erro ao gravar o arquivo CSV.");
            e.printStackTrace();
        }
    }
}
