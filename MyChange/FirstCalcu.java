package MyChange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FirstCalcu extends JFrame implements ActionListener{
	JPanel panel3;    
    JMenuBar bar;  
    JMenu menu;  
    JMenuItem itemInitial , itemChange;  
    FlowLayout layout = new FlowLayout();  
    SecondCalcu second;
    ThirdCalcu third;
	public FirstCalcu(){
		init();
		this.setBackground(Color.LIGHT_GRAY);
		this.setResizable(false);
		setBounds(100,100,350,230);  
        setVisible(true);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
	private void init() {
		bar = new JMenuBar();
		menu = new JMenu("菜单");
		second = new SecondCalcu();
		third = new ThirdCalcu();
		itemInitial = new JMenuItem("方差");
		itemChange = new JMenuItem("无方差");
		panel3 = new JPanel(new BorderLayout(2,2));
		add(panel3);
		itemInitial.addActionListener(this);
		itemChange.addActionListener(this);
		bar.add(menu);
		menu.add(itemInitial);
		menu.add(itemChange);
		setJMenuBar(bar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == itemInitial) {
			panel3.removeAll();
			panel3.add(third);
			panel3.validate();
			repaint();
		}
		else if(e.getSource() == itemChange) {
			panel3.removeAll();
			panel3.add(second);
			panel3.validate();
			repaint();
		}
	}
	public static void main(String args[]) {
		FirstCalcu test = new FirstCalcu();
		test.setTitle("Calculator");
	}
	
}
