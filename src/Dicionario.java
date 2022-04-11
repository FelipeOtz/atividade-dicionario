import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dicionario {

	/*
	 * Entrega do Trabalho 1- Algoritmos e Programa��o II eu, 
	 * Felipe Ortiz, 
	 * declaro que todas as respostas s�o fruto de meu pr�prio trabalho, n�o copiei
	 * respostas de colegas externos � equipe, n�o disponibilizei nossas respostas
	 * para colegas externos ao grupo e n�o realizei quaisquer outras atividades
	 * desonestas para nos beneficiar ou prejudicar outros.
	 */

	// variavel que faz contagem de palvras unicas
	static int palavrasUnicas = 0;

	public static void main(String[] args) throws Exception {
		Scanner ler = new Scanner(System.in);
		System.out.println("Qual o nome/caminho do arquivo que deve ser lido?");

		String nomeArquivo = ler.next();

		// recebe o vetor que foi feito o Split
		String[] palavrasSplitadas = fazSplit(leArquivo(nomeArquivo));

		// ordena as palavras
		String[] palavras = ordenaPalavras(palavrasSplitadas);

		// ordena o dicionario utiliando as palavras ja ordenadas e exclui as respitidas
		String[] dicionario = ordenaDicionario(palavras);

		// imprime o dicionario
		imprimirDicionario(dicionario);
		
		//imprime a quantidade de palavras �nicas
		System.out.println("Quantidade de palavras �nicas: " + palavrasUnicas);

	}

	

	// faz split
	public static String[] fazSplit(String leitura) {
		String[] palavrasSplitadas = leitura.split(" ");
		return palavrasSplitadas;
	}

	// Faz busca binaria, verificando se h� alguma palavra igual e retorna sua
	// posi��o
	public static int temPalavraigual(String[] dicionario, String x) {
		int inicio = 0, fim = dicionario.length - 1;
		while (inicio <= fim) {
			int meio = inicio + (fim - inicio) / 2;

			int res = x.compareTo(dicionario[meio]);

			if (res == 0)
				return meio;

			if (res > 0)
				inicio = meio + 1;

			else
				fim = meio - 1;
		}

		return -1;
	}

	// Ordena o vetor de palavras com inserction sort
	public static String[] ordenaPalavras(String[] palavras) {

		palavras[0] = palavras[0];
		for (int i = 1; i < palavras.length; i++) {
			int j = i - 1;
			String x = palavras[j];
			while ((j > 0 && 0 > x.compareTo(palavras[j - 1]))) {
				palavras[j] = palavras[j - 1];
				j--;
			}
			palavras[j] = x;
		}
		return palavras;
	}

	// insere no vetor dicinario, as palvras de forma ordenada e excluindo as repitidas
	public static String[] ordenaDicionario(String[] palavras) {

		String[] dicionario = new String[1000];

		for (int j = 0; j < palavras.length - 1; j++) {
			if (temPalavraigual(palavras, palavras[j]) == (j)) {
				// baseado em sua posi��o retornada de temPalavraigual(), seleciona as palavras unicas
				dicionario[j] = palavras[j];

				// Aumenta o valor de palvras �nicas
				palavrasUnicas += 1;

			} else {
				// caso temPalavraigual() retorne uma palavra repitida baseado em sua posi��o
				// transforma a palavra em "".
				dicionario[j] = "";
			}
		}
		return dicionario;
	}

	// Imprime o dicionario
	public static void imprimirDicionario(String[] dicionario) {
		int i = 0;
		while (dicionario[i] != null) {
			if (!dicionario[i].equals("")) { // palavras repitidas transformadas em "" n�o ser� impressa
				System.out.println(dicionario[i]);
			}
			i++;
		}

	}

	// le o arquivo
	public static String leArquivo(String file) throws FileNotFoundException {

		File arquivo = new File(file);
		Scanner leitor = new Scanner(arquivo);
		String leitura = "";

		while (leitor.hasNextLine()) {
			// concatena as linhas com um espa�o depois de cada linha lida
			leitura += leitor.nextLine() + " ";
		}

		leitor.close();
		return leitura.toLowerCase().trim();

	}

}