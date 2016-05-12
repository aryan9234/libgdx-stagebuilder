package net.peakgames.libgdx.stagebuilder.core.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import net.peakgames.libgdx.stagebuilder.core.assets.AssetsInterface;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;
import net.peakgames.libgdx.stagebuilder.core.model.BaseModel;
import net.peakgames.libgdx.stagebuilder.core.model.GroupModel;
import net.peakgames.libgdx.stagebuilder.core.model.OneDimensionGroupModel;
import net.peakgames.libgdx.stagebuilder.core.services.LocalizationService;
import net.peakgames.libgdx.stagebuilder.core.xml.XmlModelBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class VerticalHorizontalGroupBuilderTest {

    private XmlModelBuilder builder;
    private AssetsInterface assets = Mockito.mock(AssetsInterface.class);
    private ResolutionHelper resolutionHelper = Mockito.mock(ResolutionHelper.class);
    private LocalizationService localizationService = Mockito.mock(LocalizationService.class);

    @BeforeClass
    public static void beforeClass() {
        Gdx.files = new LwjglFiles();
    }

    @Before
    public void setup() {
        builder = new XmlModelBuilder();
    }

    private FileHandle fileHandle(String fileName) {
        return Gdx.files.internal("layout/" + fileName);
    }
    
    @Test
    public void getting_values_from_xml() throws Exception {
        List<BaseModel> models = builder.buildModels(fileHandle("vertical_horizontal_layout.xml"));
        assertEquals(1, models.size());
        GroupModel group = (GroupModel)models.get(0);
        assertEquals(2, group.getChildren().size());
        
        OneDimensionGroupModel horizontalGroupModel = (OneDimensionGroupModel) group.getChildren().get(0);
        List<BaseModel> children = horizontalGroupModel.getChildren();
        assertEquals("1", children.get(0).getName());
        assertEquals("2", children.get(1).getName());
        assertEquals("3", children.get(2).getName());
        assertTrue(horizontalGroupModel.isReverse());
        assertFalse(horizontalGroupModel.isFill());
        assertNull(horizontalGroupModel.getPads());
        assertEquals(1, horizontalGroupModel.getPadBottom(), 0);
        assertEquals(0, horizontalGroupModel.getSpacing(), 0);
        assertEquals(100, horizontalGroupModel.getWidth(), 0);
        assertEquals(Align.top, horizontalGroupModel.getAlign());

        OneDimensionGroupModel verticalGroupModel = (OneDimensionGroupModel) group.getChildren().get(1);
        List<BaseModel> verticalChildren = verticalGroupModel.getChildren();
        assertEquals("1", verticalChildren.get(0).getName());
        assertEquals("2", verticalChildren.get(1).getName());
        assertEquals("3", verticalChildren.get(2).getName());

        assertTrue(verticalGroupModel.isReverse());
        assertFalse(verticalGroupModel.isFill());
        assertArrayEquals(new float[] {10, 10, 20, 20}, verticalGroupModel.getPads(), 0);
        assertEquals(OneDimensionGroupModel.DEFAULT_ALIGNMENT, verticalGroupModel.getAlign());
    }
    
    @Test
    public void building_actual_actors_from_models() throws Exception {
        float posMultip = 1.2f;
        
        float width = 480;
        float height = 800;
        StageBuilder stageBuilder = new StageBuilder(assets, resolutionHelper, localizationService);
        Mockito.when(resolutionHelper.getScreenWidth()).thenReturn(width);
        Mockito.when(resolutionHelper.getScreenHeight()).thenReturn(height);
        Mockito.when(resolutionHelper.getPositionMultiplier()).thenReturn(posMultip);
        
        Group root = stageBuilder.buildGroup("vertical_horizontal_layout.xml");
        HorizontalGroup horizontalGroup = root.findActor("horizontal");
        
        assertEquals(10 * posMultip, horizontalGroup.getX(), 0.1f);
        assertEquals(100 * posMultip, horizontalGroup.getWidth(), 0.1f);
        assertEquals(1 * posMultip, horizontalGroup.getPadBottom(), 0.1f);

        VerticalGroup verticalGroup = root.findActor("vertical");

        assertEquals(10 * posMultip, verticalGroup.getX(), 0.1f);
        assertEquals(10 * posMultip, verticalGroup.getWidth(), 0.1f);
        assertEquals(10 * posMultip, verticalGroup.getPadTop(), 0.1f);
        assertEquals(10 * posMultip, verticalGroup.getPadLeft(), 0.1f);
        assertEquals(20 * posMultip, verticalGroup.getPadBottom(), 0.1f);
        assertEquals(20 * posMultip, verticalGroup.getPadRight(), 0.1f);
        assertEquals(15 * posMultip, verticalGroup.getSpace(), 0.1f);
    }
}
