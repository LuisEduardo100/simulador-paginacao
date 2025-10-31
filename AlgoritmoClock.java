import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AlgoritmoClock {
    private int numFrames;
    private int[] frames;
    private boolean[] rBits;
    private int ponteiroRelogio;
    private int pageFaults;

    private Set<Integer> paginasNaMemoria;

    public AlgoritmoClock(int numFrames) {
        this.numFrames = numFrames;
        this.frames = new int[numFrames];
        this.rBits = new boolean[numFrames];
        this.paginasNaMemoria = new HashSet<>(numFrames);
        this.ponteiroRelogio = 0;
        this.pageFaults = 0;

        Arrays.fill(this.frames, -1);
    }

    public void executar(int[] referencias) {
        System.out.println("=== ALGORITMO CLOCK (RELÓGIO) DE SUBSTITUIÇÃO DE PÁGINAS ===\n");
        System.out.println("Número de frames: " + numFrames);
        System.out.println("Sequência de referências: " + Arrays.toString(referencias));
        System.out.println("\n" + "=".repeat(60) + "\n");

        for (int i = 0; i < referencias.length; i++) {
            int paginaAtual = referencias[i];

            System.out.printf("Passo %d: Referência à página %d\n", i + 1, paginaAtual);

            if (paginasNaMemoria.contains(paginaAtual)) {
                System.out.println("  → Página já está na memória (HIT)");
                int indice = encontrarIndiceDaPagina(paginaAtual);
                rBits[indice] = true;
            } else {
                pageFaults++;
                System.out.println("  → PAGE FAULT!");

                while (true) {
                    if (frames[ponteiroRelogio] == -1) {
                        frames[ponteiroRelogio] = paginaAtual;
                        rBits[ponteiroRelogio] = true;
                        paginasNaMemoria.add(paginaAtual);
                        System.out.println("  → Página " + paginaAtual + " adicionada (espaço disponível)");

                        avancarPonteiro();
                        break;

                    } else if (rBits[ponteiroRelogio] == true) {
                        rBits[ponteiroRelogio] = false;
                        avancarPonteiro();
                    } else {
                        int paginaSubstituir = frames[ponteiroRelogio];

                        paginasNaMemoria.remove(paginaSubstituir);
                        frames[ponteiroRelogio] = paginaAtual;
                        rBits[ponteiroRelogio] = true;
                        paginasNaMemoria.add(paginaAtual);

                        System.out.println("  → Página " + paginaSubstituir + " (R-bit=0) substituída por " + paginaAtual);

                        avancarPonteiro();
                        break;
                    }
                }
            }

            System.out.println("  Memória: " + Arrays.toString(frames));
            System.out.println("  R-Bits : " + Arrays.toString(rBits));
            System.out.println("  Ponteiro: " + ponteiroRelogio);
            System.out.println();
        }

        System.out.println("=".repeat(60));
        System.out.println("\n*** RESULTADO FINAL ***");
        System.out.println("Total de Page Faults: " + pageFaults);
        System.out.println("Taxa de Page Faults: " +
            String.format("%.2f%%", (pageFaults * 100.0) / referencias.length));
    }

    private void avancarPonteiro() {
        ponteiroRelogio = (ponteiroRelogio + 1) % numFrames;
    }

    private int encontrarIndiceDaPagina(int pagina) {
        for (int i = 0; i < numFrames; i++) {
            if (frames[i] == pagina) {
                return i;
            }
        }
        return -1;
    }

    public int getPageFaults() {
        return pageFaults;
    }
}
