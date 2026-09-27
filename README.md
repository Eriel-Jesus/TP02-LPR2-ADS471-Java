# TP02 - LPR2 - ADS471 - Java

Este projeto consiste em uma aplicação Java baseada em Swing para o cadastro de alunos em memória. A solução foi desenvolvida com foco na organização de dados em uma classe modelo e na criação de uma interface gráfica responsável pela coleta, validação e exibição das informações.

## Objetivo

O sistema tem como finalidade permitir que o usuário informe os dados de um aluno, valide essas informações e armazene os registros temporariamente durante a execução da aplicação. O projeto ilustra, de forma prática, o uso de classes, objetos, listas, eventos de interface gráfica e manipulação de dados em Java.

## Estrutura do projeto

O repositório contém dois arquivos principais:

- `Aluno.java`: representa o modelo de dados do aluno.
- `FormularioAluno.java`: implementa a interface gráfica e os mecanismos de interação com o usuário.

## Arquivo `Aluno.java`

A classe `Aluno` define a estrutura de dados utilizada pelo sistema. Cada instância representa um aluno com os seguintes atributos:

- `UUID uuid`: identificador único gerado automaticamente para cada objeto.
- `String nome`: nome do aluno.
- `int idade`: idade do aluno.
- `String endereco`: endereço residencial do aluno.

Além dos atributos, a classe oferece métodos de acesso (`get` e `set`) para cada campo, permitindo a leitura e atualização dos dados de forma encapsulada. Isso mantém a integridade dos valores e facilita a manipulação dos objetos em outras partes do programa.

A classe possui dois construtores:

- `Aluno()`: cria uma instância vazia e gera um UUID automaticamente.
- `Aluno(String nome, int idade, String endereco)`: cria um objeto já inicializado com os dados do aluno.

Em termos de arquitetura, `Aluno.java` funciona como o modelo do sistema, ou seja, a representação dos dados que serão armazenados e exibidos pela interface.

## Arquivo `FormularioAluno.java`

A classe `FormularioAluno` estende `JFrame`, sendo responsável pela criação da janela principal da aplicação. Ela organiza os componentes gráficos e trata os eventos gerados pelo usuário, como cadastro, limpeza, visualização e encerramento do programa.

### Componentes da interface

A tela contém os seguintes elementos:

- `txtNome`: campo para inserção do nome.
- `txtIdade`: campo para inserção da idade.
- `txtEndereco`: campo para inserção do endereço.
- `btnOk`: botão para confirmar o cadastro do aluno.
- `btnLimpar`: botão para limpar os campos digitados.
- `btnMostrar`: botão para exibir os alunos cadastrados.
- `btnSair`: botão para encerrar a aplicação.

### Organização visual da janela

A interface é montada em um `BorderLayout`, com:

- um painel superior em `GridLayout(3, 2)`, contendo os campos de entrada;
- um painel inferior em `GridLayout(1, 4)`, contendo os botões de ação.

A janela recebe o título `TP02 - LPR2` e é centralizada na tela com `setLocationRelativeTo(null)`.

### Estrutura de armazenamento

A classe mantém uma lista de objetos `Aluno` em memória:

```java
private List<Aluno> listaAlunos;
```

Essa lista é inicializada com `new ArrayList<>()` e armazena os alunos cadastrados enquanto a aplicação permanece em execução. Importante destacar que os dados não são gravados em arquivo, banco de dados ou persistência externa; eles existam apenas na memória da aplicação.

## Funcionamento dos botões

### Botão `Ok`
Ao clicar em `Ok`, o sistema realiza as seguintes ações:

1. lê os valores digitados nos campos;
2. verifica se os campos obrigatórios foram preenchidos;
3. converte a idade para `int`;
4. valida se a idade é maior que zero;
5. cria um novo objeto `Aluno`;
6. adiciona esse aluno à lista `listaAlunos`.

Se algum dado for inválido, é exibida uma mensagem de alerta com `JOptionPane`, informando o usuário do problema.

### Botão `Limpar`
Esse botão chama o método `limparCampos()`, que apaga o conteúdo dos campos e retorna o foco para o campo de nome.

### Botão `Mostrar`
Ao acionar esse botão, o sistema verifica se a lista de alunos está vazia. Caso esteja, exibe uma mensagem informando que nenhum aluno foi cadastrado. Caso contrário, percorre a lista e apresenta, em uma caixa de diálogo, o identificador UUID e o nome de cada aluno cadastrado.

### Botão `Sair`
Esse botão encerra a execução do programa por meio de `System.exit(0)`.

## Método principal

A aplicação é iniciada pelo método `main`, que executa a criação da interface em uma thread correta para Swing:

```java
SwingUtilities.invokeLater(new Runnable() {
    @Override
    public void run() {
        new FormularioAluno().setVisible(true);
    }
});
```

Esse mecanismo é necessário para garantir uma execução adequada da interface gráfica e evitar problemas de atualização da UI em Java Swing.

## Considerações finais

O projeto demonstra, de maneira simples e didática, a integração entre:

- classes de modelo;
- interface gráfica com Swing;
- validação de entrada;
- uso de coleções para armazenamento temporário;
- tratamento de eventos e interação com o usuário.

Seu funcionamento principal consiste em receber informações do aluno, validá-las, armazená-las em memória e apresentá-las ao usuário conforme solicitado.

## Execução

Para executar o programa, é necessário compilar os arquivos Java e rodar a classe principal `FormularioAluno`:

```bash
javac FormularioAluno.java Aluno.java
java FormularioAluno
```

Em uma IDE Java, basta abrir o projeto e executar a classe `FormularioAluno` como aplicação.
