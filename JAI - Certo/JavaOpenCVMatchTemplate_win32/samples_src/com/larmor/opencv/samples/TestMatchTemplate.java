package com.larmor.opencv.samples;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.larmor.opencv.MatchTemplate;

/**
 * Test class for com.larmor.opencv.MatchTemplate
 * 
 * @author  	Pier Paolo Ciarravano Larmor  
 * License: 	GNU General Public License   
 * @version  	Vers. 0.5 beta (March.2008) 
 */
public class TestMatchTemplate {

	
	//Run using: java -Xms32m -Xmx128m
	public static void main(String[] args) throws IOException {
		System.out.println("START TestMatchTemplate...");
		
		String source = "C:\\Test\\test.jpg";
		String template = "C:\\Test\\mark2.jpg";
		
		RenderedImage sourceImg = ImageIO.read(new File(source));
		RenderedImage templateImg = ImageIO.read(new File(template));
		
		BufferedImage sourceImgBI = MatchTemplate.getGrayBufferedImage(sourceImg);
		BufferedImage templateImgBI = MatchTemplate.getGrayBufferedImage(templateImg);
		
		MatchTemplate.displayBufferedImage(templateImgBI, "template");
		
		MatchTemplate matchObj = new MatchTemplate(sourceImgBI, templateImgBI);
		
		//Left top
		Point p1 = matchObj.matchTemplate(0,0,200,200,MatchTemplate.CV_TM_CCOEFF_NORMED);
		System.out.println("matchTemplateBestPoint 1: CV_TM_CCOEFF_NORMED: <"+p1.x+","+p1.y+">");
		MatchTemplate.displayBufferedImageCrossPoint(MatchTemplate.getImageGrayFromImage(sourceImgBI,0,0,200,200), "input 1", p1, 0, 0);
		MatchTemplate.displayBufferedImageCrossPoint(matchObj.getMatchImage(), "result 1", p1, 0, 0);
					
		//Right top 
		Point p2 = matchObj.matchTemplate(sourceImgBI.getWidth()-200,0,200,200, MatchTemplate.CV_TM_CCOEFF_NORMED);
		System.out.println("matchTemplateBestPoint 2: CV_TM_CCOEFF_NORMED: <"+p2.x+","+p2.y+">");
		MatchTemplate.displayBufferedImageCrossPoint(MatchTemplate.getImageGrayFromImage(sourceImgBI,sourceImgBI.getWidth()-200,0,200,200), "input 2", p2, sourceImgBI.getWidth()-200,0);
		MatchTemplate.displayBufferedImageCrossPoint(matchObj.getMatchImage(), "result 2", p2, sourceImgBI.getWidth()-200,0);
				
		//Right bottom
		Point p3 = matchObj.matchTemplate(sourceImgBI.getWidth()-200,sourceImgBI.getHeight()-200,200,200, MatchTemplate.CV_TM_CCOEFF_NORMED);
		System.out.println("matchTemplateBestPoint 3: CV_TM_CCOEFF_NORMED: <"+p3.x+","+p3.y+">");
		MatchTemplate.displayBufferedImageCrossPoint(MatchTemplate.getImageGrayFromImage(sourceImgBI,sourceImgBI.getWidth()-200,sourceImgBI.getHeight()-200,200,200), "input 3", p3, sourceImgBI.getWidth()-200,sourceImgBI.getHeight()-200);
		MatchTemplate.displayBufferedImageCrossPoint(matchObj.getMatchImage(), "result 3", p3, sourceImgBI.getWidth()-200,sourceImgBI.getHeight()-200);
		
		//Left Bottom
		Point p4 = matchObj.matchTemplate(0,sourceImgBI.getHeight()-200,200,200, MatchTemplate.CV_TM_CCOEFF_NORMED);
		System.out.println("matchTemplateBestPoint 4: CV_TM_CCOEFF_NORMED: <"+p4.x+","+p4.y+">");
		MatchTemplate.displayBufferedImageCrossPoint(MatchTemplate.getImageGrayFromImage(sourceImgBI,0,sourceImgBI.getHeight()-200,200,200), "input 4", p4, 0,sourceImgBI.getHeight()-200);
		MatchTemplate.displayBufferedImageCrossPoint(matchObj.getMatchImage(), "result 4", p4, 0,sourceImgBI.getHeight()-200);
				
		//Left top		
		Point p11 = matchObj.matchTemplateBestPoint(0,0,200,200,MatchTemplate.CV_TM_SQDIFF);
		System.out.println("matchTemplateBestPoint 1: CV_TM_SQDIFF: <"+p11.x+","+p11.y+">");
		Point p12 = matchObj.matchTemplateBestPoint(0,0,200,200,MatchTemplate.CV_TM_SQDIFF_NORMED);
		System.out.println("matchTemplateBestPoint 1: CV_TM_SQDIFF_NORMED: <"+p12.x+","+p12.y+">");
		Point p13 = matchObj.matchTemplateBestPoint(0,0,200,200,MatchTemplate.CV_TM_CCORR);
		System.out.println("matchTemplateBestPoint 1: CV_TM_CCORR: <"+p13.x+","+p13.y+">");
		Point p14 = matchObj.matchTemplateBestPoint(0,0,200,200,MatchTemplate.CV_TM_CCORR_NORMED);
		System.out.println("matchTemplateBestPoint 1: CV_TM_CCORR_NORMED: <"+p14.x+","+p14.y+">");
		Point p15 = matchObj.matchTemplateBestPoint(0,0,200,200,MatchTemplate.CV_TM_CCOEFF);
		System.out.println("matchTemplateBestPoint 1: CV_TM_CCOEFF: <"+p15.x+","+p15.y+">");
		Point p16 = matchObj.matchTemplateBestPoint(0,0,200,200,MatchTemplate.CV_TM_CCOEFF_NORMED);
		System.out.println("matchTemplateBestPoint 1: CV_TM_CCOEFF_NORMED (NCC): <"+p16.x+","+p16.y+">");
		
		System.out.println("\nBenchmark global image:");
		long startTime1 = System.currentTimeMillis();
		Point ptot1 = matchObj.matchTemplateBestPoint(MatchTemplate.CV_TM_CCOEFF_NORMED);
		System.out.println("matchTemplateBestPoint ptot: CV_TM_CCOEFF_NORMED (NCC): <"+ptot1.x+","+ptot1.y+">");
		long totalTime1 = System.currentTimeMillis() - startTime1;
		System.out.println("totalTime for ptot using CV_TM_CCOEFF_NORMED (NCC) global image ("+sourceImgBI.getWidth()+"x"+sourceImgBI.getHeight()+"): millisec:" + totalTime1);
		
		System.out.println("\nBenchmark piece image:");
		long startTime2 = System.currentTimeMillis();
		Point ptot2 = matchObj.matchTemplateBestPoint(0,0,200,200,MatchTemplate.CV_TM_CCOEFF_NORMED);
		System.out.println("matchTemplateBestPoint ptot: CV_TM_CCOEFF_NORMED (NCC): <"+ptot2.x+","+ptot2.y+">");
		long totalTime2 = System.currentTimeMillis() - startTime2;
		System.out.println("totalTime for ptot using CV_TM_CCOEFF_NORMED (NCC) piece image ("+200+"x"+200+"): millisec:" + totalTime2);
	}

}
