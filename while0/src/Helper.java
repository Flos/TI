import java.util.HashMap;
import java.util.Map;


public class Helper {
	
	public static String transformiereLabel(String sequenz){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		String[] zeilen = sequenz.split("\n");
		
		int zeilennummer = 1;
		for (String zeile : zeilen) {
			if (zeile.startsWith("LABEL")){
				
				int pos = zeile.indexOf(" ");
				String labelNummerString = zeile.substring(5, pos);
				int labelNummer = Integer.parseInt(labelNummerString);
				
				map.put(labelNummer, zeilennummer);	
			}
			++zeilennummer;
		}
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			String zeilenNummer = "" + entry.getValue();
			sequenz = sequenz.replaceAll("goto LABEL" + entry.getKey(), "goto " + zeilenNummer);
		}
		
		sequenz = sequenz.replaceAll("LABEL[0-9]+ ", "");
		
		return sequenz;
	}
}
