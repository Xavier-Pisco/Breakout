package com.g19.breakout.controller;

import com.g19.breakout.controller.ball.*;
import com.g19.breakout.elements.Chronometer;
import com.g19.breakout.elements.Dimensions;
import com.g19.breakout.elements.Position;
import com.g19.breakout.model.ArenaModel;
import com.g19.breakout.model.BallModel;
import com.g19.breakout.model.PlayerModel;
import com.g19.breakout.model.TileModel;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

public class CollisionCheckerTests {
    ArenaModel arena;
    BallModel ball;
    CollisionChecker collisionChecker;

    @BeforeEach
    @BeforeProperty
    public void setup() {
        arena = Mockito.mock(ArenaModel.class);
        collisionChecker = new CollisionChecker(arena, new Chronometer());
        ball = Mockito.mock(BallModel.class);
        Mockito.when(arena.getBall()).thenReturn(ball);
        Mockito.when(arena.getWidth()).thenReturn(100);
        Mockito.when(arena.getHeight()).thenReturn(120);
        Mockito.when(ball.getDimensions()).thenReturn(new Dimensions(2, 1));
    }

    @Test
    public void checkHitTopOrSideTest(){

        TileModel tile = Mockito.mock(TileModel.class);
        Mockito.when(tile.getDimensions()).thenReturn(new Dimensions(1, 1));
        Mockito.when(tile.getPosition()).thenReturn(new Position(10, 10));

        Position position = new Position(9, 10);
        Mockito.when(ball.getPosition()).thenReturn(position);
        assertEquals(BallHitVertical.class, collisionChecker.checkHitTopOrSideTile(tile).getClass());

        position = new Position(10, 11);
        Mockito.when(ball.getPosition()).thenReturn(position);
        assertEquals(BallHitHorizontal.class, collisionChecker.checkHitTopOrSideTile(tile).getClass());
    }

    @Test
    public void checkBallCollisionsTest(){
        CollisionChecker collisionCheckerSpy = Mockito.spy(collisionChecker);

        collisionCheckerSpy.checkBallCollisions(new Position(10, 10), new Dimensions(1, 1));

        Mockito.verify(arena, times(1)).checkHitPlayer(any(), any());

        Mockito.verify(arena, times(1)).checkHitTile(any());

        Mockito.verify(collisionCheckerSpy, times(1)).checkBallHitArenaWalls(any(), any());



        /*Position position = new Position(this.dimensions.getDiscreteX() / 2., 11);
        List<TileModel> tiles = new ArrayList<>();
        tiles.add(new TileModel(new Position(this.dimensions.getDiscreteX()/2., 8), new Dimensions(10, 4), 5));
        tiles.add(new TileModel(new Position(this.dimensions.getDiscreteX()/2. + 20, 8), new Dimensions(10, 4), 5));
        arena.setTiles(tiles);

        PlayerModel player = Mockito.mock(PlayerModel.class);
        doNothing().when(player).addPoints(any(Integer.class));
        Mockito.when(arena.getPlayer()).thenReturn(player);
        Mockito.when(player.getPosition()).thenReturn(new Position(50, 100));
        Mockito.when(player.getDimensions()).thenReturn(new Dimensions(10, 1));
        Mockito.when(ball.getPosition()).thenReturn(position);

        BallHit ballHit = collisionChecker.checkBallCollisions(position, new Dimensions(2, 1));
        assertEquals(BallHitHorizontal.class, ballHit.getBallHit().getClass());

        Mockito.when(ball.getPosition()).thenReturn(new Position(50, 100));

        position = new Position(50, 100);
        ballHit = collisionChecker.checkBallCollisions(position, new Dimensions(2, 1));
        assertEquals(BallHitPlayerBar.class, ballHit.getBallHit().getClass());*/
    }

    @Property
    public void checkBallHitArenaWallsTest(@ForAll int x, @ForAll int y, @ForAll @IntRange(min = 1) int dx, @ForAll @IntRange(min = 1) int dy) {
        Position position = new Position(x, y);
        Dimensions dimensions = new Dimensions(dx, dy);
        BallHit ballHit = collisionChecker.checkBallHitArenaWalls(position, dimensions);

        assert(position.getDiscreteY() <= -1 || !(BallHitHorizontal.class == ballHit.getBallHit().getClass()));

        assert(position.getDiscreteY() >= arena.getHeight() - dimensions.getDiscreteY() + 1 || !(BallHitBottom.class == ballHit.getBallHit().getClass()));

        assert((position.getDiscreteX() <= dimensions.getDiscreteX()/2. - 1 ||
                position.getDiscreteX() >= arena.getWidth() - dimensions.getDiscreteX()/2. + 1) || !(BallHitVertical.class == ballHit.getBallHit().getClass()));
    }
}
