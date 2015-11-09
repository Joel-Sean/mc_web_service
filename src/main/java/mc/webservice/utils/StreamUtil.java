package mc.webservice.utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class StreamUtil {
	
	public static void save(InputStream stream, File file) throws FileNotFoundException, IOException {
		save(stream, new FileOutputStream(file));
	}
	
	public static void save(InputStream iStream, OutputStream oStream) throws IOException {
		byte[] buffer = new byte[1024];
		int length;
		try {
			while ((length = iStream.read(buffer)) != -1) {
				oStream.write(buffer,  0, length);
			}
		} finally {
			try {
				iStream.close();
			} catch (Exception ee) {
				
			}
			try {
				oStream.close();
			} catch (Exception ee) {
				
			}
		}
	}
	
	public static byte[] toByteArray(InputStream iStream) throws IOException {
		ByteArrayOutputStream oStream = new ByteArrayOutputStream(1024);
		byte[] buffer = new byte[1024];
		int length;
		try {
			while ((length = iStream.read(buffer)) != -1) {
				oStream.write(buffer,  0, length);
			}
			return oStream.toByteArray();
		} finally {
			try {
				iStream.close();
			} catch (Exception ee) {
				
			}
			try {
				oStream.close();
			} catch (Exception ee) {
				
			}
		}
		
	}
	
	public static File getFile(String dir, String file) throws IOException {
		return getFile(new File(dir), file);
		
	}
	
	public static File getFile(File dir, String file) throws IOException {
		dir.mkdirs();
		for (int i=0; i<100000; i++) {
			String fileName = i == 0 ? file : new StringBuffer(file).append(".").append(i).toString();
			File result = new File(dir, fileName);
			if (result.createNewFile()) {
				return result;
			}
		}
		return null;
		
	}
	

}
