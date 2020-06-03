package app.view;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import app.model.Expression;

import java.awt.Font;

public class Caculator extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel textPanel;
	private JTextPane expression;
	private JTextPane result;
	private boolean typing = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Caculator frame = new Caculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Caculator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 285, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		textPanel = new JPanel();
		contentPane.add(textPanel);
		textPanel.setLayout(new GridLayout(0, 1, 0, 0));

		expression = new JTextPane();
		expression.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		expression.setEditable(false);
		textPanel.add(expression);

		result = new JTextPane();
		result.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		result.setEditable(false);
		textPanel.add(result);

		JPanel keyBoard = new JPanel();
		contentPane.add(keyBoard);
		keyBoard.setLayout(new GridLayout(5, 4, 0, 0));

		prepareKeyBoard(keyBoard);
	}

	private void prepareKeyBoard(JPanel keyBoard) {
		keyBoard.add(new Action("C"));
		keyBoard.add(new Action("Del"));
		keyBoard.add(new Operator("%"));
		keyBoard.add(new Operator("/"));
		for (int i = 7; i <= 9; i++) {
			keyBoard.add(new Number(i));
		}
		keyBoard.add(new Operator("*"));
		for (int i = 4; i <= 6; i++) {
			keyBoard.add(new Number(i));
		}
		keyBoard.add(new Operator("-"));
		for (int i = 1; i <= 3; i++) {
			keyBoard.add(new Number(i));
		}
		keyBoard.add(new Operator("+"));
		keyBoard.add(new Action("@@"));
		keyBoard.add(new Number(0));
		keyBoard.add(new Operator(","));
		keyBoard.add(new Action("="));
	}

	public class Number extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public Number(int number) {
			this.setText(String.valueOf(number));
			this.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(typing == false) {
				expression.setText("");
				typing = true;
			}
			String currentEx = expression.getText();
			if (currentEx.isEmpty()) {
				expression.setText(expression.getText() + ((Number) e.getSource()).getText());
			} else {
				String lastToken = String.valueOf(currentEx.charAt(currentEx.length() - 1));
				if (lastToken.matches("\\+|\\-|\\/|\\*")) {
					expression.setText(expression.getText() + " " + ((Number) e.getSource()).getText());
				} else {
					expression.setText(expression.getText() + ((Number) e.getSource()).getText());
				}
			}
		}
	}

	public class Operator extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public Operator(String op) {
			this.setText(op);
			this.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(typing == false) {
				expression.setText("");
				typing = true;
			}
			String currentEx = expression.getText();
			if (currentEx.isEmpty()) {
				expression.setText(expression.getText() + ((Operator) e.getSource()).getText());
			} else {
				String lastToken = String.valueOf(currentEx.charAt(currentEx.length() - 1));
				System.out.println("Last: " + lastToken);
				if (lastToken.matches("\\d+")) {
					expression.setText(expression.getText() + " " + ((Operator) e.getSource()).getText());
				} else {
				}
			}
		}

	}

	public class Action extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public Action(String action) {
			this.setText(action);
			this.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Action action = (Action) e.getSource();
			if(action.getText().equals("=")) {
				if(expression.getText().substring(0, 1).matches("\\+|\\-|\\*|\\/|\\%")) {
					Expression ex = new Expression(result.getText() + expression.getText());
					result.setText(ex.caculate(ex.buildTree()) + "");
					typing = false;
				}
				Expression ex = new Expression(expression.getText());
				result.setText(ex.caculate(ex.buildTree()) + "");
				typing = false;
			}
			if(action.getText().equals("C")) {
				expression.setText("");
			}
			if(action.getText().equals("Del")) {
				String currentEx = expression.getText();
				expression.setText(currentEx.substring(0, currentEx.length() - 2).trim());
			}
		}
	}

}
