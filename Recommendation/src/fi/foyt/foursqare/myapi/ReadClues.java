package fi.foyt.foursqare.myapi;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadClues {
	// public ReadClues() {
	// super();
	// TODO Auto-generated constructor stub
	// }

	Document doc;
	ArrayList<Clue> clues;
	public static String urlXml = "";

	public ReadClues(String gameNumber, String url) {
		urlXml = url;
		// "http://192.168.10.109:81/ReturnGame.aspx?gameNumber="
		// + gameNumber + "&username=petar";
		// System.out.println(urlXml);
	}

	// public ReadClues(String gameNumber, String path) {
	// if(path!=null){
	// urlXml = path;
	// }else {
	// urlXml = "http://192.168.10.109:81/ReturnGame.aspx?gameNumber="
	// + gameNumber + "&username=petar";
	// }
	//
	//
	// // System.out.println(urlXml);
	// }

	public String isPublic() {
		Node game = doc.getElementsByTagName("game").item(0);
		return game.getAttributes().getNamedItem("isPublic").getTextContent();
	}

	public String getDuration() {
		Node game = doc.getElementsByTagName("game").item(0);
		return game.getAttributes().getNamedItem("duration").getTextContent();
	}

	public String returnCategoriesCount(String[] categories) {
		int[] categoriesCounts = { 0, 0, 0, 0 };
		for (int i = 0; i < categories.length; i++) {
			String s = categories[i];
			int k = 1;
			if (i == categories.length - 1) {
				k = 2;
			}
			switch (s) {
			case "bussines":

				categoriesCounts[0] += k;
				break;
			case "social":
				categoriesCounts[1] += k;
				break;
			case "travel":
				categoriesCounts[2] += k;
				break;
			case "irrelevant":
				categoriesCounts[3] += k;
				break;
			default:
				break;
			}
		}

		// urlXml = "http://192.168.10.109:81/ReturnGame.aspx?gameNumber="
		// + categories[0] + "&username=petar";
		 readXML();
		return categoriesCounts[0] + "," + categoriesCounts[1] + ","
				+ categoriesCounts[2] + "," + categoriesCounts[3] + ","
				+ isPublic() + "," + getDuration() + "," + categories[1] + "\n";
	}

	public void readXML() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			URL url = new URL(urlXml);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			doc = db.parse(conn.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Clue> returnAllClues() {
		readXML();
		ArrayList<Clue> cluesList = new ArrayList<Clue>();
		Element game = (Element) doc.getElementsByTagName("game").item(0);
		NodeList clues = game.getElementsByTagName("clue");
		for (int i = 0; i < clues.getLength(); i++) {
			Node clue = clues.item(i);
			String radius = clue.getChildNodes().item(1).getAttributes()
					.item(2).getTextContent();
			String lat = clue.getChildNodes().item(3).getAttributes().item(0)
					.getTextContent();
			String lng = clue.getChildNodes().item(3).getAttributes().item(1)
					.getTextContent();

			cluesList.add(new Clue(lat, lng, radius));
		}
		return cluesList;
	}


	public void printAllClues() {
		ArrayList<Clue> clues = returnAllClues();
		for (Clue c : clues) {
			System.out.println(c.radius);
		}
	}
}
