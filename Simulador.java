import java.util.Arrays;

public class Simulador {

    public static void main(String[] args) {
        // Definição de variáveis

        int numFrames = 3;

        int[] referencias = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };

        System.out.println("Iniciando Simulador de Substituição de Páginas...");
        System.out.println("Número de Frames: " + numFrames);
        System.out.println("Cadeia de Referência: " + Arrays.toString(referencias) + "\n");

        // Execução dos algoritmos

        AlgoritmoOtimo otimo = new AlgoritmoOtimo(numFrames);
        otimo.executar(referencias);

        AlgoritmoFIFO fifo = new AlgoritmoFIFO(numFrames);
        fifo.executar(referencias);

        AlgoritmoLRU lru = new AlgoritmoLRU(numFrames);
        lru.executar(referencias);

        AlgoritmoClock clock = new AlgoritmoClock(numFrames);
        clock.executar(referencias);


        // --- RESULTADO FINAL COMPILADO ---
        // Exatamente como pedido no objetivo 3 do trabalho

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPARAÇÃO FINAL DE PAGE FAULTS");
        System.out.println("=".repeat(60) + "\n");

        System.out.println("Método Clock - " + clock.getPageFaults() + " faltas de página");
        System.out.println("Método FIFO - " + fifo.getPageFaults() + " faltas de página");
        System.out.println("Método LRU - " + lru.getPageFaults() + " faltas de página");
        System.out.println("Método Ótimo - " + otimo.getPageFaults() + " faltas de página");

    }
}