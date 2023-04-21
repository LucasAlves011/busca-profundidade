package com.example.guiprofundidade2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class HelloApplication extends Application {

    public static No objetivo=new No(null, new int[][]{{1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}});

    public static No inicial=new No(null, new int[][]{{8, 7, 6},
            {5, 4, 3},
            {2, 1, 0}});

    public static List<No> resultado;

    public static int expandidos=0;

    public static StringBuilder sb=new StringBuilder();

    public static void main(String[] args) {
        boolean paridade=paridade(inicial.estado);
        int max_Profundidade=10;

        if (paridade) {

            while (resultado == null) {
                resultado=buscaEmProfundidade(inicial, objetivo, max_Profundidade);
                max_Profundidade+=5;
            }

            launch();
        } else {
            System.out.println("\u001B[41m \u001B[30m" + "Não é possivel resolver " + "\u001B[0m");
            System.out.println("A paridade do estado inicial é ímpar, impossibilitando que o sistema tenha solução.");
        }
    }

    public static boolean paridade(int[][] estado) {
        int tamanho=estado.length * estado.length;
        int[] vetor=new int[tamanho];

        // Transforma a matriz em um vetor
        for (int i=0; i < estado.length; i++) {
            for (int j=0; j < estado.length; j++) {
                vetor[i * estado.length + j]=estado[i][j];
            }
        }

        int inversoes=0;
        for (int i=0; i < tamanho - 1; i++) {
            for (int j=i + 1; j < tamanho; j++) {
                if (vetor[i] != 0 && vetor[j] != 0 && vetor[i] > vetor[j]) {
                    inversoes++;
                }
            }
        }
        return inversoes % 2 == 0;
    }

    public static List<No> buscaEmProfundidade(No noInicial, No noObjetivo, int maxProfundidade) {
        Stack<No> pilha=new Stack<>();
        Set<No> explorados=new HashSet<>();
        expandidos=0;

        pilha.push(noInicial);

        while (!pilha.isEmpty()) {
            No noAtual=pilha.pop();
            System.out.println("Tamanho da pilha: " + pilha.size() + " ---- Explorados " + explorados.size() + " ---- Profundidade: " + noAtual.profundidade + " ---- Estado: " + Arrays.deepToString(noAtual.estado));

            explorados.add(noAtual);
            if (noAtual.equals(noObjetivo)) {
                System.out.println("Nós expandidos: " + expandidos + " ---- Profundidade: " + maxProfundidade);
                return getCaminho(noAtual);
            }

            if (noAtual.profundidade < maxProfundidade) {
                List<String> acoes=possiveisAcoes(noAtual.estado);
                for (String acao : acoes) {
                    No filho=new No(noAtual, swap(noAtual.estado, acao));
                    if (!explorados.contains(filho) && !pilha.contains(filho)) {
                        pilha.push(filho);
                        expandidos++;
                    }
                }
            }
        }

        System.out.println("\u001B[41m \u001B[30m" + " Não encontrei o objetivo!" + "\u001B[0m");
        System.out.println("Nós expandidos: " + expandidos);
        return null;
    }

    public static List<String> possiveisAcoes(int[][] estado) {
        List<String> acoes=new ArrayList<>();
        int[] posicaoZero=procurarZero(estado);

        if (posicaoZero[1] > 0)
            acoes.add("esquerda");
        if (posicaoZero[0] > 0)
            acoes.add("cima");
        if (posicaoZero[1] < 2)
            acoes.add("direita");
        if (posicaoZero[0] < 2)
            acoes.add("baixo");
        Collections.reverse(acoes);
        return acoes;
    }

    public static int[][] swap(int[][] estado, String acao) {
        int[] posicaoZero=procurarZero(estado);

        int[][] novoEstado=new int[3][3];

        for (int i=0; i < 3; i++) {
            System.arraycopy(estado[i], 0, novoEstado[i], 0, 3);
        }

        switch (acao) {
            case "cima" -> {
                novoEstado[posicaoZero[0]][posicaoZero[1]]=novoEstado[posicaoZero[0] - 1][posicaoZero[1]];
                novoEstado[posicaoZero[0] - 1][posicaoZero[1]]=0;
            }
            case "baixo" -> {
                novoEstado[posicaoZero[0]][posicaoZero[1]]=novoEstado[posicaoZero[0] + 1][posicaoZero[1]];
                novoEstado[posicaoZero[0] + 1][posicaoZero[1]]=0;
            }
            case "esquerda" -> {
                novoEstado[posicaoZero[0]][posicaoZero[1]]=novoEstado[posicaoZero[0]][posicaoZero[1] - 1];
                novoEstado[posicaoZero[0]][posicaoZero[1] - 1]=0;
            }
            case "direita" -> {
                novoEstado[posicaoZero[0]][posicaoZero[1]]=novoEstado[posicaoZero[0]][posicaoZero[1] + 1];
                novoEstado[posicaoZero[0]][posicaoZero[1] + 1]=0;
            }
        }
        return novoEstado;
    }

    public static List<No> getCaminho(No no) {
        List<No> caminho=new ArrayList<>();
        No noAtual=no;
        while (noAtual != null) {
            caminho.add(noAtual);
            noAtual=noAtual.pai;
        }
        Collections.reverse(caminho);
        System.out.println("\u001B[42m \u001B[30m" + "Encontrei o objetivo!" + "\u001B[0m");
        System.out.println("Caminho Size: " + caminho.size());
        return caminho;
    }

    public static int[] procurarZero(int[][] estado) {
        int[] posicaoZero=new int[2];

        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                if (estado[i][j] == 0)
                    posicaoZero=new int[]{i, j};
            }
        }
        return posicaoZero;
    }

    public static class No {
        public No pai;
        public int[][] estado;
        public int profundidade;

        public No(No pai, int[][] estado) {
            this.pai=pai;
            this.estado=estado;
            if (pai == null)
                this.profundidade=0;
            else
                this.profundidade=pai.profundidade + 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            No no=(No) o;

            return Arrays.deepEquals(estado, no.estado);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(estado);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("busca.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        stage.setTitle("Busca em Profundidade! - By: Lucas Alves e Matheus Florentino");
        stage.setScene(scene);
        stage.show();
    }
}