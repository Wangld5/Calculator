package MyChange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SecondCalcu extends JPanel implements ActionListener{
	String key[] = {"0","1","2","3","4","5","6","7","8","9",".","%","*","/","+","-","+/-",
			"sqrt","=","^"};
	String command[] = {"Back","Clear","ANS"};
	JButton KEYS[] = new JButton[key.length];
	JButton COMMANDS[] = new JButton[command.length];
	JTextField Text = new JTextField();
	boolean firstDigit = true;
	double resultnum = 0;
	String operator = "=";
	boolean operatorValid = true;
	public SecondCalcu() {
		Text.setHorizontalAlignment(JTextField.RIGHT);
		Text.setEditable(false);
		Text.setBackground(Color.WHITE);
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.add("Center", Text);
		JPanel keypanel = new JPanel();
		keypanel.setLayout(new GridLayout(4,5,3,3));
		for(int i=0;i<key.length;i++) {
			KEYS[i] = new JButton(key[i]);
			KEYS[i].setForeground(Color.blue);
			keypanel.add(KEYS[i]);
		}
		for(int i=10;i<key.length;i++) {
			KEYS[i].setForeground(Color.RED);
		}
		JPanel commandpanel = new JPanel();
		commandpanel.setLayout(new GridLayout(1,3,3,3));
		for(int i=0;i<command.length;i++) {
			COMMANDS[i] = new JButton(command[i]);
			COMMANDS[i].setForeground(Color.RED);
			commandpanel.add(COMMANDS[i]);
		}
		setLayout(new BorderLayout(3,3));
		add("North",top);
		add("Center", commandpanel);
		add("South", keypanel);
		for(int i=0;i<key.length;i++) {
			KEYS[i].addActionListener(this);
		}
		for(int i=0;i<command.length;i++) {
			COMMANDS[i].addActionListener(this);
		}
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand();
		if(action.equals(command[0])) {
			BackOperator();
		}
		else if(action.equals(command[1])) {
			ClearOperation();
		}
		else if("0123456789.".indexOf(action) >= 0) {
			NumberOperator(action);
		}
		else {
			CalculateOperator(action);
		}
	}
	private void BackOperator() {
		String text = Text.getText();
		int i = text.length();
		if(i>0) {
			text = text.substring(0, i-1);
			if(text.length()==0) {
				Text.setText("");
				firstDigit = true;
				operator = "=";
			}
			else {
				Text.setText(text);
			}
		}
	}
	private void ClearOperation() {
		Text.setText("");
		operator = "=";
		firstDigit = true;
	}
	private void NumberOperator(String number) {
		if(firstDigit) {
			Text.setText(number);
		}
		else if(key.equals(".") && Text.getText().indexOf(".")<0) {
			Text.setText(Text.getText() + ".");
		}
		else if(!key.equals(".")) {
			Text.setText(Text.getText() + number);
		}
		firstDigit = false;
	}
	private void CalculateOperator(String ope) {
		if(operator.equals("+")) {
			resultnum += Double.valueOf(Text.getText()).doubleValue(); 
		}
		else if(operator.equals("-")) {
			resultnum -= Double.valueOf(Text.getText()).doubleValue();
		}
		else if(operator.equals("*")) {
			resultnum *= Double.valueOf(Text.getText()).doubleValue();
		}
		else if(operator.equals("/")) {
			if(Double.valueOf(Text.getText()).doubleValue() == 0.0) {
				operatorValid = false;
				Text.setText("NOT VALID INPUT");
			}
			else {
				resultnum /= Double.valueOf(Text.getText()).doubleValue();
			}
		}
		else if(operator.equals("+/-")) {
			resultnum = resultnum*(-1);
		}
		else if(operator.equals("sqrt")) {
			resultnum = Math.sqrt(resultnum);
		}
		else if(operator.equals("%")) {
			resultnum %= Double.valueOf(Text.getText()).doubleValue();
		}
		else if(operator.equals("^")) {
			resultnum = Math.pow(resultnum, Double.valueOf(Text.getText()).doubleValue());
		}
		else if(operator.equals("=")) {
			resultnum = Double.valueOf(Text.getText()).doubleValue();
		}
		if(operatorValid) {
			long a;
			Double b;
			a = (long)resultnum;
			b = resultnum - a;
			if(b == 0) {
				Text.setText(Long.toString(a));
			}
			else {
				Text.setText(Double.toString(resultnum));
			}
		}
		operator = ope;
		firstDigit = true;
		operatorValid = true;
	}
}
