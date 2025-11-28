# Sudoku-COM755

# Sudoku Solver - Computação de Alto Desempenho

## 1. Introdução

Este projeto consiste na implementação completa de um jogo de Sudoku e de um *solver* automatizado, desenvolvido como atividade avaliativa da disciplina de Computação de Alto Desempenho.

O objetivo principal foi aplicar o algoritmo de **Backtracking** tanto para a resolução quanto para a geração procedural de tabuleiros válidos. O projeto vai além da lógica algorítmica, apresentando uma aplicação completa com interface gráfica amigável, persistência de dados e diferentes modos de jogo.

## 2. Identificação Acadêmica

* **Aluno:** Gabriel Palace Novaes Henrique
* **Disciplina:** Computação de Alto Desempenho (COM755)
* **Professor:** Sergio Yoshioka
* **Curso:** Engenharia da Computação

## 3. Funcionalidades e Diferenciais

* **Algoritmo de Backtracking:** Núcleo do sistema para criação e resolução de tabuleiros.
* **Níveis de Dificuldade:**
  * **Fácil, Médio, Difícil:** Garantia matemática de solução **única**.
  * **Modo CAOS:** Gera tabuleiros com **múltiplas soluções**, permitindo exploração livre.
* **Interface Gráfica (GUI):** Desenvolvida em Swing com a biblioteca **FlatLaf** para visual moderno e responsivo.
* **Persistência Completa:** Login de usuários, salvamento de score e capacidade de **Salvar/Carregar** partidas em andamento via MySQL.
* **Validações Visuais:** Feedback imediato de jogadas válidas/inválidas e completude do tabuleiro.

## 4. Relatório Técnico de Implementação

Conforme requisito da avaliação, abaixo detalho o processo de desenvolvimento, desafios e soluções.

### 4.1. Experiência Geral
A implementação da base do projeto foi fluida, dado o conhecimento prévio nas tecnologias utilizadas (Java e Swing). A estrutura MVC (Model-View-Controller) facilitou a separação entre a lógica do tabuleiro e a interface do usuário.

### 4.2. Desafios de Interface (UI)
Um dos desafios específicos foi o controle fino dos componentes de entrada (`JTextField`). Para garantir uma experiência de usuário robusta, foi necessário implementar uma classe interna `DocumentFilter` dentro do `SudokuPanel`.
* **Objetivo:** Impedir a entrada de caracteres não numéricos e limitar o campo a apenas um dígito.
* **Feedback Visual:** Implementação de ouvintes para alterar a cor da célula quando clicada ou focada, melhorando a usabilidade.

### 4.3. Desafio da Unicidade (Geração de Tabuleiro)
O obstáculo mais complexo foi a lógica de geração de jogos.
* **O Problema:** Inicialmente, o algoritmo removia peças aleatoriamente do tabuleiro completo. Entretanto, ao remover muitas peças sem validação, o jogo passava a ter **várias soluções possíveis**, o que é inválido para um Sudoku clássico.
* **A Solução:** Foi implementada uma verificação robusta baseada em Backtracking antes de finalizar a criação do tabuleiro. Os métodos cruciais desenvolvidos foram:
    1.  `popularTabuleiro()`: Preenche a grade inicial.
    2.  `BacktrackingSolver` e `buscarSolucoesRecursivo`: Core do algoritmo.
    3.  `contarSolucoes()`: Garante a integridade do jogo verificando o número de soluções possíveis após a remoção de cada peça. O comportamento é dinâmico e controlado pela variável **`limiteSolucoes`** (definida no método `gerarJogo` da classe `Sudoku`). Se a remoção de uma peça fizer o número de soluções exceder esse limite (garantindo solução única nos modos clássicos), o algoritmo desfaz a ação (*backtrack*).

## 5. Análise de Desempenho

O desempenho do algoritmo foi medido em uma máquina padrão. O Backtracking se mostrou extremamente eficiente para grades $9\times9$.

### Tabela de Tempos de Resolução (Solver)

| Dificuldade | Peças Removidas | Comportamento | Tempo Médio (ms) |
| :--- | :---: | :--- | :---: |
| **Fácil** | 36 | Instantâneo | ~2 ms |
| **Médio** | 46 | Instantâneo | ~15 ms |
| **Difícil** | 56 | Muito Rápido | ~45 ms |
| **Modo Caos** | 62 | Varia conforme número de soluções | ~180 ms* |

*\*Nota sobre o Modo Caos:* O tempo de geração e resolução no modo Caos é superior. Isso ocorre porque o algoritmo precisa verificar múltiplas ramificações de soluções. Foi observado que, se a variável interna `limiteSolucoes` for configurada para um valor muito alto, o tempo de processamento cresce exponencialmente, podendo causar lentidão ou travamentos momentâneos na interface devido ao custo computacional de encontrar todas as permutações possíveis.

---

## 6. Tecnologias e Bibliotecas

* **Linguagem:** Java (JDK 17+)
* **IDE:** Apache NetBeans 23
* **Interface:** Java Swing + [FlatLaf](https://www.formdev.com/flatlaf/)
* **Banco de Dados:** MySQL (Connector/J)

## 7. Instalação e Execução

### Pré-requisitos
1.  Ter o **MySQL** instalado.
2.  Criar um banco de dados e importar o arquivo `SudokuDump.sql` (disponível no repositório).

### Configuração
Edite a classe `DBConnection.java` com suas credenciais locais:

```java
private static final String URL = "jdbc:mysql://localhost:3306/sudoku_db";
private static final String USER = "seu_usuario"; // ex: root
private static final String PASS = "sua_senha";
