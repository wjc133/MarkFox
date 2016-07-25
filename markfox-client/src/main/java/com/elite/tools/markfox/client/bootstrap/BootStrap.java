	package com.elite.tools.markfox.client.bootstrap;
    import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import javax.swing.border.EmptyBorder;

	public class BootStrap
    {

		JFrame mainJFrame;
		Container con;
		JPanel Panel,Panel1;
		JMenuBar mainMenuBar;
		JScrollPane JSPane1=new JScrollPane(),JSPane2=new JScrollPane();
		JTextArea text1,text2;
		JMenu fileMenu,editMenu,formatMenu,helpMenu,insertMenu,checkMenu,toolMenu;

		JMenuItem newItem,openItem,saveItem,saveasItem,pageItem,printItem,exitItem;

		JMenuItem undoItem,cutItem,copyItem,pasteItem,findItem,replaceItem,selectallItem;

		JCheckBoxMenuItem wrapItem;
		JMenuItem fontItem;

		JMenuItem helpItem,aboutItem;

	public BootStrap()
	{

		initWindow();
//		mainJFrame=new JFrame("MarkFox");
//		mainJFrame.pack();
//		mainJFrame.setSize(894, 533);
//		Dimension dim = mainJFrame.getSize();
//		Dimension dim2=Toolkit.getDefaultToolkit().getScreenSize();
//		Panel=new JPanel();
//		int w=(int)(dim.getWidth()/2);
//		int h=(int)dim.getHeight();
//		con=mainJFrame.getContentPane();

		mainJFrame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent state) {

				//if(state.getNewState() == 1 || state.getNewState() == 7) {
				//	System.out.println("������С��");
				//}else if(state.getNewState() == 0) {
				//	System.out.println("���ڻָ�����ʼ״̬");
				//}else
				if (state.getNewState() == 6) {
					System.out.println("�������");
					initWindow();

				}else if(state.getNewState() == 0) {
				System.out.println("���ڻָ�����ʼ״̬");
					reinitWindow();
			}
		}

		});

//		text1=new JTextArea("��"+w);
//		text1.setTabSize(4);
//		text1.setFont(new Font("����",Font.BOLD,16));
//		text1.setLineWrap(true);
//		text1.setWrapStyleWord(true);
//		text1.setSize(w,h);
//
//		text2=new JTextArea("�ߣ�"+h);
//		text2.setFont(new Font("����",Font.BOLD,16) );
//		text2.setLineWrap(true);
//		text2.setWrapStyleWord(true);
//		text2.setSize(w,h);
//
//		JSPane1=new JScrollPane(text1);
//		JSPane2=new JScrollPane(text2);


//		con.add(JSPane1,BorderLayout.WEST);
//		con.add(JSPane2);
//		con.add(Panel,BorderLayout.NORTH);

//
//		mainJFrame.setJMenuBar(mainMenuBar);


