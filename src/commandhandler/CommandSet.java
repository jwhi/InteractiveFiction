package commandhandler;

import java.io.IOException;
import java.util.ArrayList;


public class CommandSet {
	private PlayerCommandAnalyzer analyzer;
	
	// Was having trouble with this loading more than once. Wanted to do a null check to speed load times
	public CommandSet() {
		if (analyzer == null) {
			analyzer = new PlayerCommandAnalyzer();
		}
	}
	
	
	//Analyzer uses abbreviations from the Penn Treebank Tagset fount at http://www.computing.dcu.ie/~acahill/tagset.html
	// NN: Noun, singular or mass
	// NNS: Noun, plural
	public ArrayList<String> getNouns(String cmd) {
		String[] parsedCommand = analyzer.splitCommand(cmd);
		ArrayList<String> nouns = new ArrayList<String>();
		for (int i = 0; i < parsedCommand.length; i++) {
			if (parsedCommand[i].equalsIgnoreCase("NN") || parsedCommand[i].equalsIgnoreCase("NNS")) {
				nouns.add(parsedCommand[i-1]);
			}
		}
		
		return nouns;
	}
	
	// VB: Verb, base form
		public ArrayList<String> getVerbs(String cmd) {
			String[] parsedCommand = analyzer.splitCommand(cmd);
			ArrayList<String> verbs = new ArrayList<String>();
			for (int i = 0; i < parsedCommand.length; i++) {
				if (parsedCommand[i].equalsIgnoreCase("VB") || (parsedCommand[i].equalsIgnoreCase("VBN"))) {
					verbs.add(parsedCommand[i-1]);
				}
			}
			
			return verbs;
		}
		
		// RB: Adverb
		public ArrayList<String> getAdverbs(String cmd) {
			String[] parsedCommand = analyzer.splitCommand(cmd);
			ArrayList<String> adverbs = new ArrayList<String>();
			for (int i = 0; i < parsedCommand.length; i++) {
				if (parsedCommand[i].equalsIgnoreCase("RB")) {
					adverbs.add(parsedCommand[i-1]);
				}
			}
			
			return adverbs;
		}
		
		// RP: Particle
		public ArrayList<String> getParticles(String cmd) {
			String[] parsedCommand = analyzer.splitCommand(cmd);
			ArrayList<String> particles = new ArrayList<String>();
			for (int i = 0; i < parsedCommand.length; i++) {
				if (parsedCommand[i].equalsIgnoreCase("RP")) {
					particles.add(parsedCommand[i-1]);
				}
			}
			
			return particles;
		}
		
		// Allows the scene to get the tagged sentence from the tagger.
		public String getTags(String cmd) throws IOException{
			return analyzer.toTagged(cmd);
		}
}
