<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<%@ page import="java.awt.*,javax.imageio.*,java.awt.image.*"%>
<%!Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	String charsLong = "23456789abcdefghjklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
	String charsShort = "0123456789";
	String charss = charsLong;
	int charsLength = charss.length();
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	int width = 60;
	int height = 20;
	BufferedImage image = new BufferedImage(width, height, 8);
	Graphics g = image.getGraphics();
	Random random = new Random();
	g.setColor(getRandColor(200, 250));
	g.fillRect(0, 0, width, height);
	g.setFont(new Font("Arial", Font.PLAIN, 18));
	g.setColor(getRandColor(100, 199));
	//画边框
	g.drawRect(0, 0, width - 1, height - 1);
	//画155条干扰线
	for (int i = 0; i < 155; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int x1 = random.nextInt(12);
		int y1 = random.nextInt(12);
		g.drawLine(x, y, x + x1, y + y1);
	}
	//随机产生随机码
	String str = "";
	String[] fontNames = { "Times New Roman", "Arial", "Book antiqua",
			"隶书" };
	for (int i = 0; i < 4; i++) {
		g.setFont(new Font(fontNames[random.nextInt(4)], Font.PLAIN, 18));
		String rand = String.valueOf(charss.charAt(random
				.nextInt(charsLength)));
		str += rand;
		//将图片显示到图片中
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
				.nextInt(110), 20 + random.nextInt(110)));
		g.drawString(rand, 13 * i + 6, 16);
	}
	session.setAttribute("str", str);
	g.dispose();
	ImageIO.write(image, "jpeg", response.getOutputStream());
	out.clear();
	out = pageContext.pushBody();
%>


