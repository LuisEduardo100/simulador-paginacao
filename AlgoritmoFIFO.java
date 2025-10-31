import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgoritmoFIFO {
    private int numFrames;
    private List<Integer> frames;
    private int pageFaults;
    private int ponteiroFIFO;

    public AlgoritmoFIFO(int numFrames) {
        this.numFrames = numFrames;
        this.frames = new ArrayList<>(numFrames);
        this.pageFaults = 0;
        this.ponteiroFIFO = 0;
    }

    public void executar(int[] referencias) {
        System.out.println("=== ALGORITMO FIFO DE SUBSTITUIÇÃO DE PÁGINAS ===\n");
        System.out.println("Número de frames: " + numFrames);
        System.out.println("Sequência de referências: " + Arrays.toString(referencias));
        System.out.println("\n" + "=".repeat(60) + "\n");

        for (int i = 0; i < referencias.length; i++) {
            int paginaAtual = referencias[i];

            System.out.printf("Passo %d: Referência à página %d\n", i + 1, paginaAtual);

            if (frames.contains(paginaAtual)) {
                System.out.println("  → Página já está na memória (HIT)");
            } else {
                pageFaults++;
                System.out.println("  → PAGE FAULT!");

                if (frames.size() < numFrames) {
                    frames.add(paginaAtual);
                    System.out.println("  → Página " + paginaAtual + " adicionada (espaço disponível)");
                } else {
                    int paginaSubstituir = frames.get(ponteiroFIFO);
                    frames.set(ponteiroFIFO, paginaAtual);
                    System.out.println("  → Página " + paginaSubstituir + " (a mais antiga) substituída por " + paginaAtual);

                    ponteiroFIFO = (ponteiroFIFO + 1) % numFrames;
                }
            }

            System.out.println("  Memória: " + frames);
            System.out.println();
        }

        System.out.println("=".repeat(60));
        System.out.println("\n*** RESULTADO FINAL ***");
        System.out.println("Total de Page Faults: " + pageFaults);
        System.out.println("Taxa de Page Faults: " +
            String.format("%.2f%%", (pageFaults * 100.0) / referencias.length));
    }

    public int getPageFaults() {
        return pageFaults;
    }
}