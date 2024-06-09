package monitoramentoph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MonitoramentoPh extends JFrame {
    private JTextField phTextField;
    private JTextArea resultTextArea;
    private JLabel exampleImageLabel;
    private JTextArea exampleNameTextArea;
    private JComboBox<String> zonaComboBox;
    private JButton submitButton;
    private JButton openSiteButton;
    private JButton helpButton;
    private JLabel phScaleLabel;
    private boolean siteOpened = false; // Variável de controle para verificar se o site já foi aberto

    public MonitoramentoPh() {
        setTitle("Monitoramento de Ph");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de entrada do pH
        JPanel phPanel = new JPanel();
        phPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Botão de ajuda
        helpButton = new JButton("Ajuda");
        phPanel.add(helpButton);

        // Espaço entre o botão de ajuda e os três elementos
        phPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Texto do pH
        JLabel phLabel = new JLabel("Informe o valor de pH:");
        phLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Definindo a fonte e o tamanho
        phPanel.add(phLabel);

        phTextField = new JTextField(10);
        phPanel.add(phTextField);

        // Painel para o botão "Confirmar"
        JPanel confirmButtonPanel = new JPanel();
        confirmButtonPanel.setLayout(new BoxLayout(confirmButtonPanel, BoxLayout.X_AXIS)); // BoxLayout com alinhamento para a direita
        confirmButtonPanel.add(Box.createHorizontalGlue()); // Espaço à esquerda para empurrar o botão para a direita
        submitButton = new JButton("Confirmar");
        confirmButtonPanel.add(submitButton);
        phPanel.add(confirmButtonPanel); // Adicionando o painel do botão ao painel principal

        // Painel para a área de resultado e exemplo
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // Área de resultado com caixa colorida
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(Color.LIGHT_GRAY); // Cor de fundo contrastante
        JLabel resultLabel = new JLabel("Resultado:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultPanel.add(resultLabel, BorderLayout.NORTH);

        resultTextArea = new JTextArea(4, 32);
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Alterado de 0.5 para 1.0
        textAreaPanel.add(resultPanel, gbc);

        // Área de exemplo com título em caixa colorida
        JPanel examplePanel = new JPanel(new BorderLayout());
        JLabel exampleLabel = new JLabel("Exemplo:");
        exampleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        exampleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel exampleTitlePanel = new JPanel(new BorderLayout());
        exampleTitlePanel.setBackground(Color.LIGHT_GRAY); // Cor de fundo contrastante apenas para o título
        exampleTitlePanel.add(exampleLabel, BorderLayout.CENTER);
        examplePanel.add(exampleTitlePanel, BorderLayout.NORTH);

        // Área de texto para o nome do exemplo
        exampleNameTextArea = new JTextArea();
        exampleNameTextArea.setEditable(false);
        exampleNameTextArea.setFont(new Font("Arial", Font.BOLD, 15));
        exampleNameTextArea.setBackground(Color.LIGHT_GRAY); // Cor de fundo contrastante
        exampleNameTextArea.setWrapStyleWord(true);
        exampleNameTextArea.setLineWrap(true);
        examplePanel.add(exampleNameTextArea, BorderLayout.NORTH);

        // Campo de imagem para o exemplo
        URL exampleImageUrl = getClass().getResource("/resources/ph-grafico.png");
        ImageIcon exampleImageIcon = new ImageIcon(exampleImageUrl);
        Image exampleImage = exampleImageIcon.getImage();
        Image scaledExampleImage = exampleImage.getScaledInstance(220, 200, Image.SCALE_SMOOTH); // Redimensiona a imagem
        exampleImageIcon = new ImageIcon(scaledExampleImage);
        exampleImageLabel = new JLabel(exampleImageIcon);
        exampleImageLabel.setHorizontalAlignment(JLabel.CENTER);
        examplePanel.add(exampleImageLabel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Alterado de 2.5 para 1.0
        textAreaPanel.add(examplePanel, gbc);

        // Painel de seleção de zona
        JPanel zonaPanel = new JPanel();
        zonaPanel.setLayout(new BorderLayout());

        JPanel zonaSubPanel = new JPanel();
        zonaSubPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        zonaSubPanel.add(new JLabel("Selecione sua zona:"));
        zonaComboBox = new JComboBox<>(new String[]{
                "Norte", "Leste", "Oeste", "Centro Oeste", "Centro Sul"
        });
        zonaSubPanel.add(zonaComboBox);

        zonaPanel.add(zonaSubPanel, BorderLayout.WEST);

        // Painel para o botão "Mais informações"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Botão para abrir o site
        openSiteButton = new JButton("Mais informações"); // Alterando o texto do botão
        buttonPanel.add(openSiteButton);

        zonaPanel.add(buttonPanel, BorderLayout.EAST);

        // Campo de imagem para a escala de pH
        URL imageUrl;
        //imageUrl = getClass().getResource("/resources/escala-ph-cortada.jpeg");
        imageUrl = getClass().getResource("/resources/ind.png");
        ImageIcon phScaleImageIcon = new ImageIcon(imageUrl);
        Image phScaleImage = phScaleImageIcon.getImage();
        Image scaledPhScaleImage = phScaleImage.getScaledInstance(250, 400, Image.SCALE_SMOOTH);
        phScaleImageIcon = new ImageIcon(scaledPhScaleImage);
        phScaleLabel = new JLabel(phScaleImageIcon);
        phScaleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Adiciona os painéis à janela principal
        add(phPanel, BorderLayout.NORTH);
        add(phScaleLabel, BorderLayout.CENTER);
        add(textAreaPanel, BorderLayout.EAST);
        add(zonaPanel, BorderLayout.SOUTH);

        // Adiciona os listeners aos botões
        submitButton.addActionListener(new SubmitButtonListener());
        openSiteButton.addActionListener(new OpenSiteButtonListener());
        helpButton.addActionListener(new HelpButtonListener());
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double ph = Double.parseDouble(phTextField.getText());
                if (ph < 0 || ph > 14) {
                    resultTextArea.setText("Ph fora do intervalo. Tente novamente.");
                } else {
                    Solo solo;
                    if (ph < 7) {
                        resultTextArea.setText("Ph ácido\n");
                        solo = new Acidez(ph);
                    } else if (ph >= 7 && ph < 8) {
                        solo = new Solo(ph) {
                            public String classificar() {
                                resultTextArea.setText("\"pH Fraco/Neutro\"\n");
                                return "Ideal";
                            }
                        };
                    } else {
                        resultTextArea.setText("Ph alcalino\n");
                        solo = new Alcalinidade(ph);
                    }
                   
                    resultTextArea.append("\nClassificação do ph: " + solo.classificar());
                    updateExampleImage(ph); // Atualiza a imagem de exemplo com base no valor do pH
                   
                }
            } catch (NumberFormatException ex) {
                resultTextArea.setText("Entrada inválida. Por favor, digite um número.");
            }
        }

         private void updateExampleImage(double ph) {
            String imageName = "";
            String exampleName = "";

            if (ph >= 0 && ph < 1) {
                imageName = "/resources/acido-bateria01.jpg";
                exampleName = "Ácido de Bateria";
            } else if (ph >= 1 && ph < 2) {
                imageName = "/resources/acido-estomacal1.jpg";
                exampleName = "Ácido Estomacal";
            } else if (ph >= 2 && ph < 3) {
                imageName = "/resources/vinagre2.jpg";
                exampleName = "Vinagre";
            } else if (ph >= 3 && ph < 4) {
                imageName = "/resources/suco-laranja3.jpg";
                exampleName = "Suco de Laranja";
            } else if (ph >= 4 && ph < 5) {
                imageName = "/resources/tomate4.png";
                exampleName = "Tomate";
            } else if (ph >= 5 && ph < 6) {
                imageName = "/resources/cafe5.jpg";
                exampleName = "Café";
            } else if (ph >= 6 && ph < 7) {
                imageName = "/resources/leite6.jpg";
                exampleName = "Leite";
            } else if (ph >= 7 && ph < 8) {
                imageName = "/resources/copo-agua7.jpeg";
                exampleName = "Água";
            } else if (ph >= 8 && ph < 9) {
                imageName = "/resources/agua-mar8.png";
                exampleName = "Água do Mar";
            } else if (ph >= 9 && ph < 10) {
                imageName = "/resources/sodio9.jpg";
                exampleName = "Bicarbonato de Sódio";
            } else if (ph >= 10 && ph < 11) {
                imageName = "/resources/magnesia10.jpg";
                exampleName = "Magnésia";
            } else if (ph >= 11 && ph < 12) {
                imageName = "/resources/solucao-amonia11.png";
                exampleName = "Amônia";
            } else if (ph >= 12 && ph < 13) {
                imageName = "/resources/agua-sabao12.jpg";
                exampleName = "Água e Sabão";
            } else if (ph >= 13 && ph < 14) {
                imageName = "/resources/agua-sanitaria13.jpg";
                exampleName = "Água Sanitária";
            }
            else if (ph == 14) {
                imageName = "/resources/limpa-ralo14.jpg";
                exampleName = "Limpador de Ralo";
            }

            URL imageUrl = getClass().getResource(imageName);
            if (imageUrl != null) {
                ImageIcon exampleImageIcon = new ImageIcon(imageUrl);
                Image exampleImage = exampleImageIcon.getImage();
                Image scaledExampleImage = exampleImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                exampleImageIcon = new ImageIcon(scaledExampleImage);
                exampleImageLabel.setIcon(exampleImageIcon);
                exampleNameTextArea.setText(exampleName);
            } else {
                exampleNameTextArea.setText("Imagem não encontrada.");
            }
        }
    }
    
    private class OpenSiteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abrir o documento HTML
            try {
                String zonaSelecionada = (String) zonaComboBox.getSelectedItem();
                String htmlFileName = "";

                switch (zonaSelecionada) {
                    case "Norte":
                        htmlFileName = "dicas-zn.html";
                        break;
                    case "Leste":
                        htmlFileName = "dicas-zl.html";
                        break;
                    case "Oeste":
                        htmlFileName = "dicas-zoeste.html";
                        break;
                    case "Centro Oeste":
                        htmlFileName = "dicas-zcentro-oeste.html";
                        break;
                    case "Centro Sul":
                        htmlFileName = "dicas-zcentrosul.html";
                        break;
                }

                URL htmlURL = getClass().getResource("/resources/" + htmlFileName);
                if (htmlURL != null) {
                    Desktop.getDesktop().browse(htmlURL.toURI());
                } else {
                    resultTextArea.setText("Arquivo HTML não encontrado para a zona selecionada.");
                }
            } catch (Exception ex) {
                resultTextArea.setText("Erro ao abrir o documento HTML.");
            }
        }
    }

    private class HelpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "1° Informe o nível de pH.\n\n2° Selecione sua zona, pois iremos listar pontos de coleta de lixo conforme sua zona.\n\n3° Depois de preencher e selecionar, clique em 'Confirmar'.\n\n4° Clique em 'Mais informações' para dicas e ver os pontos de coleta.\n\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MonitoramentoPh frame = new MonitoramentoPh();
            frame.setVisible(true);
            JOptionPane.showMessageDialog(null, "1° Informe o nível de pH.\n\n2° Selecione sua zona, pois iremos listar pontos de coleta de lixo conforme sua zona.\n\n3° Depois de preencher e selecionar, clique em 'Confirmar'.\n\n4° Clique em 'Mais informações' para dicas e ver os pontos de coleta.\n\n");
        });
    }
}
