import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.awt.Color;

public class ListDisplayPanel extends JPanel {
	private ArrayList<Checklist> checklists;
    private JTable CurrentLists;
    private JTable PastLists;
    
	private ArrayList<String> getFiles(String directory) {
		ArrayList<String> textFiles = new ArrayList<String>();
		
		File dir = new File(directory);
		if (!dir.exists()){
			dir.mkdirs();
		}
		File[] files = dir.listFiles();
		for (File file : files) {
			textFiles.add(file.getName());
		}
		return textFiles;
	}
	
	public void getChecklists() throws IOException {
		checklists = new ArrayList<Checklist>();
		ArrayList<String> files = getFiles("checklists/");
		for (String fileName : files) {
			//System.out.println(fileName);
			Checklist list =  new Checklist("checklists/" + fileName, "");
			checklists.add(list);
		}
	}
	
	public void refreshLists() throws IOException {
		getChecklists();
		DefaultTableModel currentModel = new DefaultTableModel(new Object[][] {}, new String[] {"Checklists"});
		DefaultTableModel pastModel = new DefaultTableModel(new Object[][] {}, new String[] {"Completed Checklists"});
		if (!checklists.isEmpty()) {
			for (Checklist list : checklists) {
				list.checkForCompleteness();
				//System.out.println(list.isListComplete());
				if (list.isListComplete()) {
					pastModel.addRow(new Checklist[] {list});
				} else {
					currentModel.addRow(new Checklist[] {list});
				}
			}
		}
		CurrentLists.setModel(currentModel);
		PastLists.setModel(pastModel);
	}
	
	public JTable getCurrentListsTable() {
		return CurrentLists;
	}
	
	public JTable getPastListsTable() {
		return PastLists;
	}
	
	public ListDisplayPanel(JPanel parent) {
		JPanel ListPanel = new JPanel();
		ListPanel.setBounds(0, 43, 331, 276);
		ListPanel.setBackground(new Color(255,255,255,0));
		parent.add(ListPanel);
		ListPanel.setLayout(null);
		ListPanel.setVisible(true);
		
		JScrollPane TopScrollPane = new JScrollPane();
		TopScrollPane.setBounds(10, 11, 311, 130);
		//TopScrollPane.setBackground(new Color(255,255,255,0));
		ListPanel.add(TopScrollPane);
		
		CurrentLists = new JTable();
		CurrentLists.setFillsViewportHeight(true);
		TopScrollPane.setViewportView(CurrentLists);
		CurrentLists.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//CurrentLists.setFont(new Font("Tahoma", Font.PLAIN, 12));
		//CurrentLists.setBackground(new Color(255,255,255,50));
		CurrentLists.setDefaultEditor(Object.class, null);
		
		JScrollPane BottomScrollPane = new JScrollPane();
		BottomScrollPane.setBounds(10, 152, 311, 113);
		//BottomScrollPane.setBackground(new Color(255,255,255,0));
		ListPanel.add(BottomScrollPane);
		
		PastLists = new JTable();
		PastLists.setFillsViewportHeight(true);
		BottomScrollPane.setViewportView(PastLists);
		PastLists.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//CurrentLists.setFont(new Font("Tahoma", Font.PLAIN, 12));
		PastLists.setDefaultEditor(Object.class, null);
		
		try {
			refreshLists();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
