import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.print.Book;
import java.awt.print.Printable;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;

public class MyPrintTest implements Printable {
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// 转换成Graphics2D
		Graphics2D g2 = (Graphics2D) graphics;
		// 设置打印颜色为黑色
		g2.setColor(Color.black);
		// 打印起点坐标
		// double x = pf.getImageableX();
		// double y = pf.getImageableY();
		switch (pageIndex) {
		case 0:
			// 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
			Font font = new Font("新宋体", Font.PLAIN, 11);
			g2.setFont(font);// 设置字体
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
			String dateString = formatter.format(date);
			g2.drawString("根据《中华人民共和国国境卫生检疫法》及其实施细则和有关规定，上述物品已经卫", 150, 435);
			g2.drawString("生处理，特发此证。", 138, 445);
			g2.drawString("日期", 135, 479);
			g2.drawString(dateString, 182, 479);
			g2.drawString("药剂及浓度", 313, 478);
			g2.drawString("Date", 135, 492);
			g2.drawString("Chemical and Concentration", 313, 492);
			g2.drawString("处理方法", 135, 508);
			g2.drawString("持续时间及温度", 313, 508);
			g2.drawString("Duration and Temperature", 313, 528);
			g2.drawString("*******", 313, 555);
			g2.drawString("深圳", 259, 740);
			g2.drawString(dateString, 467, 737);
			return PAGE_EXISTS;
		default:
			return NO_SUCH_PAGE;
		}
	}

	public static void main(String[] args) {
		MyPrintTest myprinttest = new MyPrintTest();
		myprinttest.print();
	}

	public void print() {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		Book book = new Book();
		book.append(new MyPrintTest(), printerJob.defaultPage());
		printerJob.setPageable(book);
		HashAttributeSet hs = new HashAttributeSet();
		String printerName = "迅捷PDF虚拟打印机";
		hs.add(new PrinterName(printerName, null));
		PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, hs);
		if (pss.length == 0) {
			System.out.println("无法找到打印机:" + printerName);
			return;
		}
		try {
			printerJob.setPrintService(pss[0]);
			printerJob.print();
		} catch (PrinterException ex) {
			System.out.println(ex.getMessage());
		}
	}
}