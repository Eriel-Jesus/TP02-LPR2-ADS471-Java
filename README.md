# TP02 - LPR2 - ADS471 - Java

Este projeto é uma aplicação Java Swing para cadastrar alunos em memória. A interface permite informar nome, idade e endereço, salvar o cadastro e visualizar os alunos já registrados.

## Estrutura dos arquivos

### 1. `Aluno.java`
Este arquivo define a classe `Aluno`, que representa o modelo de dados do sistema.

A classe possui:
- `UUID uuid`: identificador único gerado automaticamente para cada aluno.
- `String nome`: nome do aluno.
- `int idade`: idade do aluno.
- `String endereco`: endereço do aluno.

Também contém os métodos de acesso (`get` e `set`) para cada atributo. A classe possui dois construtores:
- `Aluno()`: cria um objeto vazio e gera um UUID automaticamente.
- `Aluno(String nome, int idade, String endereco)`: cria um aluno já preenchido com os dados informados.

Esse arquivo é o "modelo" do sistema: ele guarda as informações do aluno e serve como estrutura de dados principal.

### 2. `FormularioAluno.java`
Este arquivo é a interface gráfica da aplicação. Ele cria a janela principal da tela usando Swing e controla os eventos dos botões.

#### Componentes da interface
A janela contém:
- `txtNome`: campo para o nome.
- `txtIdade`: campo para a idade.
- `txtEndereco`: campo para o endereço.
- `btnOk`: grava o aluno cadastrado.
- `btnLimpar`: limpa os campos de entrada.
- `btnMostrar`: mostra os alunos cadastrados.
- `btnSair`: encerra a aplicação.

#### Como a janela é montada
No construtor da classe `FormularioAluno`, são criados:
- um painel superior com `GridLayout(3, 2)` para os campos de nome, idade e endereço;
- um painel inferior com `GridLayout(1, 4)` para os botões;
- a janela principal `JFrame` com título "TP02 - LPR2";
- o layout `BorderLayout` para organizar os painéis.

#### Lista de alunos em memória
A classe mantém uma lista chamada `listaAlunos`:

```java
private List<Aluno> listaAlunos;
```

Essa lista é inicializada com `new ArrayList<>()` e armazena os objetos `Aluno` enquanto o programa estiver em execução. Ou seja, os dados ficam salvos somente na memória da aplicação e não são gravados em arquivo ou banco de dados.

#### Fluxo de funcionamento

##### Botão `Ok`
Quando o usuário clica no botão `Ok`, o programa:
1. lê os textos digitados nos campos;
2. valida se nome, idade e endereço não estão vazios;
3. tenta converter a idade para `int`;
4. verifica se a idade é maior que zero;
5. cria um novo objeto `Aluno`; e
6. adiciona esse aluno na lista `listaAlunos`.

Se algum campo estiver vazio ou a idade for inválida, aparece uma mensagem de alerta com `JOptionPane`.

##### Botão `Limpar`
Esse botão chama o método `limparCampos()`, que apaga o texto dos campos e devolve o foco para o campo de nome.

##### Botão `Mostrar`
Esse botão verifica se a lista está vazia. Se estiver vazia, mostra uma mensagem informando que não há alunos cadastrados.

Se houver alunos, ele percorre a lista e monta uma mensagem com o identificador UUID e o nome de cada estudante, exibindo tudo em um popup.

##### Botão `Sair`
Esse botão encerra a aplicação com `System.exit(0)`.

#### Método principal
O método `main` inicia a interface gráfica da seguinte forma:

```java
SwingUtilities.invokeLater(new Runnable() {
    @Override
    public void run() {
        new FormularioAluno().setVisible(true);
    }
});
```

Ele é importante porque executa a criação da janela na thread correta do Swing, garantindo que a interface funcione de modo seguro e responsivo.

## Resumo do funcionamento geral
O programa funciona como um cadastro simples de alunos:
- o usuário preenche os campos da tela;
- o sistema valida os dados;
- cria um objeto `Aluno`;
- guarda esse aluno em uma lista em memória;
- permite limpar os campos ou exibir a lista de alunos cadastrados;
- e fecha a aplicação quando solicitado.

## Observação importante
Este projeto não salva os alunos em disco. Todos os registros são mantidos apenas enquanto a aplicação está aberta. Se o programa for fechado, a lista é perdida.

## Como executar
Para executar a aplicação, compile e rode a classe `FormularioAluno`:

```bash
javac FormularioAluno.java Aluno.java
java FormularioAluno
```

Se estiver usando uma IDE, basta abrir o projeto e rodar a classe `FormularioAluno` como aplicação Java.
