import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	DefaultTableModel model = new DefaultTableModel();
	
	ManageEmployee ME = new ManageEmployee();
	Integer empID1, empID2, empID3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					SessionFactoryConfig.createSessionFactory();
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Throwable ex) {
					System.err
							.println("Failed to create sessionFactory object."
									+ ex);
					throw new ExceptionInInitializerError(ex);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Add some employee's records */
				empID1 = ME.addEmployee("Zara", "Ali", 1000);
				empID2 = ME.addEmployee("Daisy", "Das", 5000);
				empID3 = ME.addEmployee("John", "Paul", 10000);
			}
		});
		panel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Update employee's records */
				ME.updateEmployee(empID1, 5000);
			}
		});
		panel.add(btnUpdate);

		JButton button_2 = new JButton("Del");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Delete an employee from the database */
				ME.deleteEmployee(empID2);
			}
		});
		panel.add(button_2);

		JButton button_3 = new JButton("Get");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				/* List down all the employees */
				for (Object o : ME.listEmployees()) {
					Employee employee = (Employee) o;
					 model.addRow(new Object[] { employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getSalary() });
				}
			}
		});
		panel.add(button_3);

		JButton button_4 = new JButton("Quit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel.add(button_4);
		model.addColumn("ID");
		model.addColumn("First Name");
		model.addColumn("Last Name");
		model.addColumn("Salary ($)");
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
	}
}
