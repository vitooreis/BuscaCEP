package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

public class Cep extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox<String> cboUf;
	private JLabel lblStatus;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Cep() {
		setTitle("Buscar CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setBounds(23, 36, 46, 14);
		contentPane.add(lblNewLabel);

		txtCep = new JTextField();
		txtCep.setBounds(56, 33, 104, 20);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setBounds(10, 74, 59, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Bairro");
		lblNewLabel_2.setBounds(23, 114, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Cidade");
		lblNewLabel_3.setBounds(23, 153, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(79, 71, 310, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		txtBairro = new JTextField();
		txtBairro.setBounds(79, 111, 310, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(79, 150, 203, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("UF");
		lblNewLabel_4.setBounds(302, 153, 46, 14);
		contentPane.add(lblNewLabel_4);

		cboUf = new JComboBox<String>();
		cboUf.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(332, 149, 57, 22);
		contentPane.add(cboUf);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(23, 214, 89, 23);
		contentPane.add(btnLimpar);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCep.requestFocus();
				}else {
					buscarCep();
				}
			}

		});
		btnCep.setBounds(202, 32, 89, 23);
		contentPane.add(btnCep);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/about.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBounds(355, 12, 48, 48);
		contentPane.add(btnSobre);

		// Uso da biblioteca
		RestrictedTextField validar = new RestrictedTextField(txtCep);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(160, 33, 20, 20);
		contentPane.add(lblStatus);
		validar.setOnlyNums(true);
		validar.setLimit(8);
	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			@SuppressWarnings("deprecation")
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml\r\n" + "");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element>it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			//Setar o campo endereco
			txtEndereco.setText(tipoLogradouro + " " + logradouro); 
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void limpar() {
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.requestFocus();
		lblStatus.setIcon(null);
		
	}	
		
}
