package mc.webservice.utils;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

public class XmlUtil {
	private static final Logger logger = Logger.getLogger(XmlUtil.class);

	public static Object xmlToObject(String input, Class<? extends Object> type)
			throws JAXBException {
		if (input == null)
			return null;

		JAXBContext jc = null;
		Unmarshaller u = null;

		Object obj = null;
		jc = JAXBContext.newInstance(type.getPackage().getName());
		u = jc.createUnmarshaller();

		logger.info(type.getPackage().getName());
		obj = (Object) u.unmarshal(new StreamSource(new StringReader(input
				.trim())));

		return obj;
	}
	
	public static Object xmlToObject(byte[] bytes, Class<? extends Object> type)
			throws JAXBException {
		return xmlToObject(new String(bytes), type);
	}

	public static ByteArrayOutputStream objectToXml(Object obj)
			throws JAXBException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		JAXBContext jc = null;
		Marshaller m = null;
		jc = JAXBContext.newInstance(obj.getClass().getPackage().getName());
		m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(obj, os);

		return os;
	}
}
