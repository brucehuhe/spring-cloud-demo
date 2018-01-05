package test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocFlavor.SERVICE_FORMATTED;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;

/**
 * 建立与打印机的连接
 * 
 * @author Administrator
 * 
 */
public class PrintDemo {

	public static void main(String[] args) {
		FileInputStream textStream = null;
		try {
			textStream = new FileInputStream("D:\\tt.pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 String printStr = "打印测试内容";// 获取需要打印的目标文本
		if (printStr != "") // 当打印内容不为空时
		{
			InputStream inputStream = new ByteArrayInputStream(printStr.getBytes()); 
			// 指定打印输出格式
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;// SERVICE_FORMATTED.PRINTABLE,DocFlavor.INPUT_STREAM.AUTOSENSE
			
			
			// 构建打印请求属性集
			HashPrintRequestAttributeSet prass = new HashPrintRequestAttributeSet();
			//查询所有可用的打印机
			PrintService printServices[] = PrintServiceLookup.lookupPrintServices(flavor, prass);
			for(int i=0;i<printServices.length;i++) {
				System.out.println(printServices[i].getName());
			}
			// 定位默认的打印服务
			PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
			System.out.println(printService.getName());
			// 创建打印作业
			DocPrintJob job = printService.createPrintJob();
			// 设置打印属性
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			// 设置纸张大小,也可以新建MediaSize类来自定义大小
			pras.add(MediaSizeName.ISO_A4);
			DocAttributeSet das = new HashDocAttributeSet();
			// 指定打印内容
			Doc doc = new SimpleDoc(textStream, flavor, das);
			// 不显示打印对话框，直接进行打印工作
			try {
				job.print(doc, pras); // 进行每一页的具体打印操作
			} catch (PrintException pe) {
				pe.printStackTrace();
			}
		} else {
			// 如果打印内容为空时，提示用户打印将取消
			JOptionPane.showConfirmDialog(null, "Sorry, Printer Job is Empty, Print Cancelled!", "Empty",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
	}
}