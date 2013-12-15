package commandhandler;

import java.io.IOException;
import java.util.ArrayList;


public class CommandSet {
	private PlayerCommandAnalyzer analyzer;
	private ArrayList<String> Nouns;
	private ArrayList<String> Verbs;
	private ArrayList<String> Adverbs;
	private ArrayList<String> Particles;
	
	// Was having trouble with this loading more than once. Wanted to do a null check to speed load times
	public CommandSet() {
		if (analyzer == null) {
			analyzer = new PlayerCommandAnalyzer();
		}
	}
	
	
	//Analyzer uses abbreviations from the Penn Treebank Tagset fount at http://www.computing.dcu.ie/~acahill/tagset.html
	// NN: Noun, singular or mass
	// NNS: Noun, plural
	// VB: Verb, base form
	// RB: Adverb
	// RP: Particle
	public void analyzeCommand(String cmd) {
		String[] parsedCommand = analyzer.splitCommand(cmd);
		Nouns = new ArrayList<String>();
		Verbs = new ArrayList<String>();
		Adverbs = new ArrayList<String>();
		Particles = new ArrayList<String>();
		// If only a two word command sentence entered, assume they entered a command in the "verb noun" format
		// Example: light fire; go north
		if (parsedCommand.length == 4) {
			Nouns.add(parsedCommand[2]);
			Verbs.add(parsedCommand[0]);
			return;
		} else {
			for (int i = 0; i < parsedCommand.length; i++) {
				if (parsedCommand[i].equalsIgnoreCase("NN") || parsedCommand[i].equalsIgnoreCase("NNS")) {
					Nouns.add(parsedCommand[i-1]);
				} else if (parsedCommand[i].equalsIgnoreCase("VB") || (parsedCommand[i].equalsIgnoreCase("VBN"))) {
					Verbs.add(parsedCommand[i-1]);
				} else if (parsedCommand[i].equalsIgnoreCase("RB")) {
					Adverbs.add(parsedCommand[i-1]);
				} else if (parsedCommand[i].equalsIgnoreCase("RP")) {
					Particles.add(parsedCommand[i-1]);
				}
			}
		}
	}
	
	public ArrayList<String> getNouns() {
		return Nouns;
	}
	
	public ArrayList<String> getVerbs() {
		return Verbs;
	}
		
	public ArrayList<String> getAdverbs() {
		return Adverbs;
	}
		
	public ArrayList<String> getParticles() {
		return Particles;
	}
		
	// Allows the scene to get the tagged sentence from the tagger.
	public String getTags(String cmd) throws IOException{
		return analyzer.toTagged(cmd);
	}
}
