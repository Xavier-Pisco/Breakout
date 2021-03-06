package com.g19.breakout.view;

import com.g19.breakout.view.factory.ViewFactory;
import com.g19.breakout.view.graphics.Graphics;
import com.g19.breakout.view.graphics.LanternaAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ArenaViewTests {
    @Mock private Graphics graphics;
    @Mock private ViewFactory factory;

    private ArenaView view;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void testDraw() throws IOException {
        BallView ballViewMock = Mockito.mock(BallView.class);
        PlayerView playerViewMock = Mockito.mock(PlayerView.class);
        TilesView tilesViewMock = Mockito.mock(TilesView.class);

        Mockito.when(factory.createBallView(any(BallModel.class), any(Graphics.class))).thenReturn(ballViewMock);
        Mockito.when(factory.createPlayerBarView(any(PlayerModel.class), any(Graphics.class))).thenReturn(playerViewMock);
        Mockito.when(factory.createTilesView(any(TileModel.class), any(Graphics.class))).thenReturn(tilesViewMock);


        view = new ArenaView(new ArenaModel(new Dimensions(100, 100)), graphics, factory);

        ArenaView view1 = Mockito.spy(view);
        view1.draw();

        verify(view1, times(1)).drawBackground(any(ArenaModel.class));
        verify(graphics, times(1)).startDrawing();
        verify(graphics, times(1)).stopDrawing();
        verify(tilesViewMock, times(1)).draw(any());
        verify(playerViewMock, times(1)).draw(any(PlayerModel.class));
        verify(ballViewMock, times(1)).draw(any(BallModel.class));
    }

    @Test
    public void testDrawBackground() {
        view = new ArenaView(new ArenaModel(new Dimensions(100, 100)), graphics, factory);

        ArenaModel arena = Mockito.mock(ArenaModel.class);
        view.drawBackground(arena);
        verify(graphics, times(1)).drawRectangle(new Position(0, 0), new Position(arena.getWidth(), arena.getHeight()), ' ', view.getBGColor());
    }

    @Test
    public void testReadInput() throws IOException {
        view = new ArenaView(new ArenaModel(new Dimensions(100, 100)), graphics, factory);

        view.readInput();
        verify(graphics, times(1)).readInput();
    }*/
}
