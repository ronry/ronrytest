package com.ronrytest.tools;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.html.dom.HTMLDocumentImpl;
import org.apache.xerces.xni.parser.XMLDocumentFilter;
import org.cyberneko.html.filters.ElementRemover;
import org.cyberneko.html.parsers.DOMFragmentParser;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.xml.sax.InputSource;

public class Tickets {

	// 付萍：星期一下午 // 章勤：星期五，三下午 // 赵宏利:星期二下午，星期四上午

	protected static String[] commonAttribute = new String[] { "style",
			"align", "valign", "class", "bgcolor", "background", "title" };

	protected static String[] divAttribute = new String[] { "style", "align",
			"valign", "class", "bgcolor", "background", "title" };

	protected static String[] fontAttribute = new String[] { "style", "align",
			"valign", "class", "bgcolor", "background", "title", "color",
			"size", "face", "size" };

	protected static String[] tableAttribute = new String[] { "style", "align",
			"valign", "class", "bgcolor", "background", "title", "border",
			"width", "height", "cellpadding", "cellspacing", "bordercolor",
			"blockquote" };

	protected static String[] tdAttribute = new String[] { "style", "align",
			"valign", "class", "bgcolor", "background", "title", "width",
			"height", "colspan", "rowspan" };

	protected static String[] aAttribute = new String[] { "style", "align",
			"valign", "bgcolor", "background", "title", "target", "name",
			"href" };

	private static HttpClient client = new HttpClient();

	private static Scanner sysScanner = new Scanner(System.in);

	private static String userName = "吕锦亮";

	private static List<String> doctorList = Arrays.asList("赵宏利");

	private static String verifyCode = "http://guahao.zjol.com.cn/VerifyCodeCH.aspx?0.913751189256934";

	// 330481198409300846 840930
	private static String loginFormat = "http://guahao.zjol.com.cn/ashx/LoginDefault.ashx?idcode=330722198412235738&pwd=19841223&txtVerifyCode={0}";
	// 1183
	private static String doctorListURl = "http://guahao.zjol.com.cn/DepartMent.Aspx?ID=1183";

	private static String ticketListUrl = "http://guahao.zjol.com.cn/ashx/gethy.ashx";

	private static String ticketListVerifyUrl = "http://guahao.zjol.com.cn/ashx/getyzm.aspx?t=yy&hyid=";

	private static String takeTicketUrl = "http://guahao.zjol.com.cn/ashx/TreadYuyue.ashx";

	// fqdlgc8 fqdlgc8
	private static String danymicKey = "fqdlgc8";

	private static long SLEEP_TIME = 50;

	public static List<TicketInfo> parserTicketList(String ticketListStr) {

		List<TicketInfo> result = new ArrayList<TicketInfo>();

		String realTicketStr = ticketListStr.substring(
				ticketListStr.indexOf("$") + 1, ticketListStr.lastIndexOf("#"));

		String[] ss = StringUtils.split(realTicketStr, '$');

		for (String s : ss) {
			String[] sss = StringUtils.split(s, '|');
			result.add(new TicketInfo(sss[0], sss[1], sss[2]));
		}

		return result;

	}

	static class TicketInfo {
		public final String dinamic;
		public final String xh;
		public final String qhsj;

		public TicketInfo(String dinamic, String xh, String qhsj) {
			super();
			this.dinamic = dinamic;
			this.xh = xh;
			this.qhsj = qhsj;
		}

		@Override
		public String toString() {
			return "TicketInfo [dinamic=" + dinamic + ", xh=" + xh + ", qhsj="
					+ qhsj + "]";
		}

	}

