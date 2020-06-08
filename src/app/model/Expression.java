package app.model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import api.BinaryTreeNode;

public class Expression {
	private String expression;

	public Expression(String expression) {
		this.expression = expression;
	}

	public boolean isValidBracket() {
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '(' || expression.charAt(i) == '[' || expression.charAt(i) == '{') {
				stack.push(expression.charAt(i));
			} else if (expression.charAt(i) == ')') {
				if (stack.isEmpty())
					return false;
				if (stack.peek() == '(') {
					stack.pop();
				} else
					return false;
			} else if (expression.charAt(i) == ']') {
				if (stack.isEmpty())
					return false;
				if (stack.peek() == '[') {
					stack.pop();
				} else
					return false;
			} else if (expression.charAt(i) == '}') {
				if (stack.isEmpty())
					return false;
				if (stack.peek() == '{') {
					stack.pop();
				} else
					return false;
			}
		}

		return stack.isEmpty();
	}

	private String[] getTokens() {
//		if (isValid() == false)
//			return null;

		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?|\\(|\\)|\\+|\\-|\\*|\\/");
		Matcher matcher = pattern.matcher(expression);

		// Get tokens
		ArrayList<String> tokens = new ArrayList<String>();
		while (matcher.find()) {
			String token = matcher.group();
			if (tokens.isEmpty()) {
				tokens.add(token);
				continue;
			}
			String preToken = tokens.get(tokens.size() - 1);
			if (token.matches("\\+")) {
				if (preToken.matches("-?\\d+(\\.\\d+)?|\\("))
					tokens.add(token);
			} else if (token.matches("\\-")) {
				if (preToken.matches("\\+"))
					tokens.set(tokens.size() - 1, "-");
				else if (preToken.matches("\\-"))
					tokens.set(tokens.size() - 1, "+");
				else
					tokens.add(token);
			} else
				tokens.add(token);
		}
		return tokens.toArray(new String[tokens.size()]);
	}

	private String[] innerToPost() {
		String[] tokens = getTokens();
		ArrayList<String> list = new ArrayList<String>();

		Stack<String> stack = new Stack<String>();

		for (String token : tokens) {
			if (token.matches("-?\\d+(\\.\\d+)?")) {
				list.add(token);
			}
			if (token.matches("\\(")) {
				stack.push(token);
			}
			if (token.matches("\\)")) {
				while (!stack.peek().equals("(")) {
					list.add(stack.pop());
				}
				stack.pop();
			}
			if (token.matches("\\+|\\-|\\*|\\/")) {
				while (!stack.empty() && stack.peek().matches("\\+|\\-|\\*|\\/") && isPriority(stack.peek(), token)) {
					list.add(stack.pop());
				}
				stack.push(token);
			}
		}

		while (!stack.empty()) {
			list.add(stack.pop());
		}

		return list.toArray(new String[list.size()]);
	}

	private static boolean isPriority(String op1, String op2) {
		if (op1.matches("\\+|\\-") && op2.matches("\\*|\\/"))
			return false;
		return true;
	}

	public BinaryTreeNode<String> buildTree() {
		String[] tokens = innerToPost();
		Stack<BinaryTreeNode<String>> stack = new Stack<BinaryTreeNode<String>>();

		for (int i = 0; i < tokens.length; i++) {
			BinaryTreeNode<String> node = new BinaryTreeNode<String>(tokens[i]);
			if (tokens[i].matches("-?\\d+(\\.\\d+)?")) {
				stack.push(node);
			}
			if (tokens[i].matches("\\+|\\-|\\*|\\/")) {
				node.setRight(stack.pop());
				node.setLeft(stack.pop());
				stack.push(node);
			}
		}

		return stack.pop();
	}

	private static double caculate(Double value1, Double value2, String op) {
		if (op.equals("+")) {
			return Double.valueOf(value1) + Double.valueOf(value2);
		}
		if (op.equals("-")) {
			return Double.valueOf(value1) - Double.valueOf(value2);
		}
		if (op.equals("*")) {
			return Double.valueOf(value1) * Double.valueOf(value2);
		}
		if (op.equals("/")) {
			return Double.valueOf(value1) / Double.valueOf(value2);
		}
		return 0;
	}

	public double caculate(BinaryTreeNode<String> tree) {
		if (tree != null) {
			if (tree.left(tree) != null && tree.right(tree) != null) {
				return caculate(caculate(tree.getLeft()), caculate(tree.getRight()), tree.getElement());
			}
			if (tree.getElement().matches("-?\\d+(\\.\\d+)?")) {
				return Double.valueOf(tree.getElement());
			} else {
				throw new IllegalArgumentException("Tree is incorrect");
			}
		}

		else {
			throw new IllegalArgumentException("Tree is null");
		}
	}

	public static void main(String[] args) {
		Expression expression = new Expression("-1 + 2 +4 + (5) *2");

		System.out.println(expression.caculate(expression.buildTree()));
	}
}
