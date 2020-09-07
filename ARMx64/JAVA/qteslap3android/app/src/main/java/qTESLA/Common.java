/******************************************************************************
* qTESLA: An Efficient Post-Quantum Signature Scheme Based on the R-LWE Problem
*
* Common Functions
* 
* @author Yinhua Xu
*******************************************************************************/

package qTESLA;

import java.util.Arrays;

public class Common {
	
	/***********************************************************************************
	 * Description:	Convert A String to A Byte Array
	 * 
	 * @param		string			The String to Be Converted
	 * 
	 * @return		byteArray		The Generated Byte Array			
	 ***********************************************************************************/
	public static byte[] hexadecimalStringToByteArray (String string) {
		
		byte[] byteArray = new byte[string.length() / 2];
		
		for (int i = 0; i < byteArray.length; i++) {
			
			int index		= i * 2;
			int value		= Integer.parseInt(string.substring (index, index + 2), 16);
			byteArray[i]	= (byte) value;
			
		}
		
		return byteArray;
		
	}
	
	/**********************************************************************************************************
	 * Description:	Checks Whether the Two Parts of Arrays are Equal to Each Other
	 * 
	 * @param		left			Left Array
	 * @param		leftOffset		Starting Point of the Left Array
	 * @param		right			Right Array
	 * @param		rightOffset		Starting Point of the Right Array
	 * @param		length			Length to be Compared from the Starting Point
	 * 
	 * @return		true			Equal
	 *				false			Different
	 ***********************************************************************************************************/
	public static boolean memoryEqual (byte[] left, int leftOffset, byte[] right, int rightOffset, int length) {
		
		if ((leftOffset + length <= left.length) && (rightOffset + length <= right.length)) {
			
			for (int i = 0; i < length; i++) {
				
				if (left[leftOffset + i] != right[rightOffset + i]) {
					
					return false;
					
				}
				
			}
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	/*********************************************************************************
	 * Description:	Converts 2 Consecutive Bytes in "load" to A Number of "Short" from
	 *				A Known Position
	 * 
	 * @param		source			Source Array
	 * @param		sourceOffset	Starting Position
	 * 
	 * @return		A Number of "Short"
	 *********************************************************************************/
	public static short load16 (final byte[] source, int sourceOffset) {
		
		short number = 0;
		
		if (source.length - sourceOffset >= Short.SIZE / Byte.SIZE) {
		
			for (int i = 0; i < Short.SIZE / Byte.SIZE; i++) {
			
				number ^= (short) (source[sourceOffset + i] & 0xFF) << (Byte.SIZE * i);
			
			}
		
		} else {
			
			for (int i = 0; i < source.length - sourceOffset; i++) {
				
				number ^= (short) (source[sourceOffset + i] & 0xFF) << (Byte.SIZE * i);
			
			}
			
		}
		
		return number;
		
	}
	
	/******************************************************************************
	 * Description:	Converts 4 Consecutive Bytes in "load" to A Number of "Integer"
	 *				from A Known Position
	 * 
	 * @param		source			Source Array
	 * @param		sourceOffset	Starting Position
	 * 
	 * @return		A Number of "Integer"
	 ******************************************************************************/
	public static int load32 (final byte[] source, int sourceOffset) {
		
		int number = 0;
		
		if (source.length - sourceOffset >= Integer.SIZE / Byte.SIZE) {
		
			for (int i = 0; i < Integer.SIZE / Byte.SIZE; i++) {
			
				number ^= (int) (source[sourceOffset + i] & 0xFF) << (Byte.SIZE * i);
			
			}
		
		} else {
			
			
			for (int i = 0; i < source.length - sourceOffset; i++) {
				
				number ^= (int) (source[sourceOffset + i] & 0xFF) << (Byte.SIZE * i);
				
			}
			
		}
		
		return number;
		
	}
	
	/**********************************************************************************
	 * Description:	Converts 8 Consecutive Bytes in "load" to A Number of "Long" from
	 *				A Known Position
	 * 
	 * @param		source			Source Array
	 * @param		sourceOffset	Starting Position
	 * 
	 * @return		A Number of "Long"
	 *********************************************************************************/
	public static long load64_MB (final byte[] source, int sourceOffset) {
		long r = 0;		
		
		for (int i = 0; i < 8; ++i) {
			long temp = ((long)source[i + sourceOffset]) & 0xFF;
			temp = temp << 8 * i;
			r |= temp;		
		}		
		return r;
	}
	
	
	public static long load64 (final byte[] source, int sourceOffset) {
		
		long number = 0L;
		
		if (source.length - sourceOffset >= Long.SIZE / Byte.SIZE) {
		
			for (int i = 0; i < Long.SIZE / Byte.SIZE; i++) {
			
				number ^= (long) (source[sourceOffset + i] & 0xFF) << (Byte.SIZE * i);
		
			}
		
		} else {
		
			for (int i = 0; i < source.length - sourceOffset; i++) {
			
				number ^= (long) (source[sourceOffset + i] & 0xFF) << (Byte.SIZE * i);
			
			}
		
		}
		
		return number;
		
	}
	
	/********************************************************************
	 * Description:	Converts 8N Consecutive Bytes in "load" to An Array
	 *				of N "Long" Numbers
	 * 
	 * @param		source			Source Array (byte)
	 * @param		destination		Destination Array (long)
	 * 
	 * @return		A Number of "Long"
	 ********************************************************************/
	public static void load64 (final byte[] source, long[] destination) {
		
		for (int i = 0; i < destination.length; i++) {
			
			destination[i] = load64 (source, Byte.SIZE * i);
			
		}
		
	}
	
	/*******************************************************************************************
	 * Description:	Converts A Number of "Short" to 2 Consecutive Bytes in "store" from A Known
	 *				Position
	 * 
	 * @param		destination				Destination Byte Array
	 * @param		destinationOffset		Starting position
	 * @param		source					Source Short Number
	 * 
	 * @return		none
	 *******************************************************************************************/
	public static void store16 (byte[] destination, int destinationOffset, final short source) {
		
		if (destination.length - destinationOffset >= Short.SIZE / Byte.SIZE) {
		
			for (int i = 0; i < Short.SIZE / Byte.SIZE; i++) {
			
				destination[destinationOffset + i] = (byte) ((source >> (Byte.SIZE * i)) & 0xFF);
			
			}
		
		} else {
			
			for (int i = 0; i < destination.length - destinationOffset; i++) {
				
				destination[destinationOffset + i] = (byte) ((source >> (Byte.SIZE * i)) & 0xFF);
			
			}
			
		}
		
	}
	
	/********************************************************************************************
	 * Description:	Converts A Number of "Integer" to 4 Consecutive Bytes in "store" from A Known
	 * 				Position
	 * 
	 * @param		destination				Destination Byte Array
	 * @param		destinationOffset		Starting Position
	 * @param		source					Source Integer Number
	 * 
	 * @return		none
	 ********************************************************************************************/
	public static void store32 (byte[] destination, int destinationOffset, final int source) {
		
		if (destination.length - destinationOffset >= Integer.SIZE / Byte.SIZE) {
		
			for (int i = 0; i < Integer.SIZE / Byte.SIZE; i++) {
			
				destination[destinationOffset + i] = (byte) ((source >> (Byte.SIZE * i)) & 0xFF);
			
			}
		
		} else {
			
			for (int i = 0; i < destination.length - destinationOffset; i++) {
				
				destination[destinationOffset + i] = (byte) ((source >> (Byte.SIZE * i)) & 0xFF);
				
			}
			
		}
		
	}
	
	/********************************************************************************************
	 * Description:	Converts A Number of "Long" to 8 Consecutive Bytes in "store" from A Known
	 * 				Position
	 * 
	 * @param		destination				Destination Byte Array
	 * @param		destinationOffset		Starting Position
	 * @param		source					Source Long Number
	 * 
	 * @return		none
	 ********************************************************************************************/
	public static void store64 (byte[] destination, int destinationOffset, final long source) {
		
		if (destination.length - destinationOffset >= Long.SIZE / Byte.SIZE) {
		
			for (int i = 0; i < Long.SIZE / Byte.SIZE; i++) {
				byte testb = (byte) (source >> (Byte.SIZE * i));
				destination[destinationOffset + i] = (byte) ((source >> (Byte.SIZE * i)) & 0xFFL);

			
			}
		
		} else {
			
			for (int i = 0; i < destination.length - destinationOffset; i++) {				
				destination[destinationOffset + i] = (byte) ((source >> (Byte.SIZE * i)) & 0xFFL);			
			}
			
		}
		
	}
	
	/*********************************************************************
	 * Description:	Converts An Array of N "Long" Numbers to 8N
	 * 				Consecutive Bytes in "store"
	 * 
	 * @param		destination				Destination Byte Array
	 * @param		source					Source Long Number
	 * 
	 * @return		none
	 *********************************************************************/
	public static void store64 (byte[] destination, final long[] source) {
		
		for (int i = 0; i < source.length; i++) {
			
			store64 (destination, Byte.SIZE * i, source[i]);
			
		}
		
	}
	
	/**************************************************
	 * Description:	Computes Absolute Value
	 * 
	 * @param	value		Original Value
	 * 
	 * @return	Absolute Value of "value"
	 **************************************************/
	public static int absolute (int value) {
		
		return ((value >> 31) ^ value) - (value >> 31);
		
	}
	
	/*****************************************************************
	 * Description:	Computes the Average Number of An Array
	 * 
	 * @param		array			Target Array
	 * 
	 * @return		average			Average Number of the Target Array
	 *****************************************************************/
	public static double averageNumber (double[] array) {
		
		double sum = 0.0;
		
		for (int i = 0; i < array.length; i++) {
			
			sum += array[i];
			
		}
		
		double average = sum / array.length;
		
		return average;
		
	}
	
	/*****************************************************************
	 * Description:	Computes the Total Sum of An Array
	 * 
	 * @param		array			Target Array
	 * 
	 * @return		average			Sum of the Target Array
	 *****************************************************************/
	public static double sumNumber (double[] array) {
		
		double sum = 0.0;
		
		for (int i = 0; i < array.length; i++) {
			
			sum += array[i];
			
		}
		
		return sum;
		
	}	
	
	
	/****************************************************************************
	 * Description:	Computes the Median Number of An Array
	 * 
	 * @param		array			Target Array
	 * 
	 * @return		median			Median Number of the Target Array
	 ****************************************************************************/
	public static double medianNumber (double[] array) {
		
		double median = 0.0;
		
		Arrays.sort(array);
		
		if (array.length % 2 == 0) {
			
			median = (array[array.length / 2 - 1] + array[array.length / 2]) / 2;
			
		} else {
			
			median = array[array.length / 2];
			
		}
		
		return median;
		
	}
	
	public static void printBin64 (long num) {
		System.out.println(String.format("%64s", Long.toBinaryString(num)).replace(' ', '0'));
	}
	
	public static void printBin32 (int num) {
		System.out.println(String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0'));
	}
	
	public static void printBin8 (Byte num) {
		System.out.println(String.format("%8s", Integer.toBinaryString(num)).replace(' ', '0'));
	}
	
}