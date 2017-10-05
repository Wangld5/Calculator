package MyChange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThirdCalcu extends JPanel implements ActionListener{
	private String key[] = {"0","1","2","3","4","5","6","7","8","9"};
	private JButton KEYS[] = new JButton[key.length];
	private JButton virance;
	private JButton label;
	private JButton clear;
	private JTextField myText;
	private int count = 0;
	private boolean firstDigit = true;
	private String operator = "=";
	private double Store[] = new double[1000]; 
	
	public ThirdCalcu() {
		myText = new JTextField();
		myText.setBackground(Color.WHITE);
		myText.setEditable(false);
		myText.setHorizontalAlignment(JTextField.RIGHT);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(myText);
		JPanel panel4 = new JPanel(new GridLayout(2,5,3,3));
		for(int i=0;i<key.length;i++) {
			KEYS[i] = new JButton(key[i]);
			KEYS[i].setForeground(Color.BLUE);
			panel4.add(KEYS[i]);
		}
		virance = new JButton("VIRANCE");
		label = new JButton("=");
		virance.setForeground(Color.red);
		label.setForeground(Color.BLUE);
		clear = new JButton("clear");
		clear.setForeground(Color.RED);
		JPanel panel2 = new JPanel(new GridLayout(1,3,3,3));
		panel2.add(virance);
		panel2.add(label);
		panel2.add(clear);
		setLayout(new BorderLayout(2,2));
		add("North",panel1);
		add("South",panel2);
		add("Center",panel4);
		virance.addActionListener(this);
		label.addActionListener(this);
		clear.addActionListener(this);
		for(int i=0;i<key.length;i++) {
			KEYS[i].addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String myLabel = e.getActionCommand();
		if(myLabel.equals("VIRANCE")) {
			handlevirance();
		}
		else if(myLabel.equals("=")) {
			handleresult();
		}
		else if(myLabel.equals("clear")) {
			ClearOperation();
		}
		else if("0123456789.".indexOf(myLabel) >= 0) {
			NumberOperator(myLabel);
		}
	}
	private void handlevirance() {
		Store[count++] = Double.valueOf(myText.getText()).doubleValue();
		ClearOperation();
	}
	private void handleresult() {
		double average = 0;
		double sum = 0;
		for(int i=0;i<count;i++) {
			sum+=Store[i];
		}
		average = (double)sum/count;
		double accum = 0;
		for(int i=0;i<count;i++) {
			accum = Math.pow((Store[i]-average), 2);
		}
		double vira = accum/count;
		long a;
		Double b;
		a = (long)vira;
		b = vira - a;
		if(b == 0) {
			myText.setText(Long.toString(a));
		}
		else {
			myText.setText(Double.toString(vira));
		}
	}
	private void ClearOperation() {
		myText.setText("");
		operator = "=";
		firstDigit = true;
	}
	private void NumberOperator(String number) {
		if(firstDigit) {
			myText.setText(number);
		}
		else if(key.equals(".") && myText.getText().indexOf(".")<0) {
			myText.setText(myText.getText() + ".");
		}
		else if(!key.equals(".")) {
			myText.setText(myText.getText() + number);
		}
		firstDigit = false;
	}
}
