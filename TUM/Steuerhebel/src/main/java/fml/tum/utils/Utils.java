package fml.tum.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Utils 
{
	/**
	 * Returns a float from an payload array ordered in big endian.
	 * @param payload
	 * @param startingIndex
	 * @param bytes Size in bytes of the info
	 * @param byteOrder 
	 * @return
	 */
	public static float getFloat(byte[] payload, int startingIndex, int bytes, ByteOrder byteOrder) {
		byte[] temp = new byte[bytes];
		System.arraycopy(payload, startingIndex, temp, 0, bytes);
		ByteBuffer wrapped = ByteBuffer.wrap(temp);
		wrapped.order(ByteOrder.BIG_ENDIAN);
		return wrapped.getFloat();
	}
	
	/**
	 * Returns a float from an payload array ordered in big endian.
	 * @param payload
	 * @param startingIndex
	 * @param bytes Size in bytes of the info
	 * @param byteOrder 
	 * @return
	 */
	public static int getInt(byte[] payload, int startingIndex, int bytes,ByteOrder byteOrder) {
		byte[] temp = new byte[bytes];
		System.arraycopy(payload, startingIndex, temp, 0, bytes);
		ByteBuffer wrapped = ByteBuffer.wrap(temp);
		wrapped.order(byteOrder);
		return wrapped.getInt();
	}
	
	/**
	 * Returns a float from an payload array ordered in big endian.
	 * @param payload
	 * @param startingIndex
	 * @param bytes Size in bytes of the info
	 * @param byteOrder 
	 * @return
	 */
	public static short getShort(byte[] payload, int startingIndex, int bytes,ByteOrder byteOrder) {
		byte[] temp = new byte[bytes];
		System.arraycopy(payload, startingIndex, temp, 0, bytes);
		ByteBuffer wrapped = ByteBuffer.wrap(temp);
		wrapped.order(byteOrder);
		return wrapped.getShort();
	}
	
	public static float getWeirdUnsignedFloat(byte[] payload, int startingIndex, int bytes,ByteOrder byteOrder) {
		byte[] temp = new byte[bytes];
		System.arraycopy(payload, startingIndex, temp, 0, bytes);
		
		ByteBuffer wrapped = ByteBuffer.wrap(temp);
		wrapped.order(byteOrder);
		byte[] temp2 = new byte[bytes];
		wrapped.get(temp2);
		float ta = (float)mergeBytesToShort(temp2)/100.0f;
		return ta;
	}
	
	public static short mergeBytesToShort(byte[] bytes) {
		int retval = (0x0000FF00 & (bytes[0] << 8));
		retval = retval | (0x000000FF & bytes[1]);
		
		return((short)retval);
	}

	public static float getFloatUnsigned(byte[] payload, int startingIndex, int bytes,ByteOrder byteOrder) {
		//.getInt() & 0xffffffffL
		byte[] temp = new byte[bytes];
		System.arraycopy(payload, startingIndex, temp, 0, bytes);
		ByteBuffer wrapped = ByteBuffer.wrap(temp);
		wrapped.order(byteOrder);
		return wrapped.getShort() & 0xffffffffL;
	}

	public static int getIntUnsigned(byte[] payload, int startingIndex, int bytes,ByteOrder byteOrder) {
		//.getInt() & 0xffffffffL
		byte[] temp = new byte[bytes];
		System.arraycopy(payload, startingIndex, temp, 0, bytes);
		ByteBuffer wrapped = ByteBuffer.wrap(temp);
		wrapped.order(byteOrder);
		return (int) (wrapped.getInt() & 0xffffffffL);
	}
	
}
