package com.jwhi.interactivefiction.commandhandler;

import java.io.IOException;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class PlayerCommandAnalyzer {

	public static MaxentTagger tagger;
	
	public PlayerCommandAnalyzer() {
		tagger = new MaxentTagger("res/taggers/english-bidirectional-distsim.tagger");
	}
	
	// Returns the command with the tags added to each word.
	// Example Input: This is a sample sentence
	// Example Output: This_DT is_VBZ a_DT sample_NN sentence_NN
	// Uses abbreviations from the Penn Treebank Tagset fount at http://www.computing.dcu.ie/~acahill/tagset.html
	public String toTagged(String cmd) throws IOException {
		return tagger.tagString(cmd);
	}
	
	// Splits the tagged sentence into a string array. Each word is followed by it's tag in the array
	public String[] splitCommand(String cmd) {
		String str = "";
		try {
			str = toTagged(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.split("\\s*[^a-zA-Z]+\\s*");
	}
	
	// Returns the chosen tag if it appears in the sentence
	public String getSelectedTag(String cmd, String tag) {
		String[] str = splitCommand(cmd);
		for (int i = 0; i < str.length; i++) {
			if (str[i].compareToIgnoreCase(tag) == 0) {
				return str[i-1];
			}
		}
		System.out.println(str.length);
		return "";
	}
}
