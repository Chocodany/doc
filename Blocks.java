import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Blocks {
	private int number;
	private String miner;
	static ArrayList<Blocks> blocks = new ArrayList<Blocks>();
	
	//Constructors
	public Blocks() {
	 
	}
	public Blocks(int number) {
		this.number = number;
	}
	public Blocks(int number, String miner) {
		this.number = number;
		this.miner = miner;
		//System.out.println("Block Number " + "Miner Address: " + miner);
	}
	
	//Getters
	public int getNumber() {
		return number;
	}
	public String getMiner() {
		return miner;
	}
	public static ArrayList<Blocks> getBlocks(){
		ArrayList<Blocks> copy = new ArrayList<Blocks>();
		for(int i = 0; i < blocks.size(); i++) {
			copy.add(blocks.get(i));
		}
		return copy;
	}
	
	//Methods
	public String toString() {
		if ((number == 0) && (miner == null)) {
			return "Empty Block";
		}
		if ((number != 0) && (miner == null)) {
			return "Block Number: " + number;
		}
		if ((number != 0) && (miner != null)) {
			return "Block Number: " + number + " Miner Address: " + miner;
		} else {
			return "";
		}
	}
	public static void calUniqMiners() {
		ArrayList<String> miners = new ArrayList<>();
		ArrayList<String> calUniq = new ArrayList<>();
		
		for(int i = 0; i < blocks.size() - 1; i++) {
			String temp = blocks.get(i).getMiner();
			miners.add(i, temp);
		}
		
		for (int i = 0; i < blocks.size() - 1; i++) {
			String temp = blocks.get(i).getMiner();
			boolean isDuplicate = false;
			if(Collections.frequency(miners, temp) > 1)
				isDuplicate = true;
			if (isDuplicate == false)
				calUniq.add(temp);
		}
		
		System.out.println("Number of unique Miners: " + calUniq.size());
		System.out.println();
		System.out.println("Each unique Miner and its frequency:");
		for (int i = 0; i < blocks.size() - 1; i++) {
		System.out.println("Miner Address: " + miners.get(i));
		System.out.println("Miner Frequency: " + Collections.frequency(miners, miners.get(i)));
		System.out.println();
		}
		
	}
	
	public static int blockDiff(Blocks A, Blocks B) {
		return A.getNumber() - B.getNumber();
	}
	
	public static Blocks getBlockByNumber(int num) {
		Blocks index = new Blocks();
		index = null;
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).number == num) {
				index = blocks.get(i);
				break;
			}
		}
		if (index != null) {
			return index;
		} else {
			return null;
		}
	}
	
	public static void readFile(String filename) throws FileNotFoundException, IOException {
		BufferedReader scnr = new BufferedReader (new FileReader (filename));
		blocks = new ArrayList<Blocks>();
		String blockInfo[];
		String lines = scnr.readLine();
		while (lines != null) {
			blockInfo = lines.split(",");
			blocks.add(new Blocks(Integer.parseInt(blockInfo[0]), blockInfo[9]));
			lines = scnr.readLine();
		}
		scnr.close();
	}
}
