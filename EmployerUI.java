package sixgolfballs;

public class EmployerUI 
{
    public EmployerUI()
    {
        
    }
    
    public Checklist createChecklist(String listName)
    {
        return new Checklist(listName);
    }
}

Loc:
JPanel Employer = new JPanel();
		contentPane.add(Employer, "name_77521355317451");
		Employer.setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel parent_ = (JPanel)FormLogin.getParent();
				CardLayout layout_ = (CardLayout)parent_.getLayout();
				layout_.last(parent_);
			}
		});
		
		table.setBounds(10, 125, 309, 54);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Task 1"},
				{"Task 2"},
				{null},
			},
			new String[] {
				"Task"
			}
		));
		Employer.add(table);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel parent_ = (JPanel)FormLogin.getParent();
				CardLayout layout_ = (CardLayout)parent_.getLayout();
				layout_.next(parent_);
				layout_.next(parent_);
				layout_.next(parent_);
			}
		});
		btnEdit.setBounds(424, 70, 94, 25);
		Employer.add(btnEdit);
		
		txtWelcomeEmployer = new JTextField();
		txtWelcomeEmployer.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtWelcomeEmployer.setText("Welcome Employer!");
		txtWelcomeEmployer.setBounds(0, 0, 558, 44);
		Employer.add(txtWelcomeEmployer);
		txtWelcomeEmployer.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Task Lists");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(10, 71, 309, 19);
		Employer.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Today");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 100, 309, 15);
		Employer.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("This Week");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 206, 294, 24);
		Employer.add(lblNewLabel_4);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"New column"
			}
		));
		table_2.setBounds(20, 221, 131, -42);
		Employer.add(table_2);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		));
		table_3.setBounds(10, 240, 309, 54);
		Employer.add(table_3);
		
		txtPropertyOfSixgolfballs = new JTextField();
		txtPropertyOfSixgolfballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtPropertyOfSixgolfballs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPropertyOfSixgolfballs.setText("Property Of SixGolfBalls");
		txtPropertyOfSixgolfballs.setBounds(0, 320, 558, 34);
		Employer.add(txtPropertyOfSixgolfballs);
		txtPropertyOfSixgolfballs.setColumns(10);
