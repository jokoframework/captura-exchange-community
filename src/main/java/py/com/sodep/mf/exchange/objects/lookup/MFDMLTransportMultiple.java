package py.com.sodep.mf.exchange.objects.lookup;

import java.util.List;

/**
 * Transmitting a single {@link MFDMLTransport} every time is an overhead for
 * lookups with several transactions. This class is a wrapper of multiple
 * {@link MFDMLTransport} that can be used to read several
 * {@link MFDMLTransport}
 * 
 * @author danicricco
 * 
 */
public class MFDMLTransportMultiple {

	private List<MFDMLTransport> listOfTransports;

	public MFDMLTransportMultiple() {

	}

	public MFDMLTransportMultiple(List<MFDMLTransport> listOfTransports) {
		this.listOfTransports = listOfTransports;
	}

	public List<MFDMLTransport> getListOfTransports() {
		return listOfTransports;
	}

	public void setListOfTransports(List<MFDMLTransport> listOfTransports) {
		this.listOfTransports = listOfTransports;
	}

}
