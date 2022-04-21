package tetris;

import java.util.LinkedList;
import java.util.Queue;

public class PieceQueue {

    private final Queue<Piece> queue = new LinkedList<>();

    public PieceQueue() {
        addBatch();
    }

    private void addBatch() {
        queue.addAll(PieceFactory.getShuffledBatch());
    }

    public Piece pop() {
        Piece fairlyFresh = queue.poll();
        if (queue.size() < PieceFactory.getPoolSize()) {
            addBatch();
        }
        return fairlyFresh;
    }

    public Queue<Piece> getQueue() {
        return queue;
    }
}
