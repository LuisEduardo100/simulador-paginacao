import java.util.*;

public class AlgoritmoOtimo {
    private int numFrames;
    private List<Integer> frames;
    private int pageFaults;

    public AlgoritmoOtimo(int numFrames) {
        this.numFrames = numFrames;
        this.frames = new ArrayList<>();
        this.pageFaults = 0;
    }

    public void executar(int[] referencias) {
        System.out.println("=== ALGORITMO ÓTIMO DE SUBSTITUIÇÃO DE PÁGINAS ===\n");
        System.out.println("Número de frames: " + numFrames);
        System.out.println("Sequência de referências: " + Arrays.toString(referencias));
        System.out.println("\n" + "=".repeat(60) + "\n");

        for (int i = 0; i < referencias.length; i++) {
            int paginaAtual = referencias[i];

            System.out.printf("Passo %d: Referência à página %d\n", i + 1, paginaAtual);

            if (frames.contains(paginaAtual)) {
                System.out.println("  → Página já está na memória (HIT)");
            } else {
                pageFaults++;
                System.out.println("  → PAGE FAULT!");

                if (frames.size() < numFrames) {
                    frames.add(paginaAtual);
                    System.out.println("  → Página " + paginaAtual + " adicionada (espaço disponível)");
                } else {
                    int paginaSubstituir = encontrarPaginaOtima(referencias, i);
                    int indice = frames.indexOf(paginaSubstituir);
                    frames.set(indice, paginaAtual);
                    System.out.println("  → Página " + paginaSubstituir + " substituída por " + paginaAtual);
                }
            }

            System.out.println("  Memória: " + frames);
            System.out.println();
        }

        System.out.println("=".repeat(60));
        System.out.println("\n*** RESULTADO FINAL ***");
        System.out.println("Total de Page Faults: " + pageFaults);
        System.out.println("Taxa de Page Faults: " +
            String.format("%.2f%%", (pageFaults * 100.0) / referencias.length));
    }

    private int encontrarPaginaOtima(int[] referencias, int posicaoAtual) {
        int paginaSubstituir = -1;
        int distanciaMaxima = -1;

        for (int pagina : frames) {
            int proximoUso = encontrarProximoUso(referencias, posicaoAtual, pagina);

            if (proximoUso == -1) {
                return pagina;
            }

            if (proximoUso > distanciaMaxima) {
                distanciaMaxima = proximoUso;
                paginaSubstituir = pagina;
            }
        }

        return paginaSubstituir;
    }


    private int encontrarProximoUso(int[] referencias, int posicaoAtual, int pagina) {
        for (int i = posicaoAtual + 1; i < referencias.length; i++) {
            if (referencias[i] == pagina) {
                return i;
            }
        }
        return -1;
    }

    public int getPageFaults() {
        return pageFaults;
    }
}