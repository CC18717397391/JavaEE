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

	//��һ��
	public static void CreateVerificationCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//��ͼ
		//step1.����һ��ӳ��(����)
		BufferedImage image = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);//��ȣ��߶ȣ�����
		//step2.��û���
		//�˷������� Graphics2D�����˴��ǳ����������ԵĿ��ǡ�createGraphics ��Ϊ���㣬��Ϊ��������Ϊ���� Graphics2D��
		Graphics g=image.getGraphics();
		//������������ɫ
		g.setColor(new Color(255,255,255));//��ɫ
		//step4.������������ɫ
		g.fillRect(0, 0, 80, 30);
		//step5.����һ�������
		Random r=new Random();
		String number=getNumber(5);
		
		/**��number��session*/
		HttpSession session=request.getSession();
		session.setAttribute("PINs", number);
		
		//step6.����������Ƶ�������
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.setFont(new Font(null,Font.ITALIC,20));
			//������ָ�� string �������ı�,������ַ��Ļ���λ�ڴ�ͼ������������ϵ�� (x, y) λ�ô�
		g.drawString(number, 5, 20);
		//step7.�Ӹ�����
		for(int i=0;i<10;i++){
			g.setColor(new Color
					(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(80), 
					r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		//ѹ��ͼƬ�����
		//step1.���÷��������ص����ݵ���������
		response.setContentType("image/jpeg");
		//step2.��������
		OutputStream os=response.getOutputStream();
		//step3.ѹ��ͼƬ�����
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
	
	
	//�ڶ���
	private static final String[] chars = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H","J","K","L","M","N","P"};
	private static final int SIZE = 5;//�ַ�����
	private static final int LINES = 7;//������
	private static final int WIDTH = 100;
	private static final int HEIGHT = 50;
	private static final int FONT_SIZE = 30;//�����С
	
	public static Map<String,BufferedImage> CreateVerificationCode2() {
		StringBuffer sb = new StringBuffer();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics();
		graphic.setColor(Color.LIGHT_GRAY);
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		Random ran = new Random();
		//������ַ�
		for(int i=1;i<=SIZE;i++){
			
			int r = ran.nextInt(chars.length);
			graphic.setColor(getRandomColor());
			graphic.setFont(new Font(null,Font.BOLD+Font.ITALIC,FONT_SIZE));
			graphic.drawString(chars[r],(i-1)*WIDTH/SIZE , HEIGHT/2);
			//���ַ����棬����Session
			sb.append(chars[r]);
		}
		//��������
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