	/**
	 * @param args
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {

		doLogin();

		// 3 获取科室医生列表
		String doctorListSG = null;

		int sleeptime = 0;

		while (doctorListSG == null) {
			HttpMethod doctorListMethod = new GetMethod();
			doctorListMethod.setURI(new URI(doctorListURl, true));

			client.executeMethod(doctorListMethod);

			String s = doctorListMethod.getResponseBodyAsString();

			doctorListSG = getDoctorListSG(s);

			if (doctorListSG == null) {
				Thread.sleep(SLEEP_TIME);
				sleeptime += SLEEP_TIME;
				if (sleeptime % 3000 == 0) {
					System.out.println("no ticket,waiting......");
				}
			}
		}

		System.out.println(doctorListSG);

		// 4 获取某个医生可以订的票的列表
		PostMethod ticketListMethod = new PostMethod();
		ticketListMethod.setURI(new URI(ticketListUrl, true));
		ticketListMethod.addRequestHeader("Referer", doctorListURl);
		ticketListMethod.addParameter("sg", doctorListSG);

		client.executeMethod(ticketListMethod);
		String ticketListRes = ticketListMethod.getResponseBodyAsString();
		String[] ress = ticketListRes.split("#");
		String takeTicketSG = ress[ress.length - 1];
		System.out.println(ticketListRes);

		List<TicketInfo> tickets = parserTicketList(ticketListRes);

		TicketInfo ticket = null;
		for (int i = tickets.size() - 1; i >= 0; i--) {
			ticket = tickets.get(i);
			System.out.println("take ticket " + ticket.xh);
			getTicketVerifyCode(ticket);

			System.out.println("input ticket verify code .....");

			String ticketVerifyCode = sysScanner.nextLine();

			PostMethod takeTicketMethod = new PostMethod();
			takeTicketMethod.setURI(new URI(takeTicketUrl, true));
			takeTicketMethod.addRequestHeader("Referer", doctorListURl);

			takeTicketMethod.addParameter("sg", takeTicketSG);
			takeTicketMethod.addParameter("yzm", ticketVerifyCode);
			takeTicketMethod.addParameter(danymicKey, ticket.dinamic);
			takeTicketMethod.addParameter("qhsj", ticket.qhsj);
			takeTicketMethod.addParameter("xh", ticket.xh);

			client.executeMethod(takeTicketMethod);

			String response = takeTicketMethod.getResponseBodyAsString();

			System.out.println(response);
			if (StringUtils.contains(response, userName)) {
				System.out.println("订票成功！！！！");
				System.exit(0);
			}

		}

		System.out.println("订票失败！！！！");

	}

	private static String getDoctorListSG(String htmlStr) throws Exception {

		htmlStr = "<table"
				+ StringUtils.substringBetween(htmlStr, "<table", "</table>")
				+ "</table>";

		DOMFragmentParser parser = new DOMFragmentParser();

		ElementRemover remover = new ElementRemover();

		remover.acceptElement("b", commonAttribute);
		remover.acceptElement("i", commonAttribute);
		remover.acceptElement("u", commonAttribute);
		remover.acceptElement("br", commonAttribute);
		remover.acceptElement("hr", commonAttribute);
		remover.acceptElement("li", commonAttribute);
		remover.acceptElement("ul", commonAttribute);
		remover.acceptElement("h1", commonAttribute);
		remover.acceptElement("h3", commonAttribute);
		remover.acceptElement("h2", commonAttribute);
		remover.acceptElement("h4", commonAttribute);
		remover.acceptElement("h5", commonAttribute);

		remover.acceptElement("span", commonAttribute);
		remover.acceptElement("div", divAttribute);
		remover.acceptElement("p", commonAttribute);

		remover.acceptElement("a", aAttribute);
		remover.acceptElement("font", fontAttribute);
		remover.acceptElement("table", tableAttribute);
		remover.acceptElement("tr", tdAttribute);
		remover.acceptElement("td", tdAttribute);
		remover.acceptElement("blockquote", commonAttribute);
		remover.acceptElement("style", commonAttribute);

		remover.removeElement("script");
		remover.removeElement("head");
		remover.removeElement("select");

		StringWriter sw = new StringWriter();
		// create writer filter
		org.cyberneko.html.filters.Writer writer = new org.cyberneko.html.filters.Writer(
				sw, "utf-8");

		// setup filter chain
		XMLDocumentFilter[] filters = { remover, writer };

		parser.setProperty(
				"http://cyberneko.org/html/properties/default-encoding",
				"UTF-8");
		parser.setProperty("http://cyberneko.org/html/properties/filters",
				filters);

		HTMLDocument document = new HTMLDocumentImpl();
		DocumentFragment fragment = document.createDocumentFragment();
		InputSource is = new InputSource(new StringReader(htmlStr));

		is.setEncoding("GBK");
		parser.parse(is, fragment);

		NodeList nodeList = fragment.getChildNodes();

		Node tableNode = null;

		for (int i = 0; i < nodeList.getLength(); i++) {
			tableNode = nodeList.item(i);
			if (StringUtils
					.endsWithIgnoreCase("TABLE", tableNode.getNodeName())) {
				break;
			}
		}

		nodeList = tableNode.getChildNodes();

		NodeList currList = null;
		Node currNode = null;
		int trNum = 0;
		for (int i = 0; i < nodeList.getLength(); i++) {
			currNode = nodeList.item(i);
			if (StringUtils.endsWithIgnoreCase("TR", currNode.getNodeName())) {

				trNum++;
				if (trNum < 3) {
					continue;
				}

				int tdNum = 0;
				currList = currNode.getChildNodes();

				for (int j = 0; j < currList.getLength(); j++) {
					currNode = currList.item(j);
					if (StringUtils.endsWithIgnoreCase("TD",
							currNode.getNodeName())) {
						tdNum++;
						if (tdNum == 1) {
							String doctorName = getDoctorName(currNode);
							if (!doctorList.contains(doctorName)) {
								break;
							}
						} else {
							String sg = getSg(currNode);
							if (sg != null) {
								return sg;
							}
						}
					}
				}
			}
		}

		return null;
	}

	private static String getSg(Node currNode) {
		NodeList list = currNode.getChildNodes();

		Node node = null;
		for (int i = 0; i < list.getLength(); i++) {
			node = list.item(i);
			if ("A".equalsIgnoreCase(node.getNodeName())) {
				node = node.getAttributes().getNamedItem("href");
				if (node != null) {
					return StringUtils.substringBetween(node.getNodeValue(),
							"'", "'");
				}
			}
		}

		return null;

	}

	private static String getDoctorName(Node currNode) {

		NodeList list = currNode.getChildNodes();
		Node node = null;
		for (int i = 0; i < list.getLength(); i++) {
			node = list.item(i);
			if ("A".equalsIgnoreCase(node.getNodeName())) {
				return StringUtils.trim(node.getFirstChild().getFirstChild()
						.getNodeValue());
			}
		}
		return null;
	}

	private static void doLogin() throws Exception {
		// 1获取登入验证码
		HttpMethod method = new GetMethod();
		method.setURI(new URI(verifyCode, true));
		client.executeMethod(method);

		InputStream in = method.getResponseBodyAsStream();
		FileOutputStream out = new FileOutputStream(
				"/home/ronry/verifyCode.jpeg");
		int data = 0;
		while ((data = in.read()) != -1) {
			out.write(data);
		}
		System.out.println("input verify code .....");
		String verifyCode = sysScanner.nextLine();

		// 2登入
		String loginUrl = MessageFormat.format(loginFormat, verifyCode);
		HttpMethod loginMethod = new GetMethod();
		loginMethod.setURI(new URI(loginUrl, true));

		client.executeMethod(loginMethod);
	}

	private static void getTicketVerifyCode(TicketInfo ticket) throws Exception {
		int data = 0;
		HttpMethod ticketVerifyMethod = new GetMethod();
		ticketVerifyMethod.setURI(new URI(ticketListVerifyUrl + ticket.dinamic,
				true));
		ticketVerifyMethod.addRequestHeader("Referer", doctorListURl);

		client.executeMethod(ticketVerifyMethod);

		InputStream ticketVerifyIn = ticketVerifyMethod
				.getResponseBodyAsStream();
		FileOutputStream ticketVerifyOut = new FileOutputStream(
				"/home/ronry/ticketVerify.png");
		while ((data = ticketVerifyIn.read()) != -1) {
			ticketVerifyOut.write(data);
		}

		ticketVerifyOut.flush();
		ticketVerifyOut.close();
		ticketVerifyIn.close();
	}
}
