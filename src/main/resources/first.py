import pygal
from pygal.style import NeonStyle
import polyglot


class ChartVisualizer:
    def render(self, data):
        Line_Chart = pygal.StackedLine(fill=True, interpolate='cubic', style=NeonStyle)
        Line_Chart.title = data.name
        Line_Chart.x_labels = map(str, range(data.earliest, data.latest))
        Line_Chart.add('Number of invocations', data.values)

        return Line_Chart.render_data_uri()

polyglot.export_value("ChartVisualizer", ChartVisualizer)