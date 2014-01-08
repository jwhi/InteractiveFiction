package com.jwhi.interactivefiction.game;

import java.io.IOException;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class PlayerCommandAnalyzer {

	public static MaxentTagger tagger;
	
	public PlayerCommandAnalyzer() {
		tagger = new MaxentTagger("res/taggers/english-bidirectional-distsim.tagger");
	}
	
	public String toTagged(String cmd) throws IOException {
		return tagger.tagString(cmd);
	}
	
	public String[] splitCommand(String cmd) {
		String str = "";
		try {
			str = toTagged(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.split("\\s*[^a-zA-Z]+\\s*");
	}
	
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
	
	public String getVerb(String cmd) {
		return getSelectedTag(cmd, "VB");
	}
	
	public String getNoun(String cmd) {
		return getSelectedTag(cmd,"NN");
	}
	
	public void usePrimaryTagger(boolean val) {
		if (val) {
			tagger = new MaxentTagger("res/taggers/english-bidirectional-distsim.tagger");
		} else {
			tagger = new MaxentTagger("res/taggers/wsj-0-18-bidirectional-nodistsim.tagger");
		}
	}
}
