package cep;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Buscar CEP - Versão 1.0");
		lblNewLabel.setBounds(31, 39, 303, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Author: Vitor Reis");
		lblNewLabel_1.setBounds(31, 85, 224, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnGitHub = new JButton("");
		btnGitHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://github.com/vitooreis");
			}
		});
		btnGitHub.setToolTipText("Projeto");
		btnGitHub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
		btnGitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGitHub.setBorder(null);
		btnGitHub.setBackground(SystemColor.control);
		btnGitHub.setBounds(31, 169, 48, 48);
		getContentPane().add(btnGitHub);
		
		JButton btnLinkedin = new JButton("");
		btnLinkedin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://www.linkedin.com/in/vitorreiss/");
			}
		});
		btnLinkedin.setIcon(new ImageIcon(Sobre.class.getResource("/img/linkedin.png")));
		btnLinkedin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLinkedin.setBorder(null);
		btnLinkedin.setToolTipText("LinkedIn");
		btnLinkedin.setBackground(SystemColor.control);
		btnLinkedin.setBounds(104, 169, 48, 48);
		getContentPane().add(btnLinkedin);

	}
	
	private void link(String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
