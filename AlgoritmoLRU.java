import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class AlgoritmoLRU {
    private int numFrames;
    private Map<Integer, Integer> cache;
    private int pageFaults;

    public AlgoritmoLRU(int numFrames) {
        this.numFrames = numFrames;
        this.cache = new LinkedHashMap<>(numFrames, 0.75f, true);
        this.pageFaults = 0;
    }

    public void executar(int[] referencias) {
        System.out.println("=== ALGORITMO LRU DE SUBSTITUIÇÃO DE PÁGINAS ===\n");
        System.out.println("Número de frames: " + numFrames);
        System.out.println("Sequência de referências: " + Arrays.toString(referencias));
        System.out.println("\n" + "=".repeat(60) + "\n");

        for (int i = 0; i < referencias.length; i++) {
            int paginaAtual = referencias[i];

            System.out.printf("Passo %d: Referência à página %d\n", i + 1, paginaAtual);

            if (cache.containsKey(paginaAtual)) {
                System.out.println("  → Página já está na memória (HIT)");
                cache.get(paginaAtual);
            } else {
                pageFaults++;
                System.out.println("  → PAGE FAULT!");

                if (cache.size() < numFrames) {
                    cache.put(paginaAtual, paginaAtual);
                    System.out.println("  → Página " + paginaAtual + " adicionada (espaço disponível)");
                } else {
                    int paginaSubstituir = cache.keySet().iterator().next();
                    cache.remove(paginaSubstituir);
                    cache.put(paginaAtual, paginaAtual);

                    System.out.println("  → Página " + paginaSubstituir + " (a menos recente) substituída por " + paginaAtual);
                }
            }

            System.out.println("  Memória: " + new ArrayList<>(cache.keySet()));
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
