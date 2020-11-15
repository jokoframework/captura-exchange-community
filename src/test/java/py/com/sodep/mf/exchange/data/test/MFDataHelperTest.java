package py.com.sodep.mf.exchange.data.test;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import py.com.sodep.mf.exchange.MFDataHelper;
import py.com.sodep.mf.exchange.MFField.FIELD_TYPE;

public class MFDataHelperTest {

	@Test
	public void converDateAndBackAgain() {
		String originalValue = "Dani";
		String convertedValue = MFDataHelper.serialize(FIELD_TYPE.STRING, originalValue);
		Assert.assertEquals(originalValue, convertedValue);

	}

	@Test
	public void convertInteger() {
		Integer originalValue = 10;
		String valueStr = MFDataHelper.serialize(FIELD_TYPE.NUMBER, originalValue);
		Assert.assertEquals("10", valueStr);
		Object castValue = MFDataHelper.unserialize(FIELD_TYPE.NUMBER, valueStr);

		if (castValue instanceof Number) {
			Number n = (Number) castValue;
			Assert.assertEquals(originalValue.intValue(), n.intValue());
		} else {
			Assert.fail("Unexpected type after casting an interger");
		}
	}

	@Test
	public void convertDate() {
		Date originalValue = new Date();
		String valueStr = MFDataHelper.serialize(FIELD_TYPE.DATE, originalValue);
		Object castValue = MFDataHelper.unserialize(FIELD_TYPE.DATE, valueStr);
		Assert.assertEquals(originalValue, (Date) castValue);
	}
	
	
}
