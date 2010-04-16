package gui;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import model.Board;
import model.Missing;

import service.Service;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainFrame extends JFrame{
	private JPanel pnlBoard, pnlBoardGrid, pnlCheckBox, pnlNumbers;
	private JScrollPane scpBoards, scpNumbers, scpMissingNumbers;
	private JList lstBoards, lstNumbers, lstMissingNumbers;
	private JButton btnDeleteNumber, btnNewNumber;
	private JLabel lblControleNumber;
	private JTextField txfControleNumber;
	private JFormattedTextField txfNumber;
	private ArrayList<JTextField> txfNumbers = new ArrayList<JTextField>();
	private ButtonGroup btngrpCheck;
	private ArrayList<JRadioButton> rdobtnCheck = new ArrayList<JRadioButton>();
	private String[] rdobtnCheckText = new String[] {"1 Row","2 Rows", "Full"};
	private JCheckBoxMenuItem jCheckBoxMenuItemSound;
	private JMenu jMenuFile, jMenuSettings;
	private JMenuItem jMenuItemImport;
	private JMenuBar jMenuBar;
	private JFileChooser fileChooser = new JFileChooser();
	private File currentFile;
	private Controller controller = new Controller();

	public MainFrame() {

		Service.getService().addNumber(0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bingo Assistant v0.7");
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(292,302);
		{
			jMenuBar = new JMenuBar();
			setJMenuBar(jMenuBar);

			{
				jMenuFile = new JMenu();
				jMenuFile.setText("File");
				jMenuBar.add(jMenuFile);

				{	
					jMenuItemImport = new JMenuItem();
					jMenuItemImport.setText("Import");
					jMenuFile.add(jMenuItemImport);
					jMenuItemImport.addActionListener(controller);
				}
			}
			{
				jMenuSettings = new JMenu();
				jMenuSettings.setText("Settings");
				jMenuBar.add(jMenuSettings);

				{
					jCheckBoxMenuItemSound = new JCheckBoxMenuItem();
					jCheckBoxMenuItemSound.setText("Sound");
					jMenuSettings.add(jCheckBoxMenuItemSound);
					jCheckBoxMenuItemSound.setSelected(true);
				}
			}
		}

		{
			pnlBoard = new JPanel();
			getContentPane().add(pnlBoard);
			pnlBoard.setLayout(null);
			pnlBoard.setBounds(4, 4, 278, 120);
			pnlBoard.setLayout(null);
			pnlBoard.setBorder(BorderFactory.createTitledBorder("Spilleplade"));
			{
				scpBoards = new JScrollPane();
				pnlBoard.add(scpBoards);
				scpBoards.setVisible(true);
				scpBoards.setBounds(196, 16, 74, 96);
				{
					ListModel jList1Model = new DefaultComboBoxModel();
					lstBoards = new JList();
					scpBoards.setViewportView(lstBoards);
					lstBoards.setModel(jList1Model);
					lstBoards.setSelectionBackground(Color.orange);
					lstBoards.setSelectionForeground(Color.black);
					lstBoards.addListSelectionListener(controller);
				}
			}
			{
				lblControleNumber = new JLabel();
				pnlBoard.add(lblControleNumber);
				lblControleNumber.setText("Kontrolnummer:");
				lblControleNumber.setBounds(32, 92, 90, 16);
			}
			{
				txfControleNumber = new JTextField();
				pnlBoard.add(txfControleNumber);
				txfControleNumber.setBounds(120, 90, 38, 23);
				txfControleNumber.setEditable(false);
			}
			{
				pnlBoardGrid = new JPanel();
				GridLayout jPanel2Layout = new GridLayout(3, 9);
				jPanel2Layout.setHgap(0);
				jPanel2Layout.setVgap(0);
				jPanel2Layout.setColumns(9);
				pnlBoard.add(pnlBoardGrid);
				pnlBoardGrid.setLayout(jPanel2Layout);
				pnlBoardGrid.setBounds(8, 16, 180, 70);
				{
					for(int i=0;i<27;i++) {
						txfNumbers.add(new JTextField());
						pnlBoardGrid.add(txfNumbers.get(txfNumbers.size()-1));
						txfNumbers.get(i).setEditable(false);
						txfNumbers.get(i).setBackground(Color.white);
					}
				}
			}
		}
		{ 
			pnlNumbers = new JPanel();
			getContentPane().add(pnlNumbers);
			pnlNumbers.setLayout(null);
			pnlNumbers.setBounds(4, 128, 278, 120);
			pnlNumbers.setLayout(null);
			pnlNumbers.setBorder(BorderFactory.createTitledBorder("Numre"));
			{
				scpNumbers = new JScrollPane();
				pnlNumbers.add(scpNumbers);
				scpNumbers.setBounds(8, 16, 44, 96);

				{
					ListModel jList2Model = new DefaultComboBoxModel();
					lstNumbers = new JList();
					scpNumbers.setViewportView(lstNumbers);
					lstNumbers.setModel(jList2Model);
					lstNumbers.setSelectionBackground(Color.orange);
					lstNumbers.setSelectionForeground(Color.black);
					lstNumbers.addListSelectionListener(controller);
				}
			}
			{
				txfNumber = new JFormattedTextField();
				pnlNumbers.add(txfNumber);
				txfNumber.setBounds(56, 16, 50, 20);
				txfNumber.addActionListener(controller);
			}
			{
				btnNewNumber = new JButton();
				pnlNumbers.add(btnNewNumber);
				btnNewNumber.setText("Nyt");
				btnNewNumber.setBounds(56, 40, 50, 20);
				btnNewNumber.addActionListener(controller);
				btnNewNumber.setMnemonic(KeyEvent.VK_N);
			}
			{
				btnDeleteNumber = new JButton();
				pnlNumbers.add(btnDeleteNumber);
				btnDeleteNumber.setText("Slet");
				btnDeleteNumber.setBounds(56, 64, 50, 20);
				btnDeleteNumber.addActionListener(controller);
			}
			btngrpCheck = new ButtonGroup();
			{
				pnlCheckBox = new JPanel();
				BoxLayout jPanel3Layout = new BoxLayout(pnlCheckBox, javax.swing.BoxLayout.Y_AXIS);
				pnlCheckBox.setLayout(jPanel3Layout);
				pnlNumbers.add(pnlCheckBox);
				pnlCheckBox.setBounds(110, 16, 60, 70);
				{
					for(int i=0;i<3;i++) {
						rdobtnCheck.add(new JRadioButton());
						pnlCheckBox.add(rdobtnCheck.get(i));
						btngrpCheck.add(rdobtnCheck.get(i));
						rdobtnCheck.get(i).setText(rdobtnCheckText[i]);
					}
					rdobtnCheck.get(0).setSelected(true);
				}
			}
			{
				scpMissingNumbers = new JScrollPane();
				pnlNumbers.add(scpMissingNumbers);
				scpMissingNumbers.setBounds(170, 16, 100, 96);

				{
					ListModel jList2Model = new DefaultComboBoxModel();
					lstMissingNumbers = new JList();
					scpMissingNumbers.setViewportView(lstMissingNumbers);
					lstMissingNumbers.setModel(jList2Model);
					lstMissingNumbers.setSelectionBackground(Color.orange);
					lstMissingNumbers.setSelectionForeground(Color.black);
					lstMissingNumbers.addListSelectionListener(controller);
				}
			}
		}
	}

	private MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}

	private File selectFile() {
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Select an .csv file");
		fileChooser.showOpenDialog(MainFrame.this);
		File file = fileChooser.getSelectedFile();
		return file;
	}

	private void updateBoard() {

		if(lstBoards.getSelectedIndex()>-1) {
			txfControleNumber.setText(Service.getService().getAllBoards().get(lstBoards.getSelectedIndex()).getControlenumber());
			for(int i=0;i<27;i++) {
				if(i>=0 && i<=8) {
					txfNumbers.get(i).setText(Integer.toString(Service.getService().getAllBoards().get(lstBoards.getSelectedIndex()).getNumbers().get(0).get(i)));
				}
				else if(i>=9 && i<=17) {
					txfNumbers.get(i).setText(Integer.toString(Service.getService().getAllBoards().get(lstBoards.getSelectedIndex()).getNumbers().get(1).get(i-9)));
				}
				else if(i>=18 && i<=26) {
					txfNumbers.get(i).setText(Integer.toString(Service.getService().getAllBoards().get(lstBoards.getSelectedIndex()).getNumbers().get(2).get(i-18)));
				}
				if(Service.getService().getNumbers().contains(Integer.valueOf(txfNumbers.get(i).getText()))) {
					txfNumbers.get(i).setBackground(Color.orange);
				}
				else {
					txfNumbers.get(i).setBackground(Color.white);
				}
				if(Integer.valueOf(txfNumbers.get(i).getText())==0) {
					txfNumbers.get(i).setText("");
				}
			}
			lstBoards.ensureIndexIsVisible(lstBoards.getSelectedIndex());
		}
	}

	public void playSound(String sound) {
		if(jCheckBoxMenuItemSound.isSelected()) {
			AudioClip clip = null;
			try {
				clip = Applet.newAudioClip(new URL("file:"+sound));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			clip.play();
		}
	}

	private class Controller implements ActionListener, ListSelectionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jMenuItemImport) {
				currentFile = selectFile();
				Service.getService().importCSV(Service.getService().readFile(currentFile));
				lstBoards.setListData(Service.getService().getAllBoards().toArray());
				lstBoards.setSelectedIndex(0);
				JOptionPane.showMessageDialog(MainFrame.this, Service.getService().getAllBoards().size()+" Spilleplader blev indlæst", "Indlæsning færdig", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(e.getSource()==btnNewNumber && !txfNumber.getText().equals("") && Integer.valueOf(txfNumber.getText())<=90 && Integer.valueOf(txfNumber.getText())>0 && Service.getService().getNumbers().size()<=90) {
				int Nummer=Integer.valueOf(txfNumber.getText());
				while(Service.getService().getNumbers().contains(Nummer)) {					
					if(Nummer==90) {
						Nummer=0;
					}
					Nummer++;
				}				
				Service.getService().addNumber(Nummer);
				lstNumbers.setListData(Service.getService().getNumbers().subList(1,Service.getService().getNumbers().size()).toArray());
				lstNumbers.setSelectedIndex(Service.getService().getNumbers().size()-2);
				lstNumbers.ensureIndexIsVisible(Service.getService().getNumbers().size()-2);
				for(int i=0;i<=2;i++) {
					if(rdobtnCheck.get(i).isSelected()) {
						int index = Service.getService().getAllBoards().indexOf(Service.getService().checkFor(i));
						//If a board got what we where looking for
						if(index>=0) {
							lstBoards.setSelectedIndex(index);
							playSound("alarm.wav");
							//If we just found 1 or 2 rows
							if(i<2) {
								lstMissingNumbers.setListData(Service.getService().oneToGo(i+1).toArray());
								rdobtnCheck.get(i+1).setSelected(true);
							}			
						}
						//If no boards got what we where looking for
						else {
							playSound("buzz.wav");
							lstMissingNumbers.setListData(Service.getService().oneToGo(i).toArray());
							lstMissingNumbers.setSelectedIndex(0);
						}
						break;
					}
				}
				updateBoard();
				txfNumber.requestFocus();
				txfNumber.setText("");
			}

			else if(e.getSource()==btnDeleteNumber) {
				Service.getService().deleteNumber(Integer.valueOf(lstNumbers.getSelectedValue().toString()));
				lstNumbers.setListData(Service.getService().getNumbers().subList(1,Service.getService().getNumbers().size()).toArray());
				lstNumbers.setSelectedIndex(Service.getService().getNumbers().size()-1);
				updateBoard();
			}

		}

		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource()==lstBoards && !e.getValueIsAdjusting() && lstBoards.getSelectedIndex()>-1) {
				updateBoard();
			}
			else if(e.getSource()==lstMissingNumbers && !e.getValueIsAdjusting() && lstMissingNumbers.getSelectedIndex()>-1) {
				if(lstMissingNumbers.getSelectedValue() instanceof Missing) {
					Missing missing = (Missing)lstMissingNumbers.getSelectedValue();
					lstBoards.setSelectedIndex(Service.getService().getAllBoards().indexOf(missing.getBoard()));
				}
			}
		}
	}
}
