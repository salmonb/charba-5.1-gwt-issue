package issue;

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;
import org.pepstock.charba.client.Charba;
import org.pepstock.charba.client.StackedAreaChart;
import org.pepstock.charba.client.configuration.*;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.StackedAreaDataset;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.Position;

import java.util.List;


/**
 * @author Bruno Salmon
 */
public final class CharbaIssue implements EntryPoint {

    private final static String[] labels = {"Europe", "North America", "Asia", "South America", "Oceania", "Africa"};
    private final static double[] figures = {1609, 1223, 193, 186, 153, 21};

    static {
        Charba.enable(); // For embedded resources
    }

    @Override
    public void onModuleLoad() {
        StackedAreaChart chartWidget = new StackedAreaChart();
        DomGlobal.document.body.appendChild(chartWidget.getChartElement().as());
        StackedOptions options = chartWidget.getOptions();
        options.setResponsive(true);
        options.getLegend().setDisplay(true);
        options.getLegend().setPosition(Position.BOTTOM);
        options.getTooltips().setMode(InteractionMode.INDEX);
        options.getTooltips().setIntersect(false);
        options.getHover().setMode(InteractionMode.NEAREST);
        options.getHover().setIntersect(true);
        options.getElements().getPoint().setRadius(0.5); // 1px dot
        options.getElements().getLine().setTension(0.0); // No Bezier interpolation
        Scales scales = options.getScales();
        Axis xAxis = new CartesianCategoryAxis(chartWidget);
        xAxis.setDisplay(true);
        Axis yAxis = new CartesianLinearAxis(chartWidget);
        yAxis.setDisplay(true);
        scales.setAxes(xAxis, yAxis);
        List<Dataset> datasets = chartWidget.getData().getDatasets(true);
        StackedAreaDataset dataset = chartWidget.newDataset();
        dataset.setBackgroundColor(context -> "blue");
        dataset.setBorderColor(context -> "red");
        dataset.setBorderWidth(context -> 1);
        dataset.setLabel("Figures");
        datasets.add(dataset);
        chartWidget.getData().setXLabels(labels);
        dataset.setData(figures);
        chartWidget.update();
    }

}
