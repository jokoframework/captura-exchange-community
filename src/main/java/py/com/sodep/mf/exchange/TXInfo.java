package py.com.sodep.mf.exchange;

public class TXInfo {

	public static enum OPERATION {
		INSERT, UPDATE, DELETE
	};

	private String tx;
	private Long lookupTable;
	private Long startRow;
	private Long endRow;
	private OPERATION operation;

	public TXInfo() {

	}

	public TXInfo(Long lookupTable) {
		this.lookupTable = lookupTable;
	}

	public String getTx() {
		return tx;
	}

	public void setTx(String tx) {
		this.tx = tx;
	}

	public Long getLookupTable() {
		return lookupTable;
	}

	public void setLookupTable(Long lookupTable) {
		this.lookupTable = lookupTable;
	}

	public Long getStartRow() {
		return startRow;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public Long getEndRow() {
		return endRow;
	}

	public void setEndRow(Long endRow) {
		this.endRow = endRow;
	}

	public OPERATION getOperation() {
		return operation;
	}

	public void setOperation(OPERATION operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "TXInfo [tx=" + tx + ", lookupTable=" + lookupTable + ", startRow=" + startRow + ", endRow=" + endRow
				+ ", operation=" + operation + "]";
	}

}
