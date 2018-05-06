package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerificationCodeUtil {

	//第一种
	public static void CreateVerificationCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//绘图
		//step1.创建一个映像(画布)
		BufferedImage image = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);//宽度，高度，类型
		//step2.获得画笔
		//此方法返回 Graphics2D，但此处是出于向后兼容性的考虑。createGraphics 更为方便，因为它被声明为返回 Graphics2D。
		Graphics g=image.getGraphics();
		//给画笔设置颜色
		g.setColor(new Color(255,255,255));//白色
		//step4.给画布设置颜色
		g.fillRect(0, 0, 80, 30);
		//step5.生成一个随机数
		Random r=new Random();
		String number=getNumber(5);
		
		/**绑定number到session*/
		HttpSession session=request.getSession();
		session.setAttribute("PINs", number);
		
		//step6.将随机数绘制到画布上
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.setFont(new Font(null,Font.ITALIC,20));
			//绘制由指定 string 给定的文本,最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处
		g.drawString(number, 5, 20);
		//step7.加干扰线
		for(int i=0;i<10;i++){
			g.setColor(new Color
					(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(80), 
					r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		//压缩图片，输出
		//step1.设置服务器返回的数据的数据类型
		response.setContentType("image/jpeg");
		//step2.获得输出流
		OutputStream os=response.getOutputStream();
		//step3.压缩图片并输出
		javax.imageio.ImageIO.write(image, "jpeg", os);
		os.close();
		
	}
	
	private static String getNumber(int size){
		String number="";
		String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random r=new Random();
		for(int i=0;i<size;i++){
			number +=chars.charAt(r.nextInt(chars.length()));
		}
		return number;	
	}
	
	
	//第二种
	private static final String[] chars = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H","J","K","L","M","N","P"};
	private static final int SIZE = 5;//字符长度
	private static final int LINES = 7;//干扰线
	private static final int WIDTH = 100;
	private static final int HEIGHT = 50;
	private static final int FONT_SIZE = 30;//字体大小
	
	public static Map<String,BufferedImage> CreateVerificationCode2() {
		StringBuffer sb = new StringBuffer();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics();
		graphic.setColor(Color.LIGHT_GRAY);
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		Random ran = new Random();
		//画随机字符
		for(int i=1;i<=SIZE;i++){
			
			int r = ran.nextInt(chars.length);
			graphic.setColor(getRandomColor());
			graphic.setFont(new Font(null,Font.BOLD+Font.ITALIC,FONT_SIZE));
			graphic.drawString(chars[r],(i-1)*WIDTH/SIZE , HEIGHT/2);
			//将字符保存，存入Session
			sb.append(chars[r]);
		}
		//画干扰线
		for(int i=1;i<=LINES;i++){
			graphic.setColor(getRandomColor());
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH),ran.nextInt(HEIGHT));
		}
		Map<String,BufferedImage> map = new HashMap<String,BufferedImage>();
		map.put(sb.toString(), image);
		return map;
	}
	
	public static Color getRandomColor(){
		Random ran = new Random();
		Color color = new Color(ran.nextInt(156),ran.nextInt(156),ran.nextInt(156));
		return color;
	}
	
	
	
	
}
