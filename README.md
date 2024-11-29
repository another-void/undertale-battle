Undertale Battle Game

Este é um simples jogo inspirado no sistema de batalha de Undertale, implementado em Java. O jogo consiste em um jogador que deve se esquivar de balas que caem do topo da tela. O jogador pode se mover para cima, para baixo, para esquerda e para direita, enquanto as balas caem com uma velocidade crescente ao longo do tempo.

Requisitos

Java 8 ou superior: O código foi escrito utilizando a linguagem Java e utiliza a biblioteca Swing para a interface gráfica.
IDE ou terminal para rodar o projeto. Caso você não tenha uma IDE instalada, você pode rodar o código diretamente pelo terminal.
Funcionalidades

Movimento do Jogador: O jogador se move usando as setas direcionais (cima, baixo, esquerda, direita).

Balas: As balas começam a cair da parte superior da tela e o jogador deve evitá-las. A velocidade das balas aumenta com o tempo.

Game Over: O jogo termina quando o jogador colide com uma bala. O jogador então verá uma tela de Game Over com a opção de reiniciar o jogo pressionando a tecla "1".

Tela Inicial: O jogo começa na tela inicial, onde o jogador pode pressionar "Enter" para começar a partida.

Contador de Tempo: O tempo decorrido desde o início do jogo é mostrado na tela.

Instruções de Jogo

Ao iniciar o jogo, pressione Enter para começar.
Use as setas para mover o retângulo branco (que representa o jogador).
O objetivo é evitar as balas que caem da parte superior da tela.
O jogo aumentará a velocidade das balas à medida que o tempo passa.
Se você for atingido por uma bala, o jogo mostrará uma tela de Game Over.
Para reiniciar o jogo, pressione 1.


CHECKLIST DO PROJETO

Fase 1: Análise -
 Problema selecionado e definido claramente:
Criar um sistema de jogo estilo Undertale, no qual o jogador deve evitar balas em queda.

 Compreensão aprofundada da natureza e desafios do problema:
O desafio principal é garantir uma jogabilidade fluida e a detecção de colisões precisa entre o jogador e as balas.

 Modelo matemático ou teórico desenvolvido para representar o problema:
Utilização de coordenadas cartesianas para representar as posições do jogador e das balas. Verificação de colisão com base na sobreposição de retângulos.

Fase 2: Planejamento -
 Objetivos do algoritmo definidos com clareza:
Criar um jogo funcional com movimentação do jogador, balas caindo, detecção de colisão e tela de Game Over.

 Métricas para avaliação de eficiência do algoritmo estabelecidas:
Tempo de resposta do jogador e fluidez do jogo em diferentes máquinas (FPS).

 Estratégia geral de resolução do problema proposta:
Usar Java Swing para criar a interface gráfica e lógica de jogo básica.

 Subproblemas identificados e divididos, se aplicável:
Movimentação do jogador, spawn aleatório das balas, detecção de colisões, reinício do jogo e aumento progressivo de dificuldade.

 Estrutura geral do algoritmo esboçada:
Loop principal para atualizar o estado do jogo, renderizar objetos e verificar colisões.

 Casos limite ou situações especiais identificados:
Jogador preso nas bordas da tela e colisões que podem não ser detectadas.

 Análise teórica realizada para verificar a correção do algoritmo:
Detecção de colisão validada com base nas dimensões dos retângulos.


Fase 3: Desenho -
 Análise de complexidade realizada para avaliar a eficiência teórica do algoritmo:
A complexidade para verificar colisões é proporcional ao número de balas presentes na tela em cada frame.

 Pontos críticos do algoritmo identificados para otimização, se necessário:
Reduzir o número de balas em jogo ou a frequência de spawn para evitar sobrecarga.


Fase 4: Programação e Teste -
 Algoritmo traduzido com precisão em código de programação:
Todo o código foi implementado utilizando Java e Swing.

 Código de programação escrito de forma clara e organizada:
Foram usadas classes para separar a lógica do jogo (movimento, colisão) e interface gráfica.

 Testes rigorosos realizados em uma variedade de casos de teste:
Testes realizados com diferentes velocidades de bala e tamanhos de tela.

 Casos limite e situações especiais testados:
Jogador encostando nas bordas e balas atingindo o jogador.

 Erros e problemas durante o teste do programa identificados e corrigidos:
Corrigido problema com a movimentação do jogador e ajuste no tamanho do retângulo.


Documentação e Avaliação do Projeto -
 Documentação completa, incluindo especificação do algoritmo e análise de complexidade.
 
Todo o processo foi documentado no README e no código-fonte.
 Documentação revisada para clareza, rigor técnico e para descrever os passos de implementação e a lógica do jogo.
 
 Avaliação da eficácia do algoritmo em termos de tempo de execução, uso de recursos e precisão na resolução do problema.
 
O jogo roda de forma fluida em máquinas modernas com tempo de resposta adequado.

O trabalho foi realizado de forma individual com prazos atendidos.

Integrante: Marcos Anderson Almeida Oliveira
