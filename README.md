# Simulador de Algoritmos de Substituição de Páginas

Este projeto, desenvolvido para a disciplina de Sistemas Operacionais, implementa e compara o desempenho de quatro algoritmos de substituição de páginas: Ótimo, FIFO (First-In, First-Out), LRU (Least Recently Used) e Clock (Relógio/Segunda Chance).

## Algoritmos Implementados

- **Ótimo:** O algoritmo ideal, que serve como base de comparação.
- **FIFO:** Substitui a página que está há mais tempo na memória.
- **LRU:** Substitui a página que foi usada menos recentemente.
- **Clock:** Uma implementação eficiente que simula o LRU usando um "bit de referência" (R-bit).

## Como Executar

O simulador é escrito em Java e não requer bibliotecas externas.

### Pré-requisitos

- Você precisa ter o **JDK (Java Development Kit)** instalado em sua máquina.

### Passos para Execução

1.  **Clone ou baixe** os arquivos `.java` deste repositório.

2.  **Abra seu terminal** ou prompt de comando e navegue até a pasta onde os arquivos estão.

3.  **Compile** todos os arquivos `.java` com o seguinte comando:

    ```bash
    javac *.java
    ```

4.  **Execute** a classe principal `Simulador`:

    ```bash
    java -cp . Simulador
    ```

#### **OBS**: caso tenha dificuldade de rodar com o código acima, tente:

    para compilar:

      ```bash
    javac -encoding UTF-8 *.java
    ```

    para rodar o main:

    ```bash
    java Simulador
    ```

5.  O _trace_ completo de cada algoritmo será impresso no terminal, seguido por um resumo comparativo das faltas de página no final.

### Alterando os Parâmetros

Você pode facilmente alterar a sequência de referência de páginas ou o número de frames editando as variáveis no topo do arquivo `Simulador.java`:

```java
public class Simulador {
    public static void main(String[] args) {
        // Definição de variáveis

        int numFrames = 3;

        int[] referencias = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };

        //... resto do código ...
    }
}
```
