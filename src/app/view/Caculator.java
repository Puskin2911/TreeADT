package app.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.model.Expression;

public class Caculator extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField expressionField;
	private JButton del_btn;
	private JButton bracket_btn;
	private JButton devide_btn;
	private JButton num7_btn;
	private JButton num8_btn;
	private JButton num9_btn;
	private JButton mul_btn;
	private JButton num4_btn;
	private JButton num5_btn;
	private JButton num6_btn;
	private JButton sub_btn;
	private JButton num1_btn;
	private JButton num2_btn;
	private JButton num3_btn;
	private JButton add_btn;
	private JButton ans_btn;
	private JButton num0_btn;
	private JButton floatingpoint_btn;
	private JButton caculate_btn;
	private JTextField result;
	private boolean typing = true;
	private boolean subWaiting = false;
	private Expression ex;

	private ArrayList<Button> buttons;

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
		setResizable(false);
		setTitle("Mini Caculator by Thanh Cute");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 120, 403, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		expressionField = new JTextField();
		expressionField.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		expressionField.setEditable(false);
		expressionField.setBounds(51, 28, 290, 45);
		contentPane.add(expressionField);
		expressionField.setColumns(10);

		JButton ac_btn = new Action("AC");
		ac_btn.setBackground(Color.WHITE);
		ac_btn.setBounds(51, 148, 65, 50);
		contentPane.add(ac_btn);

		del_btn = new Action("Del");
		del_btn.setBackground(Color.GREEN);
		del_btn.setBounds(126, 148, 65, 50);
		contentPane.add(del_btn);

		bracket_btn = new Operator("( )");
		bracket_btn.setEnabled(false);
		bracket_btn.setBackground(Color.LIGHT_GRAY);
		bracket_btn.setBounds(201, 148, 65, 50);
		contentPane.add(bracket_btn);

		devide_btn = new Operator("/");
		devide_btn.setEnabled(false);
		devide_btn.setBounds(276, 148, 65, 50);
		contentPane.add(devide_btn);

		num7_btn = new Number("7");
		num7_btn.setBounds(51, 209, 65, 50);
		contentPane.add(num7_btn);

		num8_btn = new Number("8");
		num8_btn.setBounds(126, 209, 65, 50);
		contentPane.add(num8_btn);

		num9_btn = new Number("9");
		num9_btn.setBounds(201, 209, 65, 50);
		contentPane.add(num9_btn);

		mul_btn = new Operator("x");
		mul_btn.setBounds(276, 209, 65, 50);
		contentPane.add(mul_btn);

		num4_btn = new Number("4");
		num4_btn.setBounds(51, 270, 65, 50);
		contentPane.add(num4_btn);

		num5_btn = new Number("5");
		num5_btn.setBounds(126, 270, 65, 50);
		contentPane.add(num5_btn);

		num6_btn = new Number("6");
		num6_btn.setBounds(201, 270, 65, 50);
		contentPane.add(num6_btn);

		sub_btn = new Operator("-");
		sub_btn.setBounds(276, 270, 65, 50);
		contentPane.add(sub_btn);

		num1_btn = new Number("1");
		num1_btn.setBounds(51, 330, 65, 50);
		contentPane.add(num1_btn);

		num2_btn = new Number("2");
		num2_btn.setBounds(126, 330, 65, 50);
		contentPane.add(num2_btn);

		num3_btn = new Number("3");
		num3_btn.setBounds(201, 330, 65, 50);
		contentPane.add(num3_btn);

		add_btn = new Operator("+");
		add_btn.setBounds(276, 330, 65, 50);
		contentPane.add(add_btn);

		ans_btn = new Action("Ans");
		ans_btn.setBounds(51, 391, 65, 50);
		contentPane.add(ans_btn);

		num0_btn = new Number("0");
		num0_btn.setBounds(126, 391, 65, 50);
		contentPane.add(num0_btn);

		floatingpoint_btn = new Operator(",");
		floatingpoint_btn.setBounds(201, 391, 65, 50);
		contentPane.add(floatingpoint_btn);

		caculate_btn = new Action("==");
		caculate_btn.setBounds(276, 391, 65, 50);
		contentPane.add(caculate_btn);

		result = new JTextField();
		result.setHorizontalAlignment(SwingConstants.TRAILING);
		result.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		result.setEditable(false);
		result.setColumns(10);
		result.setBounds(138, 85, 203, 45);
		contentPane.add(result);

		buttons = new ArrayList<Caculator.Button>();
		buttons.add((Button) ac_btn);
		buttons.add((Button) del_btn);
		buttons.add((Button) bracket_btn);
		buttons.add((Button) devide_btn);
		buttons.add((Button) num0_btn);
		buttons.add((Button) num1_btn);
		buttons.add((Button) num2_btn);
		buttons.add((Button) num3_btn);
		buttons.add((Button) num4_btn);
		buttons.add((Button) num5_btn);
		buttons.add((Button) num6_btn);
		buttons.add((Button) num7_btn);
		buttons.add((Button) num8_btn);
		buttons.add((Button) num9_btn);
		buttons.add((Button) mul_btn);
		buttons.add((Button) sub_btn);
		buttons.add((Button) add_btn);
		buttons.add((Button) floatingpoint_btn);
		buttons.add((Button) caculate_btn);
		buttons.add((Button) ans_btn);

		JRadioButton onRbtn = new JRadioButton("ON");
		onRbtn.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		onRbtn.setBounds(71, 84, 55, 23);
		contentPane.add(onRbtn);

		JRadioButton offRbtn = new JRadioButton("OFF");
		offRbtn.setSelected(true);
		offRbtn.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		offRbtn.setBounds(71, 110, 55, 23);
		contentPane.add(offRbtn);

		onRbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				offRbtn.setSelected(false);
				result.setText("0.0");

				for (Button button : buttons) {
					button.setEnabled(true);
				}
			}
		});

		offRbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onRbtn.setSelected(false);
				expressionField.setText(null);
				result.setText(null);

				for (Button button : buttons) {
					button.setEnabled(false);
				}
			}
		});
	}

	public abstract class Button extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public Button(String name) {
			this.setText(name);
			this.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			this.setEnabled(false);
		}

	}

	public class Number extends Button {
		private static final long serialVersionUID = 1L;

		public Number(String name) {
			super(name);
			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (typing == false) {
				expressionField.setText("");
				typing = true;
			}
			String currentEx = expressionField.getText();
			if (currentEx.isEmpty()) {
				expressionField.setText(expressionField.getText() + ((Number) e.getSource()).getText());
			} else {
				String lastToken = String.valueOf(currentEx.charAt(currentEx.length() - 1));
				if (lastToken.matches("\\+|\\-|\\/|x")) {
					if (subWaiting == true) {
						subWaiting = false;
						expressionField.setText(expressionField.getText() + ((Number) e.getSource()).getText());
					} else {
						expressionField.setText(expressionField.getText() + " " + ((Number) e.getSource()).getText());
					}
				}

				else if (lastToken.matches("\\)")) {
					return;
				} else {
					expressionField.setText(expressionField.getText() + ((Number) e.getSource()).getText());
				}
			}
		}

	}

	public class Operator extends Button {
		private static final long serialVersionUID = 1L;

		public Operator(String name) {
			super(name);
			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (typing == false) {
				expressionField.setText("");
				typing = true;
			}
			Operator op = (Operator) e.getSource();
			String currentEx = expressionField.getText();
			if (currentEx.isEmpty()) {

				if (op.getText().equals("( )")) {
					expressionField.setText("(");
				} else {
					if (!result.getText().matches("ERROR")) {
						expressionField.setText(op.getText());
					}
				}
			} else {
				String lastToken = String.valueOf(currentEx.charAt(currentEx.length() - 1));
				// Check ()
				if (op.getText().equals("( )")) {
					if (lastToken.matches("\\d|\\)")) {
						expressionField.setText(expressionField.getText() + ")");
					} else {
						expressionField.setText(expressionField.getText() + " (");
					}
				}
				// Check + - \ *
				else if (lastToken.matches("\\d")) {
					expressionField.setText(expressionField.getText() + " " + op.getText());
				} else if (lastToken.matches("\\/|x") && op.getText().equals("-")) {
					expressionField.setText(expressionField.getText() + " " + op.getText());
					subWaiting = true;
				}
			}
		}

	}

	public class Action extends Button {
		private static final long serialVersionUID = 1L;

		public Action(String name) {
			super(name);
			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Action action = (Action) e.getSource();

			if (action.getText().equals("==")) {
				String expression = expressionField.getText().replaceAll("x", "*");
				System.out.println(expression);

				if (expression == null || expression.isEmpty()) {
					return;
				}

				if (expression.substring(0, 1).matches("\\+|\\-|\\*|\\/")) {
					ex = new Expression(result.getText() + expression);
				} else {
					ex = new Expression(expression);
				}
				if (String.valueOf(expression.charAt(expression.length() - 1)).matches("\\+|\\-|\\*|\\/")
						|| !ex.isValidBracket()) {
					result.setText("ERROR");
					typing = false;
					return;
				}
				result.setText(ex.caculate(ex.buildTree()) + "");
				typing = false;
			}
			if (action.getText().equals("AC")) {
				expressionField.setText("");
			}
			if (action.getText().equals("Del")) {
				String currentEx = expressionField.getText();
				expressionField.setText(currentEx.substring(0, currentEx.length() - 1).trim());
			}
		}

	}
}
