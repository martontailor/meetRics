package com.mtailor.meetrics.service.filter;

import com.mtailor.meetrics.data.dao.MetricDAO;
import com.mtailor.meetrics.model.filter.BasicFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class MetricDAOResultFilterTest {

    private static final MetricDAO GOOD_ITEM_1 = new MetricDAO().setTimestamp(10L).setMetricName("TestName");
    private static final MetricDAO GOOD_ITEM_2 = new MetricDAO().setTimestamp(12L).setMetricName("TestName");
    private static final MetricDAO GOOD_ITEM_3 = new MetricDAO().setTimestamp(11L).setMetricName("TestName");
    public MetricDAOResultFilter filter;

    @BeforeEach
    public void setUp() {
        filter = new MetricDAOResultFilter();
    }

    @Test
    public void testFilterEntriesOutputShouldContainElementsBasedOnFilter() {
        //given
        BasicFilter basicFilter = new BasicFilter("TestName", 10L, 12L);

        //when
        List<MetricDAO> actual = getInputList().stream().filter(filter.filterEntries(basicFilter))
                .toList();

        //then
        assertEquals(getExpected(), actual);
    }

    private List<MetricDAO> getInputList() {
        MetricDAO metricWithGoodNameAndBadTimestamp = new MetricDAO().setTimestamp(19L).setMetricName("TestName");
        MetricDAO metricWithGoodNameAndBadTimestamp2 = new MetricDAO().setTimestamp(5L).setMetricName("TestName");
        MetricDAO metricWithBadNameAndGoodTimestamp = new MetricDAO().setTimestamp(12L).setMetricName("TestName2");
        MetricDAO metricWithBadNameAndBadTimestamp = new MetricDAO().setTimestamp(14L).setMetricName("TestName2");

        return List.of(GOOD_ITEM_1, GOOD_ITEM_2, GOOD_ITEM_3,
                metricWithGoodNameAndBadTimestamp, metricWithGoodNameAndBadTimestamp2, metricWithBadNameAndGoodTimestamp,
                metricWithBadNameAndBadTimestamp);
    }

    public List<MetricDAO> getExpected() {
        return List.of(GOOD_ITEM_1, GOOD_ITEM_2, GOOD_ITEM_3);
    }

}