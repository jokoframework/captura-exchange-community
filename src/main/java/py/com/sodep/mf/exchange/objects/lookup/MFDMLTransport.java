package py.com.sodep.mf.exchange.objects.lookup;

import java.util.List;

import py.com.sodep.mf.exchange.MFManagedDataBasic;
import py.com.sodep.mf.exchange.TXInfo;

/**
 * <p>
 * This is a transport object that we use to send lookup table data from the
 * server to the device (or other destination). The synchronization process
 * synchronize the transactions that were performed on the server.
 * <p>
 * The device needs to keep track of the last transaction received and resume
 * the download from that point. If there are no more data to send, then the
 * field {@link #isFinal()} will indicate that the synchronization process is
 * over.
 * </p>
 * <p>
 * The field {@link #isFinal()} indicates the end of the transactions, note that
 * this might not mean that the device is in synch since there might be other
 * transactions to synchronize.
 * </p>
 * <p>
 * The device receives this object on the method
 * {@link LookupDataListener#applyDML(MFDMLTransport)} but there is no
 * requirement to analyze the content of the fields {@link #isFinal()} and
 * {@link #isSynch()} because the {@link DatomoSynchronizer} will call the
 * callbacks of the {@link LookupDataListener} when it is appropriate.
 * </p>
 * 
 * @author danicricco
 * 
 */
public class MFDMLTransport {

	/**
	 * Indicates that this is the last batch of data for the transaction
	 */
	private boolean isFinal;

	/**
	 * Indicates that there are no more data expected for this lookupTable
	 */
	private boolean isSynch;

	private List<MFManagedDataBasic> data;

	private TXInfo txInfo;

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public List<MFManagedDataBasic> getData() {
		return data;
	}

	public void setData(List<MFManagedDataBasic> data) {
		this.data = data;
	}

	public TXInfo getTxInfo() {
		return txInfo;
	}

	public void setTxInfo(TXInfo txInfo) {
		this.txInfo = txInfo;
	}

	public boolean isSynch() {
		return isSynch;
	}

	public void setSynch(boolean isSynch) {
		this.isSynch = isSynch;
	}

	@Override
	public String toString() {
		return "MFDMLTransport [isFinal=" + isFinal + ", isSynch=" + isSynch + ", data=" + data + ", txInfo=" + txInfo
				+ "]";
	}

}