//		mainJFrame.setVisible(true);
//		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void initWindow()
	{
		JPanel Panel1;

		mainJFrame=new JFrame("MarkFox");
		mainJFrame.pack();
		mainJFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		Dimension dim = mainJFrame.getSize();

		Dimension dim2=Toolkit.getDefaultToolkit().getScreenSize();
		Panel=new JPanel();

		Panel1=new JPanel();
		Panel1.setLayout(new BorderLayout());
		Panel1.removeAll();
		Panel1.repaint();
		int w=(int)(dim.getWidth()/2*10);
		int h=(int)dim.getHeight()*15;
		con=mainJFrame.getContentPane();

		text1=new JTextArea();
	//	text1.setTabSize(4);
		text1.setFont(new Font("����",Font.BOLD,16));
		text1.setLineWrap(true);
	//	text1.setWrapStyleWord(true);
		text1.setSize(w,h);

		text2=new JTextArea();
		text2.setFont(new Font("����",Font.BOLD,16) );
		text2.setLineWrap(true);
	//	text2.setWrapStyleWord(true);
		text2.setSize(w,h);

		JSPane1=new JScrollPane(text1);
		JSPane2=new JScrollPane(text2);
		Panel1.add(JSPane1,BorderLayout.WEST);
		Panel1.add(JSPane2);
		//con.add(JSPane2,BorderLayout.EAST);
		Panel1.add(Panel,BorderLayout.NORTH);
		con.add(Panel1);
		mainJFrame.setJMenuBar(mainMenuBar);
		createMenu();
		tool();

		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void reinitWindow()
	{
        mainJFrame.pack();
		mainJFrame.setSize(894, 533);

		Dimension dim = mainJFrame.getSize();
		//Dimension dim2=Toolkit.getDefaultToolkit().getScreenSize();

		Panel=new JPanel();
		Panel1=new JPanel();
		Panel1.setLayout(new BorderLayout());
		Panel1.removeAll();
		Panel1.repaint();

		int w=(int)(dim.getWidth()/2);
		int h=(int)dim.getHeight();
		con=mainJFrame.getContentPane();

		text1=new JTextArea();
	//	text1.setTabSize(4);
		text1.setFont(new Font("����",Font.BOLD,16));
	//	text1.setLineWrap(true);
	//	text1.setWrapStyleWord(true);
		text1.setSize(w,h);

		text2=new JTextArea();
		text2.setFont(new Font("����",Font.BOLD,16) );
	//	text2.setLineWrap(true);
	//	text2.setWrapStyleWord(true);
		text2.setSize(w,h);

		JSPane1=new JScrollPane(text1);
		JSPane2=new JScrollPane(text2);

//		Panel1.add(JSPane1,BorderLayout.WEST);
//		Panel1.add(JSPane2);
//		Panel1.add(Panel,BorderLayout.NORTH);
		con.add(Panel,BorderLayout.NORTH);
		con.add(JSPane1,BorderLayout.WEST);
		con.add(JSPane2);

	//	con.add(Panel1);
		mainJFrame.setJMenuBar(mainMenuBar);
		createMenu();
		tool();
		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void tool()
	{
		JButton button1 =new JButton("B ");
		JButton button2 =new JButton("I ");
		JButton button3 =new JButton("H1");
		JButton button4 =new JButton("H2");
		JButton iconButton1 =new JButton();
		button1.setSize(15,15);
		button2.setSize(15,15);
		button3.setSize(15,15);
		button4.setSize(15,15);
		iconButton1.setSize(15,15);

	//	iconButton1.setIcon(new ImageIcon(BootStrap.class.getResource("file.jpg")));

		JToolBar bar = new JToolBar();

		bar.add(button1);
		bar.add(button2);
		bar.add(button3);
		bar.add(button4);
		bar.add(iconButton1);

		BorderLayout bord = new BorderLayout();
		//Panel.setLayout(bord);
		//bar.setLayout(null);
		Panel.setLayout(bord);
		Panel.add("North",bar);

	}
	public void createMenu()
	{
		mainMenuBar= new JMenuBar();
		fileMenu=new JMenu("�ļ�");
		editMenu=new JMenu("�༭");
		formatMenu=new JMenu("��ʽ");
		insertMenu =new JMenu("����");
		checkMenu =new JMenu("�鿴");
		toolMenu = new JMenu("����");
		helpMenu=new JMenu("����");


		mainMenuBar.add(fileMenu);
		newItem=new JMenuItem("�½�");
		openItem=new JMenuItem("��");
		saveItem=new JMenuItem("����");
		saveasItem=new JMenuItem("���Ϊ");
		pageItem=new JMenuItem("ҳ��");
		printItem=new JMenuItem("��ӡ");
		exitItem=new JMenuItem("�˳�");
		
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveasItem);
		fileMenu.addSeparator();
		fileMenu.add(pageItem);
		fileMenu.add(printItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		


		mainMenuBar.add(editMenu);
		undoItem=new JMenuItem("����");
		cutItem=new JMenuItem("����");
		copyItem=new JMenuItem("����");
		pasteItem=new JMenuItem("ճ��");
		findItem=new JMenuItem("����");
		replaceItem=new JMenuItem("�滻");
		selectallItem=new JMenuItem("ȫѡ");
		editMenu.add(undoItem);
		editMenu.addSeparator();
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.addSeparator();
		editMenu.add(findItem);
		editMenu.add(replaceItem);
		editMenu.addSeparator();
		editMenu.add(selectallItem);

		mainMenuBar.add(insertMenu);
		mainMenuBar.add(checkMenu);
		mainMenuBar.add(toolMenu);

		mainMenuBar.add(formatMenu);
		wrapItem=new JCheckBoxMenuItem("����");
		fontItem=new JMenuItem("����");
		formatMenu.add(wrapItem);
		formatMenu.add(fontItem);
		
		mainMenuBar.add(helpMenu);
		helpItem=new JMenuItem("�鿴����");
		aboutItem=new JMenuItem("����");
		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);

		
	}
	public static void main(String[] args)
	{
		new BootStrap();
	}
}
