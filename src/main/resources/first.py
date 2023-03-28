import polyglot
import pygal
import time
from pygal.style import NeonStyle


class ChartVisualizer:
    def render(self, data):
        Line_Chart = pygal.XY(style=NeonStyle, x_label_rotation=45,truncate_label=-1,
                              x_labels_major_count=3,show_minor_x_labels=False,
                              x_value_formatter=lambda dt: time.strftime('%Y-%m-%d %H:%M:%S',
                                                                       time.gmtime(dt / 1000.0))
                              )
        Line_Chart.title = data.name()

        list_of_tuples = []

        for tuple in data.metrics():
            datapoint = (tuple.timeInMs(), tuple.value())
            list_of_tuples.append(datapoint)

        Line_Chart.add('Number of invocations', list_of_tuples)

        return Line_Chart.render_data_uri()

polyglot.export_value("ChartVisualizer", ChartVisualizer)