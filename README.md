# 💧 Jogo — Coletor de Gotas (Java + LibGDX)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![LibGDX](https://img.shields.io/badge/LibGDX-CC0000?style=for-the-badge&logo=libgdx&logoColor=white)
![IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-blue?style=for-the-badge)

---

## 🕹️ Descrição

Este é um **jogo simples feito em Java com LibGDX**, desenvolvido no **IntelliJ IDEA**.  
O jogador controla um **balde** para **coletar gotas de água** que caem da parte superior da tela.  
Cada gota coletada emite um som, e uma música de fundo toca em loop durante o jogo.

---



---

## 🚀 Tecnologias Utilizadas

- ☕ **Java 8+**
- 🎮 **LibGDX Framework**
- 🧩 **IntelliJ IDEA**
- 🎵 Áudio e sprites 2D simples

---

## 📂 Estrutura do Projeto

```text
senai.projeto.vitorhott/
│
├── Main.java               # Classe principal do jogo
├── assets/
│   ├── background.png       # Imagem de fundo
│   ├── bucket.png           # Imagem do balde
│   ├── drop.png             # Imagem da gota
│   ├── drop.mp3             # Som da gota sendo coletada
│   └── music.mp3            # Música de fundo
└── README.md
 ```

## ⚙️ Como Executar o Projeto
✅ Pré-requisitos

Antes de rodar o jogo, instale:

Java JDK 8+

IntelliJ IDEA

LibGDX Setup

▶️ Execução

Clone este repositório:

git clone https://github.com/seuusuario/seurepositorio.git


Abra o projeto no IntelliJ IDEA.

Verifique se o LibGDX está configurado corretamente.

Execute a classe Main.java.

Divirta-se coletando gotas! 💧

##🎮 Controles
Ação	Tecla / Comando
Mover para esquerda	← (seta esquerda)
Mover para direita	→ (seta direita)
Movimento via toque/mouse	Clique ou toque na tela

##🧠 Lógica do Jogo

As gotas são criadas a cada 1 segundo.

O balde se move horizontalmente para capturar as gotas.

Quando ocorre colisão entre o balde e uma gota:

O som drop.mp3 é reproduzido.

A gota é removida da tela.

A música de fundo toca em loop durante toda a execução do jogo.

##🧩 Recursos Técnicos

Renderização com SpriteBatch

Sistema de visualização com FitViewport

Detecção de colisão via Rectangle.overlaps()

Movimentação baseada em deltaTime

Áudio: Gdx.audio.newSound() e Gdx.audio.newMusic()

##💡 Melhorias Futuras

 Adicionar pontuação e contador de gotas coletadas

 Criar tela de início e tela de game over

 Implementar níveis de dificuldade

 Adicionar sprites animados

 Inserir efeitos sonoros adicionais

 Melhorar responsividade e proporção da tela

##👨‍💻 Autor

Desenvolvido por Vitor Hott

💬 Projeto criado para estudos e prática de Java + LibGDX no IntelliJ IDEA.

##🧾 Licença

Este projeto é de uso educacional e livre.
Você pode modificar e compartilhar o código livremente, desde que mantenha os créditos.
