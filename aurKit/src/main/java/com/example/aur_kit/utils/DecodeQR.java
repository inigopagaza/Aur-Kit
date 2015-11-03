package com.example.aur_kit.utils;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import android.graphics.Bitmap;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

public class DecodeQR {
	
	public static ResultQR zxing(Mat mRgba) throws ChecksumException, FormatException{

	    Bitmap bMap = Bitmap.createBitmap(mRgba.width(), mRgba.height(), Bitmap.Config.ARGB_8888);
	    Utils.matToBitmap(mRgba, bMap);
	    int[] intArray = new int[bMap.getWidth()*bMap.getHeight()];  
	    //copy pixel data from the Bitmap into the 'intArray' array  
	    bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight());  
	    
	    LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(),intArray);
	    
	    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
	    Reader reader = new QRCodeMultiReader();  
	    
	    ResultQR qrResult = null;
	    try {

	        Result result = reader.decode(bitmap);
	        ResultPoint[] points = result.getResultPoints();
	      
	        ResultPoint a= points[1];
            ResultPoint b= points[2];
            
            double z = Math.abs(a.getX()-b.getX());
            double x = Math.abs(a.getY()-b.getY());
            double theta = Math.atan(x/z);
            theta = Math.toDegrees(theta);            
            
        	/* Caso normal
        	 * a-------
        	 *        |
        	 *        b
        	 * theta = 0 + theta;
        	 */	
            if((a.getX()<b.getX()) && (a.getY()>b.getY()))
            {
            	/*
            	 *        b
            	 *        |
            	 * a-------
            	 */
            	theta = 360 - theta;
            }
            else if((a.getX()>b.getX()) && (a.getY()<b.getY()))
            {
            	/*
            	 *        a
            	 *        |
            	 * b-------       
            	 */
            	theta = 180 - theta;
            }
            else if((a.getX()>b.getX()) && (a.getY()>b.getY()))
            {
            	/*
            	 * b-------
            	 *        |
            	 *        a
            	 */
            	theta = 180 + theta;
            }
	        qrResult = new ResultQR(result.getText(), theta);
	    }
	    catch (NotFoundException e) {
	            e.printStackTrace();
	    }
	    return qrResult;
	}
}
