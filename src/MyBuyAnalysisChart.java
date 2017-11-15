

import info.MyInformationTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import net.OmxData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import data.MyTimeData;
import event.EventManager;
import util.Formulas;
import util.MyUtil;
import util.MyXmlUtil;

public class MyBuyAnalysisChart extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static final String stocks[][] = new String[][]  {
		{"SE0000337842", "OMX30"     }, //Index
		{"SSE3966"     , "ABB"       }, //0
		{"SSE18634"    , "Alfa"      }, //1
		{"SSE402"      , "Assa"      }, //2
		{"SSE45"       , "Atlas"     }, //3
		{"SSE3524"     , "Astra"     }, //4
		{"SSE15285"    , "Boliden"   }, //5
		{"SSE81"       , "Electrolux"}, //6
		{"SSE101"      , "Ericsson"  }, //7
		{"SSE812"      , "Getinge"   }, //9
		{"SSE992"      , "Hennes"    }, //10
		{"SSE161"      , "Investor"  }, //11
		{"SSE22335"    , "Lundin Petrolium"    }, //12
		{"SSE3599"     , "MTG"       }, //13
		{"SSE220"      , "Nordea"    }, //14
		{"SSE39854"    , "Nokia"     }, //15
		{"SSE4928"     , "Sandvik"   }, //16
		{"SSE323"      , "SCA"       }, //17
		{"SSE261"      , "Scania"    }, //18
		{"SSE281"      , "SEB"       }, //19
		{"SSE401"      , "Securitas" }, //20
		{"SSE340"      , "Handels"   }, //21
		{"SSE283"      , "Skanska"   }, //22
		{"SSE285"      , "SKF"       }, //23
		{"SSE300"      , "SSAB"      }, //24
		{"SSE120"      , "Swedbank"  }, //25
		{"SSE361"      , "SwMatch"   }, //26
		{"SSE1027"     , "Tele2"     }, //27
		{"SSE5095"     , "Telia"     }, //28
		{"SSE366"      , "Volvo"     } //29	
		
/*		{"SSE861"      , "Fabege"    }, //8
		{"SSE966"      , "Castellum"    }, //8
		{"SSE806"      , "Elekta"    }, //8
		{"SSE32443"      , "Hakon Invest"    }, //8
		{"SSE819"      , "Hexagon"    }, //8
		{"SSE222"      , "Holmen"    }, //8
		{"SSE820"      , "Hufvudstaden"    }, //8
		{"SSE34915"      , "Husqvarna"    }, //8
		{"SSE143"      , "Industrivärden"    }, //8
		{"SSE999"      , "Kinnevik"    }, //8
		{"SSE914"      , "Latour"    }, //8
		{"SSE1011"      , "Atrium Ljundberg"    }, //8
		{"SSE27709"      , "Lundin Mining"    }, //8
		{"SSE1012"      , "Lundbergföretagen"    }, //8
		{"SSE917"      , "Meda"    }, //8
		{"SSE37472"      , "Melker"    }, //8
		{"SSE24507"      , "Millicom"    }, //8
		{"SSE838"      , "NCC"    }, //8
		{"SSE24227"      , "Oriflame"    }, //8
		{"SSE928"      , "Peab"    }, //8
		{"SSE1045"      , "Ratos"    }, //8
		{"SSE1051"      , "Saab"    }, //8
		//{"SSE84981"      , "Semafo"    }, //8
		{"SSE4025"      , "Tieto"    }, //8
		{"SSE364"      , "Trelleborg"    }, //8
		{"SSE945"      , "Wallenstam"    } //8 */
	};
	Document doc = null;

	public MyBuyAnalysisChart() {
		this.setBackground(Color.white);
		JButton button;
		this.add(button = new JButton("Refresh"), BorderLayout.SOUTH);

		EventManager.getInstance().registerAction(button, "buy.refresh");
		EventManager.getInstance().subscribeOnAction("buy.refresh", this);
	}

	public static Document fix(String name, String id, Document doc) throws Exception {
		Document newDoc = MyXmlUtil.createEmptyDocument();

		Element node = newDoc.createElement("stocks");
		newDoc.appendChild(node);

		Element node2 = newDoc.createElement("stock");
		node2.setAttribute("name", name);
		node2.setAttribute("id", id);
		node.appendChild(node2);

		Node dup = newDoc.importNode(doc.getFirstChild(), true);
		node2.appendChild(dup);

		return newDoc;
	}

	public String getValue(Node node, String name) {
		return node.getAttributes().getNamedItem(name).getNodeValue();
	} 

	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Dimension dim = this.getSize();
		g.drawLine(0, dim.height/2, dim.width, dim.height/2);
		g.drawLine(dim.width/2, 0, dim.width/2, dim.height);
		g.drawString("Positive market trend", dim.width-155, dim.height/2);
		g.drawString("Negative market trend", 0, dim.height/2);
		g.drawString("Fibonacci", dim.width/2-35, 50);

		if(doc != null) {
			Node root = doc.getChildNodes().item(0);
			NodeList stockNodes = root.getChildNodes();
			for(int i = 0; i < stockNodes.getLength(); i++) {

				Node respondNode = stockNodes.item(i).getChildNodes().item(0);
				NodeList nodes = respondNode.getChildNodes();

				try {
					double current = MyUtil.parseN(getValue(nodes.item(nodes.getLength()-1), "cp")).doubleValue();
					double low = MyUtil.parseN(getValue(nodes.item(nodes.getLength()-1), "lp")).doubleValue();
					double high = MyUtil.parseN(getValue(nodes.item(nodes.getLength()-1), "hp")).doubleValue();

					// Get market trend
					double trend = Formulas.mean(nodes, 200);
					double oscillator = Formulas.stochastic(nodes);
					double fibonacci = Formulas.fibonacci(nodes);
					System.out.println("namn=" + getValue(stockNodes.item(i), "name") + " fibonacci=" + fibonacci);
					g.setColor(Color.black);
					if(oscillator >= 70) {
						g.setColor(Color.red);
					}
					if(oscillator <= 30) {
						g.setColor(Color.green);
					}
					g.drawOval( 
							(int) (dim.width/2 + 2 * dim.width*(current-trend)/current), 
							(int) (dim.height/2 - 2 * dim.height*fibonacci), 3, 3);
					
					g.drawString(getValue(stockNodes.item(i), "name"), 
							(int) (dim.width/2 + 2 * dim.width*(current-trend)/current), 
							(int) (dim.height/2 - 2 * dim.height*fibonacci)-2);
					
					if(Formulas.singleRichochette(nodes)) {
						g.drawOval( 
								(int) (dim.width/2 + 2 * dim.width*(current-trend)/current)-2, 
								(int) (dim.height/2 - 2 * dim.height*fibonacci)-1, 7, 7);
					}
					
					if(Formulas.doubleRichochette(nodes)) {
						g.drawOval( 
								(int) (dim.width/2 + 2 * dim.width*(current-trend)/current)-4, 
								(int) (dim.height/2 - 2 * dim.height*fibonacci)-1, 9, 9);
					}

					if(high >= Formulas.donchianHigh(nodes, 20)) {
						g.drawLine( 
								(int) (dim.width/2 + 2 * dim.width*(current-trend)/current), 
								(int) (dim.height/2 - 2 * dim.height*fibonacci),
								(int) (dim.width/2 + 2 * dim.width*(current-trend)/current)+3, 
								(int) (dim.height/2 - 2 * dim.height*fibonacci));
					}

					if(low <= Formulas.donchianLow(nodes, 20)) {
						g.drawLine( 
								(int) (dim.width/2 + 2 * dim.width*(current-trend)/current), 
								(int) (dim.height/2 - 2 * dim.height*fibonacci)+4,
								(int) (dim.width/2 + 2 * dim.width*(current-trend)/current)+3, 
								(int) (dim.height/2 - 2 * dim.height*fibonacci)+4);
					}
				} 
				catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		try {
			doc = MyXmlUtil.createDocumentFromString("<stocks/>");

			for(String stock[] : stocks) {
				MyInformationTable.getInstance().debug("Stock: " + stock[1]);
				MyInformationTable.getInstance().debug("getDataSeriers=" + MyXmlUtil.documentToString(MyBuyAnalysisChart.fix(stock[1], stock[0], OmxData.getStockData(new MyTimeData("1 year", Calendar.YEAR, 1), stock[0]))));
				doc = MyXmlUtil.merge(doc, MyBuyAnalysisChart.fix(stock[1], stock[0], OmxData.getStockData(new MyTimeData("1 year", Calendar.YEAR, 1), stock[0])));
			}
			MyInformationTable.getInstance().info("> " + MyXmlUtil.documentToString(doc)); 
			this.repaint();
		} 
		catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
