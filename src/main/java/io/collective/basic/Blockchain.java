package io.collective.basic;

import java.util.ArrayList;
import java.util.List;

import java.security.NoSuchAlgorithmException;

public class Blockchain {
    private List<Block> chain = new ArrayList<>();

    public boolean isEmpty() {
        return chain.isEmpty();
    }
    public void add(Block block) {
        chain.add(block);
    }
    public int size() {
        return chain.size();
    }
    public boolean isValid() throws NoSuchAlgorithmException {
        // todo - check an empty chain
        if (chain.isEmpty()){return true;}
        // todo - check a chain of one
        if (chain.size() == 1){
            Block block = chain.get(0);
            if (block.getHash().contains(" ") || !isMined(block)){
                return false;
            }
        }
        // todo - check a chain of many
        if (chain.size() > 1) {
            for (int i = 1; i < chain.size(); i++) {
                Block current = chain.get(i);
                Block previous = chain.get(i-1);
                boolean correctPreviousHash = previous.getHash().equals(current.getPreviousHash());
                boolean correctCurrentHash = current.getHash().equals(current.calculatedHash());
                if (!correctPreviousHash|| !correctCurrentHash || !isMined(current) ) {
                    return false;
                }
            }
        }
        return true;
    }

    /// Supporting functions that you'll need.
    public static Block mine(Block block) {
        Block mined = new Block(block.getPreviousHash(), block.getTimestamp(), block.getNonce());
        while (!isMined(mined)) {
            mined = new Block(mined.getPreviousHash(), mined.getTimestamp(), mined.getNonce() + 1);
        }
        return mined;
    }

    public static boolean isMined(Block mined) {
        return mined.getHash().startsWith("00");
    }
}