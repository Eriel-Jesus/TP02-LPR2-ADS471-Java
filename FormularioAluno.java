import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormularioAluno extends JFrame {

    // Componentes da interface
    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextField txtEndereco;
    private JButton btnOk;
    private JButton btnLimpar;
    private JButton btnMostrar;
    private JButton btnSair;

    // Lista para armazenar os alunos em memória
    private List<Aluno> listaAlunos;

    public FormularioAluno() {
        super("TP02 - LPR2");
        listaAlunos = new ArrayList<>();

        // Configurações da janela
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(new BorderLayout(5, 5)); 

        // --- PAINEL SUPERIOR (Campos de Entrada) ---
        // GridLayout (3 linhas, 2 colunas) com hgap 10 e vgap 10
        JPanel painelSuperior = new JPanel(new GridLayout(3, 2, 10, 10));

        painelSuperior.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelSuperior.add(txtNome);

        painelSuperior.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelSuperior.add(txtIdade);

        painelSuperior.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painelSuperior.add(txtEndereco);

        // --- PAINEL INFERIOR (Botões) ---
        // GridLayout (1 linha, 4 colunas)
        JPanel painelInferior = new JPanel(new GridLayout(1, 4, 5, 5));

        btnOk = new JButton("Ok");
        btnLimpar = new JButton("Limpar");
        btnMostrar = new JButton("Mostrar");
        btnSair = new JButton("Sair");

        painelInferior.add(btnOk);
        painelInferior.add(btnLimpar);
        painelInferior.add(btnMostrar);
        painelInferior.add(btnSair);

        // Adiciona os dois painéis à janela
        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        // --- TRATAMENTO DOS EVENTOS DOS BOTÕES ---

        btnOk.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = txtNome.getText().trim();
        String endereco = txtEndereco.getText().trim();
        String idadeStr = txtIdade.getText().trim();
        // Validar se Nome ou Endereço estão vazios
        if (nome.isEmpty() || endereco.isEmpty() || idadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(FormularioAluno.this, 
                "Todos os campos (Nome, Idade e Endereço) devem ser preenchidos!", 
                "Campo Obrigatório", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try{
            int idade = Integer.parseInt(idadeStr);
            if (idade <= 0)
            {
               JOptionPane.showMessageDialog(FormularioAluno.this, 
                    "A idade deve ser um valor positivo e maior que zero!", 
                    "Idade Inválida", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            Aluno aluno = new Aluno(nome, idade, endereco);
            listaAlunos.add(aluno);

            JOptionPane.showMessageDialog(FormularioAluno.this, 
                "Aluno cadastrado com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(FormularioAluno.this, 
                "Por favor, insira um número válido para a idade.", 
                "Erro de Validação", 
                JOptionPane.ERROR_MESSAGE);
            
        }
    }
});

        // ii. Botão Limpar: Apaga o conteúdo dos campos de texto
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        // iii. Botão Mostrar: Exibe a lista formatada em pop-up
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaAlunos.isEmpty()) {
                    JOptionPane.showMessageDialog(FormularioAluno.this, 
                        "Nenhum aluno cadastrado.", 
                        "Aviso", 
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                StringBuilder mensagem = new StringBuilder("Resultado\n");
                for (Aluno a : listaAlunos) {
                    mensagem.append("Id: ").append(a.getUuid())
                            .append("  Nome: ").append(a.getNome())
                            .append("\n");
                }

                JOptionPane.showMessageDialog(FormularioAluno.this, mensagem.toString());
            }
        });

        // iv. Botão Sair: Encerra a aplicação
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtEndereco.setText("");
        txtNome.requestFocus(); // Retorna o foco para o primeiro campo
    }

    public static void main(String[] args) {
        // Executa a interface gráfica na Thread de Eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioAluno().setVisible(true);
            }
        });
    }
}