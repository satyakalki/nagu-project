import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;


public class AddCandidate extends JPanel {
	private JTextField candidateTextField;
	private JTextField partyTextField;
	private List<String> localities;

	private DBRepoManager repo = new DBRepoManager();
	/**
	 * Create the panel.
	 */
	public AddCandidate(AdminDashboardPanel parentPanel) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		localities = repo.getScheduleElectionLocalities();
		
		
		if (localities.isEmpty()) {
			
			JLabel label = new JLabel("No Elections are scheduled to add candidates.");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Tahoma", Font.PLAIN, 18));
			label.setBounds(0, 250, 790, 21);
			add(label);
			return;
		}
		
		
		
		JLabel lblAddCandidate = new JLabel("Add Candidate");
		lblAddCandidate.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddCandidate.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAddCandidate.setBounds(262, 35, 337, 28);
		add(lblAddCandidate);
		
		JLabel lblCandidateName = new JLabel("Candidate Name");
		lblCandidateName.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCandidateName.setBounds(262, 107, 164, 14);
		add(lblCandidateName);
		
		JLabel lblParty = new JLabel("Party");
		lblParty.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblParty.setBounds(262, 158, 160, 27);
		add(lblParty);
		
		JLabel lblLocality = new JLabel("Locality");
		lblLocality.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblLocality.setBounds(262, 210, 160, 27);
		add(lblLocality);
		
		candidateTextField = new JTextField();
		candidateTextField.setFont(new Font("Calibri", Font.PLAIN, 18));
		candidateTextField.setBounds(436, 101, 176, 27);
		add(candidateTextField);
		candidateTextField.setColumns(10);
		
		partyTextField = new JTextField();
		partyTextField.setFont(new Font("Calibri", Font.PLAIN, 18));
		partyTextField.setBounds(436, 158, 176, 27);
		add(partyTextField);
		partyTextField.setColumns(10);
		String[] options = new String[localities.size()];
		for (int i=0;i<localities.size();i++) {
			options[i] = localities.get(i);
		}
		JComboBox comboBox = new JComboBox(options);
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 18));
		comboBox.setBounds(436, 210, 176, 27);
		add(comboBox);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = candidateTextField.getText();
				String party = partyTextField.getText();
				String locality = comboBox.getSelectedItem().toString();
				System.out.println(locality);
				
//				parentPanel.showElectionDetails();
				int res = repo.AddCandidate(name, party, locality);
				if (res == 1) {
					JOptionPane.showMessageDialog(null, "Candidate added successfully!!!");
					candidateTextField.setText("");
					partyTextField.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Something went wrong. make sure you entered valid Details.");

				}
				
				//repo.AddCandidate(name, party, locality);
				//demo
			
				
			}
		});
		btnAdd.setBounds(338, 300, 154, 27);
		add(btnAdd);
	}
}
