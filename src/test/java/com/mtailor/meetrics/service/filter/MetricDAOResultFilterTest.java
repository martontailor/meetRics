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

    private static final MetricDAO GOOD_ITEM_1 = MetricDAO.builder().timestamp(10L).metricName("TestName").build();
    private static final MetricDAO GOOD_ITEM_2 = MetricDAO.builder().timestamp(12L).metricName("TestName").build();
    private static final MetricDAO GOOD_ITEM_3 = MetricDAO.builder().timestamp(11L).metricName("TestName").build();
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
        List<MetricDAO> actual = getInputList().stream().filter(filter.filterEntries(basicFilter)).toList();

        //then
        assertEquals(getExpected(), actual);
    }

    private List<MetricDAO> getInputList() {
        MetricDAO metricWithGoodNameAndBadTimestamp = MetricDAO.builder().timestamp(19L).metricName("TestName").build();
        MetricDAO metricWithGoodNameAndBadTimestamp2 = MetricDAO.builder().timestamp(5L).metricName("TestName").build();
        MetricDAO metricWithBadNameAndGoodTimestamp = MetricDAO.builder().timestamp(12L).metricName("TestName2").build();
        MetricDAO metricWithBadNameAndBadTimestamp = MetricDAO.builder().timestamp(14L).metricName("TestName2").build();

        return List.of(GOOD_ITEM_1, GOOD_ITEM_2, GOOD_ITEM_3, metricWithGoodNameAndBadTimestamp,
                metricWithGoodNameAndBadTimestamp2, metricWithBadNameAndGoodTimestamp, metricWithBadNameAndBadTimestamp);
    }

    public List<MetricDAO> getExpected() {
        return List.of(GOOD_ITEM_1, GOOD_ITEM_2, GOOD_ITEM_3);
    }

}