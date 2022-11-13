package domain;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class UserAdapter extends AbstractTableModel {
	private final Vector<ApustuAnitza> apuestas;
	private Registered user;
	private String[] colNames = new String[] {"Event", "Question", "Event Date", "Bet (€)"};
	
	public UserAdapter(Registered user) {
		apuestas = new Vector<ApustuAnitza>(user.getApustuAnitzak());
		this.user = user;
	}

	public int getRowCount() {
		return apuestas.size();
	}

	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int col) {
		return colNames[col];
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: return (Object)apuestas.get(rowIndex).getApustuak();
		case 1: return (Object)apuestas.get(rowIndex);
		case 2: return (Object)apuestas.get(rowIndex).getData();
		case 3: return (Object)apuestas.get(rowIndex).getBalioa();
		}
		return null;
	}
	
}
